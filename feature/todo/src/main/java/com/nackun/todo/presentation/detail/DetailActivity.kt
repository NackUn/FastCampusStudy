package com.nackun.todo.presentation.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isGone
import com.nackun.todo.databinding.ActivityDetailBinding
import com.nackun.base.BaseActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import kotlin.coroutines.CoroutineContext

internal class DetailActivity : BaseActivity<DetailViewModel>(), CoroutineScope {
    override val viewModel: DetailViewModel by viewModel {
        parametersOf(
            intent.getSerializableExtra(DETAIL_MONDE_KEY),
            intent.getLongExtra(TODO_ID_KEY, -1)
        )
    }
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + Job()

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setResult(RESULT_OK)
    }

    private fun initViews(binding: ActivityDetailBinding) = with(binding) {
        detailEdittextTitle.isEnabled = false
        detailEdittextDescription.isEnabled = false

        detailButtonDelete.isGone = true
        detailButtonModify.isGone = true
        detailButtonSave.isGone = true

        detailButtonDelete.setOnClickListener {
            viewModel.deleteTodoItem()
        }
        detailButtonModify.setOnClickListener {
            viewModel.setModifyMode()
        }
        detailButtonSave.setOnClickListener {
            viewModel.updateTodoItem(
                title = detailEdittextTitle.text.toString(),
                description = detailEdittextDescription.text.toString()
            )
        }
    }

    override fun observeData() {
        viewModel.todoItemState.observe(this) {
            when (it) {
                is TodoDetailState.UnInitialized -> {
                    initViews(binding)
                }
                is TodoDetailState.Loading -> {
                    handleLoadingState()
                }
                is TodoDetailState.Success -> {
                    handleSuccessState(it)
                }
                is TodoDetailState.Error -> {
                    handleErrorState()
                }
                TodoDetailState.Delete -> {
                    finish()
                }
                TodoDetailState.Modify -> {
                    handleModifyState()
                }
                TodoDetailState.Write -> {
                    handleWriteState()
                }
            }
        }
    }

    private fun handleLoadingState() = with(binding) {
        detailProgressbar.isGone = false
    }

    private fun handleSuccessState(state: TodoDetailState.Success) = with(binding) {
        detailProgressbar.isGone = true

        detailEdittextTitle.isEnabled = false
        detailEdittextDescription.isEnabled = false

        detailButtonDelete.isGone = false
        detailButtonModify.isGone = false
        detailButtonSave.isGone = true

        state.todoItem.run {
            detailEdittextTitle.setText(title)
            detailEdittextDescription.setText(description)
        }
    }

    private fun handleErrorState() {
        Toast.makeText(this, "에러가 발생했습니다.", Toast.LENGTH_SHORT).show()
    }

    private fun handleModifyState() = with(binding) {
        detailEdittextTitle.isEnabled = true
        detailEdittextDescription.isEnabled = true

        detailButtonDelete.isGone = true
        detailButtonModify.isGone = true
        detailButtonSave.isGone = false
    }

    private fun handleWriteState() = with(binding) {
        detailEdittextTitle.isEnabled = true
        detailEdittextDescription.isEnabled = true

        detailButtonDelete.isGone = true
        detailButtonModify.isGone = true
        detailButtonSave.isGone = false
    }

    companion object {
        const val TODO_ID_KEY = "TodoId"
        const val DETAIL_MONDE_KEY = "DetailMode"

        fun getIntent(context: Context, detailMode: DetailMode) =
            Intent(context, DetailActivity::class.java).apply {
                putExtra(DETAIL_MONDE_KEY, detailMode)
            }

        fun getIntent(context: Context, detailMode: DetailMode, id: Long) =
            Intent(context, DetailActivity::class.java).apply {
                putExtra(DETAIL_MONDE_KEY, detailMode)
                putExtra(TODO_ID_KEY, id)
            }
    }
}
