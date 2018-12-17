package testTake;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;


@CucumberOptions( monochrome = true,
        features = "src/test/resources/testTake",
        format = {"pretty", "html:target/cucumber", "json:target/cucumber/Cucumber.json"},
        dryRun = false,
        glue = "testTake")

public class RunCucumberTest extends AbstractTestNGCucumberTests {

}
