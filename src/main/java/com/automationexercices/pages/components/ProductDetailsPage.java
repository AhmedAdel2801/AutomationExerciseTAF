package com.automationexercices.pages.components;

import com.automationexercices.drivers.GUIDriver;
import com.automationexercices.utils.dataReader.PropertyReader;
import com.automationexercices.utils.logs.LogsManager;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class ProductDetailsPage {
    private final GUIDriver driver;


    public ProductDetailsPage(GUIDriver driver) {
        this.driver = driver;
    }
    //Variables
    private String productDetailsEndpoint="/product_details/2";
    //Locators
    private final By productName = By.cssSelector(".product-information > h2");
    private final By productPrice = By.cssSelector(".product-information >span > span");
    private final By name= By.id("name");
    private final By email= By.id("email");
    private final By reviewTextArea= By.id("review");
    private final By submitButton= By.id("button-review");
    private final By reviewMessage= By.cssSelector("#review-section span");
    //Actions
    @Step("Navigate to Product Details Page")
    public ProductDetailsPage navigate() {
        driver.browser().navigateTo(PropertyReader.getProperty("baseUrlWeb") + productDetailsEndpoint);
        return this;
    }
    @Step("Add Review: UserName - {userName}, UserEmail - {userEmail}, ReviewText - {reviewText}")
    public ProductDetailsPage addReview(String userName, String userEmail, String reviewText) {
        driver.element().type(name, userName);
        driver.element().type(email, userEmail);
        driver.element().type(reviewTextArea, reviewText);
        driver.element().click(submitButton);
        return this;
    }

    //Validations
    @Step("Verify Product Details: Name - {expectedName}, Price - {expectedPrice}")
    public ProductDetailsPage verifyProductDetails(String expectedName, String expectedPrice) {
        String actualName = driver.element().getText(productName);
        String actualPrice = driver.element().getText(productPrice);
        LogsManager.info("Actual Product Name: " ,actualName , "Actual Product Price: " , actualPrice);
        driver.validation().Equals(actualName, expectedName, "Validating Product Name");
        driver.validation().Equals(actualPrice, expectedPrice, "Validating Product Price");

    return this;
    }
    @Step("Verify Review Submission Message: {expectedMessage}")
    public ProductDetailsPage verifyReviewMessage(String expectedMessage) {
        String actualMessage = driver.element().getText(reviewMessage);
        LogsManager.info("Actual Review Message: " ,actualMessage);
        driver.verification().Equals(actualMessage, expectedMessage, "Validating Review Submission Message");
        return this;
    }
}
