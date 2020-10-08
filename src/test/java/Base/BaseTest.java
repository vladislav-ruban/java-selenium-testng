package Base;

import DriverFactory.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class BaseTest extends BaseUtil {

    BaseUtil base;

    public BaseTest(BaseUtil base) {
        this.base = base;
    }

    @Before
    public void setUp() {
        DriverFactory driverFactory = new DriverFactory();
        base.setDriver(driverFactory.getBrowser());
        base.getDriver().manage().window().maximize();
        base.getDriver().manage().deleteAllCookies();
    }

    @After
    public void tearDown(){
        base.getDriver().close();
    }
}
