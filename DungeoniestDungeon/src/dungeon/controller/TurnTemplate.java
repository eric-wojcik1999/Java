package dungeon.controller; 

import dungeon.model.Player; 
import dungeon.model.Item; 
import dungeon.model.Enemy;
import java.util.*; 
import java.lang.*;

/**************************************************************************
*  Class: TurnTemplate                                                    *
*  Author: Eric Wojcik                                                    *
*  StudentID: 19142124                                                    *
*  Date Created: 27/5/2020                                                *
*  Date Modified: 27/5/2020                                               *
*  Purpose: Base class used to facilitate either an enemy or player turn  *
***************************************************************************/

public abstract class TurnTemplate
{
    /**************************************************************************
    *  SUBMODULE: performAction                                               *
    *  IMPORT: p (Player), e (Enemy), itemUsed (String), itemSelected (int)   *
    *  EXPORT: actionString (String)                                          *
    *  ASSERTION: Returns a transcript of player or enemy action              *
    ***************************************************************************/

    // Template method for a player or enemy turn that provides skeleton functionality, 
    //whose implementation depends on whether it is said player or enemy
    public String performAction(Player p, Enemy e, String itemUsed, int itemSelected)
    {
        String actionString = "";
        int effect = 0, defence = 0;

        effect = doAction(p,e,itemUsed,itemSelected); // Hook Method 1

        if(effect >= 0) // Attack
        {
            defence = getDefence(p,e); // Hook Method 2 
            effect = Math.max(0, effect - defence); 
            setHealth(effect, p, e); // Hook Method 3
            actionString = generateActionString(effect, p, e, itemUsed); // Hook Method 4
        }
        else if(effect < 0) // Passive e.g. Healing
        {
            setHealth(effect, p, e); // Hook Method 3
            actionString = generateActionString(effect, p, e, itemUsed); // Hook Method 4
        }

        if(itemUsed.equals("P"))
        {
            removeFromInventory(p, itemUsed, itemSelected); // Hook Method 5 
        }

        return actionString;
    }

    // Hook Method 1 
    protected abstract int doAction(Player p, Enemy e, String itemUsed, int itemSelected);
   
    // Hook Method 2
    protected abstract int getDefence(Player p, Enemy e);

    // Hook Method 3 
    protected abstract void setHealth(int effect, Player p, Enemy e);

    // Hook Method 4 
    protected abstract String generateActionString(int effect, Player p, Enemy e, String itemUsed);

    // Hook Method 5
    protected abstract void removeFromInventory(Player p, String itemUsed, int itemSelected);
    

}