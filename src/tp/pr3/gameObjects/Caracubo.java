package tp.pr3.gameObjects;

public class Caracubo extends Zombie {
	public Caracubo() {
  		super(4,1,8,"W","Caracubo",1);
  	}
	
	public Zombie getZombie() {
		return new Caracubo();
	}

}
