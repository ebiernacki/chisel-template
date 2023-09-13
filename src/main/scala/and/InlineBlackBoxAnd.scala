package and

import chisel3._
import chisel3.util.HasBlackBoxInline

class BlackBoxAndIO extends Bundle{
  val a = Input(UInt(1.W))
  val b = Input(UInt(1.W))
  val c = Output(UInt(1.W))
}

class InlineBlackBoxAnd extends HasBlackBoxInline {
  val io = IO(new BlackBoxAndIO)
  setInline("InlineBlackBoxAnd.v",
  s"""
  |module InlineBlackBoxAnd(a, b, c);
  |input a, b;
  |output c;
  |
  |assign c = a && b;
  |
  |endmodule
  """.stripMargin)

  
}






// }