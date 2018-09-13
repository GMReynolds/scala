//Imports
import scala.io.Source
import scala.util.Random

object game_settings {
  def main(args: Array[String]): Unit = {
    // Putting all the words into an array to select from
    val words: Array[String] = Source.fromFile("resources/enable1.txt").getLines.toArray
    //Want to select the difficulty into different arrays
    val veryEasy: Array[String] = words.filter(_.length <= 2)
    val easy : Array[String] = words.filter(_.length > 2).filter(_.length < 5)
    val average: Array[String] = words.filter(_.length > 5).filter(_.length <= 8)
    val hard : Array[String] = words.filter(_.length > 8).filter(_.length <= 11)
    val veryHard : Array[String] = words.filter(_.length > 11).filter(_.length <= 15)

    //User Selecting difficulty of game
    println("*********************** " + "\n" + "Please Select the level of difficulty you wish to play: " + "\n"
      + " 1 is Very Easy" + "\n" + " 2 is Easy" + "\n" + " 3 is Average" + "\n" + " 4 is Hard" + "\n"
      + " 5 is Very Hard" + "\n" + "*********************** ")
    val chosenDifficulty = scala.io.StdIn.readLine(">>>")
    chosenDifficulty match {
      case "1" => game(veryEasy,5)
      case "2" => game(easy,7)
      case "3" => game(average,10)
      case "4" => game(hard,12)
      case "5" => game(veryHard,15)
    }
    def game (difficulty: Array[String], numOfWords: Int): Unit = {
    val play = Random.shuffle(difficulty.toList).take(numOfWords)
      println(play.map(_.mkString("")).mkString("\n")
        + "\n" + "*********************** " + "\n")
      // Randomly selecting the password
      val password = Random.shuffle(play).head
      println("Select the correct password from above. You get 4 attempts at guessing the correct one.")
      println("Please enter your Attempt 1 below: ")
      val attempt1 = scala.io.StdIn.readLine(">>>")
      if (attempt1 == password) {
        println("You have Guessed the Password Correctly")
      }
      else {
        val matchedLetters = attempt1 intersect password
        println(matchedLetters)
        println("Incorrect guess" + " Matched on " + matchedLetters.length + " out of " + password.length)
        val attempt2 = scala.io.StdIn.readLine(">>>")
        if (attempt2 == password){
          println("You have Guessed the Password Correctly")
        }
        else {
          val matchedLetters2 = attempt2 intersect password
          println("Incorrect guess" + " Matched on " + matchedLetters2.length + " out of " + password.length)
          val attempt3 = scala.io.StdIn.readLine(">>>")
          if (attempt3 == password){
            println("You have Guessed the Password Correctly")
          }
          else {
            val matchedLetters3 = attempt3 intersect password
            println("Incorrect guess" + " Matched on " + matchedLetters3.length + " out of " + password.length)
            val attempt4 = scala.io.StdIn.readLine(">>>")
            if (attempt4 == password){
              println("You have Guessed the Password Correctly")
            }
            else {
              val matchedLetters3 = attempt4 intersect password
              println("Incorrect guess" + " Matched on " + matchedLetters3.length + " out of " + password.length)
              println("This is the password " + password)
            }
          }
        }
      }

    }

  }
}