import java.util.*;
import java.io.*;
/**
 * Write a description of class GuidedTour here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class GuidedTour
{
    // instance variables - replace the example below with your own
    private File source;
    private Scanner input;
    private Command demoCommand;
    private String word1;
    private String word2;
   
    
    /**
     * Constructor for objects of class GuidedTour
     */
    public GuidedTour()
    {
        // initialises the demo game
        Game demoGame = new Game();
        demoGame.playDemo();
        
        //reads input from text file
        try
        {
            source = new File("GuidedTourCommands.txt");
            input = new Scanner(source);
        }
        
        catch(Exception e)
        {
            //prints out error if file is not found
            System.out.println(e);
        }
        
        input.useDelimiter(" ");
        
        while(input.hasNext())
        {
         word1 = input.next();
         if(input.hasNext())
         {
         word2 = input.next();
         //creates command based on words from .txt file
         demoCommand = new Command(word1, word2);
         //processes the command
         demoGame.processCommand(demoCommand);
        }
        else
        {
            return;
        }
        }
        
    }
    
}
