package io.petstore.swagger.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.petstore.swagger.questions.ResponseServerBody;
import io.petstore.swagger.questions.ResponseServerCode;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.GivenWhenThen;
import io.petstore.swagger.constants.Constants;
import io.petstore.swagger.task.CallToAPI;
import io.petstore.swagger.task.GetUser;

import java.util.Map;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.CoreMatchers.equalTo;

public class UserReadStepDef {

    private static final Actor user = Actor.named("user");
    private static Map<String, String> path;


    @When("user uses the path user to get a user information")
    public void userUsesThePathUserToGetAUserInformation() {
        user.wasAbleTo(CallToAPI.en());
        path = Constants.constantsExcelUser(1,"Path");
        user.attemptsTo(
                GetUser.fromUser(path.get("path") + path.get("pathget"))
        );
    }

    @Then("user can validate the response service get code is {int}")
    public void userCanValidateTheResponseServiceCodeIs(Integer code) {
        user.should(
                GivenWhenThen.seeThat("The response code is:", ResponseServerCode.was(), equalTo(code))
        );
    }

    @And("the body response should has the user information")
    public void theBodyResponseShouldHasTheUserInformation() {

        user.should(
                seeThat("The response body is:", ResponseServerBody.was(), equalTo(Constants.infoResponse(user,1,"Users")))
        );
    }

}
