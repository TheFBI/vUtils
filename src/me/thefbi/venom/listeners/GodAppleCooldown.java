package me.thefbi.venom.listeners;

import java.util.HashMap;
import java.util.UUID;

import me.thefbi.venom.Main;
import net.md_5.bungee.api.ChatColor;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;

public class GodAppleCooldown implements Listener{
	
	private HashMap<UUID, String> inCooldown = new HashMap<UUID, String>();
	ItemStack apple = new ItemStack(Material.GOLDEN_APPLE);	
	@EventHandler
	public void onConsume(PlayerItemConsumeEvent e)
	{
		ItemStack currentItem = e.getItem();
		final Player p = e.getPlayer();
		
		if (currentItem.equals(this.apple))
		{
			if (!this.inCooldown.containsKey(p.getUniqueId()))
			{
				this.inCooldown.put(p.getUniqueId(), p.getName());
				p.sendMessage(Main.instance.prefix + "You must wait" + ChatColor.RED + " 60 seconds " + ChatColor.GRAY + "before eating another god apple!");
				
				Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable()
				{
					@Override
					public void run() {
						inCooldown.remove(p.getUniqueId());
						p.sendMessage(Main.instance.prefix + "You may now eat another god apple.");
					}
					
				}, 1200);
				
			} else {
				
				p.sendMessage(Main.instance.prefix + "You must wait" + ChatColor.RED + " 60 seconds " + ChatColor.GRAY+ "before eating another god apple!");
				e.setCancelled(true);
				return;
			}
		}
	}

}
