import org.scalatest._
import com.example.Subframe

class SubframeSpec extends FlatSpec with Matchers {

  val bytes = Array[Byte]('a','b', 'c')
  val subframe = new Subframe(bytes)
  for (i <- bytes) subframe.print(i)

  "Subframe" should "get odd words" in {
    subframe.getWord(1) should be ( ( 97 << 4 ) + 6 )
  }

  "Subframe" should "get even words" in {
    subframe.getWord(2) should be ( (2 << 8) + 99 )
  }

  "Subframe" should "decode odd words" in {
    subframe.decodedWord(1, 1, 11, true, 1.0) should be ( - ( ( 97 - 64 << 4 ) + 6 ) )
  }

  "Subframe" should "decode even words" in {
    subframe.decodedWord(2, 1, 10, true, 1.0) should be ( - ( 99 ) )
  }
}
