package com.nackun.todo.viewmodel

import com.example.appstudy.todo.domain.model.TodoEntity
import com.nackun.domain.todo.usecase.todo.GetTodoItemUseCase
import com.nackun.domain.todo.usecase.todo.InsertTodoListUseCase
import com.nackun.todo.presentation.list.ListViewModel
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
@ExperimentalCoroutinesApi
internal class ListViewModelTest : ViewModelTest() {

    private val viewModel: ListViewModel by inject()

    private val insertTodoListUseCase: InsertTodoListUseCase by inject()
    private val getTodoItemUseCase: GetTodoItemUseCase by inject()

    private val mockList = (0 until 10).map {
        TodoEntity(
            id = it.toLong(),
            title = "title $it",
            description = "description $it",
            hasCompleted = false
        )
    }

    /**
     * 필요한 Usecase들
     * 1. InsertTodoListUseCase
     * 2. GetTodoItemUseCase
     */

    @Before
    fun init() {
        initData()
    }

    private fun initData() = runBlockingTest {
        insertTodoListUseCase(mockList)
    }

    // Test : 입력된 데이터를 불러와서 검증한다.
    @Test
    fun `test viewModel fetch`(): Unit = runBlockingTest {
        val testObservable = viewModel.todoListState.test()
        viewModel.fetchData()
        testObservable.assertValueSequence(
            listOf(
                com.nackun.todo.presentation.list.TodoListState.UnInitialized,
                com.nackun.todo.presentation.list.TodoListState.Loading,
                com.nackun.todo.presentation.list.TodoListState.Success(mockList),
            )
        )
    }

    // todo
    // Test : 데이터를 업데이트 했을 때 잘 반영되는가
    @Test
    fun `test Item Update`(): Unit = runBlockingTest {
        val todo = TodoEntity(
            id = 1,
            title = "title 1",
            description = "description 1",
            hasCompleted = true
        )
        viewModel.updateItem(todo)
        assert(getTodoItemUseCase(id = todo.id) == todo)
    }

    // Test : 데이터를 다 날렸을 때 빈 상태로 보여지는가?
    @Test
    fun `test Item Delete All`(): Unit = runBlockingTest {
        val testObservable = viewModel.todoListState.test()
        viewModel.fetchData()
        viewModel.deleteAll()
        testObservable.assertValueSequence(
            listOf(
                com.nackun.todo.presentation.list.TodoListState.UnInitialized,
                com.nackun.todo.presentation.list.TodoListState.Loading,
                com.nackun.todo.presentation.list.TodoListState.Success(mockList),
                com.nackun.todo.presentation.list.TodoListState.Loading,
                com.nackun.todo.presentation.list.TodoListState.Success(emptyList())
            )
        )
    }
}
