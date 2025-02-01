abstract class OrderedItem extends ListItem
{
    public abstract boolean isBefore(OrderedItem oi, boolean increasingOrder);
}
