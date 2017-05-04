package uk.anatwine.automation.apitest

import java.io.InputStream

import com.typesafe.config.ConfigFactory
import uk.anatwine.automation.base.RestFulAPIConnect

import scala.io.Source


/**
  * Created by SUMOHAMM on 03/05/2017.
  */
object CompaniesAddressApi
{
  val environmentProperty = System.getProperty("environment", "local").toLowerCase

  val apiConfPropertiesFileName = "/restful-api-conf.properties"

  def main(args: Array[String]): Unit = {
    getApiUrl("company")
    getApiUrlFromConfig("company")
  }

  def apiCall(url:String):Map[String,String]={

    RestFulAPIConnect.getRestResponse(url)
  }


  def getApiUrl(requestedApi:String) : String = {
    // this will use with version scala 2.12.x  -- Source.fromResource("/restful-api-conf.properties").getLines
    val stream : InputStream = getClass.getResourceAsStream(apiConfPropertiesFileName)
    val props = Source.fromInputStream( stream ).getLines
    val apiUrl = props.find(_.startsWith(environmentProperty+"."+requestedApi+".api")).getOrElse("")
    println(apiUrl)
    apiUrl
  }

  def getApiUrlFromConfig(requestedApi:String) : String = {
    val configInfo = ConfigFactory.load().getConfig(environmentProperty)
    val apiUrl = configInfo.getString(requestedApi+".api")
    apiUrl
  }

}
