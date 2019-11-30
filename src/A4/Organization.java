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
public class Organization {
    private String Name;
    List<Volunteer> volunteers=new ArrayList<>();
    List<Donor> donors=new ArrayList<>();
    List<Project> Projects=new ArrayList<>();
    
    public void AddDonor(String Name,Funding f,String PName)
    {
        Boolean b=false;
        int id=0;
        for(int i=0;i<donors.size() && b==false;i++)
        {
            if(donors.get(i).getName().compareToIgnoreCase(Name)==0)
            {
                b=true;
                id=i;
            }
        }
        
        Boolean flag=false;
        int j=0;
        for(int i=0;i<Projects.size() && flag==false;i++)
        {
            if(Projects.get(i).getName().compareToIgnoreCase(PName)==0)
            {
                flag=true;
                j=i;
            }
        }
        
        Boolean b2=false;
        if(flag==true)
        {
            if(b==true)
            {
                for(int k=0;k<donors.get(id).Projects.size() && b2==false;k++)
                {
                   if(donors.get(id).Projects.get(k).getName().compareToIgnoreCase(PName)==0)
                   {
                        b2=true;
                   }
                }
            }   
        }
        else
            System.out.println("Project not found");
        
        if(flag==true && b2==false)
        {
            if(b==false)
            {
                Donor d=new Donor(Name);
                d.FundSpent.add(f);
                id=donors.size();
                donors.add(d);
            }
            else
            {
                donors.get(id).FundSpent.add(f);
            }
            
            donors.get(id).Projects.add(Projects.get(j));
            Projects.get(j).donors.add(donors.get(id));
            Projects.get(j).FundsCollected.add(f);
        }
        else if(flag==true && b2==true && b==true )
            System.out.println("You have already donated");
        
        
        
//        
    }
    
    public void SearchProjectByName(String PName)
    {
        for(int i=0;i<Projects.size();i++)
        {
            if(Projects.get(i).getName().compareToIgnoreCase(PName)==0)
            {
               Projects.get(i).display();
            }
        }
    }
    
    public void SearchProjectByManager(String PManagerName)
    {
        for(int i=0;i<Projects.size();i++)
        {
            if(Projects.get(i).getProject_Manager().getName().compareToIgnoreCase(PManagerName)==0)
            {
               Projects.get(i).display();
            }
        }
    }
    
    void DonorDetails(String DonorName)
    {
        int p_total=0;
        int d_total=0;
        for(int i=0;i<donors.size();i++)
        {
            if(donors.get(i).getName().compareToIgnoreCase(DonorName)==0)
            {
                for(int j=0;j<donors.get(i).Projects.size();j++)
                {
                    donors.get(i).Projects.get(j).display();
                }
                for(int k=0;k<donors.get(i).Projects.size();k++)
                {
                    donors.get(i).FundSpent.get(k).display();
                }
               p_total=p_total+donors.get(i).Projects.size();
               d_total=d_total+donors.get(i).FundSpent.size();
            }
        }
        System.out.println("Project Total " + p_total);
        System.out.println("Donation Total " + d_total);
    }
    void ProjectBenificiary(String ProjectName)
    {
        for(int i=0;i<Projects.size();i++)
        {
            if(Projects.get(i).getName().compareToIgnoreCase(ProjectName)==0)
            {
                Projects.get(i).VeiwBeneficiary();
            }
        }
    }
    
    void ProjectDonor(String ProjectName)
    {
        for(int i=0;i<Projects.size();i++)
        {
            if(Projects.get(i).getName().compareToIgnoreCase(ProjectName)==0)
            {
                Projects.get(i).VeiwDonor();
            }
        }
    }
    
    void addProject(Project p)
    {
       Projects.add(p);
    }
    
    void BeneficiaryDetails(String BName)
    {
        int P_Total=0;
        for(int i=0;i<Projects.size();i++)
        {
            for(int j=0;j<Projects.get(i).beneficairies.size();j++)
            {
                if(Projects.get(i).beneficairies.get(j).getName().compareToIgnoreCase(BName)==0)
                {
                     Projects.get(i).beneficairies.get(j).display();
                     Projects.get(i).display();
                     P_Total++;
                     for(int k=0;k<Projects.get(i).beneficairies.get(j).FundingHistory.size();k++)
                     {
                        Projects.get(i).beneficairies.get(j).FundingHistory.get(k).display();
                     }
                    System.out.println("Donation Total " + Projects.get(i).beneficairies.get(j).FundingHistory.size());
                }
            }
        }
         System.out.println("Project Total " + P_Total);     
    }
}
