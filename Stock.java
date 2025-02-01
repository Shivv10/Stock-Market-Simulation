public class Stock extends ListItem
{
    //Fields
    private String symbol;
    private double currentPrice;
    private MyOrderedList bidPrices;  //offers to buy
    private MyOrderedList askPrices;  //offers to sell

    //Constructor
    public Stock(String symbol)
    {
	this.symbol = symbol;
	this.currentPrice = 0;
	bidPrices = new MyOrderedList(false);
	askPrices = new MyOrderedList(true);
    }

    //Accessors
    public String getSymbol()
    {
	return symbol;
    }

    public double getCurrentPrice()
    {
	return currentPrice;
    }

    //required method
    public void print()
    {
	System.out.println("Stock: " + symbol + " has a current price of $" + currentPrice + ".");
	System.out.println("Here is the list of bids:");
	bidPrices.print();
	System.out.println("Here is the list of asks:");
	askPrices.print();
    }

    //required method
    public boolean isEqual(ListItem li)
    {
	Stock s = (Stock) li;

	if(s.symbol.equals(symbol))
	    return true;

	return false;
    }

    //
    //mutators
    //
    public void setCurrentPrice(double price)
    {
	currentPrice = price;
    }

    //adding offer to sell
    public boolean addAsk(StockOrder so)
    {
	//First checking if there is already an order from this account
	if(askPrices.contains(so))
	    return false;
	
	askPrices.add(new Node(so));
	return true;
    }

    //removing offer to sell
    public boolean removeAsk(StockOrder so)
    {
	//First checking if there is no order from this account
	if(!askPrices.contains(so))
	    return false;

	askPrices.remove(so);
	return true;
    }

    //adding offer to buy
    public boolean addBid(StockOrder so)
    {
	//First checking if there is already an order from this account
	if(bidPrices.contains(so))
	    return false;
	
	bidPrices.add(new Node(so));
	return true;
    }

    //removing offer to buy
    public boolean removeBid(StockOrder so)
    {
	//First checking if there is no order from this account
	if(!bidPrices.contains(so))
	    return false;

	bidPrices.remove(so);
	return true;
    }


    //To check if we need to process the transaction, if last order added was a bid, lastWasBid will be true (used to determine which price will be used)
    public boolean processTransaction(boolean lastWasBid)
    {
	if(bidPrices.isEmpty() || askPrices.isEmpty())
	    return false;
	
	StockOrder bestBid = (StockOrder) bidPrices.getFirst().getData();
	StockOrder bestAsk = (StockOrder) askPrices.getFirst().getData();

	if(bestBid.getPrice() >= bestAsk.getPrice()) //we can do the transaction
	{
	    //Updating the prices
	    if(lastWasBid)
		bestBid.setPrice(bestAsk.getPrice());
	    else
		bestAsk.setPrice(bestBid.getPrice());

	    bestBid.getStock().setCurrentPrice(bestBid.getPrice()); //updating the stock price

	    //Processing transaction
	    bestAsk.getAccount().sell(bestAsk);
	    removeAsk(bestAsk);
	    
	    bestBid.getAccount().buy(bestBid);
	    removeBid(bestBid);
	    
	    return true;
	}

	return false;
    }
}
