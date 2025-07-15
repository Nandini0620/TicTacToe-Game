import java.awt.*;
import java.awt.event.*;

class FDemo extends Frame implements ActionListener
{
    Button b[] = new Button[9];
    String board[] = new String[9];
    int c = 1;
    Label statusLabel;
    Button restartButton;
    Color c3 = new Color(106,53,53);
  
   FDemo() 
	{
        setTitle("TIC TAC TOE");
        setLayout(null);

        Font f = new Font("Arial", Font.BOLD, 30);
        setFont(f);

        // Status label at the top
        statusLabel = new Label("Player O's Turn");
		statusLabel.setForeground(c3);
        statusLabel.setBounds(135, 50, 300, 35);
        add(statusLabel);


        int x = 100, y = 100, k = 0;

        for (int i = 0; i < 3; i++) {  // rows
            for (int j = 0; j < 3; j++) {  // columns
                b[k] = new Button();
                b[k].setBounds(x, y, 100, 100);
                add(b[k]);

                b[k].addActionListener(this);
                board[k] = "";

                x += 100;
                k++;
            }
            y += 100;
            x = 100;
        }

        // Restart Button
        restartButton = new Button("Restart Game");
        restartButton.setBounds(150, 420, 200, 40);
		Color c4 = new Color(255,255,255);
		restartButton.setForeground(c4);
		Color c5 = new Color(0,0,0);
		restartButton.setBackground(c5);
        restartButton.setFont(new Font("Arial", Font.PLAIN, 20));
        add(restartButton);
        restartButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetGame();
            }
        });
    }

    public void actionPerformed(ActionEvent e) {
        Button b1 = (Button) e.getSource();
        int index = -1;

        for (int i = 0; i < 9; i++) {
            if (b1 == b[i]) {
                index = i;
                break;
            }
        }

        if (board[index].equals("")) {
            if (c % 2 == 1) {
                b1.setLabel("O");
				Color c1 = new Color(187,119,119);
			    b1.setBackground(c1);
                board[index] = "O";
                statusLabel.setText("Player X's Turn");
				statusLabel.setForeground(c3);
				
            } else {
                b1.setLabel("X");
				Color c2 = new Color(222,188,188);
			    b1.setBackground(c2);
				
                board[index] = "X";
                statusLabel.setText("Player O's Turn");
				statusLabel.setForeground(c3);
            }
            b1.setEnabled(false);
            c++;

            String winner = checkWin();
            if (!winner.equals("")) {
                statusLabel.setText("Winner is: " + winner);
				statusLabel.setForeground(c3);
                disableAllButtons();
            } else if (c > 9) {
                statusLabel.setText("Game Draw!");
            }
        }
    }

    String checkWin() {
        int w[][] = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}
        };

        for (int i = 0; i < 8; i++) {
            int a = w[i][0], b = w[i][1], c = w[i][2];
            if (!board[a].equals("") &&
                board[a].equals(board[b]) &&
                board[b].equals(board[c])) {
                return board[a];
            }
        }
        return "";
    }

    void disableAllButtons() {
        for (int i = 0; i < 9; i++) {
            b[i].setEnabled(false);
        }
    }

    void resetGame() {
        c = 1;
        for (int i = 0; i < 9; i++) {
            board[i] = "";
            b[i].setLabel("");
            b[i].setEnabled(true);
			b[i].setBackground(new Color(238,238,238)); 
           
        }
        statusLabel.setText("Player O's Turn");
    }
}

class TicTacToe2 
{
    public static void main(String args[]) {
        FDemo f1 = new FDemo();
        f1.setVisible(true);
        f1.setSize(500, 500);
        f1.setLocation(100, 100);
    }
}
