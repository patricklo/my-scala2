import scala.actors.Actor


object MainSenderTest {
  def main(args: Array[String]):Unit ={
    val actorTest = new ActorTest()
    actorTest ! "Patrick"
  }
}


