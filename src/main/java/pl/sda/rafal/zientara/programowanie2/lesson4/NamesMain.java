package pl.sda.rafal.zientara.programowanie2.lesson4;

import java.io.*;

public class NamesMain {
    public static void main(String[] args) {
        String maleName = "maleNames.txt";
        String femaleNames = "femaleNames.txt";
        File fileMale = new File(maleName);
        File fileFemale = new File(femaleNames);

        String [] maleNameInTable = getNamesInTable(fileMale);
        String [] femaleNameInTable = getNamesInTable(fileFemale);

        String report = getResultReport(maleNameInTable, femaleNameInTable);
        saveReportToFile(report);

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
    }

    private static String getResultReport(String[] maleNameInTable, String[] femaleNameInTable) {
        StringBuilder builder = new StringBuilder();
        for (char i = 'A'; i<='Z'; i++) {
            char currentLetter = i;
            int maleCounter = getNamesWithLetterCount(currentLetter, maleNameInTable);
            int femaleCounter = getNamesWithLetterCount(currentLetter, femaleNameInTable);
            if (maleCounter > femaleCounter) {
                builder.append("Wiêcej mêskich imion na literê: ").append(currentLetter);
            } else if (maleCounter < femaleCounter) {
                builder.append("Wiêcej ¿eñskich imion na literê: ").append(currentLetter);
            } else if (maleCounter!=0){
                builder.append("Tyle samo mêskich i ¿eñskich imion na literê: ").append(currentLetter);
            } else {
                builder.append("Nie ma imion na literê: ").append(currentLetter);
            }
            if(i<'Z') {
                builder.append("\n");
            }
        }
        System.out.println(builder);
        return builder.toString();
    }

    private static String[] getNamesInTable(File file) {
        String [] NamesInTable;
        try {
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String allMaleNames = bufferedReader.readLine();
            bufferedReader.close();

            NamesInTable = getSplitNames(allMaleNames);
            return NamesInTable;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String[] getSplitNames(String allNames) {
        String [] allNamesInTable = allNames.split(",");
        for (int i = 0; i < allNamesInTable.length; i++) {
            allNamesInTable[i] = allNamesInTable[i].trim(); //trim = usuwa niepotrzebne spacje z pocz¹tku i koñca
        }
        return allNamesInTable;
    }

    private static int getNamesWithLetterCount(char letter, String[] names) {
        int count=0;
        for (int i = 0; i < names.length; i++) {
            if(names[i].toUpperCase().charAt(0) == letter){
                count++;
        }
    }
        return count;
}
}
