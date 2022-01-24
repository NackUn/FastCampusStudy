package com.example.appstudy.todo.viewmodel

import com.example.appstudy.todo.domain.model.ToDoEntity
import com.example.appstudy.todo.domain.usecase.todo.InsertToDoItemUseCase
import com.example.appstudy.todo.domain.usecase.todo.InsertToDoListUseCase
import com.example.appstudy.todo.presentation.detail.DetailMode
import com.example.appstudy.todo.presentation.detail.DetailViewModel
import com.example.appstudy.todo.presentation.detail.ToDoDetailState
import com.example.appstudy.todo.presentation.list.ListViewModel
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

    private val insertToDoListUseCase: InsertToDoListUseCase by inject()
    private val insertToDoItemUseCase: InsertToDoItemUseCase by inject()

    private val mockItem = ToDoEntity(
        id = id,
        title = "title $id",
        description = "description $id",
        hasCompleted = false
    )

    @Test
    fun `test viewModel fetch`(): Unit = runBlockingTest {
        val testObservable = detailViewModel.toDoItemState.test()

        detailViewModel.fetchData()

        testObservable.assertValueSequence(
            listOf(
                ToDoDetailState.UnInitialized,
                ToDoDetailState.Loading,
                ToDoDetailState.Write,
            )
        )
    }
}
