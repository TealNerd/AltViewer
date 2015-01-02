/*  1:   */ package com.TealNerd.AltViewer;
/*  2:   */ 
/*  3:   */ /*  5:   */ import net.minecraft.client.Minecraft;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
/*  4:   */ 
/*  6:   */ 
/*  7:   */ 
/*  8:   */ public class KeyInputHandler
/*  9:   */ {
/* 10: 9 */   Minecraft mc = Minecraft.getMinecraft();
/* 11:   */   
/* 12:   */   @SubscribeEvent
/* 13:   */   public void onKeyInput(InputEvent.KeyInputEvent e)
/* 14:   */   {
/* 15:13 */     if (AltViewer.addAltKey.getIsKeyPressed()) {
/* 16:14 */       this.mc.displayGuiScreen(new GuiAssociations(this.mc.currentScreen));
/* 17:   */     }
/* 18:   */   }
/* 19:   */ }



/* Location:           C:\Users\Jacob\Documents\AltFormatter\[1.7.10]AltViewer-1.0.0\

 * Qualified Name:     com.TealNerd.AltViewer.KeyInputHandler

 * JD-Core Version:    0.7.0.1

 */
