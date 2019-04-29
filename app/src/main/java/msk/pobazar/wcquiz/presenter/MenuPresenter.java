package msk.pobazar.wcquiz.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import msk.pobazar.wcquiz.view.MenuView;


@InjectViewState
public class MenuPresenter extends MvpPresenter<MenuView> {
    private MenuView view;

    @ProvidePresenter
    MenuPresenter newInstance() {
        return new MenuPresenter();
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
    }

    @Override
    public void attachView(MenuView view) {
        super.attachView(view);
        this.view = view;
    }
}
