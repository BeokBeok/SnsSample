package com.beok.auth.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class AuthLocalDataSourceImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : AuthLocalDataSource {

    override fun isSignIn(): Flow<Boolean> {
        return dataStore.data
            .map { preferences ->
                preferences[PREFERENCES_KEY_USER_ID] ?: -1 >= 0
            }
    }

    override suspend fun signIn(userID: Int) {
        dataStore.edit { preferences ->
            preferences[PREFERENCES_KEY_USER_ID] = userID
        }
    }

    companion object {
        private val PREFERENCES_KEY_USER_ID = intPreferencesKey("user_id")
    }
}
