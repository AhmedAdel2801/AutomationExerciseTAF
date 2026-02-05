package com.automationexercices.apis;

import com.automationexercices.utils.dataReader.PropertyReader;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;


import java.util.Map;

public class Builder {
    private static String baseURI= PropertyReader.getProperty("baseUrlApi");
    private Builder() {}
    public static RequestSpecification getUserMangmentSpecification(Map<String,?>formParams){
        return new RequestSpecBuilder() .setBaseUri(baseURI)
                .setContentType(ContentType.URLENC)
                .addFormParams(formParams)
                .build();
    }

}
