

/**
  * 闭包 Closure
  * 闭包最简洁的解释：函数在变量不处于其有效作用域时，还能够对变量进行访问，即为闭包。
  *
  * 下方例子中，2次调用getGreetingFunc02函数，不同的Msg值，并创建不同的函数返回
  * 然而，msg只是个局部变量，却在getGreetingFunc02执行完后，还可以继续存在创建的函数中，保持值不改变
  * Hello 被保存为msg中，不被改变； Hi 也被保存。
  *
  * 这种变量超出作用域，还可以使用的情况，称之为闭包
  *
  * Scala 编译器会确保上述闭包机制
  */

object ClosuresScala {
  def main(args: Array[String]): Unit = {
    def getGreetingFunc02(msg:String) = (name:String) => println(msg +", "+name)
    val greetingMsg01 = getGreetingFunc02("Hello")
    val greetingMsg02 = getGreetingFunc02("Hi")
    greetingMsg01("Patrick")
    greetingMsg02("Jack")
    greetingMsg01("Icy")

  }
}