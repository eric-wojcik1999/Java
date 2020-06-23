package dungeon.controller; 

import dungeon.model.Player; 
import dungeon.model.Item; 
import dungeon.model.Potion;
import dungeon.model.Enemy;
import java.util.*; 

/**************************************************************************
*  Class: EnemyTurn                                                       *
*  Author: Eric Wojcik                                                    *
*  StudentID: 19142124                                                    *
*  Date Created: 27/5/2020                                                *
*  Date Modified: 27/5/2020                                               *
*  Purpose: Class used to facilitate a enemy's turn                       *
***************************************************************************/

public class EnemyTurn extends TurnTemplate
{
    @Override
    protected int doAction(Player p, Enemy e, String itemUsed, int itemSelected)
    {
        return e.doAction(); 
    }

    @Override 
    protected int getDefence(Player p, Enemy e)
    {
        return p.getEquippedItem("A").use(); 
    }

    @Override
    protected void setHealth(int effect, Player p, Enemy e)
    {
        if(effect >= 0) // Enemy attack
        {
            p.setCurrHP(Math.max(0, p.getCurrHP() - effect));    
        }
        else if(effect < 0) // Enemy passive i.e. healing
        {
            effect = effect * -1; // Invert effect to a positive
            e.setCurrHP(p.getCurrHP() + effect); 
        }
    }

    @Override
    protected String generateActionString(int effect, Player p, Enemy e, String itemUsed)
    {
        String actionString = "";
    
        if(effect >= 0) // Enemy attack
        {
            actionString = "\n>" + e.getSpeciesName() + " attacked " + p.getName() + " dealing " + effect + "pts of damage!";
        }
        else if(effect < 0) // Enemy passive i.e healing 
        {
            effect = effect * -1;  //Invert effect to a positive
            actionString = "\n>" + e.getSpeciesName() + " healed themselves for " + effect + "pts of HP!"; 
        }

        return actionString; 
    }

    @Override
    protected void removeFromInventory(Player p, String itemUsed, int itemSelected)
    {
        //Do nothing 
    }

}