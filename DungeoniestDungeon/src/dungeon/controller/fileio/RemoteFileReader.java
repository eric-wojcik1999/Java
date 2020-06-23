package dungeon.controller.fileio; 

import dungeon.controller.ItemFactory; 
import dungeon.controller.ShopController; 
import dungeon.controller.exceptions.*; 
import java.io.*;
import java.util.*; 

/**************************************************************************
*  Class: RemoteFileReader                                                *
*  Author: Eric Wojcik                                                    *
*  StudentID: 19142124                                                    *
*  Date Created: 14/5/2020                                                *
*  Date Modified: 24/5/2020                                               *
*  Purpose: FileReader class that reads shop data other sources like DBs  *
***************************************************************************/

public class RemoteFileReader implements FileReader
{
    private ShopController shopController;
    private ItemFactory itemFactory; 

    /**************************************************************************
    *  SUBMODULE: ALTERNATE CONSTRUCTOR                                       *
    *  IMPORT: inShopController (ShopController), inItemFactory (ItemFactory) *
    *  EXPORT: none               s                                            *
    *  ASSERTION: A RemoteFileReader is initialised with a shop controller,    *
    *  and a valid item factory                                               *
    ***************************************************************************/

    public RemoteFileReader(ShopController inShopController, ItemFactory inItemFactory)
    {
	shopController = inShopController; 
	itemFactory = inItemFactory; 
    }

    /**************************************************************************
    *  SUBMODULE: loadShopData                                                *
    *  IMPORT: source (String)                                                *
    *  EXPORT: none                                                           *
    *  ASSERTION: The entire shop data file is read and processed into        *
    *  meaningful data                                                        *
    ***************************************************************************/

    public void loadShopData(String source)
    {
	//Requires future implementation 
    }


    /**************************************************************************
    *  SUBMODULE: getShopController                                           *
    *  IMPORT: none                                                           *
    *  EXPORT: shopController (ShopController)                                *
    *  ASSERTION: Returns a valid shopController                              *
    ***************************************************************************/

    public ShopController getShopController()
    {
 	return shopController; 
    }

    /**************************************************************************
    *  SUBMODULE: getItemFactory                                              *
    *  IMPORT: none                                                           *
    *  EXPORT: itemFactory (ItemFactory)                                      *
    *  ASSERTION: Returns a valid itemFactory                                 *
    ***************************************************************************/

    public ItemFactory getItemFactory()
    {
	return itemFactory; 
    } 
}
