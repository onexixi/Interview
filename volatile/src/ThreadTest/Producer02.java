package ThreadTest;

/**
 * 信号灯 生成者--消费者
 */
public class Producer02 {
    public static void main(String[] args) {
        Tv tv = new Tv();
        Player player = new Player(tv);
        Audience audience = new Audience(tv);
        Thread a = new Thread(player);
        Thread b = new Thread(audience);
        a.start();
        b.start();
    }
}

//生产者演员
//消费者观众
class Player implements Runnable {
    Tv tv;

    public Player(Tv tv) {
        this.tv = tv;
    }

    public void run() {
        for (int i = 0; i < 20; i++) {
            if (i % 2 == 0) {
                this.tv.play("吃苹果");
            } else {
                this.tv.play("看漫画");
            }
        }
    }
}

class Audience implements Runnable {
    Tv tv;

    public Audience(Tv tv) {
        this.tv = tv;
    }

    public void run() {
        for (int i = 0; i < 20; i++) {
            tv.watch();
        }
    }
}

class Tv {
    Boolean flag = Boolean.TRUE;
    String picture;

    public synchronized void play(String picture) {
        while (!flag) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("表演了---" + picture);
        this.picture = picture;
        this.notifyAll();
        this.flag=!this.flag;
    }

    public synchronized void watch() {
        while (flag) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("观看了---" + picture);
        this.notifyAll();
        this.flag=!this.flag;
    }
}