package com.automationexercices.pages.components;

import com.automationexercices.drivers.GUIDriver;
import com.automationexercices.utils.dataReader.PropertyReader;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class CheckoutPage {
    private final GUIDriver driver;

    public CheckoutPage(GUIDriver driver) {
        this.driver = driver;
    }
    //Todo: Add the lower part to the page
    //Todo:Switch locators to Dynamic locators where applicable


    //Variables
    private String checkoutPageEndpoint = "/checkout";
    //Locators
    private final By placeOrderButton = By.xpath("//a[.='Place Order']");
    //Delivery Address Locators
    private final By deliveryName = By.xpath("//ul[@id='address_delivery'] /li[@class='address_firstname address_lastname']");
    private final By deliveryCompany = By.xpath("//ul[@id='address_delivery'] /li[@class='address_address1 address_address2'][1]");
    private final By deliveryAddress1 = By.xpath("//ul[@id='address_delivery'] /li[@class='address_address1 address_address2'][2]");
    private final By deliveryAddress2 = By.xpath("//ul[@id='address_delivery'] /li[@class='address_address1 address_address2'][3]");
    private final By deliveryCityStatePostalCode = By.xpath("//ul[@id='address_delivery'] /li[@class='address_city address_state_name address_postcode']");
    private final By deliveryCountry = By.xpath("//ul[@id='address_delivery'] /li[@class='address_country_name']");
    private final By deliveryPhone = By.xpath("//ul[@id='address_delivery'] /li[@class='address_phone']");
    //Billing Address Locators
    private final By billingName = By.xpath("//ul[@id='address_invoice'] /li[@class='address_firstname address_lastname']");
    private final By billingCompany = By.xpath("//ul[@id='address_invoice'] /li[@class='address_address1 address_address2'][1]");
    private final By billingAddress1 = By.xpath("//ul[@id='address_invoice'] /li[@class='address_address1 address_address2'][2]");
    private final By billingAddress2 = By.xpath("//ul[@id='address_invoice'] /li[@class='address_address1 address_address2'][3]");
    private final By billingCityStatePostalCode = By.xpath("//ul[@id='address_invoice'] /li[@class='address_city address_state_name address_postcode']");
    private final By billingCountry = By.xpath("//ul[@id='address_invoice'] /li[@class='address_country_name']");
    private final By billingPhone = By.xpath("//ul[@id='address_invoice'] /li[@class='address_phone']");


    //Actions
    @Step("Navigate to Checkout Page")
    public CheckoutPage navigate() {
        driver.browser().navigateTo(PropertyReader.getProperty("baseUrlWeb") + checkoutPageEndpoint);
        return this;
    }

    @Step("Click on Place Order Button")
    public PaymentPage clickOnPlaceOrderButton() {
        driver.element().click(placeOrderButton);
        return new PaymentPage(driver);
    }

    //Validations
    @Step("Verify Delivery Address: Name - {expectedName}, Company - {expectedCompany}, Address1 -" +
            " {expectedAddress1}, Address2 - {expectedAddress2}, CityStatePostalCode - {expectedCityStatePostalCode}, " +
            "Country - {expectedCountry}, Phone - {expectedPhone}")
    public CheckoutPage verifyDeliveryAddress(String title, String expectedFirstName, String expectedSecondName, String expectedCompany, String expectedAddress1,
                                              String expectedAddress2, String expectedCity, String expectedState, String expectedPostalCode, String expectedCountry,
                                              String expectedPhone) {
        driver.validation().Equals(driver.element().getText(deliveryName), (title + ". " + expectedFirstName + " " + expectedSecondName), "Delivery Name not matching")
                .Equals(driver.element().getText(deliveryCompany), expectedCompany, "Delivery Company not matching")
                .Equals(driver.element().getText(deliveryAddress1), expectedAddress1, "Delivery Address1 not matching")
                .Equals(driver.element().getText(deliveryAddress2), expectedAddress2, "Delivery Address2 not matching")
                .Equals(driver.element().getText(deliveryCityStatePostalCode), (expectedCity + " " + expectedState + " " + expectedPostalCode), "Delivery City/State/PostalCode not matching")
                .Equals(driver.element().getText(deliveryCountry), expectedCountry, "Delivery Country not matching")
                .Equals(driver.element().getText(deliveryPhone), expectedPhone, "Delivery Phone not matching");
        return this;

    }
    @Step("Verify Delivery Address: Name - {expectedName}, Company - {expectedCompany}, Address1 -" +
            " {expectedAddress1}, Address2 - {expectedAddress2}, CityStatePostalCode - {expectedCityStatePostalCode}, " +
            "Country - {expectedCountry}, Phone - {expectedPhone}")
    public CheckoutPage verifyAddBillingress(String title, String expectedFirstName, String expectedSecondName, String expectedCompany, String expectedAddress1,
                                              String expectedAddress2, String expectedCity, String expectedState, String expectedPostalCode, String expectedCountry,
                                              String expectedPhone) {
        driver.validation().Equals(driver.element().getText(billingName), (title + ". " + expectedFirstName + " " + expectedSecondName), "Delivery Name not matching")
                .Equals(driver.element().getText(billingCompany), expectedCompany, "Delivery Company not matching")
                .Equals(driver.element().getText(billingAddress1), expectedAddress1, "Delivery Address1 not matching")
                .Equals(driver.element().getText(billingAddress2), expectedAddress2, "Delivery Address2 not matching")
                .Equals(driver.element().getText(billingCityStatePostalCode), (expectedCity + " " + expectedState + " " + expectedPostalCode), "Delivery City/State/PostalCode not matching")
                .Equals(driver.element().getText(billingCountry), expectedCountry, "Delivery Country not matching")
                .Equals(driver.element().getText(billingPhone), expectedPhone, "Delivery Phone not matching");
        return this;

    }

}
