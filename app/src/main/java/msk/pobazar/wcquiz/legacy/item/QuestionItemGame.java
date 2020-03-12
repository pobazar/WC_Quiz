package msk.pobazar.wcquiz.legacy.item;

public class QuestionItemGame extends QuestionItem {
    private boolean right;

    public QuestionItemGame(int id, String question, String[] answer, String answerRight, String urlImage, boolean right) {
        super(id, question, answer, answerRight, urlImage);
        this.right = right;
    }

    public QuestionItemGame(int id, String question, String[] answer, String answerRight, String urlImage) {
        super(id, question, answer, answerRight, urlImage);
    }


    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }
}
