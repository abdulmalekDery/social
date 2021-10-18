package com.tessafold.social.utils

sealed class BaseCommand {
    class Error(val errorString: String): BaseCommand()

    class Success(val toastMessage: String?): BaseCommand()

    class SignUp(): BaseCommand()

    class SignIn(): BaseCommand()
}