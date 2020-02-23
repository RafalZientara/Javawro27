package pl.sda.rafal.zientara.programowanie2.lesson4;

import java.io.*;

public class NamesMain {

    public static void main(String[] args) {

//        System.out.println(new File("").getAbsolutePath());
        String[] maleNames = downloadFile("maleNames.txt");
        String[] femaleNames = downloadFile("femaleNames.txt");

        String report = getLettersReport(maleNames, femaleNames);
        saveReportToFile(report);

    }

    public static String getLettersReport(String[] maleNames, String[] femaleNames) {
        StringBuilder builder = new StringBuilder();
        for (char currentLetter = 'A'; currentLetter <= 'Z'; currentLetter++) {
            int maleCount = getNamesWithLetterCount(maleNames, currentLetter);
            int femaleCount = getNamesWithLetterCount(femaleNames, currentLetter);
            if (maleCount > femaleCount) {
                builder.append("Wiêcej mêskich imion na literê: ").append(currentLetter);
            } else if (maleCount < femaleCount) {
                builder.append("Wiêcej ¿eñskich imion na literê: ").append(currentLetter);
            } else {
                builder.append("Tyle samo na literê: ").append(currentLetter);
            }
            if (currentLetter < 'Z') {
                builder.append(System.lineSeparator());
            }
        }
        System.out.println(builder);
        return builder.toString();
    }

    private static void saveReportToFile(String report) {
        File file = new File("report.txt");
        try {
            FileWriter writer = new FileWriter(file);
            writer.write(report);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        openTextEditor(file);
    }

    private static void openTextEditor(File file) {         //ta funkcja od razu otwiera plik
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("cmd.exe", "/c", file.getAbsolutePath());
        try {
            processBuilder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int getNamesWithLetterCount(String[] names, char firstLetter) {
        int counter = 0;
        for (String name : names) {
            if (name.charAt(0) == firstLetter) {
                counter++;
            }
        }
        return counter;
    }

    public static String[] downloadFile(String fileName) {
        String allNames = "";
        File file = new File(fileName);
        try {
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            allNames = bufferedReader.readLine();
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return getNamesInArray(allNames);
    }

    public static String[] getNamesInArray(String names) {
        return names.split(", ");
    }


}
