
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import org.jsoup.Jsoup;


public class Uyg extends javax.swing.JFrame {
    ArrayList<HashMap> urunler = new ArrayList<>();
    int resimSayisi;
    int resimSira=0;
    
    public Uyg() {
        initComponents();
        String url = "http://jsonbulut.com/json/product.php";
        HashMap<String, String> hm = new HashMap<>();
        hm.put("ref", "c539de7cf7ab8f5eeb54ea3aaf93f727");
        hm.put("start", "0");
        txtBilgi.setLineWrap(true);
        //txtBilgi.setWrapStyleWord( true );
        try {
            String data = Jsoup.connect(url).timeout(30000).ignoreContentType(true).data(hm).get().body().text();
            JsonObject obj = (JsonObject) new JsonParser().parse(data);
            JsonObject elmt = obj.getAsJsonArray("Products").get(0).getAsJsonObject();
            boolean durum = elmt.get("durum").getAsBoolean();
            String mesaj = elmt.get("mesaj").getAsString();
            DefaultListModel dlm = new DefaultListModel();
            if(durum){
                
                int  bilgiSay=elmt.getAsJsonArray("bilgiler").size();
                for(int i=0;i<bilgiSay;i++){
                     HashMap<String, String> urun = new HashMap<>();
                    JsonObject bilgi=elmt.getAsJsonArray("bilgiler").get(i).getAsJsonObject();
                    dlm.addElement(""+bilgi.get("productName").getAsString());
                    urun.put("urunAdi", bilgi.get("productName").getAsString());
                    urun.put("urunOzellik", bilgi.get("description").getAsString());
                    urun.put("urunSatisFiyati", bilgi.get("price").getAsString());
                    boolean image=bilgi.get("image").getAsBoolean();
                    if(image){ 
                        int resimSay=bilgi.getAsJsonArray("images").size();
                        urun.put("resimSay", ""+resimSay);
                        for (int j = 0; j < resimSay; j++) {
                            JsonObject resim=bilgi.getAsJsonArray("images").get(j).getAsJsonObject();
                            urun.put(""+j,""+resim.get("normal").getAsString());
                        } 
                    }else{
                        urun.put("resimSay", "0");
                    }
                    urunler.add(urun);
                }
            }
            lstUrun.setModel(dlm);
        } catch (IOException ex) {
            System.err.println("Hata : " + ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstUrun = new javax.swing.JList<>();
        jPanel2 = new javax.swing.JPanel();
        lblUrunResim = new javax.swing.JLabel();
        btnGeri = new javax.swing.JButton();
        btnileri = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtBilgi = new javax.swing.JTextArea();
        jPanel4 = new javax.swing.JPanel();
        lblBaslik = new javax.swing.JLabel();
        lblSatisFiyati = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lstUrun.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        lstUrun.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lstUrunValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(lstUrun);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Ürün Resim"));

        btnGeri.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        btnGeri.setText("<");
        btnGeri.setEnabled(false);
        btnGeri.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGeriActionPerformed(evt);
            }
        });

        btnileri.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        btnileri.setText(">");
        btnileri.setEnabled(false);
        btnileri.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnileriActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnGeri)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnileri)
                .addGap(85, 85, 85))
            .addComponent(lblUrunResim, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(lblUrunResim, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnileri)
                    .addComponent(btnGeri))
                .addContainerGap())
        );

        txtBilgi.setColumns(20);
        txtBilgi.setRows(5);
        txtBilgi.setWrapStyleWord(true);
        jScrollPane2.setViewportView(txtBilgi);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 491, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Ürün Adı"));

        lblBaslik.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblBaslik.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblBaslik, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblBaslik, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
        );

        lblSatisFiyati.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblSatisFiyati.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblSatisFiyati, javax.swing.GroupLayout.PREFERRED_SIZE, 772, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 16, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblSatisFiyati, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void lstUrunValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lstUrunValueChanged
        int secim=lstUrun.getSelectedIndex();
        lblBaslik.setText(""+urunler.get(secim).get("urunAdi"));
        txtBilgi.setText(""+urunler.get(secim).get("urunOzellik"));
        lblSatisFiyati.setText("Satış Fiyatı : "+urunler.get(secim).get("urunSatisFiyati")+ " TL");
        resimSayisi=Integer.valueOf(""+urunler.get(secim).get("resimSay"));

        if(resimSayisi<2){
            btnGeri.setEnabled(false);
            btnileri.setEnabled(false);
        }else{
            btnGeri.setEnabled(false);
            btnileri.setEnabled(true);
        }
        resimSira=0;
        resimGoster();
        
    }//GEN-LAST:event_lstUrunValueChanged

    private void btnileriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnileriActionPerformed
        resimSira++;
        if(resimSira>=resimSayisi-1){
            btnileri.setEnabled(false);
            btnGeri.setEnabled(true);
        }else
        {
            btnGeri.setEnabled(true);
            btnileri.setEnabled(true);
        }
        resimGoster();
    }//GEN-LAST:event_btnileriActionPerformed

    private void btnGeriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGeriActionPerformed
        resimSira--;
        if(resimSira<=0){
            btnileri.setEnabled(true);
            btnGeri.setEnabled(false);
        }else
        {
            btnGeri.setEnabled(true);
            btnileri.setEnabled(true);
        }        
        resimGoster();
    }//GEN-LAST:event_btnGeriActionPerformed

    private void resimGoster(){
        int secim=lstUrun.getSelectedIndex();
        String urlResim = ""+urunler.get(secim).get(""+resimSira);
        try {
            URL ur = new URL(urlResim);
            BufferedImage img = ImageIO.read(ur);
            Image yeni = img.getScaledInstance(lblUrunResim.getWidth(), lblUrunResim.getHeight(), Image.SCALE_REPLICATE);
            lblUrunResim.setIcon(new ImageIcon(yeni));
            
        } catch (Exception e) {
        }
    }
    
    
    
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
            java.util.logging.Logger.getLogger(Uyg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Uyg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Uyg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Uyg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Uyg().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGeri;
    private javax.swing.JButton btnileri;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblBaslik;
    private javax.swing.JLabel lblSatisFiyati;
    private javax.swing.JLabel lblUrunResim;
    private javax.swing.JList<String> lstUrun;
    private javax.swing.JTextArea txtBilgi;
    // End of variables declaration//GEN-END:variables
}
