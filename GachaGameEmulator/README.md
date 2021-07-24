# A Gacha game

## Motivations and Purpose

What will the application do? 

- *NOTE: throughout the project I use the terms Card and Unit interchangeably, they have the same meaning here.*

- "Gacha games are video games that implement the gacha mechanic. This is somewhat similar to loot boxes, inducing 
players to spend in-game currency to receive a random virtual item. Most of these games are free-to-play mobile games, 
where the gacha serves as an incentive to spend real-world money. (source: https://en.wikipedia.org/wiki/Gacha_game)"  
The application will simulate a mobile gacha game experience (as a desktop java app), where you get to summon for units 
you really want in your collection. The summoning system will have specific rates for certain rarities of each unit. 
You can then remove units from your collection that you don't like, or change the order in which your units are 
displayed, i.e. by order of rarity, or the category they belong to, or both. Then you can feel proud of your 
collection. You can have multiple Collections, which will be stored in a list of Collections. 

Who will use it?

- Any Gacha game or card collecting enthusiast will enjoy this application, and especially those that are fans of characters
from **MARVEL**, **NARUTO** and **DRAGON BALL**.

Why is this project of interest to you?

- I have loved collection card games since I was 8, from physical ones "**WWE Slam ATTAX**", "**Marvel HERO ATTAX**", to 
games simulated in mobile apps, such as "**Dragon Ball Z: *Dokkan Battle***","**NARUTO: *Ultimate Ninja Blazing***", and
"**Dragon Ball Legends**". The feeling after getting your favorite character, even more so as a card of the highest 
rarity in the game is indescribable, that surge of dopamine can stay with you for days, even weeks. However, for these
games, real money is a huge differentiator in how much you can enjoy the game. With physical cards, the only way you can 
participate in this activity is if you buy card packets with real money, which are very cheap back in India, but here 
it makes my wallet run away out of my pocket, so I haven't even dared to do so in years. It's a lot easier to do with 
the mobile games, since they are free to play, however since the rates for pulling "cards" of certain rarities are
pretty low, buying in game currency to "get" more "card packs" makes the game more fun, however this is very similar to
gambling where you're investing in just a tiny chance of getting something you want, and there's a high chance all that
money will just get wasted, so I don't invest there either. With this game I aim to create, in game currency required to
"get" "card packs" will not require any real money to buy, there's no currency, summon for free, which makes this a small
way of enjoying the same experience of getting characters you want without possibly feeling guilty about it. 

## User Stories

- As a user, I want to be able to summon for units as much as I want (no currency restriction).
- As a user, I want to be able to choose to summon for units of one specific category, two categories, or all three at 
the same time.
- As a user, I want to be able to find a specific unit by searching for it by name.
- As a user, I want to be able to remove duplicates of a card of my choice for a collection within which it exists.
- As a user, I want to be able to see a list of all my units in the order I've specified.
- As a user, I want all cards I summon for to be added to a collection of my choice.
- As a user, I want the units I summon to be added into my Collection in some order of Category, or rarity, which I will
 specify.
- As a user, I want to be able to save my Card collections to file
- As a user, I want to be able to load my Card collections from file
- As a user, when I select the quit option from the application menu, I want to be reminded to save my collections to 
file and have the option to do so or not.
 - As a user, when I start the application, I want to be given the option to load my collections from file.
 
 ##Phase 4: Task 2
 
 - "Make appropriate use of the Map interface somewhere in your code." -> I created a Map field called 
 currentCollectionAsMap in my GachaGameAppGui class, intializing it in the insertCardsToMap() method as a HashMap<>(),
 which is responsible for holding cards in currentCardCollection, and I added functionality for checking if a 
 card exists in the collection to demonstrate its use. When checking if a card exists, it will look into the map to 
 find the card, and since searching in a Map follows O(1), it is much faster than using lists to do so, which would
 be O(n). The map stores values in <String, Card> pairs, where the key is the name of the card, and card is the 
 corresponding card. So when you choose to check if a certain card exists, We just check if it's in the map, super
 quick. It's of course difficult to see the difference here, in a Swing app where I only had the capacity to create 
 less than 60 cards, but in the real world, card collections involve collecting thousands of cards, and apps are 
 far more complex than a simple Swing app, so there the extra efficiency would be priceless. I fill the map using the 
 insertCardsToMap() method in the GachaGameAppGui class, and find the card and return corresponding found/not found
 message in the findCardInMap(String name) method. These methods are then used in the handleCheckCardClick()
 method and the handleCheckCardConfirm() method, which are responsible for the creation of the panels corresponding
 the display of the functionality of this map.

 ##Phase 4: Task 3
 
 - One might look at my ListOfCardCollections class and think it's presence 
 redundant, but it's purpose was to simplify the saving process as much as possible, so I'd only need to save and load 
 one object really.
 
 - I do think I should've refactored code for changing panels in cardLayout JPanel in my GachaGameAppGui class,
 very poor code design on my part, I wish I had abstracted that code into a method in the class, would've saved a lot 
 of space too.
 
 - I also think I could've moved the "Remove all duplicates" functionality from ui to the CardCollection class, as that
 makes more sense from a design standpoint, same goes for the "Check if a certain card is there" functionality.

- Other than that, I really did design my project with a very minimalistic approach, so I can't think
of anything else I would change, and I definitely can't think of any simple way of refactoring my code
to reduce coupling between the classes, because there isn't much coupling in my project in the first place.
The Card class is the only class outside of ui that is being used by several classes at once, and that is 
out of pure necessity, it's not something I can refactor really.


