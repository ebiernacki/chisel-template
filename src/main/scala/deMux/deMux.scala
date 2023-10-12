package deMux

import chisel3._
import chisel3.util._

class deMux() extends Module { //1to2 
    //for multibit result(and in) could use bundle of bools to represent bits????
    val io = IO(new Bundle {
        val sel        = Input(Bool())
        val in         = Input(UInt(1.W))
        val d1         = Output(UInt(1.W))
        val d2         = Output(UInt(1.W))
    })
    val notSel = ~io.sel
    /*
    //???? multibit input with width...?
    when in != 1 {
        determine which output based on select
        drive that output to with in and other out to 0
    }
    else{
        set both to 0
    }
    */


    io.d1 := notSel & io.in
    io.d2 := io.sel & io.in
}

class deMux1to4() extends Module { //1to4
    val io = IO(new Bundle {
        val s0         = Input(UInt(1.W))
        val s1         = Input(UInt(1.W))
        val in         = Input(UInt(1.W))
        val o1         = Output(UInt(1.W))
        val o2         = Output(UInt(1.W))
        val o3         = Output(UInt(1.W))
        val o4         = Output(UInt(1.W))
    })

    val dm1 = Module(new deMux)

    dm1.io.sel := io.s0
    dm1.io.in := io.in

    val dm2 = Module(new deMux)
    val dm3 = Module(new deMux)

    dm2.io.sel := io.s1
    dm3.io.sel := io.s1

    dm2.io.in := dm1.io.d1
    dm3.io.in := dm1.io.d2

    io.o1 := dm2.io.d1
    io.o2 := dm2.io.d2

    io.o3 := dm3.io.d1
    io.o4 := dm3.io.d2

}
