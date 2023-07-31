package com.example.examplemod;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;

public class HUDXY extends CommandBase{
    @Override
    public String getCommandName() {
        return "hudxy";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/hudxy <x> <y>";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        if (args.length < 2) {
            sender.addChatMessage(new ChatComponentText("Usage: /hudxy <x> <y>"));
            return;
        }
        try {
            ThelowChatToggle.ChromaX = Integer.parseInt(args[0]);
            ThelowChatToggle.ChromaY = Integer.parseInt(args[1]);
            sender.addChatMessage(new ChatComponentText("HUD location X:"+ ThelowChatToggle.ChromaX+" Y:"+ ThelowChatToggle.ChromaY+""));
            TCTConfig.save();
        } catch (NumberFormatException e) {
            sender.addChatMessage(new ChatComponentText("Invalid arguments. Usage: /hudxy <x> <y>"));
        }
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true;
    }
}
