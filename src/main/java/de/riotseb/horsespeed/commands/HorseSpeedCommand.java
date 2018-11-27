package de.riotseb.horsespeed.commands;

import de.riotseb.horsespeed.HorseSpeedMain;
import de.riotseb.horsespeed.util.Message;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.AbstractHorse;
import org.bukkit.entity.Player;

import java.text.DecimalFormat;

public class HorseSpeedCommand implements CommandExecutor {

	private static final ThreadLocal<DecimalFormat> DECIMAL_FORMAT = ThreadLocal.withInitial(() -> new DecimalFormat("##.##"));

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if (!(sender instanceof Player)) {
			sender.sendMessage(Message.PLAYER_ONLY.get());
			return true;
		}

		if (!sender.hasPermission(HorseSpeedMain.PERM_HORSE_SPEED)) {
			sender.sendMessage(Message.NO_PERMISSIONS.get());
			return true;
		}

		Player player = (Player) sender;

		AbstractHorse ridingHorse = (AbstractHorse) player.getNearbyEntities(1, 2, 1)
				.stream()
				.filter(entity -> entity.getPassengers().contains(player))
				.filter(entity -> entity instanceof AbstractHorse)
				.findFirst().orElse(null);

		if (ridingHorse == null) {
			player.sendMessage(Message.NOT_RIDING_A_HORSE.get());
			return true;
		}

		player.sendMessage(Message.STATS.get());
		player.sendMessage(Message.SPEED.get().replace("%value%", DECIMAL_FORMAT.get().format(getHorseSpeed(ridingHorse))));
		player.sendMessage(Message.JUMP_STRENGTH.get().replace("%value%", DECIMAL_FORMAT.get().format(getHorseJumpHeight(ridingHorse))));

		return true;
	}

	private double getHorseSpeed(AbstractHorse entity) {
		double rawHorseSpeed = entity.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getBaseValue();
		return rawHorseSpeed * 43.1;
	}

	private double getHorseJumpHeight(AbstractHorse entity) {
		double rawHorseJumpHeight = entity.getAttribute(Attribute.HORSE_JUMP_STRENGTH).getBaseValue();

		return -0.1817584952 * Math.pow(rawHorseJumpHeight, 3) +
				3.689713992 * Math.pow(rawHorseJumpHeight, 2) +
				2.128599134 * rawHorseJumpHeight - 0.343930367;
	}

}
