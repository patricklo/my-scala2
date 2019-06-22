/**
 * Created by Patrick on 22/6/2019.
 */
object EnumInScala extends Enumeration{
 val SPRING,SUMMER,WINTER = Value
}


object Season extends Enumeration{
  val SPRING = Value(0,"spring")
  val WINTER = Value(1,"winter")
  val SUMMER = Value(2,"summer")
  val AUTUMN = Value(3,"autumn")
}


object MainEntry100{
  def main(args: Array[String]): Unit = {
    Season(0)
    Season.withName("spring")
    println(Season.SPRING.toString)
    println(Season.SPRING.id)
    for(ele <- Season.values) println(ele)
  }
}
