package com.example.examplemod;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;

public class TCTDebug extends CommandBase {
    @Override
    public String getCommandName() {
        return "tctdebug";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/" + getCommandName();
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("mode:"+ ThelowChatToggle.mode+""));
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("prefix:"+ ThelowChatToggle.prefix));
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("chatmsg:"+ ThelowChatToggle.chatmsg+""));
    }

}
