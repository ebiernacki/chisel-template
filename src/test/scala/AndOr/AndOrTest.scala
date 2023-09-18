package AndOr


import chisel3._
import chisel3.experimental.BundleLiterals._
import chiseltest._

import org.scalatest.flatspec.AnyFlatSpec


class AndOrTest extends AnyFlatSpec with ChiselScalatestTester {
  
  def generateOutputVals(): Seq[Int] = {
    var outputs:Seq[Int] = Seq()

    for (a <- 0 until 2){
      for(b <- 0 until 2){
        //calculate and of both values 
        var anded = a & b
        outputs = outputs :+ anded
      }
    }
    println("OUTPUTS ARE: " + outputs)
    //return the seq
    return outputs
  } 

  def generateInputVals(bitLen: Int): Seq[Seq[Int]]  = {
    val bitL = bitLen + 1
    //generate sequences of all	possible variations of bits
    //then put them in seq

    var wrap:Seq[Seq[Int]] = Seq()
    
    for (a <- 0 until bitL){
      for(b <- 0 until bitL){ //recursion note!
        var vals:Seq[Int] = Seq(a , b)
        wrap = wrap :+ vals
      }
    }
    println("ALL POSSIBLE COMBOS:::")
    for(s <- wrap){
      for (e <- s){
        print(e)
      }
      print(", ")
    }
    println()
    
    return wrap

  } 

  def temp(width: Int): Int = {
    val totalCombos = Math.pow(2, 2 * width).toInt
    for (i <- 0 until totalCombos) {
        // Convert the decimal number 'i' to its binary representation
        val binaryStr = i.toBinaryString //.padTo(2 * width, 0)
        println(binaryStr)

        
    }
    return 0
  }



  "Hardware" should "match Software" in {
    
    test(new AndOr(1)) { c =>
      //Parse through the seq[seq] and poke with those values

      var seqs = generateInputVals(1)
      var outs = generateOutputVals()
      var count = 0

      //var i = temp(2) //for printing binary


      println("a b q")
      //for each seq in seqs
      for(s <- seqs){
        c.io.a.poke(s(0))
        c.io.b.poke(s(1))


        //expect the correct outputs
        c.io.q.expect(outs(count))
        count = count + 1
        
        for(e <- s) print(s"$e ")
        println(c.io.q.peek().litValue)
        //println("Peeked: " + c.io.q.peek().litValue)


     }
    }
    
  }
  
}



