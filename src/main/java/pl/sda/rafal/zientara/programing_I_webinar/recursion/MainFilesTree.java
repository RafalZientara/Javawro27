package pl.sda.rafal.zientara.programing_I_webinar.recursion;

import java.io.File;

public class MainFilesTree {

    public static void main(String[] args) {
        File file = new File("C:\\DATA\\data-wyszukiwanie plikow");

        System.out.println(file.getAbsolutePath());
        System.out.println(file.exists());
        System.out.println(file.isDirectory());

        TreeWindow window = new TreeWindow();
    }
}
