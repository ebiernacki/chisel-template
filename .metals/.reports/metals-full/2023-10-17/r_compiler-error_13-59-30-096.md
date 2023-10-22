file:///C:/Users/mark/Desktop/Chisel/chisel-template/src/main/scala/adder/adder.scala
### java.lang.IndexOutOfBoundsException: 0

occurred in the presentation compiler.

action parameters:
offset: 624
uri: file:///C:/Users/mark/Desktop/Chisel/chisel-template/src/main/scala/adder/adder.scala
text:
```scala
package adder

import chisel3._

//full ripple-carry adder
class adder(width: Int) extends Module { 
    val io = IO(new Bundle{
        val a     = Input(UInt(width.W))
        val b     = Input(UInt(width.W))
        val sum   = Output(UInt((width+1).W))
    })

    //vector for output
    val fin = (width + 1)
    val vect = VecInit(Seq.fill(fin)(0.U(1.W))) 

    //create half adder for first bit
    val hAdd1 = Module(new halfAdder)
    hAdd1.io.a := io.a{0}
    hAdd1.io.b := io.b{0}

    val c0 = hAdd1.io.carry
    vect(0) := hAdd1.io.sum

    var currentCarry = c0
    for(i <- 1 until (@@width+1){
        val fAdd = Module(new fullAdder)
        fAdd.io.a := io.a{i}
        fAdd.io.b := io.b{i}
        fAdd.io.c_in := currentCarry
        vect(i) := fAdd.io.sum
        currentCarry = fAdd.io.c_out
    }

    // //create full adders for 1-width bits
    // val fAdd1 = Module(new fullAdder)
    // fAdd1.io.a := io.a{1}
    // fAdd1.io.b := io.b{1}

    // fAdd1.io.c_in := c0
    // val c1 = fAdd1.io.c_out
    // vect(1) := fAdd1.io.sum

    // val fAdd2 = Module(new fullAdder)
    // fAdd2.io.a := io.a{2}
    // fAdd2.io.b := io.b{2}

    // fAdd2.io.c_in := c1
    // val c2 = fAdd2.io.c_out
    // vect(2) := fAdd2.io.sum

    // val fAdd3 = Module(new fullAdder)
    // fAdd3.io.a := io.a{3}
    // fAdd3.io.b := io.b{3}

    // fAdd3.io.c_in := c2
    // val c3 = fAdd3.io.c_out
    // vect(3) := fAdd3.io.sum


    //last full adder carry is ignored


    io.sum := vect.reverse.reduce(_ ## _)
}


class halfAdder() extends Module { 
    val io = IO(new Bundle{
        val a     = Input(UInt(1.W))
        val b     = Input(UInt(1.W))
        val sum   = Output(UInt(1.W))
        val carry = Output(UInt(1.W))
    })

    io.sum := io.a ^ io.b
    io.carry := io.a & io.b
}

class fullAdder() extends Module { 
    val io = IO(new Bundle{
        val a     = Input(UInt(1.W))
        val b     = Input(UInt(1.W))
        val c_in  = Input(UInt(1.W))
        val sum   = Output(UInt(1.W))
        val c_out = Output(UInt(1.W))
    })

    val ha1 = Module(new halfAdder)
    ha1.io.a := io.a
    ha1.io.b := io.b

    val ha2 = Module(new halfAdder)
    ha2.io.a := io.c_in
    ha2.io.b := ha1.io.sum

    io.sum := ha2.io.sum
    io.c_out := ha1.io.carry | ha2.io.carry

}
```



#### Error stacktrace:

```
scala.collection.LinearSeqOps.apply(LinearSeq.scala:131)
	scala.collection.LinearSeqOps.apply$(LinearSeq.scala:128)
	scala.collection.immutable.List.apply(List.scala:79)
	dotty.tools.dotc.util.Signatures$.countParams(Signatures.scala:501)
	dotty.tools.dotc.util.Signatures$.applyCallInfo(Signatures.scala:186)
	dotty.tools.dotc.util.Signatures$.computeSignatureHelp(Signatures.scala:94)
	dotty.tools.dotc.util.Signatures$.signatureHelp(Signatures.scala:63)
	scala.meta.internal.pc.MetalsSignatures$.signatures(MetalsSignatures.scala:17)
	scala.meta.internal.pc.SignatureHelpProvider$.signatureHelp(SignatureHelpProvider.scala:51)
	scala.meta.internal.pc.ScalaPresentationCompiler.signatureHelp$$anonfun$1(ScalaPresentationCompiler.scala:375)
```
#### Short summary: 

java.lang.IndexOutOfBoundsException: 0