package poi.game.controllers;

import java.util.ArrayList;

public class ColorGameController {
    private ArrayList<String> texturesP1;
    private ArrayList<String> texturesP2;
    private int colorP1;
    private int colorP2;
    private int typeP1;
    private int typeP2;

    public ColorGameController() {
        texturesP1 = new ArrayList<>();
        texturesP2 = new ArrayList<>();
        colorP1 = ColorController.colorP1;
        colorP2 = ColorController.colorP2;
        typeP1 = ColorController.penguinTypeP1;
        typeP2 = ColorController.penguinTypeP2;
    }

    public ArrayList<String> getColorP1() {
        // Reset selection
        texturesP1.clear();

        if (typeP1 == 0) {
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
            // Purple selected
            else if (colorP1 == 3) {
                texturesP1.add("players/lilla-bak.png");
                texturesP1.add("players/lilla-skli.png");
                texturesP1.add("players/lilla-finish.png");
            }
        }
        if (typeP1 == 1) {
            // Black selected
            if (colorP1 == 0) {
                texturesP1.add("players/ny-bak-svart.png");
                texturesP1.add("players/ny-skli-svart.png");
                texturesP1.add("players/ny-finish-svart.png");
            }
            // Pink selected
            else if (colorP1 == 1) {
                texturesP1.add("players/ny-bak-rosa.png");
                texturesP1.add("players/ny-skli-rosa.png");
                texturesP1.add("players/ny-finish-rosa.png");
            }
            // Green selected
            else if (colorP1 == 2) {
                texturesP1.add("players/ny-bak-grønn.png");
                texturesP1.add("players/ny-skli-grønn.png");
                texturesP1.add("players/ny-finish-grønn.png");
            }
            //Purple selected
            else if (colorP1 == 3) {
                texturesP1.add("players/ny-bak-lilla.png");
                texturesP1.add("players/ny-skli-lilla.png");
                texturesP1.add("players/ny-finish-lilla.png");
            }
        }
        return texturesP1;
    }

    public ArrayList<String> getColorP2() {
        // Reset selection
        texturesP2.clear();

        if (typeP2 == 0) {
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
            // Purple selected
            else if (colorP2 == 3) {
                texturesP2.add("players/lilla-bak.png");
                texturesP2.add("players/lilla-skli.png");
                texturesP2.add("players/lilla-finish.png");
            }
        }
        if (typeP2 == 1) {
            // Black selected
            if (colorP2 == 0) {
                texturesP2.add("players/ny-bak-svart.png");
                texturesP2.add("players/ny-skli-svart.png");
                texturesP2.add("players/ny-finish-svart.png");
            }
            // Pink selected
            else if (colorP2 == 1) {
                texturesP2.add("players/ny-bak-rosa.png");
                texturesP2.add("players/ny-skli-rosa.png");
                texturesP2.add("players/ny-finish-rosa.png");
            }
            // Green selected
            else if (colorP2 == 2) {
                texturesP2.add("players/ny-bak-grønn.png");
                texturesP2.add("players/ny-skli-grønn.png");
                texturesP2.add("players/ny-finish-grønn.png");
            }
            // Purple selected
            else if (colorP2 == 3) {
                texturesP2.add("players/ny-bak-lilla.png");
                texturesP2.add("players/ny-skli-lilla.png");
                texturesP2.add("players/ny-finish-lilla.png");
            }
        }
        return texturesP2;
    }
}
