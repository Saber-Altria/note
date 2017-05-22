package studyz.cn.algorithms.sort

import org.scalatest.FunSuite

/**
  * Created by studyz on 17/5/20.
  */
class MergeSortSuite extends FunSuite{

  test("merge array"){
    val array = Array(1,3,5,7,2,4,6,8)
    MergeSort.merge(array,1,3,6)

    println(array.mkString(","))
  }

  test("merge Sort"){

    val array = Array(1,3,5,7,2,4,6,8)

    println(MergeSort.mergeSort(array,0,array.length-1).mkString(","))

  }

}
