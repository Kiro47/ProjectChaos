package com.kiro.projchaos.methods;

import com.kiro.projchaos.nms.ILivingEntity;

public interface IAgeable extends ILivingEntity
{

	boolean isBaby();

	void setBaby(boolean flag);

}