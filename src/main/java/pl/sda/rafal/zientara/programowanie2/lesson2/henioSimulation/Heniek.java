package pl.sda.rafal.zientara.programowanie2.lesson2.henioSimulation;

import java.util.concurrent.atomic.AtomicInteger;

public class Heniek implements DownloadProcess.OnProgressListener{

    private int count;
    private AtomicInteger finishedCount = new AtomicInteger();
    private AtomicInteger errorCount = new AtomicInteger();

    public void startsThreads (int count){
        this.count=count;
        for (int i = 0; i < count; i++) {
            DownloadProcess process = new DownloadProcess();
            process.setListener(this);
            Thread thread = new Thread(process);
            thread.start();
        }
    }

    public static void main(String[] args) {
        Heniek heniek = new Heniek();
        heniek.startsThreads(10000);
    }

    @Override
    public void finished() {
        finishedCount.incrementAndGet(); //zwiększy o 1
        checkFinish();
    }

    @Override
    public void onError() {
        errorCount.incrementAndGet();
        checkFinish();
    }

    private void checkFinish() {
        int finished = finishedCount.get();
        int error = errorCount.get();
        if (finished + error == count){
            System.out.printf("Proces zakończony. Udało się pobrać %d/%d %s", finished, count, "plików"); //%d (decimal) żeby potem liczbę wstawić i tu trzeba ...printf. Zmiennoprzecinkowe to %f
        }
    }
}
