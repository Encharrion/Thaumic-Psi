package encharrion.thaumicpsi.events;

import java.util.HashMap;

import encharrion.thaumicpsi.ThaumicPsi;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import thaumcraft.api.capabilities.ThaumcraftCapabilities;
import thaumcraft.api.research.ResearchEvent;
import vazkii.psi.common.core.handler.PlayerDataHandler;
import vazkii.psi.common.core.handler.PlayerDataHandler.PlayerData;

@EventBusSubscriber
public class ResearchEventHandler {
	/*This HashMap uses the research Key and Stage number as a key for what psi piece group should be unlocked by that research. 
	 *There is probably a better way to do this than embed a HashMap in a HashMap*/
	private static HashMap<HashMap<String, Integer>, String> registeredPsiResearch = new HashMap<HashMap<String, Integer>, String>();
	
	//This handles unlocking the appropriate Piece Group when a registered research is completed.
	@SubscribeEvent
	public static void onResearch(ResearchEvent.Research e) {
		int stage = ThaumcraftCapabilities.getKnowledge(e.getPlayer()).getResearchStage(e.getResearchKey());
		PlayerData psiPlayerData = PlayerDataHandler.get(e.getPlayer());
		ThaumicPsi.logger.info("ResearchKey:" + e.getResearchKey() + "  Stage:" + stage);
		for (HashMap<String, Integer> key : registeredPsiResearch.keySet()) {
			if (key.containsKey(e.getResearchKey()) && key.containsValue(stage)) {
				String pieceGroupToUnlock = registeredPsiResearch.get(key);
				if (!psiPlayerData.isPieceGroupUnlocked(pieceGroupToUnlock)) {
					ThaumicPsi.logger.info("Attempting to add Piece Group " + pieceGroupToUnlock);
					psiPlayerData.levelUp();
					psiPlayerData.unlockPieceGroup(pieceGroupToUnlock);
				}
			}
		}
	}
	
	/**Registers a Piece Group to be unlocked by the given stage of research
	 * 
	 * @param researchKey The name of the research needed
	 * @param stage The stage of the research needed
	 * @param pieceGroup The Piece Group to unlock
	 */
	public static void registerPieceGroupUnlockByResearch(String researchKey, int stage, String pieceGroup) {
		HashMap<String, Integer> research = new HashMap<String, Integer>();
		research.put(researchKey, stage);
		registeredPsiResearch.put(research, pieceGroup);
	}

}
