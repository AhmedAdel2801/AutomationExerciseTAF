package com.automationexercices.pages.components;

import com.automationexercices.drivers.GUIDriver;
import com.automationexercices.utils.dataReader.PropertyReader;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class SignupLoginPage {
   public NavigationBarComponent navigationBar;
    private GUIDriver driver;
    private final String signupLoginEndpoint="/login";
    public SignupLoginPage(GUIDriver driver) {
        this.driver = driver;
        navigationBar=new NavigationBarComponent(driver);
    }
    //locators
    private final By loginEmail= By.cssSelector("input[data-qa='login-email']");
    private final By loginPassword= By.cssSelector("input[data-qa='login-password']");
    private final By loginButton= By.cssSelector("button[data-qa='login-button']");
    private final By signupName= By.cssSelector("input[data-qa='signup-name']");
    private final By signupEmail= By.cssSelector("input[data-qa='signup-email']");
    private final By signupButton= By.cssSelector("button[data-qa='signup-button']");
    private final By SignupLabel= By.cssSelector(".signup-form >h2");
    private final By LoginError= By.cssSelector(".login-form p");
    private final By RegisterError= By.cssSelector(".signup-form p");




    //actions
    @Step("Navigate to Signup/Login Page")
    public SignupLoginPage navigate(){
    driver.browser().navigateTo(PropertyReader.getProperty("baseUrlWeb")+signupLoginEndpoint);
    return this;
    }
    @Step("Enter login email: {email}")
    public SignupLoginPage enterLoginEmail(String email){
    driver.element().type(loginEmail,email);
    return this;
    }
    @Step("Enter login password: {password}")
    public SignupLoginPage enterLoginPassword(String password){
    driver.element().type(loginPassword,password);
    return this;
    }
    @Step("Click login button")
    public SignupLoginPage clickLoginButton(){
    driver.element().click(loginButton);
    return this;
    }
    @Step("Enter signup name: {name}")
    public SignupLoginPage enterSignupName(String name){
    driver.element().type(signupName,name);
    return this;
    }
    @Step("Enter signup email: {email}")
    public SignupLoginPage enterSignupEmail(String email){
    driver.element().type(signupEmail,email);
    return this;
    }
    @Step("Click signup button")
    public SignupLoginPage clickSignupButton(){
    driver.element().click(signupButton);
    return new SignupLoginPage(driver);
    }

    //validations
    @Step("Verify Signup label is displayed")
    public SignupLoginPage VerifySignupLabelIsDisplayed() {
        driver.verification().isElementVisible(SignupLabel);
        return this;
    }
    @Step("Verify Login error message is displayed: {errorExpected}")
    public SignupLoginPage VerifyLoginErrorMessageIsDisplayed(String errorExpected) {
    String errorActual =driver.element().getText(LoginError);
        driver.verification().Equals(errorActual,errorExpected,"Login error message is not as expected");
        return this;
    }
    @Step("Verify Register error message is displayed: {errorExpected}")
    public SignupLoginPage VerifyRegisterErrorMessageIsDisplayed(String errorExpected) {
        String errorActual = driver.element().getText(RegisterError);
        driver.verification().Equals(errorActual, errorExpected, "Register error message is not as expected");
        return this;
    }

}
