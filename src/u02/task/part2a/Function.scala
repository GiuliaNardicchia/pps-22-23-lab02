package u02.task.part2a

object Function extends App:

  object Function:

    val positiveVal: Int => String = x => x >= 0 match
      case true => "positive"
      case _ => "negative"

    def positiveMethod(x: Int): String = x >= 0 match
      case true => "positive"
      case _ => "negative"

    val negVal: (String => Boolean) => (String => Boolean) = predicate => !predicate(_)
    def negMethod(predicate: String => Boolean): String => Boolean = !predicate(_)

    def neg[A](predicate: A => Boolean): A => Boolean = !predicate(_)


  import Function.*

  val empty: String => Boolean = _ == "" // predicate on strings
  val notEmptyVal = negVal(empty) // which type of notEmpty?
  val notEmptyMethod = negMethod(empty) // which type of notEmpty?
  val notEmpty = neg(empty) // which type of notEmpty?

  println(notEmptyVal("foo")) // true
  println(notEmptyVal("")) // false
  println(notEmptyVal("foo") && !notEmptyVal("")) // true.. a comprehensive test
  println(notEmptyMethod("foo")) // true
  println(notEmptyMethod("")) // false
  println(notEmptyMethod("foo") && !notEmptyMethod("")) // true.. a comprehensive test
  println(notEmpty("foo")) // true
  println(notEmpty("")) // false
  println(notEmpty("foo") && !notEmpty("")) // true.. a comprehensive test

  println(positiveVal(2))
  println(positiveMethod(-2))
