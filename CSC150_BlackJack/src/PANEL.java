import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;

@SuppressWarnings("serial")
public class PANEL extends JPanel
{
	public GamePlay game = new GamePlay();
	
	public Color boardC = new Color(60,163,63);
	public DeckOfCards deck = new DeckOfCards();
    public int XD = 1000, YD = 700, betAmount = 0, potValue = 0, dealerIndex = 0, playerIndex = 0;
	public int cardWidth = 72, cardHeight = 96, centerX = XD/2-20, playersX = centerX-50, farLeftCenterX = XD/2-120, itemHeight = 20, playersButtonWidth = 60, averageButtonWidth = 70, sButtonWidth = 100, biggerButtonWidth = 120, valueButtonWidth = 225, bottomButton = YD-20, aboveBottomButton = bottomButton-30, aboveAboveBottomButton = bottomButton-60;
	public JButton bet = new JButton("Bet"), increaseBet = new JButton("Increase Bet"), decreaseBet = new JButton("Decrease Bet"), deal = new JButton("Deal"), hit = new JButton("Hit"), stay = new JButton("Stay"), surrender = new JButton("Surrender");
	public JLabel player = new JLabel("Player"), comp1 = new JLabel("Computer"), comp2 = new JLabel("Computer"), dealer = new JLabel("Dealer"), betValue = new JLabel("Bet: "+betAmount), dealerValue = new JLabel("Dealer's Worth: "+game.dealer.wallet), playerValue = new JLabel("Player's Worth: "+game.player.wallet), pot = new JLabel("Current pot value of "+potValue);
	public final int MAX_POSSIBLE_HAND = 12;
	public JLabel dealerCard[] = new JLabel[MAX_POSSIBLE_HAND], playerCard[] = new JLabel[MAX_POSSIBLE_HAND];
	public Listener listener = new Listener();
	
	public PANEL()
	{
		deck.shuffleDeck();
		setLayout(null);
		addGameItems();
		setItemBounds();
		addListener();
		panelSpecifics();
	}
	
	public void panelSpecifics()//sets the window dimension, background color, and other specifics
	{
		setPreferredSize(new Dimension(XD,YD));
		setBackground(boardC);
		betValue.setOpaque(true);
		enableGame(false);
	}
	
	public void addGameItems()//adds the players labels and the buttons to the window
	{
		addPlayers();
		addCards();
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
	
	private void addPlayers()
	{
		add(dealer);
		add(dealerValue);
		add(player);
		add(playerValue);
//		add(comp1);
//		add(comp2);
	}
	
	private void addCards()
	{
		for(int i = dealerCard.length-1; i >= 0; i--)
		{
			dealerCard[i] = new JLabel(new ImageIcon());
			add(dealerCard[i]);
		}
			
		for(int i = playerCard.length-1; i >= 0; i--)
		{
			playerCard[i] = new JLabel(new ImageIcon());
			add(playerCard[i]);
		}
	}
	
	public void addListener()//buttons receive their listeners
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
		setPlayerBounds();
		setCardBounds();
		pot.setBounds(centerX-57, YD/2-25, valueButtonWidth, itemHeight);
		bet.setBounds(farLeftCenterX, aboveAboveBottomButton, averageButtonWidth, itemHeight);
		betValue.setBounds(centerX, aboveAboveBottomButton, averageButtonWidth, itemHeight);
		increaseBet.setBounds(farLeftCenterX, aboveBottomButton, biggerButtonWidth, itemHeight);
		decreaseBet.setBounds(XD/2-10, aboveBottomButton, biggerButtonWidth, itemHeight);
		hit.setBounds(farLeftCenterX, bottomButton, averageButtonWidth, itemHeight);
		stay.setBounds(centerX-35, bottomButton, averageButtonWidth, itemHeight);
		surrender.setBounds(XD/2+10, bottomButton, sButtonWidth, itemHeight);
	}
	
	private void setPlayerBounds()
	{
		dealer.setBounds(centerX, 10, playersButtonWidth, itemHeight);
		dealerValue.setBounds(playersX, 30, valueButtonWidth, itemHeight);
		deal.setBounds(centerX-15, YD/2-45, averageButtonWidth, itemHeight);
		player.setBounds(centerX, YD/4*3, playersButtonWidth, itemHeight);
		playerValue.setBounds(playersX, YD/4*3+20, valueButtonWidth, itemHeight);
//		comp1.setBounds(5, YD/2-50, playersButtonWidth, itemHeight);
//		comp2.setBounds(XD-60, YD/2-50, playersButtonWidth, itemHeight);
	}
	
	private void setCardBounds()
	{
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
	}
	
	public void enableGame(boolean g)//allows for certain buttons to be enabled
	{
		hit.setEnabled(g);
		stay.setEnabled(g);
	}
	
	private void updateValues()
	{
		playerValue.setText("Player's Worth: "+game.player.wallet);
		dealerValue.setText("Dealer's Worth: "+game.dealer.wallet);
		pot.setText("Current pot value of "+potValue);
		betValue.setText("Bet: "+betAmount);
	}
	
	private void setCards()
	{
		game.dealer.setHand(game.deck.dealCard());
		game.dealer.setHand(game.deck.dealCard());
		game.player.setHand(game.deck.dealCard());
		game.player.setHand(game.deck.dealCard());
	}
	
	private void dealCards()
	{
		dealerCard[dealerIndex].setIcon(new ImageIcon(game.deck.findCardImg(game.dealer.getHand(dealerIndex))));
		dealerIndex++;
		
		dealerCard[dealerIndex].setIcon(new ImageIcon(game.deck.findCardImg("Back")));
		dealerIndex++;
		
		playerCard[playerIndex].setIcon(new ImageIcon(game.deck.findCardImg(game.player.getHand(playerIndex))));
		playerIndex++;
		
		playerCard[playerIndex].setIcon(new ImageIcon(game.deck.findCardImg(game.player.getHand(playerIndex))));
		playerIndex++;
	}
	
	private void newGame()
	{
		game.deck = new DeckOfCards();
		game.deck.shuffleDeck();
		playerIndex = 0;
		dealerIndex = 0;
		enableGame(false);
		deal.setEnabled(true);
		game.dealer.wallet += potValue;
		betAmount = 0;
		potValue = 0;
		updateValues();
	}
	
	private class Listener implements MouseListener//where the magic happens
	{
		public void mouseClicked(MouseEvent e)
		{
			if(e.getSource() == increaseBet)
			{
				if(game.player.wallet > betAmount)
					betAmount += 5;
				betValue.setText("Bet: "+betAmount);
			}
			else if(e.getSource() == decreaseBet && betAmount >=1)
			{
				if(game.player.wallet > 0)
					betAmount -= 5;
				betValue.setText("Bet: "+betAmount);
			}
			else if(e.getSource() == bet && game.player.wallet >= betAmount && game.dealer.wallet >= betAmount)
			{
				if(betAmount > 0)
				{
					game.player.wallet -= betAmount;
					game.dealer.wallet -= betAmount;
				}
				potValue += betAmount*2;
				betAmount = 0;
				updateValues();
			}
			else if(e.getSource() == deal && deal.isEnabled())
			{
				setCards();
				dealCards();
				
				enableGame(true);
				deal.setEnabled(false);
			}
			else if(e.getSource() == hit && hit.isEnabled() && playerIndex <= playerCard.length-1)
			{
				game.player.setHand(game.deck.dealCard());
				playerCard[playerIndex].setIcon(new ImageIcon(game.deck.findCardImg(game.player.getHand(playerIndex))));
				playerIndex++;
			}
			else if (e.getSource() == surrender)
			{
				for(int i = 0; i<playerCard.length; i++)
				{
					playerCard[i].setIcon(new ImageIcon());
				}
				for(int i = 0; i<dealerCard.length; i++)
				{
					dealerCard[i].setIcon(new ImageIcon());
				}
				newGame();
			}
		}
		public void mouseEntered(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}
		public void mousePressed(MouseEvent e) {}
		public void mouseReleased(MouseEvent e) {}
	}
}
