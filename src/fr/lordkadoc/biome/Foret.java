package fr.lordkadoc.biome;

import fr.lordkadoc.element.Tree;
import fr.lordkadoc.image.ImageID;
import fr.lordkadoc.terrain.Cellule;
import fr.lordkadoc.terrain.Chunk;

public class Foret extends Biome{

	Foret(long seed,ImageID imageGround) {
		super(seed,imageGround);
	}

	@Override
	public void generateElements(Chunk chunk) {
		Cellule[][] cellules = chunk.getCellules();
		Cellule cellule;
		
		for(int i=2;i<cellules.length-2;i++){
			for(int j=2;j<cellules[i].length-2;j++){
				cellule = cellules[i][j];
				if(!cellule.getGround().isWater()){
					if(random.nextInt(100) < 3){
						cellule.ajouterElement(new Tree());
					}
				}
			}
		}
	}

}

