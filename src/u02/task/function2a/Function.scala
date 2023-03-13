package u02.task.function2a

object Function extends App:

  object Function:
    val positiveVal: Int => String = _ match
      case x if x >= 0 => "positive"
      case x if x < 0 => "negative"

    def positiveMethod(x: Int): String = x match
      case x if x >= 0 => "positive"
      case x if x < 0 => "negative"

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
