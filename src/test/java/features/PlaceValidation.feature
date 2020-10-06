Feature: Validating Place API's
@AddPlace @Regression
Scenario Outline: Verify if place is being successfully added using AddPlaceAPI
Given Add Place Payload "<name>" "<language>" "<address>"
When User calls "AddPlaceAPI" with "POST" http request
Then API call is success with status code 200
And "status" in reponse body is "OK"
And "scope" in reponse body is "APP"
And verify place_id created maps to "<name>" using "getPlaceAPI"
Examples:
   |name  |language|address             |
   |srikar|French  |auburn hills detroit|
  # |sudha|English  |saint matieu street |
  
 

@DeletePlace @Regression
Scenario: Verify if Delete Place functionality is working

	Given DeletePlace Payload
	When User calls "deletePlaceAPI" with "POST" http request
    Then API call is success with status code 200
    And "status" in reponse body is "OK"
	
  
  