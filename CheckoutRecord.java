
package seacomtools;

public class CheckoutRecord {
    
    private Tool toolNumber;
    private Employee employeeObj;
    private Locations locationObj;
    private String date;
    

// Does toolNumber variable need to change to int and only pass-through the number
    // instead of passing through the whole object
    // Same for Employee Name
public CheckoutRecord(Tool toolNumber, Employee employeeObj, Locations locationObj, String date)
    {
        this.toolNumber = toolNumber;
        this.employeeObj = employeeObj;
        this.locationObj = locationObj;
        this.date = date;
    
    } 

public CheckoutRecord()
{
    
} 

//Getters
public Tool getToolNumber()
{
    return this.toolNumber;
}

public Employee getEmployeeObj()
{
    return this.employeeObj;
}

public Locations getLocationObj()
{
    return this.locationObj;
}

public String getDate()
{
    return this.date;
}

//Setters
public void setToolNumber(Tool toolNumber)
{
    this.toolNumber = toolNumber;
}

public void setEmployeeObj(Employee employeeObj)
{
    this.employeeObj = employeeObj;
}

public void setLocationObj(Locations locationObj)
{
    this.locationObj = locationObj;
}

public void setDate(String date)
{
    this.date = date;
} 

public String toString()
{
    return this.toolNumber.getToolNum() + " " + this.toolNumber.getToolName()+ " " 
            + this.employeeObj.getName() + " " + this.date;
}


}
