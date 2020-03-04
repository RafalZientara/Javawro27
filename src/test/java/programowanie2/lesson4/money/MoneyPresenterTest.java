package programowanie2.lesson4.money;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;


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
        Mockito.verify(view).refreshList(any());
    }

    @Test
    public void whenQueryChangedRefreshResults() {
        List<Cost> expected = Arrays.asList(new Cost("test", 1, LocalDate.of(2020,1,1)));
        presenter.onWordChange("test");
        Mockito.verify(view).refreshList(expected);
    }

    @Test
    public void findAllZabkas() {
        List<Cost> expected = Arrays.asList(
                new Cost("zabka",12, LocalDate.of(2020,1,16)),
                new Cost("zabka",53.23, LocalDate.of(2020,1,23)),
                new Cost("zabka",12, LocalDate.of(2020,1,16)),
                new Cost("zabka",700.01, LocalDate.of(2020,1,23)));
        presenter.onWordChange("zab");
        Mockito.verify(view).refreshList(expected);
    }

    @Test
    public void findCostByNameAndPrice() {
        presenter.onWordChange("zabka");
        presenter.onPriceFromChange(500d);
        Mockito.verify(view,Mockito.times(2))
                .refreshList(any());

        List<Cost> lastResult = presenter.getLastResult();
        assertEquals(1,lastResult.size());
    }

    @Test
    public void findCostByNameAndPriceFromAndTo() {
        presenter.onWordChange("zabka");
        presenter.onPriceToChange(60);
        presenter.onPriceFromChange(10);
        Mockito.verify(view, Mockito.times(3)).refreshList(any());
        List<Cost> lastResult = presenter.getLastResult();
        assertEquals(3,lastResult.size());
    }

    @Test
    public void onFromByFromDate() {
        presenter.onFromDateChange(LocalDate.of(2020,1,23));

        Mockito.verify(view, Mockito.times(1)).refreshList(any());
        List<Cost> lastResult = presenter.getLastResult();
        assertEquals(6,lastResult.size());
    }

    @Test
    public void onFromByToDate() {
        presenter.onToDateChange(LocalDate.of(2020,1,31));

        Mockito.verify(view, Mockito.times(1)).refreshList(any());
        List<Cost> lastResult = presenter.getLastResult();
        assertEquals(17,lastResult.size());
    }


    //todo napisz test do sumy


}