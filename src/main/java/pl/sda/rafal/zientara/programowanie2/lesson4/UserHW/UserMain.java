package pl.sda.rafal.zientara.programowanie2.lesson4.UserHW;

public class UserMain {

    public static void main(String[] args) {

        UserDetailsStorage userDetailsStorage = new UserDetailsStorage();

        userDetailsStorage.fileImport();
        System.out.println(userDetailsStorage.getDataFromFile().toString());

    }

}
