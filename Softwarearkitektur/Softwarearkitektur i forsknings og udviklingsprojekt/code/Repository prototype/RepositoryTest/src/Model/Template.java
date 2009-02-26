package Model;
import java.util.*;


public class Template implements DomainObject {

	private String name;
	private UUID id = null;
	private List <Item> items = new ArrayList<Item>();
	private String description = "";
	private String userComment = "";
	private Rating userRating = Rating.None;
	private String templateCategory ="";
	
	

	public Template(String name) {
		this(true);
		this.name = name;
		
	}
	
	public Template(String name, Boolean generateId) {
		this(generateId);
		this.name = name;
		
	}
	
	public Template(Boolean generateId)
	{
		if(generateId)
		{
			this.id = UUID.randomUUID();
		}
	}

	public Template(UUID id) {
		this.id = id;
	}

	@Override
	public UUID getIdentifier() {
		
		return this.id;
	}
	public String getName()
	{
		return this.name;
	}
	
	public List <Item> getItems()
	{
		return this.items;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param userComment
	 *            the userComment to set
	 */
	public void setUserComment(String userComment) {
		this.userComment = userComment;
	}

	/**
	 * @return the userComment
	 */
	public String getUserComment() {
		return userComment;
	}

	/**
	 * @param userRating
	 *            the userRating to set
	 */
	public void setUserRating(Rating userRating) {
		this.userRating = userRating;
	}

	/**
	 * @return the userRating
	 */
	public Rating getUserRating() {
		return userRating;
	}

	/**
	 * @param templateCategory
	 *            the templateCategory to set
	 */
	public void setTemplateCategory(String templateCategory) {
		this.templateCategory = templateCategory;
	}

	/**
	 * @return the templateCategory
	 */
	public String getTemplateCategory() {
		return templateCategory;
	}

	
}

