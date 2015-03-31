package me.thefbi.venom.listeners;

import me.thefbi.venom.Main;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerChatListener implements Listener{
	
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent e)
	{
		if(!(Main.instance.chat))
		{	
			return;
		}
		
		if(!(e.getPlayer().hasPermission("venom.mutechat.bypass")))
		{
			e.setCancelled(true);
			e.getPlayer().sendMessage(Main.instance.prefix + "Chat is currently muted!");
		} else {
			e.setCancelled(false);
		}
	} 
}
