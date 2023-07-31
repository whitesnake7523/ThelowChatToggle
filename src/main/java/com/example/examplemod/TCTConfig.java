package com.example.examplemod;

import java.io.*;

import gnu.trove.impl.hash.THash;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.common.config.Configuration;

public class TCTConfig {

    public static void load() {
        try {
            File file = new File("config/TCTConfig.txt");
            if(file.exists()) {
                FileReader filereader = new FileReader(file);
                BufferedReader br = new BufferedReader(filereader);
                String data;
                int num=0;
                while((data = br.readLine()) != null) {
                    if(data.startsWith("fixed:")){
                        try {
                            num=Integer.parseInt(data.substring(6, 7));
                            if(num==0)
                                ThelowChatToggle.fixed=false;
                            if(num==1)
                            ThelowChatToggle.fixed=true;
                        }
                        catch (NumberFormatException e){
                        }
                    }
                    if(data.startsWith("response:")){
                        try {
                            num=Integer.parseInt(data.substring(9, 10));
                            if(num==0)
                                ThelowChatToggle.response=false;
                            if(num==1)
                                ThelowChatToggle.response=true;
                        }
                        catch (NumberFormatException e){
                        }
                    }
                    if(data.startsWith("HUDX:")){
                        try {
                            num=Integer.parseInt(data.substring(5, 6));
                            ThelowChatToggle.ChromaX=num;
                        }
                        catch (NumberFormatException e){
                        }
                    }
                    if(data.startsWith("HUDY:")){
                        try {
                            num=Integer.parseInt(data.substring(5, 6));
                            ThelowChatToggle.ChromaY=num;
                        }
                        catch (NumberFormatException e){
                        }
                    }
                }

                //ファイルクローズ
                filereader.close();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (NullPointerException e){
            e.printStackTrace();
        }
        catch (IndexOutOfBoundsException e){
            e.printStackTrace();
        }
    }

    public static void save() {
        try {
            File file = new File("config/TCTConfig.txt");
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            if (ThelowChatToggle.fixed)
                bufferedWriter.write("fixed:1");
            else
                bufferedWriter.write("fixed:0");
            bufferedWriter.newLine();
            if (ThelowChatToggle.response)
                bufferedWriter.write("response:1");
            else
                bufferedWriter.write("response:0");
            bufferedWriter.newLine();
            bufferedWriter.write("HUDX:"+ ThelowChatToggle.ChromaX +"");
            bufferedWriter.newLine();
            bufferedWriter.write("HUDY:"+ ThelowChatToggle.ChromaY +"");

            bufferedWriter.flush();

            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
