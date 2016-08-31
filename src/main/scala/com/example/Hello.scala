package com.example

import java.nio.file.{Files, Paths}

object Hello {
  def main(args: Array[String]): Unit = {
    println("Hello, world!")
    val array = loadFile
    val byte1 = array(1).toInt
    print( (byte1 >>> 3).toByte )
  }

  def loadFile(): Array[Byte] = {
    val byteArray = Files.readAllBytes(Paths.get("test.in"))
    for (byte <- byteArray) {
      print(byte)
      // println(byte.toChar)
    }
    return byteArray
  }

  def print(byte: Byte): Unit = {
      println(s"${byte.toInt}: ${Integer.toBinaryString(byte)}")
  }
}
