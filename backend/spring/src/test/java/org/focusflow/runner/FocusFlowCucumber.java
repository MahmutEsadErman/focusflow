package org.focusflow.runner;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features", // Path to the feature files
        glue = "org.focusflow.steps",    // Package containing step definitions
        plugin = {"pretty", "summary", "html:target/cucumber-report.html"}, // Verbose output
        monochrome = true
)
public class FocusFlowCucumber {
}
