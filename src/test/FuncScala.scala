/**
 * Created by Patrick on 22/6/2019.
 */

//Scala函数式编程

object FuncScala {
  def main(args: Array[String]): Unit = {


    //1. 将函数直接赋给变量
    def sayFunc(name:String) = println("hello, "+name)
    val sayHelloFunc = sayFunc _
    sayHelloFunc("Patrick")

    //2. 匿名函数
    //在scala中，函数可以不需要命名，此时函数称为匿名函数
    //可以直接定义匿名函数后，将函数赋值给变量； 或者可以直接定义的匿名函数传入其他函数之中
    //scala定义匿名函数的语法规则是：  (参数名:参数类型) => 函数体
    //这是匿名函数体在spark中有大量的体现
    val sayHelloAnonyFunc = (name:String) => println("hello,"+name)
    sayHelloAnonyFunc("匿名函数")

    //3. 高阶函数 higher-order function
    //在scala中，由于函数是一等公民，因此可以直接将某个函数传入其他函数，作为参数。这个功能是极其强大的，也是Java不具备的
    //3.1 接收其他函数作为参数的函数，称为高阶函数
    val sayHelloHOFunc = (name:String) => println("hello sayHelloHOFunc, "+name)
    def greeting(func:(String) => Unit, name:String){func(name)}
    greeting(sayHelloHOFunc,"Patrick")

    val newArray = Array(1,2,3,4,5).map((num:Int) => num*num)

    //3.2 将函数作为返回值
    def getGreetingFunc(msg:String) = (name:String) => println(msg +" , "+name)
    val greetingFunc = getGreetingFunc("hello")
    greetingFunc("Patrick")

    //3.3 高阶函数的类型推断
    //高阶函数可以自动推断出参数类型，而不需要写明类型； 同时对于只有一个参数的函数，还可以省去其小括号；
    //如果仅有的一个参数在右侧的函数体内只使用一次，则还可以将接收参数省略，并且将参数 用_来替代
    def greeting2(func:(String) => Unit,name:String) { func(name)}
    greeting2((name:String) => println("hello, "+name),"leo")
    greeting2((name) => println("hello, "+name),"leo")
    greeting2(name => println("hello, "+name),"leo")

    def triple(func:(Int)=>Int) = {func(2)}
    println(triple(3 * _))

    //3.4 其它常用的高阶函数
    //map
    Array(1,2,3,4,5).map(2 * _)
    //foreach 对传入的每个元素都进行处理，但是没有返回值
    (1 to 9).map("*" * _).foreach(println _)
    //filter 对传入的每个元素都进行条件判断，如果对元素返回 true，则保留该元素
    (1 to 20).filter(_ % 2 ==0)
    //reduceLeft 从左侧元素开始，进行reduce 操作，即先对元素1和元素2 进行处理，然后将结果与元素3处理，再将结果与元素4处理，依次类推，即为reduce
    //下面的操作相当于 1 * 2 * 3 * 4 * 5 * 6 * 7 * 8 * 9
    (1 to 9).reduceLeft(_ * _)
    //sortWith： 对元素进行两两对比 ，进行排序
    Array(3,2,5,4,10,1).sortWith(_ < _)


  }
}


