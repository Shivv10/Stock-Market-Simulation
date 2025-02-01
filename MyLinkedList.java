public class MyLinkedList
{
    //Fields
    protected Node first;

    //Constructor
    public MyLinkedList()
    {
	first = null;
    }

    //
    //Accessors
    //
    public void print()
    {
	if(first != null)
	    first.print();
    }

    public Node getFirst()
    {
	return first;
    }

    public boolean isEmpty()
    {
	return first == null;
    }

    //
    //Mutators
    //

    //To add a node at the beginning
    public void add(Node node)
    {
	if(first == null)
	    first = node;

	else
	{
	    node.setNext(first);
	    first = node;
	}
    }


    //To remove the first node
    public void removeFirst()
    {
	if(first != null)
	    first = first.getNext();
    }


    //To remove a Node containing a specific ListItem
    public void remove(ListItem li)
    {
	if(first != null)
	{
	    if(first.getData().isEqual(li))
		this.removeFirst();

	    else
	    {
		Node previous = first;
		Node current = first.getNext();

		while(current != null)
		{
		    if(current.getData().isEqual(li))
		    {
			previous.setNext(current.getNext());
			return;
		    }

		    previous = current;
		    current = current.getNext();
		}
	    }
	}
    }

    //To check if the list contains a specific ListItem
    public boolean contains(ListItem li)
    {
	Node current = first;

	while(current != null)
	{
	    if(current.getData().isEqual(li))
		return true;

	    current = current.getNext();
	}

	return false;
    }
    
}
    
