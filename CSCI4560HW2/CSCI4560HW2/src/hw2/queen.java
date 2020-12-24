package hw2;

public class queen {

	private int maxInt = 2147483647;
	private int minInt = 0;
	public int xPos;//X position of queen object
	public int yPos;//Y position of queen object
	
	public int getXPos()
	{
		return xPos;
	}
	
	public void setXPos(int x)
	{
		this.xPos = x;
	}
	
	public int getYPos()
	{
		return yPos;
	}
	
	public void setYPos(int y)
	{
		this.yPos = y;
	}
	
 queen()// create queen with null values
 {
	
 }
 
 queen(int x, int y)//create queen with specified x and y positions
 {
	if (x >= minInt && x <= maxInt)//make sure int value x is within range
	{
		 this.xPos = x ;
		 
	}
	else 
	{
		 this.xPos = 0 ;
	}
	if (y >= minInt && y <= maxInt)//make sure int value y is within range
	{
		this.yPos = y ;
	}
	else
	{
		this.yPos = 0 ;
	}
 }
 
}
