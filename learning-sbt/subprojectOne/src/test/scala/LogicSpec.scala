import org.specs2.mutable.Specification

object LogicSpec extends Specification {
  "Method 'total'" should {
    "define debt fubar situation" in {
      val mom = Dummy(999)
      val dad = OtherDummy(1457)
      val balance = logic.total(mom, dad)
      balance must beLessThan(0)
    }
    "maybe is not so bad" in {
      val mom = Dummy(999)
      val dad = OtherDummy(500)
      val balance = logic.total(mom, dad)
      balance must beGreaterThan(0)
    }
  }
}