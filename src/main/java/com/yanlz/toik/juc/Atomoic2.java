package com.yanlz.toik.juc;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * i++原子性问题 i++实际可分成3个操作'读改写'
 * 10个线程都存在i++操作，serialNumber为主存中的共享数据
 * 例如当某一时刻主存中的serialNumber为5时，A从主存中读取到5，然后+1之后同步会回存
 * 在同步之前的并且是在A本地缓存改变成6之后，B从主存读取到了5，此时A B都在5的基础上进行了+1 打印了6
 *
 *
 * volatile虽然解决了内存可见性问题，但是不能保证该数据的原子性操作
 *
 * 解决方法是将serialNumber改成原子变量 ，jdk1.5 之后提供了封装类
 *  原子变量具有以下特征：
 *      1.volatile修饰，保证内存可见性
 *      2.CAS（Compare-And-Swap）算法，保证数据的原子性
 *  CAS算法是硬件系统对于并发访问操作共享数据的支持
 *      CAS 包含了三个操作数：
 *          内存值 A
 *          预估值 B
 *          更新值 C
 *          当且仅当A==B时，将V=B，否则，不进行任何操作
 *          因此，多个线程修改该变量时，同一时刻只有一个会成功
 *
 *  代码效果见 V2
 *  模拟CAS算法操作 V3
 *
 *
 *
 * AtomicBoolean
 * AtomicInteger
 * AtomicIntegerArray
 * AtomicIntegerFieldUpdater
 * AtomicLong
 * AtomicLongArray
 * AtomicLongFieldUpdater
 * AtomicMarkableReference
 * AtomicReference
 * AtomicReferenceArray
 * AtomicReferenceFieldUpdater
 * AtomicStampedReference
 * DoubleAccumulator
 * DoubleAdder
 * LongAccumulator
 * LongAdder
 * Striped64
 *
 *
 *
 * @Author:yanlz
 * @Date:00:39 2019-07-16
 */
public class Atomoic2 {
    public static void main(String[] args) {
        AtomicDemo2 atomicDemo = new AtomicDemo2();
        for(int i=0; i<10; i++){
            new Thread(atomicDemo).start();
        }
    }
}

class AtomicDemo2 implements Runnable{
    
    private volatile AtomicInteger serialNumber = new AtomicInteger();

    @Override
    public void run() {
        try {
            Thread.sleep(200);
        }catch (Exception e){
            System.out.println(e);
        }

        System.out.println("serialNumber:"+getSerialNumber());
    }

    public int getSerialNumber() {
        return serialNumber.getAndIncrement();
    }
}
