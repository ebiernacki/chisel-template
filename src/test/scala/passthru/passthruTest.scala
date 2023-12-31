package passthru

import chisel3._
import chisel3.experimental.BundleLiterals._
import chiseltest._

import org.scalatest.flatspec.AnyFlatSpec

class passthruTest extends AnyFlatSpec with ChiselScalatestTester {
  behavior of "MyModule"
  
  //define test case
  it should "run tests" in { 

    //create module and set values for test
    test(new passthru(16)) { c => 
        c.io.in.poke(0.U)     // Set our input to value 0
        c.io.out.expect(0.U)  // Assert that the output correctly has 0
        c.io.in.poke(1.U)     // Set our input to value 1
        c.io.out.expect(1.U)  // Assert that the output correctly has 1
        c.io.in.poke(2.U)     // Set our input to value 2
        c.io.out.expect(2.U)  // Assert that the output correctly has 2
    }

  }

}
