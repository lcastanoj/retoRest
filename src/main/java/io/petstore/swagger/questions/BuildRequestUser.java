package io.petstore.swagger.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import io.petstore.swagger.models.PostUserModel;

import java.util.Collections;
import java.util.List;

public class BuildRequestUser implements Question<List<PostUserModel>> {
    private final String id;
    private final String username;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String password;
    private final String phone;
    private final String userStatus;

    public BuildRequestUser(String id, String username, String firstName, String lastName, String email, String password, String phone, String userStatus) {
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
    public List<PostUserModel> answeredBy(Actor actor) {
        PostUserModel array = PostUserModel.builder()
                .id(Long.parseLong(id))
                .username(username)
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .password(password)
                .phone(phone)
                .userStatus(Long.parseLong(userStatus))
                .build();

        return Collections.singletonList(array);
    }

    public static BuildRequestUser was(String id, String username, String firstName, String lastName, String email, String password, String phone, String userStatus){
        return new BuildRequestUser(id, username, firstName, lastName, email, password, phone, userStatus);
    }
}
