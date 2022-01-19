package tp.pr3.gameObjects;

public class Sunflower extends Plant{
	private static final int suns = 10;
	
	public Sunflower() {
		super(20,0,1,"S","Sunflower",2,0);
	}

	@Override
	public void action() {
		this.game.addSuns(suns);
	}
	public Plant getPlant() {
		return new Sunflower();
	}
	
}