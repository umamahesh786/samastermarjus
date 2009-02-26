package Application;
import Model.Item;
import Model.Guide;
import Persistence.DB4ORepository;
import Persistence.Repository;


public class Program {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Repository repository = new DB4ORepository();
		Guide temp = new Guide("Cheese");
		temp.getItems().add(new Item("milk",4));
		temp.getItems().add(new Item("cream",2));		
		temp.getItems().add(new Item("caraway ",1));
		repository.storeTemplate(temp);
		
		Guide temp2 = new Guide("cookie");
		temp2.getItems().add(new Item("milk",4));
		temp2.getItems().add(new Item("coco",2));		
		temp2.getItems().add(new Item("chocolate",1));
		temp2.getItems().add(new Item("flour",2));
		repository.storeTemplate(temp2);
		
		for(Item item: temp.getItems())
		{
			repository.storeItem(item);
		}
		for(Item item: temp2.getItems())
		{
			repository.storeItem(item);
		}
		
		Guide load = new Guide(null,false);
		load.getItems().add(new Item("chocolate",0,false));
		
		Item obj = repository.loadItemByName("chocolate");
		Guide obj2 = repository.loadGuideByName("Cheese");
		Guide obj3 = repository.loadGuideById(temp.getIdentifier());
		Guide[] goods =repository.loadAllGuides();
		
	}

}
