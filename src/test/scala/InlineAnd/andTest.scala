package InlineAnd

import chisel3._
import chisel3.experimental.BundleLiterals._
import chiseltest._

import org.scalatest.flatspec.AnyFlatSpec

//Write a scala func that takes in 2 boolean (one bit) inputs
//and output the and of those 2 values. Then have chisel test run 
//and compare that software = hardware output

//scala func should generate truth table values: store some how (array?)
//then use peak poke and expect said value
//try printf truth table first then feed values to peak poke





//need scala func stuff 
class InlineAnd extends Module {
  val io = IO(new BlackBoxAndIO)
  val and = Module(new InlineBlackBoxAnd)
  io <> and.io
}

//Hardware test using chiseltest libary and peak/poke functions
//.withAnnotations(Seq(VerilatorBackendAnnotation))


class andTest extends AnyFlatSpec with ChiselScalatestTester {
  /*
  def swTestVals(): Array[BigInt] = {
    var z = new Array[BigInt](0)

    for (a <- 0 until 2){
      for(b <- 0 until 2){

        //actually want to feed these values to the hardware tester

        //calculate and of both values 
        var anded = a & b
        z = z :+ anded
      }
    }
    var zstring = z.mkString
    println("Bitwise Result:")
    println(zstring)

    //return the array
    return z
  } 

  def valGen(): Seq[Seq[Int]]  = {
    val bitlen = 1
    //val inputs = 2 
    //generate sequences of all	possible variations of bits
    //then put them in seq

    var wrap:Seq[Seq[Int]] = Seq()
    
    for (a <- 0 until 2){
      for(b <- 0 until 2){
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
    println("")
    
    return wrap

  } 
  */


  // "AndTest" should "pass" in {
    
  //   test(new InlineAnd).withAnnotations(Seq(VerilatorBackendAnnotation)) { c =>
  //     //Parse through the seq[seq] and poke with those values
  //     //work in yeild???????

  //     var seqs = valGen()
  //     //for each seq in seqs
  //       //for each value in seq
  //         //poke values


  //         //expect values from swTestVals

  //     println("a b c")
  //     var z = new Array[BigInt](0)
  //     for (a <- 0 until 2){
  //       for(b <- 0 until 2){
  //         c.io.a.poke(a.U)
  //         c.io.b.poke(b.U)
  //         var cVal = c.io.c.peek().litValue
          
  //         z = z :+ cVal
  //         println(a.toString + " " + b.toString + " " + cVal)
  //       }
  //     }
  //     var zstring = z.mkString
  //     println("Hardware Result:")
  //     println(zstring)


  //     //print statement is inside tester
  //     var myArr = valGen()

      

  //   }
    
  // }
  
}



