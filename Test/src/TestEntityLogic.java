import com.gray.lightcycles.logic.entity.Entity;
import com.gray.lightcycles.logic.game.Game;
import com.gray.lightcycles.logic.math.Vector2d;
import org.junit.Assert;
import org.junit.Test;


public class TestEntityLogic
{
	@Test
	public void turnTest()
	{
		//Create an entity and force it through every turn possibility
		try {
			Entity entity = new Entity(new Vector2d(0,0), new Vector2d(0,0), Entity.DIR_UP);

			entity.turn(Entity.LEFT);
			Assert.assertEquals(Entity.DIR_LEFT, entity.getDir());
			entity.turn(Entity.LEFT);
			Assert.assertEquals(Entity.DIR_DOWN, entity.getDir());
			entity.turn(Entity.LEFT);
			Assert.assertEquals(Entity.DIR_RIGHT, entity.getDir());
			entity.turn(Entity.LEFT);
			Assert.assertEquals(Entity.DIR_UP, entity.getDir());

			entity.turn(Entity.RIGHT);
			Assert.assertEquals(Entity.DIR_RIGHT, entity.getDir());
			entity.turn(Entity.RIGHT);
			Assert.assertEquals(Entity.DIR_DOWN, entity.getDir());
			entity.turn(Entity.RIGHT);
			Assert.assertEquals(Entity.DIR_LEFT, entity.getDir());
			entity.turn(Entity.RIGHT);
			Assert.assertEquals(Entity.DIR_UP, entity.getDir());

		} catch (Exception exception) {
			Assert.fail(exception.getMessage());
		}
	}

	@Test
	public void derezzTest()
	{
		//Create a new game and add a player that is outside the board. On the update, the player should derezz
		try
		{
			Game game = new Game(100);
			game.addPlayer(101, 101, "Test");
			game.update(0.02);
			Assert.assertEquals(true, game.getPlayer("Test").isDead());
		}
		catch(Exception e)
		{
			Assert.fail(e.getMessage());
		}
	}
}
