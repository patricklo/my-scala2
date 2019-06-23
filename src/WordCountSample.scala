/**
 * Created by Patrick on 23/6/2019.
 */
object WordCountSample {

  def main(args: Array[String]): Unit ={
    val line1= scala.io.Source.fromFile("/Users/Patrick/Downloads/test").mkString
    val line2= scala.io.Source.fromFile("/Users/Patrick/Downloads/test2").mkString
    val line3 = List(line1,line2)
    //map(_._2). tuple中第2个值拿出来
    println(line3.flatMap(_.split(",")).map((_,1)).map(_._2).reduceLeft(_+_))
  }

}
