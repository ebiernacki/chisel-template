package AndOr

import chisel3._


class AndOr(width: Int) extends Module {
    val io = IO(new Bundle{
        val a = Input(UInt(width.W))
        val b = Input(UInt(width.W))
        

        val q = Output(UInt(width.W))
    })
    io.q := io.a & io.b
}

//abstraction:
//what is range of given input
//how to sample: random? all of them? what about 64bits?

