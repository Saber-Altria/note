package studyz.cn.algorithms.sort

import java.util

/**
  * Created by studyz on 17/5/20.
  */
object MergeSort {

  //对数组的start－end部分进行归并排序
  def mergeSort(array: Array[Int],start:Int,end:Int):Array[Int] = {

    if(start<0 || start >= array.length||end<0 || end >= array.length){
      throw new IndexOutOfBoundsException
    }

    if(start < end){//递归结束条件：需要排序的部分只有一个单位，及start＝end
      val split = (start+end)/2
      val left = mergeSort(array,start,split)
      val right = mergeSort(array,split+1,end)
      merge(array,start,split,end)
    }
    array
  }


  //将两个有序数组归并成一个有序数组
  def merge(array: Array[Int],start:Int,split:Int,end:Int): Unit ={

    if(start<0 || start >= array.length||end<0 || end >= array.length||split<0||split>=array.length){
      throw new IndexOutOfBoundsException
    }

    val arrL = new Array[Int](split-start+1+1)
    arrL(arrL.length-1) = Integer.MAX_VALUE
    for(i <- start to split){
      arrL(i-start) = array(i)
    }

    val arrR = new Array[Int](end-split+1)
    arrR(arrR.length-1) = Integer.MAX_VALUE
    for(i <- split+1 to end){
      arrR(i-(split+1)) = array(i)
    }

    var cursorL = 0
    var cursorR = 0
    for(cursor <- start to end ){
      var min:Int = 0
      if(arrL(cursorL)<=arrR(cursorR)) {
        min = arrL(cursorL)
        cursorL = cursorL+1
      } else {
        min = arrR(cursorR)
        cursorR = cursorR+1
      }
      array(cursor) = min
    }
  }
}
