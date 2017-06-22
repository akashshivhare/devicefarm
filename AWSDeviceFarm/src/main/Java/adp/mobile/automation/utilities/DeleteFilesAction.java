package adp.mobile.automation.utilities;


import java.io.File;

/**
 * Created by BhuteSac on 4/13/2016.
 */
public class DeleteFilesAction {

        public static void deleteScreenShot(String path){
            File file = new File(path);
            File[] files = file.listFiles();
            
            for (File f:files)
            {if (f.isFile() && f.exists())
            {
                f.deleteOnExit();
                f.delete();
            System.out.println("successfully deleted : " + f.getName());
            }else{
            	System.out.println("ALL FILES ARE DELETED");
            }
            }
        }

}

