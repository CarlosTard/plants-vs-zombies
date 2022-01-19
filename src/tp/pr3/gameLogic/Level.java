package tp.pr3.gameLogic;

public enum Level {
	EASY(3, 0.1f), HARD(5, 0.2f), INSANE(10, 0.3f);
	private int numZombies;
	private float frequency;

	Level(int numZombies, float frequency) {
		this.numZombies = numZombies;
		this.frequency = frequency;
	}

	public static Level parse(String args) {
		for (Level level : Level.values())
			if (level.name().equalsIgnoreCase(args))
				return level;
		return null;
	}

	public String toString() {
		return this.name();
	}

	public static String all(String separator) {
		StringBuilder sb = new StringBuilder();
		for (Level level : Level.values())
			sb.append(level.name() + separator);
		String allLevels = sb.toString();
		return allLevels.substring(0, allLevels.length() - separator.length());
	}

	public int getnumZombies() {
		return numZombies;
	}

	public float getFrequency() {
		return this.frequency;
	}
}
