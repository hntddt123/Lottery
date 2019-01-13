package com.hnt;

import javax.swing.*;
import java.awt.*;
import java.util.regex.Pattern;

public class LotteryUI extends JFrame {

  private JLabel headCountLabel;

  private JButton drawButton;
  private JButton revealButton;

  private JTextField headCountTextField;
  private JTextArea winnerTextArea;

  private JPanel jPanel;
  private JScrollPane jScrollPane;

  private Lottery lottery;
  private Font systemFont;

  public static void main(String[] args) {
    JFrame frame = new LotteryUI();
    frame.setTitle("Lottery");
    frame.setSize(400, 400);
    frame.setLocationRelativeTo(null);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }

  @Override
  protected void frameInit() {
    super.frameInit();

    jPanel = new JPanel();
    jPanel.setLayout(new FlowLayout());
    jPanel.setBackground(new Color(247, 195, 74));

    headCountLabel = new JLabel("參加人數:");
    headCountTextField = new JTextField(5);

    drawButton = new JButton("抽獎");
    revealButton = new JButton("開獎");

    winnerTextArea = new JTextArea(10,20);
    winnerTextArea.setLineWrap(true);
    winnerTextArea.setWrapStyleWord(true);
    winnerTextArea.setEditable(false);
    jScrollPane = new JScrollPane(winnerTextArea);

    systemFont = new Font("Microsoft JhengHei", Font.PLAIN, 20);
    setFont();

    addElementToPanel();
    getContentPane().add(jPanel);

    lottery = new Lottery();

    addDrawActionListener();
    addRevealActionListener();
  }

  private void addDrawActionListener() {
    drawButton.addActionListener(e -> {
      winnerTextArea.setText("");
      if (!headCountTextField.getText().isEmpty()) {
        String valid = headCountTextField.getText();
        if (Pattern.matches("[0-9]+", valid)) {
          lottery.drawPrize(headCountTextField.getText());
          lottery.getNameFromFile();
        }
      }
    });
  }

  private void addRevealActionListener() {
    revealButton.addActionListener(e -> {
      winnerTextArea.append(lottery.revealPrize());
    });
  }


  private void addElementToPanel() {
    jPanel.add(headCountLabel);
    jPanel.add(headCountTextField);
    jPanel.add(drawButton);
    jPanel.add(revealButton);
    jPanel.add(jScrollPane);
  }

  private void setFont() {
    headCountLabel.setFont(systemFont);
    headCountTextField.setFont(systemFont);
    drawButton.setFont(systemFont);
    revealButton.setFont(systemFont);
    winnerTextArea.setFont(systemFont);
  }

}
