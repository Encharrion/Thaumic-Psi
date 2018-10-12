package encharrion.thaumicpsi.spell.trick;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import thaumcraft.api.items.ItemsTC;
import thaumcraft.api.aura.AuraHelper;
import vazkii.psi.api.spell.EnumSpellStat;
import vazkii.psi.api.spell.Spell;
import vazkii.psi.api.spell.SpellCompilationException;
import vazkii.psi.api.spell.SpellContext;
import vazkii.psi.api.spell.SpellMetadata;
import vazkii.psi.api.spell.SpellRuntimeException;
import vazkii.psi.api.spell.piece.PieceTrick;
import vazkii.psi.common.item.ItemCAD;

public class PieceTrickTransmute extends PieceTrick {
	
	public PieceTrickTransmute (Spell spell) {
		super(spell);
	}

	@Override
	public void addToMetadata(SpellMetadata meta) throws SpellCompilationException {
		super.addToMetadata(meta);
		
		meta.addStat(EnumSpellStat.POTENCY, 100);
		meta.addStat(EnumSpellStat.COST, 600);
		
	}
	
	@Override
	public Object execute(SpellContext context) throws SpellRuntimeException {
		AuraHelper.drainVis(context.caster.getEntityWorld(), context.caster.getPosition(), 1, false);
		ItemCAD.craft(context.caster, new ItemStack(Items.REDSTONE), new ItemStack(ItemsTC.salisMundus));
		return null;
	}
	
}
