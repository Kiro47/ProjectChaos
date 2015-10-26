package com.kiro.projchaos.methods;

import net.minecraft.server.v1_8_R3.Entity;

public interface Modifier
{

	/**
	 * @param entity: the entity that this modifier is being applied to
	 *     This is where you should alter the entities AI
	 */
	void setEntity(Entity entity);

}