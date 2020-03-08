package pl.sda.rafal.zientara.programowanie2.lesson4;

import javax.jws.soap.SOAPBinding;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class UsersDetail {
    private String name;
    private int age;
    private String email;
    private int phoneNumber;

    public UsersDetail(String name, int age, String email, int phoneNumber) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public UsersDetail() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public static UsersDetail printUserDetail(File file){
        UsersDetail usersDetail= new UsersDetail();
        try {
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String [] name = bufferedReader.readLine().split(": ");
            usersDetail.setName(name[1]);
            String [] age = bufferedReader.readLine().split(": ");
            usersDetail.setAge(Integer.parseInt(age[1]));
            String [] email = bufferedReader.readLine().split(": ");
            usersDetail.setEmail(email[1]);
            String [] phone = bufferedReader.readLine().split(": ");
            String allMaleNames = bufferedReader.readLine();
            usersDetail.setPhoneNumber(Integer.parseInt(phone[1]));
            bufferedReader.close();

            return usersDetail;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String toString() {
        return  "name: " + name + '\n' +
                "age: " + age + '\n' +
                "email: " + email + '\n' +
                "phoneNumber: " + phoneNumber;
    }

    public static void main(String[] args) {
//        BufferedReader br;
//        try {
//            br = new BufferedReader(new FileReader("user_details.txt"));
//            String nextLine = br.readLine();
//            System.out.println(nextLine);
//            while (nextLine != null)
//                System.out.println(nextLine = br.readLine());
//            br.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        System.out.println(printUserDetail(new File("user_details.txt")));
    }
}
