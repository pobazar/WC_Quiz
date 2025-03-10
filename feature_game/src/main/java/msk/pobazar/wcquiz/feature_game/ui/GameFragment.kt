package msk.pobazar.wcquiz.feature_game.ui

import android.graphics.PorterDuff
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_game.*
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import msk.pobazar.wcquiz.core.base.ui.BaseFragment
import msk.pobazar.wcquiz.core.delegates.FragmentArgument
import msk.pobazar.wcquiz.core.extensions.setOnClick
import msk.pobazar.wcquiz.core.extensions.visible
import msk.pobazar.wcquiz.core.navigation.transitionsParams.GameParams
import msk.pobazar.wcquiz.feature_game.R
import msk.pobazar.wcquiz.feature_game.presenter.GamePresenter
import msk.pobazar.wcquiz.feature_game.presenter.GameView
import msk.pobazar.wcquiz.view_error.ErrorType
import toothpick.config.Module
import javax.inject.Inject


class GameFragment : BaseFragment(), GameView {

    override val layout: Int = R.layout.fragment_game

    override val moduleProvider: (Module) -> Unit
        get() = {
            it.bind(GameParams::class.java).toInstance(params)
        }

    override var params: GameParams by FragmentArgument()

    @Inject
    @InjectPresenter
    @get:ProvidePresenter
    lateinit var presenter: GamePresenter

    override fun initUi() {
        pbGameTimer.max = resources.getInteger(R.integer.time_to_answer)
        pbGameTimer.progress = pbGameTimer.max
    }

    override fun initUx() {
        btnGameAnswer1.setOnClick { presenter.onAnswerClick(btnGameAnswer1.text.toString()) }
        btnGameAnswer2.setOnClick { presenter.onAnswerClick(btnGameAnswer2.text.toString()) }
        btnGameAnswer3.setOnClick { presenter.onAnswerClick(btnGameAnswer3.text.toString()) }
        btnGameAnswer4.setOnClick { presenter.onAnswerClick(btnGameAnswer4.text.toString()) }
        errorGame.onActionClick { presenter.onRetryClick() }
    }

    override fun setCountQuestion(count: String) {
        tvGameCountQuestion.text = count
    }

    override fun setQuestion(question: String) {
        tvGameQuestion.text = question
    }

    override fun setAnswers(answers: List<String>) {
        btnGameAnswer1.text = answers[0]
        btnGameAnswer2.text = answers[1]
        btnGameAnswer3.text = answers[2]
        btnGameAnswer4.text = answers[3]
    }

    override fun setImage(image: String) {
        Glide.with(this)
            .load(image)
            .into(ivGameImageQuestion)
    }

    override fun setTimer(progress: Int, color: Int) {
        pbGameTimer.progress = progress
        pbGameTimer.progressDrawable = pbGameTimer.progressDrawable.mutate().apply {
            setColorFilter(color, PorterDuff.Mode.SRC_IN)
        }
    }

    override fun showError(type: ErrorType) {
        errorGame.type = type
        errorGame.visible(type != ErrorType.NONE)
    }

    override fun showProgress(isShow: Boolean) {
        loaderGame.visible(isShow)
    }

    companion object {
        fun newInstance(params: GameParams): GameFragment =
            GameFragment().apply {
                this.params = params
            }
    }
}