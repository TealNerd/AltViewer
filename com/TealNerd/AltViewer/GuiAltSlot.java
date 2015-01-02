/*  1:   */ package com.TealNerd.AltViewer;
/*  2:   */ 
/*  3:   */ import java.awt.Color;

import net.minecraft.client.Minecraft;
/*  4:   */ import net.minecraft.client.gui.FontRenderer;
/*  5:   */ import net.minecraft.client.gui.GuiScreen;
/*  6:   */ import net.minecraft.client.gui.GuiSlot;
/*  7:   */ import net.minecraft.client.renderer.Tessellator;

/*  8:   */ import org.lwjgl.opengl.GL11;
/*  9:   */ 
/* 10:   */ public class GuiAltSlot
/* 11:   */   extends GuiSlot
/* 12:   */ {
/* 13:   */   final GuiAlts parentGui;
/* 14:   */   private Minecraft minecraft;
/* 15:   */   private FontRenderer fontRenderer;
/* 16:   */   
/* 17:   */   public GuiAltSlot(GuiAlts par1GuiMultiplayer)
/* 18:   */   {
/* 19:17 */     super(Minecraft.getMinecraft(), par1GuiMultiplayer.width, par1GuiMultiplayer.height, 32, par1GuiMultiplayer.height - 64, 16);
/* 20:18 */     this.parentGui = par1GuiMultiplayer;
/* 21:19 */     this.minecraft = Minecraft.getMinecraft();
/* 22:20 */     this.fontRenderer = this.minecraft.fontRenderer;
/* 23:   */   }
/* 24:   */   
/* 25:   */   protected int getSize()
/* 26:   */   {
/* 27:24 */     return GuiAlts.getMemberList(this.parentGui).countGroups();
/* 28:   */   }
/* 29:   */   
/* 30:   */   protected void elementClicked(int par1, boolean par2, int par3, int par4)
/* 31:   */   {
/* 32:28 */     if (par1 < GuiAlts.getMemberList(this.parentGui).countGroups())
/* 33:   */     {
/* 34:29 */       int j = GuiAlts.getSelectedGroup(this.parentGui);
/* 35:30 */       GuiAlts.getAndSetSelectedGroup(this.parentGui, par1);
/* 36:31 */       boolean flag1 = (GuiAlts.getSelectedGroup(this.parentGui) >= 0) && (GuiAlts.getSelectedGroup(this.parentGui) < getSize());
/* 37:   */       
/* 38:33 */       boolean flag2 = GuiAlts.getSelectedGroup(this.parentGui) < GuiAlts.getMemberList(this.parentGui).countGroups();
/* 39:   */       
/* 40:35 */       GuiAlts.getButtonDelete(this.parentGui).enabled = flag2;
/* 41:36 */       if (((!par2) || (!flag1)) && 
/* 42:37 */         (flag2) && (GuiScreen.isShiftKeyDown()) && (j >= 0) && (j < GuiAlts.getMemberList(this.parentGui).countGroups())) {
/* 43:42 */         GuiAlts.getMemberList(this.parentGui).swapGroups(j, GuiAlts.getSelectedGroup(this.parentGui));
/* 44:   */       }
/* 45:   */     }
/* 46:   */   }
/* 47:   */   
/* 48:   */   protected boolean isSelected(int par1)
/* 49:   */   {
/* 50:50 */     return par1 == GuiAlts.getSelectedGroup(this.parentGui);
/* 51:   */   }
/* 52:   */   
/* 53:   */   protected int getContentHeight()
/* 54:   */   {
/* 55:54 */     return getSize() * this.slotHeight;
/* 56:   */   }
/* 57:   */   
/* 58:   */   protected void drawBackground() {}
/* 59:   */   
/* 60:   */   protected void drawSlot(int par1, int par2, int par3, int par4, Tessellator par5Tessellator, int par5, int par6)
/* 61:   */   {
/* 62:62 */     if (par1 < GuiAlts.getMemberList(this.parentGui).countGroups())
/* 63:   */     {
/* 64:63 */       String username = GuiAlts.getMemberList(this.parentGui).get(par1);
/* 66:65 */       this.parentGui.drawString(this.fontRenderer, username, par2 + 5, par3 + 2, Color.white.getRGB());
/* 67:   */     }
/* 68:   */   }
/* 69:   */   
/* 70:   */   public void drawContainerBackground(Tessellator t)
/* 71:   */   {
/* 72:71 */     GL11.glBlendFunc(770, 771);
/* 73:72 */     GL11.glEnable(3042);
/* 74:73 */     GL11.glDisable(3553);
/* 75:74 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 76:75 */     float height = 32.0F;
/* 77:76 */     t.startDrawingQuads();
/* 78:77 */     t.setColorRGBA(0, 0, 0, 200);
/* 79:78 */     t.addVertexWithUV(this.left, this.bottom, 0.0D, this.left / height, (this.bottom + getAmountScrolled()) / height);
/* 80:   */     
/* 81:80 */     t.addVertexWithUV(this.right, this.bottom, 0.0D, this.right / height, (this.bottom + getAmountScrolled()) / height);
/* 82:   */     
/* 83:82 */     t.addVertexWithUV(this.right, this.top, 0.0D, this.right / height, (this.top + getAmountScrolled()) / height);
/* 84:   */     
/* 85:84 */     t.addVertexWithUV(this.left, this.top, 0.0D, this.left / height, (this.top + getAmountScrolled()) / height);
/* 86:   */     
/* 87:86 */     t.draw();
/* 88:87 */     GL11.glEnable(3553);
/* 89:   */   }
/* 90:   */ }

