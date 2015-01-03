/*  1:   */ package com.TealNerd.AltViewer;
/*  2:   */ 
/*  3:   */ /*  7:   */ import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.client.gui.GuiPlayerInfo;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.NameFormat;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ClientTickEvent;
/*  4:   */ 
/*  5:   */ 
/*  6:   */ 
/*  8:   */ 
/*  9:   */ 
/* 10:   */ 
/* 11:   */ 
/* 12:   */ 
/* 13:   */ public class NameTagOverride
/* 14:   */ {
/* 15:15 */   AltSave altList = AltViewer.altList;
/* 16:16 */   static Minecraft mc = Minecraft.getMinecraft();
static List<EntityOtherPlayerMP> players;
static ArrayList<String> onlinePlayers = new ArrayList();
Pattern snitch = Pattern.compile("^ \\* ([a-zA-Z0-9_]+) (?:entered|logged out in|logged in to) snitch at .*? \\[([-]?[0-9]+) ([-]?[0-9]+) ([-]?[0-9]+)\\]$");
Pattern pm = Pattern.compile("^From (.*?): .*?$");
static List<String> previousPlayerList = new ArrayList();
static ArrayList<String> playersList = new ArrayList();
/* 17:   */   
/* 18:   */   @SubscribeEvent(priority = EventPriority.LOWEST)
/* 19:   */   public void getName(NameFormat e)
/* 20:   */   {
/* 21:20 */     int dist = (int) mc.thePlayer.getDistanceToEntity(e.entity);
				boolean assd = false;
				Association ass = null;
				for(Association a : altList.getAltList()) {
					if(a.getAlts().contains(e.username)) {
						assd = true;
						ass = a;
					}
				}
				
				if(assd) {
					e.displayname += "(" + ass.getMain() + ")";
				}
/* 28:   */   }
/* 29:   */   
/* 30:   */   @SubscribeEvent
/* 31:   */   public void onTick(ClientTickEvent e)
/* 32:   */   {
/* 33:30 */     if (mc.theWorld != null)
/* 34:   */     {
					players = mc.theWorld.loadedEntityList;
					onlinePlayers.removeAll(onlinePlayers);
					playersList.removeAll(playersList);
/* 35:31 */       	for(Object o : players) {
						if(o instanceof EntityOtherPlayerMP) {
							onlinePlayers.add(((EntityOtherPlayerMP) o).getDisplayName());
						}
					}

					for(Object o : mc.thePlayer.sendQueue.playerInfoList) {
						if(o instanceof GuiPlayerInfo) {
							playersList.add(EnumChatFormatting.getTextWithoutFormattingCodes(((GuiPlayerInfo) o).name));
						}
					}
					
					ArrayList<String> playerList = new ArrayList();
	    			List players = mc.thePlayer.sendQueue.playerInfoList;
	    			for(Object o : players) {
	    				if((o instanceof GuiPlayerInfo)) {
	    					GuiPlayerInfo info = (GuiPlayerInfo)o;
	    					
	    					playerList.add(EnumChatFormatting.getTextWithoutFormattingCodes(info.name));
	    				}
	    			}
	    			ArrayList<String> temp = (ArrayList)playerList.clone();
	    			playerList.removeAll(previousPlayerList);
	    			previousPlayerList.removeAll(temp);
	    			for(String player : previousPlayerList) {
	    				//leave
	    				if(altList.getAltList().getByUsername(player) != null) {
	    					sendChat(EnumChatFormatting.DARK_AQUA + "[SkyNet] " + EnumChatFormatting.GRAY + player + " (" + altList.getAltList().getByUsername(player).getMain() + ") left the game");
	    				} else {
	    					sendChat(EnumChatFormatting.DARK_AQUA + "[SkyNet] " + EnumChatFormatting.GRAY + player + " left the game");
	    				}
	    			}
	    			for(String player : playerList) {
	    				//join
	    				if(altList.getAltList().getByUsername(player) != null) {
	    					sendChat(EnumChatFormatting.DARK_AQUA + "[SkyNet] " + EnumChatFormatting.GRAY + player + " (" + altList.getAltList().getByUsername(player).getMain() + ") joined the game");
	    				} else {
	    					sendChat(EnumChatFormatting.DARK_AQUA + "[SkyNet] " + EnumChatFormatting.GRAY + player + " joined the game");
	    				}
	    			}
	    			previousPlayerList = temp;
	    			}
/* 50:   */   }

			@SubscribeEvent
			public void onChat(ClientChatReceivedEvent e) {
				String msg = e.message.getUnformattedText();
				Matcher snitchMatcher = snitch.matcher(msg);
				Matcher pmMatcher = pm.matcher(msg);
				while(snitchMatcher.find()) {
					String name = snitchMatcher.group(1);
					if(altList.getAltList().getByUsername(name) != null) {
						e.setCanceled(true);
						StringBuilder out = new StringBuilder(msg).insert((" * " + name).length(), " (" + altList.getAltList().getByUsername(name).getMain() + ")");
						sendChat(formatString(out.toString(), EnumChatFormatting.AQUA));
					}
				}
				while(pmMatcher.find()) {
					String name = pmMatcher.group(1);
					if(altList.getAltList().getByUsername(name) != null) {
						e.setCanceled(true);
						StringBuilder out = new StringBuilder(msg).insert(("From " + name).length(), " (" + altList.getAltList().getByUsername(name).getMain() + ")");
						sendChat(formatString(out.toString(), EnumChatFormatting.LIGHT_PURPLE));
					}
				}
			}
			
			public String formatString(String s, EnumChatFormatting color) {
				StringBuilder build = new StringBuilder();
				String[] arr = s.split(" ");
				for(String word : arr) {
					build.append(color + word + " ");
				}
				return build.toString();
			}
			
			public void sendChat(String msg) {
				mc.thePlayer.addChatMessage(new ChatComponentText(msg));
			}
/* 51:   */ }

