package com.example.appstudy.todo.viewmodel

import com.example.appstudy.todo.domain.model.ToDoEntity
import com.example.appstudy.todo.presentation.detail.DetailMode
import com.example.appstudy.todo.presentation.detail.DetailViewModel
import com.example.appstudy.todo.presentation.detail.ToDoDetailState
import com.example.appstudy.todo.presentation.list.ListViewModel
import com.example.appstudy.todo.presentation.list.ToDoListState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import org.koin.core.parameter.parametersOf
import org.koin.test.inject

/**
 * [DetailViewModelForWriteTest]을 테스트하기 위한 Unit Test Class
 *
 * 1. test viewModel fetch
 * 2. test Item Insert
 *
 */
@ExperimentalCoroutinesApi
internal class DetailViewModelForWriteTest : ViewModelTest() {

    private val id = 0L

    private val listViewModel: ListViewModel by inject()
    private val detailViewModel by inject<DetailViewModel> { parametersOf(DetailMode.WRITE, id) }

    private val mockItem = ToDoEntity(
        id = id,
        title = "",
        description = "",
        hasCompleted = false
    )

    @Test
    fun `test viewModel fetch`(): Unit = runBlockingTest {
        val testObservable = detailViewModel.toDoItemState.test()

        detailViewModel.fetchData()

        testObservable.assertValueSequence(
            listOf(
                ToDoDetailState.UnInitialized,
                ToDoDetailState.Write,
            )
        )
    }

    @Test
    fun `test Item Insert`(): Unit = runBlockingTest {
        val listTestObservable = listViewModel.toDoListState.test()
        val detailTestObservable = detailViewModel.toDoItemState.test()

        detailViewModel.updateToDoItem(
            title = mockItem.title,
            description = mockItem.description
        )
        detailTestObservable.assertValueSequence(
            listOf(
                ToDoDetailState.UnInitialized,
                ToDoDetailState.Loading,
                ToDoDetailState.Success(mockItem),
            )
        )

        assert(detailViewModel.detailMode == DetailMode.DETAIL)
        assert(detailViewModel.id == id)

        // 뒤로나가서 리스트 보기
        listViewModel.fetchData()
        listTestObservable.assertValueSequence(
            listOf(
                ToDoListState.UnInitialized,
                ToDoListState.Loading,
                ToDoListState.Success(listOf(mockItem)),
            )
        )
    }
}
