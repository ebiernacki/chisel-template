package deMux

import chisel3._
import chisel3.experimental.BundleLiterals._
import chiseltest._
import org.scalatest.flatspec.AnyFlatSpec

class deMuxTest extends AnyFlatSpec with ChiselScalatestTester {
    val in = 1 //always 1 otherwise never any output 


    println(s"Input vals: in = $in")
    "deMux2to1" should s"d1 should be $in when sel = 0" in {
        test(new deMux){ dut => 
            dut.io.sel.poke(false.B)
            dut.io.in.poke(in.U)

            dut.io.d1.expect(1.U)

            dut.io.d2.expect(0.U)
            
        }
    }/*
    "deMux2to1" should s"d2 should be $in when sel = 1" in {
        test(new deMux(1)){ dut => 
            dut.io.sel.poke(1.U)
            dut.io.in.poke(in.U)

            
            dut.io.d1.expect(0.U)
            dut.io.d2.expect(1.U)
            
        }
    }

    
    "deMux1to4" should "work" in { //still need to make varying widths and test them
        test(new deMux1to4){ dut => 
            dut.io.s0.poke(0.U)  
            dut.io.s1.poke(1.U)
            dut.io.in.poke(in.U)

            dut.io.o1.expect(0.U)     
            dut.io.o2.expect(1.U)     
            dut.io.o3.expect(0.U)     
            dut.io.o4.expect(0.U)     
        }
    }
    
    
    def test1to4(sel: Seq[Int], expected: Seq[Int]) = {
        "deMux1to4" should s"compute for sel: $sel and out: $expected " in {
            test(new deMux1to4(1)){ dut => 
                
                dut.io.s0.poke(sel(0).U)  
                dut.io.s1.poke(sel(1).U)
                dut.io.in.poke(in.U)

                dut.io.o1.expect(expected(0).U)     
                dut.io.o2.expect(expected(1).U)     
                dut.io.o3.expect(expected(2).U)     
                dut.io.o4.expect(expected(3).U)     
            }
        }
    }

    //set seq for inputs and outputs then call func 
    var inputs: Seq[Seq[Int]] = Seq(Seq(0,0),Seq(0,1),Seq(1,0),Seq(1,1))
    var outputs: Seq[Seq[Int]] = Seq(Seq(1,0,0,0),Seq(0,1,0,0),Seq(0,0,1,0),Seq(0,0,0,1))

    for (i <- 0 until 4){
        test1to4(inputs(i), outputs(i))
    }*/
}