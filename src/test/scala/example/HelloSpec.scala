package example

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import main.Greeting

class HelloSpec extends AnyFlatSpec with Matchers {

  "The Greeting trait" should "say hello" in {
    val greeting = new Greeting {}
    greeting.greeting shouldEqual "hello"
  }
}
