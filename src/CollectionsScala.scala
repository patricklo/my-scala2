/**
 * Created by Patrick on 23/6/2019.
 */
object CollectionsScala{
  def main(args: Array[String]){

    //List immutable
    //[1,2,3,4] head 1, tail [2,3,4]
    val listData = List(1,2,4,5)
    println(listData.head+", tail:"+listData.tail)
    println(0 :: listData) //合并list,前head,后tail
    listsample(listData,"list ele:")


    //LinkedList mutable list
    //elem: 头部  next:尾部 (list中的tail)
    val linked = scala.collection.mutable.LinkedList(1,2,3,4,5)
    println(linked.elem)
    println(linked.next)
    var currenList = linked
    while(currenList!=Nil){
      currenList.elem = currenList.elem * 2
      currenList = currenList.next
    }



    //Set 没有重复的集合,插入无序
    //LinkedHashSet 插入按hash值排序
    //sortedSet 按key排序
    val set01 = Set(1,2,4,5)
    println(set01 + 1)


    //集合的函数式编程
    println(List("leo","patrick").map("name is "+_))
    //拆分 split
    println(List("le,o","pat,rick").flatMap(_.split(",")))
    //zip 两个list之间映射
    println(List("leo","patrick","jack").zip(List(100,80,20,60)))
  }


  def listsample(l: List[Int], prefix: String): Unit ={
    //如果List只有一个元素,则它的head就是这个元素,tail是nil
    if(l != Nil){
      println(prefix + l.head)
      listsample(l.tail, prefix)
    }
  }
}


