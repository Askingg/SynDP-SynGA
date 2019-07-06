package me.syn.syndp.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import me.syn.syndp.main.Config;
import me.syn.syndp.main.Core;

public class DropPartyCMD implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String lab, String[] args) {
		if (args.length == 0) {
			Core.message(Config.noPerm, s);
		}
		return false;
	}
}
