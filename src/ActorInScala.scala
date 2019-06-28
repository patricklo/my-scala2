import scala.actors.Actor
import scala.swing.Dialog.Message
object ActorInScala {
  //Actor 在scala即类似于java的多线程编程。 但是不同的是，scala的actor提供的模型与多线程有所不同。actor尽可能地避免锁和共享状态，从而避免多线程并发时出现资源争用的情况。
  // 因此actor可以避免死锁等传统多线程编程的问题

  //spark中使用的分布式多线程框架 是akka， akka也实现了类似 actor的模型，其核心概念也是actor

  def main(args: Array[String]): Unit = {
    val helloActor = new HelloActor
    helloActor.start()
    helloActor ! "my message!!!"




    //收发case class 类型的消息
    //actor模型与java多线程之间的有一个很大区别就是，actor天然支持线程之间的精准通信，即一个actor可以给其它 actor发送消息，这个功能 是非常强大和方便的
    //要给一个actor发送消息，需要使用 actor ! "消息"  的语法。 在scala中， 通常建议使用样例类，即case class来作为消息进行发送。
    // 然后actor接收消息之后 ，可以使用scala强大的模式匹配功能进行不同消息的处理

    //案例：用户注册登录后台接口
    val userManageActor = new UserManageActor
    userManageActor.start()
    userManageActor ! Register("patric,","1234"); userManageActor ! Login("patric,","1234");




    //actor之间互相发送消息
    //案例：打电话
    val leoTelphoneActor = new LeoTelphoneActor
    val jackTelephoneActor = new JackTelephoneActor(leoTelphoneActor)
    leoTelphoneActor.start()
    jackTelephoneActor.start()

  }


}


class HelloActor extends Actor{
  override def act(): Unit = {
    while (true){
      receive{
        case name:String => println("hello, "+name)
      }
    }
  }
}


//收发case class 类型的消息
//actor模型与java多线程之间的有一个很大区别就是，actor天然支持线程之间的精准通信，即一个actor可以给其它 actor发送消息，这个功能 是非常强大和方便的
//要给一个actor发送消息，需要使用 actor ! "消息"  的语法。 在scala中， 通常建议使用样例类，即case class来作为消息进行发送。
// 然后actor接收消息之后 ，可以使用scala强大的模式匹配功能进行不同消息的处理

//案例：用户注册登录后台接口
case class Login(username: String, password: String)
case class Register(username: String, password: String)

class UserManageActor extends Actor{
  override def act(): Unit = {
    while(true){
      receive{
        case Login(username,password) => println("login, username is "+ username+", pwd is "+password)
        case Register(username, password) => println(" register ,username is "+ username)
      }
    }
  }
}

//actor之间互相发送消息
//案例：打电话
case class Message(content: String, sender:Actor)
class LeoTelphoneActor extends Actor{
  override def act(): Unit = {
    while(true){
      receive{
        case Message(content,sender) => {println("leo telphone : "+ content); sender ! "I'm leo, pls call me after 10 mins"}
      }
    }
  }
}

class JackTelephoneActor(val leoTelphoneActor: Actor) extends Actor{
  override def act(): Unit = {
    leoTelphoneActor ! Message("hello, leo, I'm jack", this)
    while(true) {
      receive {
        case response: String => println("jack response: " + response)
      }
    }
  }
}


//同步消息和future
//默认情况下，消息都是异步的，如果 希望发送的消息是同步的，即对方接收后，一定要给自己返回结果，那么可以使用 !? 的方法发送消息，即val reply = actor !? message
//如果 要异步发送一个消息，但是在后续要获得消息的返回值，那么可以使用future, 即 !! 语句。  val future = actor !! message val reply = future()