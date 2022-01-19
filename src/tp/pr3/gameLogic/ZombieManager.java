package tp.pr3.gameLogic;
import java.util.Random;

public class ZombieManager { 
	private Random rand;
	private int zombiesLeft;
	private float freq;
	public ZombieManager(Level level,Random rand) {
		this.rand = rand;
		this.zombiesLeft = level.getnumZombies();
		this.freq = level.getFrequency();
	}

	public boolean isZombieAdded() {
		if(this.zombiesLeft > 0 && this.rand.nextDouble() <= this.freq) 
			return true;
		return false;
	}
	//game has successfully added zombie 
	public void added() {
		this.zombiesLeft--;
	}
	
	public int getZombies() {
		return this.zombiesLeft;
	}
	public void setZombiesLeft(int zombies) {
		this.zombiesLeft = zombies;
	}
}
