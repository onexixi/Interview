package ThreadTest;

public class Thread03 {

    /**
     * join
     *
     * @param args
     */


    public static void main(String[] args) {
        System.out.println("爸爸和儿子买烟");
        new Thread(new Father()).start();
    }


}

class Father extends Thread {
    public void run() {
        System.out.println("想抽烟叫儿子去买烟");
        Thread t = new Thread(new Son());
        t.start();
        try {
            System.out.println(t.getState());
            t.join();
            System.out.println(t.getState());
        } catch (InterruptedException e) {
            System.out.println("儿子走丢了，去找儿子");
        }
        System.out.println("接过烟，把零钱给儿子");
    }
}

class Son extends Thread {
    public void run() {
        System.out.println("去买烟，发现游戏厅");
        for (int i = 1; i <= 10; i++) {
            System.out.println(i + "秒过去了");
            try {
                System.out.println();
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("赶紧去买烟，回家");
    }
}
