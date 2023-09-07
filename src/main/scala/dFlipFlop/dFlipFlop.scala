package dFlipFlop

import chisel3._
import chisel3.util._
import chisel3.experimental._
import chisel3.experimental.BundleLiterals._

class dFlipFlop extends Module{
    val io = IO(new Bundle {
        val d        = Input(UInt(1.W))
        //val clk       = Input()
        val q        = Output(UInt(1.W))
        val nq       = Output(UInt(1.W))
    })
}