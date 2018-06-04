import javafx.scene.image.ImageView;

import java.util.logging.Level;
import java.util.logging.Logger;

public class TheThread implements Runnable {


    ImageView imagevw; //ImageView
    Reel reel; //Reel
    volatile Symbol theSymbol; //Symbol
    int i = 0; //variable i
    static volatile int index = 0; //index

    public TheThread(ImageView imagevw, Reel reel) { //Constructor for TheThread class
        super();
        this.imagevw = imagevw;
        this.reel = reel;
    }

    @Override
    public void run() { //Overriden run method
        while (this.reel.getSpinning() == true) {
            reel.spin();
            for (Symbol s : this.reel.getImages()) {
                if (this.reel.getSpinning()) {
                    synchronized (TheThread.class) {
                        imagevw.setImage(reel.getImages().get(i).getImage());
                        theSymbol = s;
                    }
                } else {
                    break;
                }
            }
            index = theSymbol.getValue();
            try {
                java.lang.Thread.sleep(50);
            } catch (InterruptedException ex) {
                Logger.getLogger(Thread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}