import javafx.scene.image.Image;

public class Symbol implements ISymbol {

    Image image; //Private variable image
    private int value; //Private variable value

    @Override
    public Image getImage() { //Overridden getter method for image
        return image;
    }

    @Override
    public int getValue() { //Overridden getter method for value
        return value;
    }

    @Override
    public void setImage(Image image) { //Overridden setter method for image
        this.image = image;
    }

    @Override
    public void setValue(int i) { //Overridden setter method for value
        this.value = i;
    }
}
