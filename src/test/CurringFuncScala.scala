

/**
  * Curring 函数，指的是，将原来接收的2个参数的1个函数，转换为2个函数
  * 第1个函数接收原来的第1个参数，然后返回接收原先的第2个参数的第2个函数
  * 在函数调用的过程中，就变为了2个函数连续调用的形式
  *
  * 好处是？？？
  *
  * spark源码中有大量使用
  */

object CurringFuncScala {
  def main(args: Array[String]): Unit = {
    def sum(a:Int,b:Int) = a+b
    println(sum(1,1))

    def sum2(a:Int) = (b:Int) =>a+b
    println(sum2(1)(1))

    def sum3(a:Int)(b:Int) = a+b
    println(sum3(1)(2))
  }
}