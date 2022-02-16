package com.nackun.todo.presentation.list

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isGone
import androidx.recyclerview.widget.LinearLayoutManager
import com.nackun.todo.R
import com.nackun.todo.databinding.ActivityListBinding
import com.nackun.todo.presentation.base.BaseActivity
import com.nackun.todo.presentation.util.toModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.coroutines.CoroutineContext

class ListActivity : BaseActivity<ListViewModel>(), CoroutineScope {
    override val viewModel: ListViewModel by viewModel()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + Job()

    private lateinit var binding: ActivityListBinding
    private lateinit var adapter: ListAdapter

    private val startDetailActivity =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                viewModel.fetchData()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun initViews(binding: ActivityListBinding) = with(binding) {
        listRecycler.layoutManager =
            LinearLayoutManager(this@ListActivity, LinearLayoutManager.VERTICAL, false)
        adapter = ListAdapter()
        listRecycler.adapter = adapter

        listSwipeRefresh.setOnRefreshListener {
            viewModel.fetchData()
        }

        listButtonAdd.setOnClickListener {
            startDetailActivity.launch(
                com.nackun.todo.presentation.detail.DetailActivity.getIntent(
                    this@ListActivity,
                    com.nackun.todo.presentation.detail.DetailMode.WRITE
                )
            )
        }
    }

    override fun observeData() {
        viewModel.todoListState.observe(this) {
            when (it) {
                is TodoListState.UnInitialized -> {
                    initViews(binding)
                }
                is TodoListState.Loading -> {
                    handleLoadingState()
                }
                is TodoListState.Success -> {
                    handleSuccessState(it)
                }
                is TodoListState.Error -> {
                    handleErrorState()
                }
            }
        }
    }

    private fun handleLoadingState() = with(binding) {
        listSwipeRefresh.isRefreshing = true
    }

    private fun handleSuccessState(state: TodoListState.Success) = with(binding) {
        listSwipeRefresh.isEnabled = state.todoList.isNotEmpty()
        listSwipeRefresh.isRefreshing = false

        if (state.todoList.isEmpty()) {
            listTextEmpty.isGone = false
            listRecycler.isGone = true
        } else {
            listTextEmpty.isGone = true
            listRecycler.isGone = false
            adapter.setToDoList(
                state.todoList.toModel(),
                toDoItemClickListener = {
                    startDetailActivity.launch(
                        com.nackun.todo.presentation.detail.DetailActivity.getIntent(
                            this@ListActivity,
                            com.nackun.todo.presentation.detail.DetailMode.DETAIL,
                            it.id
                        )
                    )
                }, toDoCheckListener = {
                    viewModel.updateItem(it)
                }
            )
        }
    }

    private fun handleErrorState() {
        Toast.makeText(this, "에러가 발생했습니다.", Toast.LENGTH_SHORT).show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_delete_all -> {
                viewModel.deleteAll()
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.list_menu, menu)
        return true
    }
}
