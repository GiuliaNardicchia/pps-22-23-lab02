package u02.task.part4

object GeometricShape extends App:

  enum Shape:
    case Rectangle(width: Double, height: Double)
    case Circle(radius: Double)
    case Square(side: Double)

  object GeometricShape:
      def perimeter(shape: Shape): Double = shape match
        case Shape.Rectangle(width,height) => 2*(width+height)
        case Shape.Circle(radius) => 2*Math.PI*radius
        case Shape.Square(side) => side*side

      private def checkIsInside(width: Double, height: Double, point: (Double, Double)): Boolean =
        point._1 > 0 && point._1 < width && point._2 > 0 && point._2 < height

      def contains(shape: Shape, point: (Double, Double)): Boolean = shape match
        case Shape.Rectangle(width, height) => checkIsInside(width, height, point)
        case Shape.Circle(radius) => Math.sqrt(Math.pow(point._1, 2) + Math.pow(point._2, 2))<=radius
        case Shape.Square(side) => checkIsInside(side, side, point)

  import GeometricShape.*

  val rectangle = Shape.Rectangle(10.0, 8.0)
  val circle = Shape.Circle(3.0)
  val square = Shape.Square(10.0)
  val pointA = (2.0, 2.0)
  val pointB = (1.0, 5.0)
  val pointC = (1.0, 2.0)

  println(perimeter(rectangle))
  println(perimeter(circle))
  println(perimeter(square))

  println(contains(rectangle, pointA))
  println(contains(circle, pointB))
  println(contains(square, pointC))


