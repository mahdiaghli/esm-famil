import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ExitPage {
    public void exitPage() {
        Stage exitStage = new Stage();
        VBox vBox = new VBox();
        Text text = new Text("THE END!");
        Text text2 = new Text("hope you enjoyed!");
        vBox.getChildren().addAll(text,text2);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(20);
        vBox.setPadding(new Insets(20,20,20,20));

        text.setStyle("-fx-background-color:blue;-fx-text-fill:white;-fx-front:normal bold 30px 'serif'");
        text2.setStyle("-fx-background-color:orange;-fx-text-fill:white;-fx-front:normal bold 20px 'serif'");
        vBox.setBackground(new Background(new BackgroundFill(Color.DARKORANGE, CornerRadii.EMPTY, Insets.EMPTY)));
        Scene scene = new Scene(vBox, 400, 400);
        exitStage.setTitle("Time");
        exitStage.setScene(scene);
        exitStage.show();
    }
}
