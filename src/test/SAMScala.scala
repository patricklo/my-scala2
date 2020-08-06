

/**
  * SAM转换
  *
  * 在java中，不支持直接将函数传入一个方法作为参数，通常来说，唯一的方法就是定义一个实现了某个接口的类的实例对象，该对象只有一个方法；
  * 而这些接口都只有单个的抽象方法，也就是single abstract method ,即SAM
  *
  * 由于scala是可以调用Java代码的，因此当我们调用java的某个方法时，可能就不得不创建SAM传递给方法，非常麻烦；
  * 但是scala又是支持直接传递函数的。
  *
  * 此时就可以使用SCALA提供的，在调用 Java方法时，使用SAM转换功能，即将SAM转换成scala函数
  *
  * 要使用sam转换，需要使用scala提供的特性，隐式转换
  */

import javax.swing._
import java.awt.event._

object SAMScala {
  def main(args: Array[String]): Unit = {
    val javaButton = new JButton("Click")
    javaButton.addActionListener(new ActionListener {
      override def actionPerformed(e: ActionEvent): Unit ={
        println("Click ME!")
      }
    })

    implicit def getActionListener(actionProcessFunc:(ActionEvent) => Unit) = new ActionListener {
      override def actionPerformed(event: ActionEvent): Unit ={
        actionProcessFunc(event)
      }
    }

    javaButton.addActionListener((event:ActionEvent)=>println("Click MEEE!"))

  }
}