package pl.sda.rafal.zientara.programing_I_webinar;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class LottoMain {
    public static void main(String[] args) {
        List<Integer> balls = new LinkedList<>();

        for (int i = 0; i < 10; i++) {
            Integer value = i + 1;
            balls.add(value);
        }

        for (int i = 0; i < 5; i++) {

            int randomIndex = new Random().nextInt(balls.size());
            TimeMeasure timeMeasure = new TimeMeasure();

            timeMeasure.startMeasure();
            Integer ball = balls.get(randomIndex);
            timeMeasure.endMeasure();

            balls.remove(ball);

            System.out.println("Wylosowana: " + ball);
            System.out.println("Zosta³o:" + balls);
        }
    }
}
