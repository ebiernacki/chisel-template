// See README.md for license details.

package passthru

import chisel3._
import chisel3.util._
import chisel3.experimental._
import chisel3.experimental.BundleLiterals._

/**
  * Simple Passthrough module to test build enviroment and test files etc
  */
class passthru(width: Int) extends Module {
  val io = IO(new Bundle {
    val in        = Input(UInt(width.W))
    val out       = Output(UInt(width.W))
  })
  
  io.out := io.in
}


