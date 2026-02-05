package com.automationexercices.pages.components;

import com.automationexercices.drivers.GUIDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class PaymentPage {
    private GUIDriver driver;
    public PaymentPage(GUIDriver driver) {
    this.driver = driver;
    }

    //Variables
    private String paymentEndpoint="/payment";

    //Locators
    private final By nameOnCard = By.name("name_on_card");
    private final By cardNumber = By.name("card_number");
    private final By cvc = By.name("cvc");
    private final By expirationMonth = By.name("expiry_month");
    private final By expirationYear = By.name("expiry_year");
    private final By payAndConfirmOrderButton = By.id("submit");
    private final By orderPlaced = By.cssSelector("h2 >  b");
    private final By continueButton = By.id("continue-button");
    private final By downloadInvoiceButton = By.xpath("//a[.='Download Invoice']");

    //Actions
    @Step("Fill Card Information: Name - {name}, Card Number - {cardNum}, CVC - {cvcNum}, Expiration Month - {expMonth}, Expiration Year - {expYear}")
    public PaymentPage fillCardInfo(String name, String cardNum, String cvcNum, String expMonth, String expYear) {
        driver.element().type(nameOnCard, name)
        .type(cardNumber, cardNum)
        .type(cvc, cvcNum)
        .type(expirationMonth, expMonth)
        .type(expirationYear, expYear)
                .click(payAndConfirmOrderButton);
        return this;
    }
    @Step("Click on Download Invoice Button")
    public PaymentPage clickDownloadInvoiceButton() {
        driver.element().click(downloadInvoiceButton);
        return this;
    }

    //Validations
    @Step("Verify Order Placed Successfully Message: {expectedMessage}")
    public PaymentPage verifyOrderPlacedSuccessfully(String expectedMessage) {

        driver.verification().Equals(driver.element().getText(orderPlaced),expectedMessage ,"Validating Order Placed Message");
        return this;
    }
    @Step("Verify invoide is downloaded successfully")
    public PaymentPage verifyInvoiceIsDownloadedSuccessfully(String invoiceName) {
        driver.verification().assertFileExists(invoiceName, "Validating Invoice is downloaded successfully");
        return this;

    }
}
