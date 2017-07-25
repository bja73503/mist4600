import java.util.Set;
import java.util.HashMap;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  For each existing exit, the room 
 * stores a reference to the neighboring room.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.08.08
 */

public class Room 
{
    protected String description;
    protected boolean walkers;
    protected HashMap<String, Room> exits;        // stores exits of this room.
    protected Item roomItem;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(boolean walkers, String description) 
    {
        this.description = description;
        exits = new HashMap<String, Room>();
        //items = new HashMap<String,Item>();
        
    }

    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor  The room to which the exit leads.
     */
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }

    /**
     * @return The short description of the room
     * (the one that was defined in the constructor).
     */
    public String getShortDescription()
    {
        return description;
    }

    /**
     * Return a description of the room in the form:
     *     You are in the kitchen.
     *     Exits: north west
     * @return A long description of this room
     */
    public String getLongDescription()
    {
        return "You are " + description + ".\n" + getExitString() + ".\n" + getItemString();
        
        //^^^ going to need to workshop this; set non-sig. rooms to null and manually enter descriptions for 
        // sig. rooms (e.g. c1, d3, etc.) -Pat
    }

    /**
     * Return a string describing the room's exits, for example
     * "Exits: north west".
     * @return Details of the room's exits.
     */
    private String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    private String getItemString()
    {
        if (hasItem() == true) {
        String returnString = "Items:";
        // Set<String> keys = items.keySet();
        // for(String item : keys) {
            // returnString += " " + item;
        // }
        
       System.out.print(roomItem.getName());
        return returnString;
        } else {
            return "No useful items here";
        }
    }
    
    
    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     * @param direction The exit's direction.
     * @return The room in the given direction.
     */
    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }
    
    public void setItem(Item item)
    {
        //items.put(item.getName(),item);
        roomItem = item;
    }
    
    public Item getItem()
    {
        //return items.get(itemName);
        return roomItem;
    }
    
    public void removeItem()
    {
        //items.remove(itemName);
        roomItem = null;
        
    }
    
    public void enter(Command command)
    {
        System.out.println("Enter what?");
    }
    
    public boolean hasWalkers()
    {
        return walkers;
    }
    
    public void look(Command command)
    {
        if (!command.hasSecondWord()) {
            System.out.println(getLongDescription());           
            
        } else {
            System.out.println("There is nothing to look at.");
        }
    }
    
    public boolean hasItem()
    {
        if (roomItem != null) {
            return true;
        } else {
            return false;
        }
        
    }
}

