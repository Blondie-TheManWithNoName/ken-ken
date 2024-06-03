package presentation.views;

import models.Score;
import presentation.PresentationController;
import presentation.controllers.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class RankingView extends MainView{
    private final PresentationController pController;
    private final RankingController controller;

    private DefaultListModel<String> top3Model;
    private DefaultListModel<String> ranksModel;

    List<Score> ranking;


    public RankingView(PresentationController controller) {
        super();
        this.pController = controller;
        this.controller = new RankingController(pController,this);
        top3Model = new DefaultListModel<>();
        ranksModel = new DefaultListModel<>();
        start();
    }

    public void start() {
        populateRanking();
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
        JList<String> top3List = new JList<>(top3Model);
        gridbag.setConstraints(top3List, c);
        add(top3List, c);

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
        JList<String> ranksList = new JList<>(ranksModel);
        gridbag.setConstraints(ranksList, c);
        add(ranksList, c);

        c.gridx = 3;
        c.gridheight = 1;
        makeSquare("");

        c.gridy = 4;
        makeSquare("");
    }

    public void populateRanking() {
        this.ranking = pController.getRanking();
        top3Model.clear();
        ranksModel.clear();
        int count = 0;
        for (Score score : ranking) {
            if (count < 3) {
                top3Model.addElement(score.getUser() + "....." + score.getScore());
            } else {
                ranksModel.addElement(score.getUser() + "....." + score.getScore());
            }
            count++;
        }
    }
}
