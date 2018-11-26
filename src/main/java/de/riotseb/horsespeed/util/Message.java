package de.riotseb.horsespeed.util;

public enum Message {

	PLAYER_ONLY("player only"),
	NO_PERMISSIONS("no permissions"),
	NOT_RIDING_A_HORSE("not riding a horse"),
	STATS("stats");

	private String key;

	Message(String key) {
		this.key = key;
	}

	public String get() {
		return MessageHandler.getMessage(key);
	}

}
