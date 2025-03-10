import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;

public class JabberClient {

        static final int PORT = 5014;
        public void jabberClient(String firstLetter, boolean nameC, boolean lastnameC, boolean flowerC,
                                 boolean foodC, boolean fruitC, boolean thingsC, boolean carC, boolean countryC,
                                 boolean cityC, boolean clothesC, boolean animalC, int serverOrClient, int rounds,
                                 int clientSumSum, int serverSumSum, String[] letters, int[] valueLetters, int minutes,
                                 int seconds, int time, int turn, int subjectsCount, ArrayList<String> subjects, ArrayList<String> computerSubjects) throws IOException, InterruptedException {
            PointerPage pointerPage = new PointerPage();
            GamePage gamePage = new GamePage();
            OtherLetter otherLetter = new OtherLetter();
            InetAddress inetAddress =  InetAddress.getByName(null);
            System.out.println("inetAddress = " + inetAddress);
            Socket socket = new Socket(inetAddress,JabberServer.PORT);
            System.out.println("socket = " + socket);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);

            System.out.println("firstLetter = " + firstLetter);
            out.println(nameC);
            out.println(lastnameC);
            out.println(flowerC);
            out.println(foodC);
            out.println(fruitC);
            out.println(thingsC);
            out.println(carC);
            out.println(countryC);
            out.println(cityC);
            out.println(clothesC);
            out.println(animalC);
            out.println(firstLetter);
            out.println(time);
            out.println(rounds);
            out.println(subjectsCount);
            out.println(clientSumSum);
            out.println(serverSumSum);
            out.println(minutes);
            out.println(seconds);
            out.println(time);
            out.println(turn);

            System.out.println(nameC);
            System.out.println(lastnameC);
            System.out.println(flowerC);
            System.out.println(foodC);
            System.out.println(fruitC);
            System.out.println(thingsC);
            System.out.println(carC);
            System.out.println(countryC);
            System.out.println(cityC);
            System.out.println(clothesC);
            System.out.println(animalC);
            System.out.println("firstLetter = " + firstLetter);
            System.out.println("time = " + time);
            System.out.println("rounds = " + rounds);
            System.out.println("subjectsCount = " + subjectsCount);
            System.out.println("serverOrClient = " + serverOrClient);
            System.out.println("clientSumSum = " + clientSumSum);
            System.out.println("serverSumSum = " + serverSumSum);
            System.out.println("minutes = " + minutes);
            System.out.println("seconds = " + seconds);
            System.out.println("time = " + time);
            System.out.println("turn = " + turn);

            pointerPage.pointer(firstLetter, nameC, lastnameC, flowerC, foodC,
                    fruitC, thingsC, carC, countryC,
                    cityC, clothesC, animalC, serverOrClient,subjectsCount, rounds,clientSumSum,serverSumSum,
                    letters,valueLetters,minutes,seconds,time,turn);

            int serverPoint = 0;
            int serverSum = 0;
            int clientPoint = 0;
            int clientSum = 0;
            System.out.println("clientSum = " + clientSum);


            out.println("END");
            System.out.println("Closing...");
            socket.close();
        }
}
