package com.group9.scoresystem;

import data.GameData;
import data.MovableEntity;
import data.World;
import java.util.ArrayList;
import java.util.Collection;
import org.openide.util.lookup.ServiceProvider;
import services.iEntityProcessingService;

@ServiceProvider(service = iEntityProcessingService.class)
public class ScoreSystem implements iEntityProcessingService
{
	private int totalScore = 0;
	private ArrayList<MovableEntity> enemies = new ArrayList();

	@Override
	public void process(GameData gameData, World world)
	{
		Collection<MovableEntity> gameMovableEntities = world.getGameMovableEntities();
		for (MovableEntity enemy : enemies)
		{
			if (!gameMovableEntities.contains(enemy))
			{
				totalScore++;
				System.out.println(totalScore);
			}
		}
		
		enemies = new ArrayList();

		for (MovableEntity enemy : world.getGameMovableEntities())
		{
			if (!enemy.getType().equalsEnemy())
			{
				continue;
			}

			if (!enemies.contains(enemy))
			{
				enemies.add(enemy);
			}
		}
	}

}
