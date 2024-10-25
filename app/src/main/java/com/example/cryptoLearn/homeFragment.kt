package com.example.cryptoLearn

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast


class homeFragment : Fragment() {
    private lateinit var homeTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        homeTextView = view.findViewById(R.id.home)

        homeTextView.setOnClickListener {
            Toast.makeText(context, "Clicked on Home!", Toast.LENGTH_SHORT).show()
        }

        return view
    }

}