package recfun

import org.junit._
import org.junit.Assert.assertEquals

class RecFunSuite {
  import RecFun._

  // ------ balance tests -----------------------------------------------------

  @Test def `balance: '(if (zero? x) max (/ 1 x))' is balanced`: Unit =
    assert(balance("(if (zero? x) max (/ 1 x))".toList))

  @Test def `balance: 'I told him ...' is balanced`: Unit =
    assert(balance("I told him (that it's not (yet) done).\n(But he wasn't listening)".toList))

  @Test def `balance: ':-)' is unbalanced`: Unit =
    assert(!balance(":-)".toList))

  @Test def `balance: counting is not enough`: Unit =
    assert(!balance("())(".toList))

  // ------ countChange tests -------------------------------------------------

  @Test def `countChange: example given in instructions`: Unit =
    assertEquals(3, countChange(4,List(1,2)))

  @Test def `countChange: sorted CHF`: Unit =
    assertEquals(1022, countChange(300,List(5,10,20,50,100,200,500)))

  @Test def `countChange: no pennies`: Unit =
    assertEquals(0, countChange(301,List(5,10,20,50,100,200,500)))

  @Test def `countChange: unsorted CHF`: Unit =
    assertEquals(1022, countChange(300,List(500,5,50,100,20,200,10)))

  // ------ minCountChange tests -------------------------------------------------

  @Test def `minCountChange: change denomination not included`: Unit =
    assertEquals(2, minCountChange(5, List(1, 2, 3, 4)))

  @Test def `minCountChange: change denomination included`: Unit =
    assertEquals(1, minCountChange(5, List(1, 2, 3, 4, 5)))

  @Test def `minCountChange: US coins`: Unit =
    assertEquals(6, minCountChange(63, List(1, 5, 10, 25)))

  @Test def `minCountChange: US coins plus 21 cents`: Unit =
    assertEquals(3, minCountChange(63, List(1, 5, 10, 21, 25)))

  // ------ pascal tests ------------------------------------------------------

  @Test def `pascal: col=0,row=2`: Unit =
    assertEquals(1, pascal(0, 2))

  @Test def `pascal: col=1,row=2`: Unit =
    assertEquals(2, pascal(1, 2))

  @Test def `pascal: col=1,row=3`: Unit =
    assertEquals(3, pascal(1, 3))

  @Rule def individualTestTimeout = new org.junit.rules.Timeout(10 * 10000)
}
