package com.example

class Subframe(raw: Array[Byte]){
  val SIZE = 768 // 512 words * 12 bitsPerWord / 8bitsPerByte

  def getWord(index: Int): Int = (index % 2) match  { // indexed from 1
    case 1 => { // odd
      val ind = (index - 1) + (index - 1) / 2
      val highEightOdd = raw(ind).toInt << 4
      val lowFourOdd = ( raw(ind+1) >>> 4 ).toInt
      highEightOdd + lowFourOdd
    }
    case 0 => { // even
      val ind = (index - 1) + (index - 1) / 2
      val mask =  Integer.parseInt("111100000000", 2)
      val highFourEven = ( raw(ind).toInt << 8 ) & mask
      val lowEightEven = raw(ind+1).toInt
      highFourEven + lowEightEven
    }
  }

  def decodedWord(wordIndex: Int, leastSignificantBit: Int, length: Int, signed: Boolean, resolution: Double): Double = {
    val word = getWord(wordIndex)
    val maskWithHighestBit = ( 1 << length ) - 1
    val maskWithoutHighestBit = ( 1 << length-1  ) - 1
    val maskSingleBit = 1 << length-1
    val shiftedWord = word >> leastSignificantBit-1
    val resultWithoutResolution = if (signed && (shiftedWord & maskSingleBit) != 0 ) {
      -( shiftedWord & maskWithoutHighestBit )
    } else {
      shiftedWord & maskWithHighestBit
    }
    resolution * resultWithoutResolution
  }


  def print(el: Any): Unit = el match {
    case i: Int =>
      println(s"${i}: ${Integer.toBinaryString(i)}")
    case b: Byte =>
      println(s"${b.toInt}: ${Integer.toBinaryString(b)}")
  }

}