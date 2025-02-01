public class Node
{
    //Fields
    private ListItem data;
    private Node next;

    //Constructors
    public Node(ListItem data)
    {
	this.data = data;
	next = null;
    }

    public Node(ListItem data, Node next)
    {
	this.data = data;
	this.next = next;
    }

    //
    //Accessors
    //
    public Node getNext()
    {
	return next;
    }

    public ListItem getData()
    {
	return data;
    }

    public void print()
    {
	data.print();
	if(next != null)
	    next.print();
    }

    //
    //Mutators
    //
    public void setNext(Node next)
    {
	this.next = next;
    }

    public void setData(ListItem li)
    {
	data = li;
    }
}
