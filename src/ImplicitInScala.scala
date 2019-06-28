
object ImplicitInScala {
 //scala提供 隐式转换 和 隐式参数 的功能。 它可以允许你手动指定，将某种类型的对象转换成其他类型的对象。

  //隐式转换，最核心的是定义隐式转换函数，即implicit conversion function, 会被scala自动引用。
  //隐式转换中的隐式转换函数通常叫什么名字无所谓，但建议函数名字为  one2another的形式、


  //作用域或导入隐式函数类型
  //1.当前程序作用域
  //2.源类型中或者目标类型的伴生对象内的隐式转换函数
  //3.手动import

  implicit def object2SpecialPerson(obj: Object):SpecialPerson = {
    if(obj.getClass == classOf[SpecialStudent]) { val stu = obj.asInstanceOf[SpecialStudent]; new SpecialPerson(stu.name)}
    else if (obj.getClass == classOf[SpecialStudent]) { val stu = obj.asInstanceOf[SpecialStudent]; new SpecialPerson(stu.name)}
    else new SpecialPerson("")
  }


  var ticketNumber = 0
  def buySpecialTicker(p: SpecialPerson) = {
    ticketNumber += 1
    "T-"+ticketNumber
  }

  implicit def man2Superman(man:Man):Superman = new Superman(man.name)




  //隐式参数，指的是在函数或者方法中，定义一个用implicit修饰的参数，此时scala会尝试找到一个指定类型的对象
  implicit val signPen = new SignPen
  def signForExam(name:String)(implicit signPen: SignPen): Unit ={
    signPen.write(name+" come to the exam in time")
  }

  def main(args: Array[String]): Unit = {

    val specialStudent = new SpecialStudent("SpecialStudent01")
    val olderPerson = new OlderPerson("OlderPerson01")
    println(buySpecialTicker(specialStudent))
    println(buySpecialTicker(olderPerson))

    val man = new Man("man")
    man.emitLaser

    //隐式参数，指的是在函数或者方法中，定义一个用implicit修饰的参数，此时scala会尝试找到一个指定类型的对象
    signForExam("Patrick")

  }
}


class SpecialPerson(val name:String)
class SpecialStudent(val name:String)
class OlderPerson(val name:String)

class Man(val name:String)
class Superman(val name: String){
  def emitLaser = println("emit a laser")
}



//隐式参数，指的是在函数或者方法中，定义一个用implicit修饰的参数，此时scala会尝试找到一个指定类型的对象

class SignPen{
  def write(content:String) = println(content)
}