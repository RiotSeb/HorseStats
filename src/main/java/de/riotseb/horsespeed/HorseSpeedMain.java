package de.riotseb.horsespeed;

import de.riotseb.horsespeed.commands.HorseSpeedCommand;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

public class HorseSpeedMain extends JavaPlugin {

	@Getter
	private static HorseSpeedMain instance;

	public static final String PERM_HORSE_SPEED = "horsespeed.use";

	@Override
	public void onEnable() {

		instance = this;

		getCommand("horsespeed").setExecutor(new HorseSpeedCommand());

		saveResource("messages.yml", false);

	}
}
