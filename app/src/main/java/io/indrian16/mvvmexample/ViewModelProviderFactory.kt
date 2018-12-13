package io.indrian16.mvvmexample

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

class ViewModelProviderFactory(private val viewModels: ViewModel): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(viewModels.javaClass)) {

            @Suppress("UNCHECKED_CAST")
            return viewModels as T
        }

        throw IllegalArgumentException("Unknown class name")
    }
}