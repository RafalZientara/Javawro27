package programowanie2.lesson4.money;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MoneyPresenter implements MoneyContract.presenter {
    private final MoneyContract.View view;

    public MoneyPresenter(MoneyContract.View view) {
        this.view = view;
    }

    @Override
    public void initData() {

        List<Cost> shopData = new ArrayList<>();
        //todo
        try {
            FileReader data = new FileReader("zakupy.csv");
            BufferedReader bufferedReader = new BufferedReader(data);
            boolean firstIgnored = false;
            String line = bufferedReader.readLine();
            while(line!=null){
                if (!firstIgnored){
                    firstIgnored = true;
                } else {
                    Cost cost = parseCost(line);
                    shopData.add(cost);
                }
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        view.refreshList(shopData);
    }

    private Cost parseCost(String line) {
        String[] cost = line.split(";");

        String shopName = cost[0];
        double price = Double.parseDouble(cost[1].replace(',','.').replace("\"",""));
        String time = cost[2];

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(time, formatter);

        return new Cost(shopName,price,date);
    }

    @Override
    public void onWordChange(String word) {
        //todo
    }
}
