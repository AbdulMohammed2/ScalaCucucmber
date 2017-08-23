package uk.anatwine.automation.base

/**
  * Created by sumohamm on 16/08/2017.
  * 
  */


import scala.util.Random
import scala.xml._
import scala.xml.transform._


object TransformTest extends App {



  val catalogXml =
    <catalogData>
      <catalogs>
      <book id="bk101">
        <author>Gambardella, Matthew</author>
        <title>XML Developer's Guide</title>
        <genre>Computer</genre>
        <price>44.95</price>
        <publish_date>2000-10-01</publish_date>
        <description>An in-depth look at creating applications
          with XML.</description>
      </book>
      <book id="bk102">
        <author>Ralls, Kim</author>
        <title>Midnight Rain</title>
        <genre>Fantasy</genre>
        <price>5.95</price>
        <publish_date>2000-12-16</publish_date>
        <description>A former architect battles corporate zombies,
          an evil sorceress, and her own childhood to become queen
          of the world.</description>
      </book>
      </catalogs>
    </catalogData>




  def randomPrice(dom: Node): Node = {

    object ReplacePrice extends RewriteRule {

      override def transform(n: Node): Seq[Node] = {
        n match {

          case Elem(prefix, "price", attribs, scope, _*) =>
            val r = new Random()
            <price>
              {r.nextInt(50)}
            </price>
          case other =>
            other
        }
      }
    }

    // Subclass a RuleTransformer (because it's abstract), handing
    // it a vararg list of rules to use (in this case, just one).

    object Xtransform extends RuleTransformer(ReplacePrice)

    // Do the transform. (A scala function's return value is the
    // value of the last expression (and everything's an expression).

    Xtransform(dom)
  }


  println(s"Catalog xml : \n$catalogXml\n\n")
  val newCatalogXml = randomPrice(catalogXml)
  println(s"New modified Catalog xml : \n$newCatalogXml\n\n")
}
