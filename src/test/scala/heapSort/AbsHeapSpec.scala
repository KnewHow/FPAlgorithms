package test.fp.algorithms.heapSort

import org.scalatest.FlatSpec
import fp.algorithms.heapSort._

class AbsHeapSpec extends FlatSpec {
  // case class Age(age: Int) extends Weight[Int] { self =>
  //   def compare(a: Age): Int  = self.age - a.age
  //   def modifyTo(weight: Age) = self.copy(weight.age)
  //   def max: Age              = Age(Int.MaxValue)
  //   def min: Age              = Age(Int.MinValue)
  // }
  "test absHeapSort" should "succeed" in {
    succeed
  }
}

trait Animal[F[_]] {
  def compare[A](a: F[A]): Int
}

case class Person[A](age: Int, name: String) extends Animal[Person] { self =>
  def compare[A](p: Person[A]): Int = self.age - p.age
}
