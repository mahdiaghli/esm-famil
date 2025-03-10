import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class JabberServer {
    static final int PORT = 5014;
    public void jabberServer(ArrayList<String> subjects,int subjectsCount,int serverOrClient) throws IOException {
        PointerPage pointerPage = new PointerPage();
        int PORT = 5014;
        final ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println("serverSocket = " + serverSocket);
        Socket socket = serverSocket.accept();
        System.out.println("Connection Accepted" + socket);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);
        InetAddress inetAddress =  InetAddress.getByName(null);
        System.out.println("inetAddress = " + inetAddress);

        boolean name = Boolean.parseBoolean(in.readLine());
        boolean lastname = Boolean.parseBoolean(in.readLine());
        boolean flower = Boolean.parseBoolean(in.readLine());
        boolean food = Boolean.parseBoolean(in.readLine());
        boolean fruit = Boolean.parseBoolean(in.readLine());
        boolean things = Boolean.parseBoolean(in.readLine());
        boolean car = Boolean.parseBoolean(in.readLine());
        boolean country = Boolean.parseBoolean(in.readLine());
        boolean city = Boolean.parseBoolean(in.readLine());
        boolean clothes = Boolean.parseBoolean(in.readLine());
        boolean animal = Boolean.parseBoolean(in.readLine());
        String firstLetter = in.readLine();
        String Time = in.readLine();
        int rounds = Integer.parseInt(in.readLine());
        subjectsCount = Integer.parseInt(in.readLine());
        int clientSumSum = Integer.parseInt(in.readLine());
        int serverSumSum = Integer.parseInt(in.readLine());
        int minutes = Integer.parseInt(in.readLine());
        int seconds = Integer.parseInt(in.readLine());
        int time = Integer.parseInt(in.readLine());
        int turn = Integer.parseInt(in.readLine());

        System.out.println("Echoing... " + name);
        System.out.println("Echoing... " + lastname);
        System.out.println("Echoing... " + flower);
        System.out.println("Echoing... " + food);
        System.out.println("Echoing... " + fruit);
        System.out.println("Echoing... " + things);
        System.out.println("Echoing... " + car);
        System.out.println("Echoing... " + country);
        System.out.println("Echoing... " + city);
        System.out.println("Echoing... " + clothes);
        System.out.println("Echoing... " + animal);
        System.out.println("Echoing... " + "firstLetter" + firstLetter);
        System.out.println("Echoing... " + "Time" + Time);
        System.out.println("Echoing... " + "rounds" + rounds);
        System.out.println("Echoing... " + "subjectsCount" + subjectsCount);
        System.out.println("Echoing... " + "clientSumSum = " + clientSumSum);
        System.out.println("Echoing... " + "serverSumSum = " + serverSumSum);
        System.out.println("Echoing... " + "minutes = " + minutes);
        System.out.println("Echoing... " + "seconds = " + seconds);
        System.out.println("Echoing... " + "time = " + time);
        System.out.println("Echoing... " + "turn = " + turn);

        System.out.println("serverOrClient = " + serverOrClient);

        int sw = 0;
        ArrayList<String> subjects3 = null;



        String[] letters = new String[]{"ا", "ب", "پ", "ت", "ث", "ج", "چ", "ح", "خ", "د", "ذ", "ر", "ز",
                "ژ", "س", "ش", "ص", "ض", "ط", "ظ", "ع", "غ", "ف", "ق", "ک", "گ",
                "ل", "م", "ن", "و", "ه", "ی"};
        int[] valueLetters = new int[32];
        for (int i = 0; i < 32; i++) {
            valueLetters[i] = 1;
        }


        pointerPage.pointer(firstLetter, name, lastname, flower, food,
                fruit, things, car, country,
                city, clothes, animal, serverOrClient,subjectsCount, rounds,clientSumSum,serverSumSum,
                letters,valueLetters,minutes,seconds,time,turn);

        System.out.println("subjects = " + subjects);

    }
}
