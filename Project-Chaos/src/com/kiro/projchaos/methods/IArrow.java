package com.kiro.projchaos.methods;

public interface IArrow extends IProjectile
{

	void setKnockbackStrength(int val);

	void setCritical(boolean flag);

	boolean isCritical();

	boolean isInGround();

	double getDamage();

	void setDamage(double damage);

}