import org.specs2.mutable.Specification

object AnotherLogicSpec extends Specification {
  "Arithmetic" should {
    "add two numbers" in {
      1 + 1 mustEqual 2
    }
    "add three numbers" in {
      1 + 1 + 1 mustEqual 3
    }
    "add four numbers" in {
      1 + 1 + 1 + 1 mustEqual 4
    }
  }
}