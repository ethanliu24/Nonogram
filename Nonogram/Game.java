import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
//import javax.swing.border.*;

public class Game extends JFrame implements ActionListener, KeyListener {

    final int MAP_SIZE = Nonogram.size + 1;
    final int WIDTH = 800;
    final int HEIGHT = 800;
    boolean changeMode = false;
    static boolean restart;

    GetMap map = new GetMap();
    String[][] gameFile = map.readMap();

    JFrame frame = new JFrame();
    JPanel ttl = new JPanel();
    JPanel btn = new JPanel();
    JLabel txtTitle = new JLabel();
    JButton[][] buttons = new JButton[MAP_SIZE][MAP_SIZE];

    Game() {
        restart = false;

        //frame setup
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);
        frame.getContentPane().setBackground(new Color(50, 50, 50));
        frame.setResizable(true);
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        //text setup
        txtTitle.setBackground(Color.BLACK);
        txtTitle.setForeground(Color.WHITE);
        txtTitle.setFont(new Font("Ink Free", Font.BOLD, 50));
        txtTitle.setHorizontalAlignment(JLabel.CENTER);
        txtTitle.setText("Nonogram");
        txtTitle.setOpaque(true);

        //title and border texts set up
        ttl.setLayout(new BorderLayout());

        //button setup
        btn.setLayout(new GridLayout(MAP_SIZE, MAP_SIZE));
        btn.setBackground(new Color(150, 150, 150));       

        //gameboard
        for (int i = 0; i < MAP_SIZE; i++) {
            for (int j = 0; j < MAP_SIZE; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setPreferredSize(new Dimension(1, 1));
                btn.add(buttons[i][j]);
                buttons[i][j].setBorder(BorderFactory.createBevelBorder(1, Color.LIGHT_GRAY, Color.LIGHT_GRAY));
                buttons[i][j].setText("0");
                buttons[i][j].setBackground(Color.WHITE);
                buttons[i][j].setForeground(Color.WHITE);
                buttons[i][j].setFocusable(true);
                buttons[i][j].addActionListener(this);
                buttons[i][j].addKeyListener(this);
            }
        }

        btn.add(buttons[MAP_SIZE - 1][MAP_SIZE - 1]);
        buttons[MAP_SIZE - 1][MAP_SIZE - 1].setText("RESET");
        buttons[MAP_SIZE - 1][MAP_SIZE - 1].setForeground(Color.BLACK);
        buttons[MAP_SIZE - 1][MAP_SIZE - 1].setBackground(new Color(240, 240, 240));

        //border hints setup
        String[][] row = map.hintsRow();
        String[][] col = map.hintsCol();

        for (int i = 0; i < MAP_SIZE - 1; i++) {
            //row
            buttons[MAP_SIZE - 1][i].setEnabled(false);
            buttons[MAP_SIZE - 1][i].setBackground(new Color(240, 240, 240));

            //col
            buttons[i][MAP_SIZE - 1].setEnabled(false);
            buttons[i][MAP_SIZE - 1].setBackground(new Color(240, 240, 240));

            String rowTxt = "";
            String colTxt = "";

            //row
            for (int j = 0; j < row[i].length; j++) {
                rowTxt += row[i][j] + " ";
            }

            buttons[i][MAP_SIZE - 1].setText(rowTxt);

            //col
            for (int j = 0; j < col[i].length; j++) {
                colTxt += col[i][j] + " ";
            }

            buttons[MAP_SIZE - 1][i].setText(colTxt);
        }

        ttl.add(txtTitle); 
        frame.add(ttl, BorderLayout.SOUTH);
        frame.add(btn); 

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons.length; j++) {
                if (e.getSource() == buttons[MAP_SIZE - 1][MAP_SIZE - 1]) {
                    reset();
                } else if (!changeMode) {
                    if (e.getSource() == buttons[i][j]) {
                        if (buttons[i][j].getText() == "0") {
                            buttons[i][j].setBackground(Color.BLACK);
                            buttons[i][j].setForeground(Color.BLACK);
                            buttons[i][j].setText("1");

                            checkWin();
                        } else if (buttons[i][j].getText() == "X") {
    
                        } else {
                            buttons[i][j].setBackground(Color.WHITE);
                            buttons[i][j].setForeground(Color.WHITE);
                            buttons[i][j].setText("0");
                            checkWin();
                        } 
                    }
                } else {
                    if (e.getSource() == buttons[i][j]) {
                        if (buttons[i][j].getText() == "0") {
                            buttons[i][j].setBackground(Color.WHITE);
                            buttons[i][j].setForeground(Color.BLACK);
                            buttons[i][j].setText("X");
                            checkWin();
                        } else if (buttons[i][j].getText() == "1") {
    
                        } else {
                            buttons[i][j].setBackground(Color.WHITE);
                            buttons[i][j].setForeground(Color.WHITE);
                            buttons[i][j].setText("0");
                            checkWin(); 
                        } 
                    }
                }
            } 
        }
    }

    

    public void checkWin() {
        boolean win = true;

        for (int i = 0; i < MAP_SIZE - 1 && win; i++) {
            for (int j = 0; j < MAP_SIZE - 1 && win; j++) {
                if (buttons[i][j].getText() == "X" && gameFile[i][j].equals("0")) {
                    win = true;
                } else if (!buttons[i][j].getText().equals(gameFile[i][j])) {
                    win = false;
                }
            }
        }

        if (win) {
            for (int i = 0; i < MAP_SIZE; i++) {
                for (int j = 0; j < MAP_SIZE; j++) {
                    buttons[i][j].setEnabled(false);
                }
            }

            Menu.picName();
            frame.dispose();
        }
    } 



    public void reset() {
        for (int i = 0; i < MAP_SIZE - 1; i++) {
            for (int j = 0; j < MAP_SIZE - 1; j++) {
                buttons[i][j].setText("0");
                buttons[i][j].setForeground(Color.WHITE);
                buttons[i][j].setBackground(Color.WHITE);
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        changeMode = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        changeMode = false;
    }

    //https://stackoverflow.com/questions/2536873/how-can-i-set-size-of-a-button
    
    //useless
    @Override
    public void keyTyped(KeyEvent e) {
    }
    
}
