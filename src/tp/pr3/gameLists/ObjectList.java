package tp.pr3.gameLists;

import tp.pr3.gameObjects.GameObject;
import tp.pr3.exceptions.FileContentsException;
import tp.pr3.factories.PlantFactory;
import tp.pr3.factories.ZombieFactory;
import tp.pr3.gameLogic.Game;

public class ObjectList {
	protected GameObject[] arrayObjects;
	protected int cont;
	protected int tam;

	public ObjectList(int dimX, int dimY) {
		this.arrayObjects = new GameObject[2 * dimX * dimY];
		this.cont = 0;
		this.tam = 2 * dimX * dimY;
	}

	public ObjectList(String[] Strings, Game game,int nCiclos, int tam, boolean isPlant) throws FileContentsException {
		this.tam = 2 * tam;
		this.cont = 0;
		this.arrayObjects = new GameObject[2*tam];
		for (String s : Strings) {
			String[] attrib = s.trim().split(":");
			GameObject p;
			if(attrib.length != 5)throw new FileContentsException("Load failed:Invalid attrib length:" + s);
			if (isPlant)
				p = PlantFactory.getPlant(attrib[0]);
			else
				p = ZombieFactory.getZombie(attrib[0]);
			if (p == null)
				throw new FileContentsException("Load failed: Invalid Object Name:" + attrib[0]);
			try {
				int life = Integer.parseInt(attrib[1]);
				int x = Integer.parseInt(attrib[2]);
				int y = Integer.parseInt(attrib[3]);
				int t = Integer.parseInt(attrib[4]);
				p.setAttrib(x, y, game, life, t + nCiclos);
				if(check(x,y) || !isPlant && game.checkPlant(x, y))
					throw new FileContentsException("LoadFailed: there are two Objects in the same cell");
				if(!game.insideBoard(x, y)) 
					throw new FileContentsException("LoadFailed: position must be inside board: " + x + ", " + y);
				if(life < 0) p.setDead(true);
			} catch (NumberFormatException e) {
				throw new FileContentsException("LoadFailed: Object attrib must be integers: " + s);
			}
			this.arrayObjects[this.cont] = p;
			++this.cont;
		}
	}

	private void redimensionar() {
		GameObject auxList[] = this.arrayObjects;
		this.arrayObjects = new GameObject[2 * this.tam];
		for (int i = 0; i < this.cont; ++i) {
			this.arrayObjects[i] = auxList[i];
		}
		this.tam *= 2;
	}

	public void add(GameObject pea) {
		if (cont == tam) {
			redimensionar();
		}
		this.arrayObjects[cont] = pea;
		++cont;
	}

	public void deDead() {
		GameObject auxList[] = this.arrayObjects;
		this.arrayObjects = new GameObject[this.tam];
		int k = 0;
		for (int i = 0; i < this.cont; ++i) {
			if (!auxList[i].isDead()) {
				this.arrayObjects[k] = auxList[i];
				++k;
			}
		}
		this.cont = k;
	}

	public void update() {
		for (int i = 0; i < this.cont; ++i) {
			if (!this.arrayObjects[i].isDead())
				this.arrayObjects[i].update();
		}
		deDead();
	}

	public boolean check(int x, int y) {
		boolean ok = false;
		for (int i = 0; i < this.cont && !ok; ++i) {
			ok = arrayObjects[i].check(x, y);
		}
		return ok;
	}

	public String toString(int x, int y) {
		boolean ok = false;
		int i;
		for (i = 0; i < this.cont && !ok;) {
			if (arrayObjects[i].check(x, y))
				ok = true;
			else
				++i;
		}
		if (ok)
			return arrayObjects[i].toString();
		else
			return "";
	}

	public String toDebugString(int i) {
		if (0 <= i && i < this.cont)
			return arrayObjects[i].toDebugString();
		else
			return "";
	}

	public boolean harm(int x, int y, int damage) {
		boolean harmed = false;
		for (int i = 0; i < this.cont && !harmed; ++i) {
			if (arrayObjects[i].check(x, y)) {
				arrayObjects[i].harm(damage);
				harmed = true;
			}
		}
		return harmed;
	}

	public int getSize() {
		int numLiving = 0;
		for(int i = 0; i < this.cont;++i) {
			if(!this.arrayObjects[i].isDead()) ++numLiving;
		}
		return numLiving;
	}

	public String externalise() {
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < this.cont; ++i) {
			str.append(this.arrayObjects[i].store());
			if (i < this.cont - 1)
				str.append(", ");
		}
		return str.toString();
	}

}
