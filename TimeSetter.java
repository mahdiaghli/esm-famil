import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class TimeSetter {
    int minutes = 0, seconds = 5;
    public void timer(boolean nameC, boolean lastnameC, boolean flowerC,
                      boolean foodC, boolean fruitC, boolean thingsC, boolean carC, boolean countryC,
                      boolean cityC, boolean clothesC, boolean animalC,
                      GamePage gamePage, int serverOrClient,
                      ArrayList<String> subjects, int subjectsCount, String rounds, int clientSumSum,
                      int clientSum, String[] letters, int[] valueLetters, int time, int turn) {
        OtherLetter otherLetter = new OtherLetter();
        Stage timeStage = new Stage();
        Text minText = new Text("minutes: " +(((minutes/10) == 0) ? "0" : "") + minutes);
        Text secText = new Text("seconds: " + (((seconds/10) == 0) ? "0" : "") + seconds);


        InputStream inputStream;
        try {
            inputStream= new FileInputStream("photo.jpg");
        }catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        Image image = new Image(inputStream);
        timeStage.getIcons().add(image);


        Button minPlus = new Button("add minute");
        Button minMinus = new Button("decrease minute");
        Button secPlus = new Button("add second");
        Button secMinus = new Button("decrease second");
        Button submit = new Button("submit");
        minText.setStyle("-fx-background-color:yellow;-fx-text-fill:white;-fx-front:normal bold 20px 'serif'");
        secText.setStyle("-fx-background-color:yellow;-fx-text-fill:white;-fx-front:normal bold 20px 'serif'");
        minMinus.setStyle("-fx-background-color:blue;-fx-text-fill:white;-fx-front:normal bold 20px 'serif'");
        minPlus.setStyle("-fx-background-color:blue;-fx-text-fill:white;-fx-front:normal bold 20px 'serif'");
        secMinus.setStyle("-fx-background-color:blue;-fx-text-fill:white;-fx-front:normal bold 20px 'serif'");
        secPlus.setStyle("-fx-background-color:blue;-fx-text-fill:white;-fx-front:normal bold 20px 'serif'");
        VBox vBox = new VBox(30);
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(minText, minPlus, minMinus, secText, secPlus, secMinus, submit);

        minPlus.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            minutes++;
            minText.setText("minutes: " +(((minutes/10) == 0) ? "0" : "") + minutes);
        });
        secPlus.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            seconds++;
            secText.setText("seconds: " + (((seconds/10) == 0) ? "0" : "") + seconds);
        });
        minMinus.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if(minutes > 0){
                minutes--;
                minText.setText("minutes: " +(((minutes/10) == 0) ? "0" : "") + minutes);
            }
        });
        secMinus.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if(seconds > 0){
                seconds--;
                secText.setText("seconds: " + (((seconds/10) == 0) ? "0" : "") + seconds);
            }
        });
        submit.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            try {
                otherLetter.otherLetter(nameC, lastnameC, flowerC, foodC, fruitC, thingsC, carC, countryC,
                        cityC, clothesC, animalC, gamePage, serverOrClient, subjects, subjectsCount,
                        rounds, 0, 0, 0, 0, letters,valueLetters,
                        minutes,seconds, time,turn);
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
            timeStage.close();

        });
        vBox.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        Scene timeScene = new Scene(vBox, 400, 400);
        timeStage.setTitle("time setter");
        timeStage.setScene(timeScene);
        timeStage.show();
    }
}
