import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;

public class OtherLetter {
    public int otherLetter(boolean nameC, boolean lastnameC, boolean flowerC,
                           boolean foodC, boolean fruitC, boolean thingsC, boolean carC, boolean countryC,
                           boolean cityC, boolean clothesC, boolean animalC,
                           GamePage gamePage, int serverOrClient,
                           ArrayList<String> subjects, int subjectsCount, String rounds, int clientSumSum,
                           int clientSum, int f, int serverSumSum, String[] letters, int[] valueLetters,
                           int minutes, int seconds, int time, int turn) throws IOException, InterruptedException {

        ExitPage exitPage = new ExitPage();
        JabberServer jabberServer = new JabberServer();
        JabberClient jabberClient = new JabberClient();
        Stage otherLetterStage = new Stage();
        TextField otherLetter = new TextField();
        otherLetter.setPromptText("firstLetter");
        Button submit = new Button("submit");
        otherLetter.setStyle("-fx-text-fill: blue ; -fx-font-size: 20px;");
        Text otherLetterText = new Text("enter The first letter");
        otherLetterText.setStyle("-fx-text-fill: blue ; -fx-font-size: 20px;");
        Button saveGame = new Button("save game");
        saveGame.setStyle("-fx-text-fill: blue ; -fx-font-size: 20px;");

        InputStream inputStream;
        try {
            inputStream= new FileInputStream("photo.jpg");
        }catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Image image = new Image(inputStream);
        otherLetterStage.getIcons().add(image);

        VBox vBox = new VBox();
        vBox.setBackground(new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);
        Text firstLetterText = new Text("Choose the first letter of words");
        firstLetterText.setStyle("-fx-text-fill: red; -fx-font-size: 20px;");
        Text otherLetterText2 = new Text("we're out of rounds! now press submit to exit");
        Text otherLetterText3 = new Text("press submit to play with computer's first letter");
        otherLetterText2.setStyle("-fx-text-fill: blue ; -fx-font-size: 20px;");
        otherLetterText3.setStyle("-fx-text-fill: blue ; -fx-font-size: 20px;");

        int Rounds = Integer.parseInt(rounds);
        if (turn == 0) {
            if (Rounds > 0) {
                if (Rounds % 2 == 1) {
                    System.out.println("it's your turn");
                    vBox.getChildren().add(otherLetterText);

                }
                if (Rounds % 2 == 0) {
                    System.out.println("it's computer's turn");
                    vBox.getChildren().add(otherLetterText3);
                }
            }
            if (Rounds <= 0)
                vBox.getChildren().add(otherLetterText2);
            if (Rounds % 2 == 1) {
                vBox.getChildren().add(otherLetter);
            }
        }
        if (turn == 1) {

            if (Rounds > 0) {
                vBox.getChildren().add(otherLetterText);
                vBox.getChildren().addAll(otherLetter);
            }
            if (Rounds <= 0)
                vBox.getChildren().add(otherLetterText2);
        }
        vBox.getChildren().add(submit);
        vBox.getChildren().add(saveGame);

        String firstLetter = submitEventHandler(submit, otherLetter, nameC, lastnameC, flowerC, foodC, fruitC, thingsC, carC,
                countryC, cityC, clothesC, animalC, otherLetter.getText(),
                gamePage, serverOrClient, subjects, subjectsCount, rounds,jabberServer,jabberClient , clientSumSum,
                clientSum,f,serverSumSum, letters, valueLetters, exitPage, otherLetterStage,minutes,seconds,time,turn,
                saveGame);


        vBox.setPadding(new Insets(20, 200, 20, 200));
        Scene otherLetterScene = new Scene(vBox, 1000, 1000);
        otherLetterStage.setScene(otherLetterScene);
        otherLetterStage.setTitle("first letter page");
        otherLetterStage.show();
        return 1;
    }

    private String submitEventHandler(Button submit, TextField otherLetter, boolean nameC, boolean lastnameC,
                                      boolean flowerC, boolean foodC,
                                      boolean fruitC, boolean thingsC, boolean carC, boolean countryC, boolean cityC,
                                      boolean clothesC, boolean animalC, String text, GamePage gamePage,
                                      int serverOrClient, ArrayList<String> subjects, int subjectsCount, String rounds,
                                      JabberServer jabberServer, JabberClient jabberClient , int clientSumSum,
                                      int clientSum, int f, int serverSumSum, String[] letters, int[] valueLetters,
                                      ExitPage exitPage, Stage otherLetterStage, int minutes, int seconds, int time,
                                      int turn, Button saveGame) {

        saveGame.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                File output = new File("output.txt");

                PrintWriter printWriter = null;
                try {
                    printWriter = new PrintWriter(output);
                    printWriter.println(nameC);
                    printWriter.println(lastnameC);
                    printWriter.println(flowerC);
                    printWriter.println(foodC);
                    printWriter.println(fruitC);
                    printWriter.println(thingsC);
                    printWriter.println(carC);
                    printWriter.println(countryC);
                    printWriter.println(cityC);
                    printWriter.println(clothesC);
                    printWriter.println(animalC);
                    printWriter.println(serverOrClient);
                    printWriter.println(subjectsCount);
                    printWriter.println(rounds);
                    printWriter.println(clientSumSum);
                    printWriter.println(clientSum);
                    printWriter.println(f);
                    printWriter.println(serverSumSum);
                    for (int i = 0; i < 32; i++) {
                        printWriter.println(valueLetters[i]);
                    }
                    printWriter.println(minutes);
                    printWriter.println(seconds);
                    printWriter.println(time);
                    printWriter.println(turn);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                printWriter.close();
                otherLetterStage.close();
            }
        });

        submit.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int Rounds = Integer.parseInt(rounds);
                int x = clientSumSum + clientSum;
                int y = serverSumSum + f;
                if (serverOrClient == 2) {
                    System.out.println("Round = " + Rounds);
                    Rounds--;
                    if (Rounds >= 0) {
                        if (turn == 1) {
                            gamePage.boardGame(otherLetter.getText(), nameC, lastnameC, flowerC, foodC, fruitC, thingsC, carC,
                                    countryC, cityC, clothesC, animalC, serverOrClient, subjectsCount, Rounds, x, y, letters, valueLetters,
                                    minutes, seconds, time, turn);
                        }
                        if (turn == 0) {
                            if (Rounds % 2 == 1) {
                                String str = chooseWord(letters,valueLetters);
                                System.out.println("chosen letter is " + str);
                                gamePage.boardGame(str, nameC, lastnameC, flowerC, foodC, fruitC, thingsC, carC,
                                        countryC, cityC, clothesC, animalC, serverOrClient,subjectsCount, Rounds, x, y, letters, valueLetters,
                                        minutes, seconds, time, turn);
                            }
                            if (Rounds % 2 == 0) {
                                gamePage.boardGame(otherLetter.getText(), nameC, lastnameC, flowerC, foodC, fruitC, thingsC, carC,
                                        countryC, cityC, clothesC, animalC, serverOrClient,subjectsCount, Rounds, x, y, letters, valueLetters,
                                        minutes, seconds, time, turn);
                            }
                        }
                    }

                    else {
                        if (x > y)
                            System.out.println("YOU WON!");
                        if (y > x)
                            System.out.println("COMPUTER WON!");
                        if (x == y)
                            System.out.println("TIE!");
                        exitPage.exitPage();
                    }
                }

                if (serverOrClient == 1) {
                    try {
                        String str = chooseWord(letters,valueLetters);
                        ArrayList<String> computerSubjects = null;
                        jabberClient.jabberClient(str, nameC, lastnameC, flowerC, foodC, fruitC, thingsC, carC,
                                countryC, cityC, clothesC, animalC, serverOrClient, Rounds, x, y, letters, valueLetters,
                                minutes, seconds, time, turn,subjectsCount, subjects, computerSubjects);
                    } catch (IOException | InterruptedException e) {
                        throw new RuntimeException(e);

                    }
                }
            }
        });

        submit.addEventFilter(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                for (int i = 0; i < 32; i++) {
                    if (otherLetter.getText().equals(letters[i])) {
                        if (valueLetters[i] == 0) {
                            System.out.println("You've chosen this letter before.");
                            System.out.println("Choose another letter");
                            event.consume();
                        }
                        if (valueLetters[i] == 1) {
                            valueLetters[i] = 0;
                            System.out.println("chosen letter is  " + letters[i]);
                        }
                    }
                }

            }
        });
        return otherLetter.getText();
    }
    public String chooseWord(String[] letters, int[] valueLetters) {
        String str = null;
        for (int i = 0; i < 32; i++) {
                if (valueLetters[i] == 0) {
                    System.out.println("You've chosen this letter before.");
                    System.out.println("Choose another letter");
                }
                if (valueLetters[i] == 1) {
                    str = letters[i];
                    valueLetters[i] = 0;
                    break;
                }
        }
        return str;
    }
}

