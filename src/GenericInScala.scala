/**
 * Created by Patrick on 23/6/2019.
 */
object GenericInScala {

  def main(args: Array[String]) {
    //scala中的类型参数 相当于java中的泛型 generic

    //泛型类

    val leo = new GenericStudent[String]("GD")
    println(leo.getSchool("GZ"))
  }
}

//泛型类
class GenericStudent[T](val localId:T){
  def getSchool(hukouId: T) = "S:"+hukouId+"-"+localId
}
