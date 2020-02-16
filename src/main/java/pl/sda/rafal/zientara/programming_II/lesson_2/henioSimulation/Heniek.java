package pl.sda.rafal.zientara.programming_II.lesson_2.henioSimulation;

import java.util.concurrent.atomic.AtomicInteger;

public class Heniek implements DownloadProcess.OnProgressListener{

    private int count;
    private AtomicInteger finishCount = new AtomicInteger();
    private AtomicInteger errorCount = new AtomicInteger();

    public void startThreads(int count){
        this.count = count;
        for (int i=0; i<count; i++) {
            DownloadProcess process = new DownloadProcess();
            process.setListener(this);
            Thread thread = new Thread(process);
            thread.start();
        }
    }

    public static void main(String[] args) {
        Heniek heniek = new Heniek();
        heniek.startThreads(10000);
    }

    @Override
    public void finished() {
        int finished = finishCount.incrementAndGet();
        checkFinish();
    }

    @Override
    public void onError() {
        errorCount.incrementAndGet();
        checkFinish();
    }

    private void checkFinish() {
        int finished = finishCount.get();
        int errors = errorCount.get();
        if (finished + errors == count){
            System.out.printf("Udało się pobrać %d/%d", finished, count);
        }
    }
}
