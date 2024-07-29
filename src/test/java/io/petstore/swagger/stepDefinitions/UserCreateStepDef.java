package io.petstore.swagger.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.petstore.swagger.questions.ResponseServerBody;
import io.petstore.swagger.questions.ResponseServerCode;
import io.petstore.swagger.task.CallToAPI;
import io.petstore.swagger.task.GetUser;
import io.petstore.swagger.task.PostUser;
import net.serenitybdd.core.annotations.events.BeforeScenario;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.GivenWhenThen;
import io.petstore.swagger.constants.Constants;
import io.petstore.swagger.questions.ResponseBodyUser;

import java.util.Map;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.CoreMatchers.equalTo;

public class UserCreateStepDef {

    private static final Actor user = Actor.named("user");
    private static Map<String, String> path;

    @BeforeScenario
    public static void setUp() {
        user.wasAbleTo(CallToAPI.en());
        path = Constants.constantsExcelUser(1, "Path");
        user.attemptsTo(
                GetUser.fromUser(path.get("path") + path.get("pathget"))
        );
        user.should(
                GivenWhenThen.seeThat("The response code is:", ResponseServerCode.was(), equalTo(404))
        );

        System.out.println("expectedBeforeCreateMessage = " + Constants.getExpectedJsonResponseUser("User Not Found", 0));
        user.should(
                seeThat("The response body is:", ResponseServerBody.was(), equalTo(Constants.getExpectedJsonResponseUser("User Not Found", 0)))
        );
    }

    @When("user uses the path user to create a new user with the information")
    public void userUsesThePathUserToCreateANewUserWithTheInformation() {
        user.wasAbleTo(CallToAPI.en());
        path = Constants.constantsExcelUser(1, "Path");
        Map<String, String> excelData = Constants.constantsExcelUser(1, "Users");
        user.attemptsTo(
                PostUser.fromUser(path.get("path") + path.get("pathpost"),
                        excelData.get("id"),
                        excelData.get("username"),
                        excelData.get("firstName"),
                        excelData.get("lastName"),
                        excelData.get("email"),
                        excelData.get("password"),
                        excelData.get("phone"),
                        excelData.get("userStatus"))
        );
    }

    @Then("user can validate the response service put code is {int}")
    public void userCanValidateTheResponseServiceCodeIs(Integer code) {
        user.should(
                seeThat("The response code is:", ResponseServerCode.was(), equalTo(code))
        );
    }

    @And("the body response should has a message {string}")
    public void theBodyResponseShouldHasAMessage(String message) {
        user.should(
                seeThat("The message in the body is:", ResponseBodyUser.was(), equalTo(message))
        );
    }
}
