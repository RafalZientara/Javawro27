package pl.sda.rafal.zientara.programowanie2.lesson4.UserHW;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class SDAFileReader {

    File file = new File("user details.txt");


    public List<String> readFile() {
        List<String> data = new LinkedList<>();
        BufferedReader br;
        try {
            System.out.println(file.getAbsolutePath());
            FileReader fileReader = new FileReader(file);
            br = new BufferedReader(fileReader);
            String nextLine = br.readLine();
            while (nextLine != null) {
                data.add(nextLine);
                nextLine = br.readLine();
            }
            System.out.println("Koniec pliku");
            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}
