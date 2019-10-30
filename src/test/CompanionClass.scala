/**
 * Created by Patrick on 22/6/2019.
 */
class CompanionClass {
  def sayHello = println("hi,"+CompanionClass.name)

}

object CompanionClass{
  private val name="this is Companion Object and Class"
}

//抽象类
abstract class ABSHelloClass(var message:String) {
  val absVar: String  //抽象field
  def sayHello(name: String): Unit
}

object Hello extends ABSHelloClass("hello"){
  override def sayHello(name: String) = {
    println(message +" "+name)
  }

  override val absVar: String = "abs varibale from parent class"
}


//apply方法
//通常在伴生对象object中实现apply方法, 并在其实现构造伴生类class的对象的功能 ?
//而在创建伴生类的时候, 通常不会使用new Class()的方式 , 而是使用Class()的方式 ,即代表隐式的调用伴生对象的apply方法,使用代码更简洁
//如Array类就实现了apply方法, 在初始化创建array对象的时候直接 使用  var arr = Array("var1", "var2")

object MainTest {
  def main(args: Array[String]): Unit = {
    val companio = new CompanionClass
    companio.sayHello



    Hello.sayHello("patrick")
  }
}
