package pl.sda.rafal.zientara.programowanie2.lesson2;

public interface Converter<Input, Output> {
    Output convert(Input input);
}
