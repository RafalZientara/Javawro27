package pl.sda.rafal.zientara.homeWork.read_file;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReadFileMain {

    public static void main(String[] args) throws FileNotFoundException {

        File file = new File("user details.txt");

        Scanner scanner = new Scanner(file);

            while(scanner.hasNext()) {
                String data = scanner.nextLine();
                System.out.println(data);

            }



    }
}
