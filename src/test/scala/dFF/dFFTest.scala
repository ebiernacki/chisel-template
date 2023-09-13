package dFF

import chisel3._
import chisel3.experimental.BundleLiterals._
import chiseltest._
import chisel3.experimental._ // To enable experimental features

import org.scalatest.flatspec.AnyFlatSpec

class wrapIO extends Bundle {
  val D   = Input(UInt(1.W))
  //clk and rst are already there with module
  val Q   = Output(UInt(1.W))
    
}

//DFF wrapper
class InlineDFF extends Module {
  val io = IO(new wrapIO)
  val dff = Module(new InlineBlackBoxDFF)  
  
  dff.io.d <> io.D
  dff.io.clk <> clock
  dff.io.rst <> reset 
  dff.io.q <> io.Q

}

class dFFTest extends AnyFlatSpec with ChiselScalatestTester {
  "dffTest" should "pass" in {

    test(new InlineDFF).withAnnotations(Seq(VerilatorBackendAnnotation)) { c =>
      c.io.D.poke(0.U)
      c.io.Q.expect(0.U)

      c.clock.step()

      c.io.Q.expect(0.U)

      c.io.D.poke(1.U)
      c.clock.step()
      c.io.Q.expect(1.U)

      c.clock.step(3)

      c.io.Q.expect(1.U)

      println("D after poked and stepped: "+ c.io.D.peek().litValue)
      c.reset.poke(true.B)
      c.io.Q.expect(0.U)

      

      



    }
    
  }

}