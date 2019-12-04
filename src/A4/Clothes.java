/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package A4;

/**
 *
 * @author hp
 */
public class Clothes extends Funding{
    int quant;
    
    Clothes(int q){
        quant = q;
    }
    @Override
    void display()
    {
        System.out.println(quant+" Clothes");
    }
    
    @Override
    int getMember(){
        return quant;
    }
}
