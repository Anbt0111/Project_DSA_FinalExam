public class Countdown implements Runnable{
    private int time;
    private Thread thread;
    public static final int inputTime = 9;
    public static final int resultTime = 1;
    public static final int memorizeTime = 5;
    public int getTime(){
        return time;
    }

    public Countdown(int time) {
        thread = new Thread(this);
        this.time = time;
        thread.start();
    }
    public boolean isCounting(){
        return time > 0;
    }
    public void stopCounting(){
        time = -1;
        thread.interrupt();
    }
    @Override
    public void run() {
        while(time > 0){
            try{
                Thread.sleep(1000);
            }catch(InterruptedException e) {
                return; // thread is interrupted, stop counting, stop thread
            }
            time--;
        }
        time--;
    }
}
