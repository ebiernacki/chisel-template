package multiMod

import chisel3._
import chisel3.experimental.BundleLiterals._
import chiseltest._
import scala.util.Random
import org.scalatest.flatspec.AnyFlatSpec
import scala.math._

class multiModTest extends AnyFlatSpec with ChiselScalatestTester {
  behavior of "Testing Or/And Gates of random widths..."
  
  def orTest(width: Int) = {
    //pick random value with range of 0 to 2^width - 1
    val a = BigInt(width, Random)
    val b = BigInt(width, Random) 
    

    //singular test thats generated in for loop below
    it should s"compute or : width = $width, a = $a, b = $b" in {
      test(new orGate(width)) { dut =>
        dut.io.a.poke(a.U(width.W))
        dut.io.b.poke(b.U(width.W))

        val expected = dut.op(a,b)
        dut.io.out.expect(expected.U)
      }
    }
  }

  def andTest(width: Int) = {
    //pick random value with range of 0 to 2^width - 1
    val a = BigInt(width, Random)
    val b = BigInt(width, Random) 

    //singular test thats generated in for loop below
    it should s"compute and: width = $width, a = $a, b = $b" in {
      test(new myAnd(width)) { dut =>
        dut.io.a.poke(a.U(width.W))
        dut.io.b.poke(b.U(width.W))

        val expected = dut.op(a,b)
        dut.io.out.expect(expected.U)
      }
    }
  }

  //generate seq of random widths in range  1 -> 64, add 64 to end
  var randomWidths: Seq[Int] = Seq.fill(5)(Random.nextInt(64) + 1)
  randomWidths = randomWidths :+ 64

  
  for (width <- randomWidths) {
    orTest(width) 
  }

  for (width <- randomWidths) {
    andTest(width) 
  }


  //satisfactory? need seperate tests for new module instantiation, 
  //more intuitive way of using traits to test?
}