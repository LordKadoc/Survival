package fr.lordkadoc.image;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import fr.lordkadoc.biome.Biome;
import fr.lordkadoc.biome.GenerateurBiome;
import fr.lordkadoc.element.ElementID;

public class DossierImage {
	
		
		public static ArrayList<BufferedImage> imagesBiomeSol=new ArrayList<BufferedImage>();
		public static ArrayList<BufferedImage> imagesObstacle=new ArrayList<BufferedImage>();
		public static BufferedImage imageJoueur;

		
		static {
			try {
				
				imageJoueur = ImageIO.read(ResourceLoader.load("joueur.png"));
				
				for(int i=0;i<GenerateurBiome.length();i++){
					try{
						imagesBiomeSol.add(ImageIO.read(ResourceLoader.load("sol/Biome"+i+".png")));
					}catch(Exception e){
						System.out.println("Biome"+i+"  bug");
					}
				}

				for(int i=0;i<ElementID.length();i++){
					try{
						imagesObstacle.add(ImageIO.read(ResourceLoader.load("obstacle/Element"+i+".png")));
					}catch(Exception e){
						System.out.println("Element"+i+"  bug");
					}
				}
				
			}catch(Exception e){
				System.out.println("pb image");
			}
		}	

		
}
