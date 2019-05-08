import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;

import java.io.*;
import java.security.MessageDigest;

/*
    Data structure is as follows
    A Map with key=fileSize and value=ArrayList of filenames
*/
public class Generate_Movie_Names {
  private static List<String> movieFolderList = new ArrayList<String>();
  private static List<String> filmFileList = new ArrayList<String>();
  private static List<String> filmNameList = new ArrayList<String>();
  private static List<String> exclusionList = new ArrayList<String>();
  /******************************************************************************************/
  public static void main(String[] args) throws Exception {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
    //;
    System.setOut(new PrintStream(new FileOutputStream("movie_names_all_"+dateFormat.format(new Date())+".log")));
	List<String> inputFilesList = new ArrayList<String>();
	inputFilesList = FileUtil.getFileList("input");

	for(int x=0;x<inputFilesList.size();x++) {
		ArrayList<String> fileList = readFileList(inputFilesList.get(x));
		filmFileList.addAll(fileList);
	}
	System.out.println("Final size = "+filmFileList.size());
    for(int i=0;i<filmFileList.size();i++) {
	  File fil = new File(filmFileList.get(i));
	  String fileName = fil.getName();
	  //if(fil.length() < 50 * 1000 * 1000) { //if less than 50 MB, skip it
	//	continue;
	  //}

	  if(fileName.endsWith("md5")) {
		  continue;
	  }
	  if(fileName.endsWith("srt")) {
		  continue;
	  }
	  if(fileName.endsWith("vtt")) {
		  continue;
	  }
	  if(fileName.endsWith("sub")) {
		  continue;
	  }
	  if(fileName.endsWith("idx")) {
		  continue;
	  }
	  if(fileName.startsWith("201")) {
		  continue;
	  }
	  if(fileName.startsWith("MSW S")) {
		  continue;
	  }
      
	  fileName = fileName.replaceAll("\\."," ");
	  fileName = fileName.replaceAll("_"," ");
	  fileName = fileName.replaceAll("-"," ");
	  fileName = fileName.replaceAll("\\("," ");
	  String fileNameTemp = fileName.toUpperCase();
	  if(fileNameTemp.startsWith("THE ")) {
		fileName = fileName.substring(3);
		fileName = fileName.trim();
	  }
	  if(fileNameTemp.startsWith("A ")) {
		fileName = fileName.substring(1);
		fileName = fileName.trim();
	  }
	  fileName = fileName.replaceAll("@Rarefilms"," ");
//Cinemaa Chirimaa
//Outer Limits TOS
	  fileName = fileName.replaceAll("  "," ");
	  fileName = fileName.replaceAll("  "," ");
	  fileName = fileName.replaceAll("  "," ");

      fileName = fileName.trim();
       
	  filmNameList.add(fileName +"("+filmFileList.get(i)+")");
	  //System.out.println(fileName);
    }

	StringComparator sc = new StringComparator();
	Collections.sort(filmNameList, sc);
    for(int i=0;i<filmNameList.size();i++) {
	  filmNameList.get(i);
	  System.out.println(filmNameList.get(i));
    }
    System.out.println("REM   -------- program end");
	
  }
  /******************************************************************************************/
  public static ArrayList<String> readFileList(String fileName) throws Exception {
	System.out.println("loading sob.txt - start");
    ArrayList<String> returnArray = new ArrayList<String>();
    try {
        BufferedReader input =  new BufferedReader(new FileReader(new File(fileName)));
        String line = null; //not declared within while loop
        while (( line = input.readLine()) != null){
          String[] split = line.split("\\|");
          if(split != null) {
              line = split[0];
              String size = split[1];
              size = size.replaceAll(",","");
              long sizeInt = Long.parseLong(size);
              if(sizeInt < 100 * 1000 * 1000) { //if less than 50 MB, skip it
                continue;
              }
          }
          returnArray.add(line);
        }
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
    public static List<String> getFileListFromFolder(String sourcePath) {
        //System.out.println(sourcePath);
		if(exclusionList.contains(sourcePath)) {
			return new ArrayList<String>();
		}
        File dir = new File(sourcePath);
		if(!dir.exists()) {
			return new ArrayList<String>();
		}
        List<String> fileTree = new ArrayList<String>();
        for (File entry : dir.listFiles()) {
            if (entry.isFile())
                fileTree.add(entry.getAbsolutePath());
            else
                fileTree.addAll(getFileListFromFolder(entry.getAbsolutePath()));
        }
        return fileTree;
    }
  /******************************************************************************************/

}


