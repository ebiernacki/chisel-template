package binToBCD
import chisel3._
import chisel3.util._
import chiseltest._
import org.scalatest.flatspec.AnyFlatSpec

class InlineBCD extends Module{
    val io = IO(new blackBoxBCDIO)
    val bcd = Module(new InlineBlackBoxBCD)
    io <> bcd.io
}
class bcdTest extends AnyFlatSpec with ChiselScalatestTester {  
    "binToBCD" should "pass" in {
        test(new InlineBCD).withAnnotations(Seq(VerilatorBackendAnnotation)){ dut =>
            dut.io.bin.poke(248.U)
            System.out.println(dut.io.bcd.peek().litValue)
        }
    }
}