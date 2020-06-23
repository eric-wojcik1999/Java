package dungeon.model; 

/**************************************************************************
*  Class: Enemy                                                           *
*  Author: Eric Wojcik                                                    *
*  StudentID: 19142124                                                    *
*  Date Created: 14/5/2020                                                *
*  Date Modified: 25/5/2020                                               *
*  Purpose: Enemy base class which all enemies implement                  *
***************************************************************************/

public interface Enemy
{
    String getSpeciesName();
    int getMaxHP();
    int getCurrHP();
    int getMaxAttack(); 
    int getMinAttack();
    int getMaxDefence();
    int getMinDefence();
    int getGold();
    int getDefence(); 
    boolean getIsAlive(); 
    void setSpeciesName(String inName); 
    void setMaxHP(int inMaxHealth);
    void setCurrHP(int inMinHealth); 
    void setMaxAttack(int inMaxAttack);
    void setMinAttack(int inMinAttack);
    void setMaxDefence(int inMaxDefence);
    void setMinDefence(int inMinDefence);
    void setGold(int gold); 
    int useAbility(); 
    int doAction(); 
}
