package fr.lordkadoc.biome;

import fr.lordkadoc.element.Cactus;
import fr.lordkadoc.image.ImageID;
import fr.lordkadoc.terrain.Cellule;
import fr.lordkadoc.terrain.Chunk;

public class Desert extends Biome{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3143736293566271529L;

	Desert(long seed,ImageID imageGround) {
		super(seed,imageGround,false);
	}

	@Override
	public void generateElements(Chunk chunk) {
		
		Cellule[][] cellules = chunk.getCellules();
		Cellule cellule;
		
		for(int i=2;i<cellules.length-2;i++){
			for(int j=2;j<cellules[i].length-2;j++){
				cellule = cellules[i][j];
				if(random.nextInt(100) < 1){
					cellule.ajouterElement(new Cactus());
				}
				
			}
		}
	}

}

