file:///C:/Users/mark/Desktop/Chisel/chisel-template/src/main/scala/adder/adder.scala
### java.lang.AssertionError: assertion failed: position error, parent span does not contain child span
parent      = new Module(halfAdder ha2 null) # -1,
parent span = <1854..1975>,
child       = halfAdder ha2 null # -1,
child span  = [1865..1880..1979]

occurred in the presentation compiler.

action parameters:
uri: file:///C:/Users/mark/Desktop/Chisel/chisel-template/src/main/scala/adder/adder.scala
text:
```scala
package adder

import chisel3._

//full ripple-carry adder
class adder() extends Module { 
    val io = IO(new Bundle{
        val a     = Input(UInt(4.W))
        val b     = Input(UInt(4.W))
        val sum   = Output(UInt(4.W))
    })

    //vector for output
    val vect = VecInit(Seq.fill(4)(0.U(1.W))) 

    //create half adder for first bit
    val hAdd1 = new Module(halfAdder)
    hAdd1.io.a := io.a{0}
    hAdd1.io.b := io.b{0}

    val c0 = hAdd1.io.carry
    vect(0) := hAdd1.io.sum


    //create full adders for 1-width bits
    val fAdd1 = new Module(fullAdder)
    fAdd1.io.a := io.a{1}
    fAdd1.io.b := io.b{1}

    fAdd1.io.c_in := c0
    val c1 = fAdd1.io.c_out
    vect(1) := fAdd1.io.sum

    val fAdd2 = new Module(fullAdder)
    fAdd2.io.a := io.a{2}
    fAdd2.io.b := io.b{2}

    fAdd2.io.c_in := c1
    val c2 = fAdd2.io.c_out
    vect(2) := fAdd2.io.sum

    val fAdd3 = new Module(fullAdder)
    fAdd3.io.a := io.a{3}
    fAdd3.io.b := io.b{3}

    fAdd3.io.c_in := c2
    val c3 = fAdd3.io.c_out
    vect(3) := fAdd3.io.sum


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
    val io = new Bundle{
        val a     = Input(UInt(1.W))
        val b     = Input(UInt(1.W))
        val c_in  = Input(UInt(1.W))
        val sum   = Output(UInt(1.W))
        val c_out = Output(UInt(1.W))
    }

    val ha1 = new Module(halfAdder)
    ha1.io.a := io.a
    ha1.io.b := io.b1

    val ha2 = new Module(halfAdder
    ha2.io.b := ha1.io.sum

    io.sum := ha2.io.sum
    io.c_out := ha1.io.carry | ha2.io.carry

}
```



#### Error stacktrace:

```
scala.runtime.Scala3RunTime$.assertFailed(Scala3RunTime.scala:8)
	dotty.tools.dotc.ast.Positioned.check$1(Positioned.scala:175)
	dotty.tools.dotc.ast.Positioned.check$1$$anonfun$3(Positioned.scala:205)
	scala.runtime.function.JProcedure1.apply(JProcedure1.java:15)
	scala.runtime.function.JProcedure1.apply(JProcedure1.java:10)
	scala.collection.immutable.List.foreach(List.scala:333)
	dotty.tools.dotc.ast.Positioned.check$1(Positioned.scala:205)
	dotty.tools.dotc.ast.Positioned.checkPos(Positioned.scala:226)
	dotty.tools.dotc.ast.Positioned.check$1(Positioned.scala:200)
	dotty.tools.dotc.ast.Positioned.checkPos(Positioned.scala:226)
	dotty.tools.dotc.ast.Positioned.check$1(Positioned.scala:200)
	dotty.tools.dotc.ast.Positioned.check$1$$anonfun$3(Positioned.scala:205)
	scala.runtime.function.JProcedure1.apply(JProcedure1.java:15)
	scala.runtime.function.JProcedure1.apply(JProcedure1.java:10)
	scala.collection.immutable.List.foreach(List.scala:333)
	dotty.tools.dotc.ast.Positioned.check$1(Positioned.scala:205)
	dotty.tools.dotc.ast.Positioned.checkPos(Positioned.scala:226)
	dotty.tools.dotc.ast.Positioned.check$1(Positioned.scala:200)
	dotty.tools.dotc.ast.Positioned.checkPos(Positioned.scala:226)
	dotty.tools.dotc.ast.Positioned.check$1(Positioned.scala:200)
	dotty.tools.dotc.ast.Positioned.check$1$$anonfun$3(Positioned.scala:205)
	scala.runtime.function.JProcedure1.apply(JProcedure1.java:15)
	scala.runtime.function.JProcedure1.apply(JProcedure1.java:10)
	scala.collection.immutable.List.foreach(List.scala:333)
	dotty.tools.dotc.ast.Positioned.check$1(Positioned.scala:205)
	dotty.tools.dotc.ast.Positioned.checkPos(Positioned.scala:226)
	dotty.tools.dotc.parsing.Parser.parse$$anonfun$1(ParserPhase.scala:38)
	dotty.tools.dotc.parsing.Parser.parse$$anonfun$adapted$1(ParserPhase.scala:39)
	scala.Function0.apply$mcV$sp(Function0.scala:42)
	dotty.tools.dotc.core.Phases$Phase.monitor(Phases.scala:440)
	dotty.tools.dotc.parsing.Parser.parse(ParserPhase.scala:39)
	dotty.tools.dotc.parsing.Parser.runOn$$anonfun$1(ParserPhase.scala:48)
	scala.runtime.function.JProcedure1.apply(JProcedure1.java:15)
	scala.runtime.function.JProcedure1.apply(JProcedure1.java:10)
	scala.collection.immutable.List.foreach(List.scala:333)
	dotty.tools.dotc.parsing.Parser.runOn(ParserPhase.scala:48)
	dotty.tools.dotc.Run.runPhases$1$$anonfun$1(Run.scala:246)
	scala.runtime.function.JProcedure1.apply(JProcedure1.java:15)
	scala.runtime.function.JProcedure1.apply(JProcedure1.java:10)
	scala.collection.ArrayOps$.foreach$extension(ArrayOps.scala:1321)
	dotty.tools.dotc.Run.runPhases$1(Run.scala:262)
	dotty.tools.dotc.Run.compileUnits$$anonfun$1(Run.scala:270)
	dotty.tools.dotc.Run.compileUnits$$anonfun$adapted$1(Run.scala:279)
	dotty.tools.dotc.util.Stats$.maybeMonitored(Stats.scala:67)
	dotty.tools.dotc.Run.compileUnits(Run.scala:279)
	dotty.tools.dotc.Run.compileSources(Run.scala:194)
	dotty.tools.dotc.interactive.InteractiveDriver.run(InteractiveDriver.scala:165)
	scala.meta.internal.pc.MetalsDriver.run(MetalsDriver.scala:45)
	scala.meta.internal.pc.PcCollector.<init>(PcCollector.scala:42)
	scala.meta.internal.pc.PcSemanticTokensProvider$Collector$.<init>(PcSemanticTokensProvider.scala:60)
	scala.meta.internal.pc.PcSemanticTokensProvider.Collector$lzyINIT1(PcSemanticTokensProvider.scala:60)
	scala.meta.internal.pc.PcSemanticTokensProvider.Collector(PcSemanticTokensProvider.scala:60)
	scala.meta.internal.pc.PcSemanticTokensProvider.provide(PcSemanticTokensProvider.scala:81)
	scala.meta.internal.pc.ScalaPresentationCompiler.semanticTokens$$anonfun$1(ScalaPresentationCompiler.scala:99)
```
#### Short summary: 

java.lang.AssertionError: assertion failed: position error, parent span does not contain child span
parent      = new Module(halfAdder ha2 null) # -1,
parent span = <1854..1975>,
child       = halfAdder ha2 null # -1,
child span  = [1865..1880..1979]