package fm.dice.common.view

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import fm.dice.R
import fm.dice.data.common.mapper.DomainErrorMapper
import fm.dice.domain.common.DomainError
import javax.inject.Inject

class SnackbarErrorVisualiser @Inject constructor(private val domainErrorMapper: DomainErrorMapper) {

    private var isSnackbarShown: Boolean = false

    fun show(view: View, throwable: Throwable) {
        val domainError = domainErrorMapper.mapFrom(throwable)
        createMessage(view.context, domainError).also { message: String ->
            val snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG)
            val textView = snackbar.view.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
            textView.setTextColor(ContextCompat.getColor(view.context, R.color.white))

            snackbar.addCallback(object : BaseTransientBottomBar.BaseCallback<Snackbar>() {
                override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
                    super.onDismissed(transientBottomBar, event)
                    isSnackbarShown = false
                }

                override fun onShown(transientBottomBar: Snackbar?) {
                    super.onShown(transientBottomBar)
                    isSnackbarShown = true
                }
            })

            if (!isSnackbarShown) {
                snackbar.show()
                isSnackbarShown = true
            }
        }
    }

    private fun createMessage(context: Context, error: DomainError) = when (error) {
        is DomainError.Offline -> context.getString(R.string.offline)
        is DomainError.Timeout -> context.getString(R.string.timeout)
        is DomainError.Unauthorized -> context.getString(R.string.unauthorised)
        is DomainError.Forbidden -> context.getString(R.string.forbidden)
        is DomainError.Generic.Http -> context.getString(R.string.generic_error_http, error.code, error.message)
        is DomainError.Generic.Unknown -> context.getString(R.string.generic_error, error.message)
    }
}