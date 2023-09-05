package com.example.examplemod;
import net.minecraft.command.*;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import java.util.regex.Pattern;

@Mod(modid = ThelowChatToggle.MODID, version = ThelowChatToggle.VERSION)
public class ThelowChatToggle
{
    public static final String MODID = "thelowchattoggle";
    public static final String VERSION = "1.1";
    public static boolean response =false;
    public static boolean fixed =true;
    public static String prefix="";
    public static int mode=0;
    public static boolean chatflag=false;
    public static boolean isOnTimer=false;
    public static String chatmsg="";
    public static int ChromaX=3;
    public static int ChromaY=243;
    public static int tickCounter=0;
    public static int resetTick=3600;
    public final Pattern whisperp=Pattern.compile("^.{1,16}\\sにささやかれました:");
    /* mode 1=/p mode 2=/clan msg mode 3=/tell player */
    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new EventHandlerClass());
        registerCommand(new ChatToggle());
        TCTConfig.load();
    }
    private void registerCommand(ICommand command) {
        ClientCommandHandler.instance.registerCommand(new ChatToggle());
        ClientCommandHandler.instance.registerCommand(new TCTDebug());
        ClientCommandHandler.instance.registerCommand(new TCTDebug2());
        ClientCommandHandler.instance.registerCommand(new HUDXY());
        ClientCommandHandler.instance.registerCommand(new AllChat());
    }
    public class EventHandlerClass {
        @SubscribeEvent
        public void NonActiveTimer(TickEvent.ClientTickEvent event){
            tickCounter++;
            if(tickCounter>=resetTick && isOnTimer){
                prefix="";
                mode=0;
                tickCounter=0;
            }
        }
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
            if(response &&!message.isEmpty()) {
                    if (message.startsWith("[P]")) {
                        tickCounter=0;
                        mode = 1;
                        prefix = "/p ";
                    }
                    else if (message.startsWith("[ClanChat]")) {
                        tickCounter=0;
                        mode = 2;
                        prefix = "/clan msg ";
                    }
                    else if (whisperp.matcher(message).find()){
                        tickCounter=0;
                        String[] spliiit=message.split(" ",2);
                        prefix="/tell "+spliiit[0]+" ";
                        mode = 3;
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
            tickCounter=0;
            String message = event.message;
            chatmsg = prefix + message;
            if(fixed) {
                if (message.startsWith("/p")) {
                    prefix = "/p ";
                    mode = 1;
                }
                else if (message.startsWith("/clan msg")) {
                    prefix = "/clan msg ";
                    mode = 2;
                }
                else if (message.startsWith("/tell ")){
                    String[] spliiit=message.split(" ",3);
                    try {
                        if (spliiit.length > 2) {
                            prefix = "/tell " + spliiit[1] + " ";
                            mode = 3;
                        } else {
                            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("/tell player message"));
                        }
                    }catch(NullPointerException e){Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("/tell player message"));}
                }
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

