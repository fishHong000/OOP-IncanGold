public class A1083364_Project3_Hazard extends A1083364_Project3_Card
{
    public A1083364_Project3_Hazard(int type)
    {
        super(type);
    }

    @Override
    public String name()
    {
        /********************************* TODO (Checkpoint 3) *********************************
         * TODO 4-1 (Past): The name of this hazard card depends on its type. There are 5 kinds of hazards in total.
         * Hint 1: From type 0 to 4, the names are "Spikes", "Spiders", "Mummy", "Curse" and "Collapse" in order.
         * Hint 2: The name will be "Unknown" when the type is unexpectedly not between 0 and 4.
         ************************************* End of TODO *************************************/

        /************ START OF YOUR CODE ************/

        String typeName = "";
        switch (this.getType()){
            case 0:
                typeName = "Spikes";
                break;
            case 1:
                typeName = "Spiders";
                break;
            case 2:
                typeName = "Mummy";
                break;
            case 3:
                typeName = "Curse";
                break;
            case 4:
                typeName = "Collapse";
                break;
            default:
                typeName = "Unknown";
        }
        return typeName;

        /************* END OF YOUR CODE *************/
    }
    
    @Override
    public String toString()
    {
        return String.format("<H: %s>", this.name());
    }
}
