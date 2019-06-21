import scala.io.BufferedSource
import scala.io.Source._
import java.io._
import scala.collection.mutable.ArrayBuffer

class HelloScala {
  //函数
  def sayHello(name: String, age: Int) = {
    if (age > 18) {
      printf("hi %s, you are a big boy!!!\n", name)
    }
    else printf("hi %s, you are little boy", name)
  }

  def fab(n: Int): Int = {
    if (n <= 0) 1
    else fab(n - 1) + fab(n - 2)
  }

  //默认参数
  def defaultParam(name: String = "defaultName") = {
    print("you name is " + name + "\n")
  }

  //可变长度参数
  def dynamicParam(nums: Int*): Int = {
    var result = 0
    for (num <- nums) {
      result += num
    }
    result
  }

  def oneLineMethodWithReturn(name: String) = "Hello" + name

  def onelineMethodWithoutReturn(name: String): Unit = {
    println("hello %s", name)
  }

  def lazyVar(): BufferedSource = {
    lazy val lines = fromFile("C:\\temp\\3.txt")
    lines
  }

  def tryException() = {
    try {
      throw new IOException("x should be negative")
    } catch {
      case _: IllegalArgumentException => println("illegal argument!!!")
      case _: IOException => println("IOException:illegal argument!!!")
    } finally {
      println("release")
    }
  }

  def arrayCollectionUsage(size:Int) = {
    val a = new Array[Int](size)
    val stringArray= new Array[String](size)
    val strArr = Array("hello","String","array")
    a(0) = 100
    println(a(0))
    println(strArr)

    val strArrayBuffer = ArrayBuffer("hello", "String")
    strArrayBuffer += ("more", "elements")
    strArrayBuffer ++= Array("more","array")
    strArrayBuffer.insert(5,"fithSec","sixth")
    strArrayBuffer.remove(5)
    println(strArrayBuffer)
    val strArrBufferFromArray = strArr.toBuffer
    println(strArrBufferFromArray)

    for(i<- 0 until strArrBufferFromArray.length)
      print(strArrBufferFromArray(i)+", ")
    println("")
    //跳跃遍历
    for(i<- 0 until (strArrayBuffer.length,2))
      print(strArrayBuffer(i)+", ")
    println("")
    //从后遍历
    for(i<- (0 until strArrBufferFromArray.length).reverse)
      print(strArrBufferFromArray(i)+", ")

    println("")
    //使用增强for循环遍历array/arraybuffer
    for(e <- strArrBufferFromArray)
      print(e)
  }
}


object Test {
  def main(args: Array[String]): Unit = {
    val pt = new HelloScala()
    pt.sayHello("leo", 100)
    //print(pt.fab(15))
    pt.defaultParam()
    println(pt.dynamicParam(100, 99))
    println(pt.dynamicParam(1 to 5: _*)) // 1 到 5 加法   : _*  将参数识别为序列
    println(pt.oneLineMethodWithReturn("patrick"))
    println(pt.lazyVar())
    pt.tryException()
    pt.arrayCollectionUsage(10)
  }
}