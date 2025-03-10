import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class TimePage {

    public void timeOrder(int ServerOrClient) {
        InformationPage informationPage = new InformationPage();
        Stage timeStage = new Stage();
        FlowPane flowPane = new FlowPane();
        Button endTime = new Button("end with Time");
        Button endOrder = new Button("end with your order");
        flowPane.getChildren().addAll(endOrder,endTime);
        flowPane.setAlignment(Pos.CENTER);
        flowPane.setPadding(new Insets(20,20,20,20));

        InputStream inputStream;
        try {
            inputStream= new FileInputStream("photo.jpg");
        }catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Image image = new Image(inputStream);
        timeStage.getIcons().add(image);

        endTime.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    informationPage.firstLetter(0,ServerOrClient/*,timeStage*/);
                    timeStage.close();
                }
            });

        endOrder.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    informationPage.firstLetter(1,ServerOrClient/*,timeStage*/);
                    timeStage.close();
                }
            });

        endOrder.setStyle("-fx-background-color:blue;-fx-text-fill:white;-fx-front:normal bold 20px 'serif'");
        endTime.setStyle("-fx-background-color:purple;-fx-text-fill:white;-fx-front:normal bold 20px 'serif'");
        flowPane.setBackground(new Background(new BackgroundFill(Color.DARKORANGE, CornerRadii.EMPTY, Insets.EMPTY)));
        Scene scene = new Scene(flowPane, 500, 500);
        timeStage.setTitle("Time");
        timeStage.setScene(scene);
        timeStage.show();
    }
}
