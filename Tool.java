
package seacomtools;


public class Tool {
    
    private int toolNum;
    private String toolName;
    private String toolStatus;
    private String toolDescription;
    private String modelNum;
    private String serialNum;
    private String purchaseDate;
    private double purchasePrice;
    private String extraInfo;
    private String shopLocation; 
    
public Tool(int toolNum, String toolName, String toolStatus, String toolDescription, String modelNum, 
        String serialNum, String purchaseDate, double purchasePrice, String extraInfo, String shopLocation)
{
    this.toolNum = toolNum;
    this.toolName = toolName;
    this.toolStatus = toolStatus;
    this.toolDescription = toolDescription;
    this.modelNum = modelNum;
    this.serialNum = serialNum;
    this.purchaseDate = purchaseDate;
    this.purchasePrice = purchasePrice;
    this.extraInfo = extraInfo;
    this.shopLocation = shopLocation;
    
} 

// No arg constructor
public Tool()
{
    this.toolNum = 0000;
    this.toolName = "";
    this.toolStatus = "";
    this.toolDescription = "";
    this.modelNum = "";
    this.serialNum = "";
    this.purchaseDate = "";
    this.purchasePrice = 0.00;
    this.extraInfo = "";
    this.shopLocation = "";
    
}
    //Getters
    public int getToolNum()
    {
        return this.toolNum;
    }
    
    public String getToolName()
    {
        return this.toolName;
    }
    
    public String getToolStatus()
    {
        return this.toolStatus;
    }
    
    public String getToolDescription()
    {
        return this.toolDescription;
    }
    
    public String getModelNum()
    {
        return this.modelNum;
    }
    
    public String getSerialNum()
    {
        return this.serialNum;
        
    }
    
    public String getpurchaseDate()
    {
        return this.purchaseDate;
    }
            
    public double getPurchasePrice()
    {
        return this.purchasePrice;
    }
    
    public String getExtraInfo()
    {
        return this.extraInfo;
    }
    
    public String getShopLocation()
    {
        return this.shopLocation;
    }
 
    
    //Setters 
    public void setToolNum(int toolNum)
    {
        this.toolNum = toolNum;
    }
    
    public void setToolName(String toolName)
    {
        this.toolName = toolName;
    }
    
    public void setToolStatus(String toolStatus)
    {
        this.toolStatus = toolStatus;
    }
    
    public void setToolDescription(String toolDescription)
    {
        this.toolDescription = toolDescription;
    }
    
    public void setModelNum(String modelNum)
    {
        this.modelNum = modelNum;
    }
    
    public void setSerialNum(String SerialNum)
    {
        this.serialNum = serialNum;
    }
    
    public void setPurchaseDate(String purchaseDate)
    {
        this.purchaseDate = purchaseDate;
    }
    
    public void setPurchasePrice(double purchasePrice)
    {
        this.purchasePrice = purchasePrice;
    }
    
    public void setExtraInfo(String extraInfo)
    {
        this.extraInfo = extraInfo;
    }
    
    public void setShopLocation(String shopLocation)
    {
        this.shopLocation = shopLocation;
    }
    
//    public String toString()
//    {
//        return "Toolthis.toolNum + "   " + this.toolName +"   " + this.toolStatus + "  " + this.toolDescription + 
//                "   " +  this.modelNum + "   " + this.serialNum + "   " +
//                this.purchaseDate + "   " + this.purchasePrice + " " + this.extraInfo + " " + this.shopLocation;
//    } 
    
    public String toString()
    {
        return "Tool #: " + this.toolNum + "  " + this.toolName + "     " + "Status: " + this.toolStatus;  
    }
    
}
