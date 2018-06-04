import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Collections;

public class Reel extends Symbol {

    private ArrayList<Symbol> images = new ArrayList<Symbol>();

    private boolean spinning = false; //Boolean value to check spinning

    public Reel() { //Constructor for reel class
        Symbol bell = new Symbol();
        bell.setImage(new Image("bell.png"));
        bell.setValue(6);

        Symbol cherry = new Symbol();
        cherry.setImage(new Image("cherry.png"));
        cherry.setValue(2);

        Symbol lemon = new Symbol();
        lemon.setImage(new Image("lemon.png"));
        lemon.setValue(3);

        Symbol plum = new Symbol();
        plum.setImage(new Image("plum.png"));
        plum.setValue(4);

        Symbol redseven = new Symbol();
        redseven.setImage(new Image("redseven.png"));
        redseven.setValue(7);

        Symbol watermelon = new Symbol();
        watermelon.setImage(new Image("watermelon.png"));
        watermelon.setValue(5);

        images.add(bell);
        images.add(cherry);
        images.add(lemon);
        images.add(plum);
        images.add(redseven);
        images.add(watermelon);
    }

    public ArrayList<Symbol> getImages() {
        return images;
    }

    public boolean getSpinning() {
        return spinning;
    }

    public void setSpinning(boolean spinning) {
        this.spinning = spinning;
    }

    public void spin() {
        Collections.shuffle(getImages());
    }
}
