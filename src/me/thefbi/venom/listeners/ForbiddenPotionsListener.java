package me.thefbi.venom.listeners;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.BrewEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class ForbiddenPotionsListener implements Listener{
	
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

	@EventHandler
	public void onPotionBrew(BrewEvent e)
	{	
		if(e.getContents().contains(Material.BLAZE_POWDER))
		{
			e.setCancelled(true);
			return;
		}
	}
	
}
