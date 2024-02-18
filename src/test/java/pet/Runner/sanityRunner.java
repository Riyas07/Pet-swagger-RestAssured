package pet.Runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "Feature",
glue = "pet/StepDef",tags = "@sanity")
public class sanityRunner extends AbstractTestNGCucumberTests {
}
