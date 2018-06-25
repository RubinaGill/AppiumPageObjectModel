package Test;


import org.testng.Assert;
import org.testng.annotations.Test;
import utitlity.ConfigReader;


/**
 * This class contains tests on Login Screen
 */
public class LoginScreenTest extends AbstractTest {


    @Test(groups = {"Regression"})
    public void loginWithValidCredentials(){
        app.loginScreen().enterStaffId(ConfigReader.getProperty("staffNumber1"));
        app.loginScreen().enterEPIN(ConfigReader.getProperty("EPIN"));
        app.loginScreen().clickLoginButton();
        app.loginScreen().acceptAlert();
        Assert.assertTrue(app.homeScreen().isEmployeeImagePresent(),"unable to navigate to Home Page after login");
        app.homeScreen().logout();//required for other classes, as simulator is launched in before suite
    }

    @Test(groups = {"Regression"})
    public void loginWithInValidCredentials(){
        app.loginScreen().enterStaffId(ConfigReader.getProperty("staffNumber1"));
        app.loginScreen().enterEPIN("1234");
        app.loginScreen().clickLoginButton();
        Assert.assertTrue(app.loginScreen().isErrorMessagePresent(),"unable to get login error message");

    }
}
