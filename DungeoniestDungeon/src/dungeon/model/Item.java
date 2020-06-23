package dungeon.model; 

/**************************************************************************
*  Class: Item                                                            *
*  Author: Eric Wojcik                                                    *
*  StudentID: 19142124                                                    *
*  Date Created: 14/5/2020                                                *
*  Date Modified: 24/5/2020                                               *
*  Purpose: Item interface for potion, armour, weapon                     *
***************************************************************************/

public interface Item
{
    String getName();
    int getCost(); 
    int getMinEffect();
    int getMaxEffect();
    String getItemType(); 
    void setName(String inName);
    void setCost(int inCost);
    void setMinEffect(int inMinEffect);
    void setMaxEffect(int inMaxEffect); 
    int use(); 
    boolean equal(Object inObject); 
}
