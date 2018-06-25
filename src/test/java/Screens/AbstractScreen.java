package Screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MultiTouchAction;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import static java.time.Duration.ofMillis;


/**
 * This class contains all the shared methods between Screen objects
 */
public class AbstractScreen {

    //locators
    @FindBy(className = "ion-ios-home-outline")
    private WebElement homeIcon;


    private WebDriverWait wait;
    AppiumDriver driver;


    //string to store error causing the exception
    public static String recentError = "";


    public AbstractScreen(AppiumDriver driver) {
        this.driver = driver;

        //to initialize WebElements referred by @FindBy annotation
        PageFactory.initElements(driver, this);

        //explicit wait
        wait = new WebDriverWait(driver, 10);

        //switching context to Webview
        switchContext("hybrid");
    }


    /**
     * Use this method to switch context from/to hybrid and native
     *
     * @param context, values can be either Hybrid or Native
     * @return true, if it is able to switch context
     */
    private boolean switchContext(String context) {
        try {
            boolean changeFlag = false;
            if (StringUtils.containsIgnoreCase(context, "Native")) {
                context = "Native";
            } else if (StringUtils.containsIgnoreCase(context, "Hybrid")) {
                context = "WEBVIEW_com";
            }
            if (StringUtils.containsIgnoreCase(driver.getContext(), context)) return false;
            Set<String> contextName = driver.getContextHandles();
            for (String contexts : contextName) {
                if (StringUtils.containsIgnoreCase(contexts, context)) {
                    driver.context(contexts);
                    changeFlag = true;
                    break;
                }
            }
            return changeFlag;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * Use this method to find if element is visible on screen
     *
     * @param element, WebElement to be inspected
     * @return true if element is present, otherwise false
     */
    boolean isElementPresent(WebElement element) {
        try {
            return wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * Use this method to check whether element is in DOM
     *
     * @param elementLocator, used to find element
     * @return true if element is inside DOM
     */
    public boolean isElementPresent(By elementLocator) {
        try {
            return wait.until(ExpectedConditions.presenceOfElementLocated(elementLocator)).isDisplayed();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Use this method to get WebElement
     *
     * @param elementLocator used to find the element
     * @return the WebElement once it is located
     */
    WebElement getElement(By elementLocator) {
        try {
            return wait.until(ExpectedConditions.presenceOfElementLocated(elementLocator));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Use this method to simulate typing into an element, which may set its value
     *
     * @param element, WebElement whose value is to be set
     * @param text,    String to send to an element
     */
    void enterText(WebElement element, String text) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element)).sendKeys(text);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Use this method to click on an element
     *
     * @param element, WebElement to perform click operation
     */
    void clickButton(WebElement element) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Use this method to perform click operation on element
     *
     * @param locator, used to find an element
     */
    void clickButton(By locator) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Use this method to get visible innerText of the element
     *
     * @param element, WebElement to process
     * @return innerText of the WebElement
     */
    String getText(WebElement element) {
        try {
            return wait.until(ExpectedConditions.visibilityOf(element)).getText();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }


    /**
     * Use this method to get visible innerText of the element
     *
     * @param elementLocator, used to find an Element
     * @return innerText of the WebElement
     */
    String getText(By elementLocator) {
        try {
            return wait.until(ExpectedConditions.presenceOfElementLocated(elementLocator)).getText();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }


    /**
     * Use this method to get text of all elements of a list
     *
     * @param listOfSearch, used as parent element. For example table with locator By.xpath("//table")
     * @param childLocator, used to find child element. For example td By.xpath("./tr/td")
     * @return text of visible subElements
     */
    List<String> getAllStringItemsOfList(WebElement listOfSearch, By childLocator) {

        try {
            List<WebElement> elementList;
            List<String> nameList = new ArrayList<>();
            elementList = wait.until(ExpectedConditions.visibilityOfNestedElementsLocatedBy(listOfSearch, childLocator));

            //process every element in a list, add its innerText to nameList
            for (WebElement elementName : elementList) {
                nameList.add(elementName.getText());
            }
            return nameList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * Use this method to get all the WebElements in a list
     *
     * @param listOfSearch, used as parent element. For example table with locator By.xpath("//table")
     * @param childLocator, used to find child element. For example td By.xpath("./tr/td")
     * @return subElements of a list
     */
    List<WebElement> getAllItemsOfList(WebElement listOfSearch, By childLocator) {
        try {
            return wait.until(ExpectedConditions.visibilityOfNestedElementsLocatedBy(listOfSearch, childLocator));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Select all options that have a value matching the argument.
     *
     * @param dropdownElement, dropdown to be selected
     * @param value            The value to match against
     */
    void selectValueFromDropdown(WebElement dropdownElement, String value) {
        try {
            wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(dropdownElement, By.xpath("//option[contains(text(), '" + value + "')]"))).click();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Use this method for static wait on a particular element
     *
     * @param timeInMilliSeconds
     */
    void waitForDuration(int timeInMilliSeconds) {
        try {
            Thread.sleep(timeInMilliSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * An expectation for checking that an element, known to be present on the DOM of a page, is
     * visible. Visibility means that the element is not only displayed but also has a height and
     * width that is greater than 0.
     *
     * @param element the WebElement
     */
    void waitUntilVisibilityOfElement(WebElement element) {

        int count = 10;
        while (count > 0) {
            try {
                count--;
                wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed();
            } catch (Exception e) {
                return;
            }
        }
    }

    void waitUntilVisibilityOfElement(WebElement element, String s) {
        int count = 10;
        while (count > 0) {
            try {
                count--;
                if(wait.until(ExpectedConditions.visibilityOf(element)).getAttribute(s).equalsIgnoreCase("true")){
                 continue;
                }else{
                    return;
                }
            } catch (Exception e) {
                return;
            }
        }
    }


    /**
     * If this element is a text entry element, this will clear the value. Has no effect on other
     * elements. Text entry elements are INPUT and TEXTAREA elements.
     *
     * @param element the WebElement
     */
    void clearTextBox(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element)).clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Use this method to perform swipe left on a element
     *
     * @param element, element on which to perform swipe action
     * @return true if swipe is successful
     */
    boolean swipeLeft(WebElement element) {
        Dimension size = element.getSize();
        Point point = element.getLocation();
        int anchor = point.getY() + 310;
        int startPoint = (int) (point.getX() + size.getWidth() * 0.9);
        int endPoint = point.getX();
        try {
            //Perform swipe on AndroidDriver using Appium's TouchAction
            //TouchAction will be performed in Native context
            if (driver instanceof AndroidDriver) {
                switchContext("Native");
                new TouchAction(driver).press(PointOption.point(endPoint, anchor)).waitAction(WaitOptions.waitOptions(ofMillis(200))).moveTo(PointOption.point(startPoint, anchor)).release().perform();
            } else {
                // Perform left swipe on IOSDriver using JavaScript as Appium still does not support TouchAction on iOS
                JavascriptExecutor js = driver;
                HashMap<String, String> swipeObject = new HashMap<>();
                swipeObject.put("duration", "3.0");
                swipeObject.put("direction", "left");
                js.executeScript("mobile: swipe", swipeObject);
            }
            //Switching context back to webview
            switchContext("Hybrid");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * Use this method to navigate to Home Screen by clicking on home icon
     */
    public void navigateToHomeScreen() {
        clickButton(homeIcon);
    }
}
