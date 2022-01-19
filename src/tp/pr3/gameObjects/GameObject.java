package tp.pr3.gameObjects;

import tp.pr3.gameLogic.Game;

public abstract class GameObject {
	protected int x;
	protected int y;
	private int life;
	protected boolean dead;
	private String idStr;
	private String name;
	private int cont;
	protected int damage;
	protected Game game;
	protected int nCiclo;
	protected int frecuency;
	
	public GameObject(int damage,int life,String idStr,String name,int frec,int cont) {
		this.life = life;
		this.idStr = idStr;
		this.dead = false;
		this.cont = cont;
		this.name = name;
		this.damage = damage; 
		this.frecuency = frec;
	}
	
	public void harm(int damage) {
		this.life -= damage;
		if(this.life <= 0) this.dead = true;
	}
	public boolean isDead() {
		return this.dead;
	}
	public String getName() {
		return this.name;
	}
	public int getDamage() {
		return this.damage;
	}
	public int getX() {
		return this.x;
	}
	public int getY() {
		return this.y;
	}
	public String getId() {
		return this.idStr;
	}
	public int getLife() {
		return this.life;
	}
	public boolean check(int x, int y) {
		return this.x == x && this.y == y && !this.dead;
	}
	public String toString() {
		return  " " + this.idStr + " [" + this.life + "] ";
	}
	public String toDebugString() {
		return this.idStr + "[l:" + this.life + ",x:" + this.x + ",y:" 
			 + this.y + ",t:" + (this.nCiclo - this.game.getCycles()) + "]"; 
	}
	public String list() {
		return "[" + this.getId() + "]" + this.getName() + " Harm: " 
				+ this.getDamage() + " Life: " + this.getLife();
	}
	public String store(){
		int actionLeft = this.nCiclo - this.game.getCycles();
		return this.getId() + ':' + this.life + ':' + this.x + ':' + this.y + ':' + actionLeft;
	}
	public void setAttrib(int x,int y,Game game) {
		this.x = x;this.y = y;
		this.game = game;
		this.nCiclo = game.getCycles() + this.frecuency - this.cont;
	}
	public void setAttrib(int x,int y, Game game,int life,int nCiclo) {
		this.x = x;this.y = y;
		this.game = game;
		this.life = life;
		this.nCiclo = nCiclo;
	}
	
	public abstract void update();

	public void setDead(boolean dead) {
		this.dead = dead;
		
	}
}
