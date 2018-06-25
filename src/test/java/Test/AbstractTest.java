package Test;

import AppiumSupport.AppiumBaseClass;
import AppiumSupport.AppiumLauncher;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.MobilePlatform;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

/**
 * This class will take care of setUp phase
 */
public class AbstractTest{
    private AppiumDriver driver;
    protected static HRDirectLite app;

    @BeforeSuite(alwaysRun = true)
    public void launchAppium() {
        AppiumLauncher.runProcess(false, "appium -a 127.0.0.1");
    }

    @BeforeTest(alwaysRun = true)
    public void setUp(ITestContext context) throws Exception {

        //get device name from testng.xml's test name
        String deviceName = context.getCurrentXmlTest().getName();

        //instantiate driver according to platform
        switch (getPlatform(deviceName)) {
            case MobilePlatform.ANDROID:
                this.driver = AppiumBaseClass.forAndroid().build(deviceName);
                break;
            case MobilePlatform.IOS:
                this.driver = AppiumBaseClass.forIOS().build(deviceName);
                break;
            default:
                break;
        }
        app = new HRDirectLite(driver);
    }


    /**
     * @param deviceName, name of device to launch
     * @return platform, returns IOS if device name starts with i
     */
    private String getPlatform(String deviceName) {
        String platform;
        if (deviceName.startsWith("i")) {
            platform = MobilePlatform.IOS;
        } else {
            platform = MobilePlatform.ANDROID;
        }
        return platform;
    }

    @AfterTest(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}
