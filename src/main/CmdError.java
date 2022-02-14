package main;

import java.io.BufferedReader;
import java.io.File;

/**
 * 获取命令行输出的异常并输出
 * @author Rock Chin
 */
public class CmdError extends Thread{
    BufferedReader errReader;
    StringBuffer buffer=new StringBuffer("");
    String cmd="";
    public CmdError(String cmd,BufferedReader errReader){
        this.cmd=cmd;
        this.errReader=errReader;
    }
    public void run(){
        try{
            String line;
            while (true){
                line=errReader.readLine();
                FileIO.write("err.log",cmd.substring(cmd.length()-7)+" | "+line+"\n",true);
            }
        }catch (Exception e){}
    }
}
