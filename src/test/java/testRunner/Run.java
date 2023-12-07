package testRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(

		features = ".//Features/", 
		glue = "stepDefinition", 
		dryRun = false, 
		monochrome = true, 
		tags="@Sanity",
		plugin = {"pretty", "html:target/cucumber-reports/reports_html.html" })

//plugin = { "pretty","json:target/cucumber-reports/reports_json.json" })
public class Run {

}
