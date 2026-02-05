// java
package com.automationexercices.tests.ui;

import com.automationexercices.apis.UserManagementAPI;
import com.automationexercices.drivers.GUIDriver;
import com.automationexercices.drivers.UITest;
import com.automationexercices.pages.components.NavigationBarComponent;
import com.automationexercices.pages.components.SignupLoginPage;
import com.automationexercices.tests.BaseTest;
import com.automationexercices.utils.TimeManager;
import com.automationexercices.utils.dataReader.JsonReader;
import io.qameta.allure.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Epic("Automation Exercises")
@Feature("UI User Management Tests")
@Story("User Login Tests")
@Owner("Ahmed Adel")
@Severity(SeverityLevel.CRITICAL)
@UITest
public class LoginTest extends BaseTest {
    private final String timestamp = TimeManager.getSimpleTimestamp();

    @Description("TC01 - Verify that user can login with valid credentials")
    @Test
    public void validLoginTC(){
        // Build the email value from the JSON prefix, then reuse it
        String email = testData.getJsonData("email") + timestamp + "@gmail.com";

        new UserManagementAPI().createRegisterUserAccount(
                        testData.getJsonData("name"),
                        email,
                        testData.getJsonData("password"),
                        testData.getJsonData("firstName"),
                        testData.getJsonData("lastName"))
                .verifyUserAccountCreatedSuccessfully();

        new SignupLoginPage(driver).navigate()
                .enterLoginEmail(testData.getJsonData("email")+ timestamp+"@gmail.com")
                .enterLoginPassword(testData.getJsonData("password"))
                .clickLoginButton()
                .navigationBar
                .verifyUserIsLoggedin(testData.getJsonData("name"));

        new UserManagementAPI().deleteUserAccount(email,
                        testData.getJsonData("password"))
                .verifyUserAccountDeletedSuccessfully();
    }

    @Description("TC02 - Verify that user cannot login with invalid email")
    @Test
    public void invalidLoginUsingInvalidEmailTC(){
        // Build the email value from the JSON prefix, then reuse it
        String email = testData.getJsonData("email") + timestamp + "@gmail.com";

        new UserManagementAPI().createRegisterUserAccount(
                        testData.getJsonData("name"),
                        email,
                        testData.getJsonData("password"),
                        testData.getJsonData("firstName"),
                        testData.getJsonData("lastName"))
                .verifyUserAccountCreatedSuccessfully();

        new SignupLoginPage(driver).navigate()
                .enterLoginEmail(email+"1")
                .enterLoginPassword(testData.getJsonData("password"))
                .clickLoginButton()
                .VerifyLoginErrorMessageIsDisplayed(testData.getJsonData("messages.error"));

        new UserManagementAPI().deleteUserAccount(email,
                        testData.getJsonData("password"))
                .verifyUserAccountDeletedSuccessfully();
    }

    @Description("TC03 - Verify that user cannot login with invalid password")
    @Test
    public void invalidLoginUsingInvalidPasswordTC(){
        // Build the email value from the JSON prefix, then reuse it
        String email = testData.getJsonData("email") + timestamp + "@gmail.com";

        new UserManagementAPI().createRegisterUserAccount(
                        testData.getJsonData("name"),
                        email,
                        testData.getJsonData("password"),
                        testData.getJsonData("firstName"),
                        testData.getJsonData("lastName"))
                .verifyUserAccountCreatedSuccessfully();

        new SignupLoginPage(driver).navigate()
                .enterLoginEmail(email)
                .enterLoginPassword(testData.getJsonData("password")+timestamp)
                .clickLoginButton()
                .VerifyLoginErrorMessageIsDisplayed(testData.getJsonData("messages.error"));

        new UserManagementAPI().deleteUserAccount(email,
                        testData.getJsonData("password"))
                .verifyUserAccountDeletedSuccessfully();
    }
    //Configuration methods
    @BeforeClass
    protected void preCondition() {
        testData = new JsonReader("login-data");
    }
    @BeforeMethod
    public void setUp(){
        driver = new GUIDriver();
        new NavigationBarComponent(driver).navigate();
        driver.browser().closeExtensionTab();
    }
    @AfterMethod
    public void tearDown(){
        driver.quitDriver();
    }
}
