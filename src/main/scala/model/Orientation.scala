package model

sealed trait Orientation {
  def left: Orientation
  def right: Orientation
  def toShortString: String
}

case object North extends Orientation {
  def left: Orientation = West
  def right: Orientation = East
  def toShortString: String = "N"
}

case object East extends Orientation {
  def left: Orientation = North
  def right: Orientation = South
  def toShortString: String = "E"
}

case object South extends Orientation {
  def left: Orientation = East
  def right: Orientation = West
  def toShortString: String = "S"
}

case object West extends Orientation {
  def left: Orientation = South
  def right: Orientation = North
  def toShortString: String = "W"
}

object Orientation {
  def fromString(value: String): Option[Orientation] = value match {
    case "N" => Some(North)
    case "E" => Some(East)
    case "S" => Some(South)
    case "W" => Some(West)
    case _   => None
  }
}
