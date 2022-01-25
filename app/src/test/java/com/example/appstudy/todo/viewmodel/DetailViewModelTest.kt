package com.example.appstudy.todo.viewmodel

import com.example.appstudy.todo.domain.model.TodoEntity
import com.example.appstudy.todo.domain.usecase.todo.InsertTodoItemUseCase
import com.example.appstudy.todo.domain.usecase.todo.InsertTodoListUseCase
import com.example.appstudy.todo.presentation.detail.DetailMode
import com.example.appstudy.todo.presentation.detail.DetailViewModel
import com.example.appstudy.todo.presentation.detail.TodoDetailState
import com.example.appstudy.todo.presentation.list.ListViewModel
import com.example.appstudy.todo.presentation.list.TodoListState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.koin.core.parameter.parametersOf
import org.koin.test.inject

/**
 * [DetailViewModelTest]을 테스트하기 위한 Unit Test Class
 *
 * 1. initData()
 * 2. test viewModel fetch
 * 3. test Item Delete
 * 4. test Item Update
 */
@ExperimentalCoroutinesApi
internal class DetailViewModelTest : ViewModelTest() {

    private val id = 1L

    private val listViewModel: ListViewModel by inject()
    private val detailViewModel by inject<DetailViewModel> { parametersOf(DetailMode.DETAIL, id) }

    private val insertTodoListUseCase: InsertTodoListUseCase by inject()
    private val insertTodoItemUseCase: InsertTodoItemUseCase by inject()

    private val mockItem = TodoEntity(
        id = id,
        title = "title $id",
        description = "description $id",
        hasCompleted = false
    )

    private val mockItemAfterUpdate = TodoEntity(
        id = id,
        title = "title $id Update",
        description = "description $id Update",
        hasCompleted = true
    )

    private val mockList = (0 until 10).map {
        TodoEntity(
            id = it.toLong(),
            title = "title $it",
            description = "description $it",
            hasCompleted = false
        )
    }

    private val mockListAfterDelete = (0 until 10)
        .filter { it != 1 }
        .map {
            TodoEntity(
                id = it.toLong(),
                title = "title $it",
                description = "description $it",
                hasCompleted = false
            )
        }

    private val mockListAfterUpdate = (0 until 10)
        .map {
            TodoEntity(
                id = it.toLong(),
                title = if (it == 1) {
                    "title $it Update"
                } else {
                    "title $it"
                },
                description = if (it == 1) {
                    "description $it Update"
                } else {
                    "description $it"
                },
                hasCompleted = it == 1
            )
        }

    @Before
    fun init() {
        initData()
    }

    private fun initData() = runBlockingTest {
        insertTodoListUseCase(mockList)
    }

    @Test
    fun `test viewModel fetch`(): Unit = runBlockingTest {
        val testObservable = detailViewModel.todoItemState.test()
        detailViewModel.fetchData()
        testObservable.assertValueSequence(
            listOf(
                TodoDetailState.UnInitialized,
                TodoDetailState.Loading,
                TodoDetailState.Success(mockItem),
            )
        )
    }

    @Test
    fun `test Item Delete`(): Unit = runBlockingTest {
        val listTestObservable = listViewModel.todoListState.test()
        val detailTestObservable = detailViewModel.todoItemState.test()

        listViewModel.fetchData()
        detailViewModel.fetchData()
        detailViewModel.deleteTodoItem()
        listViewModel.fetchData()

        listTestObservable.assertValueSequence(
            listOf(
                TodoListState.UnInitialized,
                TodoListState.Loading,
                TodoListState.Success(mockList),
                TodoListState.Loading,
                TodoListState.Success(mockListAfterDelete),
            )
        )
        detailTestObservable.assertValueSequence(
            listOf(
                TodoDetailState.UnInitialized,
                TodoDetailState.Loading,
                TodoDetailState.Success(mockItem),
                TodoDetailState.Delete
            )
        )
    }

    @Test
    fun `test Item Update`(): Unit = runBlockingTest {
        val listTestObservable = listViewModel.todoListState.test()
        val detailTestObservable = detailViewModel.todoItemState.test()

        listViewModel.fetchData()
        detailViewModel.fetchData()
        detailViewModel.updateTodoItem(
            title = "title $id Update",
            description = "description $id Update",
            hasComplete = true
        )
        listViewModel.fetchData()

        listTestObservable.assertValueSequence(
            listOf(
                TodoListState.UnInitialized,
                TodoListState.Loading,
                TodoListState.Success(mockList),
                TodoListState.Loading,
                TodoListState.Success(mockListAfterUpdate),
            )
        )
        detailTestObservable.assertValueSequence(
            listOf(
                TodoDetailState.UnInitialized,
                TodoDetailState.Loading,
                TodoDetailState.Success(mockItem),
                TodoDetailState.Loading,
                TodoDetailState.Success(mockItemAfterUpdate)
            )
        )
    }
}
