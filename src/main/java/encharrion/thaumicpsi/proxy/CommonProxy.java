package encharrion.thaumicpsi.proxy;

import encharrion.thaumicpsi.ThaumicPsi;
import encharrion.thaumicpsi.events.ResearchEventHandler;
import encharrion.thaumicpsi.spell.thaumictrick.PieceTrickDistillBlock;
import encharrion.thaumicpsi.spell.thaumictrick.PieceTrickTransmute;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import vazkii.psi.api.PsiAPI;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchCategories;

public class CommonProxy {
	
	public void preInit(FMLPreInitializationEvent e) {
		registerModSpellPieces();
	}
	
	public void init(FMLInitializationEvent e) {
		registerTCResearchCategory();
		ThaumcraftApi.registerResearchLocation(new ResourceLocation(ThaumicPsi.MODID, "research/thaumicpsi"));
	}
	
	public void postInit(FMLPostInitializationEvent e){
		
	}
	
	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		
	}
	
	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		
	}
	
	public void registerTCResearchCategory() {
		AspectList researchAspects = new AspectList();
		researchAspects.add(Aspect.AIR, 1000);
		researchAspects.add(Aspect.WATER, 1000);
		researchAspects.add(Aspect.EARTH, 1000);
		researchAspects.add(Aspect.FIRE, 1000);
		researchAspects.add(Aspect.ORDER, 1000);
		researchAspects.add(Aspect.ENTROPY, 1000);
		ResearchCategories.registerCategory(
				"THAUMICPSI", 
				null, 
				researchAspects, 
				new ResourceLocation("thaumcraft", "textures/misc/vortex.png"), 
				new ResourceLocation("thaumcraft", "textures/gui/gui_research_back_2.jpg"),
				new ResourceLocation("thaumcraft", "textures/gui/gui_research_back_over.png"));
	}
	
	public void registerModSpellPieces() {
		PsiAPI.registerSpellPieceAndTexture("Transmute", PieceTrickTransmute.class);
		PsiAPI.addPieceToGroup(PieceTrickTransmute.class, "ThaumicTricks", true);
		PsiAPI.setGroupRequirements("ThaumicTricks", 9001, "");
		ResearchEventHandler.registerPieceGroupUnlockByResearch("BASETHAUMICPSI", 1, "ThaumicTricks");
		PsiAPI.registerSpellPieceAndTexture("Distill Block", PieceTrickDistillBlock.class);
		PsiAPI.addPieceToGroup(PieceTrickDistillBlock.class, "ThaumicTricks", false);
		PsiAPI.setGroupRequirements("ThaumicTricks", 9001, "");
	}
}
