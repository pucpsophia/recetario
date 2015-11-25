package com.recetario.gui;


import com.hp.hpl.jena.rdf.model.Statement;
import com.recetario.Recetario;
import com.recetario.inference.AltasCalorias;
import com.recetario.inference.Dietetica;
import com.recetario.inference.IncluyenCarne;
import com.recetario.inference.Inference;
import com.recetario.inference.Ingredientes;
import com.recetario.inference.SubClass;
import com.recetario.inference.SubRecetas;
import com.recetario.search.RecetarioIndexer;
import com.recetario.search.RecetarioSearcher;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.lucene.queryparser.classic.ParseException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrador
 */
public class RecetarioJFrame extends javax.swing.JFrame {

    /**
     * Creates new form NewJFrame
     */
    public RecetarioJFrame() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BuscarTextField = new javax.swing.JTextField();
        BuscarButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TextosTextArea = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        ClasesTextArea = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Recetario v0.1");
        setResizable(false);

        BuscarTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BuscarTextFieldActionPerformed(evt);
            }
        });

        BuscarButton.setText("Buscar");
        BuscarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BuscarButtonActionPerformed(evt);
            }
        });

        jScrollPane1.setAutoscrolls(true);
        jScrollPane1.setEnabled(false);

        TextosTextArea.setEditable(false);
        TextosTextArea.setBackground(new java.awt.Color(252, 234, 216));
        TextosTextArea.setColumns(20);
        TextosTextArea.setRows(5);
        jScrollPane1.setViewportView(TextosTextArea);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Textos");

        ClasesTextArea.setEditable(false);
        ClasesTextArea.setBackground(new java.awt.Color(235, 253, 253));
        ClasesTextArea.setColumns(20);
        ClasesTextArea.setRows(5);
        jScrollPane2.setViewportView(ClasesTextArea);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Clases");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(BuscarTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 843, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BuscarButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 462, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 463, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BuscarTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BuscarButton))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 409, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap(79, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BuscarTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuscarTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BuscarTextFieldActionPerformed

    private void BuscarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuscarButtonActionPerformed
        // TODO add your handling code here:
        
        text=BuscarTextField.getText().trim();
        TextosTextArea.setText("");
        ClasesTextArea.setText("");
        initializeInferenceList();
        
        ArrayList<String> clases = toStrings(search());
        RecetarioIndexer indexer =  new RecetarioIndexer("fileDirectory", "indexDirectory");
        try {
            indexer.Load();
            RecetarioSearcher searcher  = new RecetarioSearcher("indexDirectory");  
        
            searcher.Open();
        //Search by text
            Map<String,String> textResults  =  searcher.SearchText(text);

            for(Map.Entry<String,String> texto : textResults.entrySet() )
            {

                TextosTextArea.append("\nDoc Id:  " + String.valueOf(Integer.parseInt(texto.getKey())+1) + "\n" );            
                TextosTextArea.append(texto.getValue());
                TextosTextArea.append("\n ------------------------------------------------------------------------------------------------------------ \n" );
            }

            System.out.println("==========================================");
            System.out.println("=========== Semantic result ==============");

            Map<String,String[]> result =  searcher.Search( clases );

            searcher.Close();



            for(Map.Entry<String, String[]> entry : result.entrySet())
            {

                ClasesTextArea.append("\n>Clase :  " + entry.getKey() + "\n" ); 
                String[] textos = entry.getValue();
                for (int i = 0; i < textos.length ; i++) {

                    ClasesTextArea.append("\nResult :  " + i + "\n" );            
                    ClasesTextArea.append(textos[i]);
                    ClasesTextArea.append("\n" );
                }

                ClasesTextArea.append("\n ------------------------------------------------------------------------------------------------------------ \n" );
            }        
            } catch (IOException ex) {
                Logger.getLogger(RecetarioJFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(RecetarioJFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
    }//GEN-LAST:event_BuscarButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(RecetarioJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RecetarioJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RecetarioJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RecetarioJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RecetarioJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BuscarButton;
    private javax.swing.JTextField BuscarTextField;
    private javax.swing.JTextArea ClasesTextArea;
    private javax.swing.JTextArea TextosTextArea;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables

    
    private static ArrayList<Inference> inferenceList;
    private static String text;
    private final static String inputRDF = "recetario.rdf";
    private final static String NS = "http://www.recetario.com/";
    
    private static void initializeInferenceList() {
        inferenceList = new ArrayList<>();
        inferenceList.add(new SubClass(inputRDF, "rules.txt", NS));
        inferenceList.add(new AltasCalorias(inputRDF, "rules.txt", NS));
        inferenceList.add(new IncluyenCarne(inputRDF, "rules.txt", NS));
        inferenceList.add(new Ingredientes(inputRDF, "rules.txt", NS));
        inferenceList.add(new SubRecetas(inputRDF, "rules.txt", NS));
        inferenceList.add(new Dietetica(inputRDF, "rules_diet.txt", NS));
    }
    
    private static ArrayList<Statement> search() {
        ArrayList<Statement> result = new ArrayList<>();
        Iterator<Inference> iterator = inferenceList.iterator();
        while (iterator.hasNext()) {
            try {
                ArrayList<Statement> inferenceResult = iterator.next().search(WordUpFormat(text));
                result.removeAll(inferenceResult);
                result.addAll(inferenceResult);
            } catch (FileNotFoundException | ClassCastException ex) {
                Logger.getLogger(Recetario.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }
    
    private static ArrayList<String> toStrings(ArrayList<Statement> statements) {
        ArrayList<String> result = new ArrayList<>();
        Iterator<Statement> iterator = statements.iterator();
        
        //System.out.println("-- Resultado de la búsqueda --");
        while (iterator.hasNext()) {
            Statement stmt = iterator.next();
            String subject = stmt.getSubject().getLocalName();
            String object = stmt.getResource().getLocalName();
            System.out.println(stmt.toString());
            
            if (subject != null) {
                result.remove(subject);
                result.add(subject);
            }
            
            if (object != null) {
                result.remove(object);
                result.add(object);
            }
        }
        return result;
    }
        
    private static String WordUpFormat(String wordup) {
        
        String[] word_list = {};
        wordup = wordup.trim();
        String cad="";
        if (wordup.indexOf(" ") >= 0) {
            wordup = wordup.replaceAll("  ", " ");
            word_list = wordup.split(" ");
            for (String word: word_list) {
                cad =  cad + String.valueOf(word.charAt(0)).toUpperCase() + word.substring(1);
            }            
        } else {
            cad = String.valueOf(wordup.charAt(0)).toUpperCase() + wordup.substring(1);
        }
        return cad;
    }
    
}