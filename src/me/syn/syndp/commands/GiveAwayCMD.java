package me.syn.syndp.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.syn.syndp.main.Config;
import me.syn.syndp.main.Core;
import me.syn.syndp.main.Files;
import me.syn.syndp.main.Main;
import me.syn.syndp.main.ViewInventory;

public class GiveAwayCMD implements CommandExecutor {

	public static int id = -1;
	public static String player = "";
	public static ItemStack give = null;
	public static List<ItemStack> items = new ArrayList<ItemStack>();

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String lab, String[] args) {
		if (args.length == 0) {
			if (Config.gaHeader.size() > 0) {
				for (String m : Config.gaHeader) {
					Core.message(m, s);
				}
			}
			Core.message(
					Config.gaCommand.replace("%command%", "SynGA").replaceAll("%description%", "View the help list"),
					s);
			Core.message(Config.gaCommand.replace("%command%", "SynGA Hand").replaceAll("%description%",
					"Giveaway the item in your hand"), s);
			Core.message(Config.gaCommand.replace("%command%", "SynGA Inventory").replaceAll("%description%",
					"Giveaway your inventory's contents"), s);
			Core.message(Config.gaCommand.replace("%command%", "SynGA Preview").replaceAll("%description%",
					"Preview the current inventory giveaway"), s);
			Core.message(Config.gaCommand.replace("%command%", "SynGA Cancel").replaceAll("%description%",
					"Cancel your active giveaway"), s);
			Core.message(Config.gaCommand.replace("%command%", "SynGA Reload").replaceAll("%description%",
					"Reload the config file"), s);
			if (Config.gaFooter.size() > 0) {
				for (String m : Config.gaFooter) {
					Core.message(m, s);
				}
			}
			return true;
		}
		if (args[0].equalsIgnoreCase("hand")) {
			if (!(s instanceof Player)) {
				Core.message(Config.mustBePlayer, s);
				return true;
			}
			Player p = (Player) s;
			if (!p.hasPermission("syndp.giveaway.hand")) {
				Core.noPermission(s);
				return true;
			}
			ItemStack i = p.getItemInHand();
			if (i == null || i.getType() == Material.AIR) {
				Core.message(Config.gaEmptyHand, p);
				return true;
			}
			if (Config.gaMaterialBlacklist.size() > 0 && Config.gaMaterialBlacklist.contains(i.getType().toString())) {
				Core.message(Config.blacklistedMaterial.replace("%material%", i.getType().toString().toLowerCase()), p);
				return true;
			}
			if (Bukkit.getOnlinePlayers().size() < Config.playersRequired) {
				Core.message(Config.notEnoughPlayers.replace("%online%", Bukkit.getOnlinePlayers().size() + "")
						.replaceAll("%required%", Config.playersRequired + ""), p);
				return true;
			}
			if (!player.equals("")) {
				Core.message(Config.giveawayAlreadyActive, s);
				return true;
			}
			giveaway(p, i);
			return true;
		}
		if (args[0].equalsIgnoreCase("inventory")) {
			if (!(s instanceof Player)) {
				Core.message(Config.mustBePlayer, s);
				return true;
			}
			Player p = (Player) s;
			if (!p.hasPermission("syndp.giveaway.inventory")) {
				Core.noPermission(s);
				return true;
			}
			if (emptyInventory(p)) {
				Core.message(Config.inventoryEmpty, s);
				return true;
			}
			if (Bukkit.getOnlinePlayers().size() < Config.playersRequired) {
				Core.message(Config.notEnoughPlayers.replace("%online%", Bukkit.getOnlinePlayers().size() + "")
						.replaceAll("%required%", Config.playersRequired + ""), p);
				return true;
			}
			if (items.size() > 0) {
				Core.message(Config.giveawayAlreadyActive, s);
				return true;
			}
			giveaway(p);
			return true;
		}
		if (args[0].equalsIgnoreCase("preview")) {
			if (items.size() == 0) {
				if (!(s instanceof Player)) {
					Core.message(Config.mustBePlayer, s);
					return true;
				}
			}
			Player p = (Player) s;
			if (items.size() == 0) {
				Core.message(Config.noActiveInventoryGiveaway, s);
				return true;
			}
			p.openInventory(ViewInventory.preview(p));
			return true;
		}
		if (args[0].equalsIgnoreCase("cancel")) {
			if (!(s instanceof Player)) {
				Core.message(Config.mustBePlayer, s);
				return true;
			}
			Player p = (Player) s;
			if (give == null && items.size() == 0) {
				Core.message(Config.noActiveGiveaway, p);
				return true;
			}
			if (!player.equals(p.getName())) {
				Core.message(Config.notYourGiveaway, p);
				return true;
			}
			if (give != null) {
				if (p.getInventory().firstEmpty() != -1) {
					p.getInventory().addItem(give);
				} else {
					p.getLocation().getWorld().dropItemNaturally(p.getLocation(), give);
				}
				give = null;
			}
			if (items.size() > 0) {
				for (ItemStack item : items) {
					if (p.getInventory().firstEmpty() != -1) {
						p.getInventory().addItem(item);
					} else {
						p.getLocation().getWorld().dropItemNaturally(p.getLocation(), item);
					}
				}
				items.clear();
			}
			Bukkit.getServer().getScheduler().cancelTask(id);
			player = "";
			Core.broadcast(Config.giveawayCancelledBC.replace("%player%", p.getName()));
			return true;
		}
		if (args[0].equalsIgnoreCase("reload")) {
			if (!((s instanceof ConsoleCommandSender) || s.hasPermission("syndp.command.reload"))) {
				Core.message(Config.noPerm, s);
				return true;
			}
			Files.base();
			Core.message(Config.configReloaded, s);
			return true;
		}
		if (Config.gaHeader.size() > 0) {
			for (String m : Config.gaHeader) {
				Core.message(m, s);
			}
		}
		Core.message(Config.gaCommand.replace("%command%", "SynGA").replaceAll("%description%", "View the help list"),
				s);
		Core.message(Config.gaCommand.replace("%command%", "SynGA Hand").replaceAll("%description%",
				"Giveaway the item in your hand"), s);
		Core.message(Config.gaCommand.replace("%command%", "SynGA Inventory").replaceAll("%description%",
				"Giveaway your inventory's contents"), s);
		Core.message(Config.gaCommand.replace("%command%", "SynGA Preview").replaceAll("%description%",
				"Preview the current inventory giveaway"), s);
		Core.message(Config.gaCommand.replace("%command%", "SynGA Cancel").replaceAll("%description%",
				"Cancel your active giveaway"), s);
		Core.message(Config.gaCommand.replace("%command%", "SynGA Reload").replaceAll("%description%",
				"Reload the config file"), s);
		if (Config.gaFooter.size() > 0) {
			for (String m : Config.gaFooter) {
				Core.message(m, s);
			}
		}
		return true;
	}

	@SuppressWarnings("deprecation")
	public static void giveaway(Player p, ItemStack i) {
		String name = "";
		if (i.getItemMeta().hasDisplayName()) {
			name = i.getItemMeta().getDisplayName();
		} else {
			name = i.getType().toString().toLowerCase();
		}
		String[] arg = Config.giveawayBC.replace("%prefix%", Config.prefix).replace("%player%", p.getName())
				.replace("%itemname%", name).replace("%itemmaterial%", i.getType().toString().toLowerCase())
				.replace("%itemamount%", i.getAmount() + "").split(" ");
		String msg = "";
		for (int x = 0; x < arg.length; x++) {
			msg += arg[x] + " ";
			msg = msg + ChatColor.getLastColors(msg.replace("&", "§"));
		}
		for (Player pl : Bukkit.getOnlinePlayers()) {
			Core.item(pl, i, msg);
		}
		Core.message(Config.giveawayPM, p);
		player = p.getName();
		give = i;
		p.getInventory().setItemInHand(null);
		id = Bukkit.getServer().getScheduler().scheduleAsyncDelayedTask(Main.instance, new Runnable() {
			@Override
			public void run() {
				if (Bukkit.getOnlinePlayers().size() < Config.playersRequired) {
					Core.message(Config.notEnoughPlayers.replace("%online%", Bukkit.getOnlinePlayers().size() + ""), p);
					if (p.getInventory().firstEmpty() != -1) {
						p.getInventory().addItem(i);
					} else {
						Core.message(Config.inventoryFull, p);
					}
					return;
				}
				Random r = new Random();
				Player pl = (Player) Bukkit.getOnlinePlayers().toArray()[r.nextInt(Bukkit.getOnlinePlayers().size())];
				while (pl.equals(p)) {
					pl = (Player) Bukkit.getOnlinePlayers().toArray()[r.nextInt(Bukkit.getOnlinePlayers().size())];
				}
				Core.broadcast(Config.giveawayWon.replace("%player%", pl.getName()).replace("%starter%", p.getName()));
				if (pl.getInventory().firstEmpty() != -1) {
					pl.getInventory().addItem(i);
				} else {
					Core.message(Config.inventoryFull, pl);
				}
				player = "";
				give = null;
			}
		}, Config.delay * 20);
	}

	@SuppressWarnings("deprecation")
	public static void giveaway(Player p) {
		player = p.getName();
		List<ItemStack> declined = new ArrayList<ItemStack>();
		for (ItemStack i : p.getInventory().getContents()) {
			if (i != null && i.getType() != Material.AIR)
				if (Config.gaMaterialBlacklist.size() > 0
						&& Config.gaMaterialBlacklist.contains(i.getType().toString())) {
					declined.add(i);
				} else {
					items.add(i);
				}
		}
		p.getInventory().clear();
		p.updateInventory();
		if (declined.size() > 0)
			for (ItemStack i : declined)
				p.getInventory().addItem(i);
		p.updateInventory();
		String[] arg = Config.giveawayInventoryBC.replace("%player%", p.getName()).split(" ");
		String msg = "";
		for (int x = 0; x < arg.length; x++) {
			msg += arg[x] + " ";
			msg = msg + ChatColor.getLastColors(msg.replace("&", "§"));
		}
		for (Player pl : Bukkit.getOnlinePlayers()) {
			Core.noHover(pl, msg, "/SynGA Preview");
		}
		id = Bukkit.getServer().getScheduler().scheduleAsyncDelayedTask(Main.instance, new Runnable() {
			@Override
			public void run() {
				HashMap<String, List<String>> m = new HashMap<String, List<String>>();// player, itemDisplay
				Random r = new Random();
				int o = Bukkit.getOnlinePlayers().size();
				for (ItemStack i : items) {
					Player pl = (Player) Bukkit.getOnlinePlayers().toArray()[r.nextInt(o)];
					while (pl.equals(p))
						pl = (Player) Bukkit.getOnlinePlayers().toArray()[r.nextInt(o)];
					String n = "";
					if (i.getItemMeta().hasDisplayName()) {
						n = i.getItemMeta().getDisplayName();
					} else {
						n = i.getType().toString().toLowerCase();
					}
					int a = i.getAmount();
					if (m.containsKey(pl.getName())) {
						List<String> l = new ArrayList<String>();
						for (String s : m.get(pl.getName())) {
							l.add(s);
						}
						l.add(new String(Config.invHoverItem.replace("%itemname%", n)
								.replaceAll("itemmaterial", i.getType().toString().toLowerCase())
								.replace("%itemamount%", a + "")));
						m.put(pl.getName(), l);
					} else {
						m.put(pl.getName(),
								Arrays.asList(Config.invHoverItem.replace("%itemname%", n)
										.replaceAll("itemmaterial", i.getType().toString().toLowerCase())
										.replace("%itemamount%", a + "")));
					}
					if (pl.getInventory().firstEmpty() != -1) {
						pl.getInventory().addItem(i);
						pl.updateInventory();
					} else {
						Core.message(Config.inventoryFull, pl);
					}
				}
				String msg = Config.giveawayInventoryWonBC.replace("%prefix%", Config.prefix).replace("%player%",
						p.getName());
				String hvr = "";
				for (Player pl : Bukkit.getOnlinePlayers()) {
					if (!m.containsKey(pl.getName())) {
						hvr += Config.invHoverPlayerName.replace("%player%", pl.getName()) + "\n"
								+ Config.invHoverNoWinnings + "\n";
					} else {
						hvr += Config.invHoverPlayerName.replace("%player%", pl.getName()) + "\n";
						for (String n : m.get(pl.getName())) {
							hvr += n + "\n";
						}
					}
				}
				for (Player pl : Bukkit.getOnlinePlayers()) {
					Core.hover(pl, msg, "/SynGA ", hvr);
				}
				player = "";
				items.clear();
			}
		}, Config.delay * 20);
	}

	public static boolean emptyInventory(Player p) {
		for (int x = 0; x < 36; x++) {
			if (p.getInventory().getItem(x) != null)
				return false;
		}
		return true;
	}
}
