/**
 * Created by Patrick on 22/6/2019.
 */
object ParentClassConstructor {
  def main(args: Array[String]): Unit = {
    val s = new MyStudent("patrick",0,1.1)
  }
}


class MyPerson(val name:String, val age: Int)

//子类调用父类的主构造器时,参数不能加var val 修饰符 如下面的name age
class MyStudent(name: String, age: Int, var score: Double) extends MyPerson(name, age){
  def this(name:String){
    this(name,0,0)
  }

  def this(age: Int){
    this("leo",age,0)
  }
}