package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class FileIO {
    public static final int MAX_LENGTH=Integer.MAX_VALUE;
    static class FileTooBigException extends Exception{
        public FileTooBigException(){
            super();
        }
        public FileTooBigException(String message){
            super(message);
        }
        public FileTooBigException(String message, Throwable cause) {
            super(message, cause);
        }
        public FileTooBigException(Throwable cause) {
            super(cause);
        }
    }

    public static String read(String filePath)throws Exception{
        return read(filePath,StandardCharsets.UTF_8);
    }
    public static String read(String filePath, Charset charset)throws Exception{
        File file=new File(filePath);
        long fileLength=file.length();
        if (fileLength>=MAX_LENGTH){
            throw new FileTooBigException(file+" is bigger than MAX_LENGTH("+MAX_LENGTH+"):"+fileLength);
        }
        byte[] bufByte =new byte[(int)fileLength];
        FileInputStream fis=new FileInputStream(file);
        fis.read(bufByte);
        fis.close();
        return new String(bufByte, charset);
    }

    public static void write(String filePath,String content)throws Exception{
        write(filePath,content,StandardCharsets.UTF_8,false);
    }
    public static void write(String filePath,String content,boolean append)throws Exception{
        write(filePath, content,StandardCharsets.UTF_8, append);
    }
    public static void write(String filePath,String content,Charset charset,boolean append)throws Exception{
        if (!append) {
            File file = new File(filePath);
            FileOutputStream fos = new FileOutputStream(file);
            byte[] buf = content.getBytes(charset);
            fos.write(buf);
            fos.flush();
            fos.close();
        }else {
            write(filePath,read(filePath,charset)+content,charset,false);
        }
    }
}
