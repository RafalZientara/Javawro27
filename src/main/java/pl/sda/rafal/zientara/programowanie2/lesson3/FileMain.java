package pl.sda.rafal.zientara.programowanie2.lesson3;

import pl.sda.rafal.zientara.programowanie2.lesson4.ByteFileComparator;

import java.io.File;
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
            return compareByBytes(png, possibleDuplicate);
        } else {
            return false;
        }
    }

    private static boolean compareByBytes(File png, File possibleDuplicate) {
        return new ByteFileComparator().compareByBytes(png, possibleDuplicate);
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
