package tp.pr3.gameObjects;

public class Deportista extends Zombie {
	public Deportista() {
  		super(1,1,2,"X","Deportista",0);
  	}

	public Zombie getZombie() {
		return new Deportista();
	}
}
