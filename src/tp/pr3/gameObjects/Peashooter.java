package tp.pr3.gameObjects;

public class Peashooter extends Plant{
	
	public Peashooter() {
		super(50,1,3,"P","Peashooter",1,1);
	}
	@Override
	public void action() {
		this.game.shoot(this.x, this.y, this.damage);
	}
	public Plant getPlant() {
		return new Peashooter();
	}
}