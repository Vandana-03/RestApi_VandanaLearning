Feature:
Validating Place APIs
Scenario: Verify if the place is being successfully added using AddPlaceAPI
	Given Add place Payload
	When User calls the "AddPlaceAPI" with a POST https request
	Then the API call is success with status code 200
	And "status" in the response body is "OK"
	And "scope" in the response body is "APP"
	
	#When user calls DeleteAPI with POST https request
	
#This is how you provide the comments

