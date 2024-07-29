Feature: CRUD user PetStore

  Scenario: Create
    When user uses the path user to create a new user with the information
    Then user can validate the response service put code is 200
    And the body response should has a message "ok"

  Scenario: Read
    When user uses the path user to get a user information
    Then user can validate the response service get code is 200
    And the body response should has the user information

  Scenario: Update
    When user uses the path user to update the user information
    Then user can validate the response service update code is 200
    And the body response should has the updated user information

  Scenario: Delete
    When user uses the path user to delete a user information
    Then user can validate the response service delete code is 200
    And the body response should has a message whit the users name