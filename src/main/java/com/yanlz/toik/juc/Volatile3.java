package com.yanlz.toik.juc;

/**
 *
 *  主存，可以理解为堆内存
 *  main线程,main方法所在的线程
 *  oneThread,实例化之后交给Thread启动的线程
 *  volatile 可以保证多个线程访问操作共享数据时 保证数据可见性
 *           想比较与 synchronize 是个轻量级的同步策略
 *           volatile修饰的变量 其操作都在相当于主存中完成（每次操作之前都会同步数据到线程副本中）
 *           volatile 效率低在不能重排序优化  什么是重排序？
 *           注意：
 *              不具备互斥性，多个线程可同时访主存中的共享数据
 *              不能保证变量的"原子性"   解决办法见atomic
 *
 *
 *  内存可见性问题：
 *  1.当多个线程操作共享数据时，彼此不可见 解决该问题比较直接的方法见v2
 *  2.2中在1的基础上对共享变量加锁 导致的问题是 效率降低   此外再次启动很多线程时，会造成阻塞
 *
 * @Author:yanlz
 * @Date:23:37 2019-07-15
 */
public class Volatile3 {
    public static void main(String[] args) {

        //线程先执行，main线程后执行
        OneThread3 oneThread = new OneThread3();
        new Thread(oneThread).start();


        while (true){ // 执行效率高，main线程来不及更新从从主存中获取的数据
                if (oneThread.isFlag()){     //flag是共享数据，main会去主存中获取，然后再main线程中缓存一份
                    System.out.println("------");
                    break;
                }
        }
    }
}

class OneThread3 implements Runnable{
    //共享数据，主存中有一份
    private volatile boolean flag=false;

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

