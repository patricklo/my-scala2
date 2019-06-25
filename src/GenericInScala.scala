/**
 * Created by Patrick on 23/6/2019.
 */
object GenericInScala {

  def main(args: Array[String]) {
    //scala中的类型参数 相当于java中的泛型 generic

    //泛型类

    val leo = new GenericStudent[String]("GD")
    println(leo.getSchool("GZ"))

    println(getCard[String]("carcard"))

    val p01 = new Person100("p01")
    val s01 = new Student100("s01")
    val party01 = new Party(p01,s01)
    val worker = new Worker("worker")
    //val invalidParty = new Party(p01, worker) //运行时报错
    party01.play
  }

  def getCard[T](content:T) ={
    if(content.isInstanceOf[Int]) "card: 001,"+content
    else if(content.isInstanceOf[String]) "card: this is your card"+content
    else "card: "+content
  }
}

//泛型类
class GenericStudent[T](val localId:T){
  def getSchool(hukouId: T) = "S:"+hukouId+"-"+localId
}

class Person100(val name:String){
  def sayHello = println("hello, i'm " + name )
  def makeFriends(p:Person100): Unit ={
    sayHello
    p.sayHello
  }
}

class Student100(name: String) extends Person100(name)
class Worker(name: String)
//上边界bounds <:  下边界 >:
class Party[T <: Person100](p1: T, p2: T){def play = p1.makeFriends(p2)}



//view bounds <%  上下边界的加强版： 支持可以对类型进行隐式转换，将指定的类型进行隐式转换后，再判断是否在边界指定的类型范围内
class Dog(val name:String) {def sayHello = println("wang, wang I'm "+name)}
implicit def dog2Person(obj: Object): Person100 = if (obj.isInstanceOf[Dog]) { val mydog = obj.asInstanceOf[Dog]; new Person100(mydog.name)} else Nil

