package presentation.views;

import models.Score;
import presentation.PresentationController;
import presentation.controllers.*;
import presentation.custom.ButtonColorsFirst;
import presentation.custom.ButtonColorsSecond;
import presentation.custom.JMainButton;
import presentation.custom.JSquareLabel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * RankingView represents the GUI window for displaying the game ranking.
 */
public class RankingView extends MainView{
    private final PresentationController pController;
    private final RankingController controller;

    private DefaultListModel<String> top3Model;
    private DefaultListModel<String> ranksModel;

    List<Score> ranking;

    /**
     * Constructor for RankingView.
     * @param controller Presentation controller
     */
    public RankingView(PresentationController controller) {
        super();
        this.pController = controller;
        this.controller = new RankingController(pController,this);
        top3Model = new DefaultListModel<>();
        ranksModel = new DefaultListModel<>();
        start();
    }

    /**
     * Initializes the ranking view.
     */
    public void start() {
        populateRanking();
        addEmptyRow(0);
        addSecondRow();
        addThirdRow();
//        addEmptyRow(5);
        //this.revalidate();

    }

    /**
     * Adds an empty row to the layout.
     * @param y The vertical position of the row
     */
    private void addEmptyRow(int y) {
        c.gridy = y;
        c.gridheight = 1;
        for (int x = 0; x < 4; x++) {
            c.gridx = x;
            makeSquare("");
        }
    }

    /**
     * Adds the second row of components.
     */
    private void addSecondRow() {
        c.gridy = 1;
        c.gridx = 0;
        makeBackButton(controller, RankingController.BACK_AC);

        c.gridx = 1;
        c.gridheight = 2;
        makeSquare("<html><p>RANKING</p></html>");

        c.gridx = 2;
        c.gridheight = 2;
//        JList<String> top3List = new JList<>(top3Model);
//        top3List.setCellRenderer(new RankingCellRenderer());
        JPanel podium = makePodium("Oriol", "Dani", "Brian");
        gridbag.setConstraints(podium, c);
        add(podium, c);

        c.gridx = 3;
        c.gridheight = 1;
        makeSquare("");


        c.gridy = 2;
        c.gridx = 0;
        c.gridheight = 1;
        makeSquare("");
        c.gridx = 3;
        makeSquare("");
    }

    /**
     * Adds the third row of components.
     */
    private void addThirdRow() {
        c.gridy = 3;
        c.gridheight = 1;

        c.gridx = 0;
        makeSquare("");

        c.gridx = 1;
        c.gridheight = 3;
        c.gridwidth= 2;
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // Set the layout to BoxLayout Y_AXIS
        JScrollPane panelPane = new JScrollPane(panel);
        panelPane.setBorder(null);

        for (int i = 0; i < ranking.size(); i++) {
            Score score = ranking.get(i);
            panel.add(makeLabelName(i + 1, score.getUser(), score.getScore()));
        }
//        JList<String> ranksList = new JList<>(ranksModel);
//        ranksList.setCellRenderer(new RankingCellRenderer());
        gridbag.setConstraints(panelPane, c);
        add(panelPane, c);

        c.gridx = 3;
        c.gridheight = 1;
        c.gridwidth= 1;
        makeSquare("");

        c.gridy = 4;
        c.gridx = 0;
        makeSquare("");
        c.gridx = 3;
        makeSquare("");

        c.gridy = 5;
        c.gridx = 0;
        makeSquare("");
        c.gridx = 3;
        makeSquare("");

    }

    /**
     * Populates the ranking list with scores.
     */
    public void populateRanking() {
        this.ranking = pController.getRanking();
        top3Model.clear();
        ranksModel.clear();
        int count = 0;
        for (Score score : ranking) {
            String item = formatItem(score.getUser(), score.getScore());
            if (count < 3) {
                top3Model.addElement(item);
            } else {
                ranksModel.addElement(item);
            }
            count++;
        }
    }

    /**
     * Formats the name and score for display in the list.
     */
    private String formatItem(String name, int score) {
        return name + " . " + score; // This will be split dynamically by the renderer
    }

    /**
     * Custom cell renderer to align name and score.
     */
    private static class RankingCellRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            String[] parts = value.toString().split(" . ");
            if (parts.length == 2) {
                String name = parts[0];
                String score = parts[1];

                FontMetrics fm = label.getFontMetrics(label.getFont());
                int listWidth = list.getWidth() - 2 * list.getInsets().left - 2 * list.getInsets().right;
                int nameWidth = fm.stringWidth(name);
                int scoreWidth = fm.stringWidth(score);

                int spacesWidth = listWidth - nameWidth - scoreWidth - 50;
                int spaceCount = spacesWidth / fm.charWidth('_');

                String spaces = new String(new char[Math.max(0, spaceCount)]).replace('\0', '.');
                label.setText(name + spaces + score);
                label.setFont(new Font("Monospaced", Font.PLAIN, 12)); // Use monospaced font for alignment

            }
            return label;
        }
    }

    private JPanel makeLabelName(int index, String name, int score)
    {
        JPanel rowPanel = new JPanel();
        rowPanel.setLayout(new BoxLayout(rowPanel, BoxLayout.X_AXIS));

        JLabel labelRank = new JLabel("#" + index);
        JLabel labelName = new JLabel(name.toUpperCase());
        JLabel labelScore = new JLabel(String.valueOf(score));
        labelRank.setFont(new Font("SansSerif", Font.BOLD, 18));
        labelName.setFont(new Font("SansSerif", Font.BOLD, 18));
        labelScore.setFont(new Font("SansSerif", Font.BOLD, 18));

        labelRank.setForeground(Color.decode("#375281"));
        labelName.setForeground(Color.decode("#375281"));
        labelScore.setForeground(Color.decode("#375281"));

        JPanel nameAndScorePanel = new JPanel();
        nameAndScorePanel.setLayout(new BoxLayout(nameAndScorePanel, BoxLayout.X_AXIS));

        nameAndScorePanel.add(labelName);
        nameAndScorePanel.add(Box.createVerticalStrut(0));
        nameAndScorePanel.add(labelScore);
        nameAndScorePanel.setPreferredSize(new Dimension(250, labelName.getPreferredSize().height));
        nameAndScorePanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));


        Border border = BorderFactory.createLineBorder(Color.decode("#375281"), 6);
        labelRank.setBorder(border);
        labelRank.setPreferredSize(new Dimension(50, labelName.getPreferredSize().height));
        labelRank.setHorizontalAlignment(SwingConstants.CENTER);

        rowPanel.add(labelRank);
        rowPanel.add(Box.createVerticalStrut(0));
        rowPanel.add(nameAndScorePanel);
        rowPanel.add(Box.createVerticalStrut(0));
        rowPanel.setPreferredSize(new Dimension(300, 150));
        rowPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));



        rowPanel.setBackground(Color.decode("#FAFAFA"));
        rowPanel.setForeground(Color.decode("#375281"));
        nameAndScorePanel.setBackground(Color.decode("#E2E8F3"));
        return rowPanel;
    }

    private JPanel makePodium(String name1, String name2, String name3)
    {
        JPanel podiumPanel = new JPanel();
        GridLayout grid = new GridLayout(3, 1, 0, 15);
        podiumPanel.setLayout(grid);
        podiumPanel.add(podiumLabel(name1));
        podiumPanel.add(podiumLabel(name2));
        podiumPanel.add(podiumLabel(name3));

        podiumPanel.setPreferredSize(new Dimension(100, 30));
        podiumPanel.setMaximumSize(new Dimension(100, 30));
        podiumPanel.setMinimumSize(new Dimension(100, 30));
        return podiumPanel;
    }

    private JLabel podiumLabel(String name)
    {
        JLabel label = new JLabel();
        label.setFont(new Font("SansSerif", Font.BOLD, 25));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setText(name.toUpperCase());
        label.setPreferredSize(new Dimension(100, 30));
        label.setBackground(Color.decode("#775AD8"));
        label.setForeground(Color.decode("#FAFAFA"));
        label.setOpaque(true);
        return label;
    }
}
