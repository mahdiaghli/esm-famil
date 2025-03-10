import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class TurnPage {

    public void turn(boolean nameC, boolean lastnameC, boolean flowerC,
                     boolean foodC, boolean fruitC, boolean thingsC, boolean carC, boolean countryC,
                     boolean cityC, boolean clothesC, boolean animalC,
                     GamePage gamePage, int serverOrClient,
                     ArrayList<String> subjects, int subjectsCount, String rounds, int clientSumSum,
                     int clientSum, int f, int serverSumSum, String[] letters, int[] valueLetters, int minutes,
                     int seconds, int time) {
        OtherLetter otherLetter = new OtherLetter();
        TimeSetter timeSetter = new TimeSetter();
        Stage serverStage = new Stage();
        HBox hBox = new HBox();
        Button alwaysYourTurn = new Button("always your turn");
        Button changeTurn = new Button("change turn");
        hBox.setSpacing(20);
        hBox.getChildren().addAll(alwaysYourTurn, changeTurn);
        hBox.setAlignment(Pos.CENTER);
        hBox.setPadding(new Insets(20, 20, 20, 20));

        InputStream inputStream;
        try {
            inputStream= new FileInputStream("photo.jpg");
        }catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Image image = new Image(inputStream);
        serverStage.getIcons().add(image);

        ArrayList<String> arrayList = null;

        alwaysYourTurn.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if (time == 1) {
                    try {
                        otherLetter.otherLetter(nameC, lastnameC, flowerC, foodC, fruitC, thingsC, carC,
                                countryC, cityC, clothesC, animalC,
                                gamePage, serverOrClient, subjects, subjectsCount,
                                rounds, 0, 0, 0, 0, letters,valueLetters,
                                0,0,time,1);
                        serverStage.close();
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

                if (time == 0) {
                    timeSetter.timer(nameC, lastnameC, flowerC, foodC, fruitC, thingsC, carC,
                            countryC, cityC, clothesC, animalC,
                            gamePage, serverOrClient, subjects, subjectsCount, rounds,
                            0, 0, letters, valueLetters, time, 1);
                    serverStage.close();
                }
            }
        });

        changeTurn.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if (time == 1) {
                    try {
                        otherLetter.otherLetter(nameC, lastnameC, flowerC, foodC, fruitC, thingsC, carC,
                                countryC, cityC, clothesC, animalC,
                                gamePage, serverOrClient, subjects, subjectsCount,
                                rounds, 0, 0, 0, 0, letters,valueLetters,
                                0,0,time,0);
                        serverStage.close();
                    } catch (IOException | InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                    if (time == 0) {
                        timeSetter.timer(nameC, lastnameC, flowerC, foodC, fruitC, thingsC, carC,
                                countryC, cityC, clothesC, animalC,
                                gamePage, serverOrClient, subjects, subjectsCount, rounds,
                                0, 0, letters, valueLetters, time, 0);
                        serverStage.close();
                    }
            }
        });

        alwaysYourTurn.setStyle("-fx-background-color:brown;-fx-text-fill:white;-fx-front:normal bold 20px 'serif'");
        changeTurn.setStyle("-fx-background-color:gold;-fx-text-fill:white;-fx-front:normal bold 20px 'serif'");
        hBox.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        Scene scene = new Scene(hBox, 500, 500);
        serverStage.setTitle("Turn Page");
        serverStage.setScene(scene);
        serverStage.show();
    }
}