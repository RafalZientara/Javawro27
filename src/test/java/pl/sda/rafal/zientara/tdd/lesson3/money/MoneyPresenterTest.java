package pl.sda.rafal.zientara.tdd.lesson3.money;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

class MoneyPresenterTest {

    private MoneyContract.Presenter presenter;
    private MoneyContract.View view;

    @BeforeEach
    public void setup() {
        view = Mockito.mock(MoneyContract.View.class);
        presenter = new MoneyPresenter(view);
        presenter.prepareData();
    }

    @Test
    public void initPresenter() {
        presenter.initData();
        verify(view).refreshList(any());
    }

    @Test
    public void whenQueryChangedRefreshResults() {
        List<Cost> expected = Arrays.asList(new Cost("test",1, LocalDate.of(2020,1,1)));
        presenter.onWordChange("test");
        verify(view).refreshList(expected);
    }

    @Test
    public void findAllZabkas() {
        List<Cost> expected = Arrays.asList(
                new Cost("zabka",12, LocalDate.of(2020,1,16)),
                new Cost("zabka",53.23, LocalDate.of(2020,1,23)),
                new Cost("zabka",12, LocalDate.of(2020,1,16)),
                new Cost("zabka",700.01, LocalDate.of(2020,1,23)));
        presenter.onWordChange("zab");
        verify(view).refreshList(expected);
    }

    @Test
    public void findCostByNameAndPrice() {
        presenter.onWordChange("zab");
        presenter.onPriceFromChange(50.);
        verify(view,Mockito.times(2)).refreshList(any());
        List<Cost> lastResult = presenter.getLastResult();
        assertEquals(2,lastResult.size());
    }

    @Test
    public void findCostByNameAndLessThanPrice() {
        presenter.onPriceToChange(15.);
        verify(view).refreshList(any());
        List<Cost> lastResult = presenter.getLastResult();
        assertEquals(3,lastResult.size());
    }

    @Test
    public void findCostByDate() {
        DateTimeFormatter myFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse("2019-11-24",myFormatter);
        presenter.onDateChange(date);
        verify(view).refreshList(any());
        List<Cost> lastResult = presenter.getLastResult();
        assertEquals(2,lastResult.size());
        }

        @Test
    public void findFromDate() {
            DateTimeFormatter myFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.parse("2020-01-23",myFormatter);
            presenter.fromThisDate(date);
            verify(view).refreshList(any());
            List<Cost> lastResult = presenter.getLastResult();
            assertEquals(6,lastResult.size());
        }
        @Test
        public void findToDate(){
            DateTimeFormatter myFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.parse("2020-01-31",myFormatter);
            presenter.onToThisDate(date);
            verify(view).refreshList(any());
            List<Cost> lastResult = presenter.getLastResult();
            assertEquals(17,lastResult.size());
        }

    }
