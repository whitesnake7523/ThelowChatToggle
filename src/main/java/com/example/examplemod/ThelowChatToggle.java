package com.example.examplemod;

import net.minecraft.command.*;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.gameevent.TickEvent;

@Mod(modid = ThelowChatToggle.MODID, version = ThelowChatToggle.VERSION)
public class ThelowChatToggle
{
    public static final String MODID = "thelowchattoggle";
    public static final String VERSION = "1.0a";
    public static boolean response =false;
    public static boolean fixed =true;
    public static String prefix="";
    public static int mode=0;
    public static boolean chatflag=false;
    public static String chatmsg="";
    public static int ChromaX=3;
    public static int ChromaY=243;
    /* mode 1=/p mode 2=/clan msg mode 3=/tell player (hopefully)*/
    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new EventHandlerClass());
        registerCommand(new Chattoggle());
        TCTConfig.load();
    }
    private void registerCommand(ICommand command) {
        ClientCommandHandler.instance.registerCommand(new Chattoggle());
        ClientCommandHandler.instance.registerCommand(new TCTDebug());
        ClientCommandHandler.instance.registerCommand(new TCTDebug2());
        ClientCommandHandler.instance.registerCommand(new HUDXY());
        ClientCommandHandler.instance.registerCommand(new AllChat());
    }
    public static class EventHandlerClass {
        @SubscribeEvent
        public void onClientTick(TickEvent.ClientTickEvent event) {
            if (event.phase == TickEvent.Phase.END && chatflag) {
                Minecraft.getMinecraft().thePlayer.sendChatMessage(chatmsg);
                chatmsg="";
            }
            chatflag = false;
        }
        @SubscribeEvent
        public void onChatReceived(ClientChatReceivedEvent event) {
            String message = event.message.getUnformattedText();
            if (message.length() > 3) {
                if (!message.isEmpty() && message.substring(0, 3).equals("[P]") && response == true) {
                    mode=1;
                    prefix = "/p ";
                }
            }
            if (message.length() > 10) {
                if (!message.isEmpty() && message.substring(0, 10).equals("[ClanChat]") && response == true) {
                    mode=2;
                    prefix = "/clan msg ";
                }
            }
        }
        @SubscribeEvent
        public void onRenderGameOverlay(RenderGameOverlayEvent.Text event) {
            if (mode != 0) {
                String message = prefix;
                HUDMessageRenderer.renderHUDMessage(message, ChromaX, ChromaY);
            }
        }
        @SubscribeEvent
        public void ClientChatCheck(ClientPreChatEvent event) {
            chatflag=true;
            String message = event.message;
            chatmsg=prefix + message;
            if(message.startsWith("/p")&& fixed==true){
                prefix="/p ";
                mode=1;
            }
            if(message.startsWith("/clan msg")&& fixed==true) {
                prefix = "/clan msg ";
                mode=2;
            }

            if (message.startsWith("/") || prefix == "") {
                chatflag = false;
            }
            else{
                event.setCanceled(true);
            }
        }
    }
}

