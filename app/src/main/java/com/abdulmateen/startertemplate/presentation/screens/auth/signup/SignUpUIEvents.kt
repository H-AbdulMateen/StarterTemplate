package com.abdulmateen.startertemplate.presentation.screens.auth.signup

import android.graphics.Bitmap
import com.abdulmateen.startertemplate.presentation.screens.main.profile.ProfileUIEvents

sealed class SignUpUIEvents{
    data class UpdateBitmap(val bitmap: Bitmap) : SignUpUIEvents()
    data class LoadingStatusChanged(val isLoading: Boolean): SignUpUIEvents()
    data class UpdateFullName(val name: String) : SignUpUIEvents()
    data class UpdateFullNameErrorStatus(val hasError: Boolean, val message: String): SignUpUIEvents()
    data class UpdateEmail(val email: String) : SignUpUIEvents()
    data class UpdateEmailErrorStatus(val hasError: Boolean, val message: String): SignUpUIEvents()
    data class UpdatePhoneNumber(val phoneNumber: String): SignUpUIEvents()
    data class UpdatePhoneNumberErrorStatus(val hasError: Boolean, val message: String): SignUpUIEvents()
    data class UpdateAddress(val address: String): SignUpUIEvents()
    data class UpdateAddressErrorStatus(val hasError: Boolean, val message: String): SignUpUIEvents()
    data class UpdatePassword(val password: String): SignUpUIEvents()
    data class UpdatePasswordErrorStatus(val hasError: Boolean, val message: String): SignUpUIEvents()
    data class UpdateConfirmPassword(val password: String): SignUpUIEvents()
    data class UpdateConfirmPasswordErrorStatus(val hasError: Boolean, val message: String): SignUpUIEvents()
    object UpdateTermsAcceptedStatus: SignUpUIEvents()
    object OnSignUpClick: SignUpUIEvents()
    object UpdatePasswordVisibilityStatus: SignUpUIEvents()
    object UpdateConfirmPasswordVisibilityStatus: SignUpUIEvents()
    data class UpdateDateOfBirth(val selectedDate: String): SignUpUIEvents()

}
