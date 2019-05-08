import java.io.*;
import java.util.*;

public class StringComparator implements Comparator {
  /******************************************************************************************/
  public int compare(Object first, Object second)  {
	return ((String)first).compareToIgnoreCase((String)second);
  }
}


