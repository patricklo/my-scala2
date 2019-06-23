/**
 * Created by Patrick on 23/6/2019.
 */
import javax.swing._
import java.awt.event._

object SAMScala{
  def main(args: Array[String]){
    //SAM 转换  - single abstract method
    //scala支持传入一个函数作为参数,但java不支持,因此scala在调用 java时,如需要用到类似功能,需用到sam 转换
    val button = new JButton("click")
    button.addActionListener(new ActionListener {
      override def actionPerformed(e: ActionEvent): Unit ={
        println("click me!!!")
      }
    })


    ///button.addActionListener((event: ActionEvent) => println("click me!!!!!"))
    //上面代码 报错 直接传一个匿名内部类给java代码是不能通过编译的
    //这时候可以用到SAM功能进行转换

    implicit def convertActionListener(actionPressFunc: (ActionEvent)=> Unit) = new ActionListener {
      override def actionPerformed(event: ActionEvent){
      actionPressFunc(event)
      }
    }

    button.addActionListener((event: ActionEvent) => println("click me!!!!!"))  //这时候不搞错了!!!



    //Curring 函数 : 指的是原接收2个参数的一个函数,转换为2个函数
    def sum(a: Int, b: Int) = a + b
    def sum2(a:Int)(b: Int) = a + b  //curring 函数

    def sum3(a: Int) = (b: Int) => a+ b  // curring 函数


    //return
    //scala 中不需要使用return来返回 函数值, 其使用最后一行的函数值 作为返回值
    //return在scala中用于在匿名函数中返回值 给包含匿名函数的带名函数,燕作为带名函数的返回值
    //使用return的匿名函数,是必须给出返回类型的,不然会编译出错
    def returnGreeting(name: String) ={
      def sayHello(name:String):String = {
        return "hello" + name
      }
    }

    println(returnGreeting("return in scala"))
  }
}


