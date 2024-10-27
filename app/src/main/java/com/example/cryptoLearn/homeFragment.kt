package com.example.cryptoLearn

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import com.example.cryptoLearn.R

class homeFragment : Fragment() {

    private lateinit var lessonCardContainer: LinearLayout
    private lateinit var categoryIntro: TextView
    private lateinit var categoryHistory: TextView
    private lateinit var categoryBlockchainNet: TextView

    // Define lesson content for each category
    private val lessonsMap = mapOf(
        "Introduction" to listOf("Lesson Name", "Lesson Name", "Lesson Name"),
        "History" to listOf("Lesson Name", "Lesson Name", "Lesson Name", "Lesson Name", "Lesson Name", "Lesson Name", "Lesson Name", "Lesson Name", "Lesson Name"),
        "Blockchain Networks" to listOf("Lesson Name", "Lesson Name", "Lesson Name")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // Find views
        lessonCardContainer = view.findViewById(R.id.lesson_card_container)
        categoryIntro = view.findViewById(R.id.category_intro)
        categoryHistory = view.findViewById(R.id.category_history)
        categoryBlockchainNet = view.findViewById(R.id.category_blockchain_net)

        // Set click listeners for each category
        categoryIntro.setOnClickListener { updateLessons("Introduction", categoryIntro) }
        categoryHistory.setOnClickListener { updateLessons("History", categoryHistory) }
        categoryBlockchainNet.setOnClickListener { updateLessons("Blockchain Networks", categoryBlockchainNet) }

        updateLessons("Introduction", categoryIntro)


        return view
    }

    // Update the lesson section based on the selected category and style the selected category
    private fun updateLessons(category: String, selectedCategoryTextView: TextView) {
        // Clear previous lessons
        lessonCardContainer.removeAllViews()

        // Reset all categories to default style
        resetCategoryStyles()

        // Style the selected category
        selectedCategoryTextView.apply {
            setTypeface(typeface, android.graphics.Typeface.BOLD_ITALIC)
            paint.isUnderlineText = true
            setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
        }

        // Get lessons for the selected category
        val lessons = lessonsMap[category]

        // Convert 8dp to pixels
        val marginTop8dp = resources.getDimensionPixelSize(R.dimen.margin_top_8dp)

        // Load the "Righteous" font from resources
        val righteousFont = ResourcesCompat.getFont(requireContext(), R.font.righteous)

        // Add each lesson as a TextView in the lesson container
        lessons?.forEachIndexed { index, lesson ->
            val lessonTextView = TextView(requireContext()).apply {
                text = lesson
                setPadding(40, 40, 40, 40)
                setBackgroundResource(R.drawable.lesson_cards)
                setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.check_outline, 0)
                compoundDrawablePadding = 8
                textSize = 17f
                setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
                typeface = righteousFont // Apply the Righteous font
            }

            // Set top margin of 8dp for all items except the first one
            val layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            if (index > 0) {
                layoutParams.topMargin = marginTop8dp
            }
            lessonTextView.layoutParams = layoutParams

            // Add the TextView to the container
            lessonCardContainer.addView(lessonTextView)
        }
    }
    // Reset all categories to default style
    private fun resetCategoryStyles() {
        val defaultColor = ContextCompat.getColor(requireContext(), R.color.black)

        val categoryTextViews = listOf(categoryIntro, categoryHistory, categoryBlockchainNet)

        // Reset each category TextView to default styling
        categoryTextViews.forEach { textView ->
            textView.apply {
                typeface = android.graphics.Typeface.DEFAULT // Set to default typeface (normal, no bold or italic)
                paint.isUnderlineText = false
                setTextColor(defaultColor)
                invalidate()  // Force refresh to apply styling immediately
            }
        }
    }


}
