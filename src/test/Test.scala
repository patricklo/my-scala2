package test

object Test {
  def main(args: Array[String]): Unit = {
    val leo = new Student("leo")
    val doggy = new Dog("doggy")


    implicit def dog2person (obj: Object): Person = if (obj.isInstanceOf[Dog] ) {val _dog = obj.asInstanceOf[Dog];new Person (_dog.name)} else new Person("wrong")
    val p01 = new Party(leo, doggy)
  }
}

class Person(val name: String) {
  def sayHello = println("Hello, I'm " + name)

  def makeFriends(p: Person) {
    sayHello
    p.sayHello
  }
}

class Student(name: String) extends Person(name)

class Dog(val name: String) {  def sayHello = println("Wang, Wang, I'm " + name) }

class Party[T <% Person](p1: T, p2: T)
