package dungeon.controller; 

import dungeon.model.Player; 
import dungeon.model.Item; 
import dungeon.model.Potion;
import dungeon.model.Enemy;
import java.util.*; 

/**************************************************************************
*  Class: PlayerTurn                                                      *
*  Author: Eric Wojcik                                                    *
*  StudentID: 19142124                                                    *
*  Date Created: 27/5/2020                                                *
*  Date Modified: 27/5/2020                                               *
*  Purpose: Class used to facilitate a player's turn                      *
***************************************************************************/

public class PlayerTurn extends TurnTemplate
{

    @Override
    protected int doAction(Player p, Enemy e, String itemUsed, int itemSelected)
    {
        int effect = 0, index; 
        boolean itemFound; 
        Item i = null; 

        if(itemUsed.equals("W"))
        {
            i = p.getEquippedItem(itemUsed);
            if(i!=null)
            {
                effect = i.use();
            }
        }
        else if(itemUsed.equals("P"))
        {
            itemFound = false;
            index = 0; 
            ArrayList<Item> potions = p.getPotions();	//Obtains a temporary list of all potions in the player's inventory
            for(Item potion : potions)
	        {  
	            Potion temp = (Potion)potion;
                
                if(index == itemSelected && itemFound==false) // Player will always choose potion starting from 1, whereas index starts from 0
                {
                    effect = temp.use(); // Use the potion the user selected via the UI 
                    itemFound = true; 
                }

                index ++; 
            }
        }
        return effect;
    }

    @Override 
    protected int getDefence(Player p, Enemy e)
    {
        return e.getDefence(); 
    }

    @Override
    protected void setHealth(int effect, Player p, Enemy e)
    {
        if(effect >= 0) // Player attack
        {
            e.setCurrHP(Math.max(0, e.getCurrHP() - effect));   
        }
        else if(effect < 0) // Player passive i.e. healing
        {
            effect = effect * -1; // Invert effect to a positive
            p.setCurrHP(p.getCurrHP() + effect); 
        }
    }

    @Override
    protected String generateActionString(int effect, Player p, Enemy e, String itemUsed)
    {
        String actionString = "";
    
        if(effect >= 0) // Player attack
        {
            actionString = "\n>" + p.getName() + " attacked " + e.getSpeciesName();
            
            if(itemUsed.equals("P"))
            {
                actionString += " with a potion";
            }

            actionString += " dealing " + effect + "pts of damage!"; 
        }
        else if(effect < 0) // Player passive i.e healing 
        {
            effect = effect * -1;  //Invert effect to a positive
            actionString = "\n>" + p.getName() + " healed themselves, restoring " + effect + "pts of HP";
        }

        return actionString; 
    }

    @Override
    protected void removeFromInventory(Player p, String itemUsed, int itemSelected)
    {
        boolean itemFound;
        int index; 

        if(itemUsed.equals("P"))
        {
            itemFound = false;
            index = 0; 
            ArrayList<Item> potions = p.getPotions();	//Obtains a temporary list of all potions in the player's inventory
            
            for(Item potion : potions)
	        {  
	            Potion temp = (Potion)potion;
                
                if(index == itemSelected && itemFound==false) // Player will always choose potion starting from 1, whereas index starts from 0
                {
                    p.removeInventoryItem(temp); 
                    itemFound = true; 
                }
                index ++; 
            } 
        }
    }
}