package com.example.tzsushi.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import com.example.tzsushi.MainActivity
import com.example.tzsushi.R
import com.example.tzsushi.databinding.FragmentMenuBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class MenuBottomSheetFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentMenuBottomSheetBinding? = null

    private val binding get() = _binding!!

//    private lateinit var checkBox1: CheckBox
//    private lateinit var checkBox2: CheckBox
//    private lateinit var checkBox3: CheckBox
//    private lateinit var checkBox4: CheckBox
//    private lateinit var checkBox5: CheckBox
//
//    private var selectedCount: Int = 0
//    private lateinit var mainActivity: MainActivity


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

//    companion object {
//        fun newInstance(initialCount: Int): MenuBottomSheetFragment {
//            val fragment = MenuBottomSheetFragment()
//            val args = Bundle()
//            args.putInt("initialCount", initialCount)
//            fragment.arguments = args
//            return fragment
//        }
//    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMenuBottomSheetBinding.inflate(inflater, container, false)
        return binding.root

    }

//    override fun onResume() {
//        super.onResume()
//        // При возобновлении фрагмента получаем ссылку на MainActivity
//        activity?.let { activity ->
//            if (activity is MainActivity) {
//                mainActivity = activity
//            }
//        }
//    }

//    // Метод для обновления счетчика в MainActivity
//    private fun updateSelectedCount(count: Int) {
//        mainActivity.updateSelectedCount(count)
//    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.ok.setOnClickListener {
            dismiss()
        }

//        checkBox1 = binding.hot
//        checkBox2 = binding.newProduct
//        checkBox3 = binding.vegan
//        checkBox4 = binding.Hit
//        checkBox5 = binding.expressMenu
//
//        // Проверяем, было ли предварительно выбрано какое-либо количество чекбоксов
//        val mainActivity = activity as MainActivity
//        selectedCount = arguments?.getInt("initialCount") ?: 0
//        mainActivity.updateSelectedCount(selectedCount)
//
//        // Обновляем счетчик при изменении состояния чекбоксов
//        val checkBoxes = listOf(checkBox1, checkBox2, checkBox3, checkBox4, checkBox5)
//        for (checkBox in checkBoxes) {
//            checkBox.setOnCheckedChangeListener { _, isChecked ->
//                if (isChecked) {
//                    selectedCount++
//                } else {
//                    selectedCount--
//                }
//                updateSelectedCount(selectedCount)
//                (activity as? MainActivity)?.updateSelectedCount(selectedCount)
//            }
//        }

    }



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // Настройка поведения BottomSheet
        val behavior = BottomSheetBehavior.from(view?.parent as View)
//        behavior.peekHeight = 300 // Устанавливаем высоту по умолчанию
        behavior.state = BottomSheetBehavior.STATE_COLLAPSED // Устанавливаем начальное состояние
    }



}