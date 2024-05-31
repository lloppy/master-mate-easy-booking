package com.example.skills.navigation

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

        // in client profile
        object PasswordSettings : Master("password_settings_screen")
        object EditProfile : Master("edit_profile_screen")
        object Notifications : Master("notifications_screen")

        object ViewMaster : Client("view_master")
        object ViewMasterServices : Client("view_master_services")
        object SelectDate : Client("select_date_screen")
        object EditDate : Client("edit_date_screen")
        object SelectTime : Client("select_time_screen")
        object EditTime : Client("edit_time_screen")
        object ConfirmClientBooking : Client("confirm_client_booking_screen")
        object EditConfirmClientBooking : Client("edit_confirm_client_booking_screen")
        object DoneClientBooking : Client("done_client_booking_screen")
        object EditDoneClientBooking : Client("edit_done_client_booking_screen")


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


        // in master profile
        object PasswordSettings : Master("password_settings_screen")
        object EditProfile : Master("edit_profile_screen")
        object ShareProfile : Master("share_profile_screen")
        object Notifications : Master("notifications_screen")
        object CreateCategory : Master("create_service_screen")
        object ChangeCategory: Master("change_category_screen")
        object CreateServiceCard: Master("create_service_card_screen/{selectedCategoryName}")
        object EditServiceCard: Master("edit_service_card_screen/{serviceId}")

    }
}