package com.example.appstudy.todo.viewmodel

import com.example.appstudy.todo.data.model.ToDoDto
import com.example.appstudy.todo.domain.usecase.todo.InsertToDoListUseCase
import com.example.appstudy.todo.presentation.list.ListViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.koin.test.inject

/**
 * [ListViewModelTest]을 테스트하기 위한 Unit Test Class
 *
 * 1. initData()
 * 2. test viewModel fetch
 * 3. test Item Update
 * 4. test Item Delete All
 */
internal class ListViewModelTest : ViewModelTest() {

    private val viewModel: ListViewModel by inject()

    private val insertToDoListUseCase: InsertToDoListUseCase by inject()

    private val mockList = (0 until 10).map {
        ToDoDto(
            id = it.toLong(),
            title = "title $it",
            description = "description $it",
            hasCompleted = false
        )
    }

    /**
     * 필요한 Usecase들
     * 1. InsertToDoListUseCase
     * 2. GetToDoItemUseCase
     */

    @ExperimentalCoroutinesApi
    @Before
    fun init() {
        initData()
    }

    @ExperimentalCoroutinesApi
    private fun initData() = runBlockingTest {
        insertToDoListUseCase(mockList)
    }

    // Test : 입력된 데이터를 불러와서 검증한다.
    @ExperimentalCoroutinesApi
    @Test
    fun `test viewModel fetch`(): Unit = runBlockingTest {
        val testObservable = viewModel.toDoList.test()
        viewModel.fetchData()
        testObservable.assertValueSequence(
            listOf(mockList)
        )
    }
}