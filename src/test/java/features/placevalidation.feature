Feature: Validating AddpLace API

@AddPlace @Regression
Scenario Outline: Verify if place is being successfully added using AddplaceApi
    Given Add place Payload with "<name>" "<language>" "<address>"
    When User calls "AddPlaceAPI" with "post" Http Request
    Then the APi call success eith status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And Verify place_Id create maps to "<name>" using "getPlaceAPI"
    
Examples:
    |name     | language |address          |
    |Bucky92  | English  |test1 changi greeen|
 #   |Munny    | Spanish  |baker street dsjkd|
    
    
@DeletePlace @Regression
    
Scenario: Verify Deletplace API functionality is working fine
    
    Given Deleteplace Payload
    When User calls "deletePlaceAPI" with "post" Http Request
    Then the APi call success eith status code 200
    And "status" in response body is "OK"

