package pl.sda.rafal.zientara.programowanie2.lesson4;

import pl.sda.rafal.zientara.programowanie2.lesson3.FileMain;

import java.io.File;

public class DuplicateMain {

    public static void main(String[] args) {
        String path1 = "";
        String path2 = "";

        File file1 = new File(path1);
        File file2 = new File(path2);

        boolean duplicate = FileMain.isDuplicate(file1,file2);
        System.out.println(duplicate);
    }
}
