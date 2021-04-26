import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;

public class StartPage extends JFrame implements ActionListener{
    private JButton startButton;
    private JButton loadButton;
    private JLayeredPane layeredPane;

    public void initComponent(){

        this.layeredPane = new JLayeredPane();
        this.layeredPane.setBounds(0,0,750,550);

        this.startButton = new JButton();
        this.startButton.setBounds(150, 100, 200, 50);
        this.startButton.setText("New Game");
        this.startButton.addActionListener(this);
        this.startButton.setFocusable(false);
        this.layeredPane.add(this.startButton);

        this.loadButton = new JButton();
        this.loadButton.setBounds(150, 200, 200, 50);
        this.loadButton.setText("Load Game");
        this.loadButton.addActionListener(this);
        this.loadButton.setFocusable(false);
        this.layeredPane.add(this.loadButton);
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500,500);
        this.add(this.layeredPane);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == this.startButton){
            GamePage frame = new GamePage();
            frame.initComponent();
            this.dispose();
        }
        if(e.getSource() == this.loadButton){

        }
    }
}
