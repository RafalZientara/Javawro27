package pl.sda.rafal.zientara.programowanie2.lesson2.henioSimulation;

import java.io.IOException;
import java.util.Random;

public class DownloadProcess implements Runnable {

    public interface OnProgressListener {
        void onSuccess();
        void onError();
    }

    private OnProgressListener listener;

    @Override
    public void run() {
        try {
            System.out.println("Downloading...");
            pretendRandomError();
            pretendDownloading();
            System.out.println("Finished");
            if (listener != null) {
                listener.onSuccess();
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (listener != null) {
                listener.onError();
            }
        }
    }

    private void pretendRandomError() throws IOException {
        int isError = new Random().nextInt(2);
        if (isError != 0) {
            throw new IOException("Lipa");
        }
    }

    private void pretendDownloading() throws InterruptedException {
        Thread.sleep(1000 + new Random().nextInt(3000));
    }

    public void setListener(OnProgressListener listener) {
        this.listener = listener;
    }
}
