package com.valance.english.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import com.valance.english.R
import com.valance.english.databinding.AdultCourseFragmentBinding
import com.valance.english.ui.adapter.CourseAdapter
import com.valance.english.ui.viewmodels.AdultViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AdultCorse: Fragment(){

    private lateinit var binding: AdultCourseFragmentBinding
    private var tabLayout: TabLayout? = null
    private val adultViewModel: AdultViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView
    private lateinit var courseAdapter: CourseAdapter
    private var selectedCurrency: String = "USD"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = AdultCourseFragmentBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tabLayout = binding.tabLayout
        val coursTypes = listOf("Бизнес", "Путешествия", "Учеба", "Политика", "Программирование")

        for (coursType in coursTypes) {
            val tab = tabLayout?.newTab()
            val customTabView = createTabView(coursType)
            tab?.customView = customTabView
            tab?.tag = coursType
            tabLayout?.addTab(tab!!)
        }

        tabLayout?.addOnTabSelectedListener(createTabListener())

        binding.appCompatImageView2.setOnClickListener{
            findNavController().navigate(R.id.action_adultCourse_to_chooseCours)
        }

        recyclerView = binding.recyclerView
        courseAdapter = CourseAdapter(emptyList())

        recyclerView.adapter = courseAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        adultViewModel.courses.observe(viewLifecycleOwner) { courses ->
            courseAdapter.updateCourses(courses.shuffled())
        }
        adultViewModel.filteredCourses.observe(viewLifecycleOwner) { filteredCourses ->
            courseAdapter.updateCourses(filteredCourses)
        }
        adultViewModel.getAllCourses()

        binding.USD.setOnClickListener {
            selectedCurrency = "USD"
            updateUIForSelectedCurrency()
        }

        binding.RUB.setOnClickListener {
            selectedCurrency = "RUB"
            updateUIForSelectedCurrency()
        }

    }

    private fun updateUIForSelectedCurrency() {
        courseAdapter.updateCurrency(selectedCurrency)
    }


    private fun createTabView(text: String): View {
        val customTabView = LayoutInflater.from(context).inflate(R.layout.type_of_course, null) as TextView
        customTabView.text = text
        return customTabView
    }

    private fun createTabListener(): TabLayout.OnTabSelectedListener {
        return object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                Log.d("MainFragment", "onTabSelected called")
                val selectedCourseType: String? = tab.tag as? String
                selectedCourseType?.let { type ->
                    Log.d("MainFragment", "Selected course type: $type")
                    val currentFilterName = adultViewModel.currentFilterName.value
                    adultViewModel.filterCoursesByTypeAndName(type, currentFilterName)
                }
                updateTabAppearance(tab, R.color.white, R.drawable.selected_type, R.font.sora_semibold)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                updateTabAppearance(tab, R.color.black, R.drawable.unselected_types, R.font.sora_regular)
            }

            override fun onTabReselected(tab: TabLayout.Tab) {
                onTabSelected(tab)
            }
        }
    }


    private fun updateTabAppearance(tab: TabLayout.Tab?, textColorResId: Int, backgroundDrawableResId: Int, fontResourceId: Int) {
        val customTabView = tab?.customView as? TextView
        customTabView?.setTextColor(ContextCompat.getColor(requireContext(), textColorResId))
        customTabView?.background = ContextCompat.getDrawable(requireContext(), backgroundDrawableResId)
        customTabView?.typeface = ResourcesCompat.getFont(requireContext(), fontResourceId)
    }

}