package msk.pobazar.wcquiz.feature_result.presenter

import msk.pobazar.wcquiz.core.base.BasePresenter
import msk.pobazar.wcquiz.domain.interactor.ResultInteractor
import msk.pobazar.wcquiz.feature_result.mapper.ResultMapper
import msk.pobazar.wcquiz.feature_result.viewData.ResultViewData
import javax.inject.Inject

class ResultPresenter @Inject constructor(
    private val resultInteractor: ResultInteractor,
    private val resultMapper: ResultMapper
) : BasePresenter<ResultView>() {
    
    private lateinit var resultViewData: ResultViewData
    override fun attachView(view: ResultView?) {
        super.attachView(view)
        
        resultViewData = resultMapper.mapToResultViewData(
            resultInteractor.getResult()
        )
        
        viewState.setResults(resultViewData.items)
        viewState.setCountRight(resultViewData.title)
    }
    
    fun onAgainPlayClick() {
    
    }
    
    fun onShowAnswers() {
    
    }
}