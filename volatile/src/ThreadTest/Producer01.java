package ThreadTest;

public class Producer01 {

    /**
     * 管程法 生产者，消费者
     * @param args
     */
    public static void main(String[] args) {
        SysContainer sysContainer=new SysContainer();
        Producer producer = new Producer(sysContainer);
        Consume consume = new Consume(sysContainer);

        Thread t1 = new Thread(producer);
        Thread t2 = new Thread(consume);

        t1.start();
        t2.start();

    }


}

class SysContainer {
    Apple[] apples = new Apple[10];
    int count = 0;


    //生产
    public synchronized void push(Apple apple) {
        if (count==apples.length){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        apples[count]=apple;
        count++;
        this.notifyAll();
    }

    //消费
    public synchronized Apple get() {
        if (count==0){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        count--;
        this.notifyAll();
        return apples[count];

    }
}

class Producer implements Runnable {
    SysContainer sysContainer;
    public Producer( SysContainer sysContainer) {
        this.sysContainer = sysContainer;
    }


    @Override
    public void run() {
        for(int i=0;i<100;i++){ ;
            System.out.println("生产者生产"+i);
            sysContainer.push(new Apple(i));
        }
    }
}

class Consume implements Runnable {
    SysContainer sysContainer;

    public Consume(SysContainer sysContainer) {
        this.sysContainer = sysContainer;
    }


    @Override
    public void run() {
        for(int i=0;i<100;i++){
            System.out.println("消费者消费"+sysContainer.get().getId());
        }
    }

}

class Apple {
    public Apple(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    private Integer id;
}