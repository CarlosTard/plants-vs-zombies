package tp.pr3.factories;
import java.util.Random;

import tp.pr3.gameObjects.Caracubo;
import tp.pr3.gameObjects.Deportista;
import tp.pr3.gameObjects.Nuez;
import tp.pr3.gameObjects.Peashooter;
import tp.pr3.gameObjects.Petacereza;
import tp.pr3.gameObjects.Plant;
import tp.pr3.gameObjects.Sunflower;
import tp.pr3.gameObjects.Zombie;
import tp.pr3.gameObjects.ZombieComun;

public class ZombieFactory {
	private static Zombie[] availableZombies = {
		new Caracubo(),
		new Deportista(),
		new ZombieComun()
	};
	public static String listOfAvailableZombies() {
		StringBuilder str = new StringBuilder();
		for(Zombie c:availableZombies) {
			str.append(c.list() + System.lineSeparator());
		}
		return str.toString();
	}
	public static Zombie getZombie(String plantName){
		for(Zombie p: availableZombies) {
			Zombie com = p.parse(plantName);
			if(com != null) return com;
		}
		return null;
	}
	public static String getRandomZombie(Random rand) {
		return availableZombies[rand.nextInt(availableZombies.length)].getName();	
		
	}
	
}