package studyz.cn.algorithms.sort

import org.scalatest.FunSuite

/**
  * Created by studyz on 17/5/20.
  */
class HeapSortSuite extends FunSuite{

  test("maxheapify"){
    val array = Array[Int](1,2,3)

    HeapSort.maxHeapify(array,0,array.length)

    println(array.mkString(","))

  }

  test("build heap"){
    val array = Array[Int](1,3,5,7,9,2,4,6,8,0)

    HeapSort.buildMaxHeap(array)

    println(array.mkString(","))
  }

  test("heap sort"){
    val array = Array[Int](1,3,5,7,9,2,4,6,8,0)

    HeapSort.heapSort(array)

    println(array.mkString(","))
  }

}
