/**
 * Created by Patrick on 22/6/2019.
 */

//Trait类似java的接口类似 ,但又更多功能和复杂用法
//用extends继承trait,支持多重继承traits

//trait 调用链
object TraitScalaAdvance2 {
  def main(args: Array[String]): Unit = {
    val p = new Person002("Patrick")
    p.sayHello
  }
}

trait Handler{
  def handle(data:String){}
}

trait DataValidHandler extends Handler{
   override def handle(data: String): Unit = {println("check data:"+data); super.handle(data);}
}

trait SignatureValidHandler extends Handler{
   override def handle(data: String): Unit = { println("check signature:"+data);super.handle(data);}
}


class Person002(val name:String) extends SignatureValidHandler with DataValidHandler{
  def sayHello = { println("hello," + name); handle(name)}
}

////伴生类 伴生对象
//object Person002 {
//
//}