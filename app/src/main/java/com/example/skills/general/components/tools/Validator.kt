package com.example.skills.general.components.tools

import java.util.regex.Pattern

object Validator {
    fun isNameValid(name: String): Boolean {
        val pattern = Pattern.compile(
            "[А-Яа-я]+"
        )
        return pattern.matcher(name).matches()
    }

    fun isEmailValid(email: String): Boolean {
        val pattern = Pattern.compile(
            "[a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}"
        )
        return pattern.matcher(email).matches()
    }

    fun isPhoneValid(phone: String): Boolean {
        val pattern = Pattern.compile(
            "89[0-9]{9}"
        )
        return pattern.matcher(phone).matches()
    }

    fun isLinkValid(link: String): Boolean {
        val pattern = Pattern.compile(
            ".*\\..*"
        )
        return pattern.matcher(link).matches()
    }

    fun isNewPasswordValid(password: String): Boolean {
        val pattern = Pattern.compile(
            "[a-zA-Z0-9._%-]+"
        )
        return pattern.matcher(password).matches()
    }

    fun isPasswordMatch(password: String, confirmPassword: String): Boolean {
        return password == confirmPassword
    }

    fun isCalendarIDValid(id: String): Boolean {
        val pattern = Pattern.compile(
            "[a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}"
        )
        return pattern.matcher(id).matches()
    }

    fun isCodeValid(code: String): Boolean {
        val pattern = Pattern.compile(
            "[0-9]{4}"
        )
        return pattern.matcher(code).matches()
    }

    fun isBirthDateValid(date: String): Boolean {
        val pattern = Pattern.compile(
            "(0[1-9]|[12][0-9]|3[01])[.](0[1-9]|1[012])[.]\\d{4}"
        )
        return pattern.matcher(date).matches()
    }

    fun isServicePriceValid(price: String): Boolean {
        val pattern = Pattern.compile(
            "\\d+"
        )
        return pattern.matcher(price).matches()
    }

    fun isTimeValid(time: String): Boolean {
        val pattern = Pattern.compile(
            "(0[1-9]|1[0-9]|2[0-3]):(00|30)"
        )
        return pattern.matcher(time).matches()
    }

    fun isServiceDurationValid(duration: String): Boolean {
        val pattern = Pattern.compile(
            "\\d+(0|30)"
        )
        return pattern.matcher(duration).matches()
    }
}
