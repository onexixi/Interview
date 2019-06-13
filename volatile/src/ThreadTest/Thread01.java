package ThreadTest;

public class Thread01 extends Thread {
    @Override
    public void run() {
        for (int i = 1; i < 20; i++) {
            System.out.println("1111");
        }
    }


}

