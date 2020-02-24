package programowanie2.lesson3;

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
        System.out.println(file.getAbsolutePath());
        System.out.println(file.getPath());
        System.out.println("exists:" + file.exists());

//        if(!file.exists()) {
//            boolean isSuccess = file.createNewFile();
//        }

        System.out.println("parent:" + file.getParent());

        System.out.println("isDirectory:" + file.isDirectory());
        System.out.println("isFile:" + file.isFile());


        List<File> allFiles = new ArrayList<>();

        addSubFiles(file, allFiles);

//        printFilteredFiles(allFiles);
        System.out.println("===================== FINAL RESULT =====================");
        for (File f : allFiles) {
            System.out.println(f);
        }
//        List<File> pngFiles = allFiles.stream()
//                .filter(fil -> fil.getName().endsWith(".png"))
//                .collect(Collectors.toList());

        killDuplicates(allFiles);
    }

    private static void killDuplicates(List<File> allFiles) {
        for (File png : allFiles) {
            System.out.println("Checking duplicates for " + png);
            List<File> duplicates = getDuplicates(png, allFiles);
            System.out.println("Duplicates:" + duplicates);
            //usun z listy plikow aby nie sprawdzac jeszcze raz dla tego samego
//            for (File dup : duplicates) {
//                allFiles.remove(dup);
//            }//todo znajdz inny sposob na unikanie powtornego sprawdenia
            deleteDuplicates(duplicates);
        }
    }

    private static void deleteDuplicates(List<File> duplicates) {
        for (File dup : duplicates) {
            long size = dup.length();
            boolean delete = dup.delete();
            if (delete) {
                System.out.println("zwolniono " + size);
            } else {
                System.out.println("Nie udalo sie usunac");
            }
        }
    }

    private static List<File> getDuplicates(File png, List<File> pngFiles) {
        List<File> duplicates = new ArrayList<>();
        for (File possibleDuplicate : pngFiles) {
            if (isDuplicate(png, possibleDuplicate)) {
                duplicates.add(possibleDuplicate);
            }
        }
        return duplicates;
    }

    public static boolean isDuplicate(File png, File possibleDuplicate) {
        //C:\DATA\data-wyszukiwanie plikow\subfolder3-editpng\inna nazwa xD.png
        //C:\DATA\data-wyszukiwanie plikow\subfolder3-editpng\inna nazwa xD.png
        //to jestem ja!
        if (png.getAbsolutePath().equals(possibleDuplicate.getAbsolutePath())) {
            return false;
        }
        //te same rozmiary pliku
        if (png.length() == possibleDuplicate.length()) {
            //todo porownaj pliki bajt po bajcie
            return compareByBytes(png, possibleDuplicate);
        } else {
            return false;
        }
    }

    private static boolean compareByBytes(File png, File possibleDuplicate) {
        try {

            long startTime = System.currentTimeMillis();
            FileReader reader1 = new FileReader(png);
            FileReader reader2 = new FileReader(possibleDuplicate);

            char[] buffer1 = new char[1024];
            char[] buffer2 = new char[1024];
            int read1;
            int read2;
            do  {
                read1 = reader1.read(buffer1);
                read2 = reader2.read(buffer2);
                if (read1 != read2) {
                    System.out.println("Inne! " + read1 + "!=" + read2);
                    reader1.close();
                    reader2.close();

                    long end = System.currentTimeMillis();
                    long diff = end - startTime;
                    System.out.println("Nie duplikat! Zmarnowałem [ms] " + diff);
                    return false;
                    //plik1:11111111
                    //plik2:11111101
                } else {
                    for (int i = 0; i < buffer1.length; i++) {
                        char c1 = buffer1[i];
                        char c2 = buffer2[i];
                        if(c1 != c2){
                            System.out.println("Inne! " + read1 + "!=" + read2);
                            reader1.close();
                            reader2.close();

                            long end = System.currentTimeMillis();
                            long diff = end - startTime;
                            System.out.println("Nie duplikat! Zmarnowałem [ms] " + diff);
                            return false;
                        }
                    }
                }
            } while (read1 != -1 && read2 != -1);

            //todo sprawdz jeszcze raz read1 i read2
            reader1.close();
            reader2.close();

            long end = System.currentTimeMillis();
            long diff = end - startTime;
            System.out.println("Duplikat! Znaleziono po [ms] " + diff);

            return true;
        } catch (FileNotFoundException e) {
            System.out.println("Nie ma takiego pliku!");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Błąd odczytu!");
            e.printStackTrace();
        }

        return false;
    }

    private static void printFilteredFiles(List<File> allFiles) {
        System.out.println("===================== FINAL RESULT =====================");
        for (File f : allFiles.stream()
                .filter(fil -> fil.getName().endsWith(".png"))
                .collect(Collectors.toList())) {
            System.out.println(f);
        }
    }

    private static void addSubFiles(File file, List<File> allFiles) {
        File[] files = file.listFiles();
        if (files != null) {
            System.out.println("============SUB_FILES============");
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
