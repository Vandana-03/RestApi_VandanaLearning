package CucumberOptions;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

//specify all the feature file location, step definition location is not required
@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/java/Features",  glue = {"Stepdefinitions"})
public class TestRunner {

}
