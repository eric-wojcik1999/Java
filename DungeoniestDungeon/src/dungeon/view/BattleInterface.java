package dungeon.view; 

import dungeon.controller.BattleController; 
import dungeon.view.UserInputUtil; 

/**************************************************************************
*  Class: BattleInterface                                                 *
*  Author: Eric Wojcik                                                    *
*  StudentID: 19142124                                                    *
*  Date Created: 14/5/2020                                                *
*  Date Modified: 26/5/2020                                               *
*  Purpose: Serves as the user interface for the battle system            *
***************************************************************************/

public class BattleInterface
{
    private BattleController battleController; 

    /**************************************************************************
    *  SUBMODULE: ALTERNATE CONSTRUCTOR                                       *
    *  IMPORT: inBattleController (BattleController)                          *
    *  EXPORT: none                                                           *
    *  ASSERTION: Initalize a BattleInterface object with a valid             *
    *  BattleController                                                       *
    ***************************************************************************/	

    public BattleInterface(BattleController inBattleController)
    {
	battleController = inBattleController; 
    }

    /**************************************************************************
    *  SUBMODULE: battleMenu                                                  *
    *  IMPORT: gameStatus (String)                                            *
    *  EXPORT: gameStatus (String)                                            *
    *  ASSERTION: Runs the battle menu and returns the gameStatus whose value *
    *  depends on the outcome of the battle                                   *
    ***************************************************************************/

    public String battleMenu(String gameStatus)
    {
        boolean running = true; 
	int continueOption; 
	
        System.out.println("------------------------------------BATTLE MODE-----------------------------------");

        //Keep track of the rounds i.e. every time an enemy is created, means new round which var will be in battle controller 
        //Generate enemy for player to battle 
        battleController.makeEnemy();    
	
	if(battleController.checkEnemyValid())		
	{

            System.out.println("\n>" + "Entering ROUND " + battleController.getRoundNum() + ".... ");

            System.out.println("\n>" + battleController.getPlayerName() + " vs. " + battleController.getEnemyName()); 
	
            do
            {   
	        if(battleController.getPlayerAlive()==true)
	        {
                    playerTurn();
		
		    if(battleController.getEnemyAlive()==true)  
	            {
	                enemyTurn();
			//if player dies at this point, enemy cannot have a turn 
	            }
		    else if((battleController.getEnemyAlive()==false)) //If enemy dies 
		    {	
		        System.out.println("\n>" + battleController.getEnemyName() + " has perished. " + battleController.getEnemyGold() + " gold awarded.");    
		        battleController.destroyEnemy();
		        battleController.replenishPlayerHP();  
		        System.out.println("\n>" + "ROUND WON");

			if(battleController.getEnemyName().equals("Dragon")) //If the enemy that died is a Dragon 
			{
			    System.out.println("\n>" + "You have slain the mighty dragon and won the game! Your freedom is now earnt, and you shall see the sunrise once more.."); 
		    	    battleController.destroyEnemy(); 
		            gameStatus = "WON";
		            battleController.resetGame(gameStatus);  
			}
	                running = false;  
		    }

	        }
	        else  //If the player dies 
	        {
		    System.out.println("\n>" + battleController.getPlayerName() + " has perished in the tides of battle. All hope is lost."); 
		    System.out.println("\n>" + "ROUND LOST");
		    gameStatus = "LOST";

		    continueOption = UserInputUtil.userInput("\nYou lost the game. Do you wish to 1) Restart or 2) Exit the game??",1,2); 

		    if(continueOption == 2)			   		    
		    {
			System.out.println("Exiting game....."); 
		        System.exit(0);    
		    }
		
		    battleController.resetGame(gameStatus);  		 
		    running = false; 
	        }
            }
            while(running); 
	}
	
	return gameStatus; 
    }

    /**************************************************************************
    *  SUBMODULE: enemyTurn                                                   *
    *  IMPORT: none                                                           *
    *  EXPORT: none                                                           *
    *  ASSERTION: An enemy peforms their turn in the battle, and a transcript *
    *  of their action is output to the screen                                *
    ***************************************************************************/

    public void enemyTurn()
    {	
	System.out.println("\nENEMY TURN");
	System.out.println("\n>" + battleController.getEnemyName() + " HP: " + battleController.getEnemyHP() + "/" + battleController.getEnemyMaxHP());  
   	System.out.println(battleController.performTurn(""));       	
    }

    /**************************************************************************
    *  SUBMODULE: playerTurn                                                  *
    *  IMPORT: none                                                           *
    *  EXPORT: none                                                           *
    *  ASSERTION: The player peforms their turn in the battle, and a          *
    *  transcript of their action is output to the screen                     *
    ***************************************************************************/

    public void playerTurn()
    {
        int selectedOption, subMenuOption, potionSelection; 
	boolean menuRunning = true, subMenuRunning = true;

        System.out.println("\n>" + battleController.getPlayerName() + " HP: " + battleController.getPlayerHP() + "/" + battleController.getPlayerMaxHP()); 

        do
        {
            System.out.println("\nYOUR TURN");
            System.out.println(" 1) Attack! "); 
            System.out.println(" 2) Use Potion ");
            System.out.println(" 3) Yield, coward! ");
	     
            selectedOption = UserInputUtil.userInput("Choose an action!",1,3); 

            switch(selectedOption)
            {
                case 1:  //Player chooses to attack 
		    System.out.println(battleController.performTurn("W")); //Player uses weapon 
	            System.out.println("\n>" + battleController.getEnemyName() + " HP: " + battleController.getEnemyHP() + "/" + battleController.getEnemyMaxHP()); 
		    menuRunning = false; 
                    break;
	        case 2:  //Player chooses to use a potion 	    
	            do
		    {
		        System.out.println(" 1) Healing Potions");        		
	                System.out.println(" 2) Damaging Potions"); 
		        System.out.println(" 3) Return"); 
		
	                subMenuOption = UserInputUtil.userInput("Choose which potion type!",1,3); 

		        switch(subMenuOption) 
	                {
		            case 1:  //Player chooses healing potion 
		                if(battleController.getNumPotions("H") > 0) 
		                {
		                    System.out.println(battleController.displayPotions("H"));
		           	    potionSelection = UserInputUtil.userInput("Select a potion",1,battleController.getNumPotions("H"));
				    potionSelection = battleController.getRealPotionIndex(potionSelection, "H"); //Gets proper position of potion in inventory
				    System.out.println(battleController.performTurn("P", potionSelection)); //Player uses healing potion 
				    subMenuRunning = false;
				    menuRunning = false; 
		                }
		                else
		                { 
		                    System.out.println("\n>" + "You have no healing potions!"); 
		                }
 		                break; 
		            case 2:  //Player chooses damage potion 
		                if(battleController.getNumPotions("D") > 0)
		                {
   		                    System.out.println(battleController.displayPotions("D"));
		                    potionSelection = UserInputUtil.userInput("Select a potion",1,battleController.getNumPotions("D"));	
				    potionSelection = battleController.getRealPotionIndex(potionSelection, "D"); //Gets proper position of potion in inventory
				    System.out.println(battleController.performTurn("P", potionSelection)); //Player uses damaging potion 
				    subMenuRunning = false; 
			   	    menuRunning = false; 		
		                }
		                else
		                {
		 	            System.out.println("\n>" + "You have no damaging potions!");
  		                }
		                break;
		            case 0:
		                subMenuRunning = false;
			        break; 
	   	            default:
			        subMenuRunning = false; 
		                break;  
 	               }
		   }
		   while(subMenuRunning);
 	           break;
	        case 3:
		    System.out.println("\n>" + "Yield");
		    menuRunning = false; 
	            break;
	        default:
		    break;	
            }
        }
   	while(menuRunning); 
    }

}
