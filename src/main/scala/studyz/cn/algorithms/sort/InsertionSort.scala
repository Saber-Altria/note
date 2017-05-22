package studyz.cn.algorithms.sort

/**
  * Created by studyz on 17/5/20.
  * 插入排序：原理类似于斗地主抽牌时整理牌的方法
  * 从小到大
  */
object InsertionSort {

  //思路：将n张牌分成2份，一份为k，且未排序，剩下(n-k)张已排序，每次从k张中抽出一张插入到(n-k)张中的合适位置
  def insertionSort(arr:Array[Int]): Array[Int] ={
    val len = arr.length
    var remain = arr.length
    while(remain >0){
      val sorted = len -remain
      val toBeSortedNum = arr(sorted)
      var toBeInsertedIndex = sorted-1
      while(toBeInsertedIndex>=0 && arr(toBeInsertedIndex)>toBeSortedNum){
        arr(toBeInsertedIndex+1) = arr(toBeInsertedIndex)
        toBeInsertedIndex = toBeInsertedIndex-1
      }
      arr(toBeInsertedIndex+1) = toBeSortedNum
      remain = remain-1
    }
    arr
  }

  def insertionSort2(arr:Array[Int]): Array[Int] ={
    val len = arr.length
    for(j <- 1 until len){
      val tmp = arr(j)
      var i = j-1
      while(i>=0 && arr(i) > tmp){
        arr(i+1 )= arr(i)
        i = i-1
      }
      arr(i+1) = tmp
    }
    arr
  }

}
