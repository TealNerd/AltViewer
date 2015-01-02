/*  1:   */ package com.TealNerd.AltViewer;
import java.util.ArrayList;
/*  2:   */ 
/*  3:   */ import java.util.List;
/*  4:   */ 
/*  5:   */ public class MemberList
/*  6:   */ {
/*  7:   */   private List members;
/*  8:   */   
/*  9:   */   public MemberList(List members)
/* 10:   */   {
/* 11:10 */     this.members = members;
/* 12:   */   }
/* 13:   */   
/* 14:   */   public void add(String name)
/* 15:   */   {
/* 16:14 */     this.members.add(name);
/* 17:   */   }
/* 18:   */   
/* 19:   */   public String get(int i)
/* 20:   */   {
/* 21:18 */     return (String)this.members.get(i);
/* 22:   */   }
/* 23:   */   
/* 24:   */   public int countGroups()
/* 25:   */   {
/* 26:22 */     return this.members.size();
/* 27:   */   }
/* 28:   */   
/* 29:   */   public void swapGroups(int i1, int i2)
/* 30:   */   {
/* 31:26 */     String s1 = (String)this.members.get(i1);
/* 32:27 */     String s2 = (String)this.members.get(i2);
/* 33:   */     
/* 34:29 */     this.members.set(i1, s2);
/* 35:30 */     this.members.set(i2, s1);
/* 36:   */   }
/* 37:   */   
/* 38:   */   public void remove(int i)
/* 39:   */   {
/* 40:34 */     this.members.remove(i);
/* 41:   */   }
/* 42:   */ }
