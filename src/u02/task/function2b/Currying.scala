package u02.task.function2b

object Currying extends App:

  val p1: Int => Int => Int => Boolean = x => y => z => x <= y && y == z

  val p2: (Int, Int, Int) => Boolean = (x,y,z) => x <= y && y == z
  def p3(x: Int)(y: Int)(z: Int): Boolean = x <= y && y == z
  def p4(x: Int, y: Int, z: Int): Boolean = x <= y && y == z

  //true
  println(p1(1)(2)(2))
  println(p2(1,2,2))
  println(p3(1)(2)(2))
  println(p4(1,2,2))
  //false
  println(p1(1)(2)(3))
  println(p2(1, 2, 3))
  println(p3(1)(2)(3))
  println(p4(1, 2, 3))


