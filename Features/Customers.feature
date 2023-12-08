Feature: Customer

Background: steps common for all scenerios
Given User Launch Chrome browser 
	When User opens URL "http://admin-demo.nopcommerce.com/login" 
	And User enters Email as "admin@yourstore.com" and Password as "admin" 
	And Click on Login
	Then User can view dashboard
	When user click on customer menu
	And  click on customer menu item

@regression
Scenario: Add customer
  
	And  click on add new button
	Then user can view add new customer page
	When user enter customer info
	And click on save button
	Then user can view confirmation message "The new customer has been added successfully."
	And close the browser
	
	@regression
	Scenario: search customer by email
	
	And enter customer email address
	When click on search button
	Then user should found email in search table
	And close the browser.
	
	@regression
	Scenario: search customer by name
	 
	And Enter customer FirstName
	And Enter customer LastName
	When Click on search button
	Then User should found Name in the Search table
	And close browser 
