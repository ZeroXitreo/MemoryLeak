package group9.core;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import com.badlogic.gdx.graphics.OrthographicCamera;
import data.Entity;
import data.GameData;
import data.World;
import group9.manager.GameInputProcessor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.openide.util.Lookup;
import org.openide.util.LookupEvent;
import org.openide.util.LookupListener;
import services.iEntityProcessingService;
import services.iGamePluginServices;
import services.iPostEntityProcessingService;

/**
 *
 * @author Jorge Báez Garrido
 */
public class Game implements ApplicationListener
{

	private GameData gameData = new GameData();
	private World world = new World();
	private static OrthographicCamera cam;
	private GameInputProcessor gip = new GameInputProcessor(gameData);
	private List<iGamePluginServices> gamePlugin = new ArrayList<>();
	private Lookup lookup = Lookup.getDefault();
	private Lookup.Result<iGamePluginServices> result;

	@Override
	public void create()
	{
		gameData.setDisplayHeight(540);
		gameData.setDisplayWidth(960);
		cam = new OrthographicCamera(gameData.getDisplayWidth(), gameData.getDisplayHeight());
		cam.translate(gameData.getDisplayWidth() / 2, gameData.getDisplayHeight() / 2);
		cam.update();

//        Gdx.input.setInputProcessor(
//                new GameInputProcessor(gameData)
//        );
		result = lookup.lookupResult(iGamePluginServices.class);
		result.addLookupListener(lookupListener);
		result.allItems();
		
		for(iGamePluginServices plugin : result.allInstances()){
			plugin.start(gameData, world);
			gamePlugin.add(plugin);
		}

	}
	
	private void update(){
		for(iEntityProcessingService entityProcessor : getEntityProcessingServices()){
			entityProcessor.process(gameData, world);
		}
		for(iPostEntityProcessingService postEntityProcessor : getPostEntityProcessingService()){
			postEntityProcessor.process(gameData, world);
		}
	}
        
        private void draw(){
            for (Entity entity : world.getEntities()){
                System.out.println("hej");
            }
        }

	@Override
	public void resize(int i, int i1)
	{

	}

	@Override
	public void render()
	{
                update();
		gip.keyPress();
	}

	@Override
	public void pause()
	{
	}

	@Override
	public void resume()
	{
	}

	@Override
	public void dispose()
	{
	}
	
	private Collection<? extends iGamePluginServices> getPluginServices()
	{
		return lookup.lookupAll(iGamePluginServices.class);
	}

	private Collection<? extends iEntityProcessingService> getEntityProcessingServices()
	{
		return lookup.lookupAll(iEntityProcessingService.class);
	}

	private Collection<? extends iPostEntityProcessingService> getPostEntityProcessingService()
	{
		return lookup.lookupAll(iPostEntityProcessingService.class);
	}

	private final LookupListener lookupListener = new LookupListener()
	{

		@Override
		public void resultChanged(LookupEvent le)
		{
			Collection<? extends iGamePluginServices> updated = result.allInstances();

			for(iGamePluginServices gs : updated)
			{
				if(!gamePlugin.contains(gs))
				{
					gs.start(gameData, world);
					gamePlugin.remove(gs);
				}
			}
		}
	};
}
