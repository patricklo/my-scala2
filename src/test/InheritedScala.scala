/**
 * Created by Patrick on 22/6/2019.
 */
object InheritedScala {
  def main(args: Array[String]): Unit = {
    val s1 = new Student
    println(s1.getName)
    println(s1.age )

    ///1. isInstanceOf asInstanceOf  父/子类互相转换
    val p: Person = new Student
    var s: Student = null
    if(p.isInstanceOf[Student])  s = p.asInstanceOf[Student]
    println(s.getName)

    //2. getClass classOf   精确判断是否指定同一类型
    println(p.isInstanceOf[Person])
    println(p.isInstanceOf[Student])
    println(p.getClass == classOf[Person])
    println(p.getClass == classOf[Student ])

    //3. 模式匹配方式: 非精准判断
     p match{
       case per: Person => println("it's Person object")
       case _ => println("unkown")
     }


    s.getProtectedVars(s)
  }
}


class Person{
  private var name ="hello"  //private 字段不能被子类继承修改
  val age = 0
  def getName = name

  protected var protectedvar = "protect"
  protected[this] var protectedthisvar:String = "protect[this]"
}

class Student extends Person{
 private var score = "A"
  def getScore = score

  override val age = 16
  //覆盖父类方法,如后续还需调用 使用super关键字
  override def getName = "hi, Student "+ super.getName

  def getProtectedVars(s:Student){
    println("protected var value="+protectedvar+"---"+protectedthisvar)
    //不能使用s.protectedthis 变量
    println("protected var value="+s.protectedvar)
    //println("protected var value="+s.protectedvar+"---"+s.protectedthisvar)
  }
}