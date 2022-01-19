package tp.pr3.gameLogic;

import java.util.Random;

import tp.pr3.factories.PlantFactory;
import tp.pr3.factories.ZombieFactory;
import tp.pr3.gameLists.ObjectList;
import tp.pr3.printers.GameDebugPrinter;
import tp.pr3.printers.GamePrinter;
import tp.pr3.printers.GameReleasePrinter;
import tp.pr3.gameObjects.GameObject;
import tp.pr3.gameObjects.Peashooter;
import tp.pr3.gameObjects.Plant;
import tp.pr3.gameObjects.Sunflower;
import tp.pr3.gameObjects.Zombie;
import tp.pr3.printers.BoardPrinter;
import java.io.*;
import tp.pr3.exceptions.CommandExecuteException;
import tp.pr3.exceptions.FileContentsException;

import java.lang.Exception;
import tp.pr3.util.MyStringUtils;

public class Game {
	private ObjectList plantList;
	private ObjectList zombieList;
	private long seed;
	private Random rand;
	private ZombieManager zombieManager;
	private SuncoinManager suncoinManager;
	private Level level;
	private int numCycle;
	private GamePrinter gamePrinter;
	public static final int dimX = 4;
	public static final int dimY = 8;
	private boolean userWins, zombieWins;
	private boolean areThereZombies;
	private static final int[] vecx = { -1, -1, -1, +1, +1, +1, 0, 0 };
	private static final int[] vecy = { -1, 0, 1, -1, 0, 1, -1, 1 };
	private boolean exit;

	public Game(long seed, Level level) {
		this.exit = false;
		this.areThereZombies = false;
		this.userWins = false;
		this.zombieWins = false;
		this.gamePrinter = new GameReleasePrinter(this.getDimX(), this.getDimY());
		this.numCycle = 0;
		this.seed = seed;
		this.rand = new Random(seed);
		this.level = level;
		this.plantList = new ObjectList(this.dimX, this.dimY);
		this.zombieList = new ObjectList(this.dimX, this.dimY);
		this.zombieManager = new ZombieManager(level, this.rand);
		this.suncoinManager = new SuncoinManager();
	}

	public void reset() {
		this.numCycle = 0;
		this.rand = new Random(seed);
		this.plantList = new ObjectList(this.dimX, this.dimY);
		this.zombieList = new ObjectList(this.dimX, this.dimY);

		this.zombieManager = new ZombieManager(level, this.rand);
		this.suncoinManager = new SuncoinManager();
	}

	public boolean isEmptyCell(int x, int y) {
		return !this.plantList.check(x, y) && !this.zombieList.check(x, y);
	}

	public void update() {
		if (this.zombieManager.isZombieAdded())
			this.addZombies();
		this.plantList.update();
		this.areThereZombies = false;
		this.zombieList.update();
		this.plantList.deDead();
		this.isThereWinner();

		this.numCycle++;

	}

	private void addZombies() {
		// comprueba si la ultima columna libre
		boolean ok = false;
		for (int i = 0; i < this.dimX && !ok; ++i) {
			ok = isEmptyCell(i, this.dimY - 1);
		}
		// incluye los zombies por salir si columna libre
		if (ok) {
			int fil = this.rand.nextInt(this.dimX);
			while (!isEmptyCell(fil, this.dimY - 1))
				fil = this.rand.nextInt(4);
			String zomb = ZombieFactory.getRandomZombie(rand);
			Zombie z = ZombieFactory.getZombie(zomb);
			this.addZombie(z, fil, this.dimY - 1);
			this.zombieManager.added();
		}
	}

	private void isThereWinner() {
		this.userWins = this.zombieManager.getZombies() == 0 && !this.areThereZombies;
	}

	public String cellToString(int x, int y) {
		return this.plantList.toString(x, y) + this.zombieList.toString(x, y);
	}

	public boolean add(Plant plnt, int x, int y) {
		plnt.setAttrib(x, y, this);
		if (suncoinManager.buy(plnt)) {
			this.plantList.add(plnt);
			;
			return true;
		} else {
			return false;
		}
	}

	public String toString() {
		return this.gamePrinter.printGame(this);
	}

	public void addZombie(Zombie z, int x, int y) {
		z.setAttrib(x, y, this);
		this.zombieList.add(z);
	}

	public void shoot(int x, int y, int damage) {
		boolean harmed = false;
		for (int i = y + 1; i < this.dimY && !harmed; ++i) {
			harmed = this.harmZombie(x, i, damage);
		}
	}

	public void explode(int x, int y, int damage) {
		for (int i = 0; i < vecx.length; ++i)
			if (insideBoard(x + vecx[i], y + vecy[i]))
				this.harmZombie(x + vecx[i], y + vecy[i], damage);
	}

	public void addSuns(int suns) {
		this.suncoinManager.addCoins(suns);
	}

	public String cellToDebugString(int i) {
		if (i < this.plantList.getSize()) {
			return this.plantList.toDebugString(i);
		} else
			return this.zombieList.toDebugString(i - this.plantList.getSize());
	}

	public boolean harmZombie(int x, int y, int damage) {
		return this.zombieList.harm(x, y, damage);
	}

	public boolean harmPlant(int x, int y, int damage) {
		return this.plantList.harm(x, y, damage);
	}

	public boolean checkPlant(int x, int y) {
		return this.plantList.check(x, y);
	}

	public boolean checkZombie(int x, int y) {
		return this.zombieList.check(x, y);
	}

	/**
	 * 
	 * @param x
	 *            cell x coordinate
	 * @param y
	 *            cell y coordinate
	 * @return true if x,y cell is inside the board. Returns false otherwise
	 */
	public boolean insideBoard(int x, int y) {
		return x >= 0 && y >= 0 && x < this.dimX && y < this.dimY;
	}

	public boolean plantInsideBoard(int x, int y) {
		return insideBoard(x, y) && y < this.dimY - 1;
	}

	public long getSeed() {
		return this.seed;
	}

	public int getCoins() {
		return this.suncoinManager.getCoins();
	}

	public void setPrinter(String printer) {
		switch (printer.toLowerCase()) {
		case "r":
		case "release":
			this.gamePrinter = new GameReleasePrinter(this.getDimX(), this.getDimY());
			break;
		case "d":
		case "debug":
			this.gamePrinter = new GameDebugPrinter(this.getDimX(), this.getDimY());
			break;
		}
	}

	public int getRemainingZombies() {
		return this.zombieManager.getZombies();
	}

	public int getCycles() {
		return this.numCycle;
	}

	public boolean userWins() {
		return this.userWins;
	}

	public boolean zombieWins() {
		return this.zombieWins;
	}

	public void checkIfZombieWins(int y) {
		if (y == 0)
			this.zombieWins = true;
	}

	public void thereAreZombies() {
		this.areThereZombies = true;
	}

	public void setExit() {
		this.exit = true;
	}

	public boolean isExit() {
		return this.exit;
	}

	public boolean isFinished() {
		return this.exit || this.zombieWins || this.userWins;
	}

	public String getLevel() {
		return this.level.toString();
	}

	public int getDimX() {
		return this.dimX;
	}

	public int getDimY() {
		return this.dimY;
	}

	public int getNumberLiving() {
		return this.plantList.getSize() + this.zombieList.getSize();
	}

	public String getInfo() {
		return "Number of cycles: " + this.getCycles() + "\n" + "Sun coins: " + this.getCoins() + "\n"
				+ "Remaining zombies: " + this.getRemainingZombies() + "\n";
	}

	public String getDebugInfo() {
		return "Random seed used: " + this.getSeed() + "\n" + "Number of cycles: " + this.getCycles() + "\n"
				+ "Sun coins: " + this.getCoins() + "\n" + "Remaining zombies: " + this.getRemainingZombies() + "\n"
				+ "Level: " + this.getLevel();
	}

	public String externalise() {
		return "cycle: " + this.numCycle + "\n" + "sunCoins: " + this.getCoins() + "\n" + "level: "
				+ this.level.toString() + "\n" + "remZombies: " + this.getRemainingZombies() + "\n" + "plantList: "
				+ this.plantList.externalise() + "\n" + "zombieList: " + this.zombieList.externalise();
	}

	private int loadCycle(BufferedReader reading) throws FileContentsException {
		int cycle;
		try {
			cycle = Integer.parseInt(MyStringUtils.loadLine(reading, "cycle", false)[0]);
		} catch (NumberFormatException exc) {
			throw new FileContentsException("The number of cycles must be a number!");
		} catch (IOException e) {
			throw new FileContentsException("Invalid File");
		}

		if (cycle < 0)
			throw new FileContentsException("You cannot have a negative number of cycles!");
		return cycle;
	}

	private int loadCoins(BufferedReader reading) throws FileContentsException {
		int coins;
		try {
			coins = Integer.parseInt(MyStringUtils.loadLine(reading, "sunCoins", false)[0]);
		} catch (NumberFormatException exc) {
			throw new FileContentsException("Your suncoins must be a number!");
		} catch (IOException e) {
			throw new FileContentsException("Invalid File");
		}

		if (coins < 0)
			throw new FileContentsException("You cannot have a negative number of suncoins!");
		return coins;
	}

	private Level loadLevel(BufferedReader reading) throws FileContentsException {
		Level level;
		try {
			level = Level.parse(MyStringUtils.loadLine(reading, "level", false)[0].toLowerCase());
		} catch (IOException e) {
			throw new FileContentsException("Invalid File");
		}
		if (level == null)
			throw new FileContentsException("You have stored an invalid level!");
		return level;
	}

	private int loadRemZombies(BufferedReader reading) throws FileContentsException {
		int remZombies;
		try {
			remZombies = Integer.parseInt(MyStringUtils.loadLine(reading, "remZombies", false)[0]);
		} catch (NumberFormatException exc) {
			throw new FileContentsException("remZombies must be a number!");
		} catch (IOException e) {
			throw new FileContentsException("Invalid File");
		}
		if (remZombies < 0)
			throw new FileContentsException("remZombies must be positive!");
		return remZombies;
	}

	private ObjectList loadObjects(BufferedReader reading, int cycle, String nameList, boolean isPlant)
			throws FileContentsException {
		String[] plantStrings;
		try {
			plantStrings = MyStringUtils.loadLine(reading, nameList, true);
		} catch (IOException e) {
			throw new FileContentsException("Invalid File");
		}
		if (plantStrings.length > this.dimX * (this.dimY)) {
			throw new FileContentsException("invalid " + nameList + " length");
		}
		return new ObjectList(plantStrings, this, cycle, this.dimY * this.dimX, isPlant);

	}

	public void load(BufferedReader reading) throws FileContentsException {
		ObjectList plantAux = this.plantList;
		ObjectList zombieAux = this.zombieList;
		int cycle = loadCycle(reading);
		int coins = loadCoins(reading);
		Level level = loadLevel(reading);
		int remZombies = loadRemZombies(reading);
		try {
			this.plantList = loadObjects(reading, cycle, "plantList", true);
			this.zombieList = loadObjects(reading, cycle, "zombieList", false);
		} catch (FileContentsException e) {
			this.plantList = plantAux;
			this.zombieList = zombieAux;
			throw new FileContentsException(e.getMessage());
		}
		this.level = level;
		this.numCycle = cycle;
		this.suncoinManager.setCoins(coins);
		this.zombieManager.setZombiesLeft(remZombies);
	}
}
