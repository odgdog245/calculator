package dev.odg

import scala.io.StdIn
import scala.util.{Failure, Success, Try}

object Main {

  val allowedOperations = Set('+', '-', '/', '*')
141
  def main(args: Array[String]): Unit = {

    val firstNumber = askForNumber("Enter a number:")

    val op = askForOperator
    val secondNumber = askForNumber("Enter a second number:")

    val calcResult = op match {
      case '+' => firstNumber + secondNumber
      case '-' => firstNumber - secondNumber
      case '/' => firstNumber / secondNumber
      case '*' => firstNumber * secondNumber
    }

    println(s"$firstNumber $op $secondNumber = $calcResult")
  }

  private def askForOperator: Char = {
    Try {
      println("choose operation (+,-,/,*)")
      StdIn.readChar()
    }.flatMap {
      case op if (allowedOperations.contains(op)) => Success(op)
      case op => Failure(new RuntimeException(s"$op is not allowed"))
    }
      .recover(e => askForOperator).get

  }

  private def askForNumber(prompt: String): Float = {
    Try {
      println(prompt)
      StdIn.readFloat()
    }.recover(_ => askForNumber(prompt)).get


  }


}