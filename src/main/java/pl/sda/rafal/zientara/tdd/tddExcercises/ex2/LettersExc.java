package pl.sda.rafal.zientara.tdd.tddExcercises.ex2;

public class LettersExc {

    public static String firstLetterToUpperCase(String text){
        if(text != null) {
            if(text.length()>0) {
                String[] textArr = text.split("");
                if (Character.isLetter(textArr[0].charAt(0))) textArr[0] = textArr[0].toUpperCase();
                StringBuilder textBuilder = new StringBuilder(textArr[0]);
                for (int i = 1; i < textArr.length; i++) {
                    if (!Character.isLetter(textArr[i - 1].charAt(0)) && Character.isLetter(textArr[i].charAt(0)))
                        textArr[i] = textArr[i].toUpperCase();
                    textBuilder.append(textArr[i]);
                }
                text = textBuilder.toString();
            }
            return text;
        }
        return  null;
    }
}
