package me.syn.syndp.main;

import org.bukkit.plugin.java.JavaPlugin;

import me.syn.syndp.commands.DropPartyCMD;
import me.syn.syndp.commands.GiveAwayCMD;

public class Main extends JavaPlugin {
	
	public static Main instance;
	
	public void onEnable() {
		instance = this;
		Core.console("&7");

		Files.base();
		getCommand("syndropparty").setExecutor(new DropPartyCMD());
		getCommand("syngiveaway").setExecutor(new GiveAwayCMD());
		getServer().getPluginManager().registerEvents(new ViewInventory(), this);
		Enchant.setup();
		
		Core.console("&7");
	}
}
