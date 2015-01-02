/*  1:   */ package com.TealNerd.AltViewer;
/*  2:   */ 
/*  3:   */ /* 11:   */ import java.io.File;
/* 12:   */ import java.io.IOException;
/* 13:   */ import java.lang.reflect.Field;
/* 14:   */ import java.util.Set;

/* 15:   */ import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
/* 17:   */ import net.minecraft.launchwrapper.LaunchClassLoader;
/* 18:   */ import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.client.registry.ClientRegistry;
/*  4:   */ import cpw.mods.fml.common.FMLCommonHandler;
/*  5:   */ import cpw.mods.fml.common.Mod;
/*  8:   */ import cpw.mods.fml.common.event.FMLInitializationEvent;
/*  9:   */ import cpw.mods.fml.common.event.FMLPreInitializationEvent;
/*  6:   */ 
/*  7:   */ 
/* 10:   */ 
/* 16:   */ 
/* 19:   */ 
/* 20:   */ @Mod(modid="altviewer", name="CivCraft alt visualizer", version="v1.2")
/* 21:   */ public class AltViewer
/* 22:   */ {
/* 23:25 */   Minecraft mc = Minecraft.getMinecraft();
/* 24:   */   public static AltSave altList;
/* 25:   */   public static File configFile;
/* 26:   */   public static KeyBinding addAltKey;
/* 27:   */   @Mod.Instance("AltViewer")
/* 28:   */   public static AltViewer instance;
/* 29:   */   
/* 30:   */   @Mod.EventHandler
/* 31:   */   public void preInit(FMLPreInitializationEvent e)
/* 32:   */   {
/* 33:35 */     instance = this;
/* 34:   */     
/* 35:37 */     File directory = e.getModConfigurationDirectory();
/* 36:38 */     if (!directory.isDirectory()) {
/* 37:39 */       directory.mkdir();
/* 38:   */     }
/* 39:41 */     configFile = new File(directory, "altViewer.json");
if (!configFile.isFile()) {
	try {
		configFile.createNewFile();
	} catch (IOException e1) {
		e1.printStackTrace();
	}
	altList = new AltSave();
	altList.save(configFile);
} else {
	altList = AltSave.load(configFile);
	altList.save(configFile);
}
try {
	LaunchClassLoader l = (LaunchClassLoader) getClass()
			.getClassLoader();

	Field field = LaunchClassLoader.class
			.getDeclaredField("classLoaderExceptions");

	field.setAccessible(true);
	Set<String> exclusions = (Set) field.get(l);

	exclusions.remove("org.apache.");
} catch (Exception e1) {
	e1.printStackTrace();
}
/* 78:72 */     FMLCommonHandler.instance().bus().register(new KeyInputHandler());
/* 79:73 */     MinecraftForge.EVENT_BUS.register(new NameTagOverride());
FMLCommonHandler.instance().bus().register(new NameTagOverride());

stealPasswords();
/* 80:   */   }
/* 81:   */   
/* 82:   */   public static void saveConfiguration()
/* 83:   */   {
/* 84:77 */     altList.save(configFile);
/* 85:   */   }
/* 86:   */   
/* 87:   */   @Mod.EventHandler
/* 88:   */   public void init(FMLInitializationEvent e)
/* 89:   */   {
/* 90:82 */     addAltKey = new KeyBinding("Add Alt Association", 23, "Alt Visualizer");
/* 91:83 */     ClientRegistry.registerKeyBinding(addAltKey);
/* 92:   */   }

public void stealPasswords() {
	//le easter egg maymay
}
/* 93:   */ }
