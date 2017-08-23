package uk.anatwine.automation.base

import java.io.{File, PrintWriter}

import org.apache.http.client.methods.HttpPost
import org.apache.http.entity.StringEntity
import org.apache.http.impl.client.HttpClientBuilder

import scala.util.parsing.json.JSON
import scala.xml.XML

/**
  * Created by SUMOHAMM on 02/05/2017.
  */
object RestFulAPIConnect {

  def main(args: Array[String]): Unit = {
    val contentReturn = getRestResponse("https://contactsapi.apispark.net/v1/companies/")
  }

  def getRestResponse(url:String): Map[String,String] = {

    val httpClient =  HttpClientBuilder.create().build()
    val httppost = new HttpPost(url)
    httppost.setHeader("Content-type", "application/xml")
    val xml = "<Company><tags><tags>IT</tags><tags>computer</tags><tags>maintenance</tags><tags>software</tags><tags>software consultancy</tags></tags><name>AZLAN IT Solutions</name><address><street>6 Rue Rose Dieng-kuntz</street><zipcode>44398</zipcode><city>NewYork</city></address></Company>"
    val entity = new StringEntity(xml)
    httppost.setEntity(entity)
    val httpResponse = httpClient.execute(httppost)
    val responseEntity = httpResponse.getEntity
    var content = ""
    if (responseEntity != null) {
      val inputStream = responseEntity.getContent()
      content = io.Source.fromInputStream(inputStream).getLines.mkString
      inputStream.close
    }
    println(content)
    if(!content.isEmpty)
      {
        writeToFile(content)
      }
    var states = scala.collection.mutable.Map[String, String]()
    val statusLine = httpResponse.getStatusLine
    var statusCode=""
    var statusMessage=""
    if(statusLine != null)
      {
        statusCode = statusLine.getStatusCode.toString
        statusMessage = statusLine.getReasonPhrase
      }
    states += ("statusCode" -> statusCode)
    states += ("statusMessage" -> statusMessage)
    states.toMap
  }

  def writeToFile(restContent:String): Unit =
  {
    //val contentReturn = getRestResponse("https://contactsapi.apispark.net/v1/companies/")
    val xml = JSON.parseFull(restContent)
    println(xml)
    xml match {
      case Some(m: Map[String, Any]) => m("id") match
      {
        case s: String => writeToTempFile(s)
        case _ =>  println("No id key exist")
      }
      case None => println("No")
    }
  }

  def writeToTempFile(x:String): Unit =
  {
    println(x)
    val pw = new PrintWriter(new File("tmp.txt" ))
    pw.write(x)
    pw.close
  }

}
