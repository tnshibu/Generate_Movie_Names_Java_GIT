import java.io.*;
import java.util.*;

public class TeePrintStream  {

  private PrintStream out=null;
  private PrintStream tee=null;

  public TeePrintStream() {
  }
  public TeePrintStream(PrintStream out) {
  }
  public TeePrintStream(PrintStream out, PrintStream tee) {
    if (out == null)
      throw new NullPointerException();
    else if (tee == null)
      throw new NullPointerException();

    this.out = out;
    this.tee = tee;
  }

  public void write(int b) throws IOException {
    out.write(b);
    tee.write(b);
  }

  public void write(byte[] b) throws IOException {
    out.write(b);
    tee.write(b);
  }

  public void write(byte[] b, int off, int len) throws IOException {
    out.write(b, off, len);
    tee.write(b, off, len);
  }

  public void flush() throws IOException {
    out.flush();
    tee.flush();
  }

  public void close() throws IOException {
    try {
      out.close();
    } finally {
      tee.close();
    }
  }
  
  public void println(String str) throws IOException {
      this.write((str+"\r\n").getBytes());
  }
}
