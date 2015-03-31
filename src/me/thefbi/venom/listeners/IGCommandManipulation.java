package me.thefbi.venom.listeners;

import me.thefbi.venom.Main;
import net.md_5.bungee.api.ChatColor;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class IGCommandManipulation implements Listener{
	
	private String onlineHelpers = "";
	private String onlineDevs = "";
	private String onlineMods = "";
	private String onlineAdmins = "";
	
	@EventHandler
	public void onCommandProcess(PlayerCommandPreprocessEvent e)
	{
		String command = e.getMessage();
		Player player = e.getPlayer();
		
		if(command.equalsIgnoreCase("/list") || command.equalsIgnoreCase("/who"))
		{
			e.setCancelled(true);
			getAllHelpers();
			getAllDevs();
			getAllMods();
			getAllAdmins();
			player.sendMessage(ChatColor.GRAY + "Online Players [" + ChatColor.RED + getAllPlayers() + ChatColor.GRAY + "/" + ChatColor.RED + Bukkit.getMaxPlayers() + ChatColor.GRAY + "]");
			if(!(this.onlineHelpers == ""))
			{
				player.sendMessage(ChatColor.GRAY + "Helpers: " + this.onlineHelpers);
			}
			if(!(this.onlineDevs == ""))
			{
				player.sendMessage(ChatColor.GRAY + "Developers: " + this.onlineDevs);
			}
			if(!(this.onlineMods == ""))
			{
				player.sendMessage(ChatColor.GRAY + "Mods: " + this.onlineMods);
			}
			if(!(this.onlineAdmins == ""))
			{
				player.sendMessage(ChatColor.GRAY + "Admins: " + this.onlineAdmins);
			}
			this.onlineHelpers = "";
			this.onlineDevs = "";
			this.onlineMods = "";
			this.onlineAdmins = "";
			return;
		}
	}
	
	@SuppressWarnings("deprecation")
	private int getAllPlayers()
	{
		int count = 0;
		for(@SuppressWarnings("unused") Player p : Bukkit.getOnlinePlayers())
		{
			count++;
		}
		return count;
	}

	@SuppressWarnings("deprecation")
	public void getAllHelpers()
	{
		for(Player p : Bukkit.getOnlinePlayers())
		{
				if(!this.onlineHelpers.contains(p.getName()) && p.hasPermission("venom.helpers"))
				{
					this.onlineHelpers += ChatColor.RED + p.getName() + ChatColor.GRAY +  ", ";
				}
		}
	}
	
	@SuppressWarnings("deprecation")
	public void getAllDevs()
	{
		for(Player p : Bukkit.getOnlinePlayers())
		{
				if(!this.onlineDevs.contains(p.getName()) && p.hasPermission("venom.devs"))
				{
					this.onlineDevs += ChatColor.RED + p.getName();
				}
		}
	}
	
	@SuppressWarnings("deprecation")
	public void getAllMods()
	{
		for(Player p : Bukkit.getOnlinePlayers())
		{
				if(!this.onlineMods.contains(p.getName()) && p.hasPermission("venom.mods"))
				{
					this.onlineMods += ChatColor.RED + p.getName() + ChatColor.GRAY + ", ";
				}
		}
	}
	
	@SuppressWarnings("deprecation")
	public void getAllAdmins()
	{
		for(Player p : Bukkit.getOnlinePlayers())
		{
				if(!this.onlineAdmins.contains(p.getName()) && p.hasPermission("venom.admins"))
				{
					this.onlineAdmins += ChatColor.RED + p.getName() + ChatColor.GRAY + ", ";
				}
		}
	}
	
}
