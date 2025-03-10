package msk.pobazar.wcquiz.core.base.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import msk.pobazar.wcquiz.core.R
import msk.pobazar.wcquiz.core.navigation.Router
import msk.pobazar.wcquiz.core.navigation.navigators.ActivityNavigator
import msk.pobazar.wcquiz.domain.di.DependenciesInjector
import toothpick.config.Module
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity() {

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var navigator: ActivityNavigator

    open val moduleProvider: (Module) -> Unit = {}

    private val injector = DependenciesInjector()

    private val layoutId: Int = R.layout.activity_base

    override fun onCreate(savedInstanceState: Bundle?) {
        DependenciesInjector().openScope(
            target = this,
            scopes = listOf(this),
            moduleProvider = moduleProvider
        )
        super.onCreate(savedInstanceState)
        setContentView(layoutId)
        initUi()
        initUx()
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigator.onResumeFragments(this, R.id.content)
    }

    override fun onPause() {
        super.onPause()
        navigator.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        injector.closeScope(this)
    }

    open fun initUi() {
        // to be implemented
    }

    open fun initUx() {
        // to be implemented
    }
}
