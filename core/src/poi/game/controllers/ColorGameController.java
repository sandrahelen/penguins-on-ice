package poi.game.controllers;

import java.util.ArrayList;

public class ColorGameController {
    private ArrayList<String> texturesP1;
    private ArrayList<String> texturesP2;
    private int colorP1;
    private int colorP2;

    public ColorGameController() {
        texturesP1 = new ArrayList<>();
        texturesP2 = new ArrayList<>();
        colorP1 = ColorController.colorP1;
        colorP2 = ColorController.colorP2;
    }

    public ArrayList<String> getColorP1() {
        // Reset selection
        texturesP1.clear();

        // Black selected
        if (colorP1 == 0) {
            texturesP1.add("players/svart-bak.png");
            texturesP1.add("players/svart-skli.png");
            texturesP1.add("players/svart-finish.png");
        }
        // Pink selected
        else if (colorP1 == 1) {
            texturesP1.add("players/rosa-bak.png");
            texturesP1.add("players/rosa-skli.png");
            texturesP1.add("players/rosa-finish.png");
        }
        // Green selected
        else if (colorP1 == 2) {
            texturesP1.add("players/grønn-bak.png");
            texturesP1.add("players/grønn-skli.png");
            texturesP1.add("players/grønn-finish.png");
        }
        return texturesP1;
    }

    public ArrayList<String> getColorP2() {
        // Reset selection
        texturesP2.clear();

        // Black selected
        if (colorP2 == 0) {
            texturesP2.add("players/svart-bak.png");
            texturesP2.add("players/svart-skli.png");
            texturesP2.add("players/svart-finish.png");
        }
        // Pink selected
        else if (colorP2 == 1) {
            texturesP2.add("players/rosa-bak.png");
            texturesP2.add("players/rosa-skli.png");
            texturesP2.add("players/rosa-finish.png");
        }
        // Green selected
        else if (colorP2 == 2) {
            texturesP2.add("players/grønn-bak.png");
            texturesP2.add("players/grønn-skli.png");
            texturesP2.add("players/grønn-finish.png");
        }
        return texturesP2;
    }
}
