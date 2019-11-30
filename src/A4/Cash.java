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
public class Cash extends Funding{
    int amount;

    public Cash(int amount) {
        this.amount = amount;
    }
    
    void display()
    {
        System.out.println("Donation : Cash");
    }

    int getMember()
    {
        return amount;
    }
}
