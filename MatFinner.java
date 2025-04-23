import java.util.*;

public class MatFinner {
    ArrayList<Matrett> matrett  = new ArrayList<>();
    ArrayList<String> beholdning = new ArrayList<>();



    public void settBeholdning(){
        Scanner scanner = new Scanner(System.in);
        System.err.println("skriv inn en ingrediens du har! (skriv ¨stop¨ når du er ferdig. )");
        String ingrediens = scanner.nextLine();
        while(ingrediens != "" && ingrediens != "stop"){
            beholdning.add(ingrediens);
            System.err.println("skriv en til! ");
            ingrediens = scanner.nextLine();
        }
        System.out.println();
    }

    public void leggTilMatrett(){
        System.err.println("hva heter matretten?");
        Scanner scanner = new Scanner(System.in);
        String navn = scanner.nextLine();
        Matrett mat = new Matrett(navn);
        matrett.add(mat);
        System.out.println("lagt til " + mat.navn + " vil du legge til en til?");
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


    
}
