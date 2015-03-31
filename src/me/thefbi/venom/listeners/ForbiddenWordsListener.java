package me.thefbi.venom.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ForbiddenWordsListener implements Listener{
	
	private final String[] badWords = {"faggot", "fag", "nigger", "nigga", "nigguh"};

	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent e)
	{	
		String message = e.getMessage().toLowerCase();
		
		for(String m : badWords)
		{
			if(message.contains(m))
			{
				String msg = e.getMessage().toLowerCase().replace(m, "*");
				e.setMessage(msg);
			}
		}
	}

}
