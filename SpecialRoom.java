
/**
 * Write a description of class SpecialRoom here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class SpecialRoom extends Room
{
    // instance variables - replace the example below with your own
    

    /**
     * Constructor for objects of class SpecialRoom
     */
    public SpecialRoom(boolean walkers, String description)
    {
        // initialise instance variables
        super(walkers,description);
    }

    // public void enter(Command command)
    // {
        // if(!command.hasSecondWord())
        // {
            // super.enter(command);
            // return;
        // }
        
        // if(command.getSecondWord().equals("cave"))
        // {
           // System.out.println("You enter the cave. It leads you into a dark underground tunnel. \n When you reach the end, you hit thinner woods far south."); 
           // Room d1 = new Room(false," in thinner woods. There is a grove to the south. The cave has collapsed. You cannot go back.");                   
           
           // this.setExit("out", d2);
           // // need a way to set to d2 currentRoom 
           
           // // this.changeDescription("In the computing admin office. You see a depressed button on the desk. \n You see an open bookshelf with a set of stairs leading down.");
        // }
        // else
        // {
            // super.enter(command);
        // }
   //
   // }
}
