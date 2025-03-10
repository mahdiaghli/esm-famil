import java.util.ArrayList;

public class PointerPage {


    public void pointer(String firstLetter, boolean name, boolean lastname, boolean flower, boolean food,
                        boolean fruit, boolean things, boolean car, boolean country, boolean city,
                        boolean clothes, boolean animal, int serverOrClient,int subjectCount, int rounds, int clientSumSum,
                        int serverSumSum, String[] letters, int[] valueLetters, int minutes, int seconds,
                        int time, int turn) {
        GamePage gamePage = new GamePage();
        gamePage.boardGame(firstLetter, name, lastname, flower, food, fruit, things, car,
                country, city, clothes, animal, 2,subjectCount, 0, 0, 0,
                letters, valueLetters, minutes, seconds, time, turn);
    }
}
