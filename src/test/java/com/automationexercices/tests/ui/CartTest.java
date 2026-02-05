package com.automationexercices.tests.ui;

import com.automationexercices.drivers.GUIDriver;
import com.automationexercices.drivers.UITest;
import com.automationexercices.pages.ProductsPage;
import com.automationexercices.pages.components.NavigationBarComponent;
import com.automationexercices.tests.BaseTest;
import com.automationexercices.utils.dataReader.JsonReader;
import io.qameta.allure.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
@Epic("Automation Exercise")
@Feature("UI Carts Management")
@Story("Cart Management")
@Severity(SeverityLevel.CRITICAL)
@Owner("Ahmed")
@UITest
public class CartTest extends BaseTest {
    @Description("TC01 - Verify product details in the cart after adding a product")
    @Test

    public void verifyProductDetailsOnCartTC() {
         new ProductsPage(driver)
                 .clickOnAddToCart(testData.getJsonData("product.name"))
                 .validateItemAddedLabel(testData.getJsonData("messages.cartAdded"))
                 .clickOnViewCart()
                 .verifyProductDetailsInCart(
                            testData.getJsonData("product.name"),
                            testData.getJsonData("product.price"),
                            testData.getJsonData("product.quantity"),
                            testData.getJsonData("product.total")
                 );

        // Test implementation goes here
    }
    //Configuration methods
    @BeforeClass
    protected void preCondition() {
        testData = new JsonReader("cart-data");
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
