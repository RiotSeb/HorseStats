package de.riotseb.horsespeed.util;

import de.riotseb.horsespeed.HorseSpeedMain;
import lombok.experimental.UtilityClass;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

@UtilityClass
public class MessageHandler {

	private static File messageFile = new File(HorseSpeedMain.getInstance().getDataFolder() + File.separator + "messages.yml");
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