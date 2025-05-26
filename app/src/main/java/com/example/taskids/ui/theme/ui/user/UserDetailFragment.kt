package com.example.taskids.ui.theme.ui.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.taskids.view.UserViewModel
import com.taskids.R
import com.taskids.viewmodel.UserViewModel

class UserDetailFragment(private val userId: Int) : Fragment() {

    private val viewModel: UserViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_user_detail, container, false)
        val usernameText: TextView = view.findViewById(R.id.usernameText)
        val emailText: TextView = view.findViewById(R.id.emailText)

        viewModel.loadUserById(userId)
        viewModel.user.observe(viewLifecycleOwner) { user ->
            user?.let {
                usernameText.text = it.username
                emailText.text = it.email
            }
        }

        return view
    }
}