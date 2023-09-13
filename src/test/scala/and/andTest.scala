package and

import chisel3._
import chisel3.experimental.BundleLiterals._
import chiseltest._

import org.scalatest.flatspec.AnyFlatSpec

//Write a scala func that takes in 2 boolean (one bit) inputs
//and output the and of those 2 values. Then have chisel test run 
//and compare that software = hardware output

//scala func should generate truth table values: store some how (array?)
//then use peak poke and expect said value
//try printf truth table first then feed values to peak poke

//need scala func stuff 
class InlineAnd extends Module {
  val io = IO(new BlackBoxAndIO)
  val and = Module(new InlineBlackBoxAnd)
  io <> and.io
}

//Hardware test using chiseltest libary and peak/poke functions
//.withAnnotations(Seq(VerilatorBackendAnnotation))


class andTest extends AnyFlatSpec with ChiselScalatestTester {
  "AndTest" should "pass" in {

    test(new InlineAnd).withAnnotations(Seq(VerilatorBackendAnnotation)) { c =>
      println("a b c")
      for (a <- 0 until 2){
        for(b <- 0 until 2){
          c.io.a.poke(a.U)
          c.io.b.poke(b.U)
          println(a.toString + " " + b.toString + " " + c.io.c.peek().litValue)
        }
      }
    }
    
  }

}

