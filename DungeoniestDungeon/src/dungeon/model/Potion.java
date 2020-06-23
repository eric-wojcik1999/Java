package dungeon.model; 

import dungeon.model.Item; 

import java.lang.*; 

/**************************************************************************
*  Class: Potion                                                          *
*  Author: Eric Wojcik                                                    *
*  StudentID: 19142124                                                    *
*  Date Created: 14/5/2020                                                *
*  Date Modified: 27/5/2020                                               *
*  Purpose: Potion model                                                  *
***************************************************************************/

public class Potion implements Item
{
    //CLASSFIELDS 

    private String name; 
    private int cost;
    private int minEffect;
    private int maxEffect; 
    private String potionType;  
    private static final String ITEM_TYPE = "P"; 

    //CONSTRUCTORS

    /**************************************************************************
    *  SUBMODULE: DEFAULT CONSTRUCTOR                                         *
    *  IMPORT: none                                                           *
    *  EXPORT: none                                                           *
    *  ASSERTION: A Potion object initialised with default values             *
    ***************************************************************************/

    //Default constructor 
    public Potion() 
    {
	name = "Greater Ember Flask"; 
  	cost = 701;
	minEffect = 9;
 	maxEffect = 71; 
   	potionType = "H"; 
     }

    /**************************************************************************
    *  SUBMODULE: ALTERNATE CONSTRUCTOR                                       *
    *  IMPORT: inName (String), inCost (String), inMinEffect (int),           *
    *  inMaxEffect (int), inPotionType (String)                               *
    *  EXPORT: none                                                           *
    *  ASSERTION: A potion object initialised with valid parameters           *
    ***************************************************************************/

    //Alternate Constructor 
    public Potion(String inName, int inCost, int inMinEffect, int inMaxEffect, String inPotionType)
    {
	setName(inName); 
  	setCost(inCost);
 	setMinEffect(inMinEffect);
	setMaxEffect(inMaxEffect);
	setPotionType(inPotionType); 
    }

    /**************************************************************************
    *  SUBMODULE: use                                                         *
    *  IMPORT: void                                                           *
    *  EXPORT: effect (int)                                                   *
    *  ASSERTION: Returns an effect value which is a random value between     *
    *  minEffect and maxEffect. If D, this value is unaffected. If H, it      *
    *  returns a negative version of the value, used to indicate that a heal  *
    *  has occurred                                                           *
    ***************************************************************************/

    public int use()
    {
        int effect = (int) (Math.random()*((getMaxEffect() - getMinEffect())+1)+getMinEffect()); 

        if(potionType.equals("H"))
        {
            effect = effect * -1;
            
        }
        
	    return effect; 
    }

    //ACCESSORS 

    /**************************************************************************
    *  SUBMODULE: getName                                                     *
    *  IMPORT: none                                                           *
    *  EXPORT: name (String)                                                  *
    *  ASSERTION: Return a name value                                         *
    ***************************************************************************/
    
    public String getName()
    {
	return name; 
    }

    /**************************************************************************
    *  SUBMODULE: getCost                                                     *
    *  IMPORT: none                                                           *
    *  EXPORT: cost (int)                                                     *
    *  ASSERTION: Return a cost value                                         *
    ***************************************************************************/

    public int getCost()
    {
	return cost; 
    }

    /**************************************************************************
    *  SUBMODULE: getMinEffect                                                *
    *  IMPORT: none                                                           *
    *  EXPORT: minEffect (int)                                                *
    *  ASSERTION: Return a minEffect value                                    *
    ***************************************************************************/

    public int getMinEffect()
    {
	return minEffect; 
    }

    /**************************************************************************
    *  SUBMODULE: getMaxEffect                                                *
    *  IMPORT: none                                                           *
    *  EXPORT: maxEffect (int)                                                *
    *  ASSERTION: Return a maxEffect value                                    *
    ***************************************************************************/

    public int getMaxEffect()
    {
	return maxEffect;
    }

    /**************************************************************************
    *  SUBMODULE: getPotionType                                               *
    *  IMPORT: none                                                           *
    *  EXPORT: potionType (String)                                            *
    *  ASSERTION: Return a potion type value                                  *
    ***************************************************************************/

    public String getPotionType() 
    {
	return potionType; 
    }

    /**************************************************************************
    *  SUBMODULE: getItemType                                                 *
    *  IMPORT: none                                                           *
    *  EXPORT: ITEM_TYPE (String)                                             *
    *  ASSERTION: Return the ITEM_TYPE of an item                             *
    ***************************************************************************/

    public String getItemType()
    {
	return ITEM_TYPE; 
    }

    //MUTATORS

    /**************************************************************************
    *  SUBMODULE: setName                                                     *
    *  IMPORT: inName (String)                                                *
    *  EXPORT: none                                                           *
    *  ASSERTION: Sets name to a valid name value                             *
    ***************************************************************************/

    public void setName(String inName) 
    {
        if ((validateString(inName)))
  	{
	    name = inName; 
	}
	else
	{
	    throw new IllegalArgumentException("Error: Name is null"); 
	}
    }

    /**************************************************************************
    *  SUBMODULE: setCost                                                     *
    *  IMPORT: inCost (int)                                                   *
    *  EXPORT: none                                                           *
    *  ASSERTION: Sets cost to a valid cost value                             *
    ***************************************************************************/

    public void setCost(int inCost)
    {
	if(validateInt(inCost))
        {
	    cost = inCost; 
        }
    }

    /**************************************************************************
    *  SUBMODULE: setMinEffect                                                *
    *  IMPORT: inMinEffect  (int)                                             *
    *  EXPORT: none                                                           *
    *  ASSERTION: Sets min effect to a valid min effect value                 *
    ***************************************************************************/

    public void setMinEffect(int inMinEffect)
    {
       	if(validateInt(inMinEffect))
        {
 	    minEffect = inMinEffect; 
        } 
    }

    /**************************************************************************
    *  SUBMODULE: setMaxEffect                                                *
    *  IMPORT: inMaxEffect  (int)                                             *
    *  EXPORT: none                                                           *
    *  ASSERTION: Sets max effect to a valid max effect value                 *
    ***************************************************************************/

    public void setMaxEffect(int inMaxEffect)
    {
	if(validateInt(inMaxEffect))
	{
	    maxEffect = inMaxEffect; 
	}
    }

    /**************************************************************************
    *  SUBMODULE: setPotionType                                               *
    *  IMPORT: inPotionType  (String)                                         *
    *  EXPORT: none                                                           *
    *  ASSERTION: Sets potion type to a valid potion type value               *
    ***************************************************************************/

    public void setPotionType(String inPotionType)
    {
	if ((checkPotionType(inPotionType)))
        {
	    potionType = inPotionType;
        }
        else
        {
	    throw new IllegalArgumentException("Error: Not a valid potion type"); 
        }
    }

    /**************************************************************************
    *  SUBMODULE: toString                                                    *
    *  IMPORT: none                                                           *
    *  EXPORT: toString (String)                                              *
    *  ASSERTION: Returns transcript of object state in string form           *
    ***************************************************************************/

    @Override
    public String toString()
    {
	String toString = "POTION: " + getName() + " || EFFECT: " + minEffect + "-" + maxEffect + " || TYPE: " + potionType +  " || COST: " + getCost();
	return toString; 
    }

    /**************************************************************************
    *  SUBMODULE: equal                                                       *
    *  IMPORT: inObj (Object)                                                 *
    *  EXPORT: isEquals (boolean)                                             *
    *  ASSERTION: Check if two objects are the same                           *
    ***************************************************************************/

    @Override
    public boolean equal(Object inObj)
    {
        boolean isEquals = false; 
	if (inObj instanceof Potion)
	{
	    Potion inPotion = (Potion)inObj; 
	    isEquals = (name.equals(inPotion.getName())) && 
		       (cost == (inPotion.getCost())) &&
 		       (minEffect == (inPotion.getMinEffect())) &&
		       (maxEffect == (inPotion.getMaxEffect())) &&
 		       (potionType.equals(inPotion.getPotionType()));
	}
	return isEquals; 
    }

    //PRIVATE VALIDATORS 
 
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

    /**************************************************************************
    *  SUBMODULE: checkPotionType                                             *
    *  IMPORT: inPotion (String)                                              *
    *  EXPORT: validPotionType (boolean)                                      *
    *  ASSERTION: Check if incoming potion type is valid                      *
    ***************************************************************************/

    //Checks if one of available types
    private boolean checkPotionType(String inPotion)
    {
	return ((inPotion.equals("D"))||(inPotion.equals("d"))||(inPotion.equals("H"))||(inPotion.equals("h"))); 
    }
}
