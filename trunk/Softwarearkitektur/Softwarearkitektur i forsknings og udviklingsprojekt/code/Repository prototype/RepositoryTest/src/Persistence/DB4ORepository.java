package Persistence;



import com.db4o.*;
import java.util.*;


import Model.Item;
import Model.Rating;
import Model.Guide;

public class DB4ORepository implements Repository {
 
	private ObjectContainer db;
	public DB4ORepository() {
			 this.db= Db4o.openFile("Test.db");
	}
	
	@Override
	protected void finalize() throws Throwable {
		
		this.db.close();	
	
		super.finalize();
	}
	

	@Override
	public Guide[] loadAllGuides() {
		ObjectSet<Guide> result = this.db.query(Guide.class);
		List <Guide> templates = new ArrayList<Guide>();
		while(result.hasNext())
		{
			templates.add(result.next());
			
		}
		return templates.toArray(new Guide[templates.size()]);
	}

	@Override
	public Item loadItemById(UUID id) {
		
		ObjectSet<Item> result = this.db.queryByExample(new Item(id));
		
		while(result.hasNext())
		{
			return result.next();
			
		}
		
		return null;
	}

	@Override
	public Item loadItemByName(String name) {
		
		ObjectSet<Item> result = this.db.queryByExample(new Item(name,0,false));
		
		while(result.hasNext())
		{
			return result.next();
			
		}
		
		return null;
	}

	@Override
	public Guide loadGuideByDescription(String description) {
	
		Guide t = new Guide(null,false);
		t.setDescription(description);
		ObjectSet<Guide> result = this.db.queryByExample(t);
		
		while(result.hasNext())
		{
			return result.next();
		}
		
		return null;
	}

	@Override
	public Guide loadGuideById(UUID id) {
	ObjectSet<Guide> result = this.db.queryByExample(new Guide(id));
		
		while(result.hasNext())
		{
			return result.next();
			
		}
		
		return null;
	}

	@Override
	public Guide loadGuideByName(String name) {
	ObjectSet<Guide> result = this.db.queryByExample(new Guide(name,false));
		
		while(result.hasNext())
		{
			return result.next();
			
		}
		
		return null;
	}

	@Override
	public Guide[] loadGuidesByMinUserRating(Rating rating) {

		Guide ratingBased = new Guide(null,false);
		ratingBased.setUserRating(rating);
		ObjectSet<Guide> result = this.db.queryByExample(ratingBased);
		List <Guide> templates = new ArrayList<Guide>();
		while(result.hasNext())
		{
			templates.add(result.next());
			
		}
		return templates.toArray(new Guide[templates.size()]);
	}

	

	@Override
	public void storeItem(Item obj) {
		this.db.store(obj);
		this.db.commit();
	}

	@Override
	public void storeTemplate(Guide obj) {
		this.db.store(obj);
		this.db.commit();
		
	}

}
