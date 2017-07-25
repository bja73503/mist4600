
import java.util.*;
/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.08.08
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
    private ArrayList<Item> usersInventory;

    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
        usersInventory = new ArrayList<Item>();
        
        
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room a1, a2, a3, a4, b1, b2, b3, b4, c1, c2, c3, c4, d1, d2, d3, d4, e1, e2, e3, e4, f1, f2, f3, f4, g1, g2,
        g3, g4;
        
        Item cloak;
        Item boots;
                        
        // create the rooms: rooms that are barriers are commented out
        // a1 = barrier
        a2 = new Room(false,"Snowy forests are to your south, and the gate to Castle Black is in front of you.");
        a3 = new Room(true,"You're in a snowy clearing. The Wall is to your south.");
        // a4 = barrier
        // b1 = barrier
        b2 = new Room(false,"You are in a snowy clearing. The Wall looks to be to the south.");
        b3 = new Room(false,"You are in snowy woods, but they seem to be thinning.");
        b4 = new Room(true,"You are in snowy woods, but they seem to be thinning.");
        c1 = new Room(false,"You face a Weirwood tree, and it is encircled by other trees. There is an odd energy here.");
        c2 = new Room(true,"The woods here are snowy and dense, but they seem to be getting thinner.");
        c3 = new Room(false,"The trees around you are dense and snowy"); 
        c4 = new Room(false,"Before you is a frozen lake. It should be thick enough to walk on.");
        d1 = new Room(false,"You are in a thinnly wooded area just outside the cave exit.");
        // d2 = barrier
        d3 = new Room(false,"You are inside an abandoned Cabin");
        // d4 = barrier
        // e1 = barrier
        e2 = new Room(false,"The trees around you are dense and snowy");
        e3 = new Room(true,"The trees around you are dense and snowy");
        e4 = new Room(false,"The trees around you are dense and snowy");
        f1 = new Room(false,"You are in an abandoned camp; the tents are shreadded and the fire is dead. There are boots by the fire.");
        f2 = new Room(false,"The brush and trees here are dense and snowy.");
        f3 = new Room(false,"The brush and trees here are dense and snowy.");
        // f4 = barrier
        g1 = new Room(true,"The trees here are dense and snowy");
        g2 = new Room(false,"You are in a snowy clearing surronded by trees and brush");
        g3 = new Room(false,"You surronded by trees, and there seem to be mountains to your west.");
        g4 = new Room(false,"Before you is the opening to a cave.");
       
        // declair and set items 
        cloak = new Item("cloak", "a special black cloak that helps to hide you from white walkers.", 3);
        boots = new Item("boots", "special boots that help to hide your tracks in the snow.", 3);
        d3.setItem(cloak);
        f1.setItem(boots);

        
        // initialise room exits
        a3.setExit("east",a2);
        a3.setExit("south",b3);
        
        b2.setExit("south",a2);
        b2.setExit("west",b3);
        b2.setExit("north",c2);
        
        b3.setExit("south",a3);
        b3.setExit("north",c3);
        b3.setExit("east",b2);
        b3.setExit("west",b4); 
        
        b4.setExit("east",b3);
        b4.setExit("north",c4);
        
        c1.setExit("west",c2);
        c1.setExit("north",d1);
        
        
        c2.setExit("south",b2);
        c2.setExit("west",c3);
        c2.setExit("east",c1);
        
        c3.setExit("south",b3);
        c3.setExit("north",d3);
        c3.setExit("west",c4);
        c3.setExit("east",c4);
        
        c4.setExit("south",b4);
        c4.setExit("east",c3);
        
        d1.setExit("south",c1);
        
        d3.setExit("south",c3);
        d3.setExit("north",e3);
        
        e2.setExit("north",f2);
        e2.setExit("west",e3);
        
        e3.setExit("south",d3);
        e3.setExit("north",f3);
        e3.setExit("west",e4);
        e3.setExit("east",e2);
        
        e4.setExit("east",e3);
        
        f1.setExit("west",f2);
        f1.setExit("north",g1);
        
        f2.setExit("south",e2);
        f2.setExit("north",g2);
        f2.setExit("west",f3);
        f2.setExit("east",f1);
        
        f3.setExit("south",e3);
        f3.setExit("north",g3);
        f3.setExit("east",f2);
        
        g1.setExit("south",f1);
        g1.setExit("west",g2);
        
        g2.setExit("south",f2);
        g2.setExit("west",g3);
        g2.setExit("east",g1);
        
        g3.setExit("south",f3);
        g3.setExit("west",g4);
        g3.setExit("east",g2);
        
        g4.setExit("east",g3);    
        
        currentRoom = g2;       

    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to Beyond The Wall!");
        System.out.println("You've woken up in the snowy, wooded wilderness. Disoriented, you begin to assess \n your surroundings and hear faint screams in the distance. \n You must make it to the wall!.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        // System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
            goRoom(command);
        }
        else if (commandWord.equals("look")) {
            currentRoom.look(command);
            System.out.println(currentRoom.getLongDescription());
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }
        else if (commandWord.equals("take")) {
            take(command);
        }
        else if (commandWord.equals("sneak")) {
            sneak(command);
        }
        else if (commandWord.equals("pray")) {
            pray(command);
        }
        else if (commandWord.equals("enter")) {
            enter(command);
        }
        // else command not recognised.
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("through the woods, ever aware of the Walkers \n around you.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /** 
     * Try to in to one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("The terrain looks too dangerous! Try another way.");
        }
        else {
            currentRoom = nextRoom;
            //walkerEncounter();
            if (currentRoom.hasWalkers() == true) {
                walkerEncounter();
            }         
            System.out.println(currentRoom.getLongDescription());
        }
        
        winGame();
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
    
    private void take(Command command)
    {
        if (!command.hasSecondWord()) {
            System.out.println("Take what?");
        } 
        else {
            if (currentRoom.hasItem() == false) {
                System.out.println("There is nothing to take in this area.");            
            }
            else {
                Item rooItem = currentRoom.getItem();
                if (command.getSecondWord().equals(currentRoom.getItem())) {
                    Item thisItem = currentRoom.getItem();
                    usersInventory.add(thisItem);
                    
                    currentRoom.removeItem();
                    
                    System.out.println("You have taken " + thisItem.getName() + ". [+" + thisItem.getMod()+ "sneak]");
                }           
            }
        }
    }
    
    private void sneak(Command command)
    {
        if (!command.hasSecondWord()) {
            sneakCheck();
        } else {
            System.out.println("What do you want to do?");
        }
    }

    private void pray(Command command)
    {
        if (currentRoom.getShortDescription().equals("You face a Weirwood tree, and it is encircled by other trees. There is an odd energy here.")) {
            Item blessing = new Item("Blessing","the Old Gods have heard your prayers",5);
            usersInventory.add(blessing);
            
            System.out.println("The wind seems to answer your prayers and you feel completely aware. [+5 sneak]");
        } else {
            System.out.println("You hear nothing but the wind.");
        }
    }
    
    private void enter(Command command)
    {
        if(!command.hasSecondWord()) {
            System.out.println("Enter what?");
        } else if (command.getSecondWord().equals("cave") && currentRoom.getShortDescription().equals("Before you is the opening to a cave.")) {
            System.out.println("You enter the cave and travel through the darkness for a while.");
            System.out.println("When you exit the cave, you are in a thinly wooded area.");
            System.out.println("You hear what might be walkers in the cave behind you, and decide");
            System.out.println("not to try and go back through it.");
            System.out.println("There appears to be a grove just to the south of you.");
            // currentRoom = d1;
            // need to find a way to set the player to room d1 when they enter the cave -Pat
        }
    }
    private void winGame()
    {
        if (currentRoom.getShortDescription().equals("Snowy forests are to your south, and the gate to Castle Black is in front of you.")) {
            System.out.println("Before you is the gate to Castle Black. You hear a horn blast and the gate starts to open");
            System.out.println("You return to Castle Black, and have escaped the Walkers... for now.");
            System.out.println("GAME OVER");
            
            Command quitGame = new Command("quit",null);
            quit(quitGame);
        }
    }
    
    /**
     * Establishes a int variable 'sum', looks through the items the player has, determines the items' mod value,
     * then adds the total mod value up and returns them as the variable 'sum'.
     * 
     * Used in the sneak() method.
     */
    private int modify()
    {
        int sum = 0;
        
        for (Item thisItem : usersInventory) {
            thisItem.getMod();
            if (thisItem.getMod() > 0) {
                int modifier = thisItem.getMod();
                sum = sum + modifier;
            }
        }
        return sum;
    }
    
    private int sneakCheck()
    {
        if (currentRoom.hasWalkers() == false) {
           // System.out.println("There is nothing to hide from here.");
            return 100;
        } else {
            int d20 = (int)(Math.random() * 20 + 1);           
            
            int sneakSum = d20 + modify();
            
            return sneakSum;
            
            // if (sneakSum <= 7) {              
                // loseGame();
            // } else {
                // System.out.println("The White Walker does not notice you. You may proceed.");
            // }
        }            
    }
    
    private void loseGame()
    {
        System.out.println("You hear a blood curdling scream! There is no where to hide. . .");
        System.out.println("GAME OVER");
        
        Command quitGame = new Command("quit",null);
        quit(quitGame);
        
        //^^^ these 2 lines do not do the intended purpose. Maybe parser should be included to end the game via code?
        // -Pat
    }
    
    private void walkerEncounter()
    {
            System.out.println("There is a White Walker just ahead of you. It doesn't seem to notice you yet.");
            System.out.println("Perhaps you could sneak past it. . . ");       
            
            int scv = sneakCheck();
            
            if (scv == 100) {
                System.out.println("There is nothing to hide from here.");
            } else if (scv <= 7) {
                System.out.println("Sneak check failed!");
                loseGame();
            } else {
                System.out.println();
              
            }
    }
}
