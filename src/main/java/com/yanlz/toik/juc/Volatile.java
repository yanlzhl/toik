package com.yanlz.toik.juc;

/**
 *
 *  主存，可以理解为堆内存
 *  main线程,main方法启动后的主线程
 *  oneThread,实例化之后交个Thread启动的线程
 *
 *  内存可见性问题：
 *  当多个线程操作共享数据时，彼此不可见
 *  解决该问题比较直接的方法见v2
 *
 * @Author:yanlz
 * @Date:23:37 2019-07-15
 */
public class Volatile {
    public static void main(String[] args) {

        //线程先执行，main线程后执行
        oneThread oneThread = new oneThread();
        new Thread(oneThread).start();


        while (true){ // 执行效率高，main线程来不及更新从从主存中获取的数据
            if (oneThread.isFlag()){     //flag是共享数据，main会去主存中获取，然后再main线程中缓存一份
                System.out.println("------");
                continue;
            }
        }
    }


}

class oneThread implements Runnable{
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

        System.out.println("----flag----");

    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}