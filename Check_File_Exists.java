import java.io.*;
import java.util.*;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.charset.Charset;

import java.io.*;
import java.security.MessageDigest;

/*
    Data structure is as follows
    A Map with key=fileSize and value=ArrayList of filenames
*/
public class Check_File_Exists {
  /******************************************************************************************/
  public static void main(String[] args) throws Exception {

    List<String> fileList = readFileList("movie_names_all_2019-06-03-02-52-34.log");

    int blankLineNumber = 0;
    for(int i=0;i<fileList.size();i++) {
        String line = fileList.get(i);

        int index=line.indexOf(":");
        if(index == -1 ) {
            continue;
        }
        String fileName = line.substring(index-1, line.length()-1);
        File file = new File(fileName);
        if(file.exists()) {
            System.out.println(line);
        }
	   
	  //System.out.println(fileName);
    }

    System.out.println("REM   -------- program end");
	
  }
  /******************************************************************************************/
  public static List<String> readFileList(String fileName) throws Exception {
	System.out.println("loading sob.txt - start");
    List<String> returnArray = null;
    try {
        returnArray = Files.readAllLines(Paths.get(fileName), Charset.forName("Cp1252"));
      } catch (FileNotFoundException fnfe) {
		  if (fileName.startsWith("input/E") || fileName.startsWith("input/F")) {
			  //ignore error
			  System.out.println("ignoring error. file not found :"+fileName);
		  } else {
			  throw fnfe;
		  }
	  }
      finally {
      //  input.close();
      }
	System.out.println("loading sob.txt - end. size="+returnArray.size());
    return returnArray;
  }
  /******************************************************************************************/
  /******************************************************************************************/
}
