import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class GamePage {

    Timeline timeline;
    Text timer;
    int minutes = 0, seconds = 0;
    public void boardGame(String firstLetter, boolean nameC, boolean lastnameC, boolean flowerC,
                          boolean foodC, boolean fruitC, boolean thingsC, boolean carC, boolean countryC,
                          boolean cityC, boolean clothesC, boolean animalC, int serverOrClient,int subjectCount, int rounds,
                          int clientSumSum, int serverSumSum, String[] letters, int[] valueLetters, int minutes,
                          int seconds, int time, int turn) {

        NewJabberClient newJabberClient = new NewJabberClient();
        ExitPage exitPage = new ExitPage();
        OtherLetter otherLetter = new OtherLetter();
        JabberServer jabberServer = new JabberServer();
        JabberClient jabberClient = new JabberClient();
        GamePage gamePage = new GamePage();
        TextField name = new TextField();
        name.setPromptText("name");
        TextField lastName = new TextField();
        lastName.setPromptText("lastName");
        TextField city = new TextField();
        city.setPromptText("city");
        TextField country = new TextField();
        country.setPromptText("country");
        TextField food = new TextField();
        food.setPromptText("food");
        TextField clothes = new TextField();
        clothes.setPromptText("clothes");
        TextField fruit = new TextField();
        fruit.setPromptText("fruit");
        TextField car = new TextField();
        car.setPromptText("car");
        TextField animal = new TextField();
        animal.setPromptText("animal");
        TextField things = new TextField();
        things.setPromptText("things");
        TextField flower = new TextField();
        flower.setPromptText("flower");
        Button submit = new Button("submit");
        Stage boardStage = new Stage();

        InputStream inputStream;
        try {
            inputStream= new FileInputStream("photo.jpg");
        }catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        Image image = new Image(inputStream);
        boardStage.getIcons().add(image);

        VBox boardVBox2 = new VBox();
        boardVBox2.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        boardVBox2.setAlignment(Pos.CENTER);
        boardVBox2.setSpacing(10);
        boardVBox2.setPadding(new Insets(20,200,20,200));
        boardVBox2.getChildren().addAll(name, lastName, flower, food, fruit, things, car, country, city, clothes, animal,submit);
        ArrayList<String> words = new ArrayList<String>();
                timer = new Text((((minutes / 10) == 0) ? "0" : "") + minutes + ":"
                        + (((seconds / 10) == 0) ? "0" : "") + seconds);
                this.minutes = minutes;
                this.seconds = seconds;
                timeline = new Timeline(new KeyFrame(Duration.millis(1000), event -> {
                    change();
                    if (check()) {
                        try {
                            endTime(name, lastName, flower, food, fruit, things, car, country,
                                    city, clothes, animal, submit, firstLetter, nameC, lastnameC, flowerC, foodC, fruitC,
                                    thingsC, carC, countryC, cityC, clothesC, animalC, jabberClient, jabberServer, serverOrClient,
                                    gamePage,subjectCount, rounds, otherLetter, clientSumSum, serverSumSum, letters, valueLetters, exitPage,
                                    boardStage, minutes, seconds, 0, time, turn, event, newJabberClient);
                        } catch (FileNotFoundException e) {
                            throw new RuntimeException(e);
                        }

                        System.out.println("time is up!");
                        timeline.pause();
                        boardStage.close();
                    }
                }));
                timeline.setCycleCount(Timeline.INDEFINITE);
                timeline.setAutoReverse(false);
                timeline.play();

            VBox vBox = new VBox(30);
            vBox.setAlignment(Pos.CENTER);
            if (time == 0)
                vBox.getChildren().add(timer);
            boardVBox2.getChildren().add(vBox);

        chechboxAnalizer(nameC,lastnameC,flowerC,foodC,fruitC, thingsC,carC,countryC,cityC,clothesC,animalC,name,
                                             lastName,flower,food,fruit, things,car,country,city,clothes,animal);


        submitWordEventFilter(name, lastName,flower,food,fruit, things,car,country,city,clothes,animal,submit,
                               firstLetter, nameC, lastnameC, flowerC, foodC, fruitC, thingsC, carC, countryC,
                               cityC, clothesC, animalC,jabberClient,jabberServer,serverOrClient,subjectCount,gamePage,rounds,
                               otherLetter, clientSumSum,serverSumSum, letters, valueLetters,exitPage, boardStage,
                               minutes, seconds,time,turn,newJabberClient);

        Scene boardScene = new Scene(boardVBox2,500,600);
        boardStage.setScene(boardScene);
        boardStage.setTitle("boardGame");
        boardStage.show();
    }

    public boolean check() {
        return minutes * 60 + seconds == 0;
    }

    public boolean wordCheck(File file,String str) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            String str2 = scanner.nextLine();
            if (str2.equals(str)) {
                return true;
            }
        }
        return false;
    }


    public String computerWordCheck(File file, String firstLetter, int timeValue, int[] i) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        String empty = "";
        while (scanner.hasNext()) {
            String str2 = scanner.nextLine();
            char char0 = str2.charAt(0);
            if (char0 == firstLetter.charAt(0) && timeValue == 0) {
                i[0]++;
                return str2;
            }
        }
        return empty;
    }


    public void change() {
        if(seconds == 0){
            minutes--;
            seconds = 59;
        }
        seconds--;
        timer.setText((((minutes/10) == 0) ? "0" : "") + minutes + ":"
                + (((seconds/10) == 0) ? "0" : "") + seconds);
    }


    public void chechboxAnalizer( boolean nameC, boolean lastnameC, boolean flowerC,
                                  boolean foodC, boolean fruitC, boolean thingsC, boolean carC, boolean countryC,
                                  boolean cityC, boolean clothesC, boolean animalC,TextField name,TextField lastname,
                                  TextField flower,TextField food,TextField fruit, TextField things,TextField car,
                                  TextField country,TextField city,TextField clothes,TextField animal) {
        if (!nameC)
            name.setDisable(true);
        if (!lastnameC)
            lastname.setDisable(true);
        if (!flowerC)
            flower.setDisable(true);
        if (!thingsC)
            things.setDisable(true);
        if (!carC)
            car.setDisable(true);
        if (!cityC)
            city.setDisable(true);
        if (!countryC)
            country.setDisable(true);
        if (!animalC)
            animal.setDisable(true);
        if (!clothesC)
            clothes.setDisable(true);
        if (!foodC)
            food.setDisable(true);
        if (!fruitC)
            fruit.setDisable(true);
    }

    public void submitWordEventFilter(TextField name, TextField lastName, TextField flower, TextField food, TextField fruit, TextField things,
                                      TextField car, TextField country, TextField city, TextField clothes, TextField animal, Button submit,
                                      String firstLetter, boolean nameC, boolean lastnameC, boolean flowerC,
                                      boolean foodC, boolean fruitC, boolean thingsC, boolean carC, boolean countryC,
                                      boolean cityC, boolean clothesC, boolean animalC, JabberClient jabberClient,
                                      JabberServer jabberServer, int serverOrClient, int subjectCount, GamePage gamePage,
                                      int rounds, OtherLetter otherLetter, int clientSumSum , int serverSumSum, String[] letters,
                                      int[] valueLetters, ExitPage exitPage, Stage boardStage, int minutes, int seconds, int time,
                                      int turn, NewJabberClient newJabberClient) {


        submit.addEventFilter(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {

                        ArrayList<String> subjects = new ArrayList<String>();
                        ArrayList<String> computerSubjects = new ArrayList<String>();

                        File nameFile = new File("اسم.txt");
                        File thingsFile = new File("اشیا.txt");
                        File clothesFile = new File("پوشاک.txt");
                        File animalFile = new File("حیوان.txt");
                        File fruitFile = new File("میوه.txt");
                        File lastNameFile = new File("فامیل.txt");
                        File cityFile = new File("شهر.txt");
                        File countryFile = new File("کشور.txt");
                        File flowerFile = new File("گل.txt");
                        File carFile = new File("ماشین.txt");
                        File foodFile = new File("غذا.txt");
                        int[] a = {0};
                        try {

                            if (nameC) {
                                computerSubjects.add(computerWordCheck(nameFile, firstLetter, 0,a));
                            }
                            if (lastnameC) {
                                computerSubjects.add(computerWordCheck(lastNameFile, firstLetter, 0, a));
                            }
                            if (flowerC) {
                                computerSubjects.add(computerWordCheck(flowerFile, firstLetter, 0, a));
                            }
                            if (foodC) {
                                computerSubjects.add(computerWordCheck(foodFile, firstLetter, 0, a));
                            }
                            if (fruitC) {
                                computerSubjects.add(computerWordCheck(fruitFile, firstLetter, 0, a));
                            }
                            if (thingsC) {
                                computerSubjects.add(computerWordCheck(thingsFile, firstLetter, 0, a));
                            }
                            if (carC) {
                                computerSubjects.add(computerWordCheck(carFile, firstLetter, 0, a));
                            }
                            if (countryC) {
                                computerSubjects.add(computerWordCheck(countryFile, firstLetter, 0, a));
                            }
                            if (cityC) {
                                computerSubjects.add(computerWordCheck(cityFile, firstLetter, 0, a));
                            }
                            if (clothesC) {
                                computerSubjects.add(computerWordCheck(clothesFile, firstLetter, 0, a));
                            }
                            if (animalC) {
                                computerSubjects.add(computerWordCheck(animalFile, firstLetter, 0, a));
                            }
                        }
                        catch (FileNotFoundException e) {
                            throw new RuntimeException(e);
                        }

                        int x = 0;

                            if (nameC) {
                                if (name.getText().isEmpty() || !(firstLetter.charAt(0) == name.getText().charAt(0))) {
                                    System.out.println("enter something that start whit first letter for name");
                                    event.consume();
                                    name.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
                                            BorderWidths.DEFAULT)));
                                } else {
                                    name.setBorder(new Border(new BorderStroke(Color.GREEN, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
                                            BorderWidths.DEFAULT)));
                                    subjects.add(name.getText());
                                    x++;
                                }
                            }

                            if (lastnameC) {
                                if (lastName.getText().isEmpty() || !(firstLetter.charAt(0) == lastName.getText().charAt(0))) {
                                    System.out.println("enter something that start whit first letter for lastname");
                                    event.consume();
                                    lastName.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
                                            BorderWidths.DEFAULT)));
                                } else {
                                    lastName.setBorder(new Border(new BorderStroke(Color.GREEN, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
                                            BorderWidths.DEFAULT)));
                                    subjects.add(lastName.getText());
                                    x++;
                                }
                            }

                            if (flowerC) {
                                if (flower.getText().isEmpty() || !(firstLetter.charAt(0) == flower.getText().charAt(0))) {
                                    System.out.println("enter something that start whit first letter for flower");
                                    event.consume();
                                    flower.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
                                            BorderWidths.DEFAULT)));
                                } else {
                                    flower.setBorder(new Border(new BorderStroke(Color.GREEN, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
                                            BorderWidths.DEFAULT)));
                                    subjects.add(flower.getText());
                                    x++;
                                }
                            }

                            if (foodC) {
                                if (food.getText().isEmpty() || !(firstLetter.charAt(0) == food.getText().charAt(0))) {
                                    System.out.println("enter something that start whit first letter for food");
                                    event.consume();
                                    food.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
                                            BorderWidths.DEFAULT)));
                                } else {
                                    food.setBorder(new Border(new BorderStroke(Color.GREEN, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
                                            BorderWidths.DEFAULT)));
                                    subjects.add(food.getText());
                                    x++;
                                }
                            }

                            if (fruitC) {
                                if (fruit.getText().isEmpty() || !(firstLetter.charAt(0) == fruit.getText().charAt(0))) {
                                    System.out.println("enter something that start whit first letter for fruit");
                                    event.consume();
                                    fruit.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
                                            BorderWidths.DEFAULT)));
                                } else {
                                    fruit.setBorder(new Border(new BorderStroke(Color.GREEN, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
                                            BorderWidths.DEFAULT)));
                                    subjects.add(fruit.getText());
                                    x++;
                                }
                            }

                            if (thingsC) {
                                if (things.getText().isEmpty() || !(firstLetter.charAt(0) == things.getText().charAt(0))) {
                                    System.out.println("enter something that start whit first letter for thing");
                                    event.consume();
                                    things.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
                                            BorderWidths.DEFAULT)));
                                } else {
                                    things.setBorder(new Border(new BorderStroke(Color.GREEN, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
                                            BorderWidths.DEFAULT)));
                                    subjects.add(things.getText());
                                    x++;
                                }
                            }

                            if (carC) {
                                if (car.getText().isEmpty() || !(firstLetter.charAt(0) == car.getText().charAt(0))) {
                                    System.out.println("enter something that start whit first letter for car");
                                    event.consume();
                                    car.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
                                            BorderWidths.DEFAULT)));
                                } else {
                                    car.setBorder(new Border(new BorderStroke(Color.GREEN, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
                                            BorderWidths.DEFAULT)));
                                    subjects.add(car.getText());
                                    x++;
                                }
                            }

                            if (countryC) {
                                if (country.getText().isEmpty() || !(firstLetter.charAt(0) == country.getText().charAt(0))) {
                                    System.out.println("enter something that start whit first letter for country");
                                    event.consume();
                                    country.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
                                            BorderWidths.DEFAULT)));
                                } else {
                                    country.setBorder(new Border(new BorderStroke(Color.GREEN, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
                                            BorderWidths.DEFAULT)));
                                    subjects.add(country.getText());
                                    x++;
                                }
                            }

                            if (cityC) {
                                if (city.getText().isEmpty() || !(firstLetter.charAt(0) == city.getText().charAt(0))) {
                                    System.out.println("enter something that start whit first letter for city");
                                    event.consume();
                                    city.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
                                            BorderWidths.DEFAULT)));
                                } else {
                                    city.setBorder(new Border(new BorderStroke(Color.GREEN, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
                                            BorderWidths.DEFAULT)));
                                    subjects.add(city.getText());
                                    x++;
                                }
                            }

                            if (clothesC) {
                                if (clothes.getText().isEmpty() || !(firstLetter.charAt(0) == clothes.getText().charAt(0))) {
                                    System.out.println("enter something that start whit first letter for cloth");
                                    event.consume();
                                    clothes.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
                                            BorderWidths.DEFAULT)));
                                } else {
                                    clothes.setBorder(new Border(new BorderStroke(Color.GREEN, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
                                            BorderWidths.DEFAULT)));
                                    subjects.add(clothes.getText());
                                    x++;
                                }
                            }

                            if (animalC) {
                                if (animal.getText().isEmpty() || !(firstLetter.charAt(0) == animal.getText().charAt(0))) {
                                    System.out.println("enter something that start whit first letter for animal");
                                    event.consume();
                                    animal.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
                                            BorderWidths.DEFAULT)));
                                } else {
                                    animal.setBorder(new Border(new BorderStroke(Color.GREEN, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
                                            BorderWidths.DEFAULT)));
                                    subjects.add(animal.getText());
                                    x++;
                                }
                            }

                        System.out.println();

                        if (serverOrClient == 2) {

                            int serverPoint = 0;
                            int serverSum = 0;
                            int clientPoint = 0;
                            int clientSum = 0;
                            int c = a[0] * 10;
                            for (int i = 0; i < subjects.size(); i++) {

                                String subject1 = subjects.get(i);
                                String subject2 = computerSubjects.get(i);
//                                System.out.println("computerSubjects = " + computerSubjects.get(i));
                                try {
                                    if (subject1.equals(subject2) && (wordCheck(nameFile,subject1) ||
                                            wordCheck(lastNameFile,subject1) || wordCheck(flowerFile,subject1) ||
                                            wordCheck(foodFile,subject1) || wordCheck(fruitFile,subject1) ||
                                            wordCheck(thingsFile,subject1) || wordCheck(carFile,subject1) ||
                                            wordCheck(countryFile,subject1) || wordCheck(cityFile,subject1) ||
                                            wordCheck(clothesFile,subject1) || wordCheck(animalFile,subject1))) {
                                        serverPoint += 5;
                                        clientPoint += 5;
                                        c -= 5;
                                    }
                                } catch (FileNotFoundException e) {
                                    throw new RuntimeException(e);
                                }
                                if (subject1 == null) {

                                }
                                try {
                                    if (!subject1.equals(subject2) && (wordCheck(nameFile, subject1) ||
                                            wordCheck(lastNameFile, subject1) || wordCheck(flowerFile, subject1) ||
                                            wordCheck(foodFile, subject1) || wordCheck(fruitFile, subject1) ||
                                            wordCheck(thingsFile, subject1) || wordCheck(carFile, subject1) ||
                                            wordCheck(countryFile, subject1) || wordCheck(cityFile, subject1) ||
                                            wordCheck(clothesFile, subject1) || wordCheck(animalFile, subject1))) {
                                        clientPoint += 10;
                                        serverPoint += 10;
                                    }
                                } catch (FileNotFoundException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                            serverSum += serverPoint;
                            clientSum += clientPoint;
                            int d = clientSumSum + clientSum;
                            c += serverSumSum;
                            try {
                                if (x == subjectCount) {
                                    System.out.println("your score is = " + d);
                                    System.out.println("computer's score is = " + c);
                                    otherLetter.otherLetter(nameC, lastnameC, flowerC, foodC, fruitC, thingsC, carC,
                                            countryC, cityC, clothesC, animalC, gamePage, serverOrClient,
                                            subjects, subjectCount, String.valueOf(rounds), clientSumSum, clientSum, c, serverSumSum,
                                            letters, valueLetters, minutes, seconds, time, turn);
                                    if (rounds >= 0) {
                                        System.out.println("computer's Subjects = " + computerSubjects);
                                        System.out.println("your subjects = " + subjects);
                                    }
                                }
                            } catch (IOException | InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        if (serverOrClient == 1) {
                            int serverPoint = 0;
                            int serverSum = 0;
                            int clientPoint = 0;
                            int clientSum = 0;
                            int c = a[0] * 10;
                            for (int i = 0; i < subjects.size(); i++) {

                                String subject1 = subjects.get(i);
                                String subject2 = computerSubjects.get(i);
                                try {
                                    if (subject1.equals(subject2) && (wordCheck(nameFile,subject1) ||
                                            wordCheck(lastNameFile,subject1) || wordCheck(flowerFile,subject1) ||
                                            wordCheck(foodFile,subject1) || wordCheck(fruitFile,subject1) ||
                                            wordCheck(thingsFile,subject1) || wordCheck(carFile,subject1) ||
                                            wordCheck(countryFile,subject1) || wordCheck(cityFile,subject1) ||
                                            wordCheck(clothesFile,subject1) || wordCheck(animalFile,subject1))) {
                                        serverPoint += 5;
                                        clientPoint += 5;
                                        c -= 5;
                                    }
                                } catch (FileNotFoundException e) {
                                    throw new RuntimeException(e);
                                }
                                if (subject1 == null) {

                                }
                                try {
                                    if (!subject1.equals(subject2) && (wordCheck(nameFile, subject1) ||
                                            wordCheck(lastNameFile, subject1) || wordCheck(flowerFile, subject1) ||
                                            wordCheck(foodFile, subject1) || wordCheck(fruitFile, subject1) ||
                                            wordCheck(thingsFile, subject1) || wordCheck(carFile, subject1) ||
                                            wordCheck(countryFile, subject1) || wordCheck(cityFile, subject1) ||
                                            wordCheck(clothesFile, subject1) || wordCheck(animalFile, subject1))) {
                                        clientPoint += 10;
                                        serverPoint += 10;
                                    }
                                } catch (FileNotFoundException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                            serverSum += serverPoint;
                            clientSum += clientPoint;
                            int d = clientSumSum + clientSum;
                            c += serverSumSum;
                            try {
                                if (x == subjectCount) {
//                                    while (rounds > 0) {

                                    System.out.println("your score is = " + d);
                                    System.out.println("computer's score is = " + c);
                                    newJabberClient.newjabberClient(firstLetter, nameC, lastnameC, flowerC, foodC, fruitC, thingsC, carC,
                                            countryC, cityC, clothesC, animalC, serverOrClient, rounds, c, d, letters, valueLetters,
                                            minutes, seconds, time, turn, subjectCount, subjects, computerSubjects);
//                                    }
                                    if (rounds >= 0) {
                                        System.out.println("computer's Subjects = " + computerSubjects);
                                        System.out.println("your subjects = " + subjects);
                                    }
                                }
                            } catch (IOException | InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
            }
        });
    }
    public ArrayList<String> endTime(TextField name, TextField lastName, TextField flower, TextField food, TextField fruit, TextField things,
                                     TextField car, TextField country, TextField city, TextField clothes, TextField animal, Button submit,
                                     String firstLetter, boolean nameC, boolean lastnameC, boolean flowerC,
                                     boolean foodC, boolean fruitC, boolean thingsC, boolean carC, boolean countryC,
                                     boolean cityC, boolean clothesC, boolean animalC, JabberClient jabberClient,
                                     JabberServer jabberServer, int serverOrClient, GamePage gamePage, int subjectCount,
                                     int rounds, OtherLetter otherLetter, int clientSumSum , int serverSumSum, String[] letters,
                                     int[] valueLetters, ExitPage exitPage, Stage boardStage, int minutes, int seconds,
                                     int timeValue, int time, int turn, ActionEvent event, NewJabberClient newJabberClient) throws FileNotFoundException {

        File nameFile = new File("اسم.txt");
        File thingsFile = new File("اشیا.txt");
        File clothesFile = new File("پوشاک.txt");
        File animalFile = new File("حیوان.txt");
        File fruitFile = new File("میوه.txt");
        File lastNameFile = new File("فامیل.txt");
        File cityFile = new File("شهر.txt");
        File countryFile = new File("کشور.txt");
        File flowerFile = new File("گل.txt");
        File carFile = new File("ماشین.txt");
        File foodFile = new File("غذا.txt");

        ArrayList<String> subjects = new ArrayList<String>();
        ArrayList<String> computerSubjects = new ArrayList<String>();
        int x = 0;
        int[] a = {0};
        try {
            if (nameC) {
                computerSubjects.add(computerWordCheck(nameFile, firstLetter, timeValue, a));
            }
            if (lastnameC) {
                computerSubjects.add(computerWordCheck(lastNameFile, firstLetter, timeValue, a));
            }
            if (flowerC) {
                computerSubjects.add(computerWordCheck(flowerFile, firstLetter, timeValue, a));
            }
            if (foodC) {
                computerSubjects.add(computerWordCheck(foodFile, firstLetter, timeValue, a));
            }
            if (fruitC) {
                computerSubjects.add(computerWordCheck(fruitFile, firstLetter, timeValue, a));
            }
            if (thingsC) {
                computerSubjects.add(computerWordCheck(thingsFile, firstLetter, timeValue, a));
            }
            if (carC) {
                computerSubjects.add(computerWordCheck(carFile, firstLetter, timeValue, a));
            }
            if (countryC) {
                computerSubjects.add(computerWordCheck(countryFile, firstLetter, timeValue, a));
            }
            if (cityC) {
                computerSubjects.add(computerWordCheck(cityFile, firstLetter, timeValue, a));
            }
            if (clothesC) {
                computerSubjects.add(computerWordCheck(clothesFile, firstLetter, timeValue, a));
            }
            if (animalC) {
                computerSubjects.add(computerWordCheck(animalFile, firstLetter, timeValue, a));
            }



            if (nameC) {
                if (name.getText().isEmpty() || !(firstLetter.charAt(0) == name.getText().charAt(0))) {
                    System.out.println("enter something that start whit first letter for name");
                    event.consume();
                    name.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
                            BorderWidths.DEFAULT)));
                } else {
                    name.setBorder(new Border(new BorderStroke(Color.GREEN, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
                            BorderWidths.DEFAULT)));
                    subjects.add(name.getText());
                    x++;
                }
            }

            if (lastnameC) {
                if (lastName.getText().isEmpty() || !(firstLetter.charAt(0) == lastName.getText().charAt(0))) {
                    System.out.println("enter something that start whit first letter for lastname");
                    event.consume();
                    lastName.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
                            BorderWidths.DEFAULT)));
                } else {
                    lastName.setBorder(new Border(new BorderStroke(Color.GREEN, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
                            BorderWidths.DEFAULT)));
                    subjects.add(lastName.getText());
                    x++;
                }
            }

            if (flowerC) {
                if (flower.getText().isEmpty() || !(firstLetter.charAt(0) == flower.getText().charAt(0))) {
                    System.out.println("enter something that start whit first letter for flower");
                    event.consume();
                    flower.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
                            BorderWidths.DEFAULT)));
                } else {
                    flower.setBorder(new Border(new BorderStroke(Color.GREEN, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
                            BorderWidths.DEFAULT)));
                    subjects.add(flower.getText());
                    x++;
                }
            }

            if (foodC) {
                if (food.getText().isEmpty() || !(firstLetter.charAt(0) == food.getText().charAt(0))) {
                    System.out.println("enter something that start whit first letter for food");
                    event.consume();
                    food.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
                            BorderWidths.DEFAULT)));
                } else {
                    food.setBorder(new Border(new BorderStroke(Color.GREEN, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
                            BorderWidths.DEFAULT)));
                    subjects.add(food.getText());
                    x++;
                }
            }

            if (fruitC) {
                if (fruit.getText().isEmpty() || !(firstLetter.charAt(0) == fruit.getText().charAt(0))) {
                    System.out.println("enter something that start whit first letter for fruit");
                    event.consume();
                    fruit.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
                            BorderWidths.DEFAULT)));
                } else {
                    fruit.setBorder(new Border(new BorderStroke(Color.GREEN, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
                            BorderWidths.DEFAULT)));
                    subjects.add(fruit.getText());
                    x++;
                }
            }

            if (thingsC) {
                if (things.getText().isEmpty() || !(firstLetter.charAt(0) == things.getText().charAt(0))) {
                    System.out.println("enter something that start whit first letter for thing");
                    event.consume();
                    things.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
                            BorderWidths.DEFAULT)));
                } else {
                    things.setBorder(new Border(new BorderStroke(Color.GREEN, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
                            BorderWidths.DEFAULT)));
                    subjects.add(things.getText());
                    x++;
                }
            }

            if (carC) {
                if (car.getText().isEmpty() || !(firstLetter.charAt(0) == car.getText().charAt(0))) {
                    System.out.println("enter something that start whit first letter for car");
                    event.consume();
                    car.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
                            BorderWidths.DEFAULT)));
                } else {
                    car.setBorder(new Border(new BorderStroke(Color.GREEN, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
                            BorderWidths.DEFAULT)));
                    subjects.add(car.getText());
                    x++;
                }
            }

            if (countryC) {
                if (country.getText().isEmpty() || !(firstLetter.charAt(0) == country.getText().charAt(0))) {
                    System.out.println("enter something that start whit first letter for country");
                    event.consume();
                    country.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
                            BorderWidths.DEFAULT)));
                } else {
                    country.setBorder(new Border(new BorderStroke(Color.GREEN, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
                            BorderWidths.DEFAULT)));
                    subjects.add(country.getText());
                    x++;
                }
            }

            if (cityC) {
                if (city.getText().isEmpty() || !(firstLetter.charAt(0) == city.getText().charAt(0))) {
                    System.out.println("enter something that start whit first letter for city");
                    event.consume();
                    city.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
                            BorderWidths.DEFAULT)));
                } else {
                    city.setBorder(new Border(new BorderStroke(Color.GREEN, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
                            BorderWidths.DEFAULT)));
                    subjects.add(city.getText());
                    x++;
                }
            }

            if (clothesC) {
                if (clothes.getText().isEmpty() || !(firstLetter.charAt(0) == clothes.getText().charAt(0))) {
                    System.out.println("enter something that start whit first letter for cloth");
                    event.consume();
                    clothes.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
                            BorderWidths.DEFAULT)));
                } else {
                    clothes.setBorder(new Border(new BorderStroke(Color.GREEN, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
                            BorderWidths.DEFAULT)));
                    subjects.add(clothes.getText());
                    x++;
                }
            }

            if (animalC) {
                if (animal.getText().isEmpty() || !(firstLetter.charAt(0) == animal.getText().charAt(0))) {
                    System.out.println("enter something that start whit first letter for animal");
                    event.consume();
                    animal.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
                            BorderWidths.DEFAULT)));
                } else {
                    animal.setBorder(new Border(new BorderStroke(Color.GREEN, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
                            BorderWidths.DEFAULT)));
                    subjects.add(animal.getText());
                    x++;
                }
            }
        }
        catch (StringIndexOutOfBoundsException e) {
            System.out.println("you didn't fill all fields!");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        System.out.println();

        if (serverOrClient == 2) {
            int serverPoint = 0;
            int serverSum = 0;
            int clientPoint = 0;
            int clientSum = 0;
            int c = a[0] * 10;

            System.out.println("computer's Subjects = " + computerSubjects);
            System.out.println("your subjects = " + subjects);

            for (int i = 0; i < subjects.size(); i++) {
                String subject1 = subjects.get(i);
                String subject2 = computerSubjects.get(i);

                if (subject1.equals(subject2) && (wordCheck(nameFile,subject1) || wordCheck(lastNameFile,subject1) ||
                        wordCheck(flowerFile,subject1) || wordCheck(foodFile,subject1) || wordCheck(fruitFile,subject1) ||
                        wordCheck(thingsFile,subject1) || wordCheck(carFile,subject1) || wordCheck(countryFile,subject1) ||
                        wordCheck(cityFile,subject1) || wordCheck(clothesFile,subject1) || wordCheck(animalFile,subject1))) {
                    serverPoint += 5;
                    clientPoint += 5;
                    c -= 5;
                }
                if (subject1 == null) {

                }
                if (!subject1.equals(subject2) && (wordCheck(nameFile,subject1) || wordCheck(lastNameFile,subject1) ||
                        wordCheck(flowerFile,subject1) || wordCheck(foodFile,subject1) || wordCheck(fruitFile,subject1) ||
                        wordCheck(thingsFile,subject1) || wordCheck(carFile,subject1) || wordCheck(countryFile,subject1) ||
                        wordCheck(cityFile,subject1) || wordCheck(clothesFile,subject1) || wordCheck(animalFile,subject1))){
                    clientPoint += 10;
                    serverPoint += 10;
                }
            }
            serverSum += serverPoint;
            clientSum += clientPoint;
            int d = clientSumSum + clientSum;
            c += serverSumSum;
            if (check()) {
                System.out.println("your score is = " + d);
                System.out.println("computer's score is = " + c);
            }

            try {
                    otherLetter.otherLetter(nameC, lastnameC, flowerC, foodC, fruitC, thingsC, carC,
                            countryC, cityC, clothesC, animalC, gamePage, serverOrClient,
                            subjects, subjectCount, String.valueOf(rounds), clientSumSum, clientSum, c,serverSumSum,
                            letters, valueLetters, minutes, seconds, time,turn);


            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        if (serverOrClient == 1) {
            int serverPoint = 0;
            int serverSum = 0;
            int clientPoint = 0;
            int clientSum = 0;
            int c = a[0] * 10;
            for (int i = 0; i < subjects.size(); i++) {

                String subject1 = subjects.get(i);
                String subject2 = computerSubjects.get(i);
                try {
                    if (subject1.equals(subject2) && (wordCheck(nameFile,subject1) ||
                            wordCheck(lastNameFile,subject1) || wordCheck(flowerFile,subject1) ||
                            wordCheck(foodFile,subject1) || wordCheck(fruitFile,subject1) ||
                            wordCheck(thingsFile,subject1) || wordCheck(carFile,subject1) ||
                            wordCheck(countryFile,subject1) || wordCheck(cityFile,subject1) ||
                            wordCheck(clothesFile,subject1) || wordCheck(animalFile,subject1))) {
                        serverPoint += 5;
                        clientPoint += 5;
                        c -= 5;
                    }
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                if (subject1 == null) {

                }
                try {
                    if (!subject1.equals(subject2) && (wordCheck(nameFile, subject1) ||
                            wordCheck(lastNameFile, subject1) || wordCheck(flowerFile, subject1) ||
                            wordCheck(foodFile, subject1) || wordCheck(fruitFile, subject1) ||
                            wordCheck(thingsFile, subject1) || wordCheck(carFile, subject1) ||
                            wordCheck(countryFile, subject1) || wordCheck(cityFile, subject1) ||
                            wordCheck(clothesFile, subject1) || wordCheck(animalFile, subject1))) {
                        clientPoint += 10;
                        serverPoint += 10;
                    }
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
            serverSum += serverPoint;
            clientSum += clientPoint;
            int d = clientSumSum + clientSum;
            c += serverSumSum;
            try {
                if (x == subjectCount) {
                    System.out.println("your score is = " + d);
                    System.out.println("computer's score is = " + c);
                    newJabberClient.newjabberClient(firstLetter, nameC, lastnameC, flowerC, foodC, fruitC, thingsC, carC,
                            countryC, cityC, clothesC, animalC, serverOrClient, rounds, c, d, letters, valueLetters,
                            minutes, seconds, time, turn, subjectCount, subjects, computerSubjects);
                    if (rounds >= 0) {
                        System.out.println("computer's Subjects = " + computerSubjects);
                        System.out.println("your subjects = " + subjects);
                    }
                }
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        if (submit.isPressed())
            timeline.stop();

        return subjects;
    }
}
