/**
 * Created by Patrick on 22/6/2019.
 */
object AnonymousScala {
  def main(args: Array[String]): Unit = {
    val p = new AnonyPerson("leo"){
      override def sayHello = "welcome "+ name
    }

    greeting(p)
  }

  def greeting(p: AnonyPerson){
  //def greeting(p: AnonyPerson {def sayHello: String}){

    println(p.sayHello)
  }

}

class AnonyPerson(protected val name:String){
  def sayHello = "hello, I'm "+name
}

