public class InvestingAccount extends ListItem
{
    //Constants
    public static final double TRANSACTION_FEE = 9.99;
    
    //Fields
    private int accNumber;
    private double balance;
    private MyLinkedList ownedStocks;

    //Constructors
    public InvestingAccount(int accNumber, double startingBalance)
    {
	this.accNumber = accNumber;
	balance = startingBalance;
	ownedStocks = new MyLinkedList();
    }

    public InvestingAccount(int accNumber, double startingBalance, MyLinkedList stocks)
    {
	this.accNumber = accNumber;
	balance = startingBalance;
	ownedStocks = stocks;

	//Setting the account if it is not there
	Node n = stocks.getFirst();
	while(n != null)
	{
	    ((StockOrder)n.getData()).setAccount(this);
	    n = n.getNext();
	}
    }


    //
    //Accessors
    //
    public int getAccNumber()
    {
	return accNumber;
    }
	
    public double getBalance()
    {
	return balance;
    }

    public MyLinkedList getOwnedStocks()
    {
	return ownedStocks;
    }

    //required method
    public void print()
    {
	System.out.println("Account #" + accNumber + " has a balance of $" + String.format( "%.2f", balance) + " and owns these stocks:");
	ownedStocks.print();
    }

    public boolean isEqual(ListItem li)
    {
	InvestingAccount theOtherAcc = (InvestingAccount) li;

	if(accNumber == theOtherAcc.accNumber)
	    return true;

	return false;
    }

    

    //
    //Mutators:
    //
    public void buy(StockOrder so)
    {
	ownedStocks.add(new Node(so));
	balance -= so.getNb() * so.getPrice();
	balance -= TRANSACTION_FEE;
    }
    
    public void sell(StockOrder so)
    {
	ownedStocks.remove(so);
	balance += so.getNb() * so.getPrice();
	balance -= TRANSACTION_FEE;
    }
    
}
