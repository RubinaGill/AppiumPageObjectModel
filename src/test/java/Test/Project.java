package Test;

import Screens.*;
import io.appium.java_client.AppiumDriver;

class HRDirectLite {
    private final AppiumDriver driver;

    HRDirectLite(AppiumDriver driver) {
        this.driver = driver;
    }

    LoginScreen loginScreen() {
        return new LoginScreen(driver);
    }

    HomeScreen homeScreen() {
        return new HomeScreen(driver);
    }

    ApprovalsScreen approvalsScreen() {
        return new ApprovalsScreen(driver);
    }

    EmployeeSearchScreen employeeSearchScreen() {
        return new EmployeeSearchScreen(driver);
    }

    EscServicesScreen escServiceScreen() {
        return new EscServicesScreen(driver);
    }

    RequestsScreen requestsScreen() {
        return new RequestsScreen(driver);
    }

    ToolkitScreen toolkitScreen() {
        return new ToolkitScreen(driver);
    }

    NotificationsScreen notificationsScreen() {
        return new NotificationsScreen(driver);
    }

    AnalyserScreen analyserScreen() {
        return new AnalyserScreen(driver);
    }

}
