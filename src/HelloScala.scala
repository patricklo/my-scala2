import scala.beans.BeanProperty
import scala.io.BufferedSource
import scala.io.Source._
import java.io._
import scala.collection.mutable.ArrayBuffer
import scala.beans.BeanProperty
//scala 2.11.7
class HelloScala {

  var varFiled = "varfile"  //会定义为private 并生成public getter(varField) setter(varField_)  可以自定义
  // def varField_=(newValue: String) {}

  val valFile = "valField"  //只会生成getter方法

  private var class_name="name" //会生成private getter setter

  private[this] var no_getter_setter_var = "no getter setter"   //不会生成getter setter 方法

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

    println("")
    val intArr = Array(9,6,1,1,2,3,9,5,6)
    println(intArr.sum)
    println(intArr.max)
    scala.util.Sorting.quickSort(intArr)
    println(intArr)
    println(intArr.mkString("===="))


    //数组高级操作：转换
    //普通方法
    val ab = Array(1,2,3,4,5)
    val a2 = for(ele <- ab) yield ele * ele

    //高级方法
    val abb = ab.filter(_ % 2 ==0).map(2*_)  // _ 代表每一个元素
    val abb2 = ab.filter(_ % 2 !=0).map{2*_}  // _ 代表每一个元素
    println(abb.mkString(","))
    println(abb2.mkString(","))


    val examplearray = ArrayBuffer[Int]()
    examplearray += (1,2,3,4,5,-1,-3,-5,-7) //删除负数

    var foundFirstNegative=false
    val keepIndexes = for(i <- (0 until examplearray.length) if !foundFirstNegative || examplearray(i) >= 0) yield {
      if (examplearray(i) < 0) foundFirstNegative = true
      i
    }
    for (i <- (0 until keepIndexes.length)) { examplearray(i) = examplearray(keepIndexes(i))}
    examplearray.trimEnd(examplearray.length - keepIndexes.length)
    println(examplearray.mkString(","))
  }

  def mapUsage() = {
    val ages = Map("leo"-> 20,"jen"->20,"me"->18)
    val new_args = ages + ("patrick"->18)
    println(new_args)
    //ages("Leo") = 31  //默认不可修改
    val mutable_ages = scala.collection.mutable.Map("leo"-> 20,"jen"->20,"me"->18)
    val mutable_ages_2 = scala.collection.mutable.Map(("leo", 20),("jen",20))
    mutable_ages("leo") = 31
    println(mutable_ages("leo"))
    val remove_Leo = mutable_ages - "leo"
    println(remove_Leo)
    for((key,value) <- mutable_ages) println(key +"::"+value)
  }

  def tupleUsage() ={
    val t =("leo",20)
    println(t._1)
    println(t._2)
    val names = Array("leo","jac","mike")
    val ages = Array(30,24,26)
    val nameArgsTuple = names.zip(ages)
    println(nameArgsTuple)
    for((name,ages) <- nameArgsTuple) println(name+":"+ages)
  }
}

class ValPrivate{
  private var myAge = 0 //类私有,即类内部可以访问并个性
  private[this] var privateThisAge = 100  //仅限当前实例访问,其它实例不能访问

  def anotherAge_= ( newValue : Int ){//主语 value_= 是方法名字
    if(newValue > 0) myAge = newValue
    else println("illegal age input")
  } //注意，scala中默认方法是public的

  def anotherAge  = myAge
  def older(s:ValPrivate)={
    anotherAge>s.anotherAge
  }
}

class JAVAStyleSetterGetter(@BeanProperty var name:String ){

}


class ScalaConstructor{
  //构造器
  //辅助构造器, 主构造器


  //辅助构造器:
  private var var1 = ""
  private var var2 = ""
  def this(var1:String){
    this() //主构造器  this(var1) this(var1,var2) 辅助构造器
    this.var1 = var1
  }
  def this(var1:String, var2:String){
    this(var1)
    this.var2 = var2
  }
}

class MyClass{
  class MyStudent(val name:String) {}
    val students=new ArrayBuffer[MyStudent]
    def getStudent(name:String) ={
      new MyStudent(name)
    }
}


//object 相当于class的一个实例,其构造器只会在第一次调用的时候调用一次,可用于作为单例模式的class和工作类,甚至可用于放置class对象的静态属性和成员
//如果有同名的一个class和object,则称为伴生,Object和class必须放在同一个scala文件中,同名Object和class最大特点是可以访问彼此的private fields
object Test {
  def main(args: Array[String]): Unit = {
    val pt = new HelloScala()
    pt.sayHello("leo", 100)
    //print(pt.fab(15))
    pt.defaultParam()
    println(pt.dynamicParam(100, 99))
    println(pt.dynamicParam(1 to 5: _*)) // 1 到 5 加法   : _*  将参数识别为序列
    println(pt.oneLineMethodWithReturn("patrick"))
    //println(pt.lazyVar())
    pt.tryException()
    pt.arrayCollectionUsage(10)
    pt.mapUsage()
    pt.tupleUsage()

    var valPrivate = new ValPrivate
    var valPrivate2 = new ValPrivate
    valPrivate.anotherAge=30
    println(valPrivate.older(valPrivate))

    val javaStyle = new JAVAStyleSetterGetter("ppp1")
    javaStyle.setName("ppp2")
    println(javaStyle.getName())

    val scalaConstrustor = new ScalaConstructor //调用主构造器
    //修改主构造器,在类定义里
    // class ScalaConstructor(Var1: String, Var2: String ){
    val scalaConstrustor1 = new ScalaConstructor("var1") //调用辅助构造器
    val scalaConstrustor2 = new ScalaConstructor("var1", "var2") //调用辅助构造器


    val c1 = new MyClass
    val s1 = c1.getStudent("leo")
    c1.students += s1

    val c2 = new MyClass
    val s2 = c2.getStudent("leo")
    //c1.students += s2

  }
}