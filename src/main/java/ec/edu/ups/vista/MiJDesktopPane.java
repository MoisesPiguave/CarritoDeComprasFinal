package ec.edu.ups.vista;

import javax.swing.*;
import java.awt.*;

public class MiJDesktopPane extends JDesktopPane {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int cx = getWidth() / 2;
        int cy = getHeight() / 2 + 20;

        // Color base
        Color skin = new Color(255, 224, 189);
        Color shirt = new Color(50, 120, 200);
        Color hair = new Color(80, 50, 20);
        Color chair = new Color(100, 70, 50);
        Color pcGray = new Color(180, 180, 180);
        Color screenBlue = new Color(50, 100, 200);

        // ====== Cabeza ======
        g2.setColor(skin);
        g2.fillOval(cx - 40, cy - 140, 80, 80);

        // ====== Cabello ======
        g2.setColor(hair);
        g2.fillArc(cx - 40, cy - 140, 80, 50, 0, 180);  // cabellera simple

        // ====== Ojos ======
        g2.setColor(Color.WHITE);
        g2.fillOval(cx - 20, cy - 110, 15, 10);
        g2.fillOval(cx + 5, cy - 110, 15, 10);
        g2.setColor(Color.BLACK);
        g2.fillOval(cx - 15, cy - 107, 7, 7);
        g2.fillOval(cx + 10, cy - 107, 7, 7);

        // ====== Boca ======
        g2.setColor(Color.RED);
        g2.drawArc(cx - 10, cy - 80, 20, 10, 180, 180);

        // ====== Cuello ======
        g2.setColor(skin);
        g2.fillRect(cx - 10, cy - 60, 20, 15);

        // ====== Cuerpo (camisa) ======
        g2.setColor(shirt);
        g2.fillRoundRect(cx - 50, cy - 45, 100, 100, 30, 30);

        // ====== Brazos ======
        g2.fillRoundRect(cx - 75, cy - 40, 30, 70, 20, 20); // brazo izquierdo
        g2.fillRoundRect(cx + 45, cy - 40, 30, 70, 20, 20); // brazo derecho

        // ====== Manos ======
        g2.setColor(skin);
        g2.fillOval(cx - 85, cy + 20, 30, 30); // mano izquierda
        g2.fillOval(cx + 45, cy + 20, 30, 30); // mano derecha

        // ====== Silla ======
        g2.setColor(chair);
        g2.fillRoundRect(cx - 70, cy + 50, 140, 120, 40, 40);
        g2.fillRect(cx - 60, cy + 170, 20, 50);
        g2.fillRect(cx + 20, cy + 170, 20, 50);

        // ====== Mesa ======
        g2.setColor(new Color(120, 80, 40));
        g2.fillRect(cx - 130, cy + 160, 260, 20);
        g2.fillRect(cx - 130, cy + 180, 20, 90);
        g2.fillRect(cx + 110, cy + 180, 20, 90);

        // ====== Monitor ======
        int monX = cx - 40;
        int monY = cy - 90;
        int monW = 120;
        int monH = 90;

        g2.setColor(pcGray);
        g2.fillRoundRect(monX, monY, monW, monH, 15, 15); // monitor
        g2.setColor(screenBlue);
        g2.fillRoundRect(monX + 15, monY + 15, monW - 30, monH - 30, 8, 8); // pantalla

        // Monitor base
        g2.setColor(pcGray.darker());
        g2.fillRect(monX + 40, monY + monH, 40, 10);
        g2.fillRect(monX + 50, monY + monH + 10, 20, 20);

        // ====== Teclado ======
        int keyX = cx - 70;
        int keyY = cy + 60;
        int keyW = 140;
        int keyH = 30;

        g2.setColor(pcGray.darker());
        g2.fillRoundRect(keyX, keyY, keyW, keyH, 15, 15);

        // Teclas (simplificadas)
        g2.setColor(pcGray.brighter());
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 3; j++) {
                g2.fillRect(keyX + 10 + i * 18, keyY + 5 + j * 8, 15, 6);
            }
        }

        // ====== Cables ======
        g2.setStroke(new BasicStroke(3));
        g2.setColor(Color.BLACK);
        g2.drawLine(monX + monW / 2, monY + monH + 30, cx, cy + 200);

    }
}
