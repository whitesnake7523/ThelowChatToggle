package com.example.examplemod;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;

public class AllChat extends CommandBase {
    @Override
    public String getCommandName() {
        return "all";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/" + getCommandName();
    }
    public static String usage(ICommandSender arg0) {
        return new ChatToggle().getCommandUsage(arg0);
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        if (args.length > 0) {
            String message = "";
            for(int i=0;i<args.length;i++){
                message+=args[i]+" ";
            }
            ThelowChatToggle.mode=0;
            ThelowChatToggle.prefix="";
            Minecraft.getMinecraft().thePlayer.sendChatMessage(message);
        } else {
            ThelowChatToggle.mode=0;
            ThelowChatToggle.prefix="";
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("Switched to allchat"));
        }
    }

}
