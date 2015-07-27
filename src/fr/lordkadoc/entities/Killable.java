package fr.lordkadoc.entities;

public interface Killable {
	
	/**
	 * @return true if the entity is alive (as more than 0 health), false otherwise
	 */
	public abstract boolean isAlive();

}
