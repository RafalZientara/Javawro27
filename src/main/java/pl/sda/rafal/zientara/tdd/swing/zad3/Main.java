package pl.sda.rafal.zientara.tdd.swing.zad3;

public class Main {

    public static void main(String[] args) {
        LoginWindow loginWindow = new LoginWindow();
        loginWindow.setListener(new LoginWindow.LoginListener() {
            @Override
            public void onLoginClicked(String password) {
                System.out.println(password);
                if("okon".equals(password)) {
                    //prawidlowe
                    loginWindow.hide();
                    new TopSecretWindow();
                } else {
                    new WrongPasswordDialog(loginWindow.getFrame());
                }
            }
        });
    }
}
