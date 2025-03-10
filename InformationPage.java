import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class InformationPage {
    public void firstLetter(int time,int serverOrClient) {
        TurnPage turnPage = new TurnPage();
        TimeSetter timeSetter = new TimeSetter();
        JabberClient jabberClient = new JabberClient();
        JabberServer jabberServer = new JabberServer();
        InformationPage informationPage = new InformationPage();
        GamePage gamePage = new GamePage();
        OtherLetter otherLetter = new OtherLetter();
        Stage firstLetterStage = new Stage();
        Button submit = new Button("submit");
        TextField rounds = new TextField();
        rounds.setPromptText("rounds");
        Text roundsText = new Text("enter The rounds of game");
        roundsText.setStyle("-fx-text-fill: blue ; -fx-font-size: 20px;");


        InputStream inputStream;
        try {
            inputStream= new FileInputStream("photo.jpg");
        }catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Image image = new Image(inputStream);
        firstLetterStage.getIcons().add(image);


        Label label = new Label("choose at least 5 out of these 11 subjects");
        label.setStyle("-fx-text-fill: yellow; -fx-font-size: 20px;");
        VBox firstLetterVBox = new VBox();

        TextField Time = new TextField();
        Time.setPromptText("Time");
        Text timeText = new Text("enter The Time");
        timeText.setStyle("-fx-text-fill: green; -fx-font-size: 20px;");
        firstLetterVBox.setBackground(new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        firstLetterVBox.setAlignment(Pos.CENTER);
        firstLetterVBox.setSpacing(10);
        firstLetterVBox.getChildren().addAll(roundsText,rounds,label);

        CheckBox name = new CheckBox("     name");
        CheckBox lastname = new CheckBox("lastname");
        CheckBox flower = new CheckBox("    flower");
        CheckBox food = new CheckBox("      food");
        CheckBox fruit = new CheckBox("      fruit");
        CheckBox things = new CheckBox("   things");
        CheckBox car = new CheckBox("         car");
        CheckBox country = new CheckBox("   country");
        CheckBox city = new CheckBox("         city");
        CheckBox clothes = new CheckBox("    clothes");
        CheckBox animal = new CheckBox("      animal");
        int[] i = {0};
        ArrayList<String> subjects = null;
        int subjectsCount = 0;

        submitEventHandler(submit, name, lastname, flower, food, fruit, things, car, country, city, clothes, animal,
                           i,gamePage,serverOrClient,subjects, rounds, firstLetterStage,time,turnPage);

        firstLetterVBox.getChildren().addAll(name, lastname, flower, food, fruit, things, car, country, city, clothes, animal);
        firstLetterVBox.getChildren().add(submit);
        firstLetterVBox.setPadding(new Insets(20, 200, 20, 200));
        Scene boardScene = new Scene(firstLetterVBox, 1000, 1000);
        firstLetterStage.setScene(boardScene);
        firstLetterStage.setTitle("information Page");
        firstLetterStage.show();
    }

    private void submitEventHandler(Button submit, CheckBox name, CheckBox lastname, CheckBox flower,
                                    CheckBox food, CheckBox fruit, CheckBox things, CheckBox car,
                                    CheckBox country, CheckBox city, CheckBox clothes, CheckBox animal,
                                    int[] i, GamePage gamePage, int serverOrClient,
                                    ArrayList<String> subjects,
                                    TextField rounds,
                                    Stage firstLetterStage, int time, TurnPage turnPage) {

        submit.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (serverOrClient == 1 || serverOrClient == 2) {

                    i[0] = checkedChecker(name.isSelected(), lastname.isSelected(), flower.isSelected(),
                            food.isSelected(), fruit.isSelected(), things.isSelected(), car.isSelected(),
                            country.isSelected(), city.isSelected(), clothes.isSelected(), animal.isSelected());

                    String[] letters = new String[]{"ا", "ب", "پ", "ت", "ث", "ج", "چ", "ح", "خ", "د", "ذ", "ر", "ز",
                                                    "ژ", "س", "ش", "ص", "ض", "ط", "ظ", "ع", "غ", "ف", "ق", "ک", "گ",
                                                    "ل", "م", "ن", "و", "ه", "ی"};
                    int[] valueLetters = new int[32];
                    for (int i = 0; i < 32; i++) {
                        valueLetters[i] = 1;
                    }

                    turnPage.turn(name.isSelected(), lastname.isSelected(), flower.isSelected(),
                                    food.isSelected(), fruit.isSelected(), things.isSelected(), car.isSelected(),
                                    country.isSelected(), city.isSelected(), clothes.isSelected(), animal.isSelected(),
                                    gamePage, serverOrClient, subjects, i[0],
                                    rounds.getText(), 0, 0, 0, 0,
                                    letters, valueLetters, 0, 0, time);
                }
            }
        });

        submit.addEventFilter(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                i[0] = checkedChecker(name.isSelected(), lastname.isSelected(), flower.isSelected(),
                        food.isSelected(), fruit.isSelected(), things.isSelected(), car.isSelected(),
                        country.isSelected(), city.isSelected(), clothes.isSelected(), animal.isSelected());
                if (i[0] < 5) {
                    event.consume();
                    System.out.println("choose more subjects");
                }
                if (i[0] >= 5) {
                    System.out.println("nice");
                    System.out.println("you chose " + i[0] + " subjects");
                    firstLetterStage.close();
                }
            }
        });

    }

    public int checkedChecker(boolean nameC, boolean lastnameC, boolean flowerC,
                              boolean foodC, boolean fruitC, boolean thingsC, boolean carC, boolean countryC,
                              boolean cityC, boolean clothesC, boolean animalC) {
        int count = 0;
        if (nameC)
            count++;
        if (lastnameC)
            count++;
        if (flowerC)
            count++;
        if (thingsC)
            count++;
        if (carC)
            count++;
        if (cityC)
            count++;
        if (countryC)
            count++;
        if (animalC)
            count++;
        if (clothesC)
            count++;
        if (foodC)
            count++;
        if (fruitC)
            count++;
        return count;
    }
}