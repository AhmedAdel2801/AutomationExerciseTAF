package com.automationexercices.tests.ui;

import com.automationexercices.apis.UserManagementAPI;
import com.automationexercices.drivers.GUIDriver;
import com.automationexercices.drivers.UITest;
import com.automationexercices.pages.ProductsPage;
import com.automationexercices.pages.components.*;
import com.automationexercices.tests.BaseTest;
import com.automationexercices.utils.TimeManager;
import com.automationexercices.utils.dataReader.JsonReader;
import io.qameta.allure.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
@Epic("Automation Exercise")
@Feature("UI Payment Management")
@Story("Payment Management")
@Severity(SeverityLevel.CRITICAL)
@Owner("Ahmed")
@UITest
public class PaymentTest extends BaseTest {
        //Variables
        String timestamp = TimeManager.getSimpleTimestamp();
        @Description("TC01 - Verify that user can complete payment process successfully")
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
        @Description("TC02 - Verify that user can login with valid credentials")
        @Test(dependsOnMethods = "regesterNewAccount")
        public void loginExistingAccount() {
            new SignupLoginPage(driver).navigate()
                    .enterLoginEmail(testData.getJsonData("email") + timestamp + "@gmail.com")
                    .enterLoginPassword(testData.getJsonData("password"))
                    .clickLoginButton()
                    .navigationBar
                    .verifyUserIsLoggedin(testData.getJsonData("name"));
        }
        @Description("TC03 - Verify that user can add products to cart successfully")
        @Test(dependsOnMethods = {"loginExistingAccount", "regesterNewAccount"})
        public void addToCartProducts() {
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


        }
        @Description("TC04 - Verify that user can proceed to checkout and verify address details")
        @Test(dependsOnMethods = {"loginExistingAccount", "regesterNewAccount", "addToCartProducts"})
        public void proceedToCheckout() {
            new CartPage(driver)
                    .clickOnProceedToCheckoutButton()
                    .verifyDeliveryAddress(
                            testData.getJsonData("titleMale"),
                            testData.getJsonData("firstName"),
                            testData.getJsonData("lastName"),
                            testData.getJsonData("company"),
                            testData.getJsonData("address1"),
                            testData.getJsonData("address2"),
                            testData.getJsonData("city"),
                            testData.getJsonData("state"),
                            testData.getJsonData("zipcode"),
                            testData.getJsonData("country"),
                            testData.getJsonData("mobileNumber")
                    )
                    .verifyAddBillingress(
                            testData.getJsonData("titleMale"),
                            testData.getJsonData("firstName"),
                            testData.getJsonData("lastName"),
                            testData.getJsonData("company"),
                            testData.getJsonData("address1"),
                            testData.getJsonData("address2"),
                            testData.getJsonData("city"),
                            testData.getJsonData("state"),
                            testData.getJsonData("zipcode"),
                            testData.getJsonData("country"),
                            testData.getJsonData("mobileNumber")
                    );
        }
        @Description("TC05 - Verify that user can complete payment process successfully")
        @Test (dependsOnMethods = {"loginExistingAccount", "regesterNewAccount", "addToCartProducts", "proceedToCheckout"})
        public void paymentAndOrderConfirmation() {
            new CheckoutPage(driver).
                    clickOnPlaceOrderButton()
                    .fillCardInfo(
                            testData.getJsonData("cardData.nameOnCard"),
                            testData.getJsonData("cardData.cardNumber"),
                            testData.getJsonData("cardData.cvc"),
                            testData.getJsonData("cardData.expiryMonth"),
                            testData.getJsonData("cardData.expiryYear")
                    )
                    .verifyOrderPlacedSuccessfully(testData.getJsonData("messages.orderPlaced"))
                    .clickDownloadInvoiceButton();
        }
        @Description("TC06 - Verify that user can delete account successfully")
        @Test(dependsOnMethods = {"loginExistingAccount", "regesterNewAccount", "addToCartProducts", "proceedToCheckout", "paymentAndOrderConfirmation"})
        public void deleteUserAccount() {
            new UserManagementAPI().deleteUserAccount(testData.getJsonData("email") + timestamp + "@gmail.com",
                            testData.getJsonData("password"))
                    .verifyUserAccountDeletedSuccessfully();
        }

        //Configuration methods
        @BeforeClass
        protected void setUp() {

            testData = new JsonReader("checkout-data");
            driver = new GUIDriver();
            new NavigationBarComponent(driver).navigate();
            driver.browser().closeExtensionTab();

        }

        @AfterClass
        public void tearDown() {
            driver.quitDriver();
        }
    }

