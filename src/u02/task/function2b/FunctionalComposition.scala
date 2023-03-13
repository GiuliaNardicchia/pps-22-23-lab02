package u02.task.function2b

object FunctionalComposition extends App:

  def compose(f: Int => Int, g: Int => Int): Int => Int = x => f(g(x))
  def composeGeneric[X](f: X => X, g: X => X): X => X = x => f(g(x))

  println(compose(_ - 1, _ * 2)(5))
  println(composeGeneric[Int](_ - 1, _ * 2)(5))
  // constraint: you must enter the casting