package stepdefs;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/java/feature",
        glue = {"stepdefs", "hooks"},
        tags = "@all"
)

public class TestRunner extends AbstractTestNGCucumberTests {
}
