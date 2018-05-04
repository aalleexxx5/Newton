package common.data;

/**
 * Used by the {@link common.services.Collidable} to indicate their intent to each other when colliding.
 */
public enum Hostility {
	KILLS_PLAYER, KILLS_ENEMY, PASSIVE, MOVER, ITEM, NO_EFFECT
}
