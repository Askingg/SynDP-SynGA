package me.syn.syndp.main;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.bukkit.enchantments.Enchantment;

public class Enchant {
	private static final Map<String, Enchantment> enchantments = new HashMap<String, Enchantment>();

	public static void setup() {
		enchantments.put("alldamage", Enchantment.DAMAGE_ALL);
		enchantments.put("alldmg", Enchantment.DAMAGE_ALL);
		enchantments.put("sharpness", Enchantment.DAMAGE_ALL);
		enchantments.put("sharp", Enchantment.DAMAGE_ALL);
		enchantments.put("dal", Enchantment.DAMAGE_ALL);

		enchantments.put("ardmg", Enchantment.DAMAGE_ARTHROPODS);
		enchantments.put("baneofarthropods", Enchantment.DAMAGE_ARTHROPODS);
		enchantments.put("baneofarthropod", Enchantment.DAMAGE_ARTHROPODS);
		enchantments.put("arthropod", Enchantment.DAMAGE_ARTHROPODS);
		enchantments.put("dar", Enchantment.DAMAGE_ARTHROPODS);

		enchantments.put("undeaddamage", Enchantment.DAMAGE_UNDEAD);
		enchantments.put("smite", Enchantment.DAMAGE_UNDEAD);
		enchantments.put("du", Enchantment.DAMAGE_UNDEAD);

		enchantments.put("digspeed", Enchantment.DIG_SPEED);
		enchantments.put("efficiency", Enchantment.DIG_SPEED);
		enchantments.put("minespeed", Enchantment.DIG_SPEED);
		enchantments.put("cutspeed", Enchantment.DIG_SPEED);
		enchantments.put("ds", Enchantment.DIG_SPEED);
		enchantments.put("eff", Enchantment.DIG_SPEED);

		enchantments.put("durability", Enchantment.DURABILITY);
		enchantments.put("dura", Enchantment.DURABILITY);
		enchantments.put("unbreaking", Enchantment.DURABILITY);
		enchantments.put("d", Enchantment.DURABILITY);

		enchantments.put("thorns", Enchantment.THORNS);
		enchantments.put("highcrit", Enchantment.THORNS);
		enchantments.put("thorn", Enchantment.THORNS);
		enchantments.put("highercrit", Enchantment.THORNS);
		enchantments.put("t", Enchantment.THORNS);

		enchantments.put("fireaspect", Enchantment.FIRE_ASPECT);
		enchantments.put("fire", Enchantment.FIRE_ASPECT);
		enchantments.put("meleefire", Enchantment.FIRE_ASPECT);
		enchantments.put("meleeflame", Enchantment.FIRE_ASPECT);
		enchantments.put("fa", Enchantment.FIRE_ASPECT);

		enchantments.put("knockback", Enchantment.KNOCKBACK);
		enchantments.put("kback", Enchantment.KNOCKBACK);
		enchantments.put("kb", Enchantment.KNOCKBACK);
		enchantments.put("k", Enchantment.KNOCKBACK);

		enchantments.put("blockslootbonus", Enchantment.LOOT_BONUS_BLOCKS);
		enchantments.put("fortune", Enchantment.LOOT_BONUS_BLOCKS);
		enchantments.put("fort", Enchantment.LOOT_BONUS_BLOCKS);
		enchantments.put("lbb", Enchantment.LOOT_BONUS_BLOCKS);

		enchantments.put("mobslootbonus", Enchantment.LOOT_BONUS_MOBS);
		enchantments.put("mobloot", Enchantment.LOOT_BONUS_MOBS);
		enchantments.put("looting", Enchantment.LOOT_BONUS_MOBS);
		enchantments.put("lbm", Enchantment.LOOT_BONUS_MOBS);

		enchantments.put("oxygen", Enchantment.OXYGEN);
		enchantments.put("respiration", Enchantment.OXYGEN);
		enchantments.put("breathing", Enchantment.OXYGEN);
		enchantments.put("breath", Enchantment.OXYGEN);
		enchantments.put("o", Enchantment.OXYGEN);

		enchantments.put("protection", Enchantment.PROTECTION_ENVIRONMENTAL);
		enchantments.put("prot", Enchantment.PROTECTION_ENVIRONMENTAL);
		enchantments.put("protect", Enchantment.PROTECTION_ENVIRONMENTAL);
		enchantments.put("p", Enchantment.PROTECTION_ENVIRONMENTAL);

		enchantments.put("explosionsprotection", Enchantment.PROTECTION_EXPLOSIONS);
		enchantments.put("explosionprotection", Enchantment.PROTECTION_EXPLOSIONS);
		enchantments.put("expprot", Enchantment.PROTECTION_EXPLOSIONS);
		enchantments.put("blastprotection", Enchantment.PROTECTION_EXPLOSIONS);
		enchantments.put("bprotection", Enchantment.PROTECTION_EXPLOSIONS);
		enchantments.put("bprotect", Enchantment.PROTECTION_EXPLOSIONS);
		enchantments.put("blastprotect", Enchantment.PROTECTION_EXPLOSIONS);
		enchantments.put("pe", Enchantment.PROTECTION_EXPLOSIONS);

		enchantments.put("fallprotection", Enchantment.PROTECTION_FALL);
		enchantments.put("fallprot", Enchantment.PROTECTION_FALL);
		enchantments.put("featherfall", Enchantment.PROTECTION_FALL);
		enchantments.put("featherfalling", Enchantment.PROTECTION_FALL);
		enchantments.put("pfa", Enchantment.PROTECTION_FALL);

		enchantments.put("fireprotection", Enchantment.PROTECTION_FIRE);
		enchantments.put("flameprotection", Enchantment.PROTECTION_FIRE);
		enchantments.put("fireprotect", Enchantment.PROTECTION_FIRE);
		enchantments.put("flameprotect", Enchantment.PROTECTION_FIRE);
		enchantments.put("fireprot", Enchantment.PROTECTION_FIRE);
		enchantments.put("flameprot", Enchantment.PROTECTION_FIRE);
		enchantments.put("pf", Enchantment.PROTECTION_FIRE);

		enchantments.put("projectileprotection", Enchantment.PROTECTION_PROJECTILE);
		enchantments.put("projprot", Enchantment.PROTECTION_PROJECTILE);
		enchantments.put("pp", Enchantment.PROTECTION_PROJECTILE);

		enchantments.put("silktouch", Enchantment.SILK_TOUCH);
		enchantments.put("softtouch", Enchantment.SILK_TOUCH);
		enchantments.put("st", Enchantment.SILK_TOUCH);

		enchantments.put("waterworker", Enchantment.WATER_WORKER);
		enchantments.put("aquaaffinity", Enchantment.WATER_WORKER);
		enchantments.put("watermine", Enchantment.WATER_WORKER);
		enchantments.put("ww", Enchantment.WATER_WORKER);

		enchantments.put("firearrow", Enchantment.ARROW_FIRE);
		enchantments.put("flame", Enchantment.ARROW_FIRE);
		enchantments.put("flamearrow", Enchantment.ARROW_FIRE);
		enchantments.put("af", Enchantment.ARROW_FIRE);

		enchantments.put("arrowdamage", Enchantment.ARROW_DAMAGE);
		enchantments.put("power", Enchantment.ARROW_DAMAGE);
		enchantments.put("arrowpower", Enchantment.ARROW_DAMAGE);
		enchantments.put("ad", Enchantment.ARROW_DAMAGE);

		enchantments.put("arrowknockback", Enchantment.ARROW_KNOCKBACK);
		enchantments.put("arrowkb", Enchantment.ARROW_KNOCKBACK);
		enchantments.put("punch", Enchantment.ARROW_KNOCKBACK);
		enchantments.put("arrowpunch", Enchantment.ARROW_KNOCKBACK);
		enchantments.put("ak", Enchantment.ARROW_KNOCKBACK);

		enchantments.put("infinitearrows", Enchantment.ARROW_INFINITE);
		enchantments.put("infarrows", Enchantment.ARROW_INFINITE);
		enchantments.put("infinity", Enchantment.ARROW_INFINITE);
		enchantments.put("infinite", Enchantment.ARROW_INFINITE);
		enchantments.put("unlimited", Enchantment.ARROW_INFINITE);
		enchantments.put("unlimitedarrows", Enchantment.ARROW_INFINITE);
		enchantments.put("ai", Enchantment.ARROW_INFINITE);

		enchantments.put("luck", Enchantment.LUCK);
		enchantments.put("luckofsea", Enchantment.LUCK);
		enchantments.put("luckofseas", Enchantment.LUCK);
		enchantments.put("rodluck", Enchantment.LUCK);

		enchantments.put("lure", Enchantment.LURE);
		enchantments.put("rodlure", Enchantment.LURE);
	}

	public static Enchantment getByName(String name) {
		Enchantment enchantment;
		enchantment = Enchantment.getByName(name.toUpperCase(Locale.ENGLISH));
		if (enchantment == null) {
			enchantment = enchantments.get(name.toLowerCase(Locale.ENGLISH));
		}
		if (enchantment == null) {
			enchantment = enchantments.get(name.toLowerCase(Locale.ENGLISH));
		}
		return enchantment;
	}
}
