import java.awt.Color;
public class Paddle extends Sprite 
{
	private static final int  PADDLE_WIDTH = 10;
	private static final int  PADDLE_HEIGHT = 100;
	private static final Color PADDLE_COLOUR = Color.WHITE;
	private static final int PADDLE_DISTANCE_FROM_EDGE = 40; 
	
	
	Paddle(int panelWidth, int panelHeight,Player player) {
		setWidth(PADDLE_WIDTH);
	       setHeight( PADDLE_HEIGHT);
	       setColor(PADDLE_COLOUR);
	       int xPos;
	          if(player == Player.One) {
	              xPos = PADDLE_DISTANCE_FROM_EDGE;
	          } else {
	              xPos = panelWidth - PADDLE_DISTANCE_FROM_EDGE - getWidth();
	          }
	          setInitialPosition(xPos, panelHeight / 2 - (getHeight() / 2));
	          resetToInitialPosition();
	}
}
