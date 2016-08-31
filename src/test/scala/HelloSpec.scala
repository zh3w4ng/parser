import org.scalatest._
import com.example.Hello

class HelloSpec extends FlatSpec with Matchers {
  "Hello" should "have tests" in {
    val array = Hello.loadFile
    array(0) should be (116)
  }
}
