package pl.sda.rafal.zientara.programowanie2.lesson2;

public class RunnableMain {

    public static void main(String[] args) {

        for (int i = 0; i < 3; i++) {
            MyRunnable runnable = new MyRunnable();
            Thread thread = new Thread(runnable);
            thread.start();
        }

    }

    }

    class MyRunnable implements Runnable {

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName());
            System.out.println("YOLO Runnable");
        }
    }

