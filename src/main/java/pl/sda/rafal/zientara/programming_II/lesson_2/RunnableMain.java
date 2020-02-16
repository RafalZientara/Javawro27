package pl.sda.rafal.zientara.programming_II.lesson_2;

public class RunnableMain {

    public static void main(String[] args) {
        for (int i=0; i<3; i++) {
            MyRunnable runnable = new MyRunnable();
            Thread thread = new Thread(runnable);
            thread.start();
        }
    }
    static class MyRunnable implements Runnable{

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName());
            System.out.println("Yolo Runnable");
        }
    }
}
