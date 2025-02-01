public class MyOrderedList extends MyLinkedList
{
    //fields
    private boolean increasingOrder;
    
    //Constructor
    public MyOrderedList(boolean increasingOrder)
    {
	super();
	this.increasingOrder = increasingOrder;
    }

    //
    //Mutators
    //

    //The only thing we change is the add method, now it needs to keep an order (increasing or decreasing).
    public void add(Node n)
    {
	OrderedItem theItem = (OrderedItem) n.getData();
	
	if(first == null)
	    super.add(n);  //adding at the beginning

	else if(theItem.isBefore((OrderedItem)first.getData(), increasingOrder))
	    super.add(n);  //also adding at the beginning

	else
	{
	    Node previous = first;
	    Node current = first.getNext();

	    while(current != null)
	    {
		if(theItem.isBefore((OrderedItem)current.getData(), increasingOrder))
		{
		    previous.setNext(n);
		    n.setNext(current);
		    return;
		}

		previous = current;
		current = current.getNext();
	    }

	    //if we get here, it means that current == null and the node to add must be added at the end of the list
	    previous.setNext(n);
	}
    }
}
