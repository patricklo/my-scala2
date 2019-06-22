/**
 * Created by Patrick on 22/6/2019.
 */
class CompanionClass {
  def sayHello = println("hi,"+CompanionClass.name)

}

object CompanionClass{
  private val name="this is Companion Object and Class"
}

object MainTest {
  def main(args: Array[String]): Unit = {
    val companio = new CompanionClass
    companio.sayHello
  }
}
