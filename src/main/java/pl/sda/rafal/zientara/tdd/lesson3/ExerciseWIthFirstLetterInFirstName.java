package pl.sda.rafal.zientara.tdd.lesson3;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExerciseWIthFirstLetterInFirstName {

    public static void main(String[] args) {
        StringBuilder result = getResult();
        writeAFile(result.toString());
    }

    private static void split() {
        File file = new File("zadanie dodatkowe.txt");
        String loaded="";
        try {
            loaded = loadFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Pattern pattern = Pattern.compile(":_");
        Matcher matcher = pattern.matcher(loaded);

    }

    private static StringBuilder getResult() {
        int startLetter = 'A';
        int endLetter = 'Z';
        StringBuilder result = new StringBuilder();
        for (int i = startLetter; i <=endLetter ; i++) {
            char letter = (char)i;
            String a = String.valueOf(letter);
            if (i=='A') result.append(checker(a));
            else result.append(System.lineSeparator()).append(checker(a));
        }
        return result;
    }

    public static String loadFile(File file) throws IOException {
        FileReader reader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(reader);
        return bufferedReader.readLine();
    }

    private static void writeAFile(String string) {
        File file = new File("report.txt");
        FileWriter writer;
        try {
            writer = new FileWriter(file);
        writer.write(string);
        writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        ProcessBuilder process = new ProcessBuilder();
        process.command("cmd.exe", "/c", file.getAbsolutePath());
        try {
            process.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static int printNames(String[] string, String startLetter) {
        int counter = 0;
        for (String n : string) {
            if (n.startsWith(startLetter)) {
                counter++;
            }
            System.out.print(n + " ");
        }
        return counter;
    }

    private static String checker(String startLetter) {
        File maleFile = new File("maleNames.txt");
        File femaleFile = new File("femaleNames.txt");
        String result = "Litera "+startLetter+": ";
        try {
            String[] maleNames = loadFile(maleFile).trim().split(", ");
            String[] femaleNames = loadFile(femaleFile).trim().split(", ");
            int maleCounter = printNames(maleNames, startLetter);
            System.out.println();
            int femaleCounter = printNames(femaleNames, startLetter);
            System.out.println();
            if (maleCounter > femaleCounter) result += "Wiêcej jest imion mêskich";
            else if (maleCounter < femaleCounter) result += "Wiêcej jest imion ¿eñskich";
            else if (maleCounter==0 && femaleCounter ==0) result+="Brak imion na podan¹ literê";
            else result += "Tyle samo jest imion mêskich i ¿eñskich";
            System.out.println("Meskie imiona na " + startLetter + " " + maleCounter + "\n¯eñskie imiona na " + startLetter + " " + femaleCounter);
            System.out.println(result);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}