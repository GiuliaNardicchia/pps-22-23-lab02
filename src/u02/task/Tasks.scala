package u02.task

object Tasks extends App:

    /******************************************** Task 3 (function) **********************************************/
    // svolto da sola
    private object Functions:

      val positiveVal: Int => String = x => x >= 0 match
        case true => "positive"
        case _ => "negative"
      def positiveMethod(x: Int): String = x >= 0 match
        case true => "positive"
        case _ => "negative"

      val negVal: (String => Boolean) => (String => Boolean) = predicate => !predicate(_)
      def negMethod(predicate: String => Boolean): String => Boolean = !predicate(_)
      def neg[A](predicate: A => Boolean): A => Boolean = !predicate(_)


    //Test Task 3
    import Functions.*
    val empty: String => Boolean = _ == "" // predicate on strings
    val notEmpty = neg(empty)
    def testPositive() =
      println(positiveVal(2)) // "positive"
      println(positiveVal(-3)) // "negative"
    def testNotEmpty() =
      println(notEmpty("foo"))
      println(notEmpty(""))
      println(notEmpty("foo") && !notEmpty(""))

    testPositive()
    testNotEmpty()


    /******************************************** Task 4 (currying) **********************************************/
    // svolto da sola
    private object Currying:

      val p1: Int => Int => Int => Boolean = x => y => z => x <= y && y == z
      val p2: (Int, Int, Int) => Boolean = (x, y, z) => x <= y && y == z
      def p3(x: Int)(y: Int)(z: Int): Boolean = x <= y && y == z
      def p4(x: Int, y: Int, z: Int): Boolean = x <= y && y == z


    //Test Task 4
    import Currying.*
    def testTrue() =
      println(p1(1)(2)(2))
      println(p2(1, 2, 2))
      println(p3(1)(2)(2))
      println(p4(1, 2, 2))
    def testFalse() =
      println(p1(1)(2)(3))
      println(p2(1, 2, 3))
      println(p3(1)(2)(3))
      println(p4(1, 2, 3))

    testTrue()
    testFalse()


    /************************************ Task 5 (functional composition) ******************************************/
    // svolto da sola
    object FunctionalComposition:

      def compose(f: Int => Int, g: Int => Int): Int => Int = x => f(g(x))
      def composeGeneric[X](f: X => X, g: X => X): X => X = x => f(g(x))
      val composeVal: (Int => Int, Int => Int) => Int => Int = (f, g) => x => f(g(x))

      println(compose(_ - 1, _ * 2)(5))
      println(composeGeneric[Int](_ - 1, _ * 2)(5)) // constraint: you must enter the casting
      println(composeVal(_ - 1, _ * 2)(5))

    //Test Task 5
    import FunctionalComposition.*
    def testCompose() =
      println(compose(_ - 1, _ * 2)(5))
      println(composeGeneric[Int](_ - 1, _ * 2)(5)) // constraint: you must enter the casting
      println(composeVal(_ - 1, _ * 2)(5))

    testCompose()


    /************************************ Task 6 (recursion) ****************************************/
    // svolto da sola
    private object GreatestCommonDivisor:
      @annotation.tailrec
      def gcd(a: Int, b: Int): Int = b match
        case 0 => a
        case _ => gcd(b, a % b) // if a > b

    // Test Task 6
    import GreatestCommonDivisor.*
    def testGCD() =
      println(gcd(12, 8)) // 4
      println(gcd(14, 7)) // 7

    testGCD()


    /********************************* Task 7 (sum types, product types, modules) *********************************/
    // svolto da sola
    private object GeometricShape:

      enum Shape:
        case Rectangle(width: Double, height: Double)
        case Circle(radius: Double)
        case Square(side: Double)

      object ShapeOperation:
        def perimeter(shape: Shape): Double = shape match
          case Shape.Rectangle(width, height) => 2 * (width + height)
          case Shape.Circle(radius) => 2 * Math.PI * radius
          case Shape.Square(side) => side * side

        private def checkIsInside(width: Double, height: Double, px: Double, py: Double): Boolean =
          px > 0 && px < width && py > 0 && py < height

        def contains(shape: Shape, point: (Double, Double)): Boolean = (shape, point) match
          case (Shape.Rectangle(width, height), (x, y)) => checkIsInside(width, height, x, y)
          case (Shape.Circle(radius), (x, y)) => Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2)) <= radius
          case (Shape.Square(side), (x, y)) => checkIsInside(side, side, x, y)

  //Test Task 7
    import GeometricShape.*
    import ShapeOperation.*
    val rectangle = Shape.Rectangle(10.0, 8.0)
    val circle = Shape.Circle(3.0)
    val square = Shape.Square(10.0)
    val pointA = (2.0, 2.0)
    val pointB = (1.0, 5.0)
    val pointC = (1.0, 2.0)

    def testPerimeter() =
      println(perimeter(rectangle))
      println(perimeter(circle))
      println(perimeter(square))

    def testContains() =
      println(contains(rectangle, pointA))
      println(contains(circle, pointB))
      println(contains(square, pointC))

    testPerimeter()
    testContains()


    /********************************* Task 8 (more functional combinators) *********************************/
    // svolto da sola
    object Optionals:

      enum Option[A]:
        case Some(a: A)
        case None() // here parens are needed because of genericity

      object Option:

        def isEmpty[A](opt: Option[A]): Boolean = opt match
          case None() => true
          case _ => false

        def orElse[A, B >: A](opt: Option[A], orElse: B): B = opt match
          case Some(a) => a
          case _ => orElse

        def flatMap[A, B](opt: Option[A])(f: A => Option[B]): Option[B] = opt match
          case Some(a) => f(a)
          case _ => None()

        def filter[A](opt: Option[A])(f: A => Boolean): Option[A] = opt match
          case Some(a) if f(a) => Some(a)
          case _ => None()

        def map[A, B](opt: Option[A])(f: A => B): Option[B] = opt match
          case Some(a) => Some(f(a))
          case _ => None()

        def fold[A](opt: Option[A])(default: A)(f: A => A): A = opt match
          case Some(a) => f(a)
          case _ => default

    //Test Task 8
    import Optionals.*
    import Option.*

    def testFilter() =
      println(filter(Some(5))(_ > 2)) // Some(5)
      println(filter(Some(5))(_ > 8)) // None
      println(filter(None[Int]())(_ > 2)) // None

    def testMap() =
      println(map(Some(5))(_ > 2)) // Some(true)
      println(map(Some(5))(_ > 8)) // Some(false)
      println(map(None[Int]())(_ > 2)) // None

    def testFold() =
      println(fold(Some(5))(1)(_ + 1)) // 6
      println(fold(None[Int]())(1)(_ + 1)) // 1

    testFilter()
    testMap()
    testFold()