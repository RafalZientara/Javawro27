package pl.sda.rafal.zientara.tdd.programowanie2.lesson3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class FilesMMain {

    private static  int counter = 0;
    private static long sizeCounter = 0;

    public static void main(String[] args) {
        File file = new File("C:\\Users\\matni\\IdeaProjects\\DATA");
        System.out.println(file.getAbsolutePath());
        System.out.println(file.getPath());
        System.out.println("exists: " + file.exists());
        System.out.println("parent: " + file.getParent());
        System.out.println("isDirectory: " + file.isDirectory());
        System.out.println("isFile: " + file.isFile());
        List<File> allFiles = new ArrayList<>();
        addSubFiles(file, allFiles);
        System.out.println("===== FINAL RESULT =====");
        List<File> pngFiles = allFiles.stream()
                .filter(f -> f.getName().endsWith(".png"))
                .collect(Collectors.toList());
        System.out.println("Number of files found: " + allFiles.size());
        for(File png : pngFiles) {
            System.out.println("Checking duplicates for " + png);
            List<File> duplicates;
            duplicates = getDuplicates(png, pngFiles);
            System.out.println("Duplicates: " + duplicates);
            deleteDuplicates(duplicates);
        }
        System.out.println("usunięto: "+counter+" plików");
        System.out.println("zwolniono: "+sizeCounter);
    }

    private  static  boolean compareByBytes( File png, File possibleDuplicate){
        try {
            FileReader reader1 = new FileReader(png);
            FileReader reader2 = new FileReader(possibleDuplicate);

            int read1;
            int read2;

            do {
                char[] buffer1 = new char[1024];
                char[] buffer2 = new char[1024];
                read1 = reader1.read();
                read2 = reader2.read();

                if (read1 != read2) {
                    System.out.println("Inne! "+read1+" != "+read2);
                    reader1.close();
                    reader2.close();
                    return false;

                }else{

                }
            } while (read1 != -1 && read2 != -1);

            reader1.close();
            reader2.close();
            return true;

        }catch(FileNotFoundException e1){
            System.out.println("Nie ma takiego pliku");
            e1.printStackTrace();
        } catch (IOException e2) {
            System.out.println("Błąd odczytu");
            e2.printStackTrace();
        }
        return false;
    }



    private static void deleteDuplicates(List<File> duplicates) {

        for (File dup: duplicates ) {
            long size = dup.length();
            String name = dup.getName();
            boolean delete = dup.delete();
            if(delete){
                counter++;
                sizeCounter += size;
                System.out.println("zwolniono >> " +size+" >> "+name);
            }else{
                System.out.println("nie udało się usunąć >> "+name);
            }
        }
    }

    private static List<File> getDuplicates(File png, List<File> pngFiles) {
        List<File> duplicates = new ArrayList<>();
        for (File possibleDuplicate : pngFiles) {
            if(isDuplicate(png,possibleDuplicate)) {
                duplicates.add(possibleDuplicate);
            }
        }
        return duplicates;
    }

    public static boolean isDuplicate(File png, File possibleDuplicate) {
        if(png.getAbsolutePath().equals(possibleDuplicate.getAbsolutePath())) {
            return false;
        } if (png.length() == possibleDuplicate.length()){
            return compareByBytes(png, possibleDuplicate);
        } else
            return false;
    }

    private static void printFilteredFiles(List<File> allFiles) {
        System.out.println("===== FINAL RESULT =====");
        for(File f : allFiles.stream()
                .filter(fil -> fil.getName().endsWith(".png"))
                .collect(Collectors.toList())) {
            System.out.println(f);
        }
    }
    private static void addSubFiles(File file, List<File> allFiles) {

        File[] files = file.listFiles();
        if (files != null) {
            System.out.println("===== SUB_FILES =====");
            for(File subFile : files) {
                System.out.println(subFile);
                if(subFile.isFile()) {
                    allFiles.add(subFile);
                } else if (subFile.isDirectory()) {
                    addSubFiles(subFile, allFiles);
                }
            }
        } else {
            System.out.println("No subfiles for " + file.getName());
        }
    }
}