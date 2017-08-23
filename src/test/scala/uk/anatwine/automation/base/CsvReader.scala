package uk.anatwine.automation.base

import java.io.{FileNotFoundException, IOException}

import scala.io.Source
import resource._
import java.util.Random

import scala.xml._
import scala.xml.transform._


/**
  * Created by sumohamm on 27/07/2017.
  */

/**
  * Trait responsible for reading/loading [[Stock]].
  *
  * @author Luciano Molinari
  */
trait StockReader {

  /**
    * @return A [[Seq]] containing all the sales.
    */
  def readStocks(): Seq[Stock]

}

case class Stock(val sku: String, val ean: String, val quantity: Int)
{
  def stocks=
      <stock>
        <brandArticleSku>{sku}</brandArticleSku>
        <ean>{ean}</ean>
        <quantity>{quantity}</quantity>
      </stock>
}

class CsvReader(val fileName: String) extends StockReader {
  var stockFromFile:Seq[Stock] = Seq[Stock]()
  override def readStocks(): Seq[Stock] = {

    for (source <- managed(Source.fromFile(fileName))) {
      stockFromFile = for {
        line <- Source.fromFile(fileName).getLines().drop(1).toList
        values = line.split(",").map(_.trim)
      } yield Stock(values(0), values(1), values(2).toInt)
    }
    stockFromFile
  }

  def stockXml(stocks:Seq[Stock])={
    <stockData xmlns="http://flipcar.com/schematest/brand/v2/en/stock">
       <stocks>
          {stocks.map(stock => stock.stocks)}
       </stocks>
    </stockData>
  }

  def randomStockQuantity(dom: Node): Node = {

    object ReplaceStockQuantity extends RewriteRule {
      override def transform(n: Node): Seq[Node] = {
        n match {
          case Elem(prefix, "quantity", attribs, scope, _*) =>
            val r = new Random()
            <quantity>
              {r.nextInt(50)}
            </quantity>
          case other =>
            other
        }
      }
    }
    object transform extends RuleTransformer(ReplaceStockQuantity)
    transform(dom)
  }

}
