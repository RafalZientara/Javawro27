package programowanie2.lesson4;

import java.io.*;

public class NamesMain {

    public static void main(String[] args) {
        System.out.println(new File("").getAbsolutePath());
        String[] maleNames = getNamesFromFile("maleNames.txt");
        String[] femaleNames = getNamesFromFile("femaleNames.txt");

        StringBuilder builder = new StringBuilder();

        String report = getResultBuilder(maleNames, femaleNames, builder);
        saveReportToFIle(report);


    }

    private static void saveReportToFIle(String report) {
        File file = new File("report.txt");

        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(report);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("cmd.exe", "/c", file.getAbsolutePath());

        try {
            processBuilder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getResultBuilder(String[] maleNames, String[] femaleNames, StringBuilder builder) {
        for (char currentLetter = 'A'; currentLetter <= 'Z'; currentLetter++) {
            int maleCount = getNamesWithLetterCount(maleNames,currentLetter);
            int femaleCount = getNamesWithLetterCount(femaleNames,currentLetter);

            if (maleCount > femaleCount){
                builder.append("Wiêcej imion mêskich na litere ").append(currentLetter);
            } else if (maleCount < femaleCount) {
                builder.append("Wiêcej imion ¿eñskich na litere ").append(currentLetter);
            } else {
                if (maleCount == 0 & femaleCount == 0)
                    builder.append("Nie ma imion na literê ").append(currentLetter);
                else
                    builder.append("Wszyndzie jest tylo samo - ").append(currentLetter);
            }
            if (currentLetter < 'Z')
                builder.append(System.lineSeparator());
        }
        System.out.println(builder);
        return builder.toString();
    }

    private static int getNamesWithLetterCount(String[] names, char firstLetter) {
        int counter = 0;
        for (String name: names)
            if (name.charAt(0) == firstLetter)
                counter++;
        return counter;
    }

    private static String[] getNamesFromFile(String fileName) {
        String allNames = getFileData(fileName);
        return getNormalizedNames(allNames);
    }

    private static String getFileData(String fileName) {
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
        return allNames;
    }

    private static String[] getNormalizedNames(String allNames) {
        String[] names;
        names = allNames.split(",");

        for (int i = 0; i < names.length; i++) {
            String name = names[i];
            name = name.trim();
            System.out.println("#" + name + "#");
            names[i] = name;
        }
        return names;
    }
}
