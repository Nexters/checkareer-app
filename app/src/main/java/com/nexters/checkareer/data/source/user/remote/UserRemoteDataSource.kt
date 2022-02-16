package com.nexters.checkareer.data.source.user.remote

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.nexters.checkareer.data.adapter.db.data.UserProfile
import com.nexters.checkareer.data.source.user.UserDataSource
import com.nexters.checkareer.domain.util.Result
import com.nexters.checkareer.domain.vo.Profile
import timber.log.Timber
import javax.inject.Inject

class UserRemoteDataSource @Inject constructor(

) : UserDataSource {

    val db = Firebase.firestore

    override suspend fun findUserProfile(): Result<UserProfile> {
        TODO("Not yet implemented")
    }

    override suspend fun insertUserProfile(profile: Profile): Result<Unit> {

        db.collection("profiles")
            .add(profile)
            .addOnSuccessListener { documentReference ->
                Timber.d("DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Timber.e("Error adding document $e")
            }

        return Result.Success(Unit)
    }

    override suspend fun deleteUserProfile(profile: Profile): Result<Unit> {
        TODO("Not yet implemented")
    }
}