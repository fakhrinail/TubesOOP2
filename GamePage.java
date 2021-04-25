import java.awt.Color;
import java.awt.event.*;

import javax.swing.*;

public class GamePage extends JFrame implements ActionListener{
    private JLabel[][] peta;
    private JButton wButton;
    private JButton aButton;
    private JButton sButton;
    private JButton dButton;
    private JButton battle;
    private JLabel player;
    private JButton engimons;
    private JButton skills;

    private JLayeredPane layeredPane;

    public void initComponent(){

        this.layeredPane = new JLayeredPane();
        this.layeredPane.setBounds(0,0,750,550);

        this.peta = new JLabel[10][10];
        for(int i=0; i<10; i++){
            for(int j=0; j<10; j++){
                this.peta[i][j] = new JLabel();
                this.peta[i][j].setOpaque(true);
                this.peta[i][j].setBounds(200 + 50*i, 50*j, 50, 50);
                if((i+j)%4 == 0){
                    this.peta[i][j].setBackground(Color.CYAN);
                }else if((i+j)%4 == 1){
                    this.peta[i][j].setBackground(Color.YELLOW);
                }else if((i+j)%4 == 2){
                    this.peta[i][j].setBackground(Color.RED);
                }else {
                    this.peta[i][j].setBackground(Color.BLACK);
                }
                this.layeredPane.add(this.peta[i][j], Integer.valueOf(0));
            }
        }

        ImageIcon playerIcon = new ImageIcon("player.png");

        this.player = new JLabel();
        this.player.setIcon(playerIcon);
        this.player.setBounds(205, 5, 40, 40);
        this.player.setOpaque(true);
        this.layeredPane.add(this.player, Integer.valueOf(1));

        this.wButton = new JButton();
        this.wButton.setBounds(75, 300, 50, 50);
        this.wButton.setText("W");
        this.wButton.addActionListener(this);
        this.wButton.setFocusable(false);
        this.layeredPane.add(this.wButton);

        this.aButton = new JButton();
        this.aButton.setBounds(25, 350, 50, 50);
        this.aButton.setText("A");
        this.aButton.addActionListener(this);
        this.aButton.setFocusable(false);
        this.layeredPane.add(this.aButton);

        this.sButton = new JButton();
        this.sButton.setBounds(75, 400, 50, 50);
        this.sButton.setText("S");
        this.sButton.addActionListener(this);
        this.sButton.setFocusable(false);
        this.layeredPane.add(this.sButton);

        this.dButton = new JButton();
        this.dButton.setBounds(125, 350, 50, 50);
        this.dButton.setText("D");
        this.dButton.addActionListener(this);
        this.dButton.setFocusable(false);
        this.layeredPane.add(this.dButton);

        this.battle = new JButton();
        this.battle.setBounds(75, 350, 50, 50);
        this.battle.setText("Battle");
        this.battle.setFocusable(false);
        this.layeredPane.add(this.battle);
        
        this.engimons = new JButton();
        this.engimons.setBounds(25, 100, 150, 50);
        this.engimons.setText("Engimons");
        this.engimons.setFocusable(false);
        this.layeredPane.add(this.engimons);

        this.skills = new JButton();
        this.skills.setBounds(25, 175, 150, 50);
        this.skills.setText("Skills");
        this.skills.setFocusable(false);
        this.layeredPane.add(this.skills);
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(750,550);
        this.add(this.layeredPane);
        
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == this.wButton){
            for(int i=0; i<50; i++){
                this.player.setLocation(this.player.getX(), this.player.getY()-1);
            }
        }
        if(e.getSource() == this.aButton){
            for(int i=0; i<50; i++){
                this.player.setLocation(this.player.getX()-1, this.player.getY());
            }
        }
        if(e.getSource() == this.sButton){
            for(int i=0; i<50; i++){
                this.player.setLocation(this.player.getX(), this.player.getY()+1);
            }
        }
        if(e.getSource() == this.dButton){
            for(int i=0; i<50; i++){
                this.player.setLocation(this.player.getX()+1, this.player.getY());
            }
        }
    }
}
