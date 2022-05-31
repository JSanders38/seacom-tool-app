
package seacomtools;

public class Employee {
    
    private int empID;
    private String name;
    private String phoneNum; 
    private String email; 
    private String empStatus;
    
    int nextID = 1;
    
    public Employee(String name, String phoneNum, String email, String empStatus){
        
        this.empID = nextID++;
        this.name = name;
        this.phoneNum = phoneNum; 
        this.email = email; 
        this.empStatus = empStatus;
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

    public String getEmpStatus()
    {
        return this.empStatus;
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

     public void setEmpStatus(String empStatus)
     {
         this.empStatus = empStatus;
     }
     
     public String toString()
     {
         return this.name; 
     } 
     
    
}
