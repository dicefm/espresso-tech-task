package fm.dice

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import fm.dice.di.DaggerMainComponent
import fm.dice.di.MainComponent
import fm.dice.tags.detail.di.TagDetailComponent
import fm.dice.tags.di.TagsComponent
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), TagsComponent.FactoryProvider, TagDetailComponent.FactoryProvider {

    private val navController by lazy { findNavController(container.id) }
    private val component: MainComponent = DaggerMainComponent.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupActionBarWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }

    override fun provideTagsComponentFactory(): TagsComponent.Factory {
        return component.provideTagsComponentFactory()
    }

    override fun provideTagDetailComponentFactory(): TagDetailComponent.Factory {
        return component.provideTagDetailComponentFactory()
    }
}