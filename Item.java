
/**
 * Write a description of class Item here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Item
{
    // instance variables - replace the example below with your own
    private String name;
    private String description;
    private int mod;

    /**
     * Constructor for objects of class Item
     */
    public Item(String name, String description, int mod)
    {
        this.name = name;
        this.description = description;
        this.mod = mod;
    }

    
    public String getName()
    {
        return name;
    }
    
    public String getDescription()
    {
        return description;
    }
    
    public int getMod()
    {
        return mod;
    }

}
