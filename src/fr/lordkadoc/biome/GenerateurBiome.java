package fr.lordkadoc.biome;

import java.util.Random;

import fr.lordkadoc.terrain.Chunk;

public class GenerateurBiome {
		
	
	public static Biome[] biomes = new Biome[5];
	public static long seed = 10;
	
	static{
		
		biomes[0] = new Plaine(seed);
		biomes[1] = new Desert(seed);
		biomes[2] = new Neige(seed);
		biomes[3] = new Montagne(seed);
		biomes[4] = new Foret(seed);
		
	}
	
	public static Biome getBiome(int i){
		return biomes[i];
	}
	
	public static int getSol(int i){
		return i+1;
	}
	
	public static int length(){
		return biomes.length;
	}

	public static void genererChunk(Chunk chunk) {
		int biome = new Random(seed + chunk.getX() * 1000 + chunk.getY() * 1000000).nextInt(length());
		getBiome(biome).generer(chunk);
		
	}

}

