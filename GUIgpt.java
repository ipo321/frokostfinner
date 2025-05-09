import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GUIgpt {
    private MatFinner matfinner;
    private JFrame vindu;
    
    // Panel for ingredienser
    private JPanel ingrediensPanel;
    private JButton leggTilIngrediens;
    private JTextField ingrediensInput;
    private JList<String> ingrediensList;
    private DefaultListModel<String> ingrediensModel;
    
    // Panel for matretter
    private JPanel matrettPanel;
    private JButton leggTilMatrett;
    private JTextField matrettInput;
    private JList<String> matrettList;
    private DefaultListModel<String> matrettModel;
    
    // Panel for sok
    private JPanel sokPanel;
    private JButton finnMatretter;
    private JList<String> resultatList;
    private DefaultListModel<String> resultatModel;
    
    public GUIgpt() {
        matfinner = new MatFinner();
        lagVindu();
        leggTilLyttere();
    }
    
    public void lagVindu() {
        try { 
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());   
        } catch (Exception e) { 
            System.err.println("Kunne ikke sette look and feel: " + e);
        }

        vindu = new JFrame("Matfinner");
        vindu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Hovedpanel med GridBagLayout for mer fleksibel layout
        JPanel hovedPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        
        // Oppretter ingredienspanel
        ingrediensPanel = new JPanel(new BorderLayout(5, 5));
        ingrediensPanel.setBorder(BorderFactory.createTitledBorder("Mine ingredienser"));
        
        JPanel ingrediensInputPanel = new JPanel(new BorderLayout(5, 0));
        ingrediensInput = new JTextField(15);
        leggTilIngrediens = new JButton("Legg til");
        ingrediensInputPanel.add(ingrediensInput, BorderLayout.CENTER);
        ingrediensInputPanel.add(leggTilIngrediens, BorderLayout.EAST);
        
        ingrediensModel = new DefaultListModel<>();
        ingrediensList = new JList<>(ingrediensModel);
        ingrediensList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane ingrediensScroll = new JScrollPane(ingrediensList);
        ingrediensScroll.setPreferredSize(new Dimension(200, 200));
        
        // Legg til en knapp for å fjerne ingredienser
        JButton fjernIngrediens = new JButton("Fjern valgt");
        fjernIngrediens.addActionListener(e -> {
            int selectedIndex = ingrediensList.getSelectedIndex();
            if (selectedIndex != -1) {
                matfinner.beholdning.remove(selectedIndex);
                ingrediensModel.remove(selectedIndex);
            }
        });
        
        JPanel ingrediensButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        ingrediensButtonPanel.add(fjernIngrediens);
        
        ingrediensPanel.add(ingrediensInputPanel, BorderLayout.NORTH);
        ingrediensPanel.add(ingrediensScroll, BorderLayout.CENTER);
        ingrediensPanel.add(ingrediensButtonPanel, BorderLayout.SOUTH);
        
        // Plassering av ingredienspanel
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        hovedPanel.add(ingrediensPanel, gbc);
        
        // Oppretter matrettpanel
        matrettPanel = new JPanel(new BorderLayout(5, 5));
        matrettPanel.setBorder(BorderFactory.createTitledBorder("Matretter"));
        
        JPanel matrettInputPanel = new JPanel(new BorderLayout(5, 0));
        matrettInput = new JTextField(15);
        leggTilMatrett = new JButton("Ny matrett");
        matrettInputPanel.add(matrettInput, BorderLayout.CENTER);
        matrettInputPanel.add(leggTilMatrett, BorderLayout.EAST);
        
        matrettModel = new DefaultListModel<>();
        matrettList = new JList<>(matrettModel);
        matrettList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane matrettScroll = new JScrollPane(matrettList);
        matrettScroll.setPreferredSize(new Dimension(200, 200));
        
        // Legg til en knapp for å vise og redigere detaljer
        JButton visMattrett = new JButton("Vis detaljer");
        visMattrett.addActionListener(e -> {
            int selectedIndex = matrettList.getSelectedIndex();
            if (selectedIndex != -1) {
                visMatrettDetaljer(matfinner.matretter.get(selectedIndex));
            }
        });
        
        JButton fjernMatrett = new JButton("Fjern valgt");
        fjernMatrett.addActionListener(e -> {
            int selectedIndex = matrettList.getSelectedIndex();
            if (selectedIndex != -1) {
                matfinner.matretter.remove(selectedIndex);
                matrettModel.remove(selectedIndex);
            }
        });
        
        JPanel matrettButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        matrettButtonPanel.add(visMattrett);
        matrettButtonPanel.add(fjernMatrett);
        
        matrettPanel.add(matrettInputPanel, BorderLayout.NORTH);
        matrettPanel.add(matrettScroll, BorderLayout.CENTER);
        matrettPanel.add(matrettButtonPanel, BorderLayout.SOUTH);
        
        // Plassering av matrettpanel
        gbc.gridx = 1;
        gbc.gridy = 0;
        hovedPanel.add(matrettPanel, gbc);
        
        // Oppretter panel for søkeresultater
        sokPanel = new JPanel(new BorderLayout(5, 5));
        sokPanel.setBorder(BorderFactory.createTitledBorder("Matretter du kan lage"));
        
        finnMatretter = new JButton("Finn matretter jeg kan lage nå");
        
        resultatModel = new DefaultListModel<>();
        resultatList = new JList<>(resultatModel);
        JScrollPane resultatScroll = new JScrollPane(resultatList);
        resultatScroll.setPreferredSize(new Dimension(200, 150));
        
        sokPanel.add(finnMatretter, BorderLayout.NORTH);
        sokPanel.add(resultatScroll, BorderLayout.CENTER);
        
        // Plassering av søkepanel
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.weighty = 0.5;
        hovedPanel.add(sokPanel, gbc);
        
        vindu.getContentPane().add(hovedPanel);
        vindu.setPreferredSize(new Dimension(600, 500));
        vindu.pack();
        vindu.setLocationRelativeTo(null);
        vindu.setVisible(true);
    }
    
    private void leggTilLyttere() {
        // Lytter for å legge til ingrediens
        leggTilIngrediens.addActionListener(e -> {
            String ingrediens = ingrediensInput.getText().trim();
            if (!ingrediens.isEmpty()) {
                matfinner.beholdning.add(ingrediens);
                ingrediensModel.addElement(ingrediens);
                ingrediensInput.setText("");
            }
        });
        
        // Lytter for Enter-tasten i ingrediensfeltet
        ingrediensInput.addActionListener(e -> {
            leggTilIngrediens.doClick();
        });
        
        // Lytter for å legge til matrett
        leggTilMatrett.addActionListener(e -> {
            String matrettNavn = matrettInput.getText().trim();
            if (!matrettNavn.isEmpty()) {
                Matrett nyMatrett = new Matrett(matrettNavn);
                visLeggTilIngrediensDialog(nyMatrett);
                matfinner.matretter.add(nyMatrett);
                matrettModel.addElement(matrettNavn);
                matrettInput.setText("");
            }
        });
        
        // Lytter for Enter-tasten i matrettfeltet
        matrettInput.addActionListener(e -> {
            leggTilMatrett.doClick();
        });
        
        // Lytter for å finne matretter basert på beholdning
        finnMatretter.addActionListener(e -> {
            resultatModel.clear();
            
            boolean funnGjort = false;
            for (Matrett rett : matfinner.matretter) {
                if (rett.sjekkMat(matfinner.beholdning)) {
                    resultatModel.addElement(rett.hentNavn());
                    funnGjort = true;
                }
            }
            
            if (!funnGjort) {
                JOptionPane.showMessageDialog(vindu, 
                    "Ingen matretter passer med din ingrediensbeholdning", 
                    "Ingen funn", 
                    JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }
    
    private void visLeggTilIngrediensDialog(Matrett matrett) {
        JDialog dialog = new JDialog(vindu, "Legg til ingredienser for " + matrett.hentNavn(), true);
        dialog.setLayout(new BorderLayout(5, 5));
        
        JPanel toppPanel = new JPanel(new BorderLayout(5, 5));
        JLabel info = new JLabel("Legg til ingredienser for " + matrett.hentNavn() + ":");
        toppPanel.add(info, BorderLayout.NORTH);
        
        JPanel inputPanel = new JPanel(new BorderLayout(5, 0));
        JTextField ingrediensInput = new JTextField();
        JButton leggTilKnapp = new JButton("Legg til");
        inputPanel.add(ingrediensInput, BorderLayout.CENTER);
        inputPanel.add(leggTilKnapp, BorderLayout.EAST);
        toppPanel.add(inputPanel, BorderLayout.CENTER);
        
        dialog.add(toppPanel, BorderLayout.NORTH);
        
        DefaultListModel<String> ingrediensModel = new DefaultListModel<>();
        JList<String> ingrediensList = new JList<>(ingrediensModel);
        JScrollPane scrollPane = new JScrollPane(ingrediensList);
        dialog.add(scrollPane, BorderLayout.CENTER);
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton ferdigKnapp = new JButton("Ferdig");
        buttonPanel.add(ferdigKnapp);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        
        // Legg til lyttere
        ActionListener leggTilAction = e -> {
            String ingrediens = ingrediensInput.getText().trim();
            if (!ingrediens.isEmpty()) {
                matrett.guiLeggTilIngrediens(ingrediens);
                ingrediensModel.addElement(ingrediens);
                ingrediensInput.setText("");
                ingrediensInput.requestFocus();
            }
        };
        
        leggTilKnapp.addActionListener(leggTilAction);
        ingrediensInput.addActionListener(leggTilAction);
        
        ferdigKnapp.addActionListener(e -> dialog.dispose());
        
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(vindu);
        dialog.setVisible(true);
    }
    
    private void visMatrettDetaljer(Matrett matrett) {
        JDialog dialog = new JDialog(vindu, "Detaljer for " + matrett.hentNavn(), true);
        dialog.setLayout(new BorderLayout(10, 10));
        
        JPanel infoPanel = new JPanel(new BorderLayout(5, 5));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JLabel navnLabel = new JLabel("Matrett: " + matrett.hentNavn());
        navnLabel.setFont(new Font("Sans-Serif", Font.BOLD, 14));
        infoPanel.add(navnLabel, BorderLayout.NORTH);
        
        JPanel ingrediensListePanel = new JPanel(new BorderLayout(5, 5));
        ingrediensListePanel.setBorder(BorderFactory.createTitledBorder("Ingredienser"));
        
        DefaultListModel<String> ingrediensModel = new DefaultListModel<>();
        for (String ingrediens : matrett.hentIngredienser()) {
            ingrediensModel.addElement(ingrediens);
        }
        
        JList<String> ingrediensList = new JList<>(ingrediensModel);
        JScrollPane scrollPane = new JScrollPane(ingrediensList);
        scrollPane.setPreferredSize(new Dimension(200, 150));
        ingrediensListePanel.add(scrollPane, BorderLayout.CENTER);
        
        infoPanel.add(ingrediensListePanel, BorderLayout.CENTER);
        
        dialog.add(infoPanel, BorderLayout.CENTER);
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton lukkKnapp = new JButton("Lukk");
        lukkKnapp.addActionListener(e -> dialog.dispose());
        buttonPanel.add(lukkKnapp);
        
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        
        dialog.setSize(300, 300);
        dialog.setLocationRelativeTo(vindu);
        dialog.setVisible(true);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new GUIgpt();
        });
    }
}