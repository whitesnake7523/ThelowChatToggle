package com.example.examplemod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.EnumChatFormatting;

public class HUDMessageRenderer {

    private static final Minecraft mc = Minecraft.getMinecraft();
    private static final FontRenderer fontRenderer = mc.fontRendererObj;

    public static void renderHUDMessage(String message, int x, int y) {
        String coloredMessage = EnumChatFormatting.GRAY + message;
        fontRenderer.drawStringWithShadow(coloredMessage, x, y, 0xFFFFFF);
    }
}