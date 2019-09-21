import java.util.Scanner;

public class Main {

    public static String getInput(){
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }

    public static int numOfQuestions(){
        System.out.println("How many questions are being graded?");
        String numQuesString = getInput();
        int numQues;

        try{
            numQues = Integer.parseInt(numQuesString);
        }catch (Exception e){
            System.out.println("Invalid input!");
            return numOfQuestions();
        }

        boolean flag = false;
        String ans;

        while(!flag){
            System.out.printf("Is this number of questions correct: %d (yes/no)?\n", numQues);
            ans = getInput();
            if(ans.toLowerCase().contains("y") || ans.contains("1"))
                flag = true;
            else
                return numOfQuestions();
        }

        return numQues;
    }

    public static double fractionToDouble(String fraction){
        if (fraction.contains("/")) {
            String[] rat = fraction.split("/");
            return Double.parseDouble(rat[0]) / Double.parseDouble(rat[1]);
        } else
            return Double.parseDouble(fraction);
    }

    public static void displayGrade(double[] pointsPerQues, int extraCredit){
        double total = 0.0;

        for(int i = 0; i < pointsPerQues.length; i++){
            System.out.printf("For question %d: %.2f \n", i+1, pointsPerQues[i]);
            total += pointsPerQues[i];
        }

        System.out.println("Extra credit: " + extraCredit);
        System.out.printf("Final grade: %.2f; Final grade w/ ec: %.2f\n", total, total+extraCredit);
    }

    public static void revisions(double[] pointsPerQues, double percPerQues){
        String revisedQuesString;
        int revisedQues;
        String newGradeString;
        Double newGrade;

        System.out.println("Make revisions (Yes/No)?");
        String ans = getInput();

        while(ans.toLowerCase().contains("y") || ans.contains("1")){
            System.out.println("Which question needs revision?");
            revisedQuesString = getInput();
            try{
                revisedQues = Integer.parseInt(revisedQuesString);
                System.out.printf("Enter new grade for question %d: \n", revisedQues);
                newGradeString = getInput();
                newGrade = fractionToDouble(newGradeString) * percPerQues;
                pointsPerQues[revisedQues-1] = newGrade;
            }catch (Exception e){
                System.out.println("Invalid Input!");
            }

            System.out.println("Continue revision (Yes/No)?");
            ans = getInput();
        }

    }

    public static int extraCredit(){
        String extraCredit;

        System.out.println("How many points are being added?");
        extraCredit = getInput();

        try{
            return Integer.parseInt(extraCredit);
        }catch (Exception e){
            System.out.println("Invalid input!");
            return extraCredit();
        }
    }

    public static void grading(int numQues){
        if(numQues == 0)
            numQues = numOfQuestions();

        double percPerQues = 100/ (double)numQues;
        double[] pointsPerQues = new double[numQues];

        System.out.printf("Number of questions: %d, percent per question: %.2f\n", numQues, percPerQues);
        String inputFraction;
        double fraction;
        double decRep;

        for(int i = 0; i < numQues; i++){
            try {
                System.out.printf("Enter fraction correct for question %d: \n", i + 1);
                inputFraction = getInput();
                fraction = fractionToDouble(inputFraction);
                while(fraction > 1){
                    System.out.println("Invalid Input!");
                    System.out.printf("Enter fraction correct for question %d: \n", i + 1);
                    inputFraction = getInput();
                    fraction = fractionToDouble(inputFraction);
                }
                decRep = fraction * percPerQues;
                pointsPerQues[i] = decRep;
            }catch (Exception e){
                System.out.println("Invalid Input!");
                i--;
            }
        }

        revisions(pointsPerQues, percPerQues);

        int extraCredit = 0;
        System.out.println("Any extra credit to add (Yes/No)?");
        String ans = getInput();
        if(ans.toLowerCase().contains("y") || ans.contains("1"))
            extraCredit = extraCredit();

        displayGrade(pointsPerQues, extraCredit);

        System.out.println("Continue grading (y/n)?");
        String cont = getInput();
        if(cont.toLowerCase().contains("y") || cont.contains("1"))
            grading(numQues);
    }

    public static void main(String[] args){
        grading(0);
    }
}
