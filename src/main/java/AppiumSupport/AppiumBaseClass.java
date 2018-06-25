package AppiumSupport;

import io.appium.java_client.AppiumDriver;

/**
 *
 * @param <SELF>
 * @param <DRIVER>
 */
public abstract class AppiumBaseClass<SELF,DRIVER extends AppiumDriver> {

    public static AndroidDriverBuilder forAndroid() {
        return new AndroidDriverBuilder();
    }

    public static IOSDriverBuilder forIOS() {
        return new IOSDriverBuilder();
    }

}
