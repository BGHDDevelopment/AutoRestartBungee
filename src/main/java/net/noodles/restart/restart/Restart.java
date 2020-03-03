package net.noodles.restart.restart;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.google.common.io.ByteStreams;

import net.md_5.bungee.api.plugin.Plugin;

import java.io.*;
import java.util.Calendar;

public class Restart extends Plugin {

    private String[] Messages;
    private int timer;

    @Override
    public void onEnable() {
        getLogger().info("Restart by BGHDDev");
        //Create Config if it does not exist
        try{
            loadConfig ();
        } catch (IOException e) {
            getLogger().info("Error loading config.txt. Seems to be broken.");
        }
        //Timer Task in a new thread
        getProxy().getScheduler().runAsync(this, new Runnable() {
            @Override
            public void run() {
                try {
/*                    for (int i = 0; i<timer; i++){
                        getLogger().info("BungeeCord will restart in "+(timer-i+3)+" minutes.");
                        Thread.sleep(60000);
                    }*/
                    getProxy().getPluginManager().dispatchCommand(getProxy().getConsole(), "alert "+Messages[0]);
                    Thread.sleep(120000);
                    getProxy().getPluginManager().dispatchCommand(getProxy().getConsole(), "alert "+Messages[1]);
                    Thread.sleep(50000);
                    getProxy().getPluginManager().dispatchCommand(getProxy().getConsole(), "alert "+Messages[2]);
                    Thread.sleep(1000);
                    getProxy().getPluginManager().dispatchCommand(getProxy().getConsole(), "alert "+Messages[3]);
                    Thread.sleep(1000);
                    getProxy().getPluginManager().dispatchCommand(getProxy().getConsole(), "alert "+Messages[4]);
                    Thread.sleep(1000);
                    getProxy().getPluginManager().dispatchCommand(getProxy().getConsole(), "alert "+Messages[5]);
                    Thread.sleep(1000);
                    getProxy().getPluginManager().dispatchCommand(getProxy().getConsole(), "alert "+Messages[6]);
                    Thread.sleep(1000);
                    getProxy().getPluginManager().dispatchCommand(getProxy().getConsole(), "alert "+Messages[7]);
                    Thread.sleep(1000);
                    getProxy().getPluginManager().dispatchCommand(getProxy().getConsole(), "alert "+Messages[8]);
                    Thread.sleep(1000);
                    getProxy().getPluginManager().dispatchCommand(getProxy().getConsole(), "alert "+Messages[9]);
                    Thread.sleep(1000);
                    getProxy().getPluginManager().dispatchCommand(getProxy().getConsole(), "alert "+Messages[10]);
                    Thread.sleep(1000);
                    getProxy().getPluginManager().dispatchCommand(getProxy().getConsole(), "alert "+Messages[11]);
                    Thread.sleep(1000);
                    getProxy().getPluginManager().dispatchCommand(getProxy().getConsole(), "alert "+Messages[12]);
                    Thread.sleep(1000);
                    getProxy().getPluginManager().dispatchCommand(getProxy().getConsole(), "end");
                }
                catch (InterruptedException e) {
                    getLogger().info("Failed somewhere...");
                }
            }
        });
    }
    private void loadConfig() throws IOException{
        //create config if it does not exist.
        if (!getDataFolder().exists()) {
            getDataFolder().mkdir();
        }

        File file = new File(getDataFolder(), "config.txt");

        if (!file.exists()) {
            file.createNewFile();
            try (java.io.InputStream in = getResourceAsStream("config_default.txt");
                 OutputStream out = new FileOutputStream(file)) {
                ByteStreams.copy(in, out);
            }
        }
        //Read Configfile
        FileReader fr = new FileReader("plugins/Restart/config.txt");
        BufferedReader br = new BufferedReader(fr);
        this.Messages = new String [13];
        String muell="";
        for (int i=0;i<7;i++) {
            muell = br.readLine();
        }
        String Temp = br.readLine();
        this.timer=Integer.parseInt(Temp);
        muell = br.readLine();
        muell = br.readLine();
        for (int i=0;i<13;i++) {
            this.Messages [i] = br.readLine();
        }
        muell = br.readLine();
        Temp = br.readLine();
        int rsStunden=Integer.parseInt(Temp);
        muell = br.readLine();
        Temp = br.readLine();
        int rsMinuten=Integer.parseInt(Temp);
        muell = br.readLine();
        Temp = br.readLine();
        int modus=Integer.parseInt(Temp);
        // Calculate timer if modus=time
        if (modus==1){
            this.timer=0;
            int std=Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
            int min=Calendar.getInstance().get(Calendar.MINUTE);
            getLogger().info("Time now: "+std+":"+min+" Restart-time: "+rsStunden+":"+rsMinuten);
            //Calculate timer
            this.timer=this.timer+((24-std+rsStunden)*60);
            if (this.timer>1440){
                this.timer=this.timer-1440;
            }
            this.timer=this.timer+(rsMinuten-min);
        }
        this.timer=this.timer-3;
        getLogger().info("Time to BungeeCord-restart set to "+(this.timer+3)+ " minutes");
        getLogger().info("Configuration loaded.");


    }
}
