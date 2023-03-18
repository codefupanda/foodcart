Customer Account Created -> Account Verification Code Generated And Sent via SMS 
Customer Enters ->  Account Verified (phone number) -> 

Customer Account Updated -> Account Verified (phone number) -> 


Browsed Restaurants -> Checked out Menu -> Added the Items to "plate" from one restaurant -> Payment Done -> Order confirmed -> Order Food Preparing ->  Delivery Partner Assigned -> Order Prepared -> Order being delivered -> order completed

Order completed -> Delivery Partner Paid

Domain -> Subdomain -> 


# Entity Board
---------------

CustomerAccount:
	Name name
	PhoneNumber phoneNumber

PhoneNumber:
	String countryCode
	String phoneNumber ()
	bool verified?

Name:
	String firstName
	String secondName


