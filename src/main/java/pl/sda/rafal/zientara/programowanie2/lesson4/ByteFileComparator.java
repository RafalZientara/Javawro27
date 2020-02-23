package pl.sda.rafal.zientara.programowanie2.lesson4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ByteFileComparator {
    private final static char[] buffer1 = new char[1024];
    private final static char[] buffer2 = new char[1024];
    private int read1;
    private int read2;
    private FileReader reader1;
    private FileReader reader2;

    public boolean compareByBytes(File png, File possibleDuplicate) {
        try {
            reader1 = new FileReader(png);
            reader2 = new FileReader(possibleDuplicate);
            do {
                read1 = reader1.read(buffer1);
                read2 = reader2.read(buffer2);
                if (read1 != read2) {
                    reader1.close();
                    reader2.close();
                    return false;
                }
                else {
                    for (int i = 0; i < buffer1.length; i++) {
                        char c1 = buffer1[i];
                        char c2 = buffer2[i];
                        if (c1 != c2) {
                            System.out.println("Inne! " + read1 + "!=" + read2);
                            reader1.close();
                            reader2.close();
                            long end = System.currentTimeMillis();
                            long diff;
                            System.out.println("Nie duplikat! Zmarnowałeś [ms] ");
                            return false;
                        }
                    }
                }
            }
            while (read1 != -1 && read2 != -1);
            System.out.println("Inne! " + read1 + "!=" + read2);
            reader1.close();
            reader2.close();
            return true;
        } catch (FileNotFoundException e) {
            System.out.println("Nie ma takiego pliku!");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Błąd odczytu!");
            e.printStackTrace();
        }
        return true;
    }

}
