/**
 * Created by Patrick on 22/6/2019.
 */

/**
  * scala模式匹配 pattern matching
  *
  * */


object PatternMatchingScala {
  def main(args: Array[String]): Unit = {

   /** 1. 集合类型Iterable,Seq,Set,Map
    * List
    * list中有特殊操作符 ::  用于将head和tail合并成一个List,  0::list
    */
    def judgeGrade(grade:String): Unit ={
      grade match{
        case "A" => println("Excellent")
        case "B" => println("Good")
        case "C" => println("Just soso")
        case _  =>println("you need work harder")
      }
    }  

  }
}


