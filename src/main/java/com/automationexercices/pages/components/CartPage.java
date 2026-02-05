package com.automationexercices.pages.components;

import com.automationexercices.drivers.GUIDriver;
import com.automationexercices.utils.dataReader.PropertyReader;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class CartPage {
private GUIDriver driver;
    public CartPage(GUIDriver driver) {
       this.driver=driver;

    }
    //Variables
    private String cartPageEndpoint="/view_cart";

    //Locators
    private final By proceedToCheckoutButton= By.xpath("//a[.='Proceed To Checkout']");
    //Dynamic Locators
    private By productName(String productName){
        return By.xpath("(//h4 /a[.='"+productName+"'])[1]");
    }
    private By productPrice(String productName){
        return By.xpath("(//h4 /a[.='"+productName+"'] //following::td[@class='cart_price']/p)[1]");
    }
    private By productQuantity(String productName){
        return By.xpath("(//h4 /a[.='"+productName+"'] //following::td[@class='cart_quantity']/button)[1]");
    }
    private By productTotalPrice(String productName){
        return By.xpath("(//h4 /a[.='"+productName+"'] //following::td[@class='cart_total']/p)[1]");
    }
    private By removeProductButton(String productName){
        return By.xpath("(//h4 /a[.='"+productName+"'] //following::td[@class='cart_delete']/a)[1]");
    }


    //Actions
    @Step("Navigate to Cart Page")
    public CartPage navigate(){
        driver.browser().navigateTo(PropertyReader.getProperty("baseUrlWeb") +cartPageEndpoint);
        return this;
    }
    @Step("Click on Proceed to Checkout Button")
    public CheckoutPage clickOnProceedToCheckoutButton(){
        driver.element().click(proceedToCheckoutButton);
        return new CheckoutPage(driver);
    }
    @Step ("Remove Product: {productName} from Cart")
    public CartPage removeProductFromCart(String productName){
        driver.element().click(removeProductButton(productName));
        return this;
    }
    //Validations
    @Step("Veifry Product in Cart: Name - {expectedName}, Price - {expectedPrice}, Quantity - {expectedQuantity}, Total Price - {expectedTotalPrice}")
    public CartPage verifyProductDetailsInCart(String expectedName, String expectedPrice, String expectedQuantity, String expectedTotalPrice) {
        String actualName = driver.element().getText(productName(expectedName));
        String actualPrice = driver.element().getText(productPrice(expectedName));
        String actualQuantity = driver.element().getText(productQuantity(expectedName));
        String actualTotalPrice = driver.element().getText(productTotalPrice(expectedName));
        driver.validation().Equals(actualName, expectedName, "Validating Product Name in Cart")
                .Equals(actualPrice, expectedPrice, "Validating Product Price in Cart")
                .Equals(actualQuantity, expectedQuantity, "Validating Product Quantity in Cart")
                .Equals(actualTotalPrice, expectedTotalPrice, "Validating Product Total Price in Cart");
                return this;

    }
}
