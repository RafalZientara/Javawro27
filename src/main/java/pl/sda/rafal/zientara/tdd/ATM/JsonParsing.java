package pl.sda.rafal.zientara.tdd.ATM;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class JsonParsing {

    public static ArrayList<Person> getGson() {
        String filename = "C:\\program\\myDataWithPeople.json";
        Gson gson = new Gson();
        JsonReader reader;
        Type userListType;
        try {
            reader = new JsonReader(new FileReader(filename));
            userListType = new TypeToken<ArrayList<Person>>() {
            }.getType();
            return gson.fromJson(reader, userListType);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}

