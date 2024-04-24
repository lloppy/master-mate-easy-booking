package com.example.skills.role

sealed class ScreenRole(val route: String) {
    object RoleLayout : ScreenRole("role_layout_screen")

    sealed class Client(val clientRoute: String) : ScreenRole("client/$clientRoute") {
        object LogIn : Client("log_in_screen")
        object Registration : Client("registration_screen")
        object MainLayout : Client("main_layout_screen")
        object DoneRegistration : Client("done_registration_screen")
        object ForgotPassword : Client("forgot_password_screen")
        object CodeVerification : Client("code_verification_registration_screen")
        object CodeVerificationNewPassword : Client("code_verification_new_password_screen")
        object NewPassword : Client("new_password_screen")

    }

    sealed class Master(val masterRoute: String) : ScreenRole("master/$masterRoute") {
        object LogIn : Master("log_in_screen")
        object Registration : Master("registration_screen")
        object MainLayout : Master("main_layout_screen")
        object DoneRegistration : Master("done_registration_screen")
        object ForgotPassword : Master("forgot_password_screen")
        object CodeVerification : Master("code_verification_registration_screen")
        object CodeVerificationNewPassword : Master("code_verification_new_password_screen")
        object NewPassword : Master("new_password_screen")

    }
}