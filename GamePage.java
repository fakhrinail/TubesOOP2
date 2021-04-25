import java.awt.Color;

import javax.swing.*;

public class GamePage extends JFrame{
    private JLabel[][] peta;
    private JLayeredPane layeredPane;
    public void initComponent(){

        this.layeredPane = new JLayeredPane();
        this.layeredPane.setBounds(0,0,500,500);

        this.peta = new JLabel[10][10];
        for(int i=0; i<10; i++){
            for(int j=0; j<10; j++){
                this.peta[i][j] = new JLabel();
                this.peta[i][j].setOpaque(true);
                this.peta[i][j].setBounds(50*i, 50*j, 50, 50);
                if((i+j)%4 == 0){
                    this.peta[i][j].setBackground(Color.CYAN);
                }else if((i+j)%4 == 1){
                    this.peta[i][j].setBackground(Color.YELLOW);
                }else if((i+j)%4 == 2){
                    this.peta[i][j].setBackground(Color.RED);
                }else {
                    this.peta[i][j].setBackground(Color.BLACK);
                }
                this.layeredPane.add(this.peta[i][j]);
            }
        }
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500,500);
        this.add(this.layeredPane);
        
        this.setVisible(true);
    }
}
