package com.example.examplemod;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.util.ChatComponentText;

public class Chattoggle extends CommandBase {
    @Override
    public String getCommandName() {
        return "tct";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/" + getCommandName();
    }
    public static String usage(ICommandSender arg0) {
        return new Chattoggle().getCommandUsage(arg0);
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        if (args.length > 0) {
            String argument = args[0];
            if(argument.equals("res")){
                if (ThelowChatToggle.response) {
                    ThelowChatToggle.response = false;
                    Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("mode: response off"));
                }
                else{
                    ThelowChatToggle.response=true;
                    Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("mode: response on"));
                }
                TCTConfig.save();
            }
            else if(argument.equals("fix")){
                if (ThelowChatToggle.fixed){
                    ThelowChatToggle.fixed=false;
                    Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("mode: fixed off"));
                }
                else {
                    ThelowChatToggle.fixed = true;
                    Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("mode: fixed on"));
                }
                TCTConfig.save();
            }
            else{Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("/tct res or /tct fix"));}
        } else {
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("/tct res or /tct fix"));
            throw new WrongUsageException(getCommandUsage(sender));
        }
    }

}
