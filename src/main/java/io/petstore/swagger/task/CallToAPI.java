package io.petstore.swagger.task;

import net.serenitybdd.core.environment.ConfiguredEnvironment;
import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.thucydides.core.util.EnvironmentVariables;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class CallToAPI implements Task {
    private final EnvironmentVariables environmentVariables = ConfiguredEnvironment.getEnvironmentVariables();
    private final String url = EnvironmentSpecificConfiguration.from(environmentVariables).getProperty("urlBase");

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.whoCan(CallAnApi.at(url));
    }

    public static CallToAPI en() {
        return instrumented(CallToAPI.class);
    }
}
