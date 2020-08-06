import scala.actors.Actor

object MainReceiverTest {
  def main(args: Array[String]):Unit ={
    val actorTest = new ActorTest()
    actorTest.start()

    actorTest ! "Patrick02"

    val userMgtActor = new UserMgtActor()
    userMgtActor.start()

    userMgtActor ! Register("Patrick","abc")
    userMgtActor ! Login("login","pwd")
  }
}

class ActorTest extends Actor {
  override def act(): Unit = {
    while(true){
      receive{
        case name:String => println("hello,"+name)
      }
    }
  }
}

case class Login(username: String, password: String)
case class Register(username: String, password: String)
class UserMgtActor extends Actor{
  override def act(): Unit = {
    while(true){
      receive{
        case Login(username,password) => println("login, username"+username+":"+password)
        case Register(username,password) => println("register, username"+username+":"+password)
      }
    }
  }
}