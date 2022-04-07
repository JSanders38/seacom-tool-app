
package seacomtools;

public class Employee {
    
    private int empID;
    private String name;
    private String phoneNum; 
    private String email;
    
    int nextID = 1;
    
    public Employee(String name, String phoneNum, String email){
        
        this.empID = nextID++;
        this.name = name;
        this.phoneNum = phoneNum; 
        this.email = email;
    }     
    
    //getters 
    public int getEmpID()
    {
        return this.empID;
    }
    
    public String getName()
    {
        return this.name;
    }
    
    public String getPhoneNum()
    {
        return this.phoneNum;
    } 
    
    public String getEmail()
    {
        return this.email;
    }
    
    //setter 
     public void setEmpID(int empID)
     {
         this.empID = empID;
     }
     
     public void setName(String name)
     {
         this.name = name;
     }
     
     public void setPhoneNum(String phone)
     {
         this.phoneNum = phone;
     } 
     
     public void setEmail(String email)
     {
         this.email = email;
     }
     
     public String toString()
     {
         return this.name; 
     } 
     
    
}
