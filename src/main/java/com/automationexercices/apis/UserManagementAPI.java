package com.automationexercices.apis;

import com.automationexercices.utils.logs.LogsManager;
import com.automationexercices.validations.Verification;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

public class UserManagementAPI {

 RequestSpecification requestSpecification;
 Response response;
Verification verification;
 public UserManagementAPI(){
     requestSpecification= RestAssured.given();
     verification=new Verification();
 }
 //endPoints
    private static final String createAccount_endpoint="/createAccount";
  private static final String deleteAccount_endpoint="/deleteAccount";
  //API Methods
 @Step("Create User Account with all details")
  public UserManagementAPI createRegisterUserAccount(String name, String email, String password,String title,
                                                     String day, String month, String year,String firstName,
                                                     String lastName,String company, String address1,
                                                     String address2, String country,
                                                     String state, String city,
                                                     String zipcode, String mobileNumber)
 {
     Map<String,String> formParams= new HashMap<>();
formParams.put("name",name);
formParams.put("email",email);
formParams.put("password",password);
formParams.put("title",title);
formParams.put("day",day);
formParams.put("month",month);
formParams.put("year",year);
     formParams.put("firstname",firstName);
     formParams.put("lastname",lastName);
formParams.put("company",company);
formParams.put("address1",address1);
formParams.put("address2",address2);
formParams.put("country",country);
formParams.put("state",state);
formParams.put("city",city);
formParams.put("zipcode",zipcode);
     formParams.put("mobile_number",mobileNumber);
 response=requestSpecification.spec(Builder.getUserMangmentSpecification(formParams))
     .post(createAccount_endpoint);
     LogsManager.info(response.asPrettyString());
        return this;
  }
    @Step("Create User Account with minimal details")
    public UserManagementAPI createRegisterUserAccount(String name, String email, String password,String firstName,
                                                       String lastName)
    {
        Map<String,String> formParams= new HashMap<>();
        formParams.put("name",name);
        formParams.put("email",email);
        formParams.put("password",password);
        formParams.put("title","Mr");
        formParams.put("day","1");
        formParams.put("month","March");
        formParams.put("year","2000");
        formParams.put("firstname",firstName);
        formParams.put("lastname",lastName);
        formParams.put("company","EXAB");
        formParams.put("address1","address1");
        formParams.put("address2","address2");
        formParams.put("country", "United States");
        formParams.put("state","California");
        formParams.put("city","Los Angeles");
        formParams.put("zipcode","11835");
        formParams.put("mobile_number","01002191102");
        response=requestSpecification.spec(Builder.getUserMangmentSpecification(formParams))
                .post(createAccount_endpoint);
        LogsManager.info(response.asPrettyString());
        return this;
    }
  @Step("Delete User Account with email")
  public UserManagementAPI deleteUserAccount(String email,String password){
      Map<String,String> formParams= new HashMap<>();
      formParams.put("email",email);
      formParams.put("password",password);
      response=requestSpecification.spec(Builder.getUserMangmentSpecification(formParams))
              .delete(deleteAccount_endpoint);
      LogsManager.info(response.asPrettyString());
      return this;
  }

  //validation methods
    @Step("Verify User Account Created Successfully")
    public UserManagementAPI verifyUserAccountCreatedSuccessfully(){
verification.Equals(response.jsonPath().get("message"),"User created!",
        "Verifying user account created successfully");
        return this;
    }
    @Step("Verify User Account Deleted Successfully")
    public UserManagementAPI verifyUserAccountDeletedSuccessfully() {
        verification.Equals(response.jsonPath().get("message"), "Account deleted!",
                "Verifying user account deleted successfully");
        return this;
    }

}