package me.syn.syndp.main;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;

import me.syn.syndp.commands.GiveAwayCMD;

public class ViewInventory implements Listener {

	public static List<Player> open = new ArrayList<Player>();

	public static Inventory preview(Player p) {
		open.add(p);
		Inventory inv = Bukkit.createInventory(null, 36, Core.color(Config.previewInventoryName.replace("%player%", GiveAwayCMD.player)));
		for (int x = 0; x < GiveAwayCMD.items.size(); x++) {
			inv.setItem(x, GiveAwayCMD.items.get(x));
		}
		return inv;
	}

	@EventHandler
	public void onClick(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		if (open.contains(p))
			e.setCancelled(true);
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onClose(InventoryCloseEvent e) {
		Player p = (Player) e.getPlayer();
		if (open.contains(p)) {
			Bukkit.getServer().getScheduler().scheduleAsyncDelayedTask(Main.instance, new Runnable() {
				@Override
				public void run() {
					open.remove(p);
				}
			}, 10);
		}
	}
}
