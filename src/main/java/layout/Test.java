package layout;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Test {
    public static void main(String[] args) throws FileNotFoundException {
        FileInputStream fileInputStream = null;
        try{
            fileInputStream = new FileInputStream("test.txt");
            int b;
            while((b = fileInputStream.read()) != -1){
                System.out.print((char)b);
            }
        } catch (IOException e){
            e.printStackTrace();
        } finally{
            try{
                if(fileInputStream != null)
                    fileInputStream.close();
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
