/**
 * Created by Patrick on 22/6/2019.
 */

//除了定义def main(args: Array[String]) { 方法之外
//方法2:
object MainEntry extends App{
  if(args.length>0) println("hello, "+args(0))
  else println("hello ")
}
