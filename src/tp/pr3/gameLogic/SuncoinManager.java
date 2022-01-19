package tp.pr3.gameLogic;

import tp.pr3.gameObjects.Plant;

public class SuncoinManager {
	private int suncoins;
			
	public SuncoinManager() {
		this.suncoins = 50;
	}
	public int getCoins() {
		return this.suncoins;
	}
	public void addCoins(int coins) {
		this.suncoins += coins;
	}
	public void setCoins(int coins) {
		this.suncoins = coins;
	}
	public boolean buy(Plant p) {
		boolean ok = this.suncoins - p.getCost() >= 0;
		if(ok) this.suncoins -= p.getCost();
		return ok;	
	}
}
