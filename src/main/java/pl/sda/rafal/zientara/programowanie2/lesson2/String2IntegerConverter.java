package pl.sda.rafal.zientara.programowanie2.lesson2;

import com.sun.javafx.scene.layout.region.Margins;

public class String2IntegerConverter implements Converter<String, Integer> {
    @Override
    public Integer convert(String s) {
        return Integer.parseInt(s);
    }
}
