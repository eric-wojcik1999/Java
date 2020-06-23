package dungeon.controller; 

import dungeon.controller.EnemyFactory; 
import dungeon.controller.TurnTemplate;
import dungeon.controller.TurnFactory; 
import dungeon.model.*; 
import java.util.*; 
import java.lang.*; 

/**************************************************************************
*  Class: Battle Controller                                               *
*  Author: Eric Wojcik                                                    *
*  StudentID: 19142124                                                    *
*  Date Created: 15/5/2020                                                *
*  Date Modified: 27/5/2020                                               *
*  Purpose: Serves as a thin layer between the BattleInterface and the    *
*  model classes it uses.                                                 *
***************************************************************************/


public class BattleController
{
    private Player player; 
    private EnemyFactory enemyFactory;
    private TurnFactory turnFactory; 
    private Enemy enemy; 
    private Map<String, Double> enemyProbabilities; 
    private int roundNum = 0; 

    //CONSTRUCTORS 

    /**************************************************************************
    *  SUBMODULE: ALTERNATE CONSTRUCTOR                                       *
    *  IMPORT: inPlayer (Player), inEnemyFactory(EnemyFactory)                *
    *  EXPORT: none                                                           *
    *  ASSERTION: A BattleController initialised with a valid player, and a   *
    *  valid enemy factory.                                                   *
    ***************************************************************************/

    public BattleController(Player inPlayer, EnemyFactory inEnemyFactory, TurnFactory inTurnFactory)
    {
	player = inPlayer; 
	enemyFactory = inEnemyFactory;
        turnFactory = inTurnFactory; 
        initialiseEnemyProbabilities();  
    }

    //ACCESSORS 

    /**************************************************************************
    *  SUBMODULE: getPlayerName                                               *
    *  IMPORT: none                                                           *
    *  EXPORT: name (String)                                                  *
    *  ASSERTION: Returns a valid player name to the calling interface        *
    ***************************************************************************/

    public String getPlayerName()
    {
	return player.getName();
    }

    /**************************************************************************
    *  SUBMODULE: getPlayerHP                                                 *
    *  IMPORT: none                                                           *
    *  EXPORT: currHP (int)                                                   *
    *  ASSERTION: Returns a valid player health value to the calling          *
    *  interface                                                              *
    ***************************************************************************/

    public int getPlayerHP()
    {
        return player.getCurrHP();
    }

    /**************************************************************************
    *  SUBMODULE: getPlayerMaxHP                                              *
    *  IMPORT: none                                                           *
    *  EXPORT: maxHP (int)                                                    *
    *  ASSERTION: Returns a valid max player health value to the calling      *
    *  interface                                                              *
    ***************************************************************************/

    public int getPlayerMaxHP()
    {
        return player.getMaxHP();
    }

    /**************************************************************************
    *  SUBMODULE: getPlayerAlive                                              *
    *  IMPORT: none                                                           *
    *  EXPORT: isAlive (boolean)                                              *
    *  ASSERTION: Returns a boolean specifying whether the player is alive    *
    ***************************************************************************/

    public boolean getPlayerAlive()
    { 
        return player.getIsAlive();
    }

    /**************************************************************************
    *  SUBMODULE: getRoundNum                                                 *
    *  IMPORT: none                                                           *
    *  EXPORT: roundNum (int)                                                 *
    *  ASSERTION: Returns a valid round number to the calling interface       *
    ***************************************************************************/

    public int getRoundNum()
    {
	return roundNum;
    }

    /**************************************************************************
    *  SUBMODULE: getEnemyName                                               *
    *  IMPORT: none                                                           *
    *  EXPORT: name (String)                                                  *
    *  ASSERTION: Returns a valid enemy name to the calling interface        *
    ***************************************************************************/

    public String getEnemyName() 
    {
	return enemy.getSpeciesName(); 
    }

    /**************************************************************************
    *  SUBMODULE: getEnemyHP                                                  *
    *  IMPORT: none                                                           *
    *  EXPORT: currHP (int)                                                   *
    *  ASSERTION: Returns a valid enemy health value to the calling           *
    *  interface                                                              *
    ***************************************************************************/

    public int getEnemyHP()
    {
	return enemy.getCurrHP(); 
    }

    /**************************************************************************
    *  SUBMODULE: getEnemyMaxHP                                               *
    *  IMPORT: none                                                           *
    *  EXPORT: maxHP (int)                                                    *
    *  ASSERTION: Returns a valid max enemy health value to the calling       *
    *  interface                                                              *
    ***************************************************************************/

    public int getEnemyMaxHP()
    {
	return enemy.getMaxHP(); 
    }

    /**************************************************************************
    *  SUBMODULE: getEnemyGold                                                *
    *  IMPORT: none                                                           *
    *  EXPORT: gold (int)                                                     *
    *  ASSERTION: Returns a valid enemy gold (worth) value to the calling     *
    *  interface                                                              *
    ***************************************************************************/

    public int getEnemyGold()
    {
	return enemy.getGold(); 
    }

    /**************************************************************************
    *  SUBMODULE: getEnemyAlive                                               *
    *  IMPORT: none                                                           *
    *  EXPORT: isAlive (boolean)                                              *
    *  ASSERTION: Returns a boolean specifying whether an enemy is alive      *
    ***************************************************************************/

    public boolean getEnemyAlive() 
    {
	return enemy.getIsAlive(); 
    }

    /**************************************************************************
    *  SUBMODULE: getNumPotions                                               *
    *  IMPORT: none                                                           *
    *  EXPORT: size (int)                                                     *
    *  ASSERTION:  Retrieve number of potions in player's inventory           *
    ***************************************************************************/

    public int getNumPotions(String inPotionType)
    {
	int size = 0;
	String potionType = ""; 
        ArrayList<Item> potions = player.getPotions(); 
	
	for(Item p: potions)
	{
	    Potion temp = (Potion) p;	
	    potionType = temp.getPotionType();
	    if(potionType.equals(inPotionType))
	    {
	        size++; 
	    }
	}
	return size; 
    }

    /**************************************************************************
    *  SUBMODULE: getRealPotionIndex                                          *
    *  IMPORT: selectedPotion (int), inPotionType(String)                     *
    *  EXPORT: realIndex (int)                                                *
    *  ASSERTION: Returns the real position of a potion within a list of      *
    *  potions                                                                *
    ***************************************************************************/
    
    public int getRealPotionIndex(int selectedPotion, String inPotionType)
    {
        boolean potionFound = false;
        int realIndex = 0, currIndex = 0;
        ArrayList<Item> potions = player.getPotions(); 
        for(Item p : potions)
        {
            Potion temp = (Potion)p;		

            if(temp.getPotionType().equals(inPotionType))
            {
                currIndex ++; //First instance of that type of potion 
                if(selectedPotion == currIndex && potionFound == false)
                {
                    realIndex = potions.indexOf(temp);
                    potionFound = true; 
                }
            }
        }
        return realIndex; 
    }

    /**************************************************************************
    *  SUBMODULE: displayPotions                                              *
    *  IMPORT: inPotionType(String)                                           *
    *  EXPORT: potionString (String)                                          *
    *  ASSERTION: Returns a large string containing a list of all potions     *
    *  the player currently has in their possession                           *
    ***************************************************************************/

    public String displayPotions(String inPotionType)
    {
        ArrayList<Item> potions = player.getPotions(); 
	String potionString = "";  
	String potionType = ""; 
	int index = 0; 	

	for(Item p : potions)
	{
	    Potion temp = (Potion)p;		
	    potionType = temp.getPotionType();

	    if(potionType.equals(inPotionType))
	    {
		index++; 
	        potionString += " " + index + ") " + p.getName() + " EFFECT: " + p.getMinEffect() + "-" + p.getMaxEffect() + "\n";   
	    }
	}
	return potionString; 
    }

    /**************************************************************************
    *  SUBMODULE: obtainPlayerStats                                           *
    *  IMPORT: none                                                           *
    *  EXPORT: playerStats(String)                                            *
    *  ASSERTION:  Returns a string transcript of the player's current        *
    *  statistics                                                             *
    ***************************************************************************/

    public String obtainPlayerStats()
    {
	return player.toStringAbridged(); 
    }

    //MUTATORS 

    /**************************************************************************
    *  SUBMODULE: replenishPlayerHP                                           *
    *  IMPORT: none                                                           *
    *  EXPORT: none                                                           *
    *  ASSERTION:  Recharges player health by 1.5 times their current HP      *
    ***************************************************************************/

    public void replenishPlayerHP()
    {
        int newHP = (int) (player.getCurrHP() * 1.5); 
	player.setCurrHP(Math.min(player.getMaxHP(), newHP)); 
    }

    //OTHER

    /**************************************************************************
    *  SUBMODULE: performTurn                                                  *
    *  IMPORT: itemUsed (String)                                              *
    *  EXPORT: actionString (String)                                          *
    *  ASSERTION: Player or Enemy peforms their turn and a transcript of      *
    *  their action is returned                                               *
    ***************************************************************************/

    //Version that doesn’t need selectedItem (just tItem used)
    public String performTurn(String itemUsed)
    {
        TurnTemplate turn = turnFactory.makeTurn(itemUsed); //Obtain the type of turn
        return turn.performAction(player, enemy, itemUsed, 0);
    }

    /**************************************************************************
    *  SUBMODULE: performTurn                                                  *
    *  IMPORT: itemUsed (String), itemSelected (int)                          *
    *  EXPORT: actionString (String)                                          *
    *  ASSERTION: Player or Enemy peforms their turn and a transcript of      *
    *  their action is returned                                               *
    ***************************************************************************/

    //Version that needs both itemSelected and itemUsed 
    public String performTurn(String itemUsed, int itemSelected)
    {
        TurnTemplate turn = turnFactory.makeTurn(itemUsed);  //Obtain the type of turn
        return turn.performAction(player, enemy, itemUsed, itemSelected);
    }

    
    /**************************************************************************
    *  SUBMODULE: makeEnemy                                                   *
    *  IMPORT: none                                                           *
    *  EXPORT: none                                                           *
    *  ASSERTION: Creates an enemy via the EnemyFactory based on the          *
    *  randomly selected enemy type                                           *
    ***************************************************************************/

    public void makeEnemy()
    {
	String chosenEnemyType; 
        
	chosenEnemyType = generateProbability(); 

        enemy = enemyFactory.makeEnemy(chosenEnemyType);
	
	//As one enemy is generated per round, if an enemy is successfully generated, the round num is incremented
	roundNum++; 	
    }

    /**************************************************************************
    *  SUBMODULE: destroyEnemy                                                *
    *  IMPORT: none                                                           *
    *  EXPORT: none                                                           *
    *  ASSERTION: Rewards the player gold upon the death of an enemy          *
    ***************************************************************************/

    public void destroyEnemy()
    {
	player.setGold(player.getGold() + enemy.getGold()); 
    }

    /**************************************************************************
    *  SUBMODULE: checkEnemyValid                                             *
    *  IMPORT: none                                                           *
    *  EXPORT: isValid (boolean)                                              *
    *  ASSERTION: Returns true if an enemy is valid                           *
    ***************************************************************************/

    public boolean checkEnemyValid()
    {	
        return (enemy != null); 
    }

    /**************************************************************************
    *  SUBMODULE: intialiseEnemyProbabilities                                 *
    *  IMPORT: none                                                           *
    *  EXPORT: none                                                           *
    *  ASSERTION: Populates the enemyProbabilities map with appropriate       *
    *  enemy probability values                                               *
    ***************************************************************************/

    private void initialiseEnemyProbabilities()
    {
	enemyProbabilities = new LinkedHashMap<String, Double>(); //Maintains order of insertion
	enemyProbabilities.put("Slime",(Double)0.5);
	enemyProbabilities.put("Goblin",(Double)0.3); 
	enemyProbabilities.put("Ogre",(Double)0.2);
	enemyProbabilities.put("Dragon",(Double)0.0);  
    }

   
    /**************************************************************************
    *  SUBMODULE: resetGame                                                   *
    *  IMPORT: gameStatus (String)                                            *
    *  EXPORT: none                                                           *
    *  ASSERTION: Resets the game to default status if the player has         *
    *  lost the game                                                          *
    ***************************************************************************/

    public void resetGame(String gameStatus)
    {
        if(gameStatus.equals("LOST"))
	{
	    player.resetPlayerState(); 
	    enemyProbabilities.clear(); //Remove all entries
	    initialiseEnemyProbabilities(); //Re-enter default values 	    
	}
        roundNum = 0; 
    }

    /**************************************************************************
    *  SUBMODULE: generateProbabilities                                       *
    *  IMPORT: none                                                           *
    *  EXPORT: chosenEnemyType (String)                                       *
    *  ASSERTION: Produces an enemyType depending on the probability of the   *
    *  existing enemy types to be spawned                                     *
    ***************************************************************************/

    private String generateProbability()
    {
	String chosenEnemyType = ""; 
	double randomNum;
	double currProbRange = 0.0; 	
	double prob = 0.0; 	
	int index = 0; 

 	randomNum = Math.random(); //Generates num between 0.0-1.0

	if(roundNum > 1)
	{
	    decreaseProbabilities(); 
	}

	//Loops through each key of enemyProb map, gets the value (a probaility) relating to that key 
	//which is just the prob of that enemy. Then it is added to a cumulative probability range variable. 
	//This effectively 'iterates' through each of the probability ranges. The if statement then tests if
	//the random value (0.0-1.0) is <= that range. If it is, the current enemy is selected!  

	for(String key : enemyProbabilities.keySet())
 	{
	    prob = enemyProbabilities.get(key); 
	    currProbRange += prob; 
	    index++; 	

	    //i.e. goes to 0.5 range first, so anything less/equal to 0.5 is a slime 
	    if(currProbRange >= randomNum && randomNum != -1.0)
	    {
	        chosenEnemyType = key;
		randomNum = -1.0; //Arbitrary value used to make sure chosenEnemyType is not further manipulated         
   	    }	  
	}

  	return chosenEnemyType; 
    }

    /**************************************************************************
    *  SUBMODULE: decreaseProbabilities                                       *
    *  IMPORT: none                                                           *
    *  EXPORT: chosenEnemyType (String)                                       *
    *  ASSERTION: Decreases mob enemy probabilities on a per round basis      *
    *  while ncrease the spawn chance of a dragon enemy type                  *
    ***************************************************************************/

    private void decreaseProbabilities()
    {
	double newMobProb;

	newMobProb = 0.0; 

	for(String key : enemyProbabilities.keySet())
	{
	    if(!key.equals("Dragon"))
	    {
	 	newMobProb = enemyProbabilities.get(key) - 0.05; 
	
		if(newMobProb < 0.0)
		{
		    newMobProb = 0.0;
		}       
	        enemyProbabilities.put(key,newMobProb); 

		//System.out.println("ENEMY: " + key + " has prob now: " + enemyProbabilities.get(key)); 
  	    }
	    else
	    {
	        newMobProb = enemyProbabilities.get(key) + 0.15; 
		
		if(newMobProb > 1.0)
		{
		    newMobProb = 1.0;
		}
		enemyProbabilities.put(key,newMobProb); 
		
		//System.out.println("DRAGON now has prob" + enemyProbabilities.get(key)); 	
	    }
	}	
    }	

}
