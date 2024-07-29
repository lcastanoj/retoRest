package io.petstore.swagger.task;

import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Put;
import io.petstore.swagger.models.PostUserModel;
import io.petstore.swagger.questions.BuildPutBodyAndGetResponseUser;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class PutUser implements Task {

    private final String path;
    private final String id;
    private final String username;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String password;
    private final String phone;
    private final String userStatus;

    public PutUser(String path, String id, String username, String firstName, String lastName, String email, String password, String phone, String userStatus) {
        this.path = path;
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.userStatus = userStatus;
    }


    @Override
    public <T extends Actor> void performAs(T actor) {
        PostUserModel UserInfo = actor.asksFor(BuildPutBodyAndGetResponseUser.was(id, username, firstName, lastName, email, password, "+57 " + phone, userStatus));
        actor.attemptsTo(
                Put.to(path).with(
                        requestSpecification -> requestSpecification
                                .contentType(ContentType.JSON)
                                .body(UserInfo)
                )
        );
    }
    public static PutUser fromUser(String path, String id, String username, String firstName, String lastName, String email, String password, String phone, String userStatus){
        return instrumented(PutUser.class, path, id, username, firstName, lastName, email, password, phone, userStatus);
    }
}
