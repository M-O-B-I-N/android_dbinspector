package com.infinum.dbinspector.domain.schema.view.interactors

import com.infinum.dbinspector.data.Sources
import com.infinum.dbinspector.data.models.local.cursor.QueryResult
import com.infinum.dbinspector.domain.Interactors
import com.infinum.dbinspector.data.models.local.cursor.Query

internal class GetViewsInteractor(
    val source: Sources.Local.Schema
) : Interactors.GetViews {

    override suspend fun invoke(input: Query): QueryResult =
        source.getViews(input)
}
