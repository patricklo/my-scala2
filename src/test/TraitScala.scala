/**
 * Created by Patrick on 22/6/2019.
 */

//Trait类似java的接口类似 ,但又更多功能和复杂用法
//用extends继承trait,支持多重继承traits
object TraitScala {

  def main(args: Array[String]): Unit = {
    val p = new Person001("patrick")
    p.sayHello("Leo")
    val p01 = new Person001("Leo")
    p.makeFriends(p01)
  }

}

trait HelloTrait{
  //简单当成接口来用,定义抽象方法
  def sayHello(name: String)
}

trait LoggerTrait{
  def log(message: String) = println(message)
}

trait MakeFriendTrait{
  //会被继承到实现类中,eyeNum直接被添加到下面的Person001类中, 与父子类的继承不一样,
  val eyeNum = 2
  //抽象field
  val message: String
  def makeFriends(p: Person001)
}
//从右到左 依次调用trait
class Person001(val name: String) extends HelloTrait with MakeFriendTrait with Cloneable with Serializable with LoggerTrait{
  override def sayHello(name: String) = println("hello, "+ name)

  override def makeFriends(p: Person001): Unit ={
    println("hello, my name is "+name +", your name is "+p.name)
    log("log: make friends")
    println("eyeNum:"+eyeNum)
    println("message:"+message)
  }

  override val message: String = "Message Person001"
}