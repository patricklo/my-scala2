class HelloScala {

  def sayHello(name:String, age: Int)  ={
    if(age>18){printf("hi %s, you are a big boy!!!0",name)}
    else printf("hi %s, you are little boy", name)
  }
}


object Test{
  def main(args: Array[String]): Unit ={
    val pt = new HelloScala()
    pt.sayHello("leo",100)
  }
}