package io.petstore.swagger.task;

import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class GetUser implements Task {

    private final String user;

    public GetUser(String user) {
        this.user = user;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Get.resource(user).with(
                        requestSpecification -> requestSpecification
                                .contentType(ContentType.JSON)
                )
        );
    }
    public static GetUser fromUser(String user){
        return instrumented(GetUser.class, user);
    }
}
