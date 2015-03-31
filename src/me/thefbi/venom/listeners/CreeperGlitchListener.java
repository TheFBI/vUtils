package me.thefbi.venom.listeners;

import me.thefbi.venom.Main;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class CreeperGlitchListener implements Listener{
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerInteractItem(PlayerInteractEvent event)
	{
		if(event.getPlayer().getInventory().getItemInHand().getType() == Material.MONSTER_EGG)
		{
			for (int x = -5; x < 5; x++) {
		        for (int y = -5; y < 5; y++) {
		          for (int z = -5; z < 5; z++)
		          {
		            Block block = event.getPlayer().getLocation().add(x, y, z).getBlock();
		            
		            if(block.getTypeId() == 44)
		            {
		            	event.setCancelled(true);
		            	event.getPlayer().sendMessage(Main.instance.prefix + "You are not aloud to place creeper eggs on slabs!");
		            }
		          }
		        }
			}
		}
	}
}