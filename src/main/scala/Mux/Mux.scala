package Mux

import chisel3._
import chisel3.util._

class Mux() extends Module { //2to1
  val io = IO(new Bundle {
    val sel        = Input(UInt(1.W))
    val d1         = Input(UInt(1.W))
    val d2         = Input(UInt(1.W))
    val out        = Output(UInt(1.W))
  })
  val notSel = ~io.sel

  val t1 = io.d1 & notSel
  val t2 = io.d2 & io.sel
  io.out := t1 | t2
}


class Mux4to1() extends Module { //4to1
    val io = IO(new Bundle {
        val s0         = Input(UInt(1.W))
        val s1         = Input(UInt(1.W))
        val i1         = Input(UInt(1.W))
        val i2         = Input(UInt(1.W))
        val i3         = Input(UInt(1.W))
        val i4         = Input(UInt(1.W))
        val y          = Output(UInt(1.W))
    })
    val m1 = Module(new Mux)
    val m2 = Module(new Mux)

    m1.io.sel := io.s1
    m2.io.sel := io.s1

    m1.io.d1 := io.i1
    m1.io.d2 := io.i2

    m2.io.d1 := io.i3
    m2.io.d2 := io.i4

    val m3 = Module(new Mux)
    m3.io.sel := io.s0

    m3.io.d1 := m1.io.out
    m3.io.d2 := m2.io.out

    io.y := m3.io.out

}