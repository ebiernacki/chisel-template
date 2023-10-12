package dFF

import chisel3._
import chisel3.util._
import chisel3.experimental._
import chisel3.experimental.BundleLiterals._
import chisel3.util.HasBlackBoxInline

class dFFIO extends Bundle{
    val d   = Input(UInt(1.W))
    val clk = Input(Clock())
    val rst = Input(Bool())

    val q   = Output(UInt(1.W))

    //val nq  = Output(UInt(1.W))
    
}

class InlineBlackBoxDFF extends HasBlackBoxInline {
    val io = IO(new dFFIO)
    setInline("InlineBlackBoxDFF.v",
    s"""
    |module InlineBlackBoxDFF(d, clk, rst, q);
    |input d, clk, rst;
    |output reg q;
    |
    |always @(posedge clk or posedge rst) 
    |begin
    |if(rst == 1'b1)
    |q <= 1'b0;
    |else
    |q <= d;
    |end 
    |endmodule 
     """.stripMargin)

}
//not working:
// // s"""
    // |module nand_gate(c,a,b); 
    // |input a,b; 
    // |output c; 
    // |assign c = ~(a&b); 
    // |endmodule
    // |
    // |module not_gate(f,e); 
    // |input e; 
    // |output f; 
    // |assign f= ~e; 
    // |endmodule
    // |
    // |module InlineBlackBoxDFF(q,nq,d,clk);
    // |input d,clk; 
    // |output q, nq; 
    // |wire x , y, nd;
    // |not_gate not1(nd,d); 
    // |nand_gate nand1(x,clk,d); 
    // |nand_gate nand2(y,clk,nd); 
    // |nand_gate nand3(q,nq,y); 
    // |nand_gate nand4(nq,q,x); 
    // |endmodule
    // """.stripMargin)



// working: no circular
//     s"""
//     |module InlineBlackBoxDFF(d, clk, rst, q);
//     |input d, clk, rst;
//     |output reg q;
//     |
//     |always @(posedge clk or posedge rst) 
//     |begin
//     |if(rst == 1'b1)
//     |q <= 1'b0;
//     |else
//     |q <= d;
//     |end 
//     |endmodule 
//     """.stripMargin)
// 




//working just gates, warnigns on circular logic
// s"""
//    |module nand_gate(c,a,b); 
//    |input a,b; 
//    |output c; 
//    |assign c = ~(a&b); 
//    |endmodule
//    |
//    |module not_gate(f,e); 
//    |input e; 
//    |output f; 
//    |assign f= ~e; 
//    |endmodule
//    |
//    |module InlineBlackBoxDFF(q,nq,d,clk);
//    |input d,clk; 
//    |output q, nq; 
//    |not_gate not1(dbar,d); 
//    |nand_gate nand1(x,clk,d); 
//    |nand_gate nand2(y,clk,dbar); 
//    |nand_gate nand3(q,nq,y); 
//    |nand_gate nand4(nq,q,x); 
//    |endmodule
//     """.stripMargin)