/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package A4;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hp
 */
public class Donor {
    private String Name;
    List<Funding> FundSpent=new ArrayList<>();
    List<Project> Projects=new ArrayList<>();

    public Donor(String Name) {
        this.Name = Name;
    }

    public String getName() {
        return Name;
    }
    void Addproject(Project P)
    {
        Projects.add(P);
    }
    
}
