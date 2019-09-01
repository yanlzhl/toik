package com.yanlz.toik.sorting;

/**
 * 直接插入排序，其思想可以形象的理解为纸牌游戏抓拍时排序
 *  https://blog.csdn.net/lchad/article/details/43546701
 *  https://www.cnblogs.com/skywang12345/p/3596881.html#a1
 *
 * @Author:yanlz
 * @Date:15:37 2019-09-01
 */
public class StraightInsertionSortOrderByDesc {
    public static void main(String[] args) {
        int[] array = {20,40,30,10,60,50};

        System.out.println("排序前的顺序：");
        for(int i = 0; i<array.length; i++){
            System.out.print(array[i] +",");
        }


        sort(array,array.length);

        System.out.println();
        System.out.println("排序后的顺序：");
        for(int i = 0; i<array.length; i++){
            System.out.print(array[i] +",");
        }

        System.out.println();

    }

    static void sort(int[] a,int length){

        int i,j,k;

        //找到合适的位置
        for (i = 1; i < length; i++){
            for (j = i-1; j >= 0; j--){
                if (a[j] >= a[i]){  //改变判断方式，就可以决定最终的排序顺序
                    break;
                }
            }

            //挪位并交换顺序
            if (j != i-1){

               int temp = a[i];
               for (k = i-1; k > j; k--){
                   a[k+1] = a[k];
               }
               a[j+1] = temp;
            }
        }
    }
}
