package com.yanlz.toik.juc;

/**
 *
 *  主存，可以理解为堆内存
 *  main线程,main方法所在的线程
 *  oneThread,实例化之后交给Thread启动的线程
 *  同步锁每次都刷新内存，确保内存可见性
 *
 *  内存可见性问题：
 *  1.当多个线程操作共享数据时，彼此不可见 解决该问题比较直接的方法见v2
 *  2.2中在1的基础上对共享变量加锁 导致的问题是 效率降低   此外再次启动很多线程时，会造成阻塞 解决办法见v3
 *
 * @Author:yanlz
 * @Date:23:37 2019-07-15
 */
public class Volatile2 {
    public static void main(String[] args) {

        //线程先执行，main线程后执行
        OneThread2 oneThread2 = new OneThread2();
        new Thread(oneThread2).start();


        while (true){ // 执行效率高，main线程来不及更新从从主存中获取的数据
            synchronized (oneThread2){ //同步锁每次都刷新 效率降低   此外再次启动很多线程时，会造成阻塞，CPU何时时间再分配任务
                if (oneThread2.isFlag()){     //flag是共享数据，main会去主存中获取，然后再main线程中缓存一份
                    System.out.println("------");
                    break;
                }
            }
        }
    }
}

class OneThread2 implements Runnable{
    //共享数据，主存中有一份
    private boolean flag=false;

    @Override
    public void run() {
        try {
            Thread.sleep(200);
        }catch (Exception e){
            System.out.println(e);
        }

        flag =true;

        System.out.println("----flag:"+flag);

    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}

