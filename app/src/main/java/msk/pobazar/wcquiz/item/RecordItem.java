package msk.pobazar.wcquiz.item;

import java.util.Date;

public class RecordItem {

    private int id;
    private int score;
    private Date date;

    public RecordItem(int id, int score, Date date) {
        this.id = id;
        this.score = score;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


}
