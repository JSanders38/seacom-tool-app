
package seacomtools;

public class Locations {
   
    private int locationID;
    private String locationName;
    private String address; 
    
    int nextID = 1;
    
    public Locations(String locationName, String address)
    {
        this.locationID = nextID++;
        this.locationName = locationName;
        this.address = address;
    }
    
    //Getters 
    public int getLocationID()
    {
        return this.locationID;
    }
    
    public String getLocationName()
    {
        return this.locationName;
    }
    
    public String getAddress()
    {
        return this.address;
    }
    
    //Setters
    public void setLocationID(int locationID)
    {
        this.locationID = locationID;
    }
    
    public void setLocationName(String locationName)
    {
        this.locationName = locationName;
    }
    
    public void setAddress(String address)
    {
        this.address = address;
    } 
    
    public String toString()
    {
        return this.locationName;
    }
}
