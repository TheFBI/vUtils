package me.thefbi.venom.listeners;

import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class PlayerMounting implements Listener{
	
	@EventHandler
	public void onMount(PlayerInteractEntityEvent e)
	{
		if(e.getPlayer().isOp())
		{	
			Entity p = e.getRightClicked();
			p.setPassenger(e.getPlayer());
			
		} else {
			
			return;
		}
	}

}
