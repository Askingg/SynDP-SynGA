package me.syn.syndp.main;

import java.text.NumberFormat;
import java.util.Locale;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.clip.placeholderapi.PlaceholderAPI;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.minecraft.server.v1_8_R3.NBTTagCompound;

public class Core {

	public static String color(String string) {
		return ChatColor.translateAlternateColorCodes('&', string);
	}

	public static String format(String string) {
		return color(string.replace("%prefix%", Config.prefix));
	}

	public static String decimals(Integer decimalSpaces, Double number) {
		return String.format("%1$,." + decimalSpaces + "f", number);
	}

	public static String decimals(Integer decimalSpaces, int number) {
		return String.format("%1$,." + decimalSpaces + "f", 0.0 + number);
	}

	public static String papi(Player p, String str) {
		return PlaceholderAPI.setPlaceholders((OfflinePlayer) p, str);
	}

	public static String time(int seconds) {
		if (seconds < 60) {
			return seconds + "s";
		}
		int minutes = seconds / 60;
		int s = 60 * minutes;
		int secondsLeft = seconds - s;
		if (minutes < 60) {
			if (secondsLeft > 0) {
				return String.valueOf(minutes + "m " + secondsLeft + "s");
			}
			return String.valueOf(minutes + "m");
		}
		if (minutes < 1440) {
			String time = "";
			int hours = minutes / 60;
			time = hours + "h";
			int inMins = 60 * hours;
			int leftOver = minutes - inMins;
			if (leftOver >= 1) {
				time = time + " " + leftOver + "m";
			}
			if (secondsLeft > 0) {
				time = time + " " + secondsLeft + "s";
			}
			return time;
		}
		String time = "";
		int days = minutes / 1440;
		time = days + "d";
		int inMins = 1440 * days;
		int leftOver = minutes - inMins;
		if (leftOver >= 1) {
			if (leftOver < 60) {
				time = time + " " + leftOver + "m";
			} else {
				int hours = leftOver / 60;
				time = time + " " + hours + "h";
				int hoursInMins = 60 * hours;
				int minsLeft = leftOver - hoursInMins;
				if (leftOver >= 1) {
					time = time + " " + minsLeft + "m";
				}
			}
		}
		if (secondsLeft > 0) {
			time = time + " " + secondsLeft + "s";
		}
		return time;
	}

	public static String number(double d) {
		NumberFormat form = NumberFormat.getInstance(Locale.ENGLISH);

		form.setMaximumFractionDigits(2);

		form.setMinimumFractionDigits(0);
		if (d < 1000.0D) {
			return form.format(d);
		}
		if (d < 1000000.0D) {
			return form.format(d / 1000.0D) + "k";
		}
		if (d < 1.0E9D) {
			return form.format(d / 1000000.0D) + "M";
		}
		if (d < 1.0E12D) {
			return form.format(d / 1.0E9D) + "B";
		}
		if (d < 1.0E15D) {
			return form.format(d / 1.0E12D) + "T";
		}
		if (d < 1.0E18D) {
			return form.format(d / 1.0E15D) + "Q";
		}

		long l = (long) d;
		return String.valueOf(l);
	}

	public static void item(Player p, ItemStack i, String chat) {
		net.minecraft.server.v1_8_R3.ItemStack stack = CraftItemStack.asNMSCopy(i);
		NBTTagCompound tag = new NBTTagCompound();
		stack.save(tag);
		p.spigot()
				.sendMessage(new ComponentBuilder(color(chat).replace("\\n", System.lineSeparator())).event(
						new HoverEvent(HoverEvent.Action.SHOW_ITEM, new ComponentBuilder(tag.toString()).create()))
						.create());
	}

	public static void noHover(Player p, String chat, String click) {
		p.spigot().sendMessage(new ComponentBuilder(format(chat))
				.event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, click)).create());
	}

	public static void hover(Player p, String chat, String click, String hover) {
		p.spigot()
				.sendMessage(new ComponentBuilder(color(chat))
						.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(color(hover)).create()))
						.event(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, click)).create());
	}

	public static void noPermission(Player p) {
		p.sendMessage(format(Config.noPerm));
	}

	public static void noPermission(CommandSender s) {
		s.sendMessage(format(Config.noPerm));
	}

	public static void console(String msg) {
		if (!msg.equals(""))
			Bukkit.getConsoleSender().sendMessage(format(msg));
	}

	public static void broadcast(String msg) {
		if (!msg.equals(""))
			Bukkit.broadcastMessage(format(msg));
	}

	public static void message(String msg, Player p) {
		if (!msg.equals(""))
			p.sendMessage(format(msg).replace("%player%", p.getName()));
	}

	public static void message(String msg, CommandSender sender) {
		if (!msg.equals(""))
			sender.sendMessage(format(msg).replace("%player%", sender.getName()));
	}
}
