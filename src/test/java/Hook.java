import Base.BaseUtil;
import DriverFactory.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hook extends BaseUtil {

    BaseUtil base;

    public Hook(BaseUtil base) {
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
