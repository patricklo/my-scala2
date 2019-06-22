/**
 * Created by Patrick on 22/6/2019.
 */



//apply方法
//通常在伴生对象object中实现apply方法, 并在其实现构造伴生类class的对象的功能 ?
//而在创建伴生类的时候, 通常不会使用new Class()的方式 , 而是使用Class()的方式 ,即代表隐式的调用伴生对象的apply方法,使用代码更简洁
//如Array类就实现了apply方法, 在初始化创建array对象的时候直接 使用  var arr = Array("var1", "var2")
class ApplyFunction (name: String)

object ApplyFunction{
  def apply(name: String )= new ApplyFunction(name)
}

object MainTest001{

  def main(args: Array[String]) {
    val class1 = ApplyFunction("patrick")

  }
}