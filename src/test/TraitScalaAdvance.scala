/**
 * Created by Patrick on 22/6/2019.
 */

//Trait类似java的接口类似 ,但又更多功能和复杂用法
//用extends继承trait,支持多重继承traits
object TraitScalaAdvance {
  def main(args: Array[String]): Unit = {
    val p = new Person005("patrick") with MyLogger
    p.sayHello

    val p6 = new Person006("patrick")
    p6.sayHello

    val test = new TestClass //constructor 顺序执行
    //先是父类的构造器
    //然后从左到右 执行trait的构造器


    //trait提前定义 , 因为trait中没有接收参数的构造函数, 如何对Field进行提前定义呢
   //方法1
     val tt = new GiveTraitValue
    //方法2 lazy value
    val tt01 = new SayHelloP


    //trait继承class
    val ssss = new ClassUseUtil("test")
    ssss.sayHello
  }

}


class MyUtils{
  def printMsg(msg: String): Unit ={
    println(msg)
  }
}

trait MyUtilTrait extends MyUtils{
def log(log:String) = printMsg(log)
}

class ClassUseUtil(val name: String) extends MyUtilTrait{
  def sayHello: Unit ={
    log("hi,"+name)
    printMsg("hhhhhhh")
  }
}

trait SayHelloLazyValue{
  lazy val msg: String = null
  println(msg.toString)
}
class SayHelloP extends SayHelloLazyValue{
  override lazy val msg: String = "lazy init"
}

trait SayHelloTrait{
  val msg:String
  println(msg.toString)
}

class GiveTraitValue extends {
  val msg: String = "Init value"
} with SayHelloTrait()

trait LoggerTrait01{
  def log(message: String){}
}

trait MyLogger extends LoggerTrait01{
  override def log(message: String) = println("MyLogger: " + message)
}

trait AbsMyLogger extends LoggerTrait01{
  abstract override def log(message: String) {super.log(message)} //override覆盖 LoggerTrait01 的log方法
}

class Person005(val name: String) extends LoggerTrait01{
  def sayHello: Unit ={
    println("Hi,"+name)
    log("say hello is invoked")
  }
}


trait DataHandler{
  def handler(data: String){}
}

trait ValidDataHandler extends DataHandler{
  override def handler(data: String) {
    println("1. ValidDataHandle:"+data)
    super.handler(data)
  }
}

trait SignatureDataHandler extends DataHandler{
  override def handler(data:String): Unit ={
    println("2. SignatureDataHandler: "+data)
    super.handler(data)
  }
}

//从右到左 依次调用trait   ValidDataHandler->SignatureDataHandler
class Person006(val name: String) extends SignatureDataHandler with ValidDataHandler{
  def sayHello = {println("hello, "+ name); handler(name)}
}


//trait中的构造代码,是所有不在方法里的代码
class Person007{println("Person007 constructor")}
trait LoggerPerson007{println("LoggerPerson007 constructor")}
trait LoggerPerson007Extends extends LoggerPerson007{println("LoggerPerson007Extends constructor")}
trait TimeLogger extends LoggerPerson007{println("TimeLogger constructor")}
class TestClass extends Person007 with LoggerPerson007Extends with TimeLogger {println("TestClass constructor")}












