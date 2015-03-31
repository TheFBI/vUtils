package me.thefbi.venom.listeners;

import java.util.ArrayList;
import java.util.UUID;

import me.thefbi.venom.Main;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerChatCooldown implements Listener{
	
	private ArrayList<UUID> cooldown = new ArrayList<UUID>();
	
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent e)
	{
		if(!this.cooldown.contains(e.getPlayer().getUniqueId()))
		{
			this.cooldown.add(e.getPlayer().getUniqueId());
			removePlayer(e.getPlayer());
			return;
		} else {
			e.getPlayer().sendMessage(Main.instance.prefix + "Stop chatting so quickly!");
			e.setCancelled(true);
		}
	}

	void removePlayer(final Player player)
	{
		Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable()
		{

			@Override
			public void run() {
				cooldown.remove(player.getUniqueId());
			}
			
		}, 25);
	}
	
}
