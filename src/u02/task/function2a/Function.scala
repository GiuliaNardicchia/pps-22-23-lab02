package u02.task.function2a

object Function extends App:

  object Function:
    val positiveVal: Int => String = _ match
      case x if x >= 0 => "positive"
      case x if x < 0 => "negative"

    def positiveMethod(x: Int): String = x match
      case x if x >= 0 => "positive"
      case x if x < 0 => "negative"

  import Function.*
  println(positiveVal(2))
  println(positiveMethod(-2))
