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
  }

}

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