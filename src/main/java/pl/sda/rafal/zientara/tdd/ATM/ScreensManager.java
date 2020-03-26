package pl.sda.rafal.zientara.tdd.ATM;

import pl.sda.rafal.zientara.tdd.ATM.ChangePin.ChangePin;
import pl.sda.rafal.zientara.tdd.ATM.Menu.MenuScreen;
import pl.sda.rafal.zientara.tdd.ATM.cardID.IdScreen;
import pl.sda.rafal.zientara.tdd.ATM.dashboard.DashboardScreen;
import pl.sda.rafal.zientara.tdd.ATM.model.Cash;
import pl.sda.rafal.zientara.tdd.ATM.pin.PinScreen;
import pl.sda.rafal.zientara.tdd.ATM.thanks.ThanksScreen;
import pl.sda.rafal.zientara.tdd.ATM.wrong.WrongPinScreen;

import java.util.List;

public class ScreensManager implements
        PinScreen.ScreenListener,
        WrongPinScreen.ScreenListener,
        DashboardScreen.ScreenListener,
        ThanksScreen.ScreenListener,
        IdScreen.ScreenListener,
MenuScreen.ScreenListener,
ChangePin.ScreenListener {
    private PinScreen pinScreen;
    private WrongPinScreen wrongPinScreen;
    private DashboardScreen dashboardScreen;
    private ThanksScreen thanksScreen;
    private IdScreen idScreen;
    private MenuScreen menuScreen;
    private ChangePin changePin;

    public void start() {
        idScreen = new IdScreen(this);
        idScreen.show();
    }

    @Override
    public void onCorrectPin() {
        pinScreen.hide();
        menuScreen = new MenuScreen(this);
        menuScreen.setPerson(pinScreen.getPerson());
        loadDashboardAndHide();
    }

    private void loadDashboardAndHide() {
        dashboardScreen = new DashboardScreen(this);
        dashboardScreen.hide();
    }

    @Override
    public void onCorrectId() {
        idScreen.hide();
        showPinScreen();
    }

    public void backFromDashToMenu() {
        dashboardScreen.hide();
        menuScreen.show();
    }


    @Override
    public void setPersonToPinScreen(Person person) {
        pinScreen.setPerson(person);
    }

    @Override
    public void setPersonToDashBoardScreen(Person person) {
if (dashboardScreen!=null)        dashboardScreen.setPerson(person);
    }

    private void showPinScreen() {
        pinScreen = new PinScreen(this);
        pinScreen.show();
    }

    private void showDashboard() {
        dashboardScreen.show();
    }

    @Override
    public void onWrongPin() {
        pinScreen.hide();
        if (wrongPinScreen==null) wrongPinScreen = new WrongPinScreen(this);
        wrongPinScreen.show();
    }

    @Override
    public void onWrongPinConfirm() {
        pinScreen.hide();
        wrongPinScreen.hide();
        pinScreen.show();
    }


    @Override
    public int getId() {
        return idScreen.getId();
    }


    @Override
    public void onWithdrawalConfirm() {
        thanksScreen.hide();
        menuScreen.show();
    }


    @Override
    public void onWithdrawalMoney() {
        menuScreen.hide();
        showDashboard();
    }

    @Override
    public void setNewPinFrame() {
        menuScreen.hide();
        changePin = new ChangePin(this);
    }


    @Override
    public void onChangePin(String pin) {
        menuScreen.setPin(pin);
        changePin.hide();
        menuScreen.show();
    }

    @Override
    public String getPin() {
        return pinScreen.getPerson().getPin();
    }

    @Override
    public void backFromPinToMenu() {
        changePin.hide();
        menuScreen.show();
    }

    @Override
    public void onCashWithdrawal(List<Cash> money) {
        dashboardScreen.hide();
        thanksScreen = new ThanksScreen(this, money);
        thanksScreen.show();
    }
}
