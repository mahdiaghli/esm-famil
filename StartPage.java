import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.event.EventHandler;
import javafx.util.Duration;
import javafx.scene.media.Media;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class StartPage extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        OtherLetter otherLetter = new OtherLetter();
        GamePage gamePage = new GamePage();
        Media media = new Media(Paths.get("jetelaisserai.mp3").toUri().toString());
        MediaPlayer MusicPlayer = new MediaPlayer(media);
        MusicPlayer.play();
        MusicPlayer.setOnEndOfMedia(new Runnable() {
            public void run() {
                MusicPlayer.seek(Duration.ZERO);
            }
        });
        ServerPage serverPage = new ServerPage();
        InformationPage informationPage = new InformationPage();
        Button startButton = new Button("Start");
        Button endButton = new Button("Exit");
        startButtonEventHandler(startButton, serverPage,primaryStage);
        endButtonEventHandler(endButton, primaryStage);
        Button continueGame = new Button("continueGame");
        continueGame.setStyle("-fx-background-color:orange;-fx-text-fill:white");

        continueGame.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                File output = new File("output.txt");
                try {
                    Scanner scanner = new Scanner(output);
                    while (scanner.hasNext()) {
                        String[] letters = new String[]{"ا", "ب", "پ", "ت", "ث", "ج", "چ", "ح", "خ", "د", "ذ", "ر", "ز",
                                "ژ", "س", "ش", "ص", "ض", "ط", "ظ", "ع", "غ", "ف", "ق", "ک", "گ",
                                "ل", "م", "ن", "و", "ه", "ی"};
                        boolean nameC = Boolean.parseBoolean(scanner.nextLine());
                        boolean lastnameC = Boolean.parseBoolean(scanner.nextLine());
                        boolean flowerC = Boolean.parseBoolean(scanner.nextLine());
                        boolean foodC = Boolean.parseBoolean(scanner.nextLine());
                        boolean fruitC = Boolean.parseBoolean(scanner.nextLine());
                        boolean thingsC = Boolean.parseBoolean(scanner.nextLine());
                        boolean carC = Boolean.parseBoolean(scanner.nextLine());
                        boolean countryC = Boolean.parseBoolean(scanner.nextLine());
                        boolean cityC = Boolean.parseBoolean(scanner.nextLine());
                        boolean clothesC = Boolean.parseBoolean(scanner.nextLine());
                        boolean animalC = Boolean.parseBoolean(scanner.nextLine());
                        int serverOrClient = Integer.parseInt(scanner.nextLine());
                        int subjectsCount = Integer.parseInt(scanner.nextLine());
                        String rounds = scanner.nextLine();
                        int clientSumSum = Integer.parseInt(scanner.nextLine());
                        int clientSum = Integer.parseInt(scanner.nextLine());
                        int f = Integer.parseInt(scanner.nextLine());
                        int serverSumSum = Integer.parseInt(scanner.nextLine());
                        int[] valueLetters = new int[32];
                        for (int i = 0; i < 32; i++) {
                            valueLetters[i] = Integer.parseInt(scanner.nextLine());
                        }
                        int minutes = Integer.parseInt(scanner.nextLine());
                        int seconds = Integer.parseInt(scanner.nextLine());
                        int time = Integer.parseInt(scanner.nextLine());
                        int turn = Integer.parseInt(scanner.nextLine());

                        ArrayList<String> subjects = null;
                        otherLetter.otherLetter(nameC, lastnameC, flowerC, foodC, fruitC, thingsC, carC,
                                countryC, cityC, clothesC, animalC,
                                gamePage, serverOrClient, subjects, subjectsCount,
                                rounds, clientSumSum, clientSum, f, serverSumSum, letters,valueLetters,
                                minutes,seconds,time,turn);
                    }
                } catch (IOException | InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        InputStream inputStream;
        try {
            inputStream= new FileInputStream("photo.jpg");
        }catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Image image = new Image(inputStream);
        primaryStage.getIcons().add(image);

        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);
        startButton.setStyle("-fx-background-color:green;-fx-text-fill:white;-fx-front:normal bold 36px 'serif'");
        endButton.setStyle("-fx-background-color:red;-fx-text-fill:white");
        vBox.setBackground(new Background(new BackgroundFill(Color.YELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
        Text text = new Text("Esm Famil");
        text.setStyle("-fx-text-fill: blue; -fx-font-size: 25px;");
        vBox.getChildren().addAll(text,startButton,continueGame,endButton);
        StackPane stackPane = new StackPane();

        InputStream inputStream2;
        try {
            inputStream2 = new FileInputStream("photo.jpg");
        }catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Image image2 = new Image(inputStream2);
        ImageView imageView = new ImageView(image2);
        imageView.setImage(image2);
        imageView.setFitWidth(400);
        imageView.setFitHeight(400);
        vBox.getChildren().add(imageView);
        Scene scene = new Scene(new Group());
        scene.setRoot(vBox);
        primaryStage.setTitle("Esm Famil");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void startButtonEventHandler(Button startButton, ServerPage serverPage,Stage primaryStage) {
        startButton.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Game Started");
                serverPage.serverPage1(primaryStage);
                primaryStage.close();
            }
        });
    }

    public void endButtonEventHandler(Button endButton, Stage primaryStage) {

        endButton.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (alert()) {
                    primaryStage.close();
                    System.out.println("The End");
                }
            }
        });
    }

    public boolean alert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("");
        alert.setTitle("Exit");
        alert.setContentText("Are you sure?");
        alert.getButtonTypes().clear();
        alert.getButtonTypes().add(ButtonType.YES);
        alert.getButtonTypes().add(ButtonType.NO);
        System.out.println("Are you sure?");
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {
            System.out.println("YES");
            alert.close();
//            primaryStage.close();
        }
        if (!(alert.getResult() == ButtonType.YES))
            System.out.println("NO");
        return alert.getResult() == ButtonType.YES;
    }
}


