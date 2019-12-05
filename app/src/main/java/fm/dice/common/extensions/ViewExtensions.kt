package fm.dice.common.extensions

import android.view.View

fun View.visibleIfTrueGoneIfFalse(evaluation: Boolean) {
    visibility = if (evaluation) {
        View.VISIBLE
    } else {
        View.GONE
    }
}