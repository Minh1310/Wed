package bo;

import java.util.ArrayList;
import java.util.List;

import entity.Candidate;
import entity.Experience;
import entity.Fresher;
import entity.Intern;

public class CandidateBO {

    private List<Candidate> list;

     public CandidateBO() {
        list = new ArrayList<>();
    }
    public CandidateBO(List<Candidate> list) {
        this.list = list;
    }

    public List<Candidate> getList() {
        return list;
    }

    public void setList(List<Candidate> list) {
        this.list = list;
    }

    /**
     *
     * Use to add information
     * @return 
     */
     public boolean add(int type){
        switch (type) {
            case 0:
                Candidate experience = new Experience();
                experience.input(list,type);
                return list.add(experience);              
            case 1:
                Candidate fresher = new Fresher();
                fresher.input(list, type);
                return list.add(fresher);
            case 2:
                Candidate intern = new Intern();
                intern.input(list, type);
                return list.add(intern);
        }
        return true;
     }

    /**
     * Use to search information you want
     * @param text
     * @return 
     */
    public List<Candidate> search(String text,int type){
        List<Candidate> listFind = new ArrayList<>();
        for(Candidate candidate : list){
            if(
                candidate.getCandidateType() == type&&(
                candidate.getFirstName().toLowerCase().contains(text)||
                candidate.getLastName().toLowerCase().contains(text))
            ){
                listFind.add(candidate);
            }
        }
        return listFind;
    }

    public void printListNameCandidate() {
        int countExperience = 0;
        int countFresher = 0;
        int countIntern = 0;
        for (Candidate candidate : list) {
            if (candidate instanceof Experience) {
                countExperience++;
                if (countExperience == 1) {
                    System.out.println("=====Experience Candidate=====");
                }
                System.out.printf("%5s|%5s",
                    candidate.getFirstName() , candidate.getLastName()
                    );
            }
            if (candidate instanceof Fresher) {
                countFresher++;
                if (countFresher == 1) {
                    System.out.println("=====Fresher Candidate=====");
                }
                System.out.printf("%5s|%5s",
                    candidate.getFirstName() , candidate.getLastName()
                    );
            }
            if (candidate instanceof Intern) {
                countIntern++;
                if (countIntern == 1) {
                    System.out.println("=====Internship Candidate=====");
                }
                System.out.printf("%5s|%5s",
                    candidate.getFirstName() , candidate.getLastName()
                    );
            }
        }
    }

    /**
     * Use to display information 
     * 
     */
    public void display(){
        if(list.isEmpty()){
            System.out.println("List empty");
            return;
        }
        list.forEach(ls-> ls.display());
    }

    /**
     * 
     * @param list is list take from input
     */
    public void display(List<Candidate> list){
        if(list.isEmpty()){
            System.out.println("List empty");
            return;
        }
        list.forEach(ls-> ls.display());
    }
}

