
object ImplicitInScala {
 /**
   *
   * scala提供 隐式转换 和 隐式参数 的功能。 它可以允许你手动指定，将某种类型的对象转换成其他类型的对象。
   * 隐式转换，最核心的是定义隐式转换函数，即implicit conversion function, 会被scala自动引用。
   * 隐式转换中的隐式转换函数通常叫什么名字无所谓，但建议函数名字为  one2another的形式、
*/
  //作用域或导入隐式函数类型
  /**1.隐式转换*/

 class SpecialPerson(val name:String)
  class SpecialStudent(val name:String)
  class OlderPerson(val name:String)

  implicit def object2SpecialPerson(obj: Object):SpecialPerson = {
    if (obj.getClass == classOf[SpecialStudent]) {
      val stu = obj.asInstanceOf[SpecialStudent]; new SpecialPerson(stu.name)
    }
    else if (obj.getClass == classOf[OlderPerson]) {
      val stu = obj.asInstanceOf[OlderPerson]; new SpecialPerson(stu.name)
    }
    else Nil
  }

  var ticketNumber = 0
  def buySpecialTicker(p: SpecialPerson) = {
    ticketNumber += 1
    "T-"+ticketNumber
  }

  class Teacher(val name:String)
  val tea = new Teacher("teacher")
  //byte2Byte(tea)  不可以
  val leo = new SpecialStudent("leo")
  println(buySpecialTicker(leo))


  /**2.使用隐式转换来加强现有类型*/
  class Man(val name:String)
  class Superman(val name: String){
    def emitLaser = println("emit a laser")
  }
  implicit def man2Superman(man:Man):Superman = new Superman(man.name)
  val man = new Man("man")
  man.emitLaser
  /**3. 隐式函数转换函数的作用域与导入
    * scala会默认使用两种隐式转换，一种是源类型，或者目标类型的伴生对象内的隐式转换函数
    *                           一种是当前程序作用哉内的可以用唯一标识符表示的隐式转换函数
    * 如果隐式转换函数不在上述2种情况下的话，那就必须手动使用Import语法引入某个包下的隐式转换函数，比如import test._
    * 通常建议仅在需要使用隐式转换的地方进行Import
    * */

  /**4. 隐式转换的发生时机
    *   1. 调用某个函数，但是给函数传入的参数的类型，与函数定义的接收参数类型不匹配
    *   2. 使用某个类型的对象，调用某个方法，而这个方法并不存在于该类型中
    *   3. 使用某个类型的对象，调用某个方法，虽然该类型有这个方法，但是给方法传入的参数类型与原方法定义的不一致。
    */

  /**5.隐式参数，指的是在函数或者方法中，定义一个用implicit修饰的参数，此时scala会尝试找到一个指定类型的对象*/
  //隐式参数，指的是在函数或者方法中，定义一个用implicit修饰的参数，此时scala会尝试找到一个指定类型的对象

  class SignPen{
    def write(content:String) = println(content)
  }

  implicit val signPen = new SignPen

  def signForExam(name:String)(implicit signPen: SignPen): Unit ={
    signPen.write(name+" come to the exam in time")
  }

  //隐式参数，指的是在函数或者方法中，定义一个用implicit修饰的参数，此时scala会尝试找到一个指定类型的对象
  signForExam("Patrick")

  def main(args: Array[String]): Unit = {
    val specialStudent = new SpecialStudent("SpecialStudent01")
    val olderPerson = new OlderPerson("OlderPerson01")
    println(buySpecialTicker(specialStudent))
    println(buySpecialTicker(olderPerson))
  }
}