package de.riotseb.horsestats.util;

import de.riotseb.horsestats.HorseStatsMain;
import lombok.experimental.UtilityClass;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

@UtilityClass
public class MessageHandler {

	private static File messageFile = new File(HorseStatsMain.getInstance().getDataFolder() + File.separator + "messages.yml");
	private static YamlConfiguration config = YamlConfiguration.loadConfiguration(messageFile);

	public String getMessage(String key) {

		String message = config.getString(key, ChatColor.RED + "Message not found! [" + key + "]");
		String prefix = getPrefix();

		return prefix + ChatColor.translateAlternateColorCodes('&', message);

	}

	public String getPrefix() {

		return ChatColor.translateAlternateColorCodes('&',
				config.getString("prefix", ChatColor.GRAY + "[" + ChatColor.DARK_AQUA + "HorseSpeed" + ChatColor.GRAY + "]"));

	}

}