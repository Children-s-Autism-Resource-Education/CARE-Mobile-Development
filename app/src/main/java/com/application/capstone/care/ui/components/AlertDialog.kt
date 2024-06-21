import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AlertDialog
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

object AlertDialogComponent {
    @Composable
    fun showDialog(
        context: Context,
        title: String,
        message: String,
        onPositiveClick: () -> Unit = {},
        positiveButtonText: String = "OK"
    ) {
        if (LocalContext.current is Activity) {
            val activity = LocalContext.current as Activity
            activity.runOnUiThread {
                AlertDialog.Builder(context)
                    .setTitle(title)
                    .setMessage(message)
                    .setPositiveButton(positiveButtonText) { dialog, _ ->
                        onPositiveClick()
                        dialog.dismiss()
                    }
                    .show()
            }
        }
    }
}
