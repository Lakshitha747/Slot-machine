import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.control.*;

import java.lang.Thread;
import java.util.ArrayList;

public class SlotMachine extends Application {

    static int value = 10; //Initial value
    static int betValue, won = 0; //Betting value and won variable
    static int maxBet = 3; //Maximum betting value

    ImageView img1, img2, img3; //ImageView

    Label creditLbl, betAreaLbl, payoutLbl; //Labels for credit area, betting value and payout label

    Thread thread1, thread2, thread3; //Threads

    TheThread theThread1, theThread2, theThread3; //Objects from TheThread class

    static int threadValue1, threadValue2, threadValue3; //Variables to get values from the reels

    static Reel reel1 = new Reel(); //Reel object
    static Reel reel2 = new Reel(); //Reel object
    static Reel reel3 = new Reel(); //Reel object

    @Override
    public void start(Stage primaryStage) throws Exception { //Overridden start method from javafx

        primaryStage.setTitle("Slot Machine"); //Title
        GridPane layout = new GridPane(); //Default layout
        Scene scene = new Scene(layout, 750, 500); //Default scene

        layout.setHgap(20); //Setting horizontal gap
        layout.setVgap(20); //Setting vertical gap
        layout.setAlignment(Pos.CENTER); //Positioning to the center

        Button addBtn = new Button("Add Coin"); //Add button
        Button betOneBtn = new Button("Bet One"); //Bet one button
        Button betMaxBtn = new Button("Bet Maximum"); //Bet Maximum button
        Button resetBtn = new Button("Reset"); //Reset button
        Button spinBtn = new Button("Spin"); //Spin button

        img1 = new ImageView(reel1.getImages().get(0).getImage()); //Default image for reel1
        img1.setFitWidth(100);
        img1.setFitHeight(100);

        img2 = new ImageView(reel2.getImages().get(0).getImage()); //Default image for reel2
        img2.setFitHeight(100);
        img2.setFitWidth(100);

        img3 = new ImageView(reel3.getImages().get(0).getImage()); //Default image for reel3
        img3.setFitHeight(100);
        img3.setFitWidth(100);

        creditLbl = new Label("Credit value: " + "" + value); //Credit label
        betAreaLbl = new Label("Betting value: " + "" + betValue); //Bet area label

        //Adding elements
        layout.add(addBtn, 3, 5);
        layout.add(betOneBtn, 4, 5);
        layout.add(betMaxBtn, 5, 5);
        layout.add(resetBtn, 3, 6);
        layout.add(spinBtn, 4, 6);

        layout.add(creditLbl, 3, 4);
        layout.add(betAreaLbl, 5, 4);

        layout.add(img1, 2, 2);
        layout.add(img2, 4, 2);
        layout.add(img3, 6, 2);

        //Adding button functionality
        addBtn.setOnAction(e -> {
            if (value > 0) {
                value++;
                creditLbl.setText("Credit value: " + "" + value);
                betOneBtn.setDisable(false);
            } else if (value > 2) {
                value++;
                creditLbl.setText("Credit value: " + "" + value);
                betOneBtn.setDisable(false);
            } else {
                value++;
                creditLbl.setText("Credit value: " + "" + value);
                betOneBtn.setDisable(false);
            }
        });

        betOneBtn.setOnAction(e -> {
            if (value > 0) {
                betValue++;
                value--;
                betAreaLbl.setText("Betting value: " + "" + betValue);
                creditLbl.setText("Credit value: " + "" + value);
            } else {
                betOneBtn.setDisable(true);
            }
        });

        betMaxBtn.setOnAction(e -> {
            if (value < 3) {
                betMaxBtn.setDisable(true);
            } else {
                betValue += maxBet;
                value -= maxBet;
                betAreaLbl.setText("Betting value:" + "" + betValue);
                creditLbl.setText("Credit value: " + "" + value);
                betMaxBtn.setDisable(true);
            }
        });

        resetBtn.setOnAction(e -> {
            value += betValue;
            betValue = 0;
            betAreaLbl.setText("Betting value: " + "" + betValue);
            creditLbl.setText("Credit value:" + "" + value);
            betOneBtn.setDisable(false);
            betMaxBtn.setDisable(false);
        });

        spinBtn.setOnAction(e -> {
            if (betValue == 0) {
                AlertBox.display("Alert", "Please bet");
            } else {
                spins();
            }
        });

        img1.setOnMouseClicked(e -> stopSpin(reel1)); //Stopping reel1
        img2.setOnMouseClicked(e -> stopSpin(reel2)); //Stopping reel2
        img3.setOnMouseClicked(e -> stopSpin(reel3)); //Stopping reel3

        reel1.spin();
        reel2.spin();
        reel3.spin();

        primaryStage.setScene(scene); //Setting scene
        primaryStage.show(); //Showing primary window
    }

    public void spins() {
        reel1.setSpinning(true); //Spinning reel1
        theThread1 = new TheThread(img1, reel1);
        thread1 = new Thread(theThread1);
        thread1.start();

        reel2.setSpinning(true); //Spinning reel2
        theThread2 = new TheThread(img2, reel2);
        thread2 = new Thread(theThread2);
        thread2.start();

        reel3.setSpinning(true); //Spinning reel3
        theThread3 = new TheThread(img3, reel3);
        thread3 = new Thread(theThread3);
        thread3.start();
    }

    public void stopSpin(Reel reel) {
        reel.setSpinning(false);

        //After stop spinning getting the reel values
        if (!reel1.getSpinning() & !reel2.getSpinning() & !reel3.getSpinning()) {
            threadValue1 = reel1.getImages().get(theThread1.i).getValue();
            threadValue2 = reel2.getImages().get(theThread2.i).getValue();
            threadValue3 = reel3.getImages().get(theThread3.i).getValue();

            won();
        }
    }

    private void won() {
        if (threadValue1 == threadValue2 && threadValue1 == threadValue3) { //When reel1 and reel2 are equal
            won += betValue * threadValue1;
            value += won;
            betValue = 0;

            creditLbl.setText("Credit value: " + "" + value);
            betAreaLbl.setText("Betting value: " + "" + betValue);
            AlertBox.display("Alert", "You won" + " " + won + " " + "credits");

        } else if (threadValue2 == threadValue3 && threadValue1 != threadValue2) { //When reel2 and reel3 are equal
            won = betValue * threadValue2;
            value += won;
            betValue = 0;

            creditLbl.setText("Credit value: " + "" + value);
            betAreaLbl.setText("Betting value: " + "" + betValue);
            AlertBox.display("Alert", "You won" + " " + won + " " + "credits");

        } else if (threadValue3 == threadValue1 && threadValue1 != threadValue2) { //When reel1 and reel3 are equal
            won = betValue * threadValue3;
            value += won;
            betValue = 0;

            creditLbl.setText("Credit value: " + "" + value);
            betAreaLbl.setText("Betting value: " + "" + betValue);
            AlertBox.display("Alert", "You won" + " " + won + " " + "credits");

        } else if (threadValue1 != threadValue2 && threadValue2 != threadValue3 && threadValue1 != threadValue3) { //When no reels are equal
            betValue = 0;

            creditLbl.setText("Credit value: " + "" + value);
            betAreaLbl.setText("Betting value: " + "" + betValue);
            AlertBox.display("Alert", "You lost");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
