package uk.anatwine.automation.stepdefs

import java.util.concurrent.TimeUnit

import cucumber.api.scala.ScalaDsl
import cucumber.api.scala.EN
import org.openqa.selenium.By
import org.openqa.selenium.firefox.FirefoxDriver
import org.scalatest.Matchers

/**
  * Created by sumohamm on 15/04/2017.
  */
class MyGoogleSearch extends ScalaDsl with EN with Matchers{

  //val currentDir: String = System.getProperty("user.dir")
  //val marionetteDriverLocation: String = currentDir + "/tools/marionette/wires.exe"
  //System.setProperty("webdriver.gecko.driver", marionetteDriverLocation)
  val currentDir: String = "D:\\geckodriver.exe"
  System.setProperty("webdriver.gecko.driver", currentDir)

  val driver = new FirefoxDriver()
  driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS)

  Given("""^I have navigated to google$""")
  { () =>
    driver.navigate().to("http://www.google.com")
  }


  When("""^I search for "([^"]*)"$"""){ (arg0:String) =>

    driver.findElement(By.id("lst-ib")).sendKeys(arg0)
    driver.findElement(By.className("lsb")).click()

  }

  Then("""^the page title should be "([^"]*)"$"""){ (arg0:String) =>
      driver.getTitle.shouldEqual(arg0)
  }

}
