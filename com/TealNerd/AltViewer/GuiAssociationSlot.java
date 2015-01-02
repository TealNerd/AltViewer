/*  1:   */ package com.TealNerd.AltViewer;
/*  2:   */ 
/*  3:   */ /*  4:   */ import java.awt.Color;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityOtherPlayerMP;
/*  5:   */ import net.minecraft.client.gui.FontRenderer;
/*  6:   */ import net.minecraft.client.gui.GuiScreen;
/*  7:   */ import net.minecraft.client.gui.GuiSlot;
/*  8:   */ import net.minecraft.client.renderer.Tessellator;

import org.lwjgl.opengl.GL11;
/* 10:   */ 
/* 11:   */ class GuiAssociationSlot
/* 12:   */   extends GuiSlot
/* 13:   */ {
/* 14:   */   final GuiAssociations parentGui;
/* 15:   */   private Minecraft minecraft;
/* 16:   */   private FontRenderer fontRenderer;
/* 17:   */   
/* 18:   */   public GuiAssociationSlot(GuiAssociations par1GuiMultiplayer)
/* 19:   */   {
/* 20:19 */     super(Minecraft.getMinecraft(), par1GuiMultiplayer.width, par1GuiMultiplayer.height, 32, par1GuiMultiplayer.height - 64, 36);
/* 21:20 */     this.parentGui = par1GuiMultiplayer;
/* 22:21 */     this.minecraft = Minecraft.getMinecraft();
/* 23:22 */     this.fontRenderer = this.minecraft.fontRenderer;
/* 24:   */   }
/* 25:   */   
/* 26:   */   protected int getSize()
/* 27:   */   {
/* 28:27 */     return GuiAssociations.getAltList(this.parentGui).countGroups();
/* 29:   */   }
/* 30:   */   
/* 31:   */   protected void elementClicked(int par1, boolean par2, int par3, int par4)
/* 32:   */   {
/* 33:32 */     if (par1 < GuiAssociations.getAltList(this.parentGui).countGroups())
/* 34:   */     {
/* 35:34 */       int j = GuiAssociations.getSelectedAssociation(this.parentGui);
/* 36:35 */       GuiAssociations.getAndSetSelectedAssociation(this.parentGui, par1);
/* 37:36 */       boolean flag1 = (GuiAssociations.getSelectedAssociation(this.parentGui) >= 0) && (GuiAssociations.getSelectedAssociation(this.parentGui) < getSize());
/* 38:37 */       boolean flag2 = GuiAssociations.getSelectedAssociation(this.parentGui) < GuiAssociations.getAltList(this.parentGui).countGroups();
/* 39:38 */       GuiAssociations.getButtonEditAlts(this.parentGui).enabled = flag1;
/* 40:39 */       GuiAssociations.getButtonDelete(this.parentGui).enabled = flag2;
/* 41:40 */       if ((flag2) && (GuiScreen.isShiftKeyDown()) && (j >= 0) && (j < GuiAssociations.getAltList(this.parentGui).countGroups())) {
/* 42:42 */         GuiAssociations.getAltList(this.parentGui).swapGroups(j, GuiAssociations.getSelectedAssociation(this.parentGui));
/* 43:   */       }
/* 44:   */     }
/* 45:   */   }
/* 46:   */   
/* 47:   */   protected boolean isSelected(int par1)
/* 48:   */   {
/* 49:48 */     return par1 == GuiAssociations.getSelectedAssociation(this.parentGui);
/* 50:   */   }
/* 51:   */   
/* 52:   */   protected int getContentHeight()
/* 53:   */   {
/* 54:52 */     return getSize() * 36;
/* 55:   */   }
/* 56:   */   
/* 57:   */   protected void drawBackground() {}
/* 58:   */   
/* 59:   */   protected void drawSlot(int par1, int par2, int par3, int par4, Tessellator par5Tessellator, int par5, int par6)
/* 60:   */   {
/* 61:60 */     if (par1 < GuiAssociations.getAltList(this.parentGui).countGroups())
/* 62:   */     {
/* 63:62 */       Association group = (Association)GuiAssociations.getAltList(this.parentGui).get(par1);
/* 64:   */       
/* 65:64 */       String sizeInfo = group.getAlts().size() + (group.getAlts().size() != 1 ? " alts" : " alt");
				  String onlineInfo = group.getOnlineAlts() + " online";
				  Color onlineColor = group.getOnlineAlts() > 0 ? Color.green : Color.red;
/* 66:65 */       this.parentGui.drawString(this.fontRenderer, group.getMain(), par2 + 2, par3 + 1, 16777215);
/* 67:66 */       this.parentGui.drawString(this.fontRenderer, sizeInfo, par2 + 215 - this.fontRenderer.getStringWidth(sizeInfo), par3 + 12, 8421504);
				  this.parentGui.drawString(this.fontRenderer, onlineInfo, par2 + 2, par3 + 12, onlineColor.getRGB());
/* 68:   */     }
/* 69:   */   }
/* 70:   */   
/* 71:   */   public void drawContainerBackground(Tessellator t)
/* 72:   */   {
/* 73:71 */     GL11.glBlendFunc(770, 771);
/* 74:72 */     GL11.glEnable(3042);
/* 75:73 */     GL11.glDisable(3553);
/* 76:74 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 77:75 */     float height = 32.0F;
/* 78:76 */     t.startDrawingQuads();
/* 79:77 */     t.setColorRGBA(0, 0, 0, 200);
/* 80:78 */     t.addVertexWithUV(this.left, this.bottom, 0.0D, this.left / height, (this.bottom + getAmountScrolled()) / height);
/* 81:   */     
/* 82:80 */     t.addVertexWithUV(this.right, this.bottom, 0.0D, this.right / height, (this.bottom + getAmountScrolled()) / height);
/* 83:   */     
/* 84:82 */     t.addVertexWithUV(this.right, this.top, 0.0D, this.right / height, (this.top + getAmountScrolled()) / height);
/* 85:   */     
/* 86:84 */     t.addVertexWithUV(this.left, this.top, 0.0D, this.left / height, (this.top + getAmountScrolled()) / height);
/* 87:   */     
/* 88:86 */     t.draw();
/* 89:87 */     GL11.glEnable(3553);
/* 90:   */   }
/* 91:   */ }

