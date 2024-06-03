package presentation.views;

import presentation.PresentationController;
import presentation.controllers.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class RankingView extends MainView{
    private final PresentationController pController;
    private final RankingController controller;

    private ArrayList<String> top3 = new ArrayList<>(List.of(new String[]{"1", "2", "3"}));
    private ArrayList<String> ranks = new ArrayList<>(List.of(new String[]{"random4", "random2", "random3","random4", "random2", "random3"}));;


    public RankingView(PresentationController controller) {
        super();
        this.pController = controller;
        this.controller = new RankingController(pController,this);
        start();
    }

    public void start() {
        addEmptyRow(0);
        addSecondRow();
        addThirdRow();
        addEmptyRow(5);

        //this.revalidate();

    }


    private void addEmptyRow(int y) {
        c.gridy = y;
        for (int x = 0; x < 4; x++) {
            c.gridx = x;
            makeSquare("");
        }
    }

    private void addSecondRow() {
        c.gridy = 1;

        c.gridx = 0;
        makeBackButton(controller, RankingController.BACK_AC);

        c.gridx = 1;
        c.gridheight = 2;
        makeSquare("<html><p>RANKING</p></html>");

        c.gridx = 2;
        DefaultListModel<String> options = new DefaultListModel<>();
        options.addAll(top3);
        JList<String> ranksList = new JList<>(options);
        gridbag.setConstraints(ranksList, c);
        add(ranksList, c);

        c.gridx = 3;
        c.gridheight = 2;
        makeSquare("");

        c.gridy = 2;

        c.gridx = 0;
        c.gridheight = 1;
        makeSquare("");
    }

    private void addThirdRow() {
        c.gridy = 3;

        c.gridx = 0;
        c.gridheight = 2;
        makeSquare("");

        c.gridx = 1;
        DefaultListModel<String> options2 = new DefaultListModel<>();
        options2.addAll(ranks);
        JList<String> ranksList = new JList<>(options2);
        gridbag.setConstraints(ranksList, c);
        add(ranksList, c);

        c.gridx = 3;
        c.gridheight = 1;
        makeSquare("");

        c.gridy = 4;
        makeSquare("");
    }
}
