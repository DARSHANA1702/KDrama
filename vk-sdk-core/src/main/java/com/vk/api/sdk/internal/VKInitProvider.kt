package com.vk.api.sdk.internal

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri
import android.util.Log
import com.vk.api.sdk.VK

/**
 * Fake content provider for sdk auto initialization
 */
class VKInitProvider : ContentProvider() {
    override fun onCreate(): Boolean {
        try {
            VK.initialize(context!!)
        } catch (e: Exception) {
            Log.e(VKInitProvider::class.java.simpleName, "Failed to initialize the VK SDK", e)
        }
        return false
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? = null

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? = null

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int = 0

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int = 0

    override fun getType(uri: Uri): String? = null
}