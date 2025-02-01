public class StockMarket
{
    //Fields
    private MyLinkedList accountsList;
    private MyLinkedList stocksList;

    //Constructor
    public StockMarket()
    {
	accountsList = new MyLinkedList();
	stocksList = new MyLinkedList();	
    }


    //
    //Accessors
    //

    //Search for a particular stock in stocksList; return null if it's not there
    public Stock getStock(String symbol)
    {
	Node current = stocksList.getFirst();
	while(current != null)
	{
	    Stock s = (Stock)current.getData();
	    if(s.getSymbol().equals(symbol))
		return s;
	    current = current.getNext();
	}
	return null;
    }

    //Search for a particular account in accountsList; return null if it's not there
    public InvestingAccount getAccount(int nb)
    {
	Node current = accountsList.getFirst();
	while(current != null)
	{
	    InvestingAccount ia = (InvestingAccount)current.getData();
	    if(ia.getAccNumber() == nb)
		return ia;
	    current = current.getNext();
	}
	return null;
    }
    
    
    //
    //Requested operations
    //

    //To add a new InvestingAccount
    public void addInvestAcc(int accNb, double balance, MyLinkedList stocks)  //stocks can be an empty list --> it has to be a valid list (stocks must exist), but the account will not be set
    {
	InvestingAccount ia = new InvestingAccount(accNb, balance, stocks);

	if(accountsList.contains(ia))
	    System.out.println("DUPLICATE");
	else
	{
	    accountsList.add(new Node(ia));
	    System.out.println("CONFIRMED");
	}
    }

    //To add a new stock to the system
    public void addStock(String symbol)
    {
	Stock s = new Stock(symbol);
	if(stocksList.contains(s))
	    System.out.println("DUPLICATE");
	else
	{
	    stocksList.add(new Node(s));
	    System.out.println("CONFIRMED");
	}
    }

    //To add a bid or an ask to the system
    public void addBidOrAsk(boolean isBid, int accNb, String symbol, int nbStocks, double price)
    {
	//First check if account exists
	InvestingAccount ia = getAccount(accNb);
	if(ia == null)
	{
	    System.out.println("ACCOUNT NOT FOUND");
	    return;
	}

	//Then check if stock exists
	Stock s = getStock(symbol);
	if(s == null)
	{
	    System.out.println("STOCK NOT FOUND");
	}

	StockOrder so = new StockOrder(ia, nbStocks, price, s);

	boolean addSuccess;
	
	if(isBid)
	{
	    //Checking if the balance is high enough (necessary in order to buy)
	    if(ia.getBalance() < nbStocks * price + InvestingAccount.TRANSACTION_FEE)
	    {
		System.out.println("INVALID ORDER");
		return;
	    }
	    addSuccess = s.addBid(so);
	}
	else
	{
	    //Checking if the stock is owned (necessary in order to sell)
	    if(!ia.getOwnedStocks().contains(so))
	    {
		System.out.println("INVALID ORDER");
		return;
	    }
	    addSuccess = s.addAsk(so);
	}

	if(!addSuccess)
	    System.out.println("DUPLICATE");
	else
	{
	    System.out.println("CONFIRMED");
	    boolean processed = s.processTransaction(isBid);
	    if(processed)
		System.out.println("TRANSACTION COMPLETED");
	}
    }


    //To remove a bid or an ask from the system
    public void removeBidOrAsk(boolean isBid, int accNb, String symbol)
    {
	//First check if account exists
	InvestingAccount ia = getAccount(accNb);
	if(ia == null)
	{
	    System.out.println("ACCOUNT NOT FOUND");
	    return;
	}

	//Then check if stock exists
	Stock s = getStock(symbol);
	if(s == null)
	{
	    System.out.println("STOCK NOT FOUND");
	}

	StockOrder so = new StockOrder(ia, s);

	boolean removeSuccess;

	if(isBid)
	    removeSuccess = s.removeBid(so);
	else
	    removeSuccess = s.removeAsk(so);

	if(!removeSuccess)
	    System.out.println("ORDER NOT FOUND");
	else
	    System.out.println("CONFIRMED");
    }

    
    //To print the status of an InvestorAccount
    public void printInvestAccInfo(int accNb)
    {
	InvestingAccount ia = getAccount(accNb);

	if(ia == null)
	    System.out.println("NOT FOUND");

	else
	    ia.print();
    }
    

    //To print the status of a stock
    public void printStockInfo(String symbol)
    {
	Stock s = getStock(symbol);

	if(s == null)
	    System.out.println("NOT FOUND");

	else
	    s.print();
    }
}
