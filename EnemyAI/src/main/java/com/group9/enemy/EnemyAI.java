

package com.group9.enemy;

import data.Entity;
import data.GameData;
import data.MovableEntity;
import data.World;
import movableentityparts.Move;
import movableentityparts.Position;
import org.openide.util.lookup.ServiceProvider;
import services.iEntityProcessingService;

/**
 *
 * @author Jorge Báez Garrido
 */
@ServiceProvider(service = iEntityProcessingService.class)

public class EnemyAI implements iEntityProcessingService {
	@Override
	public void process(GameData gameData, World world)
	{
		for(Entity enemy : world.getEntities(Enemy.class)){
			Position position = enemy.getPart(Position.class);
			Move move = enemy.getPart(Move.class);
			move.process(gameData, enemy);
			position.process(gameData, enemy);
			updateSprite(enemy);
		}
		
	}
	
	private void updateSprite(Entity entity){
		
	}

}
