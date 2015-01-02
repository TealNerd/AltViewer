/*  1:   */ package com.TealNerd.AltViewer;
/*  2:   */ 
/*  3:   */ import java.util.List;
/*  4:   */ import net.minecraft.client.Minecraft;
/*  5:   */ import net.minecraft.client.gui.GuiButton;
/*  6:   */ import net.minecraft.client.gui.GuiScreen;
/*  7:   */ import net.minecraft.client.gui.GuiTextField;
/*  8:   */ import org.lwjgl.input.Keyboard;
/*  9:   */ 
/* 10:   */ public class GuiAddAlt
/* 11:   */   extends GuiScreen
/* 12:   */ {
/* 13:   */   private GuiScreen parent;
/* 14:   */   private MemberList memberList;
/* 15:   */   private GuiTextField username;
/* 16:   */   
/* 17:   */   public GuiAddAlt(GuiScreen parent, MemberList memberList)
/* 18:   */   {
/* 19:16 */     this.parent = parent;
/* 20:17 */     this.memberList = memberList;
/* 21:   */   }
/* 22:   */   
/* 23:   */   public void initGui()
/* 24:   */   {
/* 25:21 */     Keyboard.enableRepeatEvents(true);
/* 26:   */     
/* 27:23 */     this.username = new GuiTextField(this.fontRendererObj, this.width / 2 - 100, this.height / 2, 200, 20);
/* 28:   */     
/* 29:25 */     this.username.setFocused(true);
/* 30:   */     
/* 31:27 */     this.buttonList.add(new GuiButton(100, this.width / 2 - 100, this.height / 2 + 88, 200, 20, "Done"));
/* 32:   */   }
/* 33:   */   
/* 34:   */   public void onGuiClosed()
/* 35:   */   {
/* 36:32 */     Keyboard.enableRepeatEvents(false);
/* 37:   */   }
/* 38:   */   
/* 39:   */   protected void keyTyped(char par1, int par2)
/* 40:   */   {
/* 41:36 */     if (this.username.isFocused()) {
/* 42:37 */       this.username.textboxKeyTyped(par1, par2);
/* 43:   */     }
/* 44:39 */     if (par2 == 28)
/* 45:   */     {
/* 46:40 */       if (!this.username.getText().trim().isEmpty()) {
/* 47:41 */         this.memberList.add(this.username.getText().trim());
/* 48:   */       }
/* 49:43 */       this.mc.displayGuiScreen(this.parent);
/* 50:   */     }
/* 51:   */   }
/* 52:   */   
/* 53:   */   public void updateScreen()
/* 54:   */   {
/* 55:48 */     this.username.updateCursorCounter();
/* 56:   */   }
/* 57:   */   
/* 58:   */   protected void actionPerformed(GuiButton par1GuiButton)
/* 59:   */   {
/* 60:52 */     AltSave settings = AltViewer.altList;
/* 61:53 */     if (par1GuiButton.enabled)
/* 62:   */     {
/* 63:54 */       if (par1GuiButton.id == 100)
/* 64:   */       {
/* 65:55 */         if (!this.username.getText().trim().isEmpty()) {
/* 66:56 */           this.memberList.add(this.username.getText().trim());
/* 67:   */         }
/* 68:58 */         this.mc.displayGuiScreen(this.parent);
/* 69:   */       }
/* 70:60 */       AltViewer.saveConfiguration();
/* 71:   */     }
/* 72:   */   }
/* 73:   */   
/* 74:   */   protected void mouseClicked(int par1, int par2, int par3)
/* 75:   */   {
/* 76:65 */     super.mouseClicked(par1, par2, par3);
/* 77:   */     
/* 78:67 */     this.username.mouseClicked(par1, par2, par3);
/* 79:   */   }
/* 80:   */   
/* 81:   */   public void drawScreen(int par1, int par2, float par3)
/* 82:   */   {
/* 83:71 */     drawDefaultBackground();
/* 84:72 */     this.username.drawTextBox();
/* 85:73 */     drawCenteredString(this.fontRendererObj, "Add Alt", this.width / 2, 15, 16777215);
/* 86:   */     
/* 87:75 */     super.drawScreen(par1, par2, par3);
/* 88:   */   }
/* 89:   */ }

