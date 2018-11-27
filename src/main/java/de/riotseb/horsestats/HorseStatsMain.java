package de.riotseb.horsestats;

import de.riotseb.horsestats.commands.HorseStatsCommand;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

public class HorseStatsMain extends JavaPlugin {

	@Getter
	private static HorseStatsMain instance;

	public static final String PERM_HORSE_SPEED = "horsestats.use";

	@Override
	public void onEnable() {

		instance = this;

		getCommand("horsestats").setExecutor(new HorseStatsCommand());

		saveResource("messages.yml", false);

	}
}
