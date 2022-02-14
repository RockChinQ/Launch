package main;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class LauncherMain {
    public static Gson gson=new Gson();
    public static Config config;

    public static Process process;
    public static CmdError cmdError;
    public static void main(String[] args) throws Exception {
        File cfgFile=new File("launch.json");
        if (!cfgFile.exists()){
            String command=javax.swing.JOptionPane.showInputDialog("输入启动指令:");
            if (command==null||command.equals("")){
                System.exit(0);
            }

            config=new Config();
            config.command=new String[]{command};
            try {
                FileIO.write("launch.json", gson.toJson(config));
            }catch (Exception e){
                javax.swing.JOptionPane.showMessageDialog(null,"无法保存启动指令:"+e.getLocalizedMessage());
                System.exit(-1);
            }
        }else {
            config=gson.fromJson(FileIO.read("launch.json"),Config.class);
        }

        for (String cmd:config.command){
            process=Runtime.getRuntime().exec(cmd);
            cmdError=new CmdError(cmd,new BufferedReader(new InputStreamReader(process.getErrorStream())));
            cmdError.start();
        }
    }
}
