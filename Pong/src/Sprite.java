import java.awt.Color;
import java.awt.Rectangle;
public class Sprite 

{
	private int xPosition;
	private int yPosition;
	private int xVelocity;
	private int yVelocity;
	private int initialXPosition;
	private int initialYPosition;
	private int width;
	private int height; 
	private Color colour;
	//	Getter Methods
	public Color getColour() 
	{
		return colour;
	}
	public int getXPosition() 
	{
		return xPosition;
	}
	
	public int getYPosition() 
	{
		return yPosition;
	}
	
	public int getXVelocity() 
	{
		return xVelocity;
	}
	
	public int getYVelocity() 
	{
		return yVelocity;
	}
	
	public int getWidth() 
	{
		return width;
	}
	
	public int getHeight() 
	{
		return height;
	}


	//Setter Methods
	public void setInitialPosition(int newInitialXPosition, int newInitialYPosition) 
	{
	this.initialXPosition = newInitialXPosition;
	this.initialYPosition = newInitialYPosition;
	}
	public void resetToInitialPosition() 
	{
		setXPosition(initialXPosition);
		setYPosition(initialYPosition);
	}
	public void setColor(Color newcolour) 
	{
		this.colour = newcolour;
	}

	public void setXPosition(int newxPosition) 
	{
		this.xPosition = newxPosition;
	}
	public void setYPosition(int newyPosition) 
	{
		this.yPosition = newyPosition;
	}
	public void setXVelocity(int newxVelocity) 
	{
		this.xVelocity = newxVelocity;
	}
	public void setYVelocity(int newyVelocity) 
	{
		this.yVelocity = newyVelocity;
	}
	public void setWidth(int newWidth) 
	{
		this.width = newWidth;
	}
	public void setHeight(int newHeight) 
	{
		this.height = newHeight;
	}
	public void setXPosition(int newX, int panelWidth) 
	{
	     xPosition = newX;
	     if(xPosition < 0) 
	     {
	           xPosition = 0;
	     } 
	     else if(xPosition + width > panelWidth) 
	     {
	           xPosition = panelWidth - width;
	     }
	 }
	 
	public void setYPosition(int newY, int panelHeight)
	{
	     yPosition = newY;
	     if(yPosition < 0) 
	     {
	          yPosition = 0;
	     } 
	     else if(yPosition + height > panelHeight) 
	     {
	          yPosition = panelHeight - height;
	     }
	}
	 public Rectangle getRectangle() 
	 {
         return new Rectangle(getXPosition(), getYPosition(), getWidth(), getHeight());
     }


		
		
		
		 
}

