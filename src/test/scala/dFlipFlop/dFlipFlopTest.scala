package dFlipFlop

import chisel3._
import chisel3.experimental.BundleLiterals._
import chiseltest._

import org.scalatest.flatspec.AnyFlatSpec

class dFlipFlopTest extends AnyFlatSpec with ChiselScalatestTester {
  behavior of "MyModule"

  //define test case
  it should "run tests" in { 

    //create module and set values for test
    test(new dFlipFlop()) { c => 
        c.io.d.poke(0.U)     // Set our input to value 0
        c.io.q.expect(0.U)  // Assert that the output correctly has 0
        c.io.nq.expect(1.U)

        c.io.d.poke(1.U)     // Set our input to value 1
        c.io.q.expect(1.U)  // Assert that the output correctly has 0
        c.io.nq.expect(0.U)
    }

  }

}