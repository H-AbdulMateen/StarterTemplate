package com.abdulmateen.startertemplate.presentation.screens.main.profile

import android.graphics.Bitmap
import com.abdulmateen.startertemplate.domain.models.User

sealed class ProfileUIEvents {
    data class UpdateUser(val user: User) : ProfileUIEvents()
    data class UpdateImageData(val imageData: String) : ProfileUIEvents()
    data class UpdateBitmap(val bitmap: Bitmap) : ProfileUIEvents()
    data class UpdateBitmapError(val error: Boolean, val message: String) : ProfileUIEvents()
    data class LoadingStatusChanged(val isLoading: Boolean) : ProfileUIEvents()
    data class UpdateFullName(val name: String) : ProfileUIEvents()
    data class UpdateFullNameErrorStatus(val hasError: Boolean, val message: String) : ProfileUIEvents()

    data class UpdateEmail(val email: String) : ProfileUIEvents()
    data class UpdateEmailErrorStatus(val hasError: Boolean, val message: String) : ProfileUIEvents()

    data class UpdatePhoneNumber(val phoneNumber: String) : ProfileUIEvents()
    data class UpdatePhoneNumberErrorStatus(val hasError: Boolean, val message: String) : ProfileUIEvents()

    data class UpdateAddress(val address: String) : ProfileUIEvents()
    data class UpdateAddressErrorStatus(val hasError: Boolean, val message: String) : ProfileUIEvents()
    data class UpdatePassword(val password: String) : ProfileUIEvents()
    data class UpdatePasswordErrorStatus(val hasError: Boolean, val message: String) : ProfileUIEvents()
    data class UpdateConfirmPassword(val password: String) : ProfileUIEvents()
    data class UpdateConfirmPasswordErrorStatus(val hasError: Boolean, val message: String) : ProfileUIEvents()
    object UpdateTermsAcceptedStatus : ProfileUIEvents()
    object OnSignUpClick : ProfileUIEvents()
    object UpdatePasswordVisibilityStatus : ProfileUIEvents()
    object UpdateConfirmPasswordVisibilityStatus : ProfileUIEvents()
    data class UpdatePostalCode(val postalCode: String): ProfileUIEvents()
    data class UpdatePostalCodeError(val hasError: Boolean, val message: String): ProfileUIEvents()
    data class UpdateCity(val city: String): ProfileUIEvents()
    data class UpdateCityError(val hasError: Boolean, val message: String): ProfileUIEvents()

}
