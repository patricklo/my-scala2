/**
 * Created by Patrick on 23/6/2019.
 */


object FunctionsScala{
  def main(args: Array[String]){

    //scala的面向过程: 函数

    //函数赋值给变量
    def sayHello(name: String)= println("hello, "+name)
    val sayHelloFunc = sayHello _   /// 空格加_  将函数赋给变量
    sayHelloFunc("hello")


    //匿名函数
    val sayHelloFunc2 = (name:String) => {println("Hello,"+name)}

    //高阶函数 - 接入其它函数作为参数的函数 称为高阶函数
    def greeting(func: (String)=> Unit, name: String) {func(name)}
    greeting(sayHelloFunc,"高阶函数")

    //高阶函数 - 将函数作为返回值
    def getGreeting(msg: String) = (name:String) => {println(msg+", :"+name)}
    val getGreetingFunc = getGreeting("HIHI (msg passed)")
    getGreetingFunc("name passed")

    //高阶函数的类型推断: 可以自动推断 不需要写明类型
    def greetingParamType(func: String => Unit, name:String) {func(name)}
    greetingParamType((name: String) => println("Hello,"+name), "Leo")
    greetingParamType((name) => println("Hello,"+name), "Leo")    //可以省略string类型定义
    greetingParamType(name => println("Hello,"+name), "Leo")  //可以进一步省略括号
    //  3 * _ 语法
    def triple(func: (Int) => Int) = func(3)
    println(triple(3 * _))

    //常用高阶函数
    //map flatmap zip reduce reduceleft
    println(Array(1,2,3,4,5).map(2 * _)) //所有元素 * 2

    (1 to 9).map("*" * _).foreach(println _)

    (1 to 9).filter(_ % 2 == 0).foreach(println _)
    //从左侧开始进行reduce操作 即先对第1和第2个元素进行处理,然后拿结果和第3个元素处理,以此类推
    //相当于是 1 * 2 * 3 * 4 * 5 * 6 * 7 * 8 * 9
    println((1 to 9).reduceLeft(_ * _))

    //sortWith
    Array(3,2,5,4,10,1).sortWith(_ < _).foreach(println _)


    //闭包???  - > 即函数在变量不处于有效作用域时 还能对变量进行访问,即为闭包
   //msg: 即为闭包,一次传递后,后面反复调用,msg的值还在
    def getGreeting2(msg: String) = (name:String) => {println(msg+", :"+name)}
    val getGreeting2Func1 = getGreeting2("HIHI (msg getGreeting2Func1)")
    val getGreeting2Func2 = getGreeting2("HIHI (msg getGreeting2Func2)")
    getGreeting2Func1("patrick1")
    getGreeting2Func2("patrick2")


    //SAM 转换  - single abstract method
    //scala支持传入一个函数作为参数,但java不支持,因此scala在调用 java时,如需要用到类似功能,需用到sam 转换

  }
}


