package com.automationexercices.tests.ui;

import com.automationexercices.apis.UserManagementAPI;
import com.automationexercices.drivers.GUIDriver;
import com.automationexercices.drivers.UITest;
import com.automationexercices.pages.components.SignupPage;
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
@Story("User Register Tests")
@Owner("Ahmed Adel")
@Severity(SeverityLevel.CRITICAL)
@UITest
public class RegisterTest extends BaseTest {
    String timestamp= TimeManager.getSimpleTimestamp();


//Tests to be implemented
    @Description("TC01 - Verify that user can register with valid data")
    @Test
    public void validSignUpTC(){
        new SignupLoginPage(driver).navigate()
                .enterSignupName(testData.getJsonData("name"))
                .enterSignupEmail(testData.getJsonData("email")+ timestamp +"@gmail.com")
                .clickSignupButton();
                new SignupPage(driver)
                .fillRegistrationForm(
                        testData.getJsonData("titleMale"),
                        testData.getJsonData("password"),
                        testData.getJsonData("day"),
                        testData.getJsonData("month"),
                        testData.getJsonData("year"),
                        testData.getJsonData("firstName"),
                        testData.getJsonData("lastName"),
                        testData.getJsonData("company"),
                        testData.getJsonData("address1"),
                        testData.getJsonData("address2"),
                        testData.getJsonData("country"),
                        testData.getJsonData("state"),
                        testData.getJsonData("city"),
                        testData.getJsonData("zipcode"),
                        testData.getJsonData("mobileNumber")
                )
                .clickCreateAccountButton()
                .verifyAccountCreated();

        new UserManagementAPI().deleteUserAccount(testData.getJsonData("email")+ timestamp +"@gmail.com",
                        testData.getJsonData("password"))
                .verifyUserAccountDeletedSuccessfully();
    }
    @Description("TC02 - Verify that user cannot register with existing email")
    @Test
    public void verifyErrorMessageWhenAccountCreatedBefore()
    {
        //precondition: create an account
        new UserManagementAPI().createRegisterUserAccount(
                testData.getJsonData("name"),
                testData.getJsonData("email")+ timestamp+"@gmail.com",
                        testData.getJsonData("password"),
                        testData.getJsonData("titleMale"),
                        testData.getJsonData("day"),
                        testData.getJsonData("month"),
                        testData.getJsonData("year"),
                        testData.getJsonData("firstName"),
                        testData.getJsonData("lastName"),
                        testData.getJsonData("company"),
                        testData.getJsonData("address1"),
                        testData.getJsonData("address2"),
                        testData.getJsonData("country"),
                        testData.getJsonData("state"),
                        testData.getJsonData("city"),
                        testData.getJsonData("zipcode"),
                        testData.getJsonData("mobileNumber")

                )
                .verifyUserAccountCreatedSuccessfully();
        new SignupLoginPage(driver).navigate()
                .enterSignupName(testData.getJsonData("name"))
                .enterSignupEmail( testData.getJsonData("email")+ timestamp+"@gmail.com")
                .clickSignupButton()
                .VerifyRegisterErrorMessageIsDisplayed(testData.getJsonData("messages.error"));

        new UserManagementAPI().deleteUserAccount(testData.getJsonData("email")+ timestamp +"@gmail.com",
                        testData.getJsonData("password"))
                .verifyUserAccountDeletedSuccessfully();

    }



    //Configuration methods
    @BeforeClass
    protected void preCondition() {
        testData = new JsonReader("register-data");
    }
    @BeforeMethod
    public void setUp(){
    driver =new GUIDriver();
    new NavigationBarComponent(driver).navigate();
    driver.browser().closeExtensionTab();


    }
    @AfterMethod
    public void tearDown(){
        driver.quitDriver();
    }
}
