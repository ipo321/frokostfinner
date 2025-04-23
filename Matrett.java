import java.util.*;

public class Matrett{
    String navn;
    ArrayList<String> ingrediens = new ArrayList<>();

    public Matrett(String navn){
        this.navn = navn;
        Scanner scanner = new Scanner(System.in);

        System.err.println("skriv inn en ingrediense i matretten! (trykk enter nor du er ferdig)");
        String ing = scanner.nextLine();
        while(ing != "" && ing != "stop"){
            ingrediens.add(ing);
            System.err.println("skriv en til! ");
            ing = scanner.nextLine();
        }
    }

    public void leggTilIngrediens(String i){
        ingrediens.add(i);
    }

    public boolean sjekkMat(ArrayList<String> beholdning){
        for(String ing: ingrediens){
            if(!beholdning.contains(ing)){
                return false;
            }
        }
        return true;
    }


    public String hentNavn(){
        return navn;
    }


    public ArrayList<String> hentIngredienser(){
        return ingrediens;
    }


    @Override
    public String toString(){
        String tekst = navn + "ingredienser:";

        for(String ing: ingrediens){
            tekst += "\n" + ing; 
        }
        
        return tekst;
    }
    
}