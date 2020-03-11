package pl.sda.rafal.zientara.tdd.lesson3.money;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MoneyPresenter implements MoneyContract.Presenter {
    private final MoneyContract.View view;
    private List<Cost> costs;
    private List<Cost> lastResult = new ArrayList<>();
    private double moreThanValue;
    private double lessThanValue;
    private String word;
    private LocalDate exactlyDate;
    private LocalDate fromDate;
    private LocalDate toDate;
    private double sumValue;
    Optional<Double> reduce;

    public MoneyPresenter(MoneyContract.View view) {
        this.view = view;
    }

    @Override
    public void prepareData() {
        File file = new File("zakupy.csv");
        costs = new ArrayList<>();
        try {
            FileReader reader = new FileReader(file);
            BufferedReader buffer = new BufferedReader(reader);
            boolean firstIgnored = false;
            String line = buffer.readLine();
            while (line != null) {
                if (!firstIgnored) {
                    firstIgnored = true;
                } else {
                    Cost cost = parseCost(line);
                    costs.add(cost);
                }
                line = buffer.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initData() {
        view.refreshList(costs);
        view.refreshSum(costs);
    }

    @Override
    public void onWordChange(String word) {
        this.word = word;
        refreshResult();
        view.refreshList(lastResult);
    }

    @Override
    public void onPriceToChange(double value) {
        this.lessThanValue = value;
        refreshResult();
        view.refreshList(lastResult);
    }

    private void refreshResult() {
        Stream<Cost> stream = costs.stream();
        if (word != null) {
            stream = stream.filter(cost -> cost.shopName.contains(word));
        }
        if (moreThanValue > 0) {
            stream = stream.filter(cost -> cost.price > moreThanValue);
        }
        if (lessThanValue > 0) {
            stream = stream.filter(cost -> cost.price < lessThanValue);
        }
        if (exactlyDate != null) {
            stream = stream.filter(cost -> cost.date.equals(exactlyDate));
        }
        if (fromDate != null) {
            stream = stream.filter(cost -> !cost.date.isBefore(fromDate));
        }
        if (toDate != null) {
            stream = stream.filter(cost -> !cost.date.isAfter(toDate));
        }
        lastResult = stream.collect(Collectors.toList());
    }

    @Override
    public void onPriceFromChange(double value) {
        this.moreThanValue = value;
        refreshResult();
        view.refreshList(lastResult);
    }

    @Override
    public List<Cost> getLastResult() {
        return lastResult;
    }

    @Override
    public void onDateChange(LocalDate date) {
        this.exactlyDate = date;
        refreshResult();
        view.refreshList(lastResult);
    }

    @Override
    public void fromThisDate(LocalDate date) {
        this.fromDate = date;
        refreshResult();
        view.refreshList(lastResult);
    }

    @Override
    public void onToThisDate(LocalDate date) {
        this.toDate = date;
        refreshResult();
        view.refreshList(lastResult);
    }

    //moja propozycja implementacji
    private void countSum() {
        sumValue = 0;
        for (Cost values : lastResult) {
            sumValue = sumValue + values.price;
        }
    }

    private Cost parseCost(String line) {
        String[] temp = line.split(";");
        String name = temp[0];
        double cost = Double.parseDouble(temp[1].replace(',', '.').replace("\"", ""));
        DateTimeFormatter myFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(temp[2], myFormatter);
        return new Cost(name, cost, date);
    }

    private double countSumOptional() {
        reduce = lastResult.stream()
                .map(cost -> cost.price)
                .reduce((acc, value) -> acc + value);
        return reduce.orElse(0D);
    }

    //moja propozycja implementacji
    public String getSumValue() {
        sumValue = countSumOptional();
        return String.valueOf(sumValue);
    }


   /* private void saveData(File file){
        try {
            PrintWriter writer;
            writer = new PrintWriter(file);
            for (Cost p : lastResult)
                writer.println(p.toString());
            writer.println("\nPrices from to: "+moreThanValue+" "+lessThanValue);
            writer.println("Filtered date from to: "+fromDate+" "+toDate);
            writer.println("Total price to pay "+ reduce.get());
            writer.close();

        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    private FileChooser setFileChooserOptions() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);
        return fileChooser;
    }

    FileChooser newFile = new FileChooser();

    @Override
    public void handleSave() {
        File file = setFileChooserOptions().showSaveDialog(new Stage());
        if (file != null) {
            saveData(file);
        }
    }*/
   @Override
   public void saveData(){
       try {
           PrintWriter pw = new PrintWriter(new FileOutputStream("baza.csv"));
           for (Cost p : lastResult)
               pw.println(p.toString());
           pw.println("\nPrices from to: "+moreThanValue+" "+lessThanValue);
           pw.println("Filtered date from to: "+fromDate+" "+toDate);
           pw.println("Total price to pay "+reduce.get());
           pw.close();
       } catch (Exception e) {
           e.getStackTrace();
       }
   }
}
