package AppiumSupport;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * This class will sets the needed capabilities and instantiates the Android driver
 */
public class AndroidDriverBuilder extends AppiumBaseClass<AndroidDriverBuilder, AndroidDriver> {

    private DesiredCapabilities capabilities = new DesiredCapabilities();

    public AndroidDriver build(String deviceName) throws MalformedURLException {

        //getting directory path where app files are placed
        File classpathRoot = new File(System.getProperty("user.dir"));

        //mandatory capabilities for Android platform

        //fetching sub directory for apk
        File app = new File(classpathRoot, "android-release.apk");

        //setting capabilities
        capabilities.setCapability("avd",deviceName);
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", deviceName);
        capabilities.setCapability("app", app.getAbsolutePath());
        capabilities.setCapability("newCommandTimeout", 60000);
        capabilities.setCapability("autoWebview", true);

        //creating AndroidDriver from capabilities passed

        AndroidDriver driver =  new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        return driver;
    }


}
