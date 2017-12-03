package at.defuex.basickotlindagger2.ui.main

import android.os.Bundle
import at.defuex.basickotlindagger2.R
import at.defuex.basickotlindagger2.ui.base.BaseActivity
import at.defuex.basickotlindagger2.ui.main.view.MainFragment

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            addFragment(R.id.container, MainFragment())
        }
    }
}
