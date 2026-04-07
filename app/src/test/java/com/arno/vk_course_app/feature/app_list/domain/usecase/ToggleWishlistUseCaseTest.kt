package com.arno.vk_course_app.feature.app_list.domain.usecase

import com.arno.vk_course_app.feature.app_list.domain.model.AppDetails
import com.arno.vk_course_app.feature.app_list.domain.repository.AppRepository
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class ToggleWishlistUseCaseTest {
        private lateinit var repository: AppRepository
        private lateinit var useCase: ToggleWishlistUseCase

        @Before
        fun setUp() {
                repository = mock()
                useCase = ToggleWishlistUseCase(repository)
        }

        @Test
        fun `invoke EXPECT repository toggleWishlist called`() = runTest {
                val a = app()
                useCase(a.id)

                verify(repository).toggleWishlist(a.id)
        }

        @Test(expected = RuntimeException::class)
        fun `invoke when repository throws EXPECT exception propagated`() = runTest {
                val a = app()
                whenever(repository.toggleWishlist(a.id)).thenThrow(RuntimeException("err"))

                useCase(a.id)
        }

        @Test
        fun `invoke twice with different ids EXPECT both delegated to repository`() = runTest {
                val a1 = app(id = "a")
                val a2 = app(id = "b")
                useCase(a1.id)
                useCase(a2.id)

                verify(repository).toggleWishlist(a1.id)
                verify(repository).toggleWishlist(a2.id)
        }

        private fun app(id: String = "1") = AppDetails(
                id = id, name = "name", category = "cat", iconUrl = "url", description = "desc",
        )
}
