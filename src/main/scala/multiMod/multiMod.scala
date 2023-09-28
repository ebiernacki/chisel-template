package multiMod

import chisel3._


trait myT {
    def op(x: BigInt, y: BigInt): BigInt
}

class orGate(width: Int) extends Module with myT{
    val io = IO(new Bundle {
        val a  = Input(UInt(width.W))
        val b  = Input(UInt(width.W))

        val out = Output(UInt(width.W))
    })

    def op(x: BigInt, y: BigInt): BigInt = {
        x | y
    }

    io.out := io.a | io.b
}

class myAnd(width: Int) extends Module with myT{
    val io = IO(new Bundle {
        val a  = Input(UInt(width.W))
        val b  = Input(UInt(width.W))

        val out = Output(UInt(width.W))
    })

    def op(x: BigInt, y: BigInt): BigInt = {
        x & y
    }

    io.out := io.a & io.b
}



    

