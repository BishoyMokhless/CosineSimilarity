import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.lang.Math;

public class CosineSimilarity {
    public static String readFile (String path) throws FileNotFoundException {
        File file = new File(path);
        Scanner fileReader = new Scanner(file);
        String fileData = "";
        while (fileReader.hasNextLine())
        {
            fileData += fileReader.nextLine();
        }
        return fileData.toLowerCase();
    }
    public static String removeDuplicates(String line1 , String line2)
    {
        String answer=line1;
        String[] docWords = line2.split("\\s+");
        for (int i=0;i<docWords.length;i++)
        {
            if (answer.contains(docWords[i])){
                i++;
            }
            else {
                answer+=" ";
                answer+=docWords[i];

            }

        }
        return answer;
    }
    public static List<Integer> checkSimilarity(String doc1 ,String allWords)
    {
        List<Integer> answer = new ArrayList<>();
        String[] docWords = allWords.split("\\s+");

        for (int i=0; i<docWords.length;i++){
            if (doc1.contains(docWords[i])){
                answer.add(1);
            }
            else {
                answer.add(0);
            }
        }
        return answer;
    }

    public static double calculateSimilarity(List<Integer>  doc1 ,List<Integer>  doc2)
    {
        double answer = 0.0;
        double temp =0.0;
        double sum =0.0;
        double power1 =0.0;
        double sqrt1 =0.0;
        double power2 =0.0;
        double sqrt2 =0.0;

        for (int i=0; i<doc1.size();i++){
            temp = doc1.get(i) * doc2.get(i);
            sum += temp;
        }
        for (int i=0; i<doc1.size();i++){
            power1 += Math.pow(doc1.get(i),2);
        }
        sqrt1 = Math.sqrt(power1);
        for (int i=0; i<doc2.size();i++){
            power2 += Math.pow(doc2.get(i),2);
        }
        sqrt2 = Math.sqrt(power2);

        answer = sum / (sqrt1*sqrt2);
        return answer;

    }
    //0 1 // 1 2 //2 3
    //
    public static List<Results> calculateAllSimilarity(List<String> files){
        List<Results> allResults = new ArrayList<>();
        for (int i=1;i<files.size();i++){
            String allWords = removeDuplicates(files.get(0),files.get(i));
            List<Integer> answer1 = checkSimilarity(files.get(0),allWords);
            //System.out.println(answer1.toString());
            List<Integer> answer2 = checkSimilarity(files.get(i),allWords);
            //System.out.println(answer2.toString());
            double test= calculateSimilarity(answer1,answer2);

            Results results = new Results("D1","D"+(i+1),test);
            allResults.add(results);
        }
        for (int i=2;i<files.size();i++){
            String allWords = removeDuplicates(files.get(1),files.get(i));
            List<Integer> answer1 = checkSimilarity(files.get(1),allWords);
            //System.out.println(answer1.toString());
            List<Integer> answer2 = checkSimilarity(files.get(i),allWords);
            //System.out.println(answer2.toString());
            double test= calculateSimilarity(answer1,answer2);

            Results results = new Results("D2","D"+(i+1),test);
            allResults.add(results);
        }
        String allWords = removeDuplicates(files.get(2),files.get(3));
        List<Integer> answer1 = checkSimilarity(files.get(2),allWords);
        //System.out.println(answer1.toString());
        List<Integer> answer2 = checkSimilarity(files.get(3),allWords);
        //System.out.println(answer2.toString());
        double test= calculateSimilarity(answer1,answer2);

        Results results = new Results("D3","D4",test);
        allResults.add(results);

        return allResults;
    }


    public static void main(String[] args) throws FileNotFoundException {
        List<String> files2 = new ArrayList<String>();
        files2.add(readFile("src/1.txt"));
        files2.add(readFile("src/2.txt"));
        String allWords = removeDuplicates(files2.get(0),files2.get(1));
        List<Integer> answer1 = checkSimilarity(files2.get(0),allWords);
        System.out.println(answer1.toString());
        List<Integer> answer2 = checkSimilarity(files2.get(1),allWords);
        System.out.println(answer2.toString());
        double test= calculateSimilarity(answer1,answer2);

        Results results = new Results("D1","D2",test);
        System.out.println("Assignment Test Case: ");
        System.out.println(results);

        List<String> files = new ArrayList<String>();
        files.add(readFile("src/524.txt"));
        files.add(readFile("src/525.txt"));
        files.add(readFile("src/526.txt"));
        files.add(readFile("src/527.txt"));



        List<Results> allDocs = calculateAllSimilarity(files);
        allDocs.sort(Comparator.comparing(Results::getDocsSimilarity).reversed());
        System.out.println("___________________________________________________________________________________");
        System.out.println("The 4 documents Test case:");
        for (int i=0; i<allDocs.size();i++){
            System.out.println(allDocs.get(i));
        }


    }


}
