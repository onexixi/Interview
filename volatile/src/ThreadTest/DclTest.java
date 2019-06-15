package ThreadTest;

/**
 * 懒汉式
 * 1 构造器私有化 避免外部创建
 * 2 提供私有的静态属性--》储存对象地址
 * 3 提供公共的静态方法-》获取属性
 */
public class DclTest {
    //私有的静态属性
    //没有volatile 其他对象可能会访问到一个没有初始化的对象
    private static volatile DclTest instance;

    //构造器私有化
    private DclTest() {

    }

    //提供公共方法
    public static DclTest getInstance() {

        //
        if (null != instance) {
            return instance;
        }
        synchronized (DclTest.class) {
            if (null == instance) {
                instance = new DclTest();
                //开辟空间
                //初始化对象信息
                //返回对象地址给引用

            }
        }
        return instance;
    }

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {

            System.out.println(DclTest.getInstance());
        });
        thread.start();
        System.out.println(DclTest.getInstance());
    }
}

