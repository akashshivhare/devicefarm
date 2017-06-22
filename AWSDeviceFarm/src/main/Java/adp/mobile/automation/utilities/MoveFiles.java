package adp.mobile.automation.utilities;

import java.io.File;


/**
 * Created by BhuteSac on 9/1/2016.
 */
public class MoveFiles {

    public static String workingDir = System.getProperty("user.dir");
    public static void moveFiles(String path){
        File file = new File(path);
        File[] files = file.listFiles();

        for (File f:files)
        {if (f.isFile() && f.exists())
        {
            if(f.isFile() && f.getName().contains(".pdf"))
            {
                if(f.getName().contains("FSU")) {
                            f.getName().replace("FSU","Old-");
            }
                if(f.getName().contains("PSNUPDATEREPORT")) {

                f.getName().replace("FSU","OldReport1_");
            }
                if(f.getName().contains("DOL")) {

                    f.getName().replace("FSU","OldReport2_");
                }
                if(f.getName().contains("WOTC")) {

                    f.getName().replace("FSU","OldReport3_");
                }
                f.delete();

            }else {
                f.renameTo(new File(workingDir + "//test//MovedFiles//" + f.getName()));
            }
            // System.out.println("successfully deleted : " + f.getName());
        }else{
             System.out.println("ALL FILES ARE Moved");
        }
        }
    }
}
