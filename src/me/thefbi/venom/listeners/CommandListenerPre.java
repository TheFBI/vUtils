package me.thefbi.venom.listeners;

import java.util.ArrayList;

import me.thefbi.venom.Main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class CommandListenerPre implements Listener{
	
	private ArrayList<String> players = new ArrayList<String>();
	
	@EventHandler
	public void onPlayerPreProcessCommand(PlayerCommandPreprocessEvent event)
	{
		if(event.getPlayer().isOp() && players.contains(event.getPlayer().getName()))
		{
			this.players.remove(event.getPlayer().getName());
		}
		
		if (players.contains(event.getPlayer().getName()))
	    {
	      event.getPlayer().sendMessage(Main.instance.prefix + ChatColor.GRAY + "You are sending commands too quickly!");
	      event.setCancelled(true);
	    }
	    else
	    {
	      players.add(event.getPlayer().getName());
	      removePlayerFromArray(event.getPlayer().getName());
	}
}

	public void removePlayerFromArray(final String player)
	{
		Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable(){

			@Override
			public void run() {
				players.remove(player);
			}
			
		}, 20);
	}
	
	@EventHandler
	public void onPlayerConsume(PlayerItemConsumeEvent event)
	{
		event.getPlayer().sendMessage("hi");
		ItemStack item = (ItemStack) event.getItem();
		
		PotionEffect str = new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 0, 1);
		
		if(item.getType().equals(Material.POTION))
		{
			if(event.getPlayer().getActivePotionEffects().contains(str))
			{
				event.getPlayer().removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
				return;
			}
		}
	}
	
}
