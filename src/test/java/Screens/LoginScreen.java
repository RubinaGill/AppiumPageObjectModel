package Screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;


/**
 * This class contains methods representing user interactions on Login Screen
 */
public class LoginScreen extends AbstractScreen {
    //locators

    @FindBy(id = "j_password")
    private WebElement passwordTextBox;

    @FindBy(xpath = "//*[contains(text(), 'Login')]")
    private WebElement loginButton;

    @FindBy(xpath = "//*[contains(text(), 'The EPIN which you entered is incorrect')]")
    private WebElement invalidEPINError;

    @FindBy(id = "j_username")
    private WebElement loginTextBox;


    public LoginScreen(AppiumDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }


    /**
     * Use this method to clear the text box and enter staff id in it
     *
     * @param id, id to be entered
     */
    public void enterStaffId(String id) {
        try {
            //clear textbox first
            clearTextBox(loginTextBox);
            enterText(loginTextBox, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Use this method to clear the text box and then enter e-pin in it
     *
     * @param epin, to enter
     */
    public void enterEPIN(String epin) {
        try {
            clearTextBox(passwordTextBox);
            enterText(passwordTextBox, epin);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Use this method to click on login button
     */
    public void clickLoginButton() {
        clickButton(loginButton);
    }


    /**
     * Use this method to enter credentials of user and click on login button
     *
     * @param id,   staffId to enter
     * @param epin, e-pin to enter
     */
    public void login(String id, String epin) {
        enterStaffId(id);
        enterEPIN(epin);
        clickLoginButton();
        acceptAlert();
        if(driver instanceof AndroidDriver) {
            if (isElementPresent(By.xpath("//*[contains(text(), 'Do you wish to receive push notifications on this device ?')]"))) {
                clickButton(By.xpath("//*[contains(text(),'Yes')]"));
                System.out.println("Excepting push notifications");
            }
        }
    }


    public void acceptAlert(){
        if(driver instanceof AndroidDriver){
            if(isElementPresent(By.xpath("//button[contains(text(),'Yes')]"))){
                clickButton(By.xpath("//button[contains(text(),'Yes')]"));
            }
        }
    }

    /**
     * Use this method to verify if invalid e-pin message is displayed
     *
     * @return true, if method is visible
     */
    public boolean isErrorMessagePresent() {
        try {
            return isElementPresent(invalidEPINError);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
