import java.io.*;

public class Main
{
    public static void processCommand(String line, StockMarket sm)
    {
	String[] splitted = line.split(" ");

	//Creating a new stock
	if(splitted[0].equals("STOCK"))
	{
	    sm.addStock(splitted[1]);
	}

	//Creating a new account
	else if(splitted[0].equals("NEW"))
	{
	    int accNum = Integer.parseInt(splitted[1]);
	    double balance = Double.parseDouble(splitted[2]);
	    MyLinkedList stocks = new MyLinkedList();

	    for(int i = 3; i < splitted.length; i++)
	    {
		String[] dashSplit = splitted[i].split("-");
		String symb = dashSplit[0];
		int nb = Integer.parseInt(dashSplit[1]);
		double price = Double.parseDouble(dashSplit[2]);

		Stock theStock = sm.getStock(symb); //could be null
		if(theStock == null)
		{
		    System.out.println(symb + "STOCK NOT FOUND");
		    return;
		}

		stocks.add(new Node(new StockOrder(nb, price, theStock)));
	    }

	    sm.addInvestAcc(accNum, balance, stocks);
	}

	//Adding an ask or a bid
	else if(splitted[0].equals("ADD"))
	{
	    int accNum = Integer.parseInt(splitted[2]);
	    String symb = splitted[3];
	    int nbStocks = Integer.parseInt(splitted[4]);
	    double price = Double.parseDouble(splitted[5]);

	    if(splitted[1].equals("ASK"))
		sm.addBidOrAsk(false, accNum, symb, nbStocks, price);
	    else if(splitted[1].equals("BID"))
		sm.addBidOrAsk(true, accNum, symb, nbStocks, price);
	    else
		System.out.println("---> UNRECOGNIZED COMMAND");
	}

	//Removing an ask or a bid
	else if(splitted[0].equals("REMOVE"))
	{
	    int accNum = Integer.parseInt(splitted[2]);
	    String symb = splitted[3];

	    if(splitted[1].equals("ASK"))
		sm.removeBidOrAsk(false, accNum, symb);
	    else if(splitted[1].equals("BID"))
		sm.removeBidOrAsk(true, accNum, symb);
	}

	//Balance
	else if(splitted[0].equals("BALANCE"))
	{
	    sm.printInvestAccInfo(Integer.parseInt(splitted[1]));
	}

	//Status of a stock
	else if(splitted[0].equals("STATUS"))
	{
	    //System.out.println("Status of: " + splitted[1]);
	    sm.printStockInfo(splitted[1]);
	}

	//QUIT
	else if(splitted[0].equals("QUIT"))
	{
	    System.out.println("DONE");
	    System.exit(0);
	}

	else
	    System.out.println("---> UNRECOGNIZED COMMAND");
    }


    public static void main(String[] args)
    {
	StockMarket sm = new StockMarket();

	//String filename = args[0];
//	String filename = "a1_data.txt";
	String filename = "a1_data-v2.txt";
	try
	{
	    BufferedReader in = new BufferedReader(new FileReader(new File(filename)));

            for (String line = in.readLine(); line != null; line = in.readLine())
	    {
		if(line.charAt(0) == '#')
		    System.out.println(line);

		else
		    processCommand(line, sm);
	    }
	}
	catch (IOException e)
        {
            System.out.println("File I/O error!");
        }
    }
}
