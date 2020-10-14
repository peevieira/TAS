package br.edu.materdei.tas.desktop.gui.core;


import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;


public class TasImagePanel extends JPanel {
    private Image image;
    
    /**
     * Creates new form ImagePanel
     */
    public TasImagePanel() {
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
    }// </editor-fold>//GEN-END:initComponents


    @Override  
    public void paintComponent(Graphics g) {  
        super.paintComponent(g);
        if (image != null) {
            int iw = image.getWidth(this);
            int ih = image.getHeight(this);
            if (iw > 0 && ih > 0) {
                for (int x = 0; x < getWidth(); x += iw) {
                    for (int y = 0; y < getHeight(); y += ih) {
                        g.drawImage(image, x, y, iw, ih, this);
                    }
                }
            }  
        }
    }

    /**
     * @param image the image to set
     */
    public void setImage(Image image) {
        this.image = image;
        repaint();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}