package com.yanlz.toik.sorting;

/**
 * 直接插入排序，其思想可以形象的理解为纸牌游戏抓拍时排序
 *  https://blog.csdn.net/lchad/article/details/43546701
 *  https://www.cnblogs.com/skywang12345/p/3596881.html#a1
 *
 * @Author:yanlz
 * @Date:15:37 2019-09-01
 */
public class StraightInsertionSort {
    public static void main(String[] args) {
        int[] array =new int[6];

        //随机6位数数组
        for(int i=0;i<6;i++){
            int j = (int)(Math.random()*100);
            array[i] = j;
        }

//        array = new int[]{20,40,30,10,60,50};


        System.out.println("排序前的顺序：");
        for(int i = 0; i<array.length; i++){
            System.out.print(array[i] +",");
        }


        sort2(array,array.length);

        System.out.println();
        System.out.println("排序后的顺序：");
        for(int i = 0; i<array.length; i++){
            System.out.print(array[i] +",");
        }

        System.out.println();

    }

    /*
     * 直接插入排序
     * 从小到大，下面的注释皆是基于此顺序上描述
     * 1.分有序无序，默认将第二个数默认为无序第一个数
     * 2.找到合适的位置，该合适位置+1即时要插入到数
     * 3.找到该位置后，将该位置之后到有序表最后的数整体向右挪动一位
     * 4.将无序表第一个数插入到合适位置+1的地方
     *
     * 参数说明：
     *     a -- 待排序的数组
     *     length -- 数组的长度
     */
    static void sort(int[] a,int length){

        /**
         * 用在获取无序表中第一个数
         * 用于找到的合适位置的数，该j位所在数小于无序表第一个数，j+1的位置为要插入的i数
         * 默认从无序表最右边依次递减，开始挪位
         */
        int i,j,k;

        //找到合适的位置
        for (i = 1; i < length; i++){  //从第二个数开始表示该数，左边为有序，右边为无序（包括i）
            for (j = i-1; j >= 0; j--){
                if (a[j] <= a[i]){  //从有序表最右边开始依次与i进行比较,直到找到合适小于i的数，如果没找到，j最终等于-1，意味则没有找到比i小的，i就是最小的，需要将i移动到最左边
                    break;
                }
            }

            //挪位并交换顺序
            if (j != i-1){  //不知道为什么要这么判断 j可能是找到的合适位置的情况，待将i插入到合适位置，也可能是有序表以及是合理位置的情况，这时通过该判断后过滤出不需要调整位置情况

               int temp = a[i];
               for (k = i-1; k > j; k--){
                   a[k+1] = a[k];
               }
               a[j+1] = temp;
            }
        }
    }

    /**
     * 第二种实现方式，比第一种方式要好
     *
     * @param a
     * @param length
     */
    static void sort2(int a[], int length) {
        int i;
        int j;

        int temp;    //在这里temp是作为哨兵,监视array[i]的值
        for (i = 1; i < length; i++) {    //此处i从1开始是因为我们首先做的是把第1个元素插入到第0个元素组成的序列中
            if (a[i] < a[i - 1]) {    //此时,array[i]之前的序列已经是有序状态
                temp = a[i];            //寄存array[i]的值
                for (j = i - 1; j>=0&& a[j] > temp; j--) {        //找到第一个不大于temp即array[i]的值,循环就结束
                    a[j + 1] = a[j];        //将array[j]向后挪一个位置
                }
                a[j + 1] = temp;                //把哨兵存储的array[i]的数值填到最后剩下来的那个正确位置.
            }                                        //这样使得array[0]~array[i]为有序的
        }
    }
}
