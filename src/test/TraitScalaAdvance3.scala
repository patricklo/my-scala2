/**
 * Created by Patrick on 22/6/2019.
 */

//Trait类似java的接口类似 ,但又更多功能和复杂用法
//用extends继承trait,支持多重继承traits



//trait中没有接收参数的构造函数，这是trait 和 class的唯一区别
//如果有需求就是要trait能够对field进行初始化，可以使用scala中非常特殊的高级特性 ---- 提前定义
object TraitScalaAdvance3 {
  def main(args: Array[String]): Unit = {
    val p = new Person002("Patrick")
    p.sayHello
  }
}

trait SayHello{
  val msg:String
  println(msg.toString)
}

////方法1 提前定义
//class PersonA3
//val p = new {val msg:String = "pre-init"} with PersonA3 with SayHello
//
////方法2 提前定义
//class Person extends {
//  val msg:String = "pre-init"
//} with SayHello{}
//
////方法3： lazy value
//trait SayHello2{
//  lazy val msg:String = null
//  println(msg.toString)
//}

