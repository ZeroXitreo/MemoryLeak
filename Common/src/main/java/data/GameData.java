package data;

//import events.Event;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @author Jorge Báez Garrido
 */
public class GameData
{
	private int displayWidth;
	private int displayHeight;
	private GameKeys gameKeys = new GameKeys();
//	private List<Event> events = new CopyOnWriteArrayList<>();
//
//	public void addEvent(Event e)
//	{
//		events.add(e);
//	}
//
//	public void removeEvenet(Event e)
//	{
//		events.remove(e);
//	}
//
//	public List<Event> getElements()
//	{
//		return events;
//	}

	public void setDisplayWidth(int width)
	{
		this.displayWidth = width;
	}

	public int getDisplayWidth()
	{
		return displayWidth;
	}

	public GameKeys getKeys()
	{
		return gameKeys;
	}

	/**
	 * @return the displayHeight
	 */
	public int getDisplayHeight()
	{
		return displayHeight;
	}

	/**
	 * @param displayHeight the displayHeight to set
	 */
	public void setDisplayHeight(int displayHeight)
	{
		this.displayHeight = displayHeight;
	}

//	public <E extends Event> List<Event> getEvents(Class<E> type, String sourceID)
//	{
//		List<Event> r = new ArrayList();
//		for(Event event : events)
//		{
//			if(event.getClass().equals(type) && event.getSource().getID().equals(sourceID))
//			{
//				r.add(event);
//			}
//		}
//		return r;
//	}

}
