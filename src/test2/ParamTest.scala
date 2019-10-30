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
  }
}

//默认参数值
class DefaultParam(){
  def defaultParamValue(firstName:String, middleName: String = "mid", lastName: String="last"): Unit ={
      print(firstName+middleName+lastName)
  }
}


//可变长参数
class DynamicParam(){
  def dynamic(nums: Int*) = {
    var result =0
    for(num <- nums){
      result += num
    }

    result
  }
}

//过程的用法
//过程通常用于不用返回值的函数
//函数体直接包裹在花括号里，而没有使用 = 连接，则函数的返回值类型就是Unit,这种函数就被称为过程。
class Demo01(){
def demoFunc01(name:String) = "Hello,"+name
def demofunc02(name:String){print("hello,"+name); "hello,"+name}
def demofunc03(name:String):Unit="hello,"+name
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