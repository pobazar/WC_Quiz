package msk.pobazar.wcquiz;

public interface MainFragmentListener {
    void startGame();
    void startRegame();
    void startRecord();
    void startResult(int right, int all, long time);
    void startMenu();
    void deleteRecord();
}
