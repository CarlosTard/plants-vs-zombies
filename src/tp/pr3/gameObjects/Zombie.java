package tp.pr3.gameObjects;

public abstract class Zombie extends GameObject {
	public Zombie(int frec,int damage, int life, String idStr, String name,int cont) {
		super(damage,life,idStr,name,frec,cont);
	}
	public void update() {
		boolean harmed = this.game.harmPlant(x, y-1, this.getDamage());
		if(harmed || this.game.checkZombie(x, y-1)) {
			this.nCiclo = game.getCycles() + this.frecuency;
		}else if(this.nCiclo <= this.game.getCycles() && this.y > 0) {
			--this.y;
			this.nCiclo = game.getCycles() + this.frecuency;
		}
		game.thereAreZombies();
		game.checkIfZombieWins(y);
	}
	public abstract Zombie getZombie();
	public Zombie parse(String Name) {
		if(Name.toLowerCase().equals(this.getId().toLowerCase()) 
			|| Name.toLowerCase().equals(this.getName().toLowerCase())) return getZombie();
		return null;
	}
	public String list() {
		return super.list() + " Speed: " + this.frecuency; 
	}
}
