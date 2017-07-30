import org.specs2.mutable.Specification

object somethingSpec extends Specification {
  "iIsImportant" should {
    "return the sum of an array of integers" in {
      val testData = (1 to 100 by 5).toArray
      val result = something.iIsImportant(testData)
      result mustEqual 970
    }
    "handle empty shit" in {
      val testData = Array[Int]()
      val result = something.iIsImportant(testData)
      result mustEqual 0
    }
  }
}