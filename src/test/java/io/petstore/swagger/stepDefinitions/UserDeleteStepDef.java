package io.petstore.swagger.stepDefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.petstore.swagger.questions.ResponseServerBody;
import io.petstore.swagger.questions.ResponseServerCode;
import io.petstore.swagger.task.CallToAPI;
import io.petstore.swagger.task.DeleteUser;
import io.petstore.swagger.task.GetUser;
import net.serenitybdd.core.annotations.events.BeforeScenario;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.GivenWhenThen;
import io.petstore.swagger.constants.Constants;
import io.petstore.swagger.questions.ResponseBodyUser;

import java.util.Map;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.CoreMatchers.equalTo;

public class UserDeleteStepDef {
    private static final Actor user = Actor.named("user");
    static Map<String, String> path;

    @BeforeScenario
    public static void setUp () {
        user.wasAbleTo(CallToAPI.en());
        path = Constants.constantsExcelUser(1,"Path");
        user.attemptsTo(
                GetUser.fromUser(path.get("path") + path.get("pathget"))
        );
        user.should(
                GivenWhenThen.seeThat("The response code is:", ResponseServerCode.was(), equalTo(200))
        );
        user.should(
                seeThat("The response body is:", ResponseServerBody.was(), equalTo(Constants.infoResponse(user,1,"UsersPut")))
        );
    }

    @When("user uses the path user to delete a user information")
    public void userUsesThePathUserToDeleteAUserInformation() {
        user.wasAbleTo(CallToAPI.en());
        user.attemptsTo(
                DeleteUser.fromUser(path.get("path") + path.get("pathget"))
        );
    }

    @Then("user can validate the response service delete code is {int}")
    public void userCanValidateTheResponseServiceCodeIs(Integer code) {
        user.should(
                seeThat("The response code is:", ResponseServerCode.was(), equalTo(code))
        );
    }

    @Then("the body response should has a message whit the users name")
    public void theBodyResponseShouldHasAMessageWhitTheUsersName() {
        Map<String, String> usersExcel = Constants.constantsExcelUser(1,"Users");
        user.should(
                seeThat("The message in the body is:", ResponseBodyUser.was(), equalTo(usersExcel.get("username")))
        );
    }

}
