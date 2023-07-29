package com.abdulmateen.startertemplate.presentation.screens.main.profile

import android.graphics.Bitmap
import com.abdulmateen.startertemplate.domain.models.User

data class ProfileUIState(
    val user: User? = null,
    val bitmap: Bitmap? = null,
    val bitmapHasError: Boolean = false,
    val bitmapError: String = "",
    val imageUrl: String ="",
    val fullName: String = "",
    val hasFullNameError: Boolean = false,
    val fullNameError: String = "",
    val email: String = "",
    val hasEmailError: Boolean = false,
    val emailError: String = "",
    val phoneNumber: String = "",
    val hasPhoneNumberError: Boolean = false,
    val phoneNumberError: String = "",
    val address: String = "",
    val hasAddressError: Boolean = false,
    val addressError: String = "",
    val postalCode: String = "",
    val postalCodeHasError: Boolean = false,
    val postalCodeError: String = "",
    val city: String = "",
    val cityHasError: Boolean = false,
    val cityError: String = "",
    val password: String = "",
    val hasPasswordError: Boolean = false,
    val passwordError: String = "",
    val confirmPassword: String = "",
    val hasConfirmPasswordError: Boolean = false,
    val confirmPasswordError: String = "",
    val passwordVisibility: Boolean = false,
    val confirmPasswordVisibility: Boolean = false,
    val hasTermsAccepted: Boolean = false,
    val loading: Boolean = false,
)

