
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.application.capstone.care.R
import com.application.capstone.care.ui.components.ButtonItem
import com.application.capstone.care.ui.components.ConfirmPasswordTextField
import com.application.capstone.care.ui.components.EmailTextField
import com.application.capstone.care.ui.components.PasswordTextField
import com.application.capstone.care.ui.screens.password.ForgotPasswordViewModel
import com.application.capstone.care.ui.theme.Dimensions.HeadlineStyle
import com.application.capstone.care.ui.theme.Dimensions.contentFontSize
import com.application.capstone.care.ui.theme.White
import com.application.capstone.care.ui.theme.WhiteSmoke

@Composable
fun ForgotPasswordScreen(
    navController: NavHostController,
    viewModel: ForgotPasswordViewModel = viewModel(),
    onClicked: () -> Unit
) {
    val email by viewModel.email
    val password by viewModel.password
    val confirmPassword by viewModel.confirmPassword

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(White),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = stringResource(R.string.forgot_password),
                style = HeadlineStyle,
            )

            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = "Set New Password.",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(top = 8.dp)
            )
            Text(
                text = "Create your new password",
                fontSize = contentFontSize,
                color = WhiteSmoke,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(bottom = 8.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))
            EmailTextField(
                email = email,
                onEmailChange = viewModel::onEmailChange
            )

            Spacer(modifier = Modifier.height(16.dp))
            PasswordTextField(
                password = password,
                onPasswordChange = viewModel::onPasswordChange
            )

            Spacer(modifier = Modifier.height(16.dp))
            ConfirmPasswordTextField(
                confirmPassword = confirmPassword,
                onPasswordChange = viewModel::onConfirmPasswordChange
            )

            Spacer(modifier = Modifier.height(32.dp))
            ForgotPasswordButton(onClick = onClicked)
        }
    }
}

@Composable
fun ForgotPasswordButton(onClick: () -> Unit) {
    ButtonItem(
        label = stringResource(R.string.sign_up),
        onClick = onClick
    )
}

@Preview(showBackground = true)
@Composable
fun ForgotPasswordScreenPreview() {
    ForgotPasswordScreen(onClicked = {}, navController = rememberNavController())
}
