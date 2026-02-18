package ub.cse.algo;

import java.util.ArrayList;
import java.util.HashMap;


public class Solution {

    private int numberOfMenAndWomen;


    // The following represent the preference list for the men and women.
    // The KEY represents the integer representation of a given man or woman.
    // The VALUE is a list, from most preferred to least, of the member of the opposite gender.
    private HashMap<Integer, ArrayList<Integer>> men;
    private HashMap<Integer, ArrayList<Integer>> women;
    private ArrayList<Matching> stableMatchings = new ArrayList<>();

    /**
     * The constructor simply sets up the necessary data structures.
     * The grader for the homework will first call this class and pass the necessary variables.
     * There is no need to edit this constructor.
     *
     * @param n The number of men/women.
     * @param men A map linking each man (integer value) with their preference list.
     * @param women A map linking each woman (integer value) with their preference list.
     */
    public Solution(int n, HashMap<Integer, ArrayList<Integer>> men, HashMap<Integer, ArrayList<Integer>> women){
        this.numberOfMenAndWomen = n;
        this.men = men;
        this.women = women;
    }

    
    public ArrayList<Matching> outputStableMatchings() {

        /* The code below just calls the allPermutations function, and then just prints all permutations*/
        /* To compare your code's output with the sample outpout you need to comment out the part about printing the permutations*/

        ArrayList<ArrayList<Integer>> listOfAllPermutations = new ArrayList<>();
        listOfAllPermutations = allPermutations(numberOfMenAndWomen);
        System.out.println("----------------------------");
        System.out.println("Printing all possible permutations of [1,2,...n] for n ="+numberOfMenAndWomen);
        System.out.println("Total number of permutation generated ="+listOfAllPermutations.size());
        System.out.println("----------------------------");
        for(ArrayList<Integer> set : listOfAllPermutations){
            ArrayList<Marriage> resulter=new ArrayList<>();
            boolean hecker=false;
            for(int i=1;i<=numberOfMenAndWomen;i++){
                boolean outer=false;
                int curr_woman_number=set.get(i-1);
                ArrayList<Integer> men_preferences=men.get(i);
                for(int man_sided_preference:men_preferences){
                    if(man_sided_preference==curr_woman_number){
                        break;
                    }
                    ArrayList<Integer> woman_preferences=women.get(man_sided_preference);
                    int curr_man_number= set.indexOf(man_sided_preference)+1;
                    for(int man:woman_preferences){
                        if(curr_man_number==man){
                            break;
                        }
                        if(man==i){
                            hecker=true;
                            outer=true;
                            break;
                        }
                    }
                    if(outer){
                        break;
                    }
                }
                if(outer){
                    break;
                }
            }
            if(!hecker){
                int i=1;
                for(int w:set) {
                    Marriage m = new Marriage(i, w);
                    resulter.add(m);
                    i++;
                }
                stableMatchings.add(new Matching(resulter));
            }
            /*System.out.println(set);*/
        }
        System.out.println("----------------------------");
        /*allPermutations call done*/

        return stableMatchings;
    }

    /**
     * Generates all permutations.
     * Just a wrapper function to call permutate
     */
     private ArrayList<ArrayList<Integer>> allPermutations(int n){
            ArrayList<Integer> start = new ArrayList<Integer>();
            for(int k = 1; k<=numberOfMenAndWomen; ++k) {
                start.add(k);
            }
            ArrayList<ArrayList<Integer>> allPermuts= new ArrayList<>();
            permutate(start,allPermuts,n); // Once this call returns the list of all permutations will be in "allPermuts"

            return allPermuts;
     }

    /**
     * This method generates all of the permutations of the input for you.
     * Implements Jeap's algorithm.
     * @param set A complete matching set, not necesarrily stable
     # * @param listOfpermut Current of of all opermutations that have been generated so far. This would be updated by ref
     * @param length length of the set
     */
    private void permutate(ArrayList<Integer> set, ArrayList<ArrayList<Integer>> listOfPermut, int length){
        if(length == 1){
            //System.out.println(set);
            //Have to deep copy the current matching so that next call of Heap's does not over-write the current matching
            ArrayList<Integer> cloneSet = new ArrayList<>();
            for(int i = 0; i < set.size(); i++){
                cloneSet.add(set.get(i));
            }
            listOfPermut.add(cloneSet); 
        }
        else{
            for(int i = 0; i < length; i++){
                permutate(set, listOfPermut, length - 1);
                int j = (length % 2 == 0 ) ? i : 0;
                Integer t = set.get(length-1);
                set.set(length-1, set.get(j));
                set.set(j, t);
            }
        }
    }

}
