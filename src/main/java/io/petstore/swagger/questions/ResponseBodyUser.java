package io.petstore.swagger.questions;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class ResponseBodyUser implements Question<String> {

    @Override
    public String answeredBy(Actor actor) {
        String response = SerenityRest.lastResponse().body().asString();
        JsonObject jsonObject = JsonParser.parseString(response).getAsJsonObject();
        System.out.println("jsonObject = " + jsonObject);
        return jsonObject.get("message").getAsString();
    }
    public static ResponseBodyUser was(){
        return new ResponseBodyUser();
    }
}
