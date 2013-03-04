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
	public JLabel dealerCard1 = new JLabel(new ImageIcon()), dealerCard2 = new JLabel(new ImageIcon()), playerCard1 = new JLabel(new ImageIcon()), playerCard2 = new JLabel(new ImageIcon()), playerCard3 = new JLabel(new ImageIcon()), player = new JLabel("Player"), comp1 = new JLabel("Computer"), comp2 = new JLabel("Computer"), dealer = new JLabel("Dealer"), betValue = new JLabel("Bet: "+betAmount), dealerValue = new JLabel("Dealer's Worth: "+dealerWorth), playerValue = new JLabel("Player's Worth: "+playerWorth), pot = new JLabel("Current pot value of "+potValue);
	public Listener listener = new Listener();
	
	public PANEL()
	{
		setLayout(null);
		addGameItems();
		setItemBounds();
		addListener();
		setPreferredSize(new Dimension(XD,YD));
		setBackground(boardC);
	}
	
	public void addGameItems()//adds the players labels and the buttons to the window
	{
		add(dealer);
		add(dealerValue);
		add(player);
		add(playerValue);
		
		add(dealerCard2);
		add(dealerCard1);

		add(playerCard3);
		add(playerCard2);
		add(playerCard1);
		
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
		
		dealerCard1.setBounds(centerX-40, 100, cardWidth, cardHeight);
		dealerCard2.setBounds(centerX-20, 100, cardWidth, cardHeight);
		playerCard1.setBounds(centerX-40, 400, cardWidth, cardHeight);
		playerCard2.setBounds(centerX-20, 400, cardWidth, cardHeight);
		playerCard3.setBounds(centerX, 400, cardWidth, cardHeight);
		
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
				betAmount += 20;
				betValue.setText("Bet: "+betAmount);
			}
			else if(e.getSource() == deal)
			{
				dealerCard1.setIcon(new ImageIcon(deck.getCardImg(gen.nextInt(52))));
				dealerCard2.setIcon(new ImageIcon(deck.getCardImg(52)));
				playerCard1.setIcon(new ImageIcon(deck.getCardImg(gen.nextInt(52))));
				playerCard2.setIcon(new ImageIcon(deck.getCardImg(gen.nextInt(52))));
			}
			else if(e.getSource() == hit)
			{
				playerCard3.setIcon(new ImageIcon(deck.getCardImg(gen.nextInt(52))));
			}
		}
		public void mouseEntered(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}
		public void mousePressed(MouseEvent e) {}
		public void mouseReleased(MouseEvent e) {}
	}
}
