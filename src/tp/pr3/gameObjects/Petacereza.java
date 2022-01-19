package tp.pr3.gameObjects;

public class Petacereza extends Plant {
	public Petacereza() {
		super(50, 10, 2, "C", "Petacereza",2,0);
	}

	@Override
	public void action() {
		this.game.explode(this.x,this.y,this.damage);
		this.dead = true;
	}

	public Plant getPlant() {
		return new Petacereza();
	}
}
