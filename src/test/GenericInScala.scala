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




    //SVM转换
    implicit def dog2Person100(obj: Object): Person100 = if (obj.isInstanceOf[Dog]) { val mydog = obj.asInstanceOf[Dog]; new Person100(mydog.name)} else new Person100("wrong")

    val leo01 = new Student100("Leo01")
    val doggy = new Dog("doggy")
    val leo02= new Student100("leo02")
    val party001 = new Party01(leo01, doggy)  //leo和dog是 不同类型，依据下面的隐式转换，使其兼容
    val party002 = new Party(leo01, leo02)


    val cal  = new Calculator(1,2)
    println(cal.max)

    val food1 = new Meat("food1")
    val food2 = new Meat("food2")
    val food3 = new Meat("food3")
    val meatPack = packFood(food1,food2,food3)
    println(meatPack.length)


    val master01 = new Card[Master]("master01")
    val professional01 = new Card[Professional]("Professional01")
    master01.enterMeeting(professional01)
    professional01.enterMeeting(master01)
  }

  def getCard[T](content:T) ={
    if(content.isInstanceOf[Int]) "card: 001,"+content
    else if(content.isInstanceOf[String]) "card: this is your card"+content
    else "card: "+content
  }


  //scala中，如果要实例化一个泛型数组，就必须使用manifest context bounds,也就是说数组元素类型如果是T的话，需要为类或者函数定义[T: Manifest] 泛型类型，这样才能实例化泛型类型
  def packFood[T: Manifest] (food: T*) ={
    val foodPack = new Array[T](food.length)
    for(i<-0 until food.length) foodPack(i) = food(i)
    foodPack
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
//上边界bounds <:  下边界 bounds >:
class Party[T <: Person100](p1: T, p2: T){def play = p1.makeFriends(p2)}



//view bounds <%  上下边界的加强版： 支持可以对类型进行隐式转换，将指定的类型进行隐式转换后，再判断是否在边界指定的类型范围内
class Dog(val name:String) {def sayHello = println("wang, wang I'm "+name)}
class Party01[T <% Person100](p1: T, p2: T)


//context bounds 是一种特殊的bounds，它会根据泛型类型的声明
//比如 "T: 类型” 要求必须存在一个类型为 “类型[T]"的隐式值。
//implicit在参数中 是隐式值
class Calculator[T: Ordering](val number1:T,val number2: T){
  def max(implicit order: Ordering[T]) = if(order.compare(number1,number2) > 0) number1 else number2
}


//scala中，如果要实例化一个泛型数组，就必须使用manifest context bounds,也就是说数组元素类型如果是T的话，需要为类或者函数定义[T: Manifest] 泛型类型，这样才能实例化泛型类型
class Meat(val name: String)
class Vegetable(val name: String)


//scala中的协变[+T]和逆变[-T] -》非常有特色，完全解决了java泛型的一大缺憾
//举例，java中， 如果有Professional 是Master的子类，那么Card[Professional]是不是Card[Master]的子类呢？答案： 不是
//而在scala中，只要灵活使用协变和逆变，就可以解决java泛型的问题
class Master
class Professional extends Master

//master及以下的名片都可以进行会议
//class Card[+T](val name:String){
//  def enterMeeting(card: Card[Master]): Unit ={
//    println("welcome to the meeting")
//  }
//}
//只要professional级别的,master级别不可以进，但要求professional的父类可以进来 -》 master
class Card[-T](val name:String){
  def enterMeeting(card: Card[Professional]): Unit ={
    println("welcome to the meeting")
  }
}


//Existential Type
//在scala里，有一种特殊的类型参数，就是Existential Type，存在性类型，
// Array[T] forSome { type T }
// Array[_]   <<---  _  即为scala中存在性类型

