package fr.lordkadoc.biome;

import fr.lordkadoc.element.Rock;
import fr.lordkadoc.element.Tree;
import fr.lordkadoc.image.ImageID;
import fr.lordkadoc.terrain.Cellule;
import fr.lordkadoc.terrain.Chunk;

public class Neige extends Biome{

	public Neige(long seed,ImageID imageGround) {
		super(seed,imageGround);
	}

	
	@Override
	public void generateElements(Chunk chunk){
		
		Cellule[][] cellules = chunk.getCellules();
		Cellule cellule;
		
		for(int i=2;i<cellules.length-2;i++){
			for(int j=2;j<cellules[i].length-2;j++){
				cellule = cellules[i][j];
				if(!cellule.getGround().isWater()){
					if(random.nextInt(100) < 1){
						cellule.ajouterElement(new Tree());
					}else if(random.nextInt(100) < 1){
						cellule.ajouterElement(new Rock());
					}
				}
			}
		}
	}

}

