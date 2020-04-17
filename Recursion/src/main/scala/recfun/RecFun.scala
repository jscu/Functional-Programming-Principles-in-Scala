package recfun

object RecFun extends RecFunInterface {

  def main(args: Array[String]): Unit = {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(s"${pascal(col, row)} ")
      println()
    }
  }

  /**
   * Exercise 1
   */
  def pascal(c: Int, r: Int): Int = {
    if (c > r) throw new Exception("c cannot be greater than r")
    else if (c == 0 || c == r) 1
    else pascal(c - 1, r - 1) + pascal(c, r - 1)
  }

  /**
   * Exercise 2
   */
  def balance(chars: List[Char]): Boolean = {
    def balance(chars: List[Char], numberOfOpenParenthesis: Int): Boolean = {
      if (chars.isEmpty) numberOfOpenParenthesis == 0
      else if (chars.head == '(') balance(chars.tail, numberOfOpenParenthesis + 1)
      else if (chars.head == ')') numberOfOpenParenthesis > 0 && balance(chars.tail, numberOfOpenParenthesis - 1)
      else balance(chars.tail, numberOfOpenParenthesis)
    }
    balance(chars, 0)
  }

  /**
   * Exercise 3
   */
  def countChange(money: Int, coins: List[Int]): Int = {
    if (money < 0 || coins.isEmpty) 0
    else if (money == 0) 1
    else countChange(money, coins.tail) + countChange(money - coins.head, coins)
  }

  // Calculate the minimum number of coins required for change
  def minCountChange(money: Int, coins: List[Int]): Int = {
    def findMinCountChange(money: Int, acc: Int): Int = {
      if (money < 0 || coins.isEmpty) Integer.MAX_VALUE
      else if (money == 0) acc
      else coins.map(x => findMinCountChange(money - x, acc + 1)).min
    }
    findMinCountChange(money, 0)
  }
}
