package module;

public class Countdown implements Runnable {
    public static final int MIN_MEMORIZE_TIME = 3;
    public static final int MIN_INPUT_TIME = 5;
    public static final int RESULT_TIME = 1;
    private int time;
    private Thread thread;

    public int getTime() {
        return time;
    }


    public void countdown(int time) {
        thread = new Thread(this);
        this.time = time;
        thread.start();
    }

    public boolean isCounting() {
        return time > 0;
    }

    public void stopCounting() {
        time = 0;
        if (thread != null) {
            thread.interrupt();
        }
    }

    @Override
    public void run() {
        while (time > 0 && !Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                return;
            }
            time--;
        }
    }
}
