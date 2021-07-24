package model;

import java.util.ArrayList;
import java.util.List;

// The functionality of this class is applied when a user wants to order a given card collection a certain way,
// according to their own preference
public class OrderGivenCollection {
    private String firstCategory;
    private String secondCategory;
    private String thirdCategory;
    private CardCollection cardCollection;
    private CardCollection cardCollectionOrderedByCategory = new CardCollection();
    private CardCollection cardCollectionOrderedByRarity = new CardCollection();

    //EFFECTS: creates object of OrderGivenCollection
    public OrderGivenCollection(CardCollection cardCollection) {
        this.cardCollection = cardCollection;
    }

    //REQUIRES: fc, sc, tc correspond distinctly to "Naruto" "Marvel" "Dragon Ball" in any order
    //EFFECTS: returns a card collection ordered by category with respect to the user preferences passed as parameters
    public CardCollection getCardCollectionOrderedByCategory(String fc, String sc, String tc) {
        this.firstCategory = fc;
        this.secondCategory = sc;
        this.thirdCategory = tc;
        List<Card> orderedCards = new ArrayList<>();
        for (Card card : cardCollection.getCards()) {
            if (card.getCategory().equals(firstCategory)) {
                orderedCards.add(card);
            }
        }
        for (Card card : cardCollection.getCards()) {
            if (card.getCategory().equals(secondCategory)) {
                orderedCards.add(card);
            }
        }
        for (Card card : cardCollection.getCards()) {
            if (card.getCategory().equals(thirdCategory)) {
                orderedCards.add(card);
            }
        }
        cardCollectionOrderedByCategory.setCards(orderedCards);
        return cardCollectionOrderedByCategory;
    }

    //Split order by rarity into three methods to handle the lines per method restriction
    //EFFECTS: returns a list of all items in card collection ordered by the top three rarities (LR, UR, SSR)
    public List<Card> getCardCollectionOrderedTopThreeRarities() {
        List<Card> orderedByRarityCards = new ArrayList<>();
        for (Card card : cardCollection.getCards()) {
            if (card.getRarity().equals("LR")) {
                orderedByRarityCards.add(card);
            }
        }
        for (Card card : cardCollection.getCards()) {
            if (card.getRarity().equals("UR")) {
                orderedByRarityCards.add(card);
            }
        }
        for (Card card : cardCollection.getCards()) {
            if (card.getRarity().equals("SSR")) {
                orderedByRarityCards.add(card);
            }
        }

        return orderedByRarityCards;
    }

    //Split order by rarity into three methods to handle the lines per method restriction
    //EFFECTS: returns a list of all items in card collection ordered by the lower two rarities (SR,R)
    public List<Card> getCardCollectionOrderedLowerTwoRarities() {
        List<Card> orderedCardsLowerTwoRarities = new ArrayList<>();
        for (Card card : cardCollection.getCards()) {
            if (card.getRarity().equals("SR")) {
                orderedCardsLowerTwoRarities.add(card);
            }
        }
        for (Card card : cardCollection.getCards()) {
            if (card.getRarity().equals("R")) {
                orderedCardsLowerTwoRarities.add(card);
            }
        }
        return orderedCardsLowerTwoRarities;
    }

    //EFFECTS: creates a card collection, adds Cards from list returned by getCardCollectionOrderedTopThreeRarities()
    // and getCardCollectionOrderedLowerTwoRarities() in order, and returns this card collection with cards ordered by
    // rarity
    public CardCollection getCardCollectionOrderedByRarity() {
        List<Card> lowerOrdered = getCardCollectionOrderedLowerTwoRarities();
        List<Card> higherOrdered = getCardCollectionOrderedTopThreeRarities();
        List<Card> allOrdered = new ArrayList<>();
        for (Card card : higherOrdered) {
            allOrdered.add(card);
        }
        for (Card card : lowerOrdered) {
            allOrdered.add(card);
        }
        cardCollectionOrderedByRarity.setCards(allOrdered);
        return cardCollectionOrderedByRarity;
    }

}
