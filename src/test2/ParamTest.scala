object ParamTest {
  def main(args: Array[String]):Unit ={
    val defaultParam = new DefaultParam()
    defaultParam.defaultParamValue(firstName = "patrick")


    val dynamicParam = new DynamicParam()
    print(dynamicParam.dynamic(1,2,3,4,5,6))
    print(dynamicParam.dynamic(1 to 6:_*))
    val demo01= new Demo01()
    println(demo01.demoFunc01("demo01"))

    val demo03= new Demo03()
    demo03.demoException()

    val fabClass = new FabClass()
    println(fabClass.fab(10))

    val defaultParamCls = new DefaultValue()
    defaultParam.defaultParamValue(lastName = "last01",firstName = "first01")

    val arrayDemo = new ArrayDemo()
    arrayDemo.test()
    arrayDemo.removeNegValExceptFirst02()
  }
}

//默认参数值
class DefaultParam(){
  def defaultParamValue(firstName:String, middleName: String = "mid", lastName: String="last"): Unit ={
      print(firstName+middleName+lastName)
  }
}


//可变长参数
// 1 to 5 : _*    ->>>
class DynamicParam(){
  def dynamic(nums: Int*) = {
    var result =0
    for(num <- nums){
      result += num
    }

    result
  }

  def sum2(nums:Int*):Int ={
    if(nums.length == 0) 0
    else nums.head + sum2(nums.tail: _*)
    /*
    如传入 1，2，3，4，5
    nums.heads = 1
    nums.tails = 2,3,4,5
     */

  }
}


//递归函数
//斐波那契函数
class FabClass(){
  def fab(n:Int): Int ={
    if(n <= 0) 1
    else fab(n-1) + fab(n - 2)
  }
}

//默认参数值
class DefaultValue(){
  def sayHello(firstName:String,middleName:String="defaultMidName",lastName:String): Unit ={
    println(firstName+middleName+lastName)
  }
}

//过程的用法
//过程通常用于不用返回值的函数
//函数体直接包裹在花括号里，而没有使用 = 连接，则函数的返回值类型就是Unit,这种函数就被称为过程。
class Demo01(){
def demoFunc01(name:String) = "Hello,"+name  //普通函数
def demofunc02(name:String){print("hello,"+name); "hello,"+name} //过程
def demofunc03(name:String):Unit="hello,"+name  //过程
}

import scala.io.Source._
//lazy值
//当将一个值声明为lazy,声明时不会加载，只有第一次调用该变量时，变量对应的表达式才会发生计算。
//这种特性对于特别耗时的计算操作特别有用，比如打开文件进行IO，进行网络IO等
class Demo02(){
  def lazyDemo01(): Unit ={
      lazy val lines = fromFile("\\Users\\noextis.file").mkString

  }
}

import java.io.IOException
import java.io.FileNotFoundException
//Exception handling
class Demo03(){
  def demoException()={
  try{
    //throw new IllegalArgumentException("x should not be negative")
    lazy val lines = fromFile("\\Users\\noextis.file").mkString
    println(lines)
  }catch{
    case e1: StringIndexOutOfBoundsException => println("StringIndexOutOfBoundsException")
    case e2: FileNotFoundException => println("file is not existed")
    case  _: IllegalArgumentException => println("error!!!")
  }finally {
    println("release resources!")
  }
  }
}

/*
Array ArrayBuffer 数组遍历 数组操作
 */

import scala.collection.mutable.ArrayBuffer
class ArrayDemo(){
  def test(): Unit ={
    val a = new Array[Int](10)
    a(0) = 100
    println(a(0)) //访问数组
    val a01 = Array("hello","world")
    a01(0) = "hi"

    //不定长数组
    val b = ArrayBuffer[Int]()
    b +=1
    b += (2,3,4,5)
    b ++= Array(6,7,8,9)

    b.insert(3, 99,100,22) //在index=3的位置后面插入元素99，100，22
    println(b)


    b.remove(1,3)  //在index=1的位置删除后面3个元素

    val aArrayBuffer = a.toBuffer //Array to ArrayBuffer
    val array01 = aArrayBuffer.toArray //ArrayBuffer to Array

    /*
    array
    ArrayBuffer 遍历
     */

    for(i <- 0 until b.length) //until 不包括这个值 b.length
      println(b(i))

    //跳跃遍历
    //
    for(i<- 0 until (b.length,2))  //跳跃，index=0，2，4，6，8这样遍历
      println(b(i))

    //逆序遍历  从尾部遍历
    for(i <- (0 until b.length).reverse)
      println(b(i))

    //使用 增强for循环 遍历
    for(e <- b)
      println(e)

    //数组求和
    val sum_a = a.sum
    println(sum_a)

    //获取最大值
    println(a.max)
    //排序
    scala.util.Sorting.quickSort(a)

    //获取数组中的所有内容
    println(a.mkString)
    println(a.mkString(","))
    println(a.mkString("<",",",">"))

    println(a.toString)


    //对array进行转换 里面数组进行再处理
    /*
    方法1
     */
    val a02 = Array(1,2,3,4,5)
    val a03 = for(ele <- a02) yield ele*ele
    println(a02.mkString(","))
    println(a03.mkString(","))
    /*
    方法2 函数式 转换 处理

     */
   println(a02.filter(_ % 2 ==0).map(2 * _))  //通常用这种
     a02.filter{_ % 2 ==0} map{2 * _}  //通常不用这种
  }

  /*
  删除除了第一个出现的负数

  方法1： 效率低
   */
  def removeNegValExceptFirst(): Unit ={
    val a = ArrayBuffer[Int]()
    a += (1,2,3,4,5,-1,-3,-5,-9)
    var foundFirstNegative = false
    var arrayLength = a.length
    var index =0
    while (index < arrayLength){
      if(a(index) >= 0){
        index += 1
      }else{
        if(!foundFirstNegative){foundFirstNegative = true; index +=1}
        else{a.remove(index);arrayLength -=1}
      }
    }
  }

  /*
  方法2
   */
  def removeNegValExceptFirst02(): Unit ={
    val a = ArrayBuffer[Int]()
    a += (1,2,3,4,5,-1,-3,-5,-9)

    //每记录所有不需要移除的元素索引，稍后一次性移除所有需要移除和元素
    //性能较高，数组内的元素迁移只要执行一次即可
    var foundFirstNegative = false
    var keepIndexes = for(i <- 0 until a.length if !foundFirstNegative || a(i) >= 0) yield {
      if(a(i) <0) foundFirstNegative=true
      i
    }
    for(i <- 0 until keepIndexes.length) { a(i) = a(keepIndexes(i))}
    a.trimEnd(a.length-keepIndexes.length)
    println(a.mkString(","))
  }
}