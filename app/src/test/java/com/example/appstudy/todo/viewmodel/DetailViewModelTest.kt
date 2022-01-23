package com.example.appstudy.todo.viewmodel

import com.example.appstudy.todo.domain.model.ToDoEntity
import com.example.appstudy.todo.domain.usecase.todo.InsertToDoItemUseCase
import com.example.appstudy.todo.domain.usecase.todo.InsertToDoListUseCase
import com.example.appstudy.todo.presentation.detail.DetailMode
import com.example.appstudy.todo.presentation.detail.DetailViewModel
import com.example.appstudy.todo.presentation.detail.ToDoDetailState
import com.example.appstudy.todo.presentation.list.ListViewModel
import com.example.appstudy.todo.presentation.list.ToDoListState
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

    private val insertToDoListUseCase: InsertToDoListUseCase by inject()
    private val insertToDoItemUseCase: InsertToDoItemUseCase by inject()

    private val mockItem = ToDoEntity(
        id = id,
        title = "title $id",
        description = "description $id",
        hasCompleted = false
    )

    private val mockItemAfterUpdate = ToDoEntity(
        id = id,
        title = "title $id Update",
        description = "description $id Update",
        hasCompleted = true
    )

    private val mockList = (0 until 10).map {
        ToDoEntity(
            id = it.toLong(),
            title = "title $it",
            description = "description $it",
            hasCompleted = false
        )
    }

    private val mockListAfterDelete = (0 until 10)
        .filter { it != 1 }
        .map {
            ToDoEntity(
                id = it.toLong(),
                title = "title $it",
                description = "description $it",
                hasCompleted = false
            )
        }

    private val mockListAfterUpdate = (0 until 10)
        .map {
            ToDoEntity(
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
        insertToDoListUseCase(mockList)
    }

    @Test
    fun `test viewModel fetch`(): Unit = runBlockingTest {
        val testObservable = detailViewModel.toDoItemState.test()
        detailViewModel.fetchData()
        testObservable.assertValueSequence(
            listOf(
                ToDoDetailState.UnInitialized,
                ToDoDetailState.Loading,
                ToDoDetailState.Success(mockItem),
            )
        )
    }

    @Test
    fun `test Item Delete`(): Unit = runBlockingTest {
        val listTestObservable = listViewModel.toDoListState.test()
        val detailTestObservable = detailViewModel.toDoItemState.test()

        listViewModel.fetchData()
        detailViewModel.fetchData()
        detailViewModel.deleteToDoItem()
        listViewModel.fetchData()

        listTestObservable.assertValueSequence(
            listOf(
                ToDoListState.UnInitialized,
                ToDoListState.Loading,
                ToDoListState.Success(mockList),
                ToDoListState.Loading,
                ToDoListState.Success(mockListAfterDelete),
            )
        )
        detailTestObservable.assertValueSequence(
            listOf(
                ToDoDetailState.UnInitialized,
                ToDoDetailState.Loading,
                ToDoDetailState.Success(mockItem),
                ToDoDetailState.Delete
            )
        )
    }

    @Test
    fun `test Item Update`(): Unit = runBlockingTest {
        val listTestObservable = listViewModel.toDoListState.test()
        val detailTestObservable = detailViewModel.toDoItemState.test()

        listViewModel.fetchData()
        detailViewModel.fetchData()
        detailViewModel.updateToDoItem(
            title = "title $id Update",
            description = "description $id Update",
            hasComplete = true
        )
        listViewModel.fetchData()

        listTestObservable.assertValueSequence(
            listOf(
                ToDoListState.UnInitialized,
                ToDoListState.Loading,
                ToDoListState.Success(mockList),
                ToDoListState.Loading,
                ToDoListState.Success(mockListAfterUpdate),
            )
        )
        detailTestObservable.assertValueSequence(
            listOf(
                ToDoDetailState.UnInitialized,
                ToDoDetailState.Loading,
                ToDoDetailState.Success(mockItem),
                ToDoDetailState.Loading,
                ToDoDetailState.Success(mockItemAfterUpdate)
            )
        )
    }
}
