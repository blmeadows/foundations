package exercises.valfunction

import exercises.valfunction.ValueFunctionExercises._
import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.scalacheck.ScalaCheckDrivenPropertyChecks

class ValueFunctionExercisesTest extends AnyFunSuite with ScalaCheckDrivenPropertyChecks {

  /////////////////////////////////////////////////////
  // Exercise 1: String API with higher-order functions
  /////////////////////////////////////////////////////

  // replace `ignore` by `test` to enable the test
  test("selectDigits examples") {
    assert(selectDigits("hello4world-80") == "480")
    assert(selectDigits("welcome") == "")
  }

  // replace `ignore` by `test` to enable the test
  test("selectDigits length is smaller") {
    forAll { (text: String) =>
      assert(selectDigits(text).length <= text.length)
    }
  }

  // replace `ignore` by `test` to enable the test
  test("selectDigits can be cast to an Int") {
    forAll { (text: String) =>
      val res = selectDigits(text)
      assert(res.isEmpty || res.toIntOption.isDefined)
    }
  }

  test("secret has the same length") {
    forAll { (text: String) =>
      assert(secret(text).length == text.length)
    }
  }

  test("secret only contains *") {
    forAll { (text: String) =>
      assert(secret(text).forall(_=='*'))
    }
  }

  test("secret is idempotent") {
    forAll { (text: String) =>
      val first = secret(text)
      val second = secret(text)
      assert(first == second)
    }
  }

  test("isValidUsername is the same in reverse") {
    forAll { (username: String) =>
      assert(username.isEmpty || isValidUsername(username) == isValidUsername(username.reverse))
    }
  }

  test("isValidUsername is true with valid characters") {
    assert(isValidUsername("-_aBc123"))
  }

  test("isValidUsername is false with invalid characters") {
    assert(!isValidUsername("-_aBc123!@#$%"))
  }

  ///////////////////////
  // Exercise 2: Point
  ///////////////////////

  test("Point isPositive") {
    forAll { (x: Int, y: Int, z: Int) =>
      assert(Point(x.max(0), y.max(0), z.max(0)).isPositive)
    }
  }

  test("Point isEvent") {
    forAll { (x: Int, y: Int, z: Int) =>
      assert(Point(x * 2, y * 2, z * 2).isEven)
    }
  }

  test("Point forAll") {
    forAll { (x: Int, y: Int, z: Int, predicate: Int => Boolean) =>
      assert(Point(x,y,z).forAll(predicate) == List(x, y, z).forall(predicate))
    }
  }

}
