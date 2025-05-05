import java.util.*;

public class MatFinner {
    ArrayList<Matrett> matretter  = new ArrayList<>();
    ArrayList<String> beholdning = new ArrayList<>();


    //setter beholdningen man har.
    public void settBeholdning(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("skriv inn en ingrediensene du har! (skriv ¨stop¨ når du er ferdig. )");
        String ingrediens = scanner.nextLine();

        while(ingrediens != "" && ingrediens != "stop"){
            beholdning.add(ingrediens);
            System.out.println("flere ingredienser? hvis ikke trykk enter. ");
            ingrediens = scanner.nextLine();
        }
        System.out.println();
        
    }


    //når man kjører denne kan man legge til så mange matretter man ønsker, med input fra bruker
    public void leggTilMatrett(){
        Scanner scanner = new Scanner(System.in);
        System.err.println("hva heter matretten du vil legge til?");

        String navn = scanner.nextLine();


        matretter.add(new Matrett(navn));
        System.out.println(navn + " er lagt til.\n " + "\nvil du legge til en til?");

        navn = scanner.nextLine();
        if(navn.equals("ja")){
            leggTilMatrett();
        }

    }

    public void skrivBeholdning(){
        for(String ing: beholdning){
            System.out.println(ing);
        }
    }


    public void hentMatretter(){
        for(Matrett rett : matretter){
            if(rett.sjekkMat(beholdning)){
                System.err.println(rett);
            }
        }
    }


    
}
