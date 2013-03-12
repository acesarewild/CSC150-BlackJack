import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;

@SuppressWarnings("serial")
public class Panel extends JPanel
{
	public GamePlay game = new GamePlay();
	public Color boardC = new Color(60,163,63);
	public int bjwin = 50, turn = 0, XD = 1000, YD = 700, betAmount = 0, potValue = 0, dealerIndex = 0, playerIndex = 0, handTotal = 0, dealerHT = 0, blackjack = 21, softDealer = 17;
	public int cardWidth = 72, cardHeight = 96, centerX = XD/2-20, playersX = centerX-50, farLeftCenterX = XD/2-120, itemHeight = 20, playersButtonWidth = 60, averageButtonWidth = 70, sButtonWidth = 100, biggerButtonWidth = 120, valueButtonWidth = 225, bottomButton = YD-20, aboveBottomButton = bottomButton-30, aboveAboveBottomButton = bottomButton-60;
	public JButton newgame = new JButton("New Game"), bet = new JButton("Bet"), increaseBet = new JButton("Increase Bet"), decreaseBet = new JButton("Decrease Bet"), deal = new JButton("Deal"), hit = new JButton("Hit"), stay = new JButton("Stay"), newHand = new JButton("New Hand");
	public JLabel handTest = new JLabel("Hand Value: "+handTotal), /*dHandTest = new JLabel("Dealer Hand Value: "+dealerHT),*/ player = new JLabel("Player"), comp1 = new JLabel("Computer"), comp2 = new JLabel("Computer"), dealer = new JLabel("Dealer"), betValue = new JLabel("Bet: "+betAmount), dealerValue = new JLabel("Dealer's Worth: "+game.getDealer().getWallet()), playerValue = new JLabel("Player's Worth: "+game.getPlayer().getWallet()), pot = new JLabel("Current pot value of "+potValue);
	public final int MAX_POSSIBLE_HAND = 12;
	public JLabel dealerCard[] = new JLabel[MAX_POSSIBLE_HAND], playerCard[] = new JLabel[MAX_POSSIBLE_HAND];
	public Listener listener = new Listener();

	public Panel()
	{
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
		add(newHand);
		add(deal);
		add(newgame);
	}

	private void addPlayers()
	{
		add(dealer);
		add(dealerValue);
		add(player);
		add(playerValue);
		add(handTest);
		//		add(dHandTest);
		//		add(comp1);
		//		add(comp2);
	}

	private void addCards()//reserves space on game board for card images
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
		newHand.addMouseListener(listener);
		newgame.addMouseListener(listener);
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
		newHand.setBounds(XD/2+10, bottomButton, sButtonWidth, itemHeight);
		newgame.setBounds(0, 0, sButtonWidth, itemHeight);
	}

	private void setPlayerBounds()
	{
		dealer.setBounds(centerX, 10, playersButtonWidth, itemHeight);
		dealerValue.setBounds(playersX, 30, valueButtonWidth, itemHeight);
		deal.setBounds(centerX-15, YD/2-45, averageButtonWidth, itemHeight);
		player.setBounds(centerX, YD/4*3, playersButtonWidth, itemHeight);
		playerValue.setBounds(playersX, YD/4*3+20, valueButtonWidth, itemHeight);
		handTest.setBounds(playersX+20, YD/4*3+40, valueButtonWidth, itemHeight);
		//		dHandTest.setBounds(1,1,300,100);
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

	public void enableGame(boolean g)//some buttons need to be available at certain times, and not be available at other times
	{
		hit.setEnabled(g);
		stay.setEnabled(g);
	}

	private void updateValues()//updates the values displayed
	{
		playerValue.setText("Player's Worth: "+game.getPlayer().getWallet());
		dealerValue.setText("Dealer's Worth: "+game.getDealer().getWallet());
		pot.setText("Current pot value of "+potValue);
		betValue.setText("Bet: "+betAmount);
		handTest();
	}

	private void setCards()//sets the dealer and player's cards
	{
		game.getDealer().setHand(game.getDeck().dealCard());
		game.getDealer().setHand(game.getDeck().dealCard());
		game.getPlayer().setHand(game.getDeck().dealCard());
		game.getPlayer().setHand(game.getDeck().dealCard());
	}

	private void dealCards()//displayes the cards to the board
	{
		dealerCard[dealerIndex].setIcon(new ImageIcon(game.getDeck().findCardImg(game.getDealer().getHand(dealerIndex))));
		dealerIndex++;

		dealerCard[dealerIndex].setIcon(new ImageIcon(game.getDeck().findCardImg("Back")));
		dealerIndex++;

		playerCard[playerIndex].setIcon(new ImageIcon(game.getDeck().findCardImg(game.getPlayer().getHand(playerIndex))));
		playerIndex++;

		playerCard[playerIndex].setIcon(new ImageIcon(game.getDeck().findCardImg(game.getPlayer().getHand(playerIndex))));
		playerIndex++;
	}

	private void clearCards()//clears the current card images from the board
	{
		for(int i = 0; i<playerCard.length; i++)
		{
			playerCard[i].setIcon(new ImageIcon());
		}
		for(int i = 0; i<dealerCard.length; i++)
		{
			dealerCard[i].setIcon(new ImageIcon());
		}
	}

	private void updateDealerCards()//shows dealer's cards at the end of turn/game 
	{
		try
		{
			for(int i = 0; i < dealerCard.length; i ++)
			{
				dealerCard[i].setIcon(new ImageIcon(game.getDeck().findCardImg(game.getDealer().getHand(i))));
			}
		}
		catch(NullPointerException e)
		{
			System.out.print("*****Tried to display null (image)*****\n");
		}
	}

	private void newHand()//sets the board up for another round/new hand
	{
		turn = 0;
		clearCards();
		game.setDeck(new DeckOfCards());
		game.getDeck().shuffleDeck();
		playerIndex = 0;
		dealerIndex = 0;
		game.getPlayer().resetHand();
		game.getDealer().resetHand();
		enableGame(false);
		deal.setEnabled(true);
		betAmount = 0;
		potValue = 0;
		updateValues();
	}

	private void handTest()//shows the values of each players hands
	{
		handTotal = game.getPlayer().getHandTotal(game.getPlayer().getHand());
		handTest.setText("Hand Value: "+handTotal);
		//		dealerHT = game.getDealer().getHandTotal(game.getDealer().getHand());
		//		dHandTest.setText("Dealer Hand Value: "+dealerHT);
	}

	private void dealersTurn()//dealer's functionality, it is recursive
	{
		if(game.getDealer().getHandTotal(game.getDealer().getHand()) < softDealer || game.getDealer().isSoftSeventeen())
		{
			game.getDealer().setHand(game.getDeck().dealCard());
			dealerCard[dealerIndex].setIcon(new ImageIcon(game.getDeck().findCardImg("Back")));
			dealerIndex++;
			if(game.isBust(game.getDealer().getHandTotal(game.getDealer().getHand())))
			{
				JOptionPane.showMessageDialog(null, "Dealer Busted!");
				System.out.println("dealer busted");
				playerWin();
				enableGame(false);
				deal.setEnabled(false);
			}
			else
				dealersTurn();
		}
	}

	private void dealerWin()//when the dealer wins the hand
	{
		game.getDealer().addWallet(potValue);
		potValue = 0;
		updateValues();
	}

	private void playerWin()//when the player wins the hand
	{
		game.getPlayer().addWallet(potValue);
		potValue = 0;
		updateValues();
	}

	private void checkHand()//checks the dealer and player's hand
	{
		if(game.determinePush(game.getPlayer().getHandTotal(game.getPlayer().getHand()), game.getDealer().getHandTotal(game.getDealer().getHand())) && turn != 1)
		{
			JOptionPane.showMessageDialog(null, "You Tied!");
			game.getPlayer().addWallet(potValue/2);
			game.getDealer().addWallet(potValue/2);
			enableGame(false);
			deal.setEnabled(false);
		}
		else if(game.isBlackjack(game.getPlayer().getHandTotal(game.getPlayer().getHand())))
		{
			if(turn == 1)
			{
				JOptionPane.showMessageDialog(null, "You Blackjacked!");
				game.getPlayer().addWallet(bjwin+potValue);
				game.getDealer().setWallet(game.getDealer().getWallet() - bjwin);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "You Got 21!");
				playerWin();
			}
			enableGame(false);
			deal.setEnabled(false);
		}
		else if(game.isBlackjack(game.getDealer().getHandTotal(game.getDealer().getHand())))
		{
			if(turn == 1)
				JOptionPane.showMessageDialog(null, "Dealer Blackjacked!");
			else
				JOptionPane.showMessageDialog(null, "Dealer Got 21!");
			dealerWin();
			enableGame(false);
			deal.setEnabled(false);
		}
		else if(game.isBust(game.getPlayer().getHandTotal(game.getPlayer().getHand())))
		{
			JOptionPane.showMessageDialog(null, "You Busted!");
			dealerWin();
			enableGame(false);
			deal.setEnabled(false);
		}
	}

	public void handResult()//says if the player won or lost
	{
		if(game.determinePush(game.getPlayer().getHandTotal(game.getPlayer().getHand()), game.getDealer().getHandTotal(game.getDealer().getHand())))
		{
			JOptionPane.showMessageDialog(null, "Tie!");
			game.getPlayer().addWallet(potValue/2);
			game.getDealer().addWallet(potValue/2);
			potValue = 0;
		}
		if(game.isPlayerWinner(game.getPlayer().getHandTotal(game.getPlayer().getHand()), game.getDealer().getHandTotal(game.getDealer().getHand())))
		{
			JOptionPane.showMessageDialog(null, "You Win!");
			playerWin();
		}
		else if(game.isDealerWinner(game.getPlayer().getHandTotal(game.getPlayer().getHand()), game.getDealer().getHandTotal(game.getDealer().getHand())))
		{
			JOptionPane.showMessageDialog(null, "You Lost!");
			dealerWin();
		}
	}

	private class Listener implements MouseListener//where the magic happens
	{
		public void mouseClicked(MouseEvent e)
		{
			/*
			 * Increase bet button function
			 */
			if(e.getSource() == increaseBet)
			{
				if(game.getPlayer().getWallet() > betAmount)
					betAmount += 5;
				betValue.setText("Bet: "+betAmount);
			}


			/*
			 * Decrease bet button function
			 */
			else if(e.getSource() == decreaseBet && betAmount >=1)
			{
				if(game.getPlayer().getWallet() > 0)
					betAmount -= 5;
				betValue.setText("Bet: "+betAmount);
			}


			/*
			 * Bet button function
			 */
			else if(e.getSource() == bet && game.getPlayer().getWallet() >= betAmount && game.getDealer().getWallet() >= betAmount && deal.isEnabled())
			{
				if(betAmount > 0)
				{
					game.getPlayer().setWallet(game.getPlayer().getWallet() - betAmount);
					game.getDealer().setWallet(game.getDealer().getWallet() - betAmount);
				}
				potValue += betAmount*2;
				betAmount = 0;
				updateValues();
			}


			/*
			 * Deal button function
			 */
			else if(e.getSource() == deal && deal.isEnabled() && potValue != 0)
			{
				turn++;
				setCards();
				dealCards();
				updateValues();
				enableGame(true);
				deal.setEnabled(false);
				checkHand();
				updateValues();
			}


			/*
			 * Hit button function
			 */
			else if(e.getSource() == hit && hit.isEnabled() && playerIndex <= playerCard.length-1)
			{
				turn++;
				game.getPlayer().setHand(game.getDeck().dealCard());
				playerCard[playerIndex].setIcon(new ImageIcon(game.getDeck().findCardImg(game.getPlayer().getHand(playerIndex))));
				playerIndex++;
				checkHand();
				updateValues();
			}


			/*
			 * Stay button function
			 */
			else if(e.getSource() == stay && stay.isEnabled())
			{
				enableGame(false);
				deal.setEnabled(false);
				dealersTurn();
				updateValues();
				updateDealerCards();
				handResult();
			}


			/*
			 * New Hand button function
			 */
			else if (e.getSource() == newHand)
			{
				newHand();
			}


			/*
			 * New Game button function
			 */
			else if(e.getSource() == newgame)
			{
				game = new GamePlay();
				newHand();
			}
		}
		public void mouseEntered(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}
		public void mousePressed(MouseEvent e) {}
		public void mouseReleased(MouseEvent e) {}
	}
}
