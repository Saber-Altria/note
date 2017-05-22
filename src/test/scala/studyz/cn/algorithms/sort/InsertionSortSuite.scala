package studyz.cn.algorithms.sort

import org.scalatest.FunSuite

/**
  * Created by studyz on 17/5/20.
  */
class InsertionSortSuite extends FunSuite{

  test("test InsertionSortSuite"){
    println(InsertionSort.insertionSort(Array(12,3,21,42,13,25,46,545,6,32,43,2,432,121,56,4,76,67,8,768)).mkString(","))
  }

  test("test InsertionSortSuite2"){
    println(InsertionSort.insertionSort2(Array(12,3,21,42,13,25,46,545,6,32,43,2,432,121,56,4,76,67,8,768)).mkString(","))
  }

}
