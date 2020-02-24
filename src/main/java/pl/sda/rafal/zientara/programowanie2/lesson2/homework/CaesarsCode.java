package pl.sda.rafal.zientara.programowanie2.lesson2.homework;

public class CaesarsCode {

    public static String encrypt(String plainText, int step) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < plainText.length(); i++) {
            int index = (i + step) % plainText.length();
            builder.append(plainText.charAt(index));
        }
        return builder.toString();
    }

    public static String decrypt(String secret, int step) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < secret.length(); i++) {
            int index = i - step;
            if (index < 0) {
                index = secret.length() + index;
            }
            builder.append(secret.charAt(index));
        }
        return builder.toString();
    }

    public static void main(String[] args) {

        String sent = "Ptaki latajo kluczem";
        System.out.println(encrypt(sent, 3));
        String encryptedSent = "ki latajo kluczemPta";
        System.out.println(decrypt(encryptedSent, 3));


    }
}
