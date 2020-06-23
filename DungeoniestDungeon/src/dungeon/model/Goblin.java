package dungeon.model; 

import dungeon.model.Enemy; 
import java.lang.*; 

/**************************************************************************
*  Class: Goblin                                                          *
*  Author: Eric Wojcik                                                    *
*  StudentID: 19142124                                                    *
*  Date Created: 14/5/2020                                                *
*  Date Modified: 24/5/2020                                               *
*  Purpose: Goblin model                                                  *
***************************************************************************/

public class Goblin implements Enemy 
{
    private String speciesName;
    private int maxHP;
    private int currHP;
    private int maxAttack;
    private int minAttack;
    private int maxDefence;
    private int minDefence; 
    private int gold; 
    private boolean isAlive = true; 

    //CONSTRUCTORS

    /**************************************************************************
    *  SUBMODULE: DEFAULT CONSTRUCTOR                                         *
    *  IMPORT: none                                                           *
    *  EXPORT: none                                                           *
    *  ASSERTION: A Goblin object initialised with default values             *
    ***************************************************************************/

    public Goblin()
    {
        speciesName = "Goblin"; 
   	maxHP = 30;
 	currHP = maxHP;
	maxAttack = 8;
	minAttack = 3;
	maxDefence = 8;
	minDefence = 4;
	gold = 20; 
    }

    /**************************************************************************
    *  SUBMODULE: ALTERNATE CONSTRUCTOR                                       *
    *  IMPORT: inSpeciesName (String), inMaxHP (int), inCurrHp (int),         *
    *  inMinAttack (int), inMaxAttack (int), inMinDefence (int),              *
    *  inMaxDefence (int), inGold (int)                                       *
    *  EXPORT: none                                                           *
    *  ASSERTION: A Goblin object initialised with valid parameters           *
    ***************************************************************************/

    public Goblin(String inSpeciesName, int inMaxHP, int inCurrHP, int inMaxAttack, 
int inMinAttack, int inMaxDefence, int inMinDefence, int inGold) 
    {
	setSpeciesName(inSpeciesName);
	setMaxHP(inMaxHP); 
	setCurrHP(inCurrHP);
	setMaxAttack(inMaxAttack);
	setMinAttack(inMinAttack); 
	setMaxDefence(inMaxDefence);
	setMinDefence(inMinDefence); 
	setGold(inGold);
    }
 

    //ACCESSORS 

    /**************************************************************************
    *  SUBMODULE: getSpeciesName                                              *
    *  IMPORT: none                                                           *
    *  EXPORT: speciesName (String)                                           *
    *  ASSERTION: Return a species name value                                 *
    ***************************************************************************/

    public String getSpeciesName()
    {
	return speciesName; 
    }

    /**************************************************************************
    *  SUBMODULE: getMaxHP                                                    *
    *  IMPORT: none                                                           *
    *  EXPORT: maxHP (int)                                                    *
    *  ASSERTION: Return a maxHP value                                        *
    ***************************************************************************/

    public int getMaxHP()
    {
	return maxHP;
    }

    /**************************************************************************
    *  SUBMODULE: getCurrHP                                                   *
    *  IMPORT: none                                                           *
    *  EXPORT: currHP (int)                                                   *
    *  ASSERTION: Return a currHP value                                       *
    ***************************************************************************/

    public int getCurrHP()
    {
	return currHP; 
    }

    /**************************************************************************
    *  SUBMODULE: getMaxAttack                                                *
    *  IMPORT: none                                                           *
    *  EXPORT: maxAttack (int)                                                *
    *  ASSERTION: Return a maxAttack value                                    *
    ***************************************************************************/

    public int getMaxAttack()
    {
	return maxAttack;
    }

    /**************************************************************************
    *  SUBMODULE: getMinAttack                                                *
    *  IMPORT: none                                                           *
    *  EXPORT: minAttack (int)                                                *
    *  ASSERTION: Return a minAttack value                                    *
    ***************************************************************************/
 
    public int getMinAttack()
    {
	return minAttack; 
    }

    /**************************************************************************
    *  SUBMODULE: getMaxDefence                                               *
    *  IMPORT: none                                                           *
    *  EXPORT: maxDefence (int)                                               *
    *  ASSERTION: Return a maxDefence value                                   *
    ***************************************************************************/
 
    public int getMaxDefence()
    {
	return maxDefence; 
    }

    /**************************************************************************
    *  SUBMODULE: getMinDefence                                               *
    *  IMPORT: none                                                           *
    *  EXPORT: minDefence (int)                                               *
    *  ASSERTION: Return a minDefence value                                   *
    ***************************************************************************/

    public int getMinDefence()
    {
	return minDefence; 
    }

    /**************************************************************************
    *  SUBMODULE: getGold                                                     *
    *  IMPORT: none                                                           *
    *  EXPORT: gold (int)                                                     * 
    *  ASSERTION: Return a gold value                                         *
    ***************************************************************************/

    public int getGold()
    {
	return gold; 
    }

    /**************************************************************************
    *  SUBMODULE: getIsAlive                                                  *
    *  IMPORT: none                                                           *
    *  EXPORT: isAlive (boolean)                                              * 
    *  ASSERTION: Return value of isAlive                                     *
    ***************************************************************************/

    public boolean getIsAlive()
    {
	return isAlive; 
    }

    /**************************************************************************
    *  SUBMODULE: getDefence                                                  *
    *  IMPORT: none                                                           *
    *  EXPORT: defence (int)                                                  * 
    *  ASSERTION: Returns random defence value between MIN and MAX Defence    *
    ***************************************************************************/

    public int getDefence()
    {
        return (int) (Math.random()*((getMaxDefence() - getMinDefence())+1)+getMinDefence()); 
    }

    //MUTATORS

    /**************************************************************************
    *  SUBMODULE: setSpeciesName                                              *
    *  IMPORT: inName (String)                                                *
    *  EXPORT: none                                                           *
    *  ASSERTION: Sets a valid species name value                             *
    ***************************************************************************/
 
    public void setSpeciesName(String inName)
    {
        if(validateString(inName))
   	{
	    speciesName = inName; 
  	}
	else
	{
	    throw new IllegalArgumentException("Error: Invalid name"); 
	}
    }

    /**************************************************************************
    *  SUBMODULE: setMaxHP                                                    *
    *  IMPORT: inMaxHP (int)                                                  *
    *  EXPORT: none                                                           *
    *  ASSERTION: Sets a valid maxHP value                                    *
    ***************************************************************************/

    public void setMaxHP(int inMaxHP)
    {
	if(validateInt(inMaxHP))
  	{
	    maxHP = inMaxHP; 
 	}
	else
	{
	    throw new IllegalArgumentException("Error: Invalid max health value specified"); 	    
	}
    }

    /**************************************************************************
    *  SUBMODULE: setCurrHP                                                   *
    *  IMPORT: inCurrHP (int)                                                 *
    *  EXPORT: none                                                           *
    *  ASSERTION: Sets a valid currHP value                                   *
    ***************************************************************************/

    public void setCurrHP(int inCurrHP)
    {
	if(validateInt(inCurrHP))
  	{
	    currHP = inCurrHP; 

	    if(currHP == 0)
	    {
	        isAlive = false; 
 	    }    
 	}
	else
	{
	    throw new IllegalArgumentException("Error: Invalid current health value specified"); 	    
	}
    }

    /**************************************************************************
    *  SUBMODULE: setMaxAttack                                                *
    *  IMPORT: inMaxAttack (int)                                              *
    *  EXPORT: none                                                           *
    *  ASSERTION: Sets a valid maxAttack value                                *
    ***************************************************************************/


    public void setMaxAttack(int inMaxAttack)
    {
	if(validateInt(inMaxAttack))
  	{
	    maxAttack = inMaxAttack; 
 	}
	else
	{
	    throw new IllegalArgumentException("Error: Invalid max attack value specified"); 	    
	}
    }

    /**************************************************************************
    *  SUBMODULE: setMinAttack                                                *
    *  IMPORT: inMinAttack (int)                                              *
    *  EXPORT: none                                                           *
    *  ASSERTION: Sets a valid minAttack value                                *
    ***************************************************************************/

    public void setMinAttack(int inMinAttack)
    {
	if(validateInt(inMinAttack))
  	{
	    maxAttack = inMinAttack; 
 	}
	else
	{
	    throw new IllegalArgumentException("Error: Invalid min attack value specified"); 	    
	}
    }

    /**************************************************************************
    *  SUBMODULE: setMaxDefence                                               *
    *  IMPORT: inMaxDefence (int)                                             *
    *  EXPORT: none                                                           *
    *  ASSERTION: Sets a valid maxDefence value                               *
    ***************************************************************************/

    public void setMaxDefence(int inMaxDefence)
    {
	if(validateInt(inMaxDefence))
  	{
	    maxDefence = inMaxDefence; 
 	}
	else
	{
	    throw new IllegalArgumentException("Error: Invalid max defence value specified"); 	    
	}
    }

    /**************************************************************************
    *  SUBMODULE: setMinAttack                                                *
    *  IMPORT: inMinAttack (int)                                              *
    *  EXPORT: none                                                           *
    *  ASSERTION: Sets a valid minAttack value                                *
    ***************************************************************************/

    public void setMinDefence(int inMinDefence)
    {
	if(validateInt(inMinDefence))
  	{
	    minDefence = inMinDefence; 
 	}
	else
	{
	    throw new IllegalArgumentException("Error: Invalid min defence value specified"); 	    
   	}
    }

    /**************************************************************************
    *  SUBMODULE: setGold                                                     *
    *  IMPORT: inGold (int)                                                   *
    *  EXPORT: none                                                           *
    *  ASSERTION: Sets a valid gold value                                     *
    ***************************************************************************/

    public void setGold(int inGold)
    {
	if(validateInt(inGold))
  	{
	    gold = inGold; 
 	}
	else
	{
	    throw new IllegalArgumentException("Error: Invalid gold value specified"); 	    
   	}
    }

    /**************************************************************************
    *  SUBMODULE: doAction                                                    *
    *  IMPORT: void                                                           *
    *  EXPORT: attackDMG (int)                                                *
    *  ASSERTION: Returns attack damage/action value based on whether an      *
    *  attack or an ability is performed (and the kind of attack/ability).    *
    *  By default returns attack damage if int > 0.                           *
    ***************************************************************************/

    public int doAction()
    {
        //If returns a negative value, it means that a self-ability was performed 
        int attackDMG;
	double abilityProb, randomNum; 
 
	abilityProb = 0.5; 
	attackDMG = (int) (Math.random()*((getMaxAttack() - getMinAttack())+1)+getMinAttack()); 
	randomNum = Math.random(); 	

	//If random value 0.0-1.0 is within the 0.5 threshold 
	if(randomNum <= abilityProb)
  	{
	    System.out.println("\n>Goblin performs an ability!");
	    attackDMG += useAbility(); 
	}
	
	return attackDMG; 
    }

    /**************************************************************************
    *  SUBMODULE: useAbility                                                  *
    *  IMPORT: void                                                           *
    *  EXPORT: abilityValue (int)                                             *
    *  ASSERTION: Returns an ability value based on the ability performed.    *
    *  Reflects ability 50% chance deals 3 extra damage                       *
    ***************************************************************************/

    public int useAbility()
    {
        System.out.println("\n>Extra Damage!");
	return 3; 
    }


    //PRIVATE METHOD

    /**************************************************************************
    *  SUBMODULE: validateString                                              *
    *  IMPORT: inString (String)                                              *
    *  EXPORT: validString (boolean)                                          *
    *  ASSERTION: Check if incoming string is valid                           *
    ***************************************************************************/

    private boolean validateString(String inString)
    {
	return (!("".equalsIgnoreCase(inString))); 
    }

    /**************************************************************************
    *  SUBMODULE: validateInt                                                 *
    *  IMPORT: inInt (int)                                                    *
    *  EXPORT: validInt (boolean)                                             *
    *  ASSERTION: Check if incoming int is valid                              *
    ***************************************************************************/

    private boolean validateInt(int inInt)
    {
	return (inInt >= 0);   
    }
}
