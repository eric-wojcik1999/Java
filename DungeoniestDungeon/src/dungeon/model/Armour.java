package dungeon.model; 

import dungeon.model.Item; 
import java.lang.*; 

/**************************************************************************
*  Class: Armor                                                           *
*  Author: Eric Wojcik                                                    *
*  StudentID: 19142124                                                    *
*  Date Created: 14/5/2020                                                *
*  Date Modified: 24/5/2020                                               *
*  Purpose: Armour model                                                  *
***************************************************************************/

public class Armour implements Item
{
    //CLASSFIELDS 

    private String name; 
    private int cost;
    private int minEffect;
    private int maxEffect; 
    private String material; 
    private static final String ITEM_TYPE = "A"; 

    /**************************************************************************
    *  SUBMODULE: DEFAULT CONSTRUCTOR                                         *
    *  IMPORT: none                                                           *
    *  EXPORT: none                                                           *
    *  ASSERTION: An Armour object is initialised with default values         *
    ***************************************************************************/

    //Default constructor 
    public Armour() 
    {
	name = "Devok’s Plate"; 
  	cost = 4123;
	minEffect = 14;
 	maxEffect = 16; 
   	material = "Leather";
     }

    /**************************************************************************
    *  SUBMODULE: ALTERNATE CONSTRUCTOR                                       *
    *  IMPORT: inName (String), inCost (String), inMinEffect (int),           *
    *  inMaxEffect (int), inMaterial (String)                                 *
    *  EXPORT: none                                                           *
    *  ASSERTION: An Armour object initialised with valid parameters          *
    ***************************************************************************/

    public Armour(String inName, int inCost, int inMinEffect, int inMaxEffect, String inMaterial)
    {
	setName(inName);
	setCost(inCost); 
	setMinEffect(inMinEffect);
	setMaxEffect(inMaxEffect);
	setMaterial(inMaterial);
    }

    /**************************************************************************
    *  SUBMODULE: use                                                         *
    *  IMPORT: void                                                           *
    *  EXPORT: effect (int)                                                   *
    *  ASSERTION: Returns an effect value which is a random value between     *
    *  minEffect and maxEffect                                                *
    ***************************************************************************/

    public int use()
    {
	return (int) (Math.random()*((getMaxEffect() - getMinEffect())+1)+getMinEffect());  
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
    *  SUBMODULE: getMaterial                                                 *
    *  IMPORT: none                                                           *
    *  EXPORT: material (String)                                              *
    *  ASSERTION: Return a material value                                     *
    ***************************************************************************/

    public String getMaterial()
    {
	return material; 
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
    *  SUBMODULE: setMaterial                                                 *
    *  IMPORT: inMaterial  (String)                                           *
    *  EXPORT: none                                                           *
    *  ASSERTION: Sets material to a valid value                              *
    ***************************************************************************/

    public void setMaterial(String inMaterial)
    {
        if ((validateString(inMaterial)))
  	{
	    material = inMaterial; 
	}
	else
	{
	    throw new IllegalArgumentException("Error: Material is null"); 
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
	String toString = "ARMOUR: " + getName() + " || DEF: " + minEffect + "-" + maxEffect+  " || COST: " + getCost() + " || MATERIAL: " + material;
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
	if (inObj instanceof Armour)
	{
	    Armour inArmour = (Armour)inObj; 
	    isEquals = (name.equals(inArmour.getName())) && 
		       (cost == (inArmour.getCost())) &&
 		       (minEffect == (inArmour.getMinEffect())) &&
		       (maxEffect == (inArmour.getMaxEffect())) &&
 		       (material.equals(inArmour.getMaterial()));
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

    //Add better comments later - Checks if String is not null  
    private boolean validateString(String inString)
    {
	return(!("".equalsIgnoreCase(inString))); 
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
