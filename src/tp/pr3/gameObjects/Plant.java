package tp.pr3.gameObjects;

public abstract class Plant extends GameObject{
	private int cost;

	public Plant(int cost,int damage, int life, String idStr, String name,int frec,int cont) {
		super(damage,life,idStr,name,frec,cont);
		this.cost = cost;
	}
	public void update() {
		if(this.game.getCycles() >= this.nCiclo) {
			this.action();
			this.nCiclo = game.getCycles() + this.frecuency;
		}
	}
	public String list() {
		return super.list() + " Cost: " + this.getCost() + " suncoins";
	}
	public abstract Plant getPlant();
	public Plant parse(String Name) {
		if(Name.toLowerCase().equals(this.getId().toLowerCase()) 
			|| Name.toLowerCase().equals(this.getName().toLowerCase())) return getPlant();
		return null;
	}
	public int getCost() { 
		return this.cost;
	}
	public abstract void action();

}
