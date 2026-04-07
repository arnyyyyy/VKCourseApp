package com.arno.vk_course_app.feature.app_list.domain.usecase

import com.arno.vk_course_app.feature.app_list.domain.model.AppDetails
import com.arno.vk_course_app.feature.app_list.domain.repository.AppRepository
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class GetAppByIdUseCaseTest {
        private lateinit var repository: AppRepository
        private lateinit var useCase: GetAppByIdUseCase

        @Before
        fun setUp() {
                repository = mock()
                useCase = GetAppByIdUseCase(repository)
        }

        @Test
        fun `invoke with existing id EXPECT complete domain object returned unchanged`() = runTest {
                val expected = app(isInWishlist = true)
                whenever(repository.getAppById(expected.id)).thenReturn(expected)

                val result = useCase(expected.id)

                assertEquals(expected, result)
                verify(repository).getAppById(expected.id)
        }

        @Test
        fun `invoke when repository returns null EXPECT null`() = runTest {
                val a = app()
                whenever(repository.getAppById(a.id)).thenReturn(null)

                assertNull(useCase(a.id))
        }

        @Test(expected = RuntimeException::class)
        fun `invoke when repository throws EXPECT exception propagated`() = runTest {
                val a = app()
                whenever(repository.getAppById(a.id)).thenThrow(RuntimeException("err"))

                useCase(a.id)
        }

        private fun app(isInWishlist: Boolean = false) = AppDetails(
                id = "1", name = "name", category = "cat", iconUrl = "url",
                description = "desc", isInWishlist = isInWishlist,
        )
}
