/*   1:    */ package com.TealNerd.AltViewer;
/*   2:    */ 
/*   3:    */ import java.util.List;
/*   4:    */ import net.minecraft.client.Minecraft;
/*   5:    */ import net.minecraft.client.gui.GuiButton;
/*   6:    */ import net.minecraft.client.gui.GuiScreen;
/*   7:    */ import org.lwjgl.input.Keyboard;
/*   8:    */ 
/*   9:    */ public class GuiAssociations
/*  10:    */   extends GuiScreen
/*  11:    */ {
/*  12:    */   private GuiScreen parentScreen;
/*  13:    */   private GuiAssociationSlot groupSlotContainer;
/*  14:    */   private AltList groupList;
/*  15:    */   private Association group;
/*  16: 15 */   private int selectedGroup = -1;
/*  17:    */   private GuiButton editMembersButton;
/*  18:    */   private GuiButton renameGroupButton;
/*  19:    */   private GuiButton deleteButton;
private GuiButton searchButton;
/*  20:    */   private int ticksOpened;
/*  21:    */   
/*  22:    */   public GuiAssociations(GuiScreen par1GuiScreen)
/*  23:    */   {
/*  24: 22 */     this.parentScreen = par1GuiScreen;
/*  25:    */   }
/*  26:    */   
/*  27:    */   public void initGui()
/*  28:    */   {
/*  29: 26 */     this.groupList = AltViewer.altList.getAltList();
/*  30:    */     
/*  31: 28 */     Keyboard.enableRepeatEvents(true);
/*  32: 29 */     this.buttonList.clear();
/*  33:    */     
/*  34: 31 */     this.groupSlotContainer = new GuiAssociationSlot(this);
/*  35:    */     
/*  36: 33 */     initGuiControls();
/*  37:    */   }
/*  38:    */   
/*  39:    */   public void initGuiControls()
/*  40:    */   {
/*  41: 37 */     this.buttonList.add(this.editMembersButton = new GuiButton(13, this.width / 2, this.height - 52, 143, 20, "Edit Alts"));
/*  42:    */     
/*  43: 39 */     this.buttonList.add(this.renameGroupButton = new GuiButton(15, this.width / 2 - 143, this.height - 52, 143, 20, "Change Main Name"));
/*  44:    */     
/*  45:    */ 	  this.buttonList.add(this.searchButton = new GuiButton(16, this.width / 2 - 71, this.height - 28, 70, 20, "Search"));
/*  46:    */ 
/*  47: 43 */     this.buttonList.add(new GuiButton(10, this.width / 2 - 143, this.height - 28, 70, 20, "Add"));
/*  48:    */     
/*  49: 45 */     this.buttonList.add(this.deleteButton = new GuiButton(12, this.width / 2 + 1, this.height - 28, 70, 20, "Delete"));
/*  50:    */     
/*  51: 47 */     this.buttonList.add(new GuiButton(14, this.width / 2 + 73, this.height - 28, 70, 20, "Cancel"));
/*  52:    */     
/*  53: 49 */     updateButtons();
/*  54:    */   }
/*  55:    */   
/*  56:    */   public void updateScreen()
/*  57:    */   {
/*  58: 53 */     super.updateScreen();
/*  59: 54 */     this.ticksOpened += 1;
/*  60:    */   }
/*  61:    */   
/*  62:    */   public void onGuiClosed()
/*  63:    */   {
/*  64: 58 */     Keyboard.enableRepeatEvents(false);
/*  65:    */   }
/*  66:    */   
/*  67:    */   protected void actionPerformed(GuiButton par1GuiButton)
/*  68:    */   {
/*  69: 62 */     if (par1GuiButton.enabled)
/*  70:    */     {
					if(par1GuiButton.id == 16) {
						mc.displayGuiScreen(new GuiSearchAlts(this));
					}
/*  71: 63 */       if (par1GuiButton.id == 12)
/*  72:    */       {
/*  73: 64 */         if ((this.selectedGroup > -1) && (this.selectedGroup < this.groupList.size())) {
/*  74: 66 */           this.groupList.remove(this.selectedGroup);
/*  75:    */         }
/*  76: 68 */         this.selectedGroup = -1;
/*  77:    */       }
/*  78: 69 */       else if (par1GuiButton.id == 10)
/*  79:    */       {
/*  80: 70 */         this.groupList.add(new Association());
/*  81: 71 */         this.selectedGroup = (this.groupList.size() - 1);
/*  82:    */       }
/*  83: 72 */       else if (par1GuiButton.id == 13)
/*  84:    */       {
/*  85: 73 */         if ((this.selectedGroup > -1) && (this.selectedGroup < this.groupList.size())) {
/*  86: 75 */           Minecraft.getMinecraft().displayGuiScreen(new GuiAlts(this,((Association)this.groupList.get(this.selectedGroup))));
/*  87:    */         }
/*  88:    */       }
/*  89: 77 */       else if (par1GuiButton.id == 14)
/*  90:    */       {
/*  91: 78 */         this.mc.displayGuiScreen(this.parentScreen);
/*  92:    */       }
/*  93: 79 */       else if (par1GuiButton.id == 15)
/*  94:    */       {
/*  95: 80 */         if ((this.selectedGroup > -1) && (this.selectedGroup < this.groupList.size())) {
/*  96: 82 */           Minecraft.getMinecraft().displayGuiScreen(new GuiChangeMainName(this, (Association)this.groupList.get(this.selectedGroup)));
/*  97:    */         }
/*  98:    */       }
/*  99:    */       else
/* 100:    */       {
/* 101: 88 */         this.groupSlotContainer.actionPerformed(par1GuiButton);
/* 102:    */       }
/* 103: 90 */       updateButtons();
/* 104: 91 */       AltViewer.saveConfiguration();
/* 105:    */     }
/* 106:    */   }
/* 107:    */   
/* 108:    */   protected void keyTyped(char par1, int par2)
/* 109:    */   {
/* 110: 96 */     int j = this.selectedGroup;
/* 111: 97 */     if ((isShiftKeyDown()) && (par2 == 200))
/* 112:    */     {
/* 113: 98 */       if ((j > 0) && (j < this.groupList.countGroups()))
/* 114:    */       {
/* 115: 99 */         this.groupList.swapGroups(j, j - 1);
/* 116:100 */         this.selectedGroup -= 1;
/* 117:101 */         if (j < this.groupList.countGroups() - 1) {
/* 118:102 */           this.groupSlotContainer.scrollBy(-this.groupSlotContainer.getSlotHeight());
/* 119:    */         }
/* 120:    */       }
/* 121:    */     }
/* 122:106 */     else if ((isShiftKeyDown()) && (par2 == 208))
/* 123:    */     {
/* 124:107 */       if (((j >= 0 ? 1 : 0) & (j < this.groupList.countGroups() - 1 ? 1 : 0)) != 0)
/* 125:    */       {
/* 126:109 */         this.groupList.swapGroups(j, j + 1);
/* 127:110 */         this.selectedGroup += 1;
/* 128:111 */         if (j > 0) {
/* 129:112 */           this.groupSlotContainer.scrollBy(this.groupSlotContainer.getSlotHeight());
/* 130:    */         }
/* 131:    */       }
/* 132:    */     }
/* 133:116 */     else if ((par2 != 28) && (par2 != 156)) {
/* 134:117 */       super.keyTyped(par1, par2);
/* 135:    */     } else {
/* 136:119 */       actionPerformed((GuiButton)this.buttonList.get(2));
/* 137:    */     }
/* 138:    */   }
/* 139:    */   
/* 140:    */   public void drawScreen(int par1, int par2, float par3)
/* 141:    */   {
/* 142:124 */     this.groupSlotContainer.drawScreen(par1, par2, par3);
/* 143:125 */     drawCenteredString(this.fontRendererObj, "Edit groups", this.width / 2, 20, 16777215);
/* 144:    */     
/* 145:127 */     super.drawScreen(par1, par2, par3);
/* 146:128 */     updateButtons();
/* 147:    */   }
/* 148:    */   
/* 149:    */   private void joinServer(int par1)
/* 150:    */   {
/* 151:132 */     if (par1 >= this.groupList.countGroups()) {
/* 152:133 */       par1 -= this.groupList.countGroups();
/* 153:    */     }
/* 154:    */   }
/* 155:    */   
/* 156:    */   public void updateButtons()
/* 157:    */   {
/* 158:138 */     boolean flag = (this.selectedGroup >= 0) && (this.selectedGroup < AltViewer.altList.getAltList().size());
/* 159:    */     
/* 160:    */ 
/* 161:141 */     this.editMembersButton.enabled = flag;
/* 162:142 */     this.renameGroupButton.enabled = flag;
/* 163:143 */     this.deleteButton.enabled = flag;
/* 164:    */   }
/* 165:    */   
/* 166:    */   static AltList getAltList(GuiAssociations guiGroups)
/* 167:    */   {
/* 168:147 */     return guiGroups.groupList;
/* 169:    */   }
/* 170:    */   
/* 171:    */   static int getSelectedAssociation(GuiAssociations guiGroups)
/* 172:    */   {
/* 173:151 */     return guiGroups.selectedGroup;
/* 174:    */   }
/* 175:    */   
/* 176:    */   static int getAndSetSelectedAssociation(GuiAssociations guiGroups, int par1)
/* 177:    */   {
/* 178:155 */     return guiGroups.selectedGroup = par1;
/* 179:    */   }
/* 180:    */   
/* 181:    */   static GuiButton getButtonEditAlts(GuiAssociations guiGroups)
/* 182:    */   {
/* 183:159 */     return guiGroups.editMembersButton;
/* 184:    */   }
/* 185:    */   
/* 186:    */   static GuiButton getButtonRenameAssociation(GuiAssociations guiGroups)
/* 187:    */   {
/* 188:163 */     return guiGroups.renameGroupButton;
/* 189:    */   }
/* 190:    */   
/* 191:    */   static GuiButton getButtonDelete(GuiAssociations guiGroups)
/* 192:    */   {
/* 193:167 */     return guiGroups.deleteButton;
/* 194:    */   }
/* 195:    */   
/* 196:    */   static int getTicksOpened(GuiAssociations guiGroups)
/* 197:    */   {
/* 198:171 */     return guiGroups.ticksOpened;
/* 199:    */   }
/* 200:    */ }

