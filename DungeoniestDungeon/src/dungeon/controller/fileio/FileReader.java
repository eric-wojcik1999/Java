package dungeon.controller.fileio; 

import dungeon.controller.ShopController; 
import dungeon.controller.ItemFactory; 
import dungeon.controller.exceptions.CanNotLoadShopDataException;

/**************************************************************************
*  Class: FileReader                                                      *
*  Author: Eric Wojcik                                                    *
*  StudentID: 19142124                                                    *
*  Date Created: 14/5/2020                                                *
*  Date Modified: 24/5/2020                                               *
*  Purpose: FileIO interface used by various FileReader classes           *
***************************************************************************/

public interface FileReader
{
    public void loadShopData(String source) throws CanNotLoadShopDataException;
    public ShopController getShopController(); 
    public ItemFactory getItemFactory(); 
}
