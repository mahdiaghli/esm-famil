import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;

public class NewJabberClient {

    static final int PORT = 5014;
    public void newjabberClient(String firstLetter, boolean nameC, boolean lastnameC, boolean flowerC,
                             boolean foodC, boolean fruitC, boolean thingsC, boolean carC, boolean countryC,
                             boolean cityC, boolean clothesC, boolean animalC, int serverOrClient, int rounds,
                             int clientSumSum, int serverSumSum, String[] letters, int[] valueLetters, int minutes,
                             int seconds, int time, int turn, int subjectsCount, ArrayList<String> subjects, ArrayList<String> computerSubjects) throws IOException, InterruptedException {
        PointerPage pointerPage = new PointerPage();
        GamePage gamePage = new GamePage();
        OtherLetter otherLetter = new OtherLetter();
        InetAddress inetAddress = InetAddress.getByName(null);
        System.out.println("inetAddress = " + inetAddress);
        Socket socket = new Socket(inetAddress, JabberServer.PORT);
        System.out.println("socket = " + socket);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);

        int size = subjects.size();
        int computerSize = computerSubjects.size();
        out.println(size);
        out.println(computerSize);
        for (int i = 0; i < size; i++) {
            out.println(subjects.get(i));
        }
        for (int i = 0; i < computerSize; i++) {
            out.println(computerSubjects.get(i));
        }

        System.out.println(size);
        System.out.println(computerSize);
        for (int i = 0; i < size; i++) {
            System.out.println(subjects.get(i));
        }
        for (int i = 0; i < computerSize; i++) {
            System.out.println(computerSubjects.get(i));
        }

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
