package io.petstore.swagger.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.petstore.swagger.questions.ResponseServerCode;
import io.petstore.swagger.task.CallToAPI;
import io.petstore.swagger.task.PutUser;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.GivenWhenThen;
import io.petstore.swagger.constants.Constants;
import io.petstore.swagger.questions.ResponseBodyUser;

import java.util.Map;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.CoreMatchers.equalTo;

public class UserUpdateStepDef {

    private static final Actor user = Actor.named("user");

    @When("user uses the path user to update the user information")
    public void userUsesThePathUserToUpdateTheUserInformation() {
        user.wasAbleTo(CallToAPI.en());
        Map<String, String> path = Constants.constantsExcelUser(1, "Path");
        Map<String, String> excelUserUpdate = Constants.constantsExcelUser(1, "UsersPut");
        user.attemptsTo(
                PutUser.fromUser(path.get("path") + path.get("pathget"), excelUserUpdate.get("id"),
                        excelUserUpdate.get("username"),
                        excelUserUpdate.get("firstName"),
                        excelUserUpdate.get("lastName"),
                        excelUserUpdate.get("email"),
                        excelUserUpdate.get("password"),
                        excelUserUpdate.get("phone"),
                        excelUserUpdate.get("userStatus"))
        );
    }

    @Then("user can validate the response service update code is {int}")
    public void userCanValidateTheResponseServiceCodeIs(Integer code) {
        user.should(
                GivenWhenThen.seeThat("The response code is:", ResponseServerCode.was(), equalTo(code))
        );
    }
    @And("the body response should has the updated user information")
    public void theBodyResponseShouldHasTheUpdatedUserInformation() {
        user.should(
                seeThat("The message in the body is:", ResponseBodyUser.was(), equalTo("1"))
        );
    }
}
