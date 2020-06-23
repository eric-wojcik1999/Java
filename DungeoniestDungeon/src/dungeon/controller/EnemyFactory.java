package dungeon.controller; 

import dungeon.model.Enemy; 
import dungeon.model.Slime; 
import dungeon.model.Goblin; 
import dungeon.model.Ogre; 
import dungeon.model.Dragon; 


/**************************************************************************
*  Class: EnemyFactory                                                    *
*  Author: Eric Wojcik                                                    *
*  StudentID: 19142124                                                    *
*  Date Created: 15/5/2020                                                *
*  Date Modified: 24/5/2020                                               *
*  Purpose: Decides which enemy sub class to use based on a given string  *
***************************************************************************/

public class EnemyFactory
{
    //For testability 
    public static Enemy testEnemy = null;

   //For testability 

    /**************************************************************************
    *  SUBMODULE: setTestEnemy                                                *
    *  IMPORT: t1 (Enemy)                                                     *
    *  EXPORT: none                                                           *
    *  ASSERTION: Returns a test enemy for mocking purposes                   *
    ***************************************************************************/

    public void setTestEnemy(Enemy t1)
    {
	testEnemy = t1; 
    }

    /**************************************************************************
    *  SUBMODULE: makeEnemy                                                   *
    *  IMPORT: enemyType (String)                                             *
    *  EXPORT: e (Enemy)                                                      *
    *  ASSERTION: Returns an Enemy based on a provided string                 *
    ***************************************************************************/

    public Enemy makeEnemy(String enemyType)
    {
	Enemy e = null; 

        //If testEnemy == null, it means the factory isn't being mocked 
   	if(testEnemy == null)
   	{
	    if(enemyType.equals("Slime"))
	    {
	        e = genSlime();
	    }
	    else if(enemyType.equals("Goblin"))
	    {
		e = genGoblin(); 
 	    }
	    else if(enemyType.equals("Ogre"))
	    {
 	        e = genOgre();
	    }
	    else if(enemyType.equals("Dragon"))
  	    {
	        e = genDragon(); 
 	    }
	}
	else
	{
	    return testEnemy; 
	}
	return e; 
    }

    /**************************************************************************
    *  SUBMODULE: genSlime                                                    *
    *  IMPORT: none                                                           *
    *  EXPORT: slime (Enemy)                                                  *
    *  ASSERTION: Returns an Enemy object of type Slime                       *
    ***************************************************************************/

    private Enemy genSlime()
    {
	return new Slime(); 
    }

    /**************************************************************************
    *  SUBMODULE: genGoblin                                                   *
    *  IMPORT: none                                                           *
    *  EXPORT: goblin (Enemy)                                                 *
    *  ASSERTION: Returns an Enemy object of type Goblin                      *
    ***************************************************************************/

    private Enemy genGoblin()
    {
	return new Goblin(); 
    }

    /**************************************************************************
    *  SUBMODULE: genOgre                                                     *
    *  IMPORT: none                                                           *
    *  EXPORT: ogre (Enemy)                                                   *
    *  ASSERTION: Returns an Enemy object of type Ogre                        *
    ***************************************************************************/

    private Enemy genOgre()
    {
	return new Ogre(); 
    }

    /**************************************************************************
    *  SUBMODULE: genDragon                                                   *
    *  IMPORT: none                                                           *
    *  EXPORT: dragon (Enemy)                                                 *
    *  ASSERTION: Returns an Enemy object of type Dragon                      *
    ***************************************************************************/

    private Enemy genDragon()
    {
	return new Dragon(); 
    }
}
