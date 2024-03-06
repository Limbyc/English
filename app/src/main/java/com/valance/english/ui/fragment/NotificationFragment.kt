package com.valance.english.ui.fragment

import NotificationAdapter
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.valance.english.databinding.NotificationFragmentBinding
import com.valance.english.db.RegistrationInfo
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotificationFragment: Fragment() {

    private lateinit var binding: NotificationFragmentBinding
    private lateinit var notificationAdapter: NotificationAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = NotificationFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


            if (isUserLoggedIn())  {
                val registrationInfo = RegistrationInfo("Регистрация", "Вы успешно зарегистрировались")
                val discountInfo = RegistrationInfo("Скидка", "Вам доступна скидка в 10% на все курсы уровня английского")

                val registrationInfoList: MutableList<RegistrationInfo> = mutableListOf()
                registrationInfoList.add(registrationInfo)
                registrationInfoList.add(discountInfo)
                notificationAdapter = NotificationAdapter(requireContext(), registrationInfoList)
                binding.recyclerView.adapter = notificationAdapter
                binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
                binding.recyclerView.visibility = View.VISIBLE
                binding.noNotification.visibility = View.GONE
            } else {
                binding.recyclerView.visibility = View.GONE
                binding.noNotification.visibility = View.VISIBLE
            }
    }
    private fun isUserLoggedIn(): Boolean {
        return preferences.contains("user_name")
    }

    private val preferences: SharedPreferences by lazy {
        requireContext().getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
    }
}