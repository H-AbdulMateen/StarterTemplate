package com.abdulmateen.startertemplate.common.utils
import android.util.Patterns

object Validator {
    fun validateIsFieldEmpty(text: String): ValidationResult{
        return if (text.isEmpty()){
            ValidationResult(status = true, errorMessage = "Please Fill Field")
        }else{
            ValidationResult(status = false)
        }
    }

    fun validatePassword(text: String): ValidationResult{
        return if (text.isEmpty()){
            ValidationResult(status = true, errorMessage = "Please Fill Field")
        }else{
            ValidationResult(status = false)
        }
    }

    fun validateConfirmPassword(password: String, confirmPassword: String): ValidationResult{
        return if (password.isEmpty()){
            ValidationResult(status = true, errorMessage = "Please Fill Field")
        }else if (password != confirmPassword){
            ValidationResult(status = true, errorMessage = "Password did not match")
        }else{
            ValidationResult(status = false)
        }
    }
    fun validateAmountField(amount: Double): ValidationResult{
        return if (amount.toString().isEmpty()){
            ValidationResult(status = true, errorMessage = "Please Fill Field")
        }else if (amount < 5){
            ValidationResult(status = true, errorMessage = "Amount should be equal or greater than 5")
        }else{
            ValidationResult(status = false)
        }
    }
    fun validateEmail(email: String): ValidationResult{
        return if (email.isEmpty()){
            ValidationResult(status = true, errorMessage = "Please Fill Field")
        }else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            ValidationResult(status = true, errorMessage = "Please Enter valid Email")
        }else{
            ValidationResult(status = false)
        }
    }
}

data class ValidationResult(
    val status: Boolean = false,
    val errorMessage: String = ""
)