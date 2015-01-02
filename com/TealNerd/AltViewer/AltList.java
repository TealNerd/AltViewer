/*  1:   */ package com.TealNerd.AltViewer;
/*  2:   */ 
/*  3:   */ import java.util.ArrayList;
/*  4:   */ 
/*  5:   */ public class AltList
/*  6:   */   extends ArrayList<Association>
/*  7:   */ {
/*  8:   */   public int countGroups()
/*  9:   */   {
/* 10: 7 */     return size();
/* 11:   */   }
/* 12:   */   
/* 13:   */   public void swapGroups(int i1, int i2)
/* 14:   */   {
/* 15:11 */     Association g1 = (Association)get(i1);
/* 16:12 */     Association g2 = (Association)get(i2);
/* 17:   */     
/* 18:14 */     set(i1, g2);
/* 19:15 */     set(i2, g1);
/* 20:   */   }
/* 21:   */   
/* 22:   */   public Association getByUsername(String username)
/* 23:   */   {
/* 24:19 */     for (Association group : this) {
/* 25:20 */       if ((group.getAlts().contains(username))) {
/* 26:21 */         return group;
/* 27:   */       }
/* 28:   */     }
/* 29:24 */     return null;
/* 30:   */   }

public Association getAltsByUsername(String main) {
	for(Association ass : this) {
		if(ass.getMain().equals(main)) {
			return ass;
		}
	}
	return null;
}
/* 31:   */ }

