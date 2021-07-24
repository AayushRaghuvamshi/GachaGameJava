package ui;

import javax.swing.*;

// This is where the app is initialised, the game begins
public class Main {
    //EFFECTS: Initialises the app for running
//    public static void main(String[] args) {
//        try {
//            new GachaGameApp();
//        } catch (FileNotFoundException e) {
//            System.out.println("Unable to run application: file not found");
//            System.exit(0);
//        }
//    }

    //EFFECTS: Initializes and creates GUI for running
    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException,
            InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                GachaGameAppGui visual = new GachaGameAppGui();
                visual.setVisible(true);
            }
        });
    }

}
