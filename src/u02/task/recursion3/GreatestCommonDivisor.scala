package u02.task.recursion3

object GreatestCommonDivisor extends App:

  @annotation.tailrec
  def gcd(a: Int, b: Int): Int = b match
    case 0 => a
    case _ if a > b => gcd(b, a % b)


  println(gcd(12,8)) // 4
  println(gcd(14,7)) // 7


