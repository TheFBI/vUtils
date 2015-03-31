package me.thefbi.venom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import me.thefbi.venom.listeners.CommandListenerPre;
import me.thefbi.venom.listeners.CreeperGlitchListener;
import me.thefbi.venom.listeners.ForbiddenPotionsListener;
import me.thefbi.venom.listeners.ForbiddenWordsListener;
import me.thefbi.venom.listeners.IGCommandManipulation;
import me.thefbi.venom.listeners.PlayerChatCooldown;
import me.thefbi.venom.listeners.PlayerChatListener;
import me.thefbi.venom.listeners.PlayerMounting;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

/*
 * VenomPvP
 * Author: Thefbi
 */

public class Main extends JavaPlugin implements Listener{
	
	public boolean chat = false;
	
	public static Main instance;
	
	private int i = 0;
	private int ticks = 1200;
	private int seconds = 5;
		
	private ArrayList<String> toggle = new ArrayList<String>();
	public List<String> canMount = new ArrayList<String>();
	
	private HashMap<UUID, Integer> time = new HashMap<UUID, Integer>();
	private HashMap<UUID, BukkitRunnable> task = new HashMap<UUID, BukkitRunnable>();
		
	public String prefix = ChatColor.GREEN + "Venom " + ChatColor.DARK_GRAY + "// " + ChatColor.GRAY;
	private String clearChat = "                                                                                                            ";
	
	public PluginManager getPluginManager()
	{
		return Bukkit.getPluginManager();
	}
	
	private void spawnCreeper(Player player)
	{
		player.getWorld().spawnEntity(player.getLocation(), EntityType.CREEPER);
	}
	
	@SuppressWarnings("deprecation")
	public void reloadChestShop()
	{
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable()
		{

			@Override
			public void run() {
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "cc reload");
				sendMessageToAll(prefix + "Chest shop was reloaded.");
				
			}
			
		}, 72000, 72000);
	}
	
	@SuppressWarnings("deprecation")
	private void sendMessageToAll(String s)
	{
		for(Player p : Bukkit.getOnlinePlayers())
		{
			p.sendMessage(s);
		}
	}
	
	private void registerListeners()
	{
		getPluginManager().registerEvents(new CommandListenerPre(), this);
		getPluginManager().registerEvents(new PlayerChatListener(), this);
		getPluginManager().registerEvents(new PlayerMounting(), this);
		getPluginManager().registerEvents(new CreeperGlitchListener(), this);
		getPluginManager().registerEvents(new ForbiddenWordsListener(), this);
		getPluginManager().registerEvents(new PlayerChatCooldown(), this);
		getPluginManager().registerEvents(new IGCommandManipulation(), this);
		getPluginManager().registerEvents(new ForbiddenPotionsListener(), this);
		getPluginManager().registerEvents(this, this);
		//getPluginManager().registerEvents(new GodAppleCooldown(), this);
	}
	
	@Override
	public void onEnable()
	{
		this.reloadChestShop();
		instance = this;
		this.registerListeners();
	}
	
	@Override
	public void onDisable()
	{
		this.instance = null;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		if(!(sender instanceof Player))
		{
			return true;
		}
		
		final Player player = (Player) sender;
		
		if(command.getName().equalsIgnoreCase("clearchat"))
		{
			if(!(player.hasPermission("venom.clearchat")))
			{
				player.sendMessage(prefix + "No permission.");
				return true;
			}
			
			for (int i = 0; i < 75; i++)
			{
				getServer().broadcastMessage(clearChat);
			}
			
			Bukkit.getServer().broadcastMessage(prefix + "Chat was cleared.");
			return true;
			
		} else if(command.getName().equalsIgnoreCase("mutechat"))
		{
			if(!(player.hasPermission("venom.mutechat")))
			{
				player.sendMessage(prefix + "No permission.");
				return true;
			}
			
			if(!toggle.isEmpty())
			{
				toggle.clear();
				chat = false;
				Bukkit.getServer().broadcastMessage(prefix + "Chat has been unmuted by " + ChatColor.RED + player.getName());
				
				return true;
				
			} else {
				
				toggle.add(player.getName());
				chat = true;
				Bukkit.getServer().broadcastMessage(prefix + "Chat has been muted by " + ChatColor.RED + player.getName());
			}
		} else if (command.getName().equalsIgnoreCase("ipcheck"))
		{
			if(!(player.hasPermission("venom.ipcheck")))
			{
				player.sendMessage(prefix + "Sorry! No permission.");
				return true;
			}
			
			if(args.length == 0)
			{
				player.sendMessage(prefix + "Usage: /ipcheck <player>");
				return true;
			}
			
			Player playerInCommand = Bukkit.getServer().getPlayer(args[0]);
			
			if(playerInCommand == null)
			{
				player.sendMessage(prefix + "Player not found.");
				return true;
			}
			
			player.sendMessage(prefix + playerInCommand.getName() + "'s IP Address: " + ChatColor.RED + playerInCommand.getAddress().getAddress());
			return true;
			
		} else if (command.getName().equalsIgnoreCase("ping"))
		{
			if(args.length == 0)
			{
				player.sendMessage(prefix + "Usage: /ping <player>");
				
				return true;
			}
			
			if(args.length == 1)
			{
				Player p = Bukkit.getServer().getPlayer(args[0]);
				
				if(p == null)
				{
					player.sendMessage(prefix + "Player not found!");
					return true;
				}
				
				player.sendMessage(prefix + p.getName() + "'s Ping : " + ChatColor.RED + getPlayerPing(p));
				return true;
			}
		} else if (command.getName().equalsIgnoreCase("ts") || command.getName().equalsIgnoreCase("teamspeak"))
		{
			player.sendMessage(prefix + "Join our Teamspeak! // " + ChatColor.RED + "Ts3.venompvp.org");
			return true;
		}  else if (command.getName().equalsIgnoreCase("website"))
		{
			player.sendMessage(prefix + "Check out our forums! // " + ChatColor.RED + "http://www.venompvp.org/");
			return true;
		} else if (command.getName().equalsIgnoreCase("schedulerestart"))
		{
			if(!(player.hasPermission("venom.shchedulerestart")))
			{
				player.sendMessage(prefix + "No permission.");
				return true;
			}
			
			this.i = 1;
			
			sendMessageToAll(prefix + ChatColor.BOLD + "Scheduled server restart in 1 minute!");
			
			i = Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable()
			{

				@Override
				public void run() {
					
					ticks -=20;
					
					if(ticks == 500)
					{
						sendMessageToAll(prefix + ChatColor.BOLD + ChatColor.RED + "Scheduled Server Restart in 30 seconds!");
						
					}
					
					if (ticks == 100)
					{
						sendMessageToAll(prefix + ChatColor.BOLD + ChatColor.RED + "Scheduled Server Restart in " + seconds + " seconds!");
						seconds--;
					}
					
					if (ticks == 80)
					{
						sendMessageToAll(prefix + ChatColor.BOLD + ChatColor.RED + "Scheduled Server Restart in " + seconds + " seconds!");
						seconds--;
					}
					
					if (ticks == 60)
					{
						sendMessageToAll(prefix + ChatColor.BOLD + ChatColor.RED + "Scheduled Server Restart in " + seconds + " seconds!");
						seconds--;
					}
					
					if (ticks == 40)
					{
						sendMessageToAll(prefix + ChatColor.BOLD + ChatColor.RED + "Scheduled Server Restart in " + seconds + " seconds!");
						seconds--;
					}
					
					if (ticks == 20)
					{
						sendMessageToAll(prefix + ChatColor.BOLD + ChatColor.RED + "Scheduled Server Restart in " + seconds + " seconds!");
						seconds--;
					}
					
					if (ticks == 0)
					{
						sendMessageToAll(prefix + ChatColor.BOLD + ChatColor.RED + "Server Restarting!");
						seconds = 5;
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "restart");
					}
				}
				
			}, 20, 20);
			
		} else if (command.getName().equalsIgnoreCase("cancelrestart"))
		{
			if(i == 0)
			{
				player.sendMessage(prefix + "There isn't a restart scheduled!");
				return true;
				
			} else {
				
				Bukkit.getScheduler().cancelTask(i);
				sendMessageToAll(prefix + "Scheduled restart has been canelled!");
				return true;
			}
		} else if (command.getName().equalsIgnoreCase("creeper"))
		{
			if(this.time.containsKey(player.getUniqueId()))
			{
				player.sendMessage(prefix + "You must wait for " + ChatColor.RED + this.time.get(player.getUniqueId()) / 20 + ChatColor.GRAY + " seconds before using this command again.");
				return true;                
			}
			
			player.sendMessage(prefix + "You have used /creeper. You must wait " + ChatColor.RED + "10 minutes(s)" + ChatColor.GRAY + " before using it again");
			this.spawnCreeper(player);
			
			int seconds = 12000;
			
			time.put(player.getUniqueId(), seconds);
			task.put(player.getUniqueId(), new BukkitRunnable()
			{
				@Override
				public void run()
				{
					time.put(player.getUniqueId(), time.get(player.getUniqueId()) - 1);
					if (time.get(player.getUniqueId()) == 0)
					{
						task.remove(player.getUniqueId());
						time.remove(player.getUniqueId());
						cancel();
					}
				}
				
			});
			
			task.get(player.getUniqueId()).runTaskTimer(this, 20, 20);
			
		}
		
		return false;
		
		}
	
	public int getPlayerPing(Player player)
	{
		return ((CraftPlayer) player).getHandle().ping;
	}
	
}
