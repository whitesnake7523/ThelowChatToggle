package com.example.examplemod;
import net.minecraftforge.fml.common.eventhandler.Event;
public class ClientPreChatEvent extends Event {
    public static String message;

    public ClientPreChatEvent(String message) {
        this.message = message;
    }

    @Override
    public boolean isCancelable() {
        return true;
    }
}