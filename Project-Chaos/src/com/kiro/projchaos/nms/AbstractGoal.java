package com.kiro.projchaos.nms;

import net.minecraft.server.v1_8_R3.EntityInsentient;
import net.minecraft.server.v1_8_R3.PathfinderGoal;

public abstract class AbstractGoal extends PathfinderGoal
{

	protected EntityInsentient entity;

	protected AbstractGoal(EntityInsentient entity)
	{
		this.entity = entity;
	}

	@Override
	public boolean a() // should operate
	{
		return canOperate();
	}

	@Override
	public boolean b()// can operate
	{
		return continueOperating();
	}

	@Override
	public boolean i()
	{
		return super.i();
	}

	@Override
	public void c() // setupGoal
	{
		goalSetup();
	}

	@Override
	public void d() // clearGoal
	{
		clearGoal();
	}

	@Override
	public void e() // goalTick
	{
		goalTick();
	}

	public abstract boolean canOperate();

	public boolean continueOperating()
	{
		return a();
	}

	public void goalSetup()
	{

	}

	public void clearGoal()
	{

	}

	public void goalTick()
	{

	}
}