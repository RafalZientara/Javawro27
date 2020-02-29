package pl.sda.rafal.zientara.programming_II.lesson_3.money;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MoneyPresenter implements MoneyContract.Presenter {

    private final MoneyContract.View view;

    List<Cost> costs = new ArrayList<>();

    public MoneyPresenter(MoneyContract.View view){
        this.view = view;
    }

    @Override
    public void initData() {

        File file = new File("zakupy.csv");
        try {
            FileReader reader = new FileReader(file);
            BufferedReader buffer = new BufferedReader(reader);
            String line = buffer.readLine();

            boolean firstIgnored = false;
            while (line != null){
                if (!firstIgnored){
                    firstIgnored = true;
                }else {
                    Cost cost = parseCost(line);
                    costs.add(cost);
                }
                line = buffer.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        view.refreshLIst(costs);
    }

    private Cost parseCost(String line) {
        String[] split = line.split(";");
        String shopName = split[0];
        double price = Double.parseDouble(split[1]
        .replace(",", ".")
        .replace("\"", ""));

        String input = split[2];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date = LocalDate.parse(input, formatter);
        return new Cost(shopName, price, date);
    }

    @Override
    public void onWordChange(String word) {
        //todo
    }
}
