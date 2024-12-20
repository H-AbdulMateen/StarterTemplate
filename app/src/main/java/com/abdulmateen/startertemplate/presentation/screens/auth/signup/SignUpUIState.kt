package com.abdulmateen.startertemplate.presentation.screens.auth.signup

import android.graphics.Bitmap

data class SignUpUIState(
    val bitmap: Bitmap? = null,
    val bitmapHasError: Boolean = false,
    val bitmapError: String = "",
    val fullName: String = "",
    val hasFullNameError: Boolean = false,
    val fullNameError: String = "",
    val email: String = "",
    val hasEmailError: Boolean = false,
    val emailError: String = "",
    val phoneNumber: String = "",
    val hasPhoneNumberError: Boolean = false,
    val phoneNumberError: String = "",
    val dateOfBirth: String = "",
    val hasDateOfBirthError: Boolean = false,
    val dateOfBirthError: String = "",
    val address: String = "",
    val hasAddressError: Boolean = false,
    val addressError: String = "",
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

