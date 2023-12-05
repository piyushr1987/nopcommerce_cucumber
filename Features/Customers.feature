Feature: Customer
Scenario: Add customer
Given User Launch Chrome browser 
	When User opens URL "http://admin-demo.nopcommerce.com/login" 
	And User enters Email as "admin@yourstore.com" and Password as "admin" 
	And Click on Login
	Then User can view dashboard
	When user click on customer menu
	And  click on customer menu item
	And  click on add new button
	Then user can view add new customer page
	When user enter customer info
	And click on save button
	Then user can view confirmation message "The new customer has been added successfully."
	And close the browser
