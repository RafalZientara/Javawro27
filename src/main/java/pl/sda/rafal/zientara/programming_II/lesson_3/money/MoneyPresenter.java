package pl.sda.rafal.zientara.programming_II.lesson3.money;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MoneyPresenter implements MoneyContract.Presenter {

    private final MoneyContract.View view;

    public MoneyPresenter(MoneyContract.View view){
        this.view = view;
    }

    @Override
    public void initData() {
        List<Cost> costs = new ArrayList<>();
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
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Cost> fakeData = Arrays.asList(
                new Cost("sklep", 50.23, LocalDate.now()));
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

    }
}
