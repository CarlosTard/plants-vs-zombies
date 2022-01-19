package tp.pr3.gameObjects;


public class ZombieComun extends Zombie{
	
  	public ZombieComun() {
  		super(2,1,5,"Z","ZombieComun",1);
  	}
  	public Zombie getZombie() {
		return new ZombieComun();
	}
}
