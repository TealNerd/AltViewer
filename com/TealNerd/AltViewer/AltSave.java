/*  1:   */ package com.TealNerd.AltViewer;
/*  2:   */ 
/*  3:   */ import com.google.gson.Gson;
/*  4:   */ import com.google.gson.GsonBuilder;
/*  5:   */ import java.io.File;
/*  6:   */ import java.io.FileReader;
/*  7:   */ import java.io.FileWriter;
/*  8:   */ 
/*  9:   */ public class AltSave
/* 10:   */ {
/* 11:12 */   private AltList altList = new AltList();
/* 12:   */   
/* 13:   */   public AltList getAltList()
/* 14:   */   {
/* 18:18 */     return this.altList;
/* 19:   */   }
/* 20:   */   
public void save(File file) {
	Gson gson = new GsonBuilder().setPrettyPrinting().create();
	try {
		String json = gson.toJson(this);

		FileWriter writer = new FileWriter(file);
		writer.write(json);
		writer.close();
	} catch (Exception e) {
		e.printStackTrace();
	}
}
/* 37:   */   
/* 38:   */   public static AltSave load(File file)
/* 39:   */   {
/* 40:35 */     Gson gson = new Gson();
/* 41:   */     try
/* 42:   */     {
/* 43:37 */       return (AltSave)gson.fromJson(new FileReader(file), AltSave.class);
/* 44:   */     }
/* 45:   */     catch (Exception e)
/* 46:   */     {
/* 47:39 */       e.printStackTrace();
/* 48:   */     }
/* 49:41 */     return new AltSave();
/* 50:   */   }
/* 51:   */ }
