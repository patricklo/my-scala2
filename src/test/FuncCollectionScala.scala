/**
 * Created by Patrick on 22/6/2019.
 */

/**函数式编程之集合操作
 * 1. 集合类型Iterable,Seq,Set,Map
  *
  * 分为可变和不可变集合
  *
  * scala.collection.mutable   scala.collection.immutable
  *
  * Seq下包含了Range, ArrayBuffer, List等子trait
  *
  * 2. 集合的函数式编程
  *    集合的函数式编程非常之重要，必须完全掌握和理解scala的高阶函数是什么意思，
  *    scala的集合类的map,flatMap,reduce,reduceLeft，foreach等这些函数，就是高阶函数，因为可以接收其他函数作为参数
  *
  *    高阶函数，也是scala与java的最大区别
  *
  * */


object FuncCollectionScala {
  def main(args: Array[String]): Unit = {

   /** 1. 集合类型Iterable,Seq,Set,Map
    * List
    * list中有特殊操作符 ::  用于将head和tail合并成一个List,  0::list
    */
    def decorator(I:List[Int],prefix:String): Unit ={
      if(I!=Nil){
        println(prefix + I.head)
        decorator(I.tail,prefix)
      }
    }

    val list1 = List.apply(1,2,3,4,5)
    decorator(list1,"patrick_")

    /**
      * LinkedList
      *
      */

    val linkedList01 = scala.collection.mutable.LinkedList(1,2,3,4,5)
    println(linkedList01.elem) //head
    println(linkedList01.next) //tail

    //案例：使用while循环将LinkedList中的每个元素都乘以2
    var currentList = linkedList01
    while(currentList!=Nil){
      currentList.elem = currentList.elem*2
      currentList = currentList.next
    }

    //案例：

    /**
      * 2. 集合的函数式编程
      */
    val list001 = List("leo","patrick","jack")
    val list2 = list001.map("name is "+ _)
    println(list2)

    //flatMap, 将多行多行句子，拆分成单词
    println(List("hello world","you me").flatMap(_.split(" ")))
    //foreach:打印list中的每一个单词
    List("I","Have","a","dream").foreach(println(_))
    //zip: 对学生姓名和学生成绩进行关联
    println(List("Leo","pat","jan").zip(List(100,90,75,83)))

    //案例：统计文本内的单词
    val lines01 = scala.io.Source.fromFile("/Users/patricklo/Documents/test1.txt").mkString
    val lines02 = scala.io.Source.fromFile("/Users/patricklo/Documents/test2.txt").mkString
    val totalLines = List(lines01,lines02)
    //flatMap:空格拆分单词
    //map((_,1)) ->  将每个单词拆分成 (单词,1)的tuple
    //map(_._2) ->将上面的tuple的第2个元素拿出来
    println(totalLines.flatMap(_.split(" ")).map((_, 1)))
    println(totalLines.flatMap(_.split(" ")).map((_, 1)).map(_._2))
    println(totalLines.flatMap(_.split(" ")).map((_, 1)).map(_._2).reduceLeft(_+_))
  }
}


