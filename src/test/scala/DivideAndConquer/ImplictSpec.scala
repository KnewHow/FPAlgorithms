package test.fp.algorithms.divideAndConquer

import org.scalatest.FlatSpec
import fp.algorithms.common.Logger.Logger

class ImplicitSpec extends FlatSpec {}

object Ops {
  self =>
  def plus(a: Person, b: Person) = Person(a.name + b.name)

  def plus2(a: Person, b: Person, c: Person) = a + b + c

  implicit def toPersonOps(p: Person): PersonOps = PersonOps(p)
  case class PersonOps(p: Person) {
    def +(a: Person) = self.plus(p, a)
  }
}

case class Person(
  name: String
)
