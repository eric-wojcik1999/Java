package dungeon.controller; 

import dungeon.controller.fileio.FileReader; 
import dungeon.controller.fileio.LocalFileReader;
import dungeon.controller.fileio.RemoteFileReader;

/**************************************************************************
*  Class: ReaderFactory                                                   *
*  Author: Eric Wojcik                                                    *
*  StudentID: 19142124                                                    *
*  Date Created: 15/5/2020                                                *
*  Date Modified: 24/5/2020                                               *
*  Purpose: Decides which reader sub class to use based on a given string *
***************************************************************************/

public class ReaderFactory 
{
    //For testability 
    private static FileReader testReader = null;

    //For testability 

    /**************************************************************************
    *  SUBMODULE: setTestReader                                               *
    *  IMPORT: t1 (Reader)                                                    *
    *  EXPORT: none                                                           *
    *  ASSERTION: Returns a test reader for mocking purposes                  *
    ***************************************************************************/

    public void setTestReader(FileReader t1)
    {
        testReader = t1;
    }

    /**************************************************************************
    *  SUBMODULE: makeReader                                                  *
    *  IMPORT: readerMethod (String), inShopController (ShopController),      *
    *  inItemFactory (ItemFactory)                                            *
    *  EXPORT: fileReader (FileReader)                                        *
    *  ASSERTION: Returns an FileReader based on the params provided          *
    ***************************************************************************/

    public FileReader makeFileReader(String readerMethod, ShopController inShopController, ItemFactory inItemFactory)
    {
	FileReader fileReader = null; 
	
	//If testReader == null, means factory isn't being mocked 
	if(testReader == null)
	{
	    if(readerMethod.equals("L") || readerMethod.equals("l"))
	    {
	        fileReader = genLocalReader(inShopController, inItemFactory); 
	    }
	    else if(readerMethod.equals("R") || readerMethod.equals("r"))
	    {
	        fileReader = genRemoteReader(inShopController, inItemFactory);
	    }
	    else	
	    {
	        throw new IllegalArgumentException("Error: Invalid Reader Selected: " + readerMethod); 
	    }
	}
	else
	{
	    return testReader; 
	}	
	return fileReader; 
    }

    /**************************************************************************
    *  SUBMODULE: genLocalReader                                              *
    *  IMPORT: inShopController (ShopController), inItemFactory (ItemFactory) *
    *  EXPORT: localFileReader (LocalFileReader)                              *
    *  ASSERTION: Returns a FileReader of type LocalFileReader                *
    ***************************************************************************/

    private FileReader genLocalReader(ShopController inShopController, ItemFactory inItemFactory)
    {
	return new LocalFileReader(inShopController, inItemFactory); 
    }

    /**************************************************************************
    *  SUBMODULE: genRemoteReader                                             *
    *  IMPORT: inShopController (ShopController), inItemFactory (ItemFactory) *
    *  EXPORT: remoteFileReader (RemoteFileReader)                            *
    *  ASSERTION: Returns a FileReader of type remoteFileReader               *
    ***************************************************************************/

    private FileReader genRemoteReader(ShopController inShopController, ItemFactory inItemFactory)
    {
	return new RemoteFileReader(inShopController, inItemFactory); 
    }


}
