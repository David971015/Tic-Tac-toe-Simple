import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToe {
    int boardWidth = 600;
    int boardHeight = 650;//50px for the board on top

    JFrame frame = new JFrame("Tic-Tac-Toe");
    JLabel lbTitle = new JLabel();
    JPanel panelText = new JPanel();
    JPanel panelBoard = new JPanel();

    JButton[][] btBoard = new JButton[3][3];
    JButton btRestart = new JButton();
    String playerX = "X";
    String playerO = "O";
    String currentPlayer = playerX;
    boolean gameOver = false;
    int tie = 0;

    public TicTacToe(){
        frame.setVisible(true);
        frame.setLocationRelativeTo(this.frame);
        frame.setSize(boardWidth,boardHeight);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        lbTitle.setFont(new Font("Arial", Font.BOLD,50));
        lbTitle.setBackground(Color.darkGray);
        lbTitle.setForeground(Color.WHITE);
        lbTitle.setText("Tic-Tac-Toe");
        lbTitle.setHorizontalAlignment(JLabel.CENTER);
        lbTitle.setOpaque(true);

        btRestart.setBackground(Color.darkGray);
        btRestart.setForeground(Color.white);
        btRestart.setFont(new Font("Arial",Font.BOLD,16));
        btRestart.setText("Restart");
        btRestart.setFocusable(false);
        btRestart.setOpaque(true);

        panelText.setLayout(new BorderLayout());
        panelText.add(lbTitle);
        panelText.add(btRestart,BorderLayout.EAST);
        frame.add(panelText,BorderLayout.NORTH);

        panelBoard.setLayout(new GridLayout(3,3));
        panelBoard.setBackground(Color.darkGray);
        frame.add(panelBoard);

        //fill boardPanel with buttons
        for (int row=0; row<3; row++){
            for (int column = 0; column < 3; column++) {
                JButton tile = new JButton();
                btBoard[row][column] = tile;
                panelBoard.add(tile);

                setBoardStyleColor(tile);
                btAction(tile);
                restartGame(tile);
            }
        }
    }

    private void test(JButton tile) {
        btRestart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tie = 0;
                setBoardStyleColor(tile);
                lbTitle.setText("Tic-Tac-Toe");
                for (int r = 0; r < 3; r++) {
                    for (int c = 0; c < 3; c++) {
                        btBoard[r][c].setText("");
                    }
                }
                gameOver = false;
            }
        });
    }

    private void setBoardStyleColor(JButton tile) {
        tile.setBackground(Color.darkGray);
        tile.setForeground(Color.WHITE);
        tile.setFont(new Font("Arial", Font.BOLD,120));
        tile.setFocusable(false);
    }

    private void restartGame(JButton tile){
        btRestart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tie = 0;
                setBoardStyleColor(tile);
                lbTitle.setText("Tic-Tac-Toe");
                for (int r = 0; r < 3; r++) {
                    for (int c = 0; c < 3; c++) {
                        btBoard[r][c].setText("");
                    }
                }
                gameOver = false;
            }
        });
    }

    private void btAction(JButton tile){
        tile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(gameOver) return;
                JButton tile = (JButton) e.getSource();
                if (tile.getText().equals("")) {
                    tile.setText(currentPlayer);
                    tie++;
                    checkWinner();
                    if (!gameOver) {
                        currentPlayer = (currentPlayer.equals(playerX)) ? playerO : playerX;
                        lbTitle.setText(currentPlayer + "`s turn");
                    }
                }
            }
        });
    }

    private void checkWinner() {
        //horizontal
        for (int r = 0; r < 3; r++) {
            if (btBoard[r][0].getText().equals("")) continue;

            if (btBoard[r][0].getText().equals(btBoard[r][1].getText()) &&
                    btBoard[r][1].getText().equals(btBoard[r][2].getText())) {
                gameOver = true;
                for (int c = 0; c < 3; c++) {
                    setWinnerStyle(btBoard[r][c]);
                }
                return;
            }
        }
        //vertical
        for (int c = 0; c < 3; c++) {
            if (btBoard[0][c].getText().equals("")) continue;

            if (btBoard[0][c].getText().equals(btBoard[1][c].getText()) &&
                    btBoard[1][c].getText().equals(btBoard[2][c].getText())) {
                gameOver = true;
                for (int r = 0; r < 3; r++) {
                    setWinnerStyle(btBoard[r][c]);
                }
                return;
            }
        }
        //diagonal
        if (btBoard[0][0].getText() != "" &&
            btBoard[0][0].getText().equals(btBoard[1][1].getText()) &&
            btBoard[1][1].getText().equals(btBoard[2][2].getText())) {

            gameOver = true;
            for (int i = 0; i < 3; i++) setWinnerStyle(btBoard[i][i]);
            return;
        }

        //anti-diagonal
        if (btBoard[0][2].getText() != "" &&
                btBoard[0][2].getText().equals(btBoard[1][1].getText()) &&
                btBoard[1][1].getText().equals(btBoard[2][0].getText())) {
            gameOver = true;
            setWinnerStyle(btBoard[0][2]);
            setWinnerStyle(btBoard[1][1]);
            setWinnerStyle(btBoard[2][0]);
            return;
        }
        //tie
        if(tie == 9){
            for (int r = 0; r < 3; r++) {
                for (int c = 0; c < 3 ; c++) {
                   setTieStyle(btBoard[r][c]);
                }
            }
            gameOver = true;
        }
    }

    private void setWinnerStyle(JButton tile){
        tile.setBackground(Color.gray);
        tile.setForeground(Color.green);
        lbTitle.setText(currentPlayer + " is the winner!");
        }

    private void setTieStyle(JButton tile){
        tile.setBackground(Color.gray);
        tile.setForeground(Color.orange);
        lbTitle.setText("Tie");
    }

}
