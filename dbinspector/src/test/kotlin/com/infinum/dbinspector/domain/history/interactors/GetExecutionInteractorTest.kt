package com.infinum.dbinspector.domain.history.interactors

import com.infinum.dbinspector.data.Sources
import com.infinum.dbinspector.data.sources.local.proto.history.HistoryDataStore
import com.infinum.dbinspector.data.sources.memory.distance.LevenshteinDistance
import com.infinum.dbinspector.shared.BaseTest
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.flow
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.koin.core.module.Module
import org.koin.dsl.module
import org.koin.test.get
import org.mockito.kotlin.any

@DisplayName("GetExecutionInteractor tests")
internal class GetExecutionInteractorTest : BaseTest() {

    override fun modules(): List<Module> = listOf(
        module {
            factory<Sources.Local.History> { HistoryDataStore(mockk()) }
            factory<Sources.Memory.Distance> { LevenshteinDistance() }
        }
    )

    @Test
    @Disabled("Flow matcher fails")
    fun `Invoking interactor invokes source current`() {
        val source: Sources.Local.History = get()
        val distance: Sources.Memory.Distance = get()
        val interactor = GetExecutionInteractor(source, distance)

        coEvery { source.flow() } returns flow { mockk() }
        coEvery { source.current() } returns mockk()

        test {
            interactor.invoke(any())
        }

        coVerify(exactly = 1) { source.current() }
    }
}
