package me.syn.syndp.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.configuration.ConfigurationSection;

public class Config {

	public static String prefix = "";
	public static String noPerm = "";
	public static String mustBePlayer = "";
	public static String configReloaded = "";

	public static String gaCommand = "";
	public static List<String> gaHeader = new ArrayList<String>();
	public static List<String> gaFooter = new ArrayList<String>();

	public static List<String> gaMaterialBlacklist = new ArrayList<String>();
	public static int playersRequired = 2;
	public static int delay = 0;
	public static String previewInventoryName = "";

	public static String giveawayAlreadyActive = "";
	public static String gaEmptyHand = "";
	public static String blacklistedMaterial = "";
	public static String noActiveGiveaway = "";
	public static String notYourGiveaway = "";
	public static String notEnoughPlayers = "";
	public static String inventoryEmpty = "";
	public static String noActiveInventoryGiveaway = "";
	public static String inventoryFull = "";

	public static String giveawayBC = "";
	public static String giveawayPM = "";
	public static String giveawayCancelledBC = "";
	public static String giveawayWon = "";
	public static String giveawayInventoryBC = "";
	public static String invHoverPlayerName = "";
	public static String invHoverItem = "";
	public static String invHoverNoWinnings = "";
	public static String giveawayInventoryWonBC = "";
	
	public static List<String> dropparties = new ArrayList<String>();
	public static HashMap<String, String> dpType = new HashMap<String, String>();
	public static HashMap<String, List<String>> dpRewards = new HashMap<String, List<String>>();
	public static HashMap<String, String> rewardType = new HashMap<String, String>(); // key: dpname:rewardname

	public static void apply() {
		prefix = Files.config.getString("prefix");
		noPerm = Files.config.getString("generalMessages.noPermission");
		mustBePlayer = Files.config.getString("generalMessages.mustBePlayer");
		configReloaded = Files.config.getString("generalMessages.configReloaded");

		gaCommand = Files.config.getString("giveaway.helpFormat.command");
		gaHeader = Files.config.getStringList("giveaway.helpFormat.header");
		gaFooter = Files.config.getStringList("giveaway.helpFormat.footer");

		List<String> materialBlacklist = new ArrayList<String>();
		if (Files.config.getStringList("giveaway.materialBlacklist").size() > 0) {
			for (String s : Files.config.getStringList("giveaway.materialBlacklist")) {
				materialBlacklist.add(s.toUpperCase());
			}
		}
		gaMaterialBlacklist = materialBlacklist;
		playersRequired = Files.config.getInt("giveaway.playersRequired");
		delay = Files.config.getInt("giveaway.delay");
		previewInventoryName = Files.config.getString("giveaway.previewInventoryName");

		giveawayAlreadyActive = Files.config.getString("giveaway.error.giveawayAlreadyActive");
		gaEmptyHand = Files.config.getString("giveaway.error.emptyHand");
		blacklistedMaterial = Files.config.getString("giveaway.error.blacklistedMaterial");
		noActiveGiveaway = Files.config.getString("giveaway.error.noActiveGiveaway");
		notYourGiveaway = Files.config.getString("giveaway.error.notYourGiveaway");
		notEnoughPlayers = Files.config.getString("giveaway.error.notEnoughPlayers");
		inventoryEmpty = Files.config.getString("giveaway.error.inventoryEmpty");
		noActiveInventoryGiveaway = Files.config.getString("giveaway.error.noActiveInventoryGiveaway");
		inventoryFull = Files.config.getString("giveaway.error.inventoryFull");

		giveawayBC = Files.config.getString("giveaway.success.giveawayBroadcast");
		giveawayPM = Files.config.getString("giveaway.success.giveawayPrivateMessage");
		giveawayCancelledBC = Files.config.getString("giveaway.success.giveawayCancelledBroadcast");
		giveawayWon = Files.config.getString("giveaway.success.giveawayWon");
		giveawayInventoryBC = Files.config.getString("giveaway.success.giveawayInventoryBC");
		invHoverPlayerName = Files.config.getString("giveaway.success.inventoryHover.playerName");
		invHoverItem = Files.config.getString("giveaway.success.inventoryHover.item");
		invHoverNoWinnings = Files.config.getString("giveaway.success.inventoryHover.noWinnings");
		giveawayInventoryWonBC = Files.config.getString("giveaway.success.giveawayInventoryWonBC");
		
		dropparties.clear();
		dpType.clear();
		dpRewards.clear();
		rewardType.clear();
		if (Files.config.getConfigurationSection("dropparty.dropparties") != null) {
			ConfigurationSection conf = Files.config.getConfigurationSection("dropparty.dropParties");
			for (String str : conf.getKeys(false)) {
				String type = conf.getString(str + ".dpType");
				if (!(type.equalsIgnoreCase("floor") || type.equalsIgnoreCase("direct"))) {
					Core.console("&4SynDP &l»&7 DropParty: '&c" + str + "&7' has an invalid dpType, skipping it");
					continue;
				}
				if (conf.getConfigurationSection(str + ".drops") == null) {
					Core.console("&4SynDP &l»&7 DropParty: '&c" + str + "&7' has an no drops setup, skipping it");
					continue;
				}
				dpType.put(str, type.toUpperCase());
				for (String drp : conf.getConfigurationSection(str + ".drops").getKeys(false)) {
					String drop = conf.getString(str + ".drops." + drp);
					if (!(drop.equalsIgnoreCase("item") || drop.equalsIgnoreCase("command")))
					rewardType.put(str + ":" + drp, conf.getString(str + ".drops." + drp));
				}
			}
		}
	}
}
