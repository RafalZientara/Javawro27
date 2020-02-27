package pl.sda.rafal.zientara.programowanie2.lesson4.userDetails;

import java.io.*;
import java.util.Formatter;

public class UserDetails {
    private String name;
    private int age;
    private String email;
    private int phoneNumber;

    @Override
    public String toString() {
        Formatter formatter = new Formatter();
        formatter.format("Name: %s%nAge: %d%nEmail: %s%nPhone: %d",
                getName(), getAge(), getEmail(), getPhoneNumber());
        return formatter.toString();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void readFile(String fileName) {
        StringBuilder details = new StringBuilder();
        try {
            FileReader reader = new FileReader(fileName + ".txt");
            BufferedReader br = new BufferedReader(reader);
            String line = br.readLine();
            while (line != null) {
                details.append(line).append(",");
                line = br.readLine();
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        setAllDetails(details.toString());
    }

    private void setAllDetails(String details) {
        String[] detail = details.split(",");
        setName(detail[0].split(" ")[1]);
        setAge(Integer.parseInt(detail[1].split(" ")[1]));
        setEmail(detail[2].split(" ")[1]);
        setPhoneNumber(Integer.parseInt(detail[3].split(" ")[1]));
    }
}

