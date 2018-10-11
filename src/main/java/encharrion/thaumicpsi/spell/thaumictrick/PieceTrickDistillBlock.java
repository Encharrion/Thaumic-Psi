package encharrion.thaumicpsi.spell.thaumictrick;

import encharrion.thaumicpsi.util.InventoryEssentiaHelper;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.items.ItemGenericEssentiaContainer;
import thaumcraft.common.blocks.essentia.BlockJarItem;
import vazkii.psi.api.internal.Vector3;
import vazkii.psi.api.spell.EnumSpellStat;
import vazkii.psi.api.spell.Spell;
import vazkii.psi.api.spell.SpellCompilationException;
import vazkii.psi.api.spell.SpellContext;
import vazkii.psi.api.spell.SpellMetadata;
import vazkii.psi.api.spell.SpellParam;
import vazkii.psi.api.spell.SpellRuntimeException;
import vazkii.psi.api.spell.param.ParamVector;
import vazkii.psi.api.spell.piece.PieceTrick;

public class PieceTrickDistillBlock extends PieceTrick {
	
	SpellParam position;
	
	public PieceTrickDistillBlock(Spell spell){
		super(spell);
	}
	
	@Override
	public void initParams() {
		addParam(position = new ParamVector(SpellParam.GENERIC_NAME_POSITION, SpellParam.BLUE, false, false));
	}
	
	@Override
	public void addToMetadata(SpellMetadata meta) throws SpellCompilationException {
		super.addToMetadata(meta);
		
		meta.addStat(EnumSpellStat.POTENCY, 100);
		meta.addStat(EnumSpellStat.COST, 20);
		
	}
	
	@Override
	public Object execute(SpellContext context) throws SpellRuntimeException{
		Vector3 positionVal = this.<Vector3>getParamValue(context, position);
		
		if(positionVal == null)
			throw new SpellRuntimeException(SpellRuntimeException.NULL_VECTOR);
		if(!context.isInRadius(positionVal))
			throw new SpellRuntimeException(SpellRuntimeException.OUTSIDE_RADIUS);
		
		BlockPos blockPos = new BlockPos(positionVal.x, positionVal.y, positionVal.z);
		Block block = context.caster.getEntityWorld().getBlockState(blockPos).getBlock();
		ItemStack itemStack = new ItemStack(block);
		AspectList blockAspects = new AspectList(itemStack);
		
		InventoryEssentiaHelper.putAspectsInInv(blockAspects, context.caster);
		//fillJar(context.caster, context.targetSlot, blockAspects);
		
		return null;
	}

	private void fillJar(EntityPlayer player, int slot, AspectList in) {
		ItemStack stack = player.inventory.getStackInSlot(slot);
		if(stack.getItem() instanceof BlockJarItem) {
			BlockJarItem container = (BlockJarItem) stack.getItem();
			//AspectList aspects = container.getAspects(stack);
			Aspect[] aspect = in.getAspects();
			if (aspect.length > 0) {
				container.setAspects(stack, new AspectList().add(aspect[0], in.getAmount(aspect[0])));
			}
		}
	}
}
