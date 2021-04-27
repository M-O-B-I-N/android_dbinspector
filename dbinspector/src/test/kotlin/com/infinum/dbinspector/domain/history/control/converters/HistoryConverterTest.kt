package com.infinum.dbinspector.domain.history.control.converters

import com.infinum.dbinspector.data.models.local.proto.input.HistoryTask
import com.infinum.dbinspector.data.models.local.proto.output.HistoryEntity
import com.infinum.dbinspector.domain.Converters
import com.infinum.dbinspector.domain.shared.models.parameters.HistoryParameters
import com.infinum.dbinspector.shared.BaseConverterTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.koin.core.module.Module
import org.koin.dsl.module
import org.koin.test.inject

internal class HistoryConverterTest : BaseConverterTest() {

    override val converter by inject<Converters.History>()

    override fun modules(): List<Module> = listOf(
        module {
            single<Converters.History> { HistoryConverter() }
        }
    )

    @Test
    fun `Invoke is not implemented and should throw AbstractMethodError`() {
        val given = HistoryParameters.All(
            databasePath = "test.db",
        )

        assertThrows<AbstractMethodError> {
            runBlockingTest {
                converter.invoke(given)
            }
        }
    }

    @Test
    fun `Get converts to data task with same value`() =
        launch {
            val given = HistoryParameters.All(
                databasePath = "test.db",
            )
            val expected = HistoryTask(
                databasePath = "test.db"
            )
            val actual = test {
                converter get given
            }
            assertEquals(expected, actual)
        }

    @Test
    fun `Execution converts to data task with same values`() =
        launch {
            val given = HistoryParameters.Execution(
                statement = "SELECT * FROM users",
                databasePath = "test.db",
                timestamp = 1L,
                isSuccess = true
            )
            val expected = HistoryTask(
                execution = HistoryEntity.ExecutionEntity.getDefaultInstance().toBuilder()
                    .setDatabasePath("test.db")
                    .setExecution("SELECT * FROM users")
                    .setTimestamp(1L)
                    .setSuccess(true)
                    .build()
            )
            val actual = test {
                converter execution given
            }
            assertEquals(expected, actual)
        }

    @Test
    fun `Clear converts to data task with same value`() =
        launch {
            val given = HistoryParameters.All(
                databasePath = "test.db",
            )
            val expected = HistoryTask(
                databasePath = "test.db"
            )
            val actual = test {
                converter clear given
            }
            assertEquals(expected, actual)
        }
}
