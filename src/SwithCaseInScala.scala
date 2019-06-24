import java.io._

object SwithCaseInScala {



  //模式匹配是scal中相当于java 的switch语句,功能要强大得多

  def judgeGrade(name:String, grade: String): Unit ={
    grade match{
      case "A" => println("Excellent")
      case "B" => println("GOOD")
      case "C" => println("FINE")
      case badGrade => println("you get "+ badGrade+" grade, hope you can get better")
      //还可以使用if守卫
      case _ if name=="leo" => println(name+", you are a good boy")
      case _ => println("YOU NEED TO WORK HARD") //其它情况
    }
  }

  def processExcpeiton (e:Exception): Unit ={
    e match {
       case e1:IllegalArgumentException => println("you pass illeage args")
       case e1:FileNotFoundException => println(" can not find file")
       case e1: IOException => println("IO EXCEPITON")
       case _: Exception => println("can not know which excpeiton you have")
    }
  }

  def greeting(arr: Array[String]): Unit ={
    arr match {
      case Array("Leo") => println("Hi leo")
      case Array(gril1, gril2, gril3 ) => println("Hi"+gril1+gril2+gril3)
      case Array("Leo",_*) => println("Hi, leo, pls intro others to me")
      case stranger => println("hi Stranger")  //或者用  _
      //case _=> println("who you?")
    }
  }

  def greeting(list: List[String]): Unit ={
    list match {
      case "Leo" :: Nil => println("Hi leo")   //集合类， :: 用于拼接
      case gril1 :: gril2 :: gril3 :: Nil => println("Hi"+gril1+gril2+gril3)
      case "Leo" :: tail => println("Hi, leo, pls intro others to me")
      case _=> println("who you?")
    }
  }

  //scala提供一种特殊的类，用case class进行声明，中文也可以叫样例类，跟Java中的java bean类似。 即只定义 field，并且由scala自动提供getter setter，但是没有其它method
  //case class 的主构造函数接收的参数通常不需要使用var 或 val 修饰，scala自动就会使用val修饰
  //scala自动为case class定义了伴生对象， 也就是Object， 并且定义了apply方法，该方法接收主构造函数中相同的参数，并返回case class 对象
  def judegID(p:Parent01): Unit ={
    p match{
      case Child01(name, subject) => println("this is Child01 "+name +" - "+subject)
      case Child02(name, classroom) => println("this is Child02 "+name +" - "+classroom)
      case _ => println("illegal access")
    }
  }

  //scala中的特殊类型  =》  Option, 有两种值，一种是Some,表示有值，一种是None,表示没有值
  //Option通常用于模式匹配中，用于判断有无值
  val grades = Map("Leo"->"A", "Jack"->"B", "Jen"->"C")
  def getGrade(name: String): Unit ={
    val grade = grades.get(name)
    grade match {
      case Some(grade) => println("your grade is "+grade)
      case None => println("sorry, you grade is null")
    }
  }



  def main(args: Array[String]) {
    judgeGrade("leo","aaaa")
    processExcpeiton(new IllegalArgumentException("bad args"))
    processExcpeiton(new FileNotFoundException("bad file"))
    processExcpeiton(new IndexOutOfBoundsException("index error"))
    greeting(Array("Leo","jassie","lucy"))
    greeting(List("Leo","jassie","lucy"))
    val c01: Parent01 = new Child01("child01","abc")
    val c02: Parent01 = new Child02("child02","2nd floor")
    judegID(c01)
    judegID(c02)
    getGrade("Leo")
    getGrade("Patrick")
  }



}

//scala提供一种特殊的类，用case class进行声明，中文也可以叫样例类，跟Java中的java bean类似。 即只定义 field，并且由scala自动提供getter setter，但是没有其它method
//case class 的主构造函数接收的参数通常不需要使用var 或 val 修饰，scala自动就会使用val修饰
//scala自动为case class定义了伴生对象， 也就是Object， 并且定义了apply方法，该方法接收主构造函数中相同的参数，并返回case class 对象
class Parent01
case class Child01(name:String, subject:String) extends Parent01
case class Child02(name:String, classroom:String) extends Parent01




