import javax.swing.Timer;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.BasicStroke;
import java.awt.Font;

public class PongPanel extends JPanel implements ActionListener, KeyListener 
{
	private final static Color BACKGROUND_COLOR = Color.BLACK;
	private final static int TIMER_DELAY = 5;

	private static final int BALL_MOVEMENT_SPEED = 3;
	private final static int POINTS_TO_WIN = 10;
	private final static int PADDLE_SPEED = 3;

	int player1Score = 0, player2Score = 0;
	Player gameWinner;
	Ball ball;
	Paddle paddle1;
	Paddle paddle2;
	GameState gameState = GameState.Initialising;

	

	public PongPanel()
	{
	 setBackground(BACKGROUND_COLOR);
	 Timer timer = new Timer(TIMER_DELAY, this);
	 timer.start();
	 addKeyListener(this);
	 setFocusable(true);
	}
	private void addScore(Player player) 
	{
		if(player == Player.One) {
			player1Score++;
		}
		else if(player == Player.Two) 
		{
			player2Score++;
		}
	}
	private void checkWin() 
	{
		if(player1Score >= POINTS_TO_WIN) 
		{
			gameWinner = Player.One;
			gameState = GameState.GameOver;
		}
		else if(player2Score >= POINTS_TO_WIN)
		{
			gameWinner = Player.Two;
			gameState = GameState.GameOver;
		}
	}
	public void createObjects() 
	{
		ball = new Ball(getWidth(),getHeight());
		paddle1 = new Paddle(getWidth(), getHeight(), Player.One);
		paddle2 = new Paddle(getWidth(), getHeight(), Player.Two);
	}
	private void paintScores(Graphics g) 
	{
		int xPadding = 100;
		int yPadding = 100;
		int fontSize = 50;
		Font scoreFont = new Font("Serif", Font.BOLD, fontSize);
		String leftScore = Integer.toString(player2Score);
		String rightScore = Integer.toString(player1Score);
		g.setFont(scoreFont);
		g.drawString(leftScore, xPadding, yPadding);
		g.drawString(rightScore, getWidth()-xPadding, yPadding);
	}
	private void paintWinner(Graphics g) 
	{
		int ypadding = 200;
		int xpadding = 100;
		int fontSize = 50;
		Font scoreFont = new Font("Serif", Font.BOLD, fontSize);
		String winText = "Winner!";
				
				
		if(player1Score >= POINTS_TO_WIN) 
		{
			g.setFont(scoreFont);
			g.drawString(winText, getWidth()-xpadding, ypadding);
		}
		else if(player2Score >= POINTS_TO_WIN)
		{
			
			g.setFont(scoreFont);
			g.drawString(winText, xpadding, ypadding);
		}
    }

	@Override
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		paintDottedLine(g);
		if(gameState != GameState.Initialising ) 
		{
	          paintSprite(g, ball);
	          paintSprite(g, paddle1);
	          paintSprite(g, paddle2);
	          paintScores(g);
	          paintWinner(g);
	    }
		
	}
	@Override
	public void keyTyped(KeyEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent event) 
	{
		if(event.getKeyCode() == KeyEvent.VK_UP) 
		{
			paddle2.setYVelocity(-PADDLE_SPEED);	
		}
		else if(event.getKeyCode() == KeyEvent.VK_DOWN) 
		{
			paddle2.setYVelocity(PADDLE_SPEED);
		}
		else if(event.getKeyCode() == KeyEvent.VK_W) 
		{
			paddle1.setYVelocity(-PADDLE_SPEED);
		}
		else if(event.getKeyCode() == KeyEvent.VK_S) 
		{
			paddle1.setYVelocity(PADDLE_SPEED);
		}
	}

	@Override
	public void keyReleased(KeyEvent event) 
	{
		if(event.getKeyCode() == KeyEvent.VK_UP || event.getKeyCode() == KeyEvent.VK_DOWN) 
		{
			paddle2.setYVelocity(0);
		}
		else if(event.getKeyCode() == KeyEvent.VK_W || event.getKeyCode() == KeyEvent.VK_S) 
		{
			paddle1.setYVelocity(0);
		}
	}
	
	private void update() 
	{
		 switch(gameState) 
		 {
         	case Initialising: 
         	{
             createObjects();
             gameState = GameState.Playing;
             ball.setXVelocity(BALL_MOVEMENT_SPEED);
             ball.setYVelocity(BALL_MOVEMENT_SPEED);
             break;
         	}
    
         	case Playing: 
         	{
         	 moveObject(paddle1);
         	 moveObject(paddle2);
        	 moveObject(ball);
        	 checkWallBounce();
        	 checkPaddleBounce();
        	 checkWin();
             break;
         	}
            case GameOver: 
         	 break;
         }
   }

	 
	private void resetBall() 
	{
		ball.resetToInitialPosition();
	}
	private void paintSprite(Graphics g, Sprite sprite) 
	 {
	      g.setColor(sprite.getColour());
	      g.fillRect(sprite.getXPosition(), sprite.getYPosition(), sprite.getWidth(), sprite.getHeight());
	 }

	@Override
	public void actionPerformed(ActionEvent event) 
	{
		
		// TODO Auto-generated method stub
		update();
		repaint();
	}
	 private void paintDottedLine(Graphics g) 
	 {
	      Graphics2D g2d = (Graphics2D) g.create();
	         Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
	         g2d.setStroke(dashed);
	         g2d.setPaint(Color.WHITE);
	         g2d.drawLine(getWidth() / 2, 0, getWidth() / 2, getHeight());
	         g2d.dispose();
	 }
	 private void moveObject(Sprite object) 
	 {
		 object.setXPosition((object.getXPosition()+object.getXVelocity()), getWidth());
		 object.setYPosition((object.getYPosition()+object.getYVelocity()), getWidth());
		 
	 }
	 private void checkWallBounce() 
	 {
		if(ball.getXPosition() <=0)
			//hit left of screen
		{
			ball.setXVelocity(-ball.getXVelocity());
			resetBall();
			addScore(Player.One);
		}
		else if(ball.getXPosition() >= getWidth()-ball.getWidth()) 
			//hit right of screen
		{
			ball.setXVelocity(-ball.getXVelocity());
			resetBall();
			addScore(Player.Two);
		}
		if(ball.getYPosition() <=0 || ball.getYPosition() >= getHeight()-ball.getHeight()) 
		{
			ball.setYVelocity(-ball.getYVelocity());
		}
	 }
	 private void checkPaddleBounce()
	 {
		if(ball.getXVelocity() < 0 && ball.getRectangle().intersects(paddle1.getRectangle())) 
		{ 
			ball.setXVelocity(BALL_MOVEMENT_SPEED);
		}
		if(ball.getXVelocity() > 0 && ball.getRectangle().intersects(paddle2.getRectangle())) 
		{
			ball.setXVelocity(-BALL_MOVEMENT_SPEED);
		}
	 }
}
