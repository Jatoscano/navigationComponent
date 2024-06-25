package com.torres.myapplication.ui.modals

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.torres.myapplication.R
import com.torres.myapplication.data.network.entities.oneNews.OneNewsDataClass
import com.torres.myapplication.databinding.ModalBottomSheetBinding
import com.torres.myapplication.logic.usercases.GetOneNewsUserCase
import com.torres.myapplication.ui.entities.NewsDataUI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ModalBottomSheet(val news: NewsDataUI?) : BottomSheetDialogFragment() {

    private lateinit var binding: ModalBottomSheetBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewRoot = inflater.inflate(R.layout.modal_bottom_sheet, container, false)
        binding = ModalBottomSheetBinding.bind(viewRoot)
        BottomSheetBehavior.from(binding.standardBottomSheet).apply {
            state = BottomSheetBehavior.STATE_EXPANDED
            peekHeight = 0
        }
        return binding.root
    }


    override fun onStart() {
        super.onStart()
        lifecycleScope.launch(Dispatchers.Main) {
            binding.pgbarLoadData.visibility = View.VISIBLE
            val item = withContext(Dispatchers.IO) {
                loadData(news!!.id)
            }
            binding.txtModalDesc.text = item?.description
            binding.pgbarLoadData.visibility = View.INVISIBLE
        }

    }

    private suspend fun loadData(itemId: String): OneNewsDataClass? {
        var item: OneNewsDataClass? = null
        val x = GetOneNewsUserCase().invoke(itemId)
        x.onSuccess {
            item = it
        }
        x.onFailure {
            Log.d("API", "El llamado a la Api Fallo")
        }
        return item
    }


    companion object {
        const val TAG = "ModalBottomSheet"
    }


}