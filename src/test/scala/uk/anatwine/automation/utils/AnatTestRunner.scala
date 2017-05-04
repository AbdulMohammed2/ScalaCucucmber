package uk.anatwine.automation.utils

import cucumber.api.CucumberOptions
import cucumber.api.junit.Cucumber
import org.junit.runner.RunWith

/**
  * Created by sumohamm on 15/04/2017.
  */
@RunWith(classOf[Cucumber])
@CucumberOptions(
  features = Array("src/test/resources/features/"),
  glue = Array("uk.anatwine.automation.stepdefs"),
  tags = Array("@sc1"),
  format = Array("pretty", "html:target/cucumber-report"
  )
)
class AnatTestRunner {

}
