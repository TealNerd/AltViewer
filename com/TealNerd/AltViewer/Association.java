/*  1:   */ package com.TealNerd.AltViewer;
/*  2:   */ 
/*  3:   */ import java.util.ArrayList;

import net.minecraft.client.entity.EntityOtherPlayerMP;
/*  4:   */ 
/*  5:   */ public class Association
/*  6:   */ {
/*  7:   */   private String main;
/*  8: 8 */   private ArrayList<String> alts = new ArrayList();
/*  9:   */   
/* 10:   */   public ArrayList<String> getAlts()
/* 11:   */   {
/* 12:11 */     if (this.alts == null) {
/* 13:12 */       this.alts = new ArrayList();
/* 14:   */     }
/* 15:14 */     return this.alts;
/* 16:   */   }
/* 17:   */   
/* 18:   */   public void setMain(String main)
/* 19:   */   {
/* 20:18 */     this.main = main;
/* 21:   */   }
/* 22:   */   
/* 23:   */   public String getMain()
/* 24:   */   {
/* 25:22 */     return this.main;
/* 26:   */   }
/* 27:   */   
/* 28:   */   public void addAlt(String alt)
/* 29:   */   {
/* 30:26 */     this.alts.add(alt);
/* 31:   */   }

				public int getOnlineAlts() {
					int i = 0;
					for(String name : NameTagOverride.playersList) {
						if(alts.contains(name)) {
							i++;
						}
						if(name.equals(main)) {
							i++;
						}
					}
					return i;
				}
				public ArrayList<String> getOnlineAccts() {
					ArrayList<String> online = new ArrayList();
					for(String name : NameTagOverride.playersList) {
						if(alts.contains(name)) {
							online.add(name);
						}
						if(main.equals(name)) {
							online.add(name);
						}
					}
					return online;
				}
				
/* 32:   */ }
