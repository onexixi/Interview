public class Thread02 implements Runnable {
     private   volatile  int sum=100;

    @Override
    public void run() {
        while (sum>0){
            sum--;
            System.out.println(Thread.currentThread().getName()+sum);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) {
        Thread02 thread02=new Thread02();
        new Thread(thread02,"xxx").start();
        new Thread(thread02,"aaa").start();
        new Thread(thread02,"ccc").start();
    }
}

