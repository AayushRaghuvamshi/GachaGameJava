package ui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

//This class represents the GUI for my game, built using java Swing
public class GachaGameAppGui extends JFrame {
    private JPanel buttonsPanel;
    private JButton summonTabButton;
    private JButton saveGameButton;
    private JButton removeAllDuplicatesButton;
    private JButton removeDuplicatesOfAButton;
    private JButton findACardButton;
    private JButton viewCollectionButton;
    private JButton orderByRarityButton;
    private JButton quitGameButton;
    private JPanel rootPanel;
    private JPanel parentCardLayoutPanel;
    private JPanel summonPanel;
    private JPanel viewingPanel;
    private JPanel orderRarityPanel;
    private JTextField marvelSummon;
    private JTextField narutoSummon;
    private JTextField dragonBallSummon;
    private JButton summonStart;
    private JTextField collectionSetSummon;
    private JList summonResultsGui;
    private JTextField viewChoice;
    private JButton confirmViewSelect;
    private JList viewCollectionList;
    private JScrollPane scrollViewCollection;
    private JButton loadGameButton;
    private JTextArea loadedGameFromDataTextArea;
    private JPanel loadPanel;
    private JPanel savePanel;
    private JTextArea savedGameToDataTextArea;
    private JButton orderACollectionByButton;
    private JTextField collectionNumberRarityField;
    private JButton confirmOrderRarityButton;
    private JPanel confirmOrderRarity;
    private JTextArea collectionOrderedByRarityTextArea;
    private JTextField orderFirstCatField;
    private JTextField orderSecondCatField;
    private JTextField orderThirdCatField;
    private JButton confirmOrderCategoryButton;
    private JPanel orderByCategoryPanel;
    private JTextField collectionToBeOrderedByCategoryField;
    private JPanel confirmOrderCategory;
    private JTextArea collectionOrderedByCategoryTextArea;
    private JPanel findCardPanel;
    private JTextField findCardInCollectionField;
    private JTextField cardToFindField;
    private JTextField cardFoundInField;
    private JButton findButton;
    private JPanel removeDuplicatesOfOneCardPanel;
    private JTextField resultOfRemoveOne;
    private JTextField removeACardTheCollectionField;
    private JTextField removeACardTheCardField;
    private JButton removeCardButton;
    private JPanel removeAllDuplicatesPanel;
    private JTextField removeAllChoiceField;
    private JTextField removeAllResultField;
    private JButton removeAllButton;
    private JPanel quitPanel;
    private JButton yesButton;
    private JButton noButton;
    private JPanel viewCardImagePanel;
    private JPanel summonCardImagePanel;
    private JPanel instructionsPanel;
    private JButton instructionsButton;
    private JTextArea forTheOrderByTextArea;
    private JPanel foundCardImagePanel;
    private JButton checkCardExistsButton;
    private JPanel checkIfCardPresentPanel;
    private JTextField checkCardColChoiceField;
    private JTextField checkCardChoiceField;
    private JButton confirmCheckCardButton;
    private JTextField checkedCardField;
    private JPanel checkCardImagePanel;
    private DefaultListModel summonResultsModel;
    private DefaultListModel viewCollectionListModel;


    private static final String JSON_STORE = "./GachaGameEmulator/data/listOfCardCollections.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private final Scanner sc = new Scanner(System.in);
    private Summoning summoner;
    private ListOfCardCollections myCardCollections;
    private CardCollection cardCollection1 = new CardCollection();
    private CardCollection cardCollection2 = new CardCollection();
    private CardCollection cardCollection3 = new CardCollection();
    private int marvelSet;
    private int narutoSet;
    private int dragonBallSet;
    int currentCollectionNumber;
    CardCollection currentCardCollection;
    private String summonCard;
    private String viewCard;
    private Image summonCardPic;
    private Image viewCardPic;
    private ImageIcon bigPic;
    private List<Card> summonResults;
    private Map<String, Card> currentCollectionAsMap;


    //EFFECTS: constructs instance of the class
    public GachaGameAppGui() {
        add(rootPanel);
        setSize(1920, 1080);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        myCardCollections = new ListOfCardCollections("Gacha Supreme");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        myCardCollections.addCollection(cardCollection1);
        myCardCollections.addCollection(cardCollection2);
        myCardCollections.addCollection(cardCollection3);
        currentCardCollection = cardCollection1;
        currentCollectionNumber = 1;
        handleClickEvents();
        setListModels();
    }

    //MODIFIES: this
    //EFFECTS: calls functions for setting summoning and viewing list models
    public void setListModels() {
        setSummonModel();
        setViewCollectionListModel();
    }

    //MODIFIES: this
    //EFFECTS: calls function for setting list model for summoning
    public void setSummonModel() {
        summonResultsModel = new DefaultListModel();
        summonResultsGui.setModel(summonResultsModel);
    }

    //MODIFIES: this
    //EFFECTS: calls function for setting list model for viewing
    public void setViewCollectionListModel() {
        viewCollectionListModel = new DefaultListModel();
        viewCollectionList.setModel(viewCollectionListModel);
    }

    //MODIFIES: this
    //EFFECTS: calls functions for handling buttons being clicked
    public void handleClickEvents() {
        handleSummonClick();
        handleViewCollectionsClick();
        handleOrderCollectionByRarityClick();
        handleSaveClick();
        handleSummonReqClick();
        handleViewConfirmClick();
        handleLoadClick();
        handleConfirmOrderRarity();
        handleOrderCollectionByCategoryClick();
        handleConfirmOrderByCategoryClick();
        handleFindCardConfirmClick();
        handleFindCardClick();
        handleRemoveDupesOneCardClick();
        handleRemoveDupesOfOneCardRemoveButtonClick();
        handleRemoveAllDupesClick();
        handleRemoveAllDupesConfirmClick();
        handleQuitButtonClick();
        handleYesButton();
        handleNoButton();
        handleInstructionsClick();
        handleClickCardSummon();
        handleClickCardView();
        handleCheckCardClick();
    }

    //MODIFIES: this
    //EFFECTS: plays sound, displays panel for checking card in collection, calls method for confirmation of choice
    public void handleCheckCardClick() {
        checkCardExistsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playSound("./GachaGameEmulator/sounds/button-30.wav");
                parentCardLayoutPanel.removeAll();
                parentCardLayoutPanel.add(checkIfCardPresentPanel);
                parentCardLayoutPanel.repaint();
                parentCardLayoutPanel.revalidate();
            }
        });
        handleCheckCardConfirm();
    }

    //MODIFIES: this
    //EFFECTS: Inserts cards from current collection to map of current collection, then follows specifications of
    //findCardInMap(), plays sound
    public void handleCheckCardConfirm() {
        confirmCheckCardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playSound("./GachaGameEmulator/sounds/button-09.wav");
                currentCollectionNumber = Integer.parseInt(checkCardColChoiceField.getText());
                currentCardCollection = getColFromNo(currentCollectionNumber);
                insertCardsToMap();
                String cardToFind = checkCardChoiceField.getText();
                checkedCardField.setText(findCardInMap(cardToFind));
            }
        });
    }

    //MODIFIES: this
    //EFFECTS: Displays instructions panel and plays sound
    public void handleInstructionsClick() {
        instructionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playSound("./GachaGameEmulator/sounds/button-30.wav");
                parentCardLayoutPanel.removeAll();
                parentCardLayoutPanel.add(instructionsPanel);
                parentCardLayoutPanel.repaint();
                parentCardLayoutPanel.revalidate();
            }
        });
    }

    /*source for panel change block of code
                parentCardLayoutPanel.removeAll();
                parentCardLayoutPanel.add(JPanel);
                parentCardLayoutPanel.repaint();
                parentCardLayoutPanel.revalidate();
    source: https://www.youtube.com/watch?v=xOCVOBDelFk&ab_channel=ScottCouprie
    */
    //MODIFIES:this
    //EFFECTS: displays image of clicked card
    public void handleClickCardSummon() {
        summonResultsGui.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int cardIndex = summonResultsGui.getSelectedIndex();
                if (cardIndex > -1) {
                    Color panelColor = new Color(255, 188, 23);
                    Card card = summonResults.get(cardIndex);
                    summonCard = card.getName();
                    JPanel summonImageReturnPanel = createPanelWithCardImage(summonCard);
                    summonImageReturnPanel.setBackground(panelColor);
                    System.out.println("now showing " + summonCard);
                    summonCardImagePanel.removeAll();
                    summonCardImagePanel.add(summonImageReturnPanel);
                    summonCardImagePanel.repaint();
                    summonCardImagePanel.revalidate();
                }
            }
        });
    }

    /*source for panel change block of code
                parentCardLayoutPanel.removeAll();
                parentCardLayoutPanel.add(JPanel);
                parentCardLayoutPanel.repaint();
                parentCardLayoutPanel.revalidate();
    source: https://www.youtube.com/watch?v=xOCVOBDelFk&ab_channel=ScottCouprie
    */
    //MODIFIES:this
    //EFFECTS: displays image of clicked card
    public void handleClickCardView() {
        viewCollectionList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int cardIndex = viewCollectionList.getSelectedIndex();
                if (cardIndex > -1) {
                    Color panelColor = new Color(90, 221, 107);
                    Card card = currentCardCollection.getCards().get(cardIndex);
                    viewCard = card.getName();
                    JPanel viewingImageReturnPanel = createPanelWithCardImage(viewCard);
                    viewingImageReturnPanel.setBackground(panelColor);
                    System.out.println("Now showing " + viewCard);
                    viewCardImagePanel.removeAll();
                    viewCardImagePanel.add(viewingImageReturnPanel);
                    viewCardImagePanel.repaint();
                    viewCardImagePanel.revalidate();
                }
            }
        });
    }

    /*source for panel change block of code
                parentCardLayoutPanel.removeAll();
                parentCardLayoutPanel.add(JPanel);
                parentCardLayoutPanel.repaint();
                parentCardLayoutPanel.revalidate();
    source: https://www.youtube.com/watch?v=xOCVOBDelFk&ab_channel=ScottCouprie
    */
    //MODIFIES: this
    //EFFECTS: launches corresponding panel with sound
    public void handleFindCardClick() {
        findACardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playSound("./GachaGameEmulator/sounds/button-30.wav");
                parentCardLayoutPanel.removeAll();
                parentCardLayoutPanel.add(findCardPanel);
                parentCardLayoutPanel.repaint();
                parentCardLayoutPanel.revalidate();
            }
        });
    }


    /*source for panel change block of code
    parentCardLayoutPanel.removeAll();
                parentCardLayoutPanel.add(JPanel);
                parentCardLayoutPanel.repaint();
                parentCardLayoutPanel.revalidate();
    source: https://www.youtube.com/watch?v=xOCVOBDelFk&ab_channel=ScottCouprie
    */
    //MODIFIES: this
    //EFFECTS: launches corresponding panel with sound
    public void handleRemoveDupesOneCardClick() {
        removeDuplicatesOfAButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playSound("./GachaGameEmulator/sounds/button-30.wav");
                parentCardLayoutPanel.removeAll();
                parentCardLayoutPanel.add(removeDuplicatesOfOneCardPanel);
                parentCardLayoutPanel.repaint();
                parentCardLayoutPanel.revalidate();
            }
        });
    }


    /*source for panel change block of code
    parentCardLayoutPanel.removeAll();
                parentCardLayoutPanel.add(JPanel);
                parentCardLayoutPanel.repaint();
                parentCardLayoutPanel.revalidate();
    source: https://www.youtube.com/watch?v=xOCVOBDelFk&ab_channel=ScottCouprie
    */
    //MODIFIES: this
    //EFFECTS: launches corresponding panel with sound and removes duplicates of card corresponding to user input
    public void handleRemoveDupesOfOneCardRemoveButtonClick() {
        removeCardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playSound("./GachaGameEmulator/sounds/button-09.wav");
                parentCardLayoutPanel.removeAll();
                parentCardLayoutPanel.add(removeDuplicatesOfOneCardPanel);
                parentCardLayoutPanel.repaint();
                parentCardLayoutPanel.revalidate();
                currentCollectionNumber = Integer.parseInt(removeACardTheCollectionField.getText());
                currentCardCollection = getColFromNo(currentCollectionNumber);
                String cardToFind = removeACardTheCardField.getText();
                resultOfRemoveOne.setText(removeDuplicatesOfACardFromCollection(cardToFind));
            }
        });
    }


    /*source for panel change block of code
    parentCardLayoutPanel.removeAll();
                parentCardLayoutPanel.add(JPanel);
                parentCardLayoutPanel.repaint();
                parentCardLayoutPanel.revalidate();
    source: https://www.youtube.com/watch?v=xOCVOBDelFk&ab_channel=ScottCouprie
    */
    //MODIFIES: this
    //EFFECTS: launches corresponding panel with sound and shows card found message
    public void handleFindCardConfirmClick() {
        findButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playSound("./GachaGameEmulator/sounds/button-09.wav");
                parentCardLayoutPanel.removeAll();
                parentCardLayoutPanel.add(findCardPanel);
                parentCardLayoutPanel.repaint();
                parentCardLayoutPanel.revalidate();
                currentCollectionNumber = Integer.parseInt(findCardInCollectionField.getText());
                currentCardCollection = getColFromNo(currentCollectionNumber);
                String name = cardToFindField.getText();
                String foundResult = findCard(name);
                cardFoundInField.setText(foundResult);
            }
        });
    }


    /*source for panel change block of code
    parentCardLayoutPanel.removeAll();
                parentCardLayoutPanel.add(JPanel);
                parentCardLayoutPanel.repaint();
                parentCardLayoutPanel.revalidate();
    source: https://www.youtube.com/watch?v=xOCVOBDelFk&ab_channel=ScottCouprie
    */
    //MODIFIES: this
    //EFFECTS: launches corresponding panel with sound
    public void handleRemoveAllDupesClick() {
        removeAllDuplicatesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playSound("./GachaGameEmulator/sounds/button-30.wav");
                parentCardLayoutPanel.removeAll();
                parentCardLayoutPanel.add(removeAllDuplicatesPanel);
                parentCardLayoutPanel.repaint();
                parentCardLayoutPanel.revalidate();
            }
        });
    }


    /*source for panel change block of code
    parentCardLayoutPanel.removeAll();
                parentCardLayoutPanel.add(JPanel);
                parentCardLayoutPanel.repaint();
                parentCardLayoutPanel.revalidate();
    source: https://www.youtube.com/watch?v=xOCVOBDelFk&ab_channel=ScottCouprie
    */
    //MODIFIES: this
    //EFFECTS:  launches corresponding panel with sound and removes all duplicates of cards in collection
    public void handleRemoveAllDupesConfirmClick() {
        removeAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playSound("./GachaGameEmulator/sounds/button-09.wav");
                parentCardLayoutPanel.removeAll();
                parentCardLayoutPanel.add(removeAllDuplicatesPanel);
                parentCardLayoutPanel.repaint();
                parentCardLayoutPanel.revalidate();
                currentCollectionNumber = Integer.parseInt(removeAllChoiceField.getText());
                currentCardCollection = getColFromNo(currentCollectionNumber);
                currentCardCollection.removeAllDuplicateCardsInCollection();
                removeAllResultField.setText("Successfully removed all duplicates!");
            }
        });
    }

    /*source for panel change block of code
    parentCardLayoutPanel.removeAll();
                parentCardLayoutPanel.add(JPanel);
                parentCardLayoutPanel.repaint();
                parentCardLayoutPanel.revalidate();
    source: https://www.youtube.com/watch?v=xOCVOBDelFk&ab_channel=ScottCouprie
    */
    //MODIFIES: this
    //EFFECTS:  launches corresponding panel with sound and orders collection by rarity
    public void handleConfirmOrderRarity() {
        confirmOrderRarityButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playSound("./GachaGameEmulator/sounds/button-09.wav");
                currentCollectionNumber = Integer.parseInt(collectionNumberRarityField.getText());
                currentCardCollection = getColFromNo(currentCollectionNumber);
                orderCollectionByRarity();
                parentCardLayoutPanel.removeAll();
                parentCardLayoutPanel.add(confirmOrderRarity);
                parentCardLayoutPanel.repaint();
                parentCardLayoutPanel.revalidate();
            }
        });
    }

    /*source for panel change block of code
    parentCardLayoutPanel.removeAll();
                parentCardLayoutPanel.add(JPanel);
                parentCardLayoutPanel.repaint();
                parentCardLayoutPanel.revalidate();
    source: https://www.youtube.com/watch?v=xOCVOBDelFk&ab_channel=ScottCouprie
    */
    //MODIFIES: this
    //EFFECTS: launches corresponding panel with sound
    public void handleOrderCollectionByCategoryClick() {
        orderACollectionByButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playSound("./GachaGameEmulator/sounds/button-30.wav");
                parentCardLayoutPanel.removeAll();
                parentCardLayoutPanel.add(orderByCategoryPanel);
                parentCardLayoutPanel.repaint();
                parentCardLayoutPanel.revalidate();
            }
        });
    }

    /*source for panel change block of code
    parentCardLayoutPanel.removeAll();
                parentCardLayoutPanel.add(JPanel);
                parentCardLayoutPanel.repaint();
                parentCardLayoutPanel.revalidate();
    source: https://www.youtube.com/watch?v=xOCVOBDelFk&ab_channel=ScottCouprie
    */
    //MODIFIES: this
    //EFFECTS: launches corresponding panel with sound and orders by category corresponding with user input
    public void handleConfirmOrderByCategoryClick() {
        confirmOrderCategoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playSound("./GachaGameEmulator/sounds/button-09.wav");
                currentCollectionNumber = Integer.parseInt(collectionToBeOrderedByCategoryField.getText());
                currentCardCollection = getColFromNo(currentCollectionNumber);
                String firstCat = orderFirstCatField.getText();
                String secondCat = orderSecondCatField.getText();
                String thirdCat = orderThirdCatField.getText();
                orderCollectionByCategory(firstCat, secondCat, thirdCat);
                parentCardLayoutPanel.removeAll();
                parentCardLayoutPanel.add(confirmOrderCategory);
                parentCardLayoutPanel.repaint();
                parentCardLayoutPanel.revalidate();
            }
        });
    }

    /*source for panel change block of code
    parentCardLayoutPanel.removeAll();
                parentCardLayoutPanel.add(JPanel);
                parentCardLayoutPanel.repaint();
                parentCardLayoutPanel.revalidate();
    source: https://www.youtube.com/watch?v=xOCVOBDelFk&ab_channel=ScottCouprie
    */
    //MODIFIES: this
    //EFFECTS: views collection and plays sound on click
    public void handleViewConfirmClick() {
        confirmViewSelect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playSound("./GachaGameEmulator/sounds/button-09.wav");
                currentCollectionNumber = Integer.parseInt(viewChoice.getText());
                currentCardCollection = getColFromNo(currentCollectionNumber);
                viewCollection();
            }
        });
    }


    //MODIFIES: this
    //EFFECTS: summons for units with respect to user preferences
    public void handleSummonReqClick() {
        summonStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                summonCardImagePanel.removeAll();
                summonCardImagePanel.repaint();
                summonCardImagePanel.revalidate();
                playSound("./GachaGameEmulator/sounds/button-09.wav");
                currentCollectionNumber = Integer.parseInt(collectionSetSummon.getText());
                currentCardCollection = getColFromNo(currentCollectionNumber);
                narutoSet = Integer.parseInt(narutoSummon.getText());
                marvelSet = Integer.parseInt(marvelSummon.getText());
                dragonBallSet = Integer.parseInt(dragonBallSummon.getText());
                summonMain(narutoSet, marvelSet, dragonBallSet);
            }
        });
    }

    /*source for panel change block of code
    parentCardLayoutPanel.removeAll();
                parentCardLayoutPanel.add(JPanel);
                parentCardLayoutPanel.repaint();
                parentCardLayoutPanel.revalidate();
    source: https://www.youtube.com/watch?v=xOCVOBDelFk&ab_channel=ScottCouprie
    */
    //MODIFIES: this
    //EFFECTS: launches corresponding panel with sound
    public void handleSummonClick() {
        summonTabButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playSound("./GachaGameEmulator/sounds/button-30.wav");
                parentCardLayoutPanel.removeAll();
                parentCardLayoutPanel.add(summonPanel);
                parentCardLayoutPanel.repaint();
                parentCardLayoutPanel.revalidate();
            }
        });
    }

    /*source for panel change block of code
    parentCardLayoutPanel.removeAll();
                parentCardLayoutPanel.add(JPanel);
                parentCardLayoutPanel.repaint();
                parentCardLayoutPanel.revalidate();
    source: https://www.youtube.com/watch?v=xOCVOBDelFk&ab_channel=ScottCouprie
    */
    //MODIFIES: this
    //EFFECTS: launches corresponding panel with sound and saves game
    public void handleSaveClick() {
        saveGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playSound("./GachaGameEmulator/sounds/button-30.wav");
                parentCardLayoutPanel.removeAll();
                parentCardLayoutPanel.add(savePanel);
                parentCardLayoutPanel.repaint();
                parentCardLayoutPanel.revalidate();
                savePrompt(false);
            }
        });
    }

    /*source for panel change block of code
    parentCardLayoutPanel.removeAll();
                parentCardLayoutPanel.add(JPanel);
                parentCardLayoutPanel.repaint();
                parentCardLayoutPanel.revalidate();
    source: https://www.youtube.com/watch?v=xOCVOBDelFk&ab_channel=ScottCouprie
    */
    //MODIFIES: this
    //EFFECTS: launches corresponding panel with sound and loads game
    public void handleLoadClick() {
        loadGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playSound("./GachaGameEmulator/sounds/button-30.wav");
                parentCardLayoutPanel.removeAll();
                parentCardLayoutPanel.add(loadPanel);
                parentCardLayoutPanel.repaint();
                parentCardLayoutPanel.revalidate();
                try {
                    loadPrompt();
                } catch (FileNotFoundException exception) {
                    System.out.println("File Not Found");
                }
            }
        });
    }

    /*source for panel change block of code
    parentCardLayoutPanel.removeAll();
                parentCardLayoutPanel.add(JPanel);
                parentCardLayoutPanel.repaint();
                parentCardLayoutPanel.revalidate();
    source: https://www.youtube.com/watch?v=xOCVOBDelFk&ab_channel=ScottCouprie
    */
    //MODIFIES: this
    //EFFECTS: launches corresponding panel with sound
    public void handleViewCollectionsClick() {
        viewCollectionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playSound("./GachaGameEmulator/sounds/button-30.wav");
                parentCardLayoutPanel.removeAll();
                parentCardLayoutPanel.add(viewingPanel);
                parentCardLayoutPanel.repaint();
                parentCardLayoutPanel.revalidate();
            }
        });
    }

    /*source for panel change block of code
    parentCardLayoutPanel.removeAll();
                parentCardLayoutPanel.add(JPanel);
                parentCardLayoutPanel.repaint();
                parentCardLayoutPanel.revalidate();
    source: https://www.youtube.com/watch?v=xOCVOBDelFk&ab_channel=ScottCouprie
    */
    //MODIFIES: this
    //EFFECTS: launches corresponding panel with sound
    public void handleOrderCollectionByRarityClick() {
        orderByRarityButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playSound("./GachaGameEmulator/sounds/button-30.wav");
                parentCardLayoutPanel.removeAll();
                parentCardLayoutPanel.add(orderRarityPanel);
                parentCardLayoutPanel.repaint();
                parentCardLayoutPanel.revalidate();
            }
        });
    }

    /*source for panel change block of code
    parentCardLayoutPanel.removeAll();
                parentCardLayoutPanel.add(JPanel);
                parentCardLayoutPanel.repaint();
                parentCardLayoutPanel.revalidate();
    source: https://www.youtube.com/watch?v=xOCVOBDelFk&ab_channel=ScottCouprie
    */
    //MODIFIES: this
    //EFFECTS: launches corresponding panel with sound
    public void handleQuitButtonClick() {
        quitGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playSound("./GachaGameEmulator/sounds/button-30.wav");
                parentCardLayoutPanel.removeAll();
                parentCardLayoutPanel.add(quitPanel);
                parentCardLayoutPanel.repaint();
                parentCardLayoutPanel.revalidate();
            }
        });
    }

    /*source for panel change block of code
    parentCardLayoutPanel.removeAll();
                parentCardLayoutPanel.add(JPanel);
                parentCardLayoutPanel.repaint();
                parentCardLayoutPanel.revalidate();
    source: https://www.youtube.com/watch?v=xOCVOBDelFk&ab_channel=ScottCouprie
    */
    //MODIFIES: this
    //EFFECTS: just saves game then exits
    public void handleYesButton() {
        yesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parentCardLayoutPanel.removeAll();
                parentCardLayoutPanel.add(quitPanel);
                parentCardLayoutPanel.repaint();
                parentCardLayoutPanel.revalidate();
                savePrompt(true);
            }
        });
    }

    /*source for panel change block of code
    parentCardLayoutPanel.removeAll();
                parentCardLayoutPanel.add(JPanel);
                parentCardLayoutPanel.repaint();
                parentCardLayoutPanel.revalidate();
    source: https://www.youtube.com/watch?v=xOCVOBDelFk&ab_channel=ScottCouprie
    */
    //MODIFIES: this
    //EFFECTS: just exits
    public void handleNoButton() {
        noButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parentCardLayoutPanel.removeAll();
                parentCardLayoutPanel.add(quitPanel);
                parentCardLayoutPanel.repaint();
                parentCardLayoutPanel.revalidate();
                System.exit(0);
            }
        });
    }


    //MODIFIES: this
    //EFFECTS: saves game, quits if quit==true else stays
    public void savePrompt(boolean quit) {

        myCardCollections.getCardCollections().clear();
        myCardCollections.getCardCollections().add(cardCollection1);
        myCardCollections.getCardCollections().add(cardCollection2);
        myCardCollections.getCardCollections().add(cardCollection3);

        try {
            jsonWriter.open();
            jsonWriter.write(myCardCollections);
            jsonWriter.close();
            System.out.println("Saved " + myCardCollections.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }

        if (quit) {
            System.out.println("Hope you had fun!");
            System.exit(0);
        }
    }

    //MODIFIES: this
    //EFFECTS: prompts option to load previous save file, and loads it according to user response
    public void loadPrompt() throws FileNotFoundException {

        try {
            myCardCollections = jsonReader.read();
            System.out.println("Loaded " + myCardCollections.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
//              System.out.println("Unable to read from file: " + JSON_STORE);
            throw new FileNotFoundException();
        }

        cardCollection1 = myCardCollections.getCardCollections().get(0);
        cardCollection2 = myCardCollections.getCardCollections().get(1);
        cardCollection3 = myCardCollections.getCardCollections().get(2);
    }


    //MODIFIES: this
    //EFFECTS: summon for characters according to user preference of which categories of characters they wish to summon
    // for. Once done summoning, calls function responsible for functionality related to the actual collections.
    public void summonMain(int naruto, int marvel, int dragonBall) {
        summonResultsModel.removeAllElements();
        summoner = new Summoning(intToBoolean(naruto), intToBoolean(marvel), intToBoolean(dragonBall));
        summonResults = summoner.summon();
        for (Card card : summonResults) {
            if (card.getRarity().equals("LR")) {
                playSound("./GachaGameEmulator/sounds/Kids Saying Yay [Sound Effect] (online-audio-converter.com).wav");
            }
            summonResultsModel.addElement(cardToText(card));
            currentCardCollection.insertCards(card);
        }
    }

    //MODIFIES: this
    //EFFECTS: Orders current collection by rarity
    public void orderCollectionByRarity() {
        OrderGivenCollection orderGivenCollection;
        if (currentCollectionNumber == 1) {
            orderGivenCollection = new OrderGivenCollection(cardCollection1);
            cardCollection1 = orderGivenCollection.getCardCollectionOrderedByRarity();
            currentCardCollection = cardCollection1;
        } else if (currentCollectionNumber == 2) {
            orderGivenCollection = new OrderGivenCollection(cardCollection2);
            cardCollection2 = orderGivenCollection.getCardCollectionOrderedByRarity();
            currentCardCollection = cardCollection2;
        } else {
            orderGivenCollection = new OrderGivenCollection(cardCollection3);
            cardCollection3 = orderGivenCollection.getCardCollectionOrderedByRarity();
            currentCardCollection = cardCollection3;
        }
    }

    //MODIFIES: this
    //EFFECTS: Orders current collection by category, as decided by the user
    public void orderCollectionByCategory(String first, String second, String third) {
        OrderGivenCollection orderGivenCollection;
        if (currentCollectionNumber == 1) {
            orderGivenCollection = new OrderGivenCollection(cardCollection1);
            cardCollection1 = orderGivenCollection.getCardCollectionOrderedByCategory(first, second, third);
            currentCardCollection = cardCollection1;
        } else if (currentCollectionNumber == 2) {
            orderGivenCollection = new OrderGivenCollection(cardCollection2);
            cardCollection2 = orderGivenCollection.getCardCollectionOrderedByCategory(first, second, third);
            currentCardCollection = cardCollection2;
        } else {
            orderGivenCollection = new OrderGivenCollection(cardCollection3);
            cardCollection3 = orderGivenCollection.getCardCollectionOrderedByCategory(first, second, third);
            currentCardCollection = cardCollection3;
        }
    }

    //EFFECTS: displays collection desired by user to be displayed. Calls function to find a card in current collection
    public void viewCollection() {
        viewCollectionListModel.removeAllElements();
        List<Card> cardList = currentCardCollection.getCards();
        for (Card card : cardList) {
            viewCollectionListModel.addElement(cardToText(card));
        }
        scrollViewCollection.setViewportView(viewCollectionList);
    }

    //EFFECTS: Displays number of copies of input card found within current card collection if card related with input
    //card name is found, else indicates its absence. Calls function for removing duplicates of a card in case user
    // decides on removing duplicates of the cards he just found, or any other card.
    public String findCard(String name) {
        boolean found = false;
        int count = 0;
        for (Card card : currentCardCollection.getCards()) {
            if (card.getName().equals(name)) {
                count++;
                found = true;
            }
        }
        if (count > 0) {
//            System.out.println("Found " + count + " of " + name + "!");
            return "Found " + count + " of " + name + "!";
        }

        return "Couldn't find " + name;

    }

    //MODIFIES: this
    //EFFECTS: Removes duplicates of input card from collection. Allows user to then view the collection.
    public String removeDuplicatesOfACardFromCollection(String removeChoice) {
        boolean result = currentCardCollection.removeDuplicatesOfOneCard(currentCardCollection.getCard(removeChoice));
        if (result) {
            return "Removed duplicates of " + removeChoice + " successfully!";
        }
        return "This card does not exist in this collection";
    }

    //EFFECTS: inserts cards in currentCardCollection into the map variant, makes findCard() function much faster,
    //as searching for objects in a map follows O(1), while in a list is O(n)
    public void insertCardsToMap() {
        currentCollectionAsMap = new HashMap<>();
        for (Card card : currentCardCollection.getCards()) {
            currentCollectionAsMap.put(card.getName(), card);
        }
    }

    //EFFECTS: With the use of HashMap, quickly find card in collection, return success message and display image of
    //card if found, else fail message.
    public String findCardInMap(String name) {
        if (currentCollectionAsMap.containsKey(name)) {
            Color panelColor = new Color(63, 180, 179);
            JPanel checkCardImageReturnPanel = createPanelWithCardImage(name);
            checkCardImageReturnPanel.setBackground(panelColor);
            System.out.println("Now showing " + name);
            checkCardImagePanel.removeAll();
            checkCardImagePanel.add(checkCardImageReturnPanel);
            checkCardImagePanel.repaint();
            checkCardImagePanel.revalidate();
            return name + " was found!";
        }
        checkCardImagePanel.removeAll();
        checkCardImagePanel.repaint();
        checkCardImagePanel.revalidate();
        return name + " not found :(";
    }


    //source for very, very small part of this idea:
    //https://stackoverflow.com/questions/299495/how-to-add-an-image-to-a-jpanel
    //EFFECTS: returns panel with image of card corresponding to input name
    public JPanel createPanelWithCardImage(String name) {
        BufferedImage cardImage = null;
        try {
            cardImage = ImageIO.read(new File("./GachaGameEmulator/images/" + name + ".png"));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        JLabel cardLabel = new JLabel(new ImageIcon(cardImage));
        JPanel cardPanel = new JPanel();

        cardPanel.add(cardLabel);
        return cardPanel;
    }

    //source: http://suavesnippets.blogspot.com/2011/06/add-sound-on-jbutton-click-in-java.html
    //Posted by Appy at 15:26
    //THIS CODE IS NOT MY OWN, I FOUND IT AT THE SOURCE ABOVE
    //The reason I 'm using this code and not tweaking it for my own project is because there's no need for tweaking,
    //this function really only invokes functions from other classes, it doesn't actually involve any real logic that
    //I'm copying
    //EFFECTS: plays sound from input file
    public void playSound(String soundName) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }

    //EFFECTS: returns card representation as text
    public String cardToText(Card card) {
        return card.getRarity() + " " + card.getName() + " " + card.getCategory() + "\n";
    }

    //EFFECTS: returns card collection corresponding to input collection number
    public CardCollection getColFromNo(int number) {
        if (number == 1) {
            return cardCollection1;
        } else if (number == 2) {
            return cardCollection2;
        }
        return cardCollection3;
    }

    //EFFECTS: converts user input of 0 or 1 (effectively 0 or any other integer, but for consistency's sake, 0 or 1) to
    //boolean and returns the boolean
    public boolean intToBoolean(int input) {
        return input != 0;
    }


}
