package Persistence;
import java.util.UUID;

import Model.*;

public interface Repository {
	
	public void storeTemplate(Guide obj);
	
	public void storeItem(Item obj);
	
	public Item loadItemById(UUID name);
	public Item loadItemByName(String name);
	public Guide loadGuideByName(String name);
	public Guide loadGuideByDescription(String descriptionSample);
	public Guide loadGuideById(UUID id);
	public Guide[] loadAllGuides();
	public Guide[] loadGuidesByMinUserRating(Rating rating);

}


