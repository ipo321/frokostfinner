import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;

public class MatGui {
    MatFinner matfinner;
    JFrame vindu;
    JPanel panel;
    JButton jaKnapp;
    JButton neste;
    
    JTextField tekst;

    MatGui(){
        lagvindu();
    }
    




    public void lagvindu(){
        try { UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());   
        } catch (Exception e) { System.exit(1); }
        vindu = new JFrame("matfinner");
        panel = new JPanel();
        jaKnapp = new JButton("JA");
        neste = new JButton("neste");
        tekst = //finn ut hvordan man får systemoutprint på skjerm. 
        vindu.setPreferredSize(new Dimension(300, 200));//

        vindu.add(panel);
        panel.add(jaKnapp);
        panel.add(neste);
        
        vindu.setLocationRelativeTo(null);
        vindu.pack();
        vindu.setVisible(true);






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
