package dungeon.model; 

import dungeon.model.Item; 
import java.lang.*; 

/**************************************************************************
*  Class: Weapon                                                          *
*  Author: Eric Wojcik                                                    *
*  StudentID: 19142124                                                    *
*  Date Created: 14/5/2020                                                *
*  Date Modified: 24/5/2020                                               *
*  Purpose: Weapon model                                                  *
***************************************************************************/

public class Weapon implements Item
{
    //CLASSFIELDS 

    private String name; 
    private int cost;
    private int minEffect;
    private int maxEffect; 
    private String weaponType; 
    private String dmgType;
    private static final String ITEM_TYPE = "W"; 

    //CONSTRUCTORS

    /**************************************************************************
    *  SUBMODULE: DEFAULT CONSTRUCTOR                                         *
    *  IMPORT: none                                                           *
    *  EXPORT: none                                                           *
    *  ASSERTION: A Weapon object initialised with default values             *
    ***************************************************************************/

    public Weapon() 
    {
	name = "Borgen Blade"; 
  	cost = 9999;
	minEffect = 21;
 	maxEffect = 39; 
   	weaponType = "Polearm";
	dmgType = "Blunt"; 
     }

    /**************************************************************************
    *  SUBMODULE: ALTERNATE CONSTRUCTOR                                       *
    *  IMPORT: inName (String), inCost (String), inMinEffect (int),           *
    *  inMaxEffect (int), inWeaponType (String), inDmgType (String)           *
    *  EXPORT: none                                                           *
    *  ASSERTION: A weapon object initialised with valid parameters           *
    ***************************************************************************/

    public Weapon(String inName, int inCost, int inMinEffect, int inMaxEffect, String inWeaponType, String inDmgType)
    {
	setName(inName);
	setCost(inCost); 
	setMinEffect(inMinEffect);
	setMaxEffect(inMaxEffect);
	setWeaponType(inWeaponType);
	setDmgType(inDmgType);
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
    *  SUBMODULE: getWeaponType                                               *
    *  IMPORT: none                                                           *
    *  EXPORT: weaponType (String)                                            *
    *  ASSERTION: Return a weapon type value                                  *
    ***************************************************************************/

    public String getWeaponType()
    {
	return weaponType; 
    }

    /**************************************************************************
    *  SUBMODULE: getDmgType                                                  *
    *  IMPORT: none                                                           *
    *  EXPORT: dmgType (String)                                               *
    *  ASSERTION: Return a damage type value                                  *
    ***************************************************************************/

    public String getDmgType()
    {
	return dmgType; 
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
	if (validateInt(inCost))
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
       	if (validateInt(inMinEffect))
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
	if (validateInt(inMaxEffect))
	{
	    maxEffect = inMaxEffect; 
	}
    }

    /**************************************************************************
    *  SUBMODULE: setWeaponType                                               *
    *  IMPORT: inWeaponType  (String)                                         *
    *  EXPORT: none                                                           *
    *  ASSERTION: Sets weapon type to a valid weapon type value               *
    ***************************************************************************/

    public void setWeaponType(String inWeaponType) 
    {
	if (validateString(inWeaponType))
	{
	    weaponType = inWeaponType; 
	}
    }

    /**************************************************************************
    *  SUBMODULE: setDmgType                                                  *
    *  IMPORT: inDamageType (String)                                          *
    *  EXPORT: none                                                           *
    *  ASSERTION: Sets damage type to a valid damage type value               *
    ***************************************************************************/

    public void setDmgType(String inDmgType) 
    {
	if (validateString(inDmgType))
	{
	    dmgType = inDmgType; 
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
	String toString = "WEAPON: " + getName() + " || W-TYPE: " + weaponType + " || D-TYPE: " + dmgType + " || DMG: " + minEffect + "-" + maxEffect +  " || COST: " + getCost();
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
	
	if (inObj instanceof Weapon)
	{
	    Weapon inWeapon = (Weapon)inObj; 
	    isEquals = (name.equals(inWeapon.getName())) && 
		       (cost == (inWeapon.getCost())) &&
 		       (minEffect == (inWeapon.getMinEffect())) &&
		       (maxEffect == (inWeapon.getMaxEffect())) &&
 		       (weaponType.equals(inWeapon.getWeaponType())) &&
 		       (dmgType.equals(inWeapon.getDmgType()));
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
}
