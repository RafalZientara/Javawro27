package homework.UserDetails;

import java.io.File;

public class MainUserDetails {
    public static void main(String[] args) {
        UserDetails userDetails = new UserDetails();
        File file = new File("user details.txt");
        userDetails.updateUserDetails(file);

        UserDetailsWindow userDetailsWindow = new UserDetailsWindow(userDetails);

    }
}
