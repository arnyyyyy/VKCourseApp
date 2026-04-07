package com.arno.vk_course_app.feature.app_list.domain.usecase

import com.arno.vk_course_app.feature.app_list.domain.model.AppDetails
import com.arno.vk_course_app.feature.app_list.domain.repository.AppRepository
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class GetAppsUseCaseTest {
        private lateinit var repository: AppRepository
        private lateinit var useCase: GetAppsUseCase

        @Before
        fun setUp() {
                repository = mock()
                useCase = GetAppsUseCase(repository)
        }

        @Test
        fun `invoke with non-empty repository EXPECT full list returned with order preserved`() = runTest {
                val apps = listOf(app(id = "1", name = "a"), app(id = "2", name = "b"), app(id = "3", name = "c"))
                whenever(repository.getApps()).thenReturn(apps)

                val result = useCase()

                assertEquals(apps.size, result.size)
                assertEquals(apps[0].name, result[0].name)
                assertEquals(apps[1].name, result[1].name)
                assertEquals(apps[2].name, result[2].name)
        }

        @Test
        fun `invoke with empty repository EXPECT empty list`() = runTest {
                whenever(repository.getApps()).thenReturn(emptyList())
                assertTrue(useCase().isEmpty())
        }

        @Test(expected = RuntimeException::class)
        fun `invoke when repository throws EXPECT exception propagated`() = runTest {
                whenever(repository.getApps()).thenThrow(RuntimeException("Timeout"))
                useCase()
        }

        private fun app(
                id: String = "0",
                name: String = "name",
                developer: String = "",
                category: String = "cat",
        ) = AppDetails(
                id = id, name = name, developer = developer, category = category,
                iconUrl = "url", description = "desc",
        )
}
