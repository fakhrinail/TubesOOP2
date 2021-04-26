import java.awt.Color;
import java.awt.Image;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;

public class GamePage extends JFrame implements ActionListener{
    //UI Components
    private JLabel[][] peta;
    private JButton wButton;
    private JButton aButton;
    private JButton sButton;
    private JButton dButton;
    private JButton battle;
    private JLabel player;
    private JButton engimons;
    private ArrayList<JLabel> wildEngimons;
    private JButton skills;
    private JLayeredPane layeredPane;

    private static final int maxInventoryItem = 20;
    private JLabel popUp;
    private JLabel popUpTitle;
    private int whichPopUp;
    private JLabel[] inventoryInfos;
    private JButton[] inventoryActions;

    //Game Components
    private KoleksiSpecies allSpecies;
    private Player gamePlayer;
    private Peta gameMap;

    public void initGamestate(String filePath){
        Elemental.loadElementals("files/elementals.txt");
        this.allSpecies = new KoleksiSpecies("files/species.txt");

        //Inisialisasi Peta
        gameMap = new Peta("files/peta.txt",10,allSpecies.getAllSpecies());

        if(filePath.equals("")){//New Game
            Engimon starter = new Engimon(this.allSpecies.getSpeciesbyName("Narutomon"), "starterMon", "Cowok", "Bapak", "Cewek", "Ibu", 3, 1);
            this.gamePlayer = new Player(starter);
            //Testing inventory dlu
            starter = new Engimon(this.allSpecies.getSpeciesbyName("Firemon"), "Randomon", "Cowok", "Bapak", "Cewek", "Ibu", 3, 15);
            this.gamePlayer.addEngimon(starter);
            this.gamePlayer.addSkillItem(new Skill(starter.getSkills().get(0)));
            starter = new Engimon(this.allSpecies.getSpeciesbyName("Watermon"), "Randomon", "Cowok", "Bapak", "Cewek", "Ibu", 3, 15);
            this.gamePlayer.addEngimon(starter);
            this.gamePlayer.addSkillItem(new Skill(starter.getSkills().get(0)));
            starter = new Engimon(this.allSpecies.getSpeciesbyName("Watermon"), "Randomon", "Cowok", "Bapak", "Cewek", "Ibu", 3, 5);
            this.gamePlayer.addEngimon(starter);
            this.gamePlayer.addSkillItem(new Skill(starter.getSkills().get(0)));
            starter = new Engimon(this.allSpecies.getSpeciesbyName("Firemon"), "Randomon", "Cowok", "Bapak", "Cewek", "Ibu", 3, 25);
            this.gamePlayer.addEngimon(starter);
            this.gamePlayer.addSkillItem(new Skill(starter.getSkills().get(0)));
            
        }else{//Load Game
            
        }
    }

    public void initComponent(){

        this.layeredPane = new JLayeredPane();
        this.layeredPane.setBounds(0,0,750,550);

        this.peta = new JLabel[gameMap.getMapWidth()][gameMap.getMapHeight()];
        System.out.println(gameMap.getMapHeight());
        System.out.println(gameMap.getMapWidth());
        for(int i=0; i<gameMap.getMapWidth(); i++){
            for(int j=0; j<gameMap.getMapHeight(); j++){
                Cell currcell = gameMap.searchMap(j, i);
                this.peta[i][j] = new JLabel();
                this.peta[i][j].setOpaque(true);
                this.peta[i][j].setBounds(200 + 50*i, 50*j, 50, 50);
                if(currcell.getCellType().equals(CellType.GRASSLAND)){
                    this.peta[i][j].setBackground(Color.GREEN);
                }else if(currcell.getCellType().equals(CellType.SEA)){
                    this.peta[i][j].setBackground(Color.BLUE);
                }else if(currcell.getCellType().equals(CellType.TUNDRA)){
                    this.peta[i][j].setBackground(Color.YELLOW);
                }else if(currcell.getCellType().equals(CellType.MOUNTAIN)){
                    this.peta[i][j].setBackground(Color.CYAN);
                }else {
                    this.peta[i][j].setBackground(Color.BLACK);
                }
                this.layeredPane.add(this.peta[i][j], Integer.valueOf(0));
            }
        }

        ImageIcon playerIcon = new ImageIcon(getClass().getResource("files/player.png"));
        Image image = playerIcon.getImage();
        image = image.getScaledInstance(45, 45, java.awt.Image.SCALE_SMOOTH);
        playerIcon = new ImageIcon(image);
        this.wildEngimons = new ArrayList<>();
        //BufferedImage img = 
        

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
        this.engimons.addActionListener(this);
        this.engimons.setFocusable(false);
        this.layeredPane.add(this.engimons);

        this.skills = new JButton();
        this.skills.setBounds(25, 175, 150, 50);
        this.skills.setText("Skills");
        this.skills.addActionListener(this);
        this.skills.setFocusable(false);
        this.layeredPane.add(this.skills);


        //Popup Items
        this.whichPopUp = 0;
        this.popUp = new JLabel();
        this.popUp.setBounds(200, 0, 500, 700);
        this.popUp.setBackground(Color.GRAY);
        this.popUp.setOpaque(true);
        this.layeredPane.add(this.popUp, Integer.valueOf(2));

        this.popUpTitle = new JLabel();
        this.popUpTitle.setBounds(200, 0, 500, 100);
        this.popUpTitle.setBackground(Color.GRAY);
        this.popUpTitle.setOpaque(true);
        this.layeredPane.add(this.popUpTitle, Integer.valueOf(3));

        this.inventoryInfos = new JLabel[maxInventoryItem];
        this.inventoryActions = new JButton[maxInventoryItem];
        for(int i=0; i<maxInventoryItem; i++){
            this.inventoryInfos[i] = new JLabel();
            this.inventoryInfos[i].setBounds(200, 100+25*i, 350, 25);
            this.inventoryInfos[i].setOpaque(true);
            this.inventoryInfos[i].setBackground(Color.GRAY);
            this.layeredPane.add(this.inventoryInfos[i], Integer.valueOf(3));

            this.inventoryActions[i] = new JButton();
            this.inventoryActions[i].setBounds(600, 100+25*i, 100, 25);
            this.inventoryActions[i].addActionListener(this);
            this.inventoryActions[i].setFocusable(false);
            this.layeredPane.add(this.inventoryActions[i], Integer.valueOf(3));
        }
        this.setPopUp(0);
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(750,750);
        this.add(this.layeredPane);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==this.wButton || e.getSource()==this.aButton  || e.getSource()==this.sButton || e.getSource()==this.dButton){
            this.moveActionPerformed(e);
        }

        if(e.getSource() == this.engimons){
            if(this.whichPopUp == 1){
                this.setPopUp(0);
            }else{
                this.setPopUp(1);
            }
        }
        if(e.getSource() == this.skills){
            if(this.whichPopUp == 2){
                this.setPopUp(0);
            }else{
                this.setPopUp(2);
            }
        }
        
        for(int i=0; i<maxInventoryItem; i++){
            if(e.getSource() == this.inventoryActions[i]){
                System.out.print("Dipencet button ");
                System.out.println(i);
                if(this.whichPopUp == 1){
                    this.gamePlayer.setActiveEngimon(i);
                }else if(this.whichPopUp == 2){

                }
                this.setPopUp(this.whichPopUp);
            }
        }

    }

    private void moveActionPerformed(ActionEvent e){
        Boolean validMovement = true;
        Integer prevX = gamePlayer.getPlayerX();
        Integer prevY = gamePlayer.getPlayerY();
        if(e.getSource() == this.wButton){
            // for(int i=0; i<50; i++){
            //     this.player.setLocation(this.player.getX(), this.player.getY()-1);
            // }
            gamePlayer.w();

            if(gameMap.searchMap(gamePlayer.getPlayerX(), gamePlayer.getPlayerY())==null){
                gamePlayer.s();
                validMovement = 1==2;
            }
        }
        if(e.getSource() == this.aButton){
            // for(int i=0; i<50; i++){
            //     this.player.setLocation(this.player.getX()-1, this.player.getY());
            // }
            gamePlayer.a();
            if(gameMap.searchMap(gamePlayer.getPlayerX(), gamePlayer.getPlayerY())==null){
                gamePlayer.d();
                validMovement = 1==2;
            }
        }
        if(e.getSource() == this.sButton){
            // for(int i=0; i<50; i++){
            //     this.player.setLocation(this.player.getX(), this.player.getY()+1);
            // }
            gamePlayer.s();
            if(gameMap.searchMap(gamePlayer.getPlayerX(), gamePlayer.getPlayerY())==null){
                gamePlayer.w();
                validMovement = 1==2;
            }
        }
        if(e.getSource() == this.dButton){
            // for(int i=0; i<50; i++){
            //     this.player.setLocation(this.player.getX()+1, this.player.getY());
            // }
            gamePlayer.d();
            if(gameMap.searchMap(gamePlayer.getPlayerX(), gamePlayer.getPlayerY())==null){
                gamePlayer.a();
                validMovement = 1==2;
            }
        }
        if(validMovement){
            Main.jumlahTurns++;
            gameMap.setPlayerPos(gamePlayer);
            if(Main.jumlahTurns%4==0){gameMap.generateEngimon();}
            if(Main.jumlahTurns%3==0&&Main.jumlahTurns>3){gameMap.randomEngimon();}
            refreshMap();
        }

    }
    
    private void setPopUp(int ID){
        this.whichPopUp = ID;
        this.popUp.setVisible(ID>0);
        this.popUpTitle.setVisible(ID>0);
        this.popUpTitle.setText(this.gamePlayer.getActiveEngimon().printDetail());
        this.wButton.setEnabled(ID==0);
        this.aButton.setEnabled(ID==0);
        this.sButton.setEnabled(ID==0);
        this.dButton.setEnabled(ID==0);
        for(int i=0; i<maxInventoryItem; i++){
            this.inventoryInfos[i].setVisible(false);
            this.inventoryActions[i].setVisible(false);
        }
        if(ID == 1 || ID == 3){
            ArrayList<String> inventoryList = this.gamePlayer.getInventory(false);
            for(int i=0; i<inventoryList.size(); i++){
                this.inventoryInfos[i].setVisible(true);
                this.inventoryInfos[i].setText(inventoryList.get(i));
                this.inventoryActions[i].setVisible(true);
                if(ID == 1){
                    this.inventoryActions[i].setText("Switch");
                }else{
                    this.inventoryActions[i].setText("Breed");
                }
            }
        }
        if(ID == 2){
            ArrayList<String> inventoryList = this.gamePlayer.getInventory(true);
            System.out.print("Tes");
            System.out.println(inventoryList.size());
            for(int i=0; i<inventoryList.size(); i++){
                this.inventoryInfos[i].setVisible(true);
                this.inventoryInfos[i].setText(inventoryList.get(i));
                this.inventoryActions[i].setVisible(true);
                this.inventoryActions[i].setText("Use");
            }
        }
    }

    private void refreshMap(){
        //System.out.println(player.getX()+" - "+player.getY());
        if(gameMap.searchMap(gamePlayer.getPlayerX(), gamePlayer.getPlayerY())!=null){
            this.player.setLocation(205+gamePlayer.getPlayerX()*50, 5+gamePlayer.getPlayerY()*50);
        }

        //COBA TAMPILIN RANDOM ENGIMON
        for(int i =0 ;i<gameMap.getMapWidth(); i++){
            for(int j=0; j <gameMap.getMapHeight(); j++){
                this.peta[i][j].setText("");
            }
        }

        this.gameMap.getWildEngimons().forEach(i -> {
            this.peta[i.getEngimonY()][i.getEngimonX()].setText("Eg");
        });
    }
}
