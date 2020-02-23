package pl.sda.rafal.zientara.tdd.lesson3.money;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MoneyPresenter implements MoneyContract.Presenter{
private final MoneyContract.View view;
private List<Cost> costs;

    public MoneyPresenter(MoneyContract.View view) {
        this.view = view;
    }

    @Override
    public void initData() {
       //todo wczytac plik csv
        File file = new File("zakupy.csv");
        costs = new ArrayList<>();
        try {
            FileReader reader = new FileReader(file);
            BufferedReader buffer = new BufferedReader(reader);
                boolean firstIgnored = false;
                String line = buffer.readLine();
                line= buffer.readLine();
                while (line!=null) {
                    if (!firstIgnored){
                        firstIgnored=true;
                    }
                    else{
                        Cost cost = parseCost(line);
                        costs.add(cost);
                    }
                    line= buffer.readLine();
                }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
       view.refreshList(costs);
    }

    @Override
    public void onWordChange(String word) {
        //todo
    }
    private Cost parseCost(String line) {
        String[] temp = line.split(";");
        String name = temp[0];
        double cost = Double.parseDouble(temp[1].replace(',','.').replace("\"",""));
        DateTimeFormatter myFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(temp[2], myFormatter);
        return new Cost(name, cost, date);
    }
}
