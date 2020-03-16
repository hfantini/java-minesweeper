package codecastle.game.mine;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import java.awt.GridLayout;

import javax.swing.JButton;

import codecastle.game.mine.Button.ObjectType;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;

import java.awt.Font;

public class Main extends JFrame 
{
	
	private JPanel pnlGameContainer;
	private Button[][] mineArray = new Button[15][15];
	Random randomGenerator = new Random();

	public Main() 
	{
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 15 * 24 + 6, 24 * 15 + 6);
		pnlGameContainer = new JPanel();
		pnlGameContainer.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(pnlGameContainer);
		pnlGameContainer.setLayout(null);
		
		generateNewGame();
	}
	
	public void generateNewGame()
	{
		for(int countX = 0 ; countX < 15 ; countX++)
		{			
			for(int countY = 0 ; countY < 15 ; countY++)
			{
				int X = countX * 24;
				int Y = countY * 24;
				
				Button btn = new Button();
				btn.setPosX(countX);
				btn.setPosY(countY);
				btn.setName("BTN_" + countX + countY);
				btn.setBounds(X, Y, 24, 24);
				btn.setType(ObjectType.TYPE_NORMAL);
				btn.setFocusable(false);
				btn.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent evt) 
					{
						resolveAction(btn);
					}
				});
				mineArray[countX][countY] = btn;
				pnlGameContainer.add(btn);
			}
		}
	
		for(int count = 0 ; count < 35 ; count++)
		{
			Boolean minePlaced = false;
			
			do
			{
				int mineX = getRandomInt(0, 14);
				int mineY = getRandomInt(0, 14);
				
				if(mineArray[mineX][mineY].getType() == ObjectType.TYPE_NORMAL)
				{
					mineArray[mineX][mineY].setType(ObjectType.TYPE_MINE);
					minePlaced = true;
				}
			}
			while(!minePlaced);
		}
	}
	
	private void resolveAction(Button btn)
	{
		btn.setRevealed(true);
		
		if(btn.getType() != ObjectType.TYPE_MINE)
		{
			List<Button> btnAround = new ArrayList<Button>();
			
			for(int count = 0 ; count < 8 ; count++)
			{
				try
				{
					switch(count)
					{
						case 0:
							btnAround.add(mineArray[btn.getPosX() - 1][btn.getPosY() - 1]);
							break;
							
						case 1:
							btnAround.add(mineArray[btn.getPosX()][btn.getPosY() - 1]);
							break;		
							
						case 2:
							btnAround.add(mineArray[btn.getPosX() + 1][btn.getPosY() - 1]);
							break;	
							
						case 3:
							btnAround.add(mineArray[btn.getPosX() - 1][btn.getPosY()]);
							break;			
							
						case 4:
							btnAround.add(mineArray[btn.getPosX() + 1][btn.getPosY()]);
							break;
							
						case 5:
							btnAround.add(mineArray[btn.getPosX() - 1][btn.getPosY() + 1]);
							break;	
							
						case 6:
							btnAround.add(mineArray[btn.getPosX()][btn.getPosY() + 1]);
							break;	
							
						case 7:
							btnAround.add(mineArray[btn.getPosX() + 1][btn.getPosY() + 1]);
							break;										
					}
				}
				catch(Exception e)
				{
					
				}
			}
			
			int minesAround = 0;
			
			for(Button button : btnAround)
			{				
				if(button.getType() == ObjectType.TYPE_MINE)
				{
					minesAround++;
				}
			}
			
			if(minesAround > 0)
			{
				btn.setIcon(new ImageIcon(Main.class.getResource("/codecraft/game/mine/img/" + minesAround + ".png")));
			}
			else
			{
				btn.setEnabled(false);
				
				for(Button button : btnAround)
				{
					//update(getGraphics());
					
					if( button.getRevealed() == false )
					{
						resolveAction(button);
					}
				}
			}
		}
		else
		{
			for(int countX = 0 ; countX < 15 ; countX++)
			{			
				for(int countY = 0 ; countY < 15 ; countY++)
				{
					if( mineArray[countX][countY].getType() == ObjectType.TYPE_MINE )
					{
						mineArray[countX][countY].setIcon(new ImageIcon(Main.class.getResource("/codecraft/game/mine/img/bomb.png")));
						mineArray[countX][countY].setEnabled(false);
					}
					else
					{
						mineArray[countX][countY].setVisible(false);
					}
				}
			}
			
			
		}
	}
	
	private int getMinesAround(Button btn)
	{
		int retValue = 0;
	
		
		if(mineArray[btn.getPosX()][btn.getPosY() + 1].getType() == ObjectType.TYPE_MINE)
		{
			retValue++;
		}		
		
		if(mineArray[btn.getPosX() + 1][btn.getPosY() + 1].getType() == ObjectType.TYPE_MINE)
		{
			retValue++;
		}		
		
		return retValue;
	}
	
	private int getRandomInt(int aStart, int aEnd)
	{
		  
      Random random = new Random();
	
	  long range = (long)aEnd - (long)aStart + 1;
	  long fraction = (long)(range * random.nextDouble());
	  int randomNumber =  (int)(fraction + aStart);    
	    
	  return randomNumber;
	    
	}	
	
	public static void main(String[] args) throws Exception
	{

        UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName() );

		
		Main main = new Main();
		main.setVisible(true);
	}
}
