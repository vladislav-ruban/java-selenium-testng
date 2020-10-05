import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = {"src/test/resources/features"}, plugin = {"pretty"})
public class RunBddTests extends AbstractTestNGCucumberTests {
}
