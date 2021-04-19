package exercises.generic

import java.time.LocalDate
import java.time.format.DateTimeFormatter

import exercises.generic.GenericFunctionExercises._
import org.scalacheck.{Arbitrary, Gen}
import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.scalacheck.ScalaCheckDrivenPropertyChecks

import scala.util.Try

import exercises.generic.GenericFunctionExercises.Predicate._

class GenericFunctionExercisesTest extends AnyFunSuite with ScalaCheckDrivenPropertyChecks {

  ////////////////////
  // Exercise 1: Pair
  ////////////////////

  test("Pair swap") {
    assert(Pair(0, 1).swap == Pair(1, 0))
    assert(Pair("John", "Doe").swap == Pair("Doe", "John")) // not needed

    forAll { (a: Int, b: Int) => // not needed as long as first uses different values for a and b
      assert(Pair(a, b).swap == Pair(b, a))
    }
  }

  test("Pair map") {
    assert(Pair(0, 1).map(identity) == Pair(0, 1))
  }

  test("Pair zipWith") {
    assert(Pair(0, 1).zipWith(Pair(2, 3))((x, y) => x + y) == Pair(2, 4))
  }

  test("Pair productNames") {
    assert(products == Pair(Product("Coffee", 2.5), Product("Plane ticket", 329.99)))
  }

  test("Pair map3") {
    assert(Pair(0, 1).map3(Pair(2, 3), Pair(4, 5))((x, y, z) => x + y + z) == Pair(6, 9))
  }

  ////////////////////////////
  // Exercise 2: Predicate
  ////////////////////////////

  test("Predicate &&") {
    assert((isEven && isPositive)(12) == true)
    assert((isEven && isPositive)(11) == false)
    assert((isEven && isPositive)(-4) == false)
    assert((isEven && isPositive)(-7) == false)
  }

  test("Predicate && pbt") {
    forAll { (eval1: Int => Boolean, value: Int) =>
      val p1 = Predicate(eval1)

      assert((p1 && False)(value) == false)
      assert((p1 && True)(value) == p1(value))
    }
  }

  test("Predicate ||") {
    forAll { (eval1: Int => Boolean, value: Int) =>
      val p1 = Predicate(eval1)

      assert((p1 || False)(value) == p1(value))
      assert((p1 || True)(value) == true)
    }
  }

  test("Predicate flip") {
    assert(True.flip(()) == false)
    assert(False.flip(()) == true)
  }

  test("Predicate isValidUser") {
    assert(isValidUser(User("John", 20)) == true)
    assert(isValidUser(User("John", 17)) == false)
    assert(isValidUser(User("john", 20)) == false)
    assert(isValidUser(User("x"   , 23)) == false)
  }

  test("Predicate isValidUser1") {
    assert(isValidUser2(User("John", 20)) == true)
    assert(isValidUser2(User("John", 17)) == false)
    assert(isValidUser2(User("john", 20)) == false)
    assert(isValidUser2(User("x"   , 23)) == false)
  }

  ////////////////////////////
  // Exercise 3: JsonDecoder
  ////////////////////////////

  test("JsonDecoder UserId") {}

  test("JsonDecoder LocalDate") {}

  test("JsonDecoder weirdLocalDateDecoder") {}

}
