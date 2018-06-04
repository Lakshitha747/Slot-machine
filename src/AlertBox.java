import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertBox {

    public static void display(String title, String message) {

        Stage stage = new Stage(); //Stage for alert box
        GridPane layout = new GridPane(); //Layout for alert box
        Scene scene = new Scene(layout); //Scene for alert box

        stage.setTitle(title);
        stage.setMinWidth(500);
        stage.setMinHeight(200);
        stage.initModality(Modality.APPLICATION_MODAL);

        Label msgLbl = new Label(message); //Message label

        Button closeBtn = new Button("Close"); //Close button

        layout.add(msgLbl, 5, 4);
        layout.add(closeBtn, 5, 8);
        layout.setAlignment(Pos.CENTER); //Setting element

        closeBtn.setOnAction(e -> stage.close());

        stage.setScene(scene);
        stage.showAndWait();
    }
}
