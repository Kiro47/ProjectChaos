package com.kiro.projchaos.customs;

import java.util.List;

import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;

import com.kiro.projchaos.customs.meleeattacks.WolfPuckMelee;
import com.kiro.projchaos.methods.NMSUtils;

import net.minecraft.server.v1_8_R3.EntityHuman;
import net.minecraft.server.v1_8_R3.EntityWolf;
import net.minecraft.server.v1_8_R3.EnumColor;
import net.minecraft.server.v1_8_R3.GenericAttributes;
import net.minecraft.server.v1_8_R3.PathfinderGoalFloat;
import net.minecraft.server.v1_8_R3.PathfinderGoalHurtByTarget;
import net.minecraft.server.v1_8_R3.PathfinderGoalLookAtPlayer;
import net.minecraft.server.v1_8_R3.PathfinderGoalSelector;
import net.minecraft.server.v1_8_R3.World;

public class WolfPuck extends EntityWolf	{
	
	@SuppressWarnings("unused")
	private final boolean isCustom;
	
	public WolfPuck(World world) {
		super(world);
		isCustom = false;
	}
	
	public WolfPuck(CraftWorld world) {
		super(world.getHandle());
		isCustom = false;
	}
	
	@SuppressWarnings("rawtypes")
	public WolfPuck(CraftWorld world, boolean isCustom) {
		super(world.getHandle());
		this.isCustom = isCustom;
		
		if (isCustom) {
			List goalB = (List) NMSUtils.getPrivateField("b", PathfinderGoalSelector.class, goalSelector);
			goalB.clear();

			List goalC = (List) NMSUtils.getPrivateField("c", PathfinderGoalSelector.class, goalSelector);
			goalC.clear();

			List targetB = (List) NMSUtils.getPrivateField("b", PathfinderGoalSelector.class, targetSelector);
			targetB.clear();

			List targetC = (List) NMSUtils.getPrivateField("c", PathfinderGoalSelector.class, targetSelector);
			targetC.clear();
			
			goalSelector.a(0, new PathfinderGoalFloat(this));
			goalSelector.a(2, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 8.0F));
			
			targetSelector.a(1, new PathfinderGoalHurtByTarget(this, true));
			
			getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(0.0D);
			getAttributeInstance(GenericAttributes.maxHealth).setValue(1000.0D);
			setCustomName(WolfPuckMelee.WOLF_NAME);
			setCustomNameVisible(true);
			setCollarColor(EnumColor.ORANGE);
			setSitting(true);
		}
	}

}
