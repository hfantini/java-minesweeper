package codecastle.game.mine;

import javax.swing.JButton;

public class Button extends JButton
{
	public enum ObjectType
	{
		TYPE_NORMAL,
		TYPE_MINE,
	}
	
	private int posX;
	private int posY;
	private ObjectType type;
	private Boolean revealed = false;

	public int getPosX() 
	{
		return posX;
	}

	public void setPosX(int posX)
{
		this.posX = posX;
	}

	public int getPosY()
	{
		return posY;
	}

	public void setPosY(int posY) 
	{
		this.posY = posY;
	}

	public ObjectType getType() 
	{
		return type;
	}

	public void setType(ObjectType type) 
	{
		this.type = type;
	}

	@Override
	public String toString()
	{
		return this.getName();
	}

	public Boolean getRevealed()
	{
		return revealed;
	}

	public void setRevealed(Boolean revealed)
	{
		this.revealed = revealed;
	}

	
	
}
