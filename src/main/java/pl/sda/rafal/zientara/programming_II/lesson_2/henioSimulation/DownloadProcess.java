package pl.sda.rafal.zientara.programming_II.lesson_2.henioSimulation;

import java.io.IOException;
import java.sql.SQLOutput;
import java.util.Random;

public class DownloadProcess implements  Runnable{

    public interface OnProgressListener {
        void finished();
        void onError();
    }

    private OnProgressListener listener;

    @Override
    public void run() {
        try {
            System.out.println("Downloading");
            pretendRandomError();
            pretendDownloading();
            System.out.println("Finished");
            if (listener != null){
                listener.finished();
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (listener != null){
                listener.onError();
            }
        }
    }



    private void pretendRandomError() throws IOException {
        int isError = new Random().nextInt(2);
        if (isError != 0){
            throw new IOException("Lipa");
        }
    }

    private void pretendDownloading() throws InterruptedException {
        Thread.sleep(100 + new Random().nextInt(3000));
    }

    public void setListener(OnProgressListener listener) {
        this.listener = listener;
    }
}
