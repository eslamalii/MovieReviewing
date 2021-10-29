package com.example.movieapp.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.movieapp.databinding.OverlapLoadingViewLayoutBinding

class OverlapLoadingView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) :
    ConstraintLayout(context, attrs, defStyleAttr) {
    private var binding: OverlapLoadingViewLayoutBinding =
        OverlapLoadingViewLayoutBinding.inflate(LayoutInflater.from(context), this, true)

    fun loadingStateType(stateType: STATETYPE) {
        when (stateType) {
            STATETYPE.LOADING -> {
                binding.loadingView.visibility = View.VISIBLE
                binding.progressBar.visibility = View.VISIBLE
                binding.icIcon.visibility = View.GONE
            }
            STATETYPE.ERROR -> {
                binding.loadingView.visibility = View.VISIBLE
                binding.progressBar.visibility = View.GONE
                binding.icIcon.visibility = View.VISIBLE
            }
            STATETYPE.DONE -> {
                binding.loadingView.visibility = View.GONE
            }
        }
    }

    enum class STATETYPE {
        ERROR, LOADING, DONE
    }
}