package com.infinum.dbinspector.ui.pragma

import com.infinum.dbinspector.domain.UseCases
import com.infinum.dbinspector.shared.BaseTest
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.koin.core.module.Module
import org.koin.dsl.module
import org.koin.test.get

@DisplayName("PragmaViewModel tests")
internal class PragmaViewModelTest : BaseTest() {

    override fun modules(): List<Module> = listOf(
        module {
            factory { mockk<UseCases.OpenConnection>() }
            factory { mockk<UseCases.CloseConnection>() }
        }
    )

    @Test
    fun `Check use cases are instantiated and constructor invoked`() {
        val openUseCase: UseCases.OpenConnection = get()
        val closeUseCase: UseCases.CloseConnection = get()

        val viewModel = PragmaViewModel(openUseCase, closeUseCase)

        assertNotNull(openUseCase)
        assertNotNull(closeUseCase)
        assertNotNull(viewModel)
    }
}
