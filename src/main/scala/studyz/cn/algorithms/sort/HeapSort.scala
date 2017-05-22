package studyz.cn.algorithms.sort

/**
  * Created by studyz on 17/5/20.
  */
object HeapSort {

  /**
    * 假设index的左子树和右子树都是大根堆，array(index)加入之后需要对堆做出调整，使他重新成为一个大根堆
    * 时间复杂度是o(lgn)
    * @param array
    * @param index
    * @param heapSize
    */
  def maxHeapify(array: Array[Int], index:Int, heapSize:Int): Unit ={
    assert(index<array.length&&index>=0)
    assert(heapSize<=array.length&&heapSize>=1)
    val l = left(index)
    val r = right(index)

    var largest = index

    //选出最大
    if(l<=heapSize-1 && array(l)>array(largest)){
      largest = l
    }
    if(r<=heapSize-1 && array(r)>array(largest)){
      largest = r
    }
    //交换
    if(largest != index){
      val tmp = array(largest)
      array(largest) = array(index)
      array(index) = tmp
      maxHeapify(array,largest,heapSize)
    }
  }

  /**
    * 利用maxHeapify函数构建一个大根堆：在非叶子节点一次调用maxHeapify函数即可
    * 时间复杂度是o(n)
    * @param array
    */
  def buildMaxHeap(array:Array[Int]): Unit ={
    val heapSize = array.length
    for(i <- (0 to heapSize/2).reverse){
      maxHeapify(array,i,heapSize)
    }
  }

  /**
    * 每次交换堆顶元素和最后一个元素(array(0)和array(array.length-1))，然后减少堆大小，并调用heapify来平衡堆，直到堆大小下降到2
    * 时间复杂度是o(nlgn)
    * @param array
    */
  def heapSort(array: Array[Int]):Unit={

    buildMaxHeap(array)
    var heapSize = array.length

    while(heapSize>=2){
      val tmp = array(heapSize-1)
      array(heapSize-1) = array(0)
      array(0) = tmp
      heapSize = heapSize-1
      maxHeapify(array,0,heapSize)
    }
  }



  def parent(index:Int):Int = {
    ((index+1)>>1)-1
  }

  def left(index:Int):Int = {
    ((index+1)<<1) -1
  }

  def right(index:Int):Int = {
    (index+1)<<1
  }

}
