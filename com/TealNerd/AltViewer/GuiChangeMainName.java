/*  1:   */ package com.TealNerd.AltViewer;
/*  2:   */ 
/*  3:   */ import java.util.List;
/*  4:   */ import net.minecraft.client.Minecraft;
/*  5:   */ import net.minecraft.client.gui.GuiButton;
/*  6:   */ import net.minecraft.client.gui.GuiScreen;
/*  7:   */ import net.minecraft.client.gui.GuiTextField;
/*  8:   */ import org.lwjgl.input.Keyboard;
/*  9:   */ 
/* 10:   */ public class GuiChangeMainName
/* 11:   */   extends GuiScreen
/* 12:   */ {
/* 13:10 */   private String screenTitle = "Change Main Account Name";
/* 14:   */   private GuiScreen parent;
/* 15:   */   private GuiTextField groupName;
/* 16:   */   private Association group;
/* 17:   */   
/* 18:   */   public GuiChangeMainName(GuiScreen parent, Association group)
/* 19:   */   {
/* 20:16 */     this.parent = parent;
/* 21:17 */     this.group = group;
/* 22:   */   }
/* 23:   */   
/* 24:   */   public void initGui()
/* 25:   */   {
/* 26:21 */     Keyboard.enableRepeatEvents(true);
/* 27:   */     
/* 28:23 */     this.groupName = new GuiTextField(this.fontRendererObj, this.width / 2 - 100, this.height / 2, 200, 20);
/* 29:   */     
/* 30:25 */     this.groupName.setFocused(true);
/* 31:26 */     this.buttonList.add(new GuiButton(100, this.width / 2 - 100, this.height / 2 + 88, 98, 20, "Done"));
/* 32:   */     
/* 33:28 */     this.buttonList.add(new GuiButton(101, this.width / 2 + 2, this.height / 2 + 88, 98, 20, "Cancel"));
/* 34:   */   }
/* 35:   */   
/* 36:   */   public void onGuiClosed()
/* 37:   */   {
/* 38:33 */     Keyboard.enableRepeatEvents(false);
/* 39:   */   }
/* 40:   */   
/* 41:   */   protected void keyTyped(char par1, int par2)
/* 42:   */   {
/* 43:37 */     if (this.groupName.isFocused()) {
/* 44:38 */       this.groupName.textboxKeyTyped(par1, par2);
/* 45:   */     }
/* 46:40 */     if (par2 == 28)
/* 47:   */     {
/* 48:41 */       if (!this.groupName.getText().trim().isEmpty()) {
/* 49:42 */         this.group.setMain(this.groupName.getText().trim());
/* 50:   */       }
/* 51:44 */       this.mc.displayGuiScreen(this.parent);
/* 52:   */     }
/* 53:46 */     AltViewer.saveConfiguration();
/* 54:   */   }
/* 55:   */   
/* 56:   */   public void updateScreen()
/* 57:   */   {
/* 58:50 */     this.groupName.updateCursorCounter();
/* 59:   */   }
/* 60:   */   
/* 61:   */   protected void actionPerformed(GuiButton par1GuiButton)
/* 62:   */   {
/* 63:54 */     AltSave settings = AltViewer.altList;
/* 64:55 */     if (par1GuiButton.enabled)
/* 65:   */     {
/* 66:56 */       if (par1GuiButton.id == 100)
/* 67:   */       {
/* 68:57 */         if (!this.groupName.getText().trim().isEmpty()) {
/* 69:58 */           this.group.setMain(this.groupName.getText().trim());
/* 70:   */         }
/* 71:60 */         this.mc.displayGuiScreen(this.parent);
/* 72:   */       }
/* 73:61 */       else if (par1GuiButton.id == 101)
/* 74:   */       {
/* 75:62 */         this.mc.displayGuiScreen(this.parent);
/* 76:   */       }
/* 77:64 */       AltViewer.saveConfiguration();
/* 78:   */     }
/* 79:   */   }
/* 80:   */   
/* 81:   */   protected void mouseClicked(int par1, int par2, int par3)
/* 82:   */   {
/* 83:69 */     super.mouseClicked(par1, par2, par3);
/* 84:   */     
/* 85:71 */     this.groupName.mouseClicked(par1, par2, par3);
/* 86:   */   }
/* 87:   */   
/* 88:   */   public void drawScreen(int par1, int par2, float par3)
/* 89:   */   {
/* 90:75 */     drawDefaultBackground();
/* 91:76 */     this.groupName.drawTextBox();
/* 92:77 */     drawCenteredString(this.fontRendererObj, this.screenTitle, this.width / 2, 15, 16777215);
/* 93:   */     
/* 94:79 */     super.drawScreen(par1, par2, par3);
/* 95:   */   }
/* 96:   */ }
