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
public class FoodItems extends Funding{
    int quant;
    
    FoodItems(int q){
        quant = q;
    }
    @Override
    void display()
    {
        System.out.println(quant+" Food Items");
    }
    
    @Override
    int getMember(){
        return quant;
    }
}
