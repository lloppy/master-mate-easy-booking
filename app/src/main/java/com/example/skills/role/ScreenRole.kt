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
        object VerificationEmailForNewPassword : Client("verification_new_password_screen")
        object CreateNewPassword : Client("new_password_screen")

    }

    sealed class Master(val masterRoute: String) : ScreenRole("master/$masterRoute") {
        object LogIn : Master("log_in_screen")
        object Registration : Master("registration_screen")
        object MainLayout : Master("main_layout_screen")
        object DoneRegistration : Master("done_registration_screen")
        object ForgotPassword : Master("forgot_password_screen")
        object CodeVerification : Master("code_verification_registration_screen")
        object VerificationEmailForNewPassword : Master("verification_new_password_screen")
        object CreateNewPassword : Master("new_password_screen")
        object FullProfile : Master("full_profile_screen")
        object DoneMasterInfoRegistration : Master("done_master_info_registration_screen")



    }
}