/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package A4;


import java.util.ArrayList;
import java.util.List;
import java.io.*;
/**
 *
 * @author hp
 */
public class Project implements Serializable
{
    private String Name;
    private String Dsc;
    List<Volunteer> Team=new ArrayList<>();
    private String TeamName;
    Volunteer Project_Manager;
    List<Donor> donors=new ArrayList<>();
    List<Beneficiary> Applicants=new ArrayList<>();
    List<Beneficiary> beneficairies=new ArrayList<>();
    List<Funding> FundsCollected=new ArrayList<>();
    int Req_Budet;

    public String getName() {
        return Name;
    }
    
    public void display()
    {
        System.out.println("Project Name "+ Name + " Description " + Dsc +
                " Project manager is "+ Project_Manager.getName()+" Required Budget is "+Req_Budet);
    }
    
    public Project(String Name, String Dsc,String Manager_Name,int Req_Budet) 
    {
        this.Name = Name;
        this.Dsc = Dsc;
        Project_Manager=new Volunteer(Manager_Name);
        this.Req_Budet=Req_Budet;
    }
    
    public void AllocateTeam(String TName)
    {
        TeamName=TName;
    }
     
    public void addTeamMember(String Name)
    {
        Volunteer v=new Volunteer(Name);
        Team.add(v);
    }
    public void RemoveTeamMember(String Name)
    {
        for(int i=0;i<Team.size();i++)
        {
            if(Team.get(i).getName().compareToIgnoreCase(Name)==0)
            {
                Team.remove(i);
            }
        }
    }
    
    public void ReplaceTeamMember(String CurrName,String NewName)
    {
       for(int i=0;i<Team.size();i++)
        {
            if(Team.get(i).getName().compareToIgnoreCase(CurrName)==0)
            {
                Team.get(i).setName(NewName);
            }
        }
    }
    
    public void ShortListApplicants(int income)
    {
        for(int i=0;i<Applicants.size();i++)
        {
            if(Applicants.get(i).getIncome()<income)
            {
                beneficairies.add(Applicants.get(i));
            }
        }
    }

    public Volunteer getProject_Manager() {
        return Project_Manager;
    }
   
    public void VeiwBeneficiary()
    {
        for(int i=0;i<beneficairies.size();i++)
        {
            beneficairies.get(i).display();
        }
    }
    
    public void VeiwDonor()
    {
        for(int i=0;i<donors.size();i++)
        {
            donors.get(i).getName();
        }
    }
    public void AddApplicant(String Name, String dob, int income, String PhoneNo, String Address)
    {
       Beneficiary b=new Beneficiary(Name,dob,income,PhoneNo,Address);
       Applicants.add(b);
    }
    
    
    public void GiveDonation(Beneficiary b , Funding f)
    {
        Boolean flag=false;
        if(FundsCollected.isEmpty())
                System.out.println("Zero balance");
        else
        {
            int Balance=0;
            for(int i=0;i<FundsCollected.size();i++)
            {
                if(FundsCollected.get(i) instanceof  Cash)
                {
                    Balance=Balance+FundsCollected.get(i).getMember();
                }
            }  
            if(f instanceof  Cash)
            {
                for(int i=0;i<FundsCollected.size()&& flag==false;i++)
                {
                    if(FundsCollected.get(i) instanceof  Cash)
                    {
                        FundsCollected.remove(i);
                        flag=true;
                    }
                        
                }     
                if(f.getMember() < Balance)
                {
                    b.FundingHistory.add(f);
                    Balance=Balance-f.getMember();
                }
                else
                    System.out.println("Insufficient balance");
            }
            else if(f instanceof FoodItems)
            {
               b.FundingHistory.add(f);
               for(int i=0;i<FundsCollected.size()&& flag==false;i++)
                {
                    Funding fund=FundsCollected.get(i);
                    if(fund instanceof  FoodItems)
                    {
                        FundsCollected.remove(i);
                        flag=true;
                    }
                }     
            }
            else
            {
               b.FundingHistory.add(f);
               for(int i=0;i<FundsCollected.size()&& flag==false;i++)
               {
                    if(FundsCollected.get(i) instanceof  Clothes)
                    {
                       FundsCollected.remove(i);
                       flag=true;
                    }       
               }
            }
            System.out.println("Remaining Cash Balance is " + Balance );
        }          
    }
}
