/**
 *	A coordinate on a grid. Integer XY values.
 *
 *	@author Sudhanva Deshpande
 *	@since	May 2, 2023
 */
public class Coordinate implements Comparable<Coordinate>
{
	private int row; //row of coordinate
	private int col; //column of coordinate
		
	/* constructor */
	public Coordinate(int r, int c)
	{
		row = r;
		col = c;
	}
	
	/* Accessor methods */

	/**
	 * @return row of coordinate
	 */
	public int getRow() { return row; } 

	/**
	 * @return column of coordinate
	 */
	public int getCol() { return col; }
	
	/**
	 *	Overridden equals method. Checks to see if other Coordinate is equal to this
	 *	@param	other other coordinate
	 *	@return	true if both coordinates match; false otherwise 
	 */

	@Override
	public boolean equals(Object other)
	{
		if (other != null && other instanceof Coordinate && row == ((Coordinate)other).row && col == ((Coordinate)other).col)
			return true;
		return false;
	}

	/**
	 * Overridden comapreTo method. Compares this object to other
	 * @return this comparedTo other
	 */

	@Override
	public int compareTo(Coordinate other)
	{
		return this.compareTo(other);
	}
	
}
