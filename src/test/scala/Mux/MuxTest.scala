package Mux

import chisel3._
import chisel3.experimental.BundleLiterals._
import chiseltest._
import org.scalatest.flatspec.AnyFlatSpec

class MuxTest extends AnyFlatSpec with ChiselScalatestTester {

    "Mux2to1" should s"choose d1 when sel = 0" in {
        test(new Mux){ dut => 
            dut.io.sel.poke(0.U)
            dut.io.d1.poke(1.U)
            dut.io.d2.poke(0.U)
            
            dut.io.out.expect(1.U)
        }
    }
    "Mux2to1" should s"choose d2 when sel = 1" in {
        test(new Mux){ dut => 
            dut.io.sel.poke(1.U)
            dut.io.d1.poke(0.U)
            dut.io.d2.poke(1.U)

            dut.io.out.expect(1.U)
        }
    }

    /*
    "Mux4to1" should "work" in { //still need to make varying widths and test them
        test(new Mux4to1){ dut => 
            // for(s0 <- 0 until 2){
            //     for(s1 <- 0 until 2){
            //         dut.io.s0.poke(s0.U) 
            //         dut.io.s1.poke(s1.U)

            //     }
            // }
            dut.io.s0.poke(0.U) 
            dut.io.s1.poke(0.U)

            dut.io.i1.poke(1.U)
            dut.io.i2.poke(0.U)
            dut.io.i3.poke(0.U)
            dut.io.i4.poke(0.U)

            dut.io.y.expect(1.U)
        }
    }
    */


    
    def test4to1(sel: Seq[Int], inputs: Seq[Int]) = {  //always expect 1?
        "Mux4to1" should s"be 1 for sel: $sel and inputs: $inputs" in {
            test(new Mux4to1){ dut =>
                dut.io.s0.poke(sel(0).U) 
                dut.io.s1.poke(sel(1).U)

                dut.io.i1.poke(inputs(0).U)
                dut.io.i2.poke(inputs(1).U)
                dut.io.i3.poke(inputs(2).U)
                dut.io.i4.poke(inputs(3).U)

                dut.io.y.expect(1.U) //always expect 1 as the inputs and select will drive 1 to output and 0 otherwise if wrong
            }
        }
    }

    var sels: Seq[Seq[Int]]   = Seq(Seq(0,0),Seq(0,1),Seq(1,0),Seq(1,1))
    var inputs: Seq[Seq[Int]] = Seq(Seq(1,0,0,0),Seq(0,1,0,0),Seq(0,0,1,0),Seq(0,0,0,1))

    for (i <- 0 until 4){
        test4to1(sels(i), inputs(i))
    }
    


    
}