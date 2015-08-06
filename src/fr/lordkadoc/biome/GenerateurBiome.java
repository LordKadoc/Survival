package fr.lordkadoc.biome;

import java.util.Random;

import fr.lordkadoc.image.DossierImage;
import fr.lordkadoc.image.ImageID;
import fr.lordkadoc.terrain.Chunk;

public class GenerateurBiome {
		
	
	public static Biome[] biomes = new Biome[5];
	public static long seed = 10;
	
	static{
		
		biomes[0] = new Plaine(seed,DossierImage.PLAIN_1);
		biomes[1] = new Desert(seed,DossierImage.DESERT_1);
		biomes[2] = new Neige(seed,DossierImage.SNOW_1);
		biomes[3] = new Montagne(seed,DossierImage.MOUNTAIN_1);
		biomes[4] = new Foret(seed,DossierImage.PLAIN_2);
		
	}
	
	public static Biome getBiome(int i){
		return biomes[i];
	}
	
	public static ImageID getImageGround(int i){
		return biomes[i].imageGround;
	}
	
	public static int length(){
		return biomes.length;
	}

	public static void genererChunk(Chunk chunk) {
		int biome = new Random(seed + chunk.getX() * 1000 + chunk.getY() * 1000000).nextInt(length());
		getBiome(biome).generate(chunk);
		
	}

}

