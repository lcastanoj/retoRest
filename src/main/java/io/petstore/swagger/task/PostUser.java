package io.petstore.swagger.task;

import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;
import io.petstore.swagger.models.PostUserModel;
import io.petstore.swagger.questions.BuildRequestUser;

import java.util.List;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class PostUser implements Task {

    private final String path;
    private final String id;
    private final String username;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String password;
    private final String phone;
    private final String userStatus;

    public PostUser(String path, String id, String username, String firstName, String lastName, String email, String password, String phone, String userStatus) {
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
        List<PostUserModel> UserInfo = actor.asksFor(BuildRequestUser.was(id, username, firstName, lastName, email, password, "+57 " + phone, userStatus));
        actor.attemptsTo(
                Post.to(path).with(
                        requestSpecification -> requestSpecification
                                .contentType(ContentType.JSON)
                                .body(UserInfo)
                )
        );
    }
    public static PostUser fromUser(String path, String id, String username, String firstName, String lastName, String email, String password, String phone, String userStatus){
        return instrumented(PostUser.class, path, id, username, firstName, lastName, email, password, phone, userStatus);
    }
}
