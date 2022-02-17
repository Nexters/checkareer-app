package com.nexters.checkareer.presentation.ui.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.nexters.checkareer.R
import com.nexters.checkareer.databinding.LoginFragBinding
import com.nexters.checkareer.databinding.SettingFragBinding
import com.nexters.checkareer.presentation.ui.login.model.Account
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private val viewModel by viewModels<LoginViewModel>()

    private lateinit var viewDataBinding: LoginFragBinding

    private lateinit var auth: FirebaseAuth
    private lateinit var getResult: ActivityResultLauncher<Intent>

    lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewDataBinding = LoginFragBinding.inflate(inflater, container, false).apply {
            viewmodel = viewModel
        }
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupGoogleLogin()
        setupCloseButton()
        setupGoogleLoginButton()
        setupActivityResultLauncher()
        setupEvents()
    }

    private fun setupEvents() {
        viewModel.isSucceededLogin.observe(this.viewLifecycleOwner, Observer {
            onFinish()
        })
    }

    private fun setupGoogleLogin() {
        activity?.let {
            val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .requestProfile()
                .build()

            googleSignInClient = GoogleSignIn.getClient(it, googleSignInOptions)

            auth = Firebase.auth
        }
    }

    private fun setupCloseButton() {
        viewDataBinding.imageviewClose.setOnClickListener {
            requireActivity().finish()
        }
    }

    private fun setupGoogleLoginButton() {
//        googleSignInClient.signOut()
        viewDataBinding.buttonGoogleLogin.setOnClickListener {
            toast("Login Success")
            signIn()
        }
    }

    private fun toast(msg: String) {
        activity?.run { Toast.makeText(this, msg, Toast.LENGTH_SHORT) }
    }

    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        getResult.launch(signInIntent)
    }

    private fun setupActivityResultLauncher() {
        getResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                try {
                    // Google Sign In was successful, authenticate with Firebase
                    task.getResult(ApiException::class.java)?.let { account ->
                        Timber.i("firebaseAuthWithGoogle: ${account.displayName} / ${account.email} / ${account.photoUrl}")
                        firebaseAuthWithGoogle(account.idToken!!)
                        viewModel.signIn(Account(account.displayName, account.email, account.photoUrl.toString()))
                    }
                } catch (e: ApiException) {
                    // Google Sign In failed, update UI appropriately
                    Log.w("TAG", "Google sign in failed", e)
                }
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Timber.i("signInWithCredential:success")
//                    val user = auth.currentUser
//                    Timber.i("$user")
//                    updateUI()
                } else {
                    // If sign in fails, display a message to the user.
                    Timber.i( "signInWithCredential:failure ${task.exception}")
//                    updateUI()
                }
            }
    }

    private fun onFinish() {
        requireActivity().finish()
    }

    companion object {
        @JvmStatic
        fun newInstance() = LoginFragment()
    }

}