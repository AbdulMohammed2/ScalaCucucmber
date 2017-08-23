package uk.anatwine.automation.stepdefs

import javax.xml.transform.Transformer

import cucumber.api.scala.{EN, ScalaDsl}
import org.scalatest.Matchers
import uk.anatwine.automation.base.{CsvReader, Stock}

import scala.collection.immutable.Stream.Empty
import scala.collection.mutable.ListBuffer
import scala.xml._

/**
  * Created by sumohamm on 27/07/2017.
  */
class StockUpload extends ScalaDsl with EN with Matchers{

  var stockFromFile:Seq[Stock] = Seq[Stock]()
  var xml:Elem =  <stockData xmlns="http://flipcar.com/schematest/brand/v2/en/stock"><stocks></stocks></stockData>
  var allEans = new ListBuffer[String]()
  Given("""^I (\d+) have an article already on sale with ([^"]*)$"""){ (brand:Int ,retailer:String) =>
    println("test stock avaialbe")
    val fileName = System.getProperty("stockFilePath", "./src/test/resources/stocks.txt")
    //println(fileName)
    //val resource = getClass.getResource("/stocks.txt").getPath
    //println(resource)
    val csvReader = new CsvReader(fileName)
    stockFromFile = csvReader.readStocks()
    stockFromFile.length should be > 0

    xml = csvReader.stockXml(stockFromFile)
    val newXml = csvReader.randomStockQuantity(xml)
    println(newXml)
    (xml \ "stocks") should not be empty
    val noOfStocks = (xml \\ "stock").length
    noOfStocks should be > 0
    for(i <- 0 until  noOfStocks)
      {
        val inner = ((xml \\ "stock")(i) \ "_")
        allEans += (inner.\\("ean").text)
        //allEans +: (inner.\\("ean").text)
        inner.length shouldBe(3)
        inner should not be empty
      }
    println(allEans)
    val xmlload = XML.loadFile("./src/test/resources/test.xml")

    val utx = new java.sql.Timestamp(System.currentTimeMillis())
    println(utx)
    val utc = java.time.Instant.now().getEpochSecond
    println(utc)

    val testListSubset= allEans.forall(List("VB48933", "VB58933", "VB88933", "VB68933", "VB78833","VB78844").contains)
    println(testListSubset)

    val x = Map("one" ->11,"two"->21,"three"->3 )
    val y = Map("one" ->18,"two"->24 )
    println(x.keySet.intersect(y.keySet))
    println(x.keySet.intersect(y.keySet).nonEmpty)
    val commonKeys = x.keySet.intersect(y.keySet)
    val result = for  {
      elem <- commonKeys
      value1 <- y.get(elem)
      value2 <- x.get(elem)
      diff = (value1 - value2)
    }yield(diff)

    (result.forall(x=>x==3)) shouldBe true


  }

  Then("""^I uploaded the updated stock feed to the (\d+) endpoint$"""){ (arg0:Int) =>

  }

  Then("""^the system has been updated with the new stock level$"""){ () =>

  }

  Then("""^the ([^"]*) receives the new stock levels$"""){ (retailer : String) =>

  }


}
