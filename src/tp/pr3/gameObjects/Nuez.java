package tp.pr3.gameObjects;

public class Nuez extends Plant {

	public Nuez() {
		super(50,0,10,"N","Nuez",1,0);
		
	}
	public Plant getPlant() {
		return new Nuez();
	}
	@Override
	public void action() {
		
	}

}
