import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;

public class MatGui {
    MatFinner matfinner;
    JFrame vindu;

    //panel for ingredienser
    JPanel ingrediensPanel;
    JButton legTilIngrediens;
    JTextField ingrediensInput;

    //panel for matretter
    JButton leggTilMat;
    JPanel matPanel;
    
    JTextField matInput;

    MatGui(){
        lagvindu();
    }
    




    public void lagvindu(){
        try { UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());   
        } catch (Exception e) { System.exit(1); }

        vindu = new JFrame("matfinner");

        
        ingrediensPanel = new JPanel();
        legTilIngrediens = new JButton("legg til ingrediens: ");
        ingrediensInput = new JTextField(" ");
        ingrediensPanel.add(legTilIngrediens);
        ingrediensPanel.add(ingrediensInput);
        
        
        matPanel = new JPanel();//lager panel for mat inputt
        leggTilMat = new JButton("legg til matrett");
        matInput = new JTextField(" ");
        matPanel.add(leggTilMat);
        matPanel.add(matInput);

        vindu.setPreferredSize(new Dimension(300, 200));//

        vindu.add(matPanel);
        vindu.add(ingrediensPanel);

        vindu.setLocationRelativeTo(null);
        vindu.pack();
        vindu.setVisible(true);






    }

    public void leggTilMatrett(String rett){

    }




    private class Trykk implements ActionListener{
        String knapp;
        
        Trykk(String knapp){
            this.knapp = knapp;
        }
        @Override
        public void actionPerformed(ActionEvent e){
            if(knapp.equals("ja")){

            }else{

            }
        }

    }
}
