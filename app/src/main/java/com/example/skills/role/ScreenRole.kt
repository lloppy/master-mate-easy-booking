package com.example.skills.role

sealed class ScreenRole(val route: String) {
    object RoleLayout : ScreenRole(route = "role_layout_screen")

    object ClientLogIn : ScreenRole(route = "client_log_in_screen")
    object ClientRegistration : ScreenRole(route = "client_registration_screen")
    object ClientMainLayout : ScreenRole(route = "client_main_layout_screen")


    object MasterLogIn : ScreenRole(route = "master_log_in_screen")
    object MasterRegistration : ScreenRole(route = "master_registration_screen")
    object MasterMainLayout : ScreenRole(route = "master_main_layout_screen")

    object DoneMasterRegistration : ScreenRole(route = "done_master_registration_screen")
    object DoneClientRegistration : ScreenRole(route = "done_client_registration_screen")

    object ForgotPasswordClient : ScreenRole(route = "client_forgot_password_screen")
    object ForgotPasswordMaster: ScreenRole(route = "master_forgot_password_screen")
    object CodeVerificationClient : ScreenRole(route = "client_code_verification_screen")
    object CodeVerificationMaster : ScreenRole(route = "master_code_verification_screen")

    object CodeVerificationNewPasswordClient : ScreenRole(route = "client_code_verification_password_screen")
    object CodeVerificationNewPasswordMaster : ScreenRole(route = "master_code_verification_password_screen")
    object NewPasswordClient : ScreenRole(route = "new_password_client_screen")
    object NewPasswordMaster : ScreenRole(route = "new_password_master_screen")

}