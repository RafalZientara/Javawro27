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
        System.out.println(file.getAbsolutePath()); //pełna ścieżka do pliku
        System.out.println(file.getPath());
        System.out.println("exist: "+file.exists()); //sprawdzenie czy istnieje
        System.out.println("parent: "+file.getParent());
        System.out.println("isDirectory: "+file.isDirectory()); //sprawdzenie czy jest folderem
        System.out.println("isFile: "+file.isFile());

        List<File> allFiles = new ArrayList<>();

        addSubFiles(file, allFiles);
        //printFilteredFiles(allFiles);
        System.out.println("---------Final result-----------");
    List<File> pngFiles = allFiles.stream()
                .filter(fil->fil.getName().endsWith(".png")) //albo np "png"
                .collect(Collectors.toList());
    for (File png : pngFiles){
        System.out.println("Checking duplicates for "+ png);
        List <File> duplicates = getDuplicates (png, pngFiles);
        System.out.println("Duplicates: "+ duplicates);
    }

    }

    private static List<File> getDuplicates(File png, List<File> pngFiles) {
        List <File> duplicates = new ArrayList<>();
        for (File possibleDuplicate :pngFiles){
            if (isDuplicate(png, possibleDuplicate)){
                duplicates.add(possibleDuplicate);
            }
        }
        return null;
    }

    public static boolean isDuplicate(File png, File possibleDuplicate) {
        if (png.getAbsolutePath().equals(possibleDuplicate.getAbsolutePath())){
            return false;
        }
        //te same rozmiary pliku:
        if (png.length()==possibleDuplicate.length()) { //length, to rozmiar pliku
            return compareByBytes(png, possibleDuplicate);
        } else {
            return false;
        }
    }

    private static boolean compareByBytes(File png, File possibleDuplicate) {
        try {
            FileReader reader1 = new FileReader(png);
            FileReader reader2 = new FileReader(possibleDuplicate);

            char [] buffer1 = new char[1024];
            char [] buffer2 = new char[1024];
            int read1;
            int read2;
            do{
                read1 = reader1.read(buffer1);
                read2 = reader2.read(buffer2);
                if (read1!=read2){
                    System.out.println("Inne "+read1 + " != "+ read2);
                    reader1.close();
                    reader2.close();
                    return false;
                } else {
                    for (int i = 0; i < buffer1.length; i++) {
                        char c1 = buffer1[i];
                        char c2 = buffer2[i];
                        if (c1 != c2){
                            System.out.println("Inne "+read1 + " != "+ read2);
                            reader1.close();
                            reader2.close();
                            return false;
                        }
                    }
                }
            }
            while (read1!=-1 && read2!=-1);
            return true;

        } catch (FileNotFoundException e) {
            System.out.println("Nie ma takiego pliku");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Błąd odczytu");
            e.printStackTrace();
        }
        return false;
    }

    private static void printFilteredFiles(List<File> allFiles) {
        System.out.println("---------Final result-----------");
        List <File> pngFiles = allFiles.stream()
                .filter(fil->fil.getName().endsWith(".txt")) //albo np "png"
                .collect(Collectors.toList());


        for(File png : pngFiles){
            List<File> duplicates = getDuplicates(png,pngFiles);
            System.out.println("duplicates: "+ duplicates);
            deleteDuplicates(duplicates);
        }
    }

    private static void deleteDuplicates(List<File> duplicates){
        for(File dup : duplicates){
            long size = dup.length();
            boolean delete = dup.delete();
            if (delete){
                System.out.println("zwolniono: " + size);
            } else {
                System.out.println("nie udało się.");
            }
        }
    }

    private static void addSubFiles(File file, List<File> allFiles) {
        File[] files = file.listFiles(); //daje listę wszystkich plików, które są w środku
        if (files!=null) {
            System.out.println("---------SUB FILES----------");
            for (File subFile : files) {
                System.out.println(subFile);
                if(subFile.isFile()){
                    allFiles.add(subFile);
                } else if (subFile.isDirectory()){
                    addSubFiles(subFile,allFiles);
                }
            }
        } else {
            System.out.println("No sub files for: " + file.getName());
        }
    }
}
