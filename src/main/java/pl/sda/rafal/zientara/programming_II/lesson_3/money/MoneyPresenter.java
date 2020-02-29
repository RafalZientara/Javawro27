package pl.sda.rafal.zientara.programming_II.lesson_3.money;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MoneyPresenter implements MoneyContract.Presenter {

    private final MoneyContract.View view;

    List<Cost> costs = new ArrayList<>();
    private List<Cost> lastResult = new ArrayList<>();
    private double fromPrice;
    private CharSequence word;
    private double toPrice;
    private LocalDate fromDate;
    private LocalDate toDate;

    public MoneyPresenter(MoneyContract.View view){
        this.view = view;
    }

    @Override
    public void prepareData() {

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
    }

    @Override
    public void initData() {
        refreshAndShow();
    }

    private Cost parseCost(String line) {
        String[] split = line.split(";");
        String shopName = split[0]; // nazwa sklepu
        double price = Double.parseDouble(split[1]
        .replace(",", ".")
        .replace("\"", ""));

        String input = split[2];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(input, formatter);
        return new Cost(shopName, price, date);
    }

    @Override
    public void onWordChange(String word) {
        lastResult = costs.stream()
                .filter(cost -> cost.getShopName().contains(word))
                .collect(Collectors.toList());

        view.refreshLIst(lastResult);
    }

    @Override
    public void onPriceFromChange(double fromPrice) {
        this.fromPrice = fromPrice;
        refreshAndShow();
    }

    @Override
    public void onPriceToChange(double toPrice) {
        this.toPrice = toPrice;
        refreshAndShow();
    }

    private void refreshAndShow() {
        refreshResult();
        refreshUi();
    }

    private void refreshUi() {
        view.refreshLIst(lastResult);
        view.refreshSum(countSum());
    }

    private double countSum() {
        Optional<Double> reduce = lastResult.stream()
                .map(cost -> cost.getPrice())
                .reduce((acc, value) -> acc + value);
        return reduce.orElseGet(() -> 0D); // akcja na pobranie alternatywnej wartoœci
    }

    private void refreshResult() {
        Stream<Cost> stream = costs.stream();
        if (word != null) {
            stream = stream
                    .filter(cost -> cost.getShopName().contains(word));
        }
        if (fromPrice > 0) {
            stream = stream
                    .filter(cost -> cost.getPrice() >= fromPrice);
        }
        if (toPrice > 0) {
            stream = stream
                    .filter(cost -> cost.getPrice() >= toPrice);
        }
            if (fromDate != null) {
                stream = stream
                        .filter(cost -> cost.getDate().isAfter(fromDate));
            }if (toDate != null) {
            stream = stream
                    .filter(cost -> cost.getDate().isBefore(toDate));
        }
            lastResult = stream.collect(Collectors.toList());

    }
    @Override
    public List<Cost> getLastResult() {
        return lastResult;
    }

    @Override
    public void onFromDateChange(LocalDate fromDate) {
        this.fromDate = fromDate;
        refreshResult();
        refreshAndShow();
    }

    @Override
    public void onToDateChange(LocalDate toDate) {
        this.toDate = toDate;
        refreshResult();
        refreshAndShow();
    }


}
