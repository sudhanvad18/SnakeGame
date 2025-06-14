/**
 *	The Snake is a singly linked list with snake behaviors.
 *	@author	Sudhanva Deshpande
 *	@since	May 2, 2023
 */
public class Snake extends SinglyLinkedList<Coordinate> {
	
	// Fields
	private SinglyLinkedList<Coordinate> snake;
	
	/** constructor. Chooses 5 verticle locations for Snake */
	public Snake(Coordinate location) 
	{
		snake = new SinglyLinkedList<Coordinate>();
		for(int i = 0; i < 5; i++)
		{
			snake.add(new Coordinate(location.getRow()+i, location.getCol()));
		}
	}

	/**
	 * @return SinglyLinkedList with snake coordinates
	 */
	public SinglyLinkedList<Coordinate> getSnake() { return snake; }
	
	
}