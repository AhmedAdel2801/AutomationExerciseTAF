package com.automationexercices.tests.ui;

import com.automationexercices.drivers.GUIDriver;
import com.automationexercices.drivers.UITest;
import com.automationexercices.pages.ProductsPage;
import com.automationexercices.pages.components.NavigationBarComponent;
import com.automationexercices.tests.BaseTest;
import com.automationexercices.utils.TimeManager;
import com.automationexercices.utils.dataReader.JsonReader;
import io.qameta.allure.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Epic("Automation Exercise")
@Feature("UI Product Details Management")
@Story("Product Details Management")
@Severity(SeverityLevel.CRITICAL)
@Owner("Ahmed")
@UITest

public class ProductDetailsTest extends BaseTest {
    //Variables
    @Description("TC01 - Verify product details on product details page")
    @Test
    public void verifyProductDetailsTC() {
         new ProductsPage(driver).navigate()
                 .clickOnViewProduct(testData.getJsonData("product.name"))
                 .verifyProductDetails(testData.getJsonData("product.name"),testData.getJsonData("product.price"));
        // Test implementation goes here
    }
    @Description("TC02 - Verify review message after adding a review")
    @Test
    public void verifyReviewMessageTC() {
            new ProductsPage(driver).navigate()
                    .clickOnViewProduct(testData.getJsonData("product.name"))
                    .addReview(
                            testData.getJsonData("review.name"),
                            testData.getJsonData("review.email"),
                            testData.getJsonData("review.reviewText"))
        .verifyReviewMessage(testData.getJsonData("messages.cartAdded"));
        // Test implementation goes here
    }
    //Configurations
    @BeforeClass
    protected void preCondition() {
        testData = new JsonReader("product-details-data");
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
