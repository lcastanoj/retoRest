package io.petstore.swagger.task;

import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Delete;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class DeleteUser implements Task {

    private final String user;

    public DeleteUser(String user) {
        this.user = user;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(
                Delete.from(user).with(
                        requestSpecification -> requestSpecification
                                .contentType(ContentType.JSON)
                )
        );
    }
    public static DeleteUser fromUser(String user){
        return instrumented(DeleteUser.class, user);
    }
}

