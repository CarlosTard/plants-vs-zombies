package tp.pr3.factories;
import tp.pr3.gameObjects.Nuez;
import tp.pr3.gameObjects.Peashooter;
import tp.pr3.gameObjects.Petacereza;
import tp.pr3.gameObjects.Plant;
import tp.pr3.gameObjects.Sunflower;

public class PlantFactory {
	private static Plant[] availablePlants = {
		new Peashooter(),
		new Sunflower(),
		new Nuez(),
		new Petacereza()
	};
	public static Plant getPlant(String plantName){
		for(Plant p: availablePlants) {
			Plant com = p.parse(plantName);
			if(com != null) return com;
		}
		return null;
	}
	public static String listOfAvailablePlants() {
		StringBuilder str = new StringBuilder();
		for(Plant c:availablePlants) {
			str.append(c.list() + System.lineSeparator());
		}
		return str.toString();
	}
}