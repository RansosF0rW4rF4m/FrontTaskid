package com.example.taskids.ui.theme.ui.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.R
import androidx.fragment.app.Fragment
import com.example.taskids.view.UserViewModel


class UserListFragment : Fragment() {

    private val viewModel: UserViewModel by UserViewModel()
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_user_list, container, false)
        recyclerView = view.findViewById(R.id.userRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        viewModel.loadUsers()
        viewModel.users.observe(viewLifecycleOwner) { users ->
            recyclerView.adapter = UserAdapter(users)
        }
        return view
    }
}