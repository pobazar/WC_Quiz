package msk.pobazar.wcquiz.legacy;

public interface MainFragmentListener {
    void startGame();
    void startRegame();
    void startRecord();
    void startResult(int right, int all, long time);
    void startMenu();
    void deleteRecord();
}
