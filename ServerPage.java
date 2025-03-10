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
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class ServerPage {
    public void serverPage1(Stage primaryStage) {
        StartPage startPage = new StartPage();
        JabberServer jabberServer = new JabberServer();
        GamePage gamePage = new GamePage();
        TimePage timePage = new TimePage();
        Stage serverStage = new Stage();
        HBox hBox = new HBox();
        Button back = new Button("back");
        Button createServer = new Button("createServer");
        Button joinServer = new Button("joinServer");
        Button playByYourOwn = new Button("play with computer");
        hBox.setSpacing(20);
        hBox.getChildren().addAll(playByYourOwn, createServer, joinServer,back);
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

        createServer.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    jabberServer.jabberServer(arrayList,0,0);
                    serverStage.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        });

        joinServer.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                timePage.timeOrder(1);
                serverStage.close();
            }
        });

        playByYourOwn.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                timePage.timeOrder(2);
                serverStage.close();
            }
        });

        back.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    startPage.start(primaryStage);
                    serverStage.close();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

            }
        });

        joinServer.setStyle("-fx-background-color:brown;-fx-text-fill:white;-fx-front:normal bold 20px 'serif'");
        createServer.setStyle("-fx-background-color:gold;-fx-text-fill:white;-fx-front:normal bold 20px 'serif'");
        back.setStyle("-fx-background-color:blue;-fx-text-fill:white;-fx-front:normal bold 20px 'serif'");
        hBox.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        Scene scene = new Scene(hBox, 500, 500);
        serverStage.setTitle("server Page");
        serverStage.setScene(scene);
        serverStage.show();
    }
}