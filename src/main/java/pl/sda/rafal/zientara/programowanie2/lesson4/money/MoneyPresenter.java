package pl.sda.rafal.zientara.programowanie2.lesson4.money;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MoneyPresenter implements
        MoneyContract.Presenter {
    private final MoneyContract.View view;

    private List<Cost> costs = new ArrayList<>();
    private List<Cost> lastResult = new ArrayList<>();
    private double fromPrice;
    private String word;
    private double price;
    private LocalDate fromDate;
    private LocalDate toDate;

    public MoneyPresenter(MoneyContract.View view) {
        this.view = view;
    }

    @Override
    public void prepareData() {
        File file = new File("zakupy.csv");
        try {
            FileReader reader = new FileReader(file);
            BufferedReader buffer = new BufferedReader(reader);
            boolean fistIgnored = false;
            String line = buffer.readLine();
            // line = buffer.readLine();
            while (line != null) {
                if (!fistIgnored) {
                    fistIgnored = true;
                } else {
                    Cost cost = parseCost(line);
                    costs.add(cost);
                }
                line = buffer.readLine();
            }
            //todo czytamy linijka po linijce i tworzymy liste Cost
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initData() {
        view.refreshList(costs);
    }

    private Cost parseCost(String line) {
        String[] split = line.split(";");
        String shopName = split[0];
        double price = Double.parseDouble(split[1]//"345.50"
                .replace(",", ".")
                .replace("\"", ""));
        String input = split[2];//2020-01-02
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(input, formatter);
        return new Cost(shopName, price, date);
    }

    @Override
    public void onWordChange(String word) {
        this.word = word;
        refreshAndShow();
    }

    private void refreshResult() {
        Stream<Cost> stream = costs.stream();
        if (word != null) {
            stream = stream
                    .filter(cost -> cost.shopName.contains(word));
        }
        if (fromPrice > 0) {
            stream = stream
                    .filter(cost -> cost.price >= fromPrice);
        }
        if (price > 0) {
            stream = stream
                    .filter(cost -> cost.price <= price);
        }
        if (fromDate != null) {
            stream = stream
                    .filter(cost -> !cost.date.isBefore(fromDate));
        }
        if (toDate != null) {
            stream = stream
                    .filter(cost -> !cost.date.isAfter(toDate));
        }
        lastResult = stream.collect(Collectors.toList());
    }

    @Override
    public void onPriceFromChange(double fromPrice) {
        this.fromPrice = fromPrice;
        refreshAndShow();
    }

    private void refreshAndShow() {
        refreshResult();
        view.refreshList(lastResult);
        view.refreshSum(countSum());
    }

    private double countSum() {
        Optional<Double> reduce = lastResult.stream().map(cost -> cost.price)
                .reduce(Double::sum);
        return reduce.orElse(0D);
    }

    @Override
    public void onPriceToChange(double price) {
        this.price = price;
        refreshAndShow();
    }

    @Override
    public List<Cost> getLastResult() {
        return lastResult;
    }

    @Override
    public void onFromDateChange(LocalDate fromDate) {
        this.fromDate = fromDate;
        refreshAndShow();
    }

    @Override
    public void onToDateChange(LocalDate toDate) {
        this.toDate = toDate;
        refreshAndShow();
    }
}
