package globalRecruitment.testRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;

@CucumberOptions(features = "src/test/java/globalRecruitment/Features", glue = { "globalRecruitment.StepDefinitions" }, plugin = { "json:target/site/cucumber.json" },
		// tags = {"@Login or @ConfigCategory_Add or @ConfigCategory_Edit or @ConfigCategory_Delete or @ConfigCategory_Search" })
		tags = { "@ConfigCategory_Add" })

@RunWith(CucumberWithSerenity.class)
public class globalRecruitmentTestRunner {
}