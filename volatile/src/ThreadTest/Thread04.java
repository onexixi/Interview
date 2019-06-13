package ThreadTest;

public class Thread04 {


    public static void main(String[] args) {
        TestPrioity testPrioity2=new TestPrioity();
        Thread t1=new Thread(testPrioity2,"AAA");
        Thread t2=new Thread(testPrioity2,"BBB");
        Thread t3=new Thread(testPrioity2,"CCC");
        Thread t4=new Thread(testPrioity2,"DDD");
        //设置优先级在start之前
        t1.setPriority(Thread.MAX_PRIORITY);
        t2.setPriority(Thread.MAX_PRIORITY);
        t3.setPriority(Thread.MIN_PRIORITY);
        t4.setPriority(Thread.NORM_PRIORITY);


        t1.start();
        t2.start();
        t3.start();
        t4.start();


    }
}

class TestPrioity implements Runnable{
@Override
    public void run() {
        System.out.println(Thread.currentThread().getState()+"----线程名字---"+Thread.currentThread().getName()+"---线程优先级---"+Thread.currentThread().getPriority());
    }
}


