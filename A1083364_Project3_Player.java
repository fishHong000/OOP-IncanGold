import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.ArrayList;

public class A1083364_Project3_Player extends A1083364_Project3_Agent
{
    public A1083364_Project3_Player(int number)
    {
        super(number);
    }

    @Override
    public boolean decision(String fileName) throws IOException
    {
        /********************************* TODO (Checkpoint 3) *********************************
         * TODO 7-1: Here goes your algorithm for the gameplay. In order to get more treasure and reduce the risk,
         *            you have to make your own decision based on the current game state.
         * Hint 1: The parameter $fileName indicates the file path that you need to read.
         *         The file contains information about the current path, removed hazards, count of explorers who stay, etc.
         * Hint 2: This method requires you to return a boolean value. If the return value is true,
         *         it means that you'd like to keep your exploration and stay in the tomb, otherwise, you chose to leave.
         * Hint 3: To recognize the format of the file content, you can just temporarily print it out or see what the document shows.
         *         The file itself will not be preserved after this method finishes.
         * Notice 1: Your explorer's data such as collected gems can be accessed from the parent class,
         *           but due to the orientation of this checkpoint (file I/O), all information must be read from the given file.
         *           That is, do not call any method or variable from the parent class.

         ************************************* End of TODO *************************************/

        /************ START OF YOUR CODE ************/

        ArrayList<String> Info = new ArrayList<>();
        try (Scanner input = new Scanner(Paths.get(fileName))){
            while (input.hasNext()){
                Info.add(input.nextLine());
            }
        }
        
        int[] appearHazards = new int[5];
        int[] occurredHazards = new int[5];
        for (int i = 0; i < 5; i++){
            appearHazards[i] = 0;
            occurredHazards[i] = 3;
        }
        String[] hazzardsType = {"Spikes", "Spiders", "Mummy", "Curse", "Collapse"};
        String[] checkOccurredHazards = Info.get(3).split("Occurred hazards: |\\[|<|H: |>|, |\\]");
        
        ArrayList<String> haveOccurredHazards = new ArrayList<>();
        for (int i = 0; i < checkOccurredHazards.length; i++){
            if (!(checkOccurredHazards[i].equals("") || checkOccurredHazards.equals(" "))){
                haveOccurredHazards.add(checkOccurredHazards[i]);
            }
        }
        
        String[] path = Info.get(2).split("Path: |\\[|\\]|<|>|, |\\: |/");
        ArrayList<String> pathCard = new ArrayList<>();
        for (int i = 0; i < path.length; i++){
            if (!(path[i].equals("") || path[i].equals(" "))){
                pathCard.add(path[i]);
            }
        }
        
        ArrayList<String> pathHazards = new ArrayList<>();
        for (int i = 0; i < pathCard.size(); i++){
            if (pathCard.get(i).contains("H")){
                pathHazards.add(pathCard.get(i+1));
            }
        }
        
        for (int i = 0; i < pathCard.size(); i++){
            for (int j = 0; j < hazzardsType.length; j++){
                if (pathCard.get(i).equals(hazzardsType[j])){
                    occurredHazards[j] -= 1;
                    appearHazards[j] += 1;
                }
            }
        }
        
        for (int i = 0; i < haveOccurredHazards.size(); i++){
            for (int j = 0; j < hazzardsType.length; j++){
                if (haveOccurredHazards.get(i).equals(hazzardsType[j])){
                    occurredHazards[j] -= 1;
                }
            }
        }
        
        int dangerous = 0;
        for (int i = 0; i < appearHazards.length; i++){
            if (appearHazards[i] != 0){
                dangerous += occurredHazards[i];
            }
        }
        
        int appearGemstone = 0;
        int remainGemstone = 0;
        for (int i = 0; i < pathCard.size(); i++){
            if (pathCard.get(i).contains("G")){
                appearGemstone += Integer.parseInt(pathCard.get(i+2));
                remainGemstone += Integer.parseInt(pathCard.get(i+1));
            }
        }
        
        ArrayList<String> collectedGem = new ArrayList<>();
        String[] takeCollectedGem = Info.get(9).split("Collected gems: ");
        for (int i = 0; i < takeCollectedGem.length; i++){
            if (!(takeCollectedGem[i].equals("") || takeCollectedGem[i].equals(" "))){
                collectedGem.add(takeCollectedGem[i]);
            }
        }
        int collectedGemNumber = Integer.parseInt(collectedGem.get(0));
        
        int expectedValue = (124 - appearGemstone) - (dangerous * (remainGemstone + collectedGemNumber));
        
        if (dangerous == 0){
            return true;
        }
        else if (dangerous <= 2 || remainGemstone >= 5){
            return (Math.random() < 0.9);
        }
        else if (dangerous >= 5){
            return false;
        }
        else if (expectedValue >= 0){
            return (Math.random() < 0.8);
        }
        else {
            return (Math.random() < 0.4);
        }

        /************* END OF YOUR CODE *************/
    }
}
