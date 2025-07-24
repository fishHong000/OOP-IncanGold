import java.io.IOException;

public class A1083364_Project3_Computer extends A1083364_Project3_Agent
{
    public A1083364_Project3_Computer(int number)
    {
        super(number);
    }

    @Override
    public boolean decision(String fileName) throws IOException
    {
        return (Math.random() < 0.65);
    }
}
