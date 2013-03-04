import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.*;


@SuppressWarnings("serial")
public class PANEL extends JPanel
{
	public Color boardC = new Color(0,128,0,200);
	public Random gen = new Random();
    public int XD = 1000, YD = 700, betAmount = 0, dealerWorth = 0, playerWorth = 0, potValue = 0;
	public int cardWidth = 72, cardHeight = 96, centerX = XD/2-20, playersX = centerX-50, farLeftCenterX = XD/2-120, itemHeight = 20, playersButtonWidth = 60, averageButtonWidth = 70, sButtonWidth = 100, biggerButtonWidth = 120, valueButtonWidth = 225, bottomButton = YD-20, aboveBottomButton = bottomButton-30, aboveAboveBottomButton = bottomButton-60;
	public JButton bet = new JButton("Bet"), increaseBet = new JButton("Increase Bet"), decreaseBet = new JButton("Decrease Bet"), deal = new JButton("Deal"), hit = new JButton("Hit"), stay = new JButton("Stay"), surrender = new JButton("Surrender");
	public DeckOfCards deck = new DeckOfCards();
	public JLabel player = new JLabel("Player"), comp1 = new JLabel("Computer"), comp2 = new JLabel("Computer"), dealer = new JLabel("Dealer"), betValue = new JLabel("Bet: "+betAmount), dealerValue = new JLabel("Dealer's Worth: "+dealerWorth), playerValue = new JLabel("Player's Worth: "+playerWorth), pot = new JLabel("Current pot value of "+potValue);
	final int MAX_POSSIBLE_HAND = 12;
	public JLabel dealerCard[] = new JLabel[MAX_POSSIBLE_HAND];
	public JLabel playerCard[] = new JLabel[MAX_POSSIBLE_HAND];
	public int dealerIndex = 0, playerIndex = 0;
	
	public Listener listener = new Listener();
	
	public PANEL()
	{
		deck.shuffleDeck();
		setLayout(null);
		addGameItems();
		setItemBounds();
		addListener();
		setPreferredSize(new Dimension(XD,YD));
		setBackground(boardC);
		betValue.setOpaque(true);
	}
	
	public void addGameItems()//adds the players labels and the buttons to the window
	{
		add(dealer);
		add(dealerValue);
		add(player);
		add(playerValue);
		
		for(int i = 0; i < dealerCard.length; i++)
		{
			dealerCard[i] = new JLabel(new ImageIcon());
			add(dealerCard[i]);
		}
			
		
		for(int i = playerCard.length-1; i >= 0; i--)
		{
			playerCard[i] = new JLabel(new ImageIcon());
			add(playerCard[i]);
		}
		
//		add(comp1);
//		add(comp2);
		add(pot);
		add(bet);
		add(betValue);
		add(increaseBet);
		add(decreaseBet);
		add(hit);
		add(stay);
		add(surrender);
		add(deal);
	}
	
	public void addListener()
	{
		bet.addMouseListener(listener);
		increaseBet.addMouseListener(listener);
		decreaseBet.addMouseListener(listener);
		deal.addMouseListener(listener);
		hit.addMouseListener(listener);
		stay.addMouseListener(listener);
		surrender.addMouseListener(listener);
	}
	
	public void setItemBounds()//sets the layout
	{
		dealer.setBounds(centerX, 10, playersButtonWidth, itemHeight);
		dealerValue.setBounds(playersX, 30, valueButtonWidth, itemHeight);
		deal.setBounds(centerX-15, YD/2-45, averageButtonWidth, itemHeight);
		player.setBounds(centerX, YD/4*3, playersButtonWidth, itemHeight);
		playerValue.setBounds(playersX, YD/4*3+20, valueButtonWidth, itemHeight);
		
		for(int i = 0; i < dealerCard.length; i++)
		{
			if(i == 0)
				dealerCard[i].setBounds(centerX-40, 100, cardWidth, cardHeight);
			else
				dealerCard[i].setBounds(((int)dealerCard[i-1].getBounds().getX()+20), ((int)dealerCard[i-1].getBounds().getY()), cardWidth, cardHeight);
		}
		
		for(int i = 0; i < playerCard.length; i++)
		{
			if(i == 0)
				playerCard[i].setBounds(centerX-40, 400, cardWidth, cardHeight);
			else
				playerCard[i].setBounds(((int)playerCard[i-1].getBounds().getX()+20), ((int)playerCard[i-1].getBounds().getY()), cardWidth, cardHeight);
		}
		
//		comp1.setBounds(5, YD/2-50, playersButtonWidth, itemHeight);
//		comp2.setBounds(XD-60, YD/2-50, playersButtonWidth, itemHeight);
		pot.setBounds(centerX-57, YD/2-25, valueButtonWidth, itemHeight);
		bet.setBounds(farLeftCenterX, aboveAboveBottomButton, averageButtonWidth, itemHeight);
		betValue.setBounds(centerX, aboveAboveBottomButton, averageButtonWidth, itemHeight);
		increaseBet.setBounds(farLeftCenterX, aboveBottomButton, biggerButtonWidth, itemHeight);
		decreaseBet.setBounds(XD/2-10, aboveBottomButton, biggerButtonWidth, itemHeight);
		hit.setBounds(farLeftCenterX, bottomButton, averageButtonWidth, itemHeight);
		stay.setBounds(centerX-35, bottomButton, averageButtonWidth, itemHeight);
		surrender.setBounds(XD/2+10, bottomButton, sButtonWidth, itemHeight);
	}
	
	private class Listener implements MouseListener
	{
		public void mouseClicked(MouseEvent e)
		{
			if(e.getSource() == increaseBet)
			{
				betAmount += 5;
				betValue.setText("Bet: "+betAmount);
			}
			if(e.getSource() == decreaseBet)
			{
				betAmount -= 5;
				betValue.setText("Bet: "+betAmount);
			}
			else if(e.getSource() == deal && deal.isEnabled())
			{
				dealerCard[dealerIndex].setIcon(new ImageIcon(deck.findCardImg(deck.dealCard())));
				dealerIndex++;
				
				String hiddenCard = deck.dealCard();
				dealerCard[dealerIndex].setIcon(new ImageIcon(deck.findCardImg("Back")));
				dealerIndex++;
				
				playerCard[playerIndex].setIcon(new ImageIcon(deck.findCardImg(deck.dealCard())));
				playerIndex++;
				
				playerCard[playerIndex].setIcon(new ImageIcon(deck.findCardImg(deck.dealCard())));
				playerIndex++;
				
				deal.setEnabled(false);
			}
			else if(e.getSource() == hit)
			{
				playerCard[playerIndex].setIcon(new ImageIcon(deck.findCardImg(deck.dealCard())));
				playerIndex++;
			}
		}
		public void mouseEntered(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}
		public void mousePressed(MouseEvent e) {}
		public void mouseReleased(MouseEvent e) {}
	}
}
