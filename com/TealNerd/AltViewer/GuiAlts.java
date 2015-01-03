/*   1:    */ package com.TealNerd.AltViewer;
/*   2:    */ 
/*   3:    */ import java.awt.Color;

/*   5:    */ import net.minecraft.client.gui.GuiButton;
/*   6:    */ import net.minecraft.client.gui.GuiScreen;

/*   7:    */ import org.lwjgl.input.Keyboard;
/*   4:    */ 
/*   8:    */ 
/*   9:    */ public class GuiAlts
/*  10:    */   extends GuiScreen
/*  11:    */ {
/*  12:    */   private GuiScreen parentScreen;
/*  13:    */   private GuiAltSlot memberSlotContainer;
/*  14: 11 */   private int selectedMember = -1;
/*  15:    */   private GuiButton deleteButton;
/*  16:    */   private int ticksOpened;
/*  17:    */   private MemberList memberList;
				private Association ass;
/*  18:    */   
/*  19:    */   public GuiAlts(GuiScreen par1GuiScreen, Association association)
/*  20:    */   {
/*  21: 17 */     this.parentScreen = par1GuiScreen;
/*  22: 18 */     this.memberList = new MemberList(association.getAlts());
				  this.ass = association;
/*  23:    */   }
/*  24:    */   
/*  25:    */   public void initGui()
/*  26:    */   {
/*  27: 22 */     Keyboard.enableRepeatEvents(true);
/*  28: 23 */     this.buttonList.clear();
/*  29:    */     
/*  30: 25 */     this.memberSlotContainer = new GuiAltSlot(this);
/*  31:    */     
/*  32: 27 */     initGuiControls();
/*  33:    */   }
/*  34:    */   
/*  35:    */   public void initGuiControls()
/*  36:    */   {
/*  37: 31 */     this.buttonList.add(new GuiButton(10, this.width / 2 - 107, this.height - 52, 70, 20, "Add"));
/*  38:    */     
/*  39: 33 */     this.buttonList.add(this.deleteButton = new GuiButton(12, this.width / 2 - 35, this.height - 52, 70, 20, "Delete"));
/*  40:    */     
/*  41: 35 */     this.buttonList.add(new GuiButton(14, this.width / 2 + 37, this.height - 52, 70, 20, "Cancel"));
/*  42:    */     
					this.buttonList.add(new GuiButton(13, this.width / 2 - 107, this.height - 28, 216, 20, "Change Main Name"));
/*  43: 37 */     updateButtons();
/*  44:    */   }
/*  45:    */   
/*  46:    */   public void updateScreen()
/*  47:    */   {
/*  48: 41 */     super.updateScreen();
/*  49: 42 */     this.ticksOpened += 1;
/*  50:    */   }
/*  51:    */   
/*  52:    */   public void onGuiClosed()
/*  53:    */   {
/*  54: 46 */     Keyboard.enableRepeatEvents(false);
/*  55:    */   }
/*  56:    */   
/*  57:    */   protected void actionPerformed(GuiButton par1GuiButton)
/*  58:    */   {
/*  59: 50 */     if (par1GuiButton.enabled)
/*  60:    */     {
					if(par1GuiButton.id == 13) {
						mc.displayGuiScreen(new GuiChangeMainName(this, ass));
					}
/*  61: 51 */       if (par1GuiButton.id == 12)
/*  62:    */       {
/*  63: 52 */         if ((this.selectedMember > -1) && (this.selectedMember < this.memberList.countGroups())) {
/*  64: 54 */           this.memberList.remove(this.selectedMember);
/*  65:    */         }
/*  66: 56 */         this.selectedMember = -1;
/*  67:    */       }
/*  68: 57 */       else if (par1GuiButton.id == 10)
/*  69:    */       {
/*  70: 58 */         this.mc.displayGuiScreen(new GuiAddAlt(this, this.memberList));
/*  71: 59 */         this.selectedMember = -1;
/*  72:    */       }
/*  73: 60 */       else if (par1GuiButton.id == 11)
/*  74:    */       {
/*  75: 61 */         if ((this.selectedMember <= -1) || (this.selectedMember < this.memberList.countGroups())) {}
/*  76:    */       }
/*  77: 65 */       else if (par1GuiButton.id == 14)
/*  78:    */       {
/*  79: 66 */         this.mc.displayGuiScreen(this.parentScreen);
/*  80:    */       }
/*  81:    */       else
/*  82:    */       {
/*  83: 68 */         this.memberSlotContainer.actionPerformed(par1GuiButton);
/*  84:    */       }
/*  85: 70 */       updateButtons();
/*  86: 71 */       AltViewer.saveConfiguration();
/*  87:    */     }
/*  88:    */   }
/*  89:    */   
/*  90:    */   protected void keyTyped(char par1, int par2)
/*  91:    */   {
/*  92: 76 */     int j = this.selectedMember;
/*  93: 77 */     if ((isShiftKeyDown()) && (par2 == 200))
/*  94:    */     {
/*  95: 78 */       if ((j > 0) && (j < this.memberList.countGroups()))
/*  96:    */       {
/*  97: 79 */         this.memberList.swapGroups(j, j - 1);
/*  98: 80 */         this.selectedMember -= 1;
/*  99: 81 */         if (j < this.memberList.countGroups() - 1) {
/* 100: 82 */           this.memberSlotContainer.scrollBy(-this.memberSlotContainer.getSlotHeight());
/* 101:    */         }
/* 102:    */       }
/* 103:    */     }
/* 104: 86 */     else if ((isShiftKeyDown()) && (par2 == 208))
/* 105:    */     {
/* 106: 87 */       if (((j >= 0 ? 1 : 0) & (j < this.memberList.countGroups() - 1 ? 1 : 0)) != 0)
/* 107:    */       {
/* 108: 89 */         this.memberList.swapGroups(j, j + 1);
/* 109: 90 */         this.selectedMember += 1;
/* 110: 91 */         if (j > 0) {
/* 111: 92 */           this.memberSlotContainer.scrollBy(this.memberSlotContainer.getSlotHeight());
/* 112:    */         }
/* 113:    */       }
/* 114:    */     }
/* 115: 96 */     else if ((par2 != 28) && (par2 != 156)) {
/* 116: 97 */       super.keyTyped(par1, par2);
/* 117:    */     } else {
/* 118: 99 */       actionPerformed((GuiButton)this.buttonList.get(2));
/* 119:    */     }
/* 120:    */   }
/* 121:    */   
/* 122:    */   public void drawScreen(int par1, int par2, float par3)
/* 123:    */   {
/* 124:104 */     this.memberSlotContainer.drawScreen(par1, par2, par3);
/* 125:105 */     drawCenteredString(this.fontRendererObj, "Edit Alts for " + ass.getMain(), this.width / 2, 20, 16777215);
if(ass.getOnlineAccts() != null) {
				  StringBuilder build = new StringBuilder();
				  build.append("Online Accounts: ");
				  for(String name : ass.getOnlineAccts()) {
					  build.append(name + ", ");
				  }
				  try{
				  build.deleteCharAt(build.lastIndexOf(","));
				  }catch(Exception e) {
					  e.printStackTrace();
				  }
				  
				  drawCenteredString(this.fontRendererObj, build.toString(), this.width / 2, 50, Color.GREEN.getRGB());
				  }
				  
/* 126:    */     
/* 127:107 */     super.drawScreen(par1, par2, par3);
/* 128:    */   }
/* 129:    */   
/* 130:    */   private void joinServer(int par1)
/* 131:    */   {
/* 132:111 */     if (par1 >= this.memberList.countGroups()) {
/* 133:112 */       par1 -= this.memberList.countGroups();
/* 134:    */     }
/* 135:    */   }
/* 136:    */   
/* 137:    */   public void updateButtons()
/* 138:    */   {
/* 139:117 */     boolean flag = (this.selectedMember >= 0) && (this.selectedMember < AltViewer.altList.getAltList().size());
/* 140:    */     
/* 141:    */ 
/* 142:120 */     this.deleteButton.enabled = flag;
/* 143:    */   }
/* 144:    */   
/* 145:    */   static MemberList getMemberList(GuiAlts GuiMembers)
/* 146:    */   {
/* 147:124 */     return GuiMembers.memberList;
/* 148:    */   }
/* 149:    */   
/* 150:    */   static int getSelectedGroup(GuiAlts GuiMembers)
/* 151:    */   {
/* 152:128 */     return GuiMembers.selectedMember;
/* 153:    */   }
/* 154:    */   
/* 155:    */   static int getAndSetSelectedGroup(GuiAlts GuiMembers, int par1)
/* 156:    */   {
/* 157:132 */     return GuiMembers.selectedMember = par1;
/* 158:    */   }
/* 159:    */   
/* 160:    */   static GuiButton getButtonDelete(GuiAlts GuiMembers)
/* 161:    */   {
/* 162:136 */     return GuiMembers.deleteButton;
/* 163:    */   }
/* 164:    */   
/* 165:    */   static int getTicksOpened(GuiAlts GuiMembers)
/* 166:    */   {
/* 167:140 */     return GuiMembers.ticksOpened;
/* 168:    */   }
/* 169:    */ }

