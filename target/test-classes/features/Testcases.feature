Feature: Ordering product in online Demo shop website

  @Addproducttowishlist
  Scenario Outline:  	Add_and_verify_lowest_price_product_to_my_cart
		Given Verify the user is on the demo shop home page
		And I add "<product_count>" different products to my wishlist
		When I view my wishlist table
		Then I find total four selected items in my wishlist
		When I search for lowest price product
		And I am able to add the lowest price item to my cart
		Then I am able to verify the item in my cart
		
		Examples:
		| product_count |
		| 4							|
    
  