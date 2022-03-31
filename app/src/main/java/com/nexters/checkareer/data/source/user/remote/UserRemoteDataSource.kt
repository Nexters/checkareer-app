package com.nexters.checkareer.data.source.user.remote

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.nexters.checkareer.data.adapter.db.data.UserProfile
import com.nexters.checkareer.data.source.user.UserDataSource
import com.nexters.checkareer.data.source.user.remote.dto.FbProfileDto
import com.nexters.checkareer.domain.error.UserNotFoundError
import com.nexters.checkareer.domain.user.User
import com.nexters.checkareer.domain.util.Result
import com.nexters.checkareer.domain.vo.Profile
import timber.log.Timber
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class UserRemoteDataSource @Inject constructor(
) : UserDataSource {

    val db = Firebase.firestore

// TODO 가입화면 추가, 간편가입 후 프로필 생성 화면 진입
    override suspend fun findUserProfileByEmail(email: String): Result<Profile> = suspendCoroutine { cont ->
        try {
            Timber.i("findUserProfileByEmail : $email")

            val profileRef = db.collection("profiles").document("email")
            profileRef
//                .whereEqualTo("user.email", email)
                .get()
                .addOnSuccessListener { document ->
                    Timber.i("document : $document")
                    if (document != null) {
                        Timber.d("DocumentSnapshot data: ${document.data}")
                        document.toObject(FbProfileDto::class.java)?.let { result ->
                            Timber.d("result: $result")
                            cont.resume(Result.Success(result.toDomain()))
                        }
                    } else {
                        Timber.d( "No such document")
                        cont.resume(Result.Error(UserNotFoundError()))
                    }

                }
                .addOnFailureListener {
                    Timber.w("Error getting documents: $it")
                    cont.resume(Result.Error(UserNotFoundError()))
                }
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun insertUserProfile(profile: Profile): Result<Unit> {
//        profile.user.logInInfo?.email?.let { email ->
            db.collection("profiles")
                .document("email")
            .set(profile.toFbProfile())
            .addOnSuccessListener { documentReference ->
                Timber.d("successfully written!")
            }
            .addOnFailureListener { e ->
                Timber.e("Error writing document $e")
            }
//        }
        return Result.Success(Unit)
    }

    override suspend fun updateUser(profile: Profile): Result<Unit> {

        val querySnapshot = db.collection("profiles")
            .whereEqualTo("user.id", profile.user.id)
            .get()

        Timber.i("updateUser $querySnapshot")
        Timber.i("updateUser result ${querySnapshot.result}")

        val resultDoc = querySnapshot.result.documents
        if (resultDoc.isNotEmpty()) {
            resultDoc.first().reference.update("user", profile.user)
        }
        return Result.Success(Unit)
    }

    override suspend fun findUserProfile(): Result<UserProfile> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteUserProfile(profile: Profile): Result<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun findUser(): Result<User> {
        TODO("Not yet implemented")
    }

    override suspend fun logout(user: User): Result<Unit> {
        TODO("Not yet implemented")
    }
}