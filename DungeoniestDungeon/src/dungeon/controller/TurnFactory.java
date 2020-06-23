package dungeon.controller; 

import dungeon.controller.TurnTemplate; 
import dungeon.controller.EnemyTurn; 
import dungeon.controller.PlayerTurn;  

/**************************************************************************
*  Class: TurnFactory                                                     *
*  Author: Eric Wojcik                                                    *
*  StudentID: 19142124                                                    *
*  Date Created: 27/5/2020                                                *
*  Date Modified: 27/5/2020                                               *
*  Purpose: Decides which Turn object to return                           *
***************************************************************************/

public class TurnFactory
{
    //For testability 
    private static TurnTemplate testTurn = null;


    //For testability
   
    /**************************************************************************
    *  SUBMODULE: setTestTurn                                                 *
    *  IMPORT: t1 (TurnTemplate)                                              *
    *  EXPORT: none                                                           *
    *  ASSERTION: Returns a test turn template for mocking purposes           *
    ***************************************************************************/
  
    public void setTestTurn(TurnTemplate t1)
    {
        testTurn = t1; 
    }

    /**************************************************************************
    *  SUBMODULE: makeTurn                                                    *
    *  IMPORT: turnType (String)                                              *
    *  EXPORT: t (TurnTemplate)                                               *
    *  ASSERTION: Returns a turn object based on a provided set of attributes *
    ***************************************************************************/

    public TurnTemplate makeTurn(String turnType)
    {
	
	TurnTemplate t = null; 

	//If testItem == null, means this factory isn't being mocked 
	if(testTurn == null)
	{
	    if(turnType.equals("W") || turnType.equals("P")) // W means weapon, P means potion, both are used by player 
	    {
	        t = genPlayerTurn(); 	    
  	    }
	    else if(turnType.equals("")) // Empty string means the entity taking their turn doesn't use an item, which is an enemy 
	    {
		t = genEnemyTurn(); 
    	    }
	}
	else
	{
	    return testTurn; 
	}
	
	return t; 
    }

   /***************************************************************************
    *  SUBMODULE: genPlayerTurn                                              *
    *  IMPORT: none                                                           *
    *  EXPORT: turn (TurnTemplate)                                            *
    *  ASSERTION: Return a player turn to the user                            *
    ***************************************************************************/
 
    private TurnTemplate genPlayerTurn()
    {
	return new PlayerTurn(); 
    }


    /**************************************************************************
    *  SUBMODULE: genEnemyTurn                                                *
    *  IMPORT: none                                                           *
    *  EXPORT: turn (TurnTemplate)                                            *
    *  ASSERTION: Return an enemy turn to the user                            *
    ***************************************************************************/
 
    private TurnTemplate genEnemyTurn()
    {
	return new EnemyTurn(); 
    }
}
