#### 前言

模式匹配是数据结构需要解决的经典问题之一，由此衍生出许多算法。本文介绍模式匹配算法的复杂度从`o(mn)`逐步演化到`o(m+n)`的过程。

#### 模式匹配

模式匹配描述了这样的一个问题：
>字符串`T[1..n]`和`P[1..m]`由字符集`∑`中的字符组成(`m<=n`)，要求字符串T中和模式P所匹配的索引号。

#### 朴素模式匹配算法

这个问题最简单的解决办法是：
>从T的每一个字符`T[i]{1≤i≤n}`开始依次向后比对P的每一个字符，如果全部匹配，则输出`i`；重复之前的操作，直到找到所有符合条件的`i`。

代码如下：

```
object KMPv1 {
  def kmp(source:String,sequence: String):scala.collection.mutable.Seq[Int] = {
    val sourceLen = source.length
    val sequenceLen = sequence.length
    if(sourceLen<sequenceLen)
      return scala.collection.mutable.Seq.empty[Int]

    var cursor = 0
    val cursorMax = sourceLen-sequenceLen+1
    var res:scala.collection.mutable.Seq[Int] = scala.collection.mutable.Seq[Int]()
    while(cursor<cursorMax){//m-n+1
      var isMatch = true
      var cursorInner = 0
      while(isMatch && cursorInner<sequenceLen){//m
        isMatch = source.charAt(cursor+cursorInner) == sequence.charAt(cursorInner)
        cursorInner+=1
      }
      if(isMatch){
        res = res.:+(cursor)
      }
      cursor+=1
    }
    res
  }
}
```
这个算法的复杂度是`o(m*(n-m+1))=o(mn)`，它并没有利用到曾经匹配过的信息。

#### 有限自动机的思想

有限自动机在接受到一个信号之后，会从一个状态转移到另外一种状态。

引入有限自动机的概念之后可以对模式匹配问题作如下变形：

>将字符串`T[1..n]`中的字符`T[i]{1≤i≤n}`依次输入有限自动机，自动机的状态`q`记录了信号输入之后与`P[1..m]`相匹配的字符数,当自动机的状态转移到`m`时我们接受此时的索引`i-m`为匹配索引。

举例来说：

假设`P＝ababaca`由`∑={a,b,c}`中的字符组成，有限自动机的初始状态是`0`，在这个时候接受输入`a`则转移到状态`1`(因为输入`a`之后和`P`有一个匹配的字符)；在状态为`1`的时候接受输入`a`则状态仍然是`1`，接受`b`则状态转移为`2`，接受`c`则状态转移为`0`(因为跟`P`没有匹配的字符)。

![有限自动机](https://github.com/Saber-Altria/note/blob/master/images/algorithms/kmp/finite-automaton.png?raw=true "有限自动机")

显然这个思想的关键在于构造出有限自动机的转移函数`∂(q,signal)`。从上图b可知，该函数只与`m`和`|∑|(全集的字符个数)`有关，该表共有`(m+1)|∑|`个状态值，计算每个状态值的复杂度最多是`o(m^2)`，所以构造转移函数的复杂度最多达到`o(|∑|m^3)`。之后与`T`来匹配只需要`o(n)`的复杂度。整个算法的复杂度最多是`o(n+|∑|m^3)`。

此算法的复杂度远远大于朴素模式匹配算法的`o(mn)`，但是它充分利用曾经匹配过的信息(存储在有限自动机的状态中)，给了我们另外一种思路来思考这个问题，如果能将转移函数的复杂度优化到`o(m)`，就可以大大降低整体的复杂度。

#### KMP算法

KMP算法在有限自动机的思想上做了一些调整，同样引入了`状态`这一概念，但是状态并不是由状态转移函数计算得到，而是通过前缀函数进行逻辑运算之后计算出来的。

举例来说：

如下图a，在输入`T[9]=b`之前，整个匹配过程处于状态`q=5`，即`T[4..8]=P[0..4]`，此时输入`T[9]=b`，发现和`P[5]=c`不匹配，此时通过观察发现：

>将`P`向右移动`2`个位置刚好又有`P[0..2]`和`T[6..8]`匹配，此时状态是`q=3`。

输入`T[9]=b`之后发现匹配则状态变成`q=4`。

尝试将上述的观察过程理论化，发现因为`P[0..4]`的所有前缀中(除开`P[0..4]`本身)，与`T[4..8]`的后缀所匹配的最大长度是`3`<sup>1</sup>，所以尝试将`P`向右移动[当前状态`q=5`减去`3`等于`2`]个位置；又由于有`T[4..8]=P[0..4]`，所以`1`处与`T[4..8]`作匹配等价于与`P[0..4]`作匹配。

![图解kmp](https://github.com/Saber-Altria/note/blob/master/images/algorithms/kmp/kmp-pic.png?raw=true)

由此，下一个状态可以通过当前状态`q`和与`P[1..q]`的后缀匹配的最长前缀(除开自身)的函数来计算出。

计算前缀函数的伪代码如下：

![前缀函数](https://github.com/Saber-Altria/note/blob/master/images/algorithms/kmp/kmp-prefix-func.png?raw=true)

匹配函数的伪代码如下：

![匹配函数](https://github.com/Saber-Altria/note/blob/master/images/algorithms/kmp/kmp-match.png?raw=true)

scala实现如下：

```
/**
  * Created by studyz on 17/3/16.
  */
object KMPv2 {

  def kmpMatch(source:String, pattern: String):scala.collection.mutable.Seq[Int] = {

    var res = scala.collection.mutable.Seq[Int]()

    val statusArr = kmpPrefixFunc(pattern)

    var k =0//表示已经匹配的个数
    val n = source.length
    val m = pattern.length

    for(q <- 0 until n){//n次
      while(k>0 && source.charAt(q) != pattern.charAt(k)){
        k = statusArr(k-1)
      }
      if(source.charAt(q) == pattern.charAt(k)){
        k+=1
      }
      if(k == m){
        res = res.:+(q-m+1)
        k = statusArr(k-1)
      }
    }
    res
  }

  //pattern字符串第k位前缀的与自身匹配的最长后缀
  //P[1..m],1≤q≤m,0≤k<q，求k使得P[1..k]是P[1..q]的最长后缀，kmpPrefixFunc(q)=k
  def kmpPrefixFunc(pattern:String):Array[Int]={
    val m = pattern.length
    val res = new Array[Int](m)
    res(0) = 0
    var k = res(0)
    for(q <- 1 until m){
      while(k>0 && pattern.charAt(k) != pattern.charAt(q)){
        k = res(k)
      }
      if(pattern.charAt(k) == pattern.charAt(q)){
        k+=1
      }
      res(q) = k
    }
    res
  }
}
```

使用平摊分析法可知此算法的复杂度是`o(m+n)`，其中前缀函数`kmpPrefixFunc`的复杂度是`o(m)`，匹配函数`kmpMatch`的复杂度是`o(n)`。`(//TODO)`

- - -
参考文献：
* 算法导论第三版