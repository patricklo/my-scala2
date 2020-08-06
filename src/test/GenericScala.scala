
/**
 * Created by Patrick on 22/6/2019.
 */

/** scala类型参数，相当于Java中的泛型。
  *
  * */


object GenericScala {
  def main(args: Array[String]): Unit = {
      /**1。泛型类*/
      class Student[T](val localId: T){
        def getSchoolId(hukou:T) = "S-"+hukou+"-"+localId
      }

    val studnet = new Student[Int](111)
    println(studnet.getSchoolId(222))
    val stu2 = new Student(222)


      /**2. 泛型函数*/
    def getCard[T](content:T)={
      if(content.isInstanceOf[Int]) "card:001,"+content
      else if(content.isInstanceOf[String]) "card, this is your card,"+content
      else "card:"+content
    }
    println(getCard(1234))
    println(getCard("string card"))
    /**3. 上边界bounds*/
    class Person(val name:String){
      def sayHello = println("hello, i'm"+name)
      def makeFriends(p:Person): Unit ={
        sayHello
        p.sayHello
      }
    }

    class MyStudent01(name:String) extends Person(name)
    class MyParty[T <: Person] (p1:T,p2:T){
      def play = p1.makeFriends(p2)
    }

    val p1 = new Person("p1")
    val s1 = new MyStudent01("s2")
    val party = new MyParty[Person](p1,s1)

    /**4.下边界bounds*/
    class Father(val name:String)
    class Child(name:String) extends Father(name)
    def getIDCard[R >: Child](person:R): Unit ={
      if(person.getClass == classOf[Child]) println("please tell us your parents names")
      else if(person.getClass == classOf[Father]) println("sign your name for you child's id card")
      else println("sorry, you are not allowed to get id card ")
    }
    val father = new Father("father")
    getIDCard(father)
    getIDCard(s1)
    /**5.view bounds
      * view bounds作为一种上下边界bounds的加强版，支持可以对类型进行隐式转换，将指定的类型进行隐式转换后，再判断是滞在边界指定的类型范围内
      * */
    class PersonVB(val name:String){
      def sayHello = println("hello,"+name)
      def makeFriends(p:PersonVB): Unit ={
        sayHello
        p.sayHello
      }
    }
    class StudentVB(name:String) extends PersonVB(name)
    class Dog(val name:String){def sayHello = println("I'm dog:"+name)}
    implicit def dog2personVB(dog:Object): PersonVB = if(dog.isInstanceOf[Dog]) {val _dog=dog.asInstanceOf[Dog]; new PersonVB(_dog.name)} else Nil
    class PartyVB[T <% PersonVB](p1:T,p2:T) //% 代表PersonVB的子类或者经隐式转换的类型
    val studentVB = new StudentVB("Studnet")
    val dog = new Dog("Dog")
    val partyvb = new PartyVB(studentVB,dog)


    /**6. context bounds
      *   是一种特殊的bounds,它会根据泛型类型的声明，比如T：类型 要求必须存在一个类型为"类型[T]"的隐式值。其实个人认为，ContextBounds之所以叫context,是因
      *   为它基于一种全局的上下文，使需要使用到上下文中的隐式值以及注入
      *
      *   实例：使用scala内置的比较器比较大小
      * */
    class Caculator[T:Ordering] (val number1:T,val number2:T){
      def max(implicit order: Ordering[T]) = if(order.compare(number1,number2) > 0) number1 else number2
    }
    val calc = new Caculator(1,2)
    println(calc.max)


    /**7. Manifest Context Bounds
      * 在scala中，如果要实例化一个泛型数组，就必须使用manifest context bounds.
      * 也就是说，如果数组元素类型为T的话，需要为类或者函数定义[T: Manifest]的泛型类型，这样才能实例化Array[T]这种数组
      *
      * 实例：打包饭菜
      * */
    class Meat(val name:String)
    class Vegetable(val name:String)
    def packFood[T:Manifest](food: T*): Unit ={
      val foodPackage = new Array[T](food.length)
      for(i<-0 until food.length) foodPackage(i) = food(i)
      foodPackage
    }

    val veg01 = new Vegetable("ve01")
    val veg02 = new Vegetable("ve02")
    val veg03 = new Vegetable("ve03")
    val meat01 = new Meat("meat01")
    println(packFood(veg01,veg02,veg03))

    /**8. 协变和逆变
      * scala协变和逆变是非常有特色的，完全解决了java泛型的一大遗憾
      * 举例来说，Java中，如果有professional是master的子类，那么Card[Professional]是不是Card[Master]的子类，在java中不是的
      * 而在scala中，只要灵活使用协变和逆变，就可以解决java泛型的问题
      * */
    class Master  //大师
    class Professional extends Master  //专家
    //Master 以及Master以下的卡片Card都可以进入会场
    //+T 协变
    class Card[+T](val name:String){
      def enterMeeting(card: Card[Master]): Unit ={
        println("welcome to meeting!")
      }
    }
    //只要Professional级别的名片就可以进入会场，如果Master级别，当然也可以
    //-T 逆变
    class Card01[-T](val name:String){
      def enterMeeting(card:Card01[Professional]): Unit ={
        println("welcome to have this meeting!")
      }
    }
    val master01 = new Master()
    val pro01 = new Professional()
    val card = new Card[Professional]("patrick")
    val card02 = new Card[Master]("patrick")
    card.enterMeeting(card02)
    card02.enterMeeting(card)
    val card03 = new Card01[Professional]("patrick")
    val card04 = new Card01[Master]("patrick")
    card03.enterMeeting(card04)
    card04.enterMeeting(card03)

    /**9. Existential Type存在性类型
      * 必须掌握 - spark中常见
      *
      * Array[_]
      * Array[T] forSome { type T}
      * */

  }
}


