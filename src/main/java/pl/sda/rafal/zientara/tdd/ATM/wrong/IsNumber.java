package pl.sda.rafal.zientara.tdd.ATM.wrong;

public class IsNumber {

        public static boolean isNumeric(String value) {
            try {
                Integer.parseInt(value.trim());
                return true;
            } catch (NumberFormatException ex) {
                return false;
            }
        }
}
