package fr.lordkadoc.image;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileFilter;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

public class DossierImage {
	
		
		public static Map<ImageID,BufferedImage> images = new HashMap<ImageID,BufferedImage>();
		
		public static ImageID PLAYER_1 = new ImageID("player",1);
		
		public static ImageID TREE_1 = new ImageID("tree",1);
		
		public static ImageID ROCK_1 = new ImageID("rock",1);
		
		public static ImageID CACTUS_1 = new ImageID("cactus",1);
		
		public static ImageID DESERT_1 = new ImageID("desert",1);
		
		public static ImageID PLAIN_1 = new ImageID("grass",1);
		
		public static ImageID PLAIN_2 = new ImageID("grass",2);
		
		public static ImageID LAKE_1 = new ImageID("water",1);
		
		public static ImageID SNOW_1 = new ImageID("snow",1);
		
		public static ImageID MOUNTAIN_1 = new ImageID("mountain",1);
			
		static {
			try {
				
				File folder = new File("src/images");
				String[] s;
				String name;
				int idx;
				
				for(File f : folder.listFiles(new FileFilter() {
					
					@Override
					public boolean accept(File pathname) {	
						return pathname.getName().endsWith(".png") || pathname.getName().endsWith(".jpg");
					}
				})){
					
					name = f.getName();
					idx = name.lastIndexOf('.');
					name = name.substring(0,idx);
					s = name.split("_");
					images.put(new ImageID(s[0],Integer.parseInt(s[1])), ImageIO.read(f));
					
				}
				
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}	
		
		
}
