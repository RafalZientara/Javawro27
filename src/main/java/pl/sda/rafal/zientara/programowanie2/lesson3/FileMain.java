package pl.sda.rafal.zientara.programowanie2.lesson3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FileMain {

    public static void main(String[] args) {
        File file = new File("C:\\DATA\\");
        System.out.println(file.getAbsoluteFile());
        System.out.println("exists: " + file.exists());
        System.out.println("parent: " + file.getParent());
        System.out.println("isDirectory: " + file.isDirectory());
        System.out.println("isFile: " + file.isFile());

        List<File> allFiles = new ArrayList<>();
        addSubFiles(file, allFiles);
        printFilteredFiles(allFiles);

        System.out.println("================FINAL_RESULT================");
        List<File> pngFiles = allFiles.stream()
                .filter(file1 -> file1.getName().endsWith(".png"))
                .collect(Collectors.toList());

        killDuplicates(pngFiles);
    }

    public static void killDuplicates(List<File> allFiles) {
        for (File png : allFiles) {
            System.out.println("checking duplicates for " + png);
            List<File> duplicates = getDuplicates(png, allFiles);
            System.out.println("Duplicates: " + duplicates);
            for (File dup : duplicates) {
                allFiles.remove(dup);
            }
            //todo znajdź inny sposób na unikanie powtórnego sprawdzenia
            deleteDuplicates(duplicates);
        }
    }

    private static void deleteDuplicates(List<File> duplicates) {
        for (File dup : duplicates) {
            boolean delete = dup.delete();
            if (delete) {
                System.out.println("zwolniono " + duplicates.size());
            } else {
                System.out.println("Nie udało się usunąć!");
            }
        }
    }

    private static boolean compareByBytes(File png, File possibleDuplicate) {
        try {
            FileReader reader1 = new FileReader(png);
            FileReader reader2 = new FileReader(possibleDuplicate);
            int read1;
            int read2;
            do {
                read1 = reader1.read();
                read2 = reader2.read();
                if (read1 != read2) {
                    reader1.close();
                    reader2.close();
                    return false;
                }
            }
            while (read1 != -1 && read2 != -1);
            System.out.println("Inne! " + read1 + "!=" + read2);
            reader1.close();
            reader2.close();
        } catch (FileNotFoundException e) {
            System.out.println("Nie ma takiego pliku!");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Błąd odczytu!");
            e.printStackTrace();
        }
        return true;
    }

    private static List<File> getDuplicates(File png, List<File> pngFiles) {
        List<File> duplicates = new ArrayList<>();
        for (File possibleDuplicates : pngFiles) {
            if (isDuplicate(png, possibleDuplicates)) {
                duplicates.add(possibleDuplicates);
            }
        }
        return duplicates;
    }

    private static boolean isDuplicate(File png, File possibleDuplicates) {
        if (png.getAbsoluteFile().equals(possibleDuplicates.getAbsoluteFile())) {
            return false;
        }
        if (png.length() == possibleDuplicates.length()) {
            return compareByBytes(png,possibleDuplicates);
        }
        return false;
    }

    public static void printFilteredFiles(List<File> allFiles) {            //wyszukiwanie po rozszerzeniu
        System.out.println("================FINAL_RESULT================");
        for (File f : allFiles.stream()
                .filter(file1 -> file1.getName().endsWith(".png"))
                .collect(Collectors.toList())) {
            System.out.println(f);
        }
    }


    public static void addSubFiles(File file, List<File> allFiles) {
        File[] files = file.listFiles();
        if (files != null) {
            System.out.println("================SUB_FILES===============");
            for (File subFile : files) {
                System.out.println(subFile);
                if (subFile.isFile()) {
                    allFiles.add(subFile);
                } else if (subFile.isDirectory()) {
                    addSubFiles(subFile, allFiles);
                }
            }
        } else {
            System.out.println("No sub files for " + file.getName());
        }
    }
}
