# My Personal Project: Smart Fridge

## What will this application do:

Smart Fridge enables users to
- add footItem into FoodItem list with foodItem name, purchase date, and it's expiry date
- Mark the foodItem in the FoodItem as Used or Out Of Stock
- add the foodItem which users want to buy into Shopping item list
- View the Expiry FoodItem in user's FoodItem List

Basically, user add what they have in FoodItem list, and SmartFridge will store the data, and also
check the Fooditem is expired or not.
User can also add what the want to buy in ShoppingItem list, and SmartFridge will store the data so the user
can be reminded when they're shopping.


## Who will use this?:
Everyone who's into cooking or buying food from outside

## Why this project interest you?:
Because I waste tons of food due to the unawareness of expiry date, 
so I want to build a helper for myself to track my FoodItem, also I always forgot
what I need to buy, so I want to build a shoppingItem list for myself to remind me to 
buy all the foodItem in the list

## Users Stories:
- As a user, I want to be able to add/delete a foodItem in my ShoppingItem list
- As a user, I want to be able to add/delete a foodItem in my FoodItem list
- As a user, I want to be able to view the list of foodItem in my ShoppingItem list 
- As a user, I want to be able to view the list of foodItem in my FoodItem list with its purchase date and expiry date.
- As a user, I want to be able to view the list of expiry FoodItems in my 
FoodItem list with its purchase date and expiry date.
- As a user, when I select the quit option from the application menu, I want to be reminded to save my current 
FoodItem list and ShoppingItem list, and have the option to do so or not.
- As a user, when I start the application, I want to be given the option to load my FoodItem and ShoppingItem list from 
file.

Phase 4: Task 2 :
- add Apple in your foodItem List with purchase date 2021/01/30 expiry date 2021/02/04
- add Banana in your shoppingItem List
- return expiry FoodItems in your foodItem List
- check expiry FoodItem on Apple
- delete Apple in your foodItem List with purchase date 2021/01/30 expiry date 2021/02/04
- delete Banana in your shoppingItem List

Phase 4: Task 3: is on the root folder "uml design diagram"
- I would probably create a abstract ItemList class, and let FoodItemList and ShoppingItemList to extends ItemList class, 
so I can make code more simple by making similar method in abstract class. 
- Or I can probably
create an interface ItemList to specify the class outlook for both FoodItemList and ShoppingItemList class.
- In ui, the cods is little messy, I would probably create more class in UI to seperate each task so the SmartFridgeApp 
class don't need to store all the behavior. 
- I can also make a consoleWriter  class in ui and implement Observer to write the console.