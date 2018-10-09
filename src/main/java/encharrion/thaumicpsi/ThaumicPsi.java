package encharrion.thaumicpsi;

import org.apache.logging.log4j.Logger;

import encharrion.thaumicpsi.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = ThaumicPsi.MODID, name = ThaumicPsi.NAME, version = ThaumicPsi.VERSION, dependencies = ThaumicPsi.DEPENDENCIES)
public class ThaumicPsi {
	
	public static final String MODID = "thaumicpsi";
	public static final String NAME = "Thaumic Psi";
	public static final String VERSION = "0.0.1";
	public static final String DEPENDENCIES = "required-after:thaumcraft;required-after:psi";
	
	@SidedProxy(clientSide = "encharrion.thaumicpsi.proxy.ClientProxy", serverSide = "encharrion.thaumicpsi.proxy.ServerProxy")
	public static CommonProxy proxy;
	
	@Mod.Instance
	public static ThaumicPsi instance;
	
	public static Logger logger;
	
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent e) {
		logger  = e.getModLog();
		proxy.preInit(e);
	}
	
	@Mod.EventHandler
	public void init(FMLInitializationEvent e) {
		proxy.init(e);
	}
	
	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent e) {
		proxy.postInit(e);
	}
	
	
}
