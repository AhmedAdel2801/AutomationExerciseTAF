package com.automationexercices.tests.api;

import com.automationexercices.apis.UserManagementAPI;
import com.automationexercices.drivers.GUIDriver;
import com.automationexercices.pages.components.NavigationBarComponent;
import com.automationexercices.tests.BaseTest;
import com.automationexercices.utils.TimeManager;
import com.automationexercices.utils.dataReader.JsonReader;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class RegisterAPITest extends BaseTest {
    String timestamp = TimeManager.getSimpleTimestamp();

    @Test
    public void regesterNewAccount() {
        //precondition: create an account
        new UserManagementAPI().createRegisterUserAccount(
                        testData.getJsonData("name"),
                        testData.getJsonData("email") + timestamp + "@gmail.com",
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
    }
    //Configuration methods
    @BeforeClass
    protected void setUp() {

        testData = new JsonReader("register-data");
    }
}
