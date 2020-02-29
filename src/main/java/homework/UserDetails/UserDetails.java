package homework.UserDetails;

import java.io.*;

public class UserDetails {
    private String name = "";
    private String age = "";
    private String email = "";
    private String phone = "";

    public UserDetails() {
    }

    public void updateUserDetails(File file){
        FileReader fileReader = null;
        try {
            clearFields();
            fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String data = bufferedReader.readLine();
            getData(bufferedReader, data);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void clearFields() {
        name = "";
        age = "";
        email = "";
        phone = "";
    }

    private void getData(BufferedReader bufferedReader, String data) throws IOException {
        while (data != null){
            if (data.contains("Name:")){
                for (int i = 6; i < data.length(); i++) {
                    name+= data.charAt(i);
                }
            } else if (data.contains("Age:")){
                for (int i = 5; i < data.length(); i++) {
                    age+= data.charAt(i);
                }
            } else if (data.contains("Email:")){
                for (int i = 7; i < data.length(); i++) {
                    email+= data.charAt(i);
                }
            } else if (data.contains("Phone:")){
                for (int i = 7; i < data.length(); i++) {
                    phone+= data.charAt(i);
                }
            } else {
                System.out.println("Nie wiem gdzie przydzieliæ czytan¹ informacjê: " + data);
            }
            data = bufferedReader.readLine();
        }
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return "Name: " + name + '\n' + "Age: " + age + "\n" + "Email: " + email + '\n' + "Phone: " + phone;
    }
}
