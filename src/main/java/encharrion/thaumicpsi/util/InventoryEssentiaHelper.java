package encharrion.thaumicpsi.util;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.blocks.essentia.BlockJarItem;

public class InventoryEssentiaHelper {
	
	/**
	 * Puts given essentia into valid storage items in the player's inventory. TODO: Warded jars with the appropriate essentia will be filled first,
	 * then empty jars, then phials, then quartz slivers will be turned into vis crystals.
	 * @param aspectsIn the essentia to be stored
	 * @param player the player who is storing the essentia
	 * @return leftover essentia that could not be stored in inventory items
	 */
	public static AspectList putAspectsInInv (AspectList aspectsIn, EntityPlayer player) {
		
		for (ItemStack stack : player.inventory.mainInventory) {
			if (stack.getItem() instanceof BlockJarItem) {
				BlockJarItem container = (BlockJarItem) stack.getItem();
				container.setAspects(stack, aspectsIn);
			}
		}
		
		AspectList remainingAspects = new AspectList();
		return remainingAspects;
	}
}
