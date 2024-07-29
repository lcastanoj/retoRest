package io.petstore.swagger.questions;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class ResponseServerBody implements Question<String> {

    @Override
    public String answeredBy(Actor actor) {
        return SerenityRest.lastResponse().body().asString();
    }

    @Override
    public String toString() {
        return "ResponseServerBody{" +
                "response='" + SerenityRest.lastResponse().body().asString() + '\'' +
                '}';
    }

    public static ResponseServerBody was() {
        return new ResponseServerBody();
    }
}