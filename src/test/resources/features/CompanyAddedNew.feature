@sc1
Feature: Add new Company Address


  Scenario: Post New Company address to the company restful api
    Given Connect to the "company" api
    Then post a new address and retrieve the id in tmp file
      | newCompanyAddress                                                                                                                                                     |
      | " {"tags":["IT","Consultancy", "software"],"name": "Arshan IT Solutions","address":{"street": "Memphis village","zipcode": "4430145","city": "Missisipiiiiiiiiii"}} " |

  #Scenario: Check the new address id exists or not
   # Given Connect to the "company" api "https://contactsapi.apispark.net/v1/companies/"
   # Then post a new address and save the id in tmp file

  @manual @wip
  Scenario: An ASR report is sent by email to the brand when it is successfully processed by the retailer endpoint
    When an ASR is received from ‘Retailer’
    And it Is successfully processed
    Then an exact copy of the original ASR is sent by email with the subject “New ASR” to:
      | Email Address                   |
      | <suboor.mohammed@capgemini.com> |


  @manual @wip
  Scenario:  No ASR report email is sent to the brand if an invalid ASR is received from the retailer
    When an ASR is received from ‘Retailer’
    And it Is processed unsuccessfully
    Then meaningfull message will send by the email to:
      | Email Address                   |
      | <suboor.mohammed@capgemini.com> |

