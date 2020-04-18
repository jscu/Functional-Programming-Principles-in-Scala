package funsets

import org.junit._

/**
 * This class is a test suite for the methods in object FunSets.
 *
 * To run this test suite, start "sbt" then run the "test" command.
 */
class FunSetSuite {

  import FunSets._

  @Test def `contains is implemented`: Unit = {
    assert(contains(x => true, 100))
  }

  /**
   * When writing tests, one would often like to re-use certain values for multiple
   * tests. For instance, we would like to create an Int-set and have multiple test
   * about it.
   *
   * Instead of copy-pasting the code for creating the set into every test, we can
   * store it in the test class using a val:
   *
   *   val s1 = singletonSet(1)
   *
   * However, what happens if the method "singletonSet" has a bug and crashes? Then
   * the test methods are not even executed, because creating an instance of the
   * test class fails!
   *
   * Therefore, we put the shared values into a separate trait (traits are like
   * abstract classes), and create an instance inside each test method.
   *
   */

  trait TestSets {
    val s1 = singletonSet(1)
    val s2 = singletonSet(2)
    val s3 = singletonSet(3)
  }

  /**
   * This test is currently disabled (by using @Ignore) because the method
   * "singletonSet" is not yet implemented and the test would fail.
   *
   * Once you finish your implementation of "singletonSet", remove the
   * @Ignore annotation.
   */
  @Test def `singleton set one contains one`: Unit = {

    /**
     * We create a new instance of the "TestSets" trait, this gives us access
     * to the values "s1" to "s3".
     */
    new TestSets {
      /**
       * The string argument of "assert" is a message that is printed in case
       * the test fails. This helps identifying which assertion failed.
       */
      assert(contains(s1, 1), "Singleton")
    }
  }

  @Test def `singleton set one does not contain two`: Unit = {
    new TestSets {
      assert(!contains(s1, 2), "Singleton one does not contain two")
    }
  }

  @Test def `union contains all elements of each set`: Unit = {
    new TestSets {
      val s = union(s1, s2)
      assert(contains(s, 1), "Union 1")
      assert(contains(s, 2), "Union 2")
      assert(!contains(s, 3), "Union 3")
    }
  }

  @Test def `intersect produces empty set`: Unit = {
    new TestSets {
      val s = intersect(s1, s2)
      assert(!contains(s, 1), "Intersect produces empty set 1")
      assert(!contains(s, 2), "Intersect produces empty set 2")
      assert(!contains(s, 3), "Intersect produces empty set 3")
    }
  }

  @Test def `intersect produces set with common elements of each set`: Unit = {
    new TestSets {
      val s4 = singletonSet(1)
      val s = intersect(s1, s4)
      assert(contains(s, 1), "Intersect with common element 1")
      assert(!contains(s, 2), "Intersect without common element 2")
      assert(!contains(s, 3), "Intersect without common element 3")
    }
  }

  @Test def `diff produces set with elements of first set`: Unit = {
    new TestSets {
      val s = diff(s1, s2)
      assert(contains(s, 1), "Diff withs element 1")
      assert(!contains(s, 2), "Diff without element 2")
      assert(!contains(s, 3), "Diff without element 3")
    }
  }

  @Test def `diff produces set with zero elements for common set`: Unit = {
    new TestSets {
      val s4 = singletonSet(1)
      val s = diff(s1, s4)
      assert(!contains(s, 1), "Diff without element 1")
      assert(!contains(s, 2), "Diff without element 2")
      assert(!contains(s, 3), "Diff without element 3")
    }
  }

  @Test def `filter produces set with element of first set`: Unit = {
    new TestSets {
      val s = filter(s1, x => x == 1)
      assert(contains(s, 1), "Filter with element 1")
      assert(!contains(s, 2), "Filter without element 2")
      assert(!contains(s, 3), "Filter without element 3")
    }
  }

  @Test def `forall return true for union of all sets`: Unit = {
    new TestSets {
      val s4 = union(union(s1, s2), s3)
      val result = forall(s4, x => x == 1 || x == 2 | x == 3)
      assert(result == true, "Forall returns true for union of all sets")
    }
  }

  @Test def `forall return false for union of all sets`: Unit = {
    new TestSets {
      val s4 = union(union(s1, s2), s3)
      val result = forall(s4, x => x == 1 || x == 2)
      assert(result == false, "Forall returns false for union of all sets")
    }
  }

  @Test def `exists return true for union of all sets`: Unit = {
    new TestSets {
      val s4 = union(union(s1, s2), s3)
      val result = exists(s4, x => x == 1)
      assert(result == true, "Exists returns true for exists of all sets")
    }
  }

  @Test def `exists return false for union of all sets`: Unit = {
    new TestSets {
      val s4 = union(union(s1, s2), s3)
      val result = exists(s4, x => x == 0)
      assert(result == false, "Exists returns false for exists of all sets")
    }
  }

  @Test def `map produces set twice the value of the first set`: Unit = {
    new TestSets {
      val s4 = map(s1, x => x * 2)
      val result = contains(s4, 2)
      assert(result == true, "Map produces set twice the value of the first set")
    }
  }

  @Test def `map produces set with value zero`: Unit = {
    new TestSets {
      val s4 = map(s1, x => 0)
      val result = contains(s4, 0)
      assert(result == true, "Map produces set with value zero")
    }
  }

  @Rule def individualTestTimeout = new org.junit.rules.Timeout(10 * 1000)
}
