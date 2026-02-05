package com.automationexercices.pages.components;

import com.automationexercices.drivers.GUIDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class SignupPage {
    private final GUIDriver driver;
    public SignupPage(GUIDriver driver) {
        this.driver = driver;
    }
    //Locators
    private final By name=By.cssSelector("[data-qa='name']");
    private final By email=By.cssSelector("[data-qa='email']");
    private final By password=By.id("password");
    private final By day=By.id("days");
    private final By month=By.id("months");
    private final By year=By.cssSelector("[data-qa='years']");
    private final By newsletter=By.id("newsletter");
    private final By offersCheckbox=By.id("optin");
    private final By firstName=By.id("first_name");
    private final By lastName=By.id("last_name");
    private final By company=By.id("company");
    private final By address1=By.id("address1");
    private final By address2=By.id("address2");
    private final By country=By.id("country");
    private final By state=By.id("state");
    private final By city=By.id("city");
    private final By zipcode=By.id("zipcode");
    private final By mobileNumber=By.id("mobile_number");
    private final By createAccountButton=By.cssSelector("[data-qa='create-account']");
    private final By accountCreatedLabel=By.cssSelector("[data-qa='account-created']");
    private final By continueButton=By.cssSelector("[data-qa='continue-button']");


    //Actions
    @Step("Choose title: {title}") //Mr or Mrs
    private SignupPage chooseTitle(String title){
        By titleLocator= By.xpath("//input[@value='"+title+"']");
        driver.element().click(titleLocator);
        return this;
    }
    @Step("Fill registration form")
    public SignupPage fillRegistrationForm(String title, String passwordValue,
                                           String dayValue, String monthValue, String yearValue,
                                           String firstNameValue, String lastNameValue,
                                           String companyValue, String address1Value, String address2Value,
                                           String countryValue, String stateValue, String cityValue,
                                           String zipcodeValue,  String mobileNumberValue){
    chooseTitle(title);
    driver.element().type(this.password,passwordValue);
    driver.element().selectFromDropdown(this.day,dayValue);
    driver.element().selectFromDropdown(this.month,monthValue);
    driver.element().selectFromDropdown(this.year,yearValue);
    driver.element().click(this.newsletter);
    driver.element().click(this.offersCheckbox);
    driver.element().type(this.firstName,firstNameValue);
    driver.element().type(this.lastName,lastNameValue);
    driver.element().type(this.company,companyValue);
    driver.element().type(this.address1,address1Value);
    driver.element().type(this.address2,address2Value);
    driver.element().selectFromDropdown(this.country,countryValue);
    driver.element().type(this.state,stateValue);
    driver.element().type(this.city,cityValue);
    driver.element().type(this.zipcode,zipcodeValue);
    driver.element().type(this.mobileNumber,mobileNumberValue);

    return this;

    }
    @Step("Click create account button")
    public SignupPage clickCreateAccountButton(){
    driver.element().click(this.createAccountButton);
    return this;
    }
    //Validations
    @Step("Verify Account Created label is displayed")
    public SignupPage verifyAccountCreated() {
    driver.verification().isElementVisible(accountCreatedLabel);
    return this;
    }
    @Step ("Click continue button" )
    public NavigationBarComponent clickContinueButton() {
        driver.element().click(continueButton);
        return new NavigationBarComponent(driver);
    }

}
