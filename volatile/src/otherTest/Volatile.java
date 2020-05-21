package otherTest;

public class Volatile {

//volatile线程可见性
    public static void main(String[] args) {
        MyDate date=new MyDate();
        new Thread(()->{
            date.getNumberAdd();
            System.out.println("线程A"+Thread.currentThread().getName()+"Nmuber"+date.number);
        },"A").start();

        System.out.println(Thread.currentThread().getName()+"Nmuber"+date.number);
    }

}
class MyDate{
    volatile int number=0;

    public  void getNumberAdd() {
        this.number=60;
    }
}