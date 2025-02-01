public class StockOrder extends OrderedItem
{
    //Fields
    private InvestingAccount account;  //owner, or who asked or made a bid
    private int nb;
    private double price;
    private Stock theStock;

    //Constructors
    public StockOrder(InvestingAccount account, int nb, double price, Stock theStock)
    {
	this.account = account;
	this.nb = nb;
	this.price = price;
	this.theStock = theStock;
    }

    public StockOrder(InvestingAccount account, int nb, Stock theStock)
    {
	this.account = account;
	this.nb = nb;
	this.price = 0;
	this.theStock = theStock;
    }

    public StockOrder(int nb, double price, Stock theStock)
    {
	this.nb = nb;
	this.price = price;
	this.theStock = theStock;
    }

    public StockOrder(InvestingAccount account, Stock theStock)
    {
	this.account = account;
	this.theStock = theStock;
    }

    //Accessors
    
    //required method
    public void print()
    {
	if(account != null)
	    System.out.println("-Account #" + account.getAccNumber() + ": " + nb + " of " + theStock.getSymbol() + " at $" + price + ".");
	else
	    System.out.println("-" + nb + " of " + theStock.getSymbol() + " at $" + price + ".");
    }

    //required method
    public boolean isBefore(OrderedItem oi, boolean increasingOrder)
    {	
	StockOrder theOther = (StockOrder) oi; 

	if(increasingOrder)
	{
	    if(this.price < theOther.price) //needs strictly smaller because if equal, we give priority to the first one that arrived
		return true;
	    else
		return false;
	}
	else  //decreasing order
	{
	    if(this.price > theOther.price) //needs strictly larger because if equal, we give priority to the first one that arrived
		return true;
	    else
		return false;
	}
    }

    //required method
    public boolean isEqual(ListItem li)
    {
	StockOrder theOther = (StockOrder) li;
	//We're going to check just the account, nb and theStock here, since the price can change
	//if(account == theOther.account && nb == theOther.nb && theStock == theOther.theStock)
	//Actually, we're only going to check the account and the stock, since only one order can be in the system for each stock.
	if(account == theOther.account && theStock == theOther.theStock)
	    return true;
	else
	    return false;
    }

    public InvestingAccount getAccount()
    {
	return account;
    }

    public int getNb()
    {
	return nb;
    }

    public double getPrice()
    {
	return price;
    }

    public Stock getStock()
    {
	return theStock;
    }

    //
    //Mutators
    //
    public void setPrice(double price)
    {
	this.price = price;
    }

    public void setAccount(InvestingAccount ia)
    {
	if(account == null)
	    this.account = ia;
    }
}
