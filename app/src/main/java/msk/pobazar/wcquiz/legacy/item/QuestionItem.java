package msk.pobazar.wcquiz.legacy.item;

public class QuestionItem {
    private int id;
    private String question;
    private String[] answer;
    private String answerRight;
    private String urlImage;

    public QuestionItem(int id, String question, String[] answer, String answerRight, String urlImage) {
        this.id=id;
        this.question = question;
        this.answer = answer;
        this.answerRight = answerRight;
        this.urlImage = urlImage;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String[] getAnswer() {
        return answer;
    }

    public void setAnswer(String[] answer) {
        this.answer = answer;
    }

    public String getAnswerRight() {
        return answerRight;
    }

    public void setAnswerRight(String answerRight) {
        this.answerRight = answerRight;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
