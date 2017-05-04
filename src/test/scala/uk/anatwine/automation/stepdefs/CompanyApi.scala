package uk.anatwine.automation.stepdefs

import cucumber.api.DataTable
import cucumber.api.scala.{EN, ScalaDsl}
import org.scalatest.Matchers

/**
  * Created by SUMOHAMM on 02/05/2017.
  */
class CompanyApi extends ScalaDsl with EN with Matchers
{

  Given("""^Connect to the "([^"]*)" api$"""){ (arg0:String) =>

  }


  Then("""^post a new address and retrieve the id in tmp file$"""){ (arg0:DataTable) =>

  }
}
