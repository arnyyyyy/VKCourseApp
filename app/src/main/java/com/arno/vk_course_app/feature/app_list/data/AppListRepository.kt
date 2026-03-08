package com.arno.vk_course_app.feature.app_list.data

import com.arno.vk_course_app.feature.app_details.data.AppDetails
import com.arno.vk_course_app.feature.app_details.data.Category

// AI generated apps description
object AppListRepository {
        fun findById(id: String): AppDetails? = getAppList().firstOrNull { it.id == id }

        fun getAppList(): List<AppDetails> {
                return listOf(
                        AppDetails(
                                id = "1",
                                name = "Гильдия Героев: Экшен ММО РПГ",
                                developer = "VK Play",
                                category = Category.GAME,
                                ageRating = 12,
                                size = 223.7f,
                                iconUrl = "https://static.rustore.ru/imgproxy/APsbtHxkVa4MZ0DXjnIkSwFQ_KVIcqHK9o3gHY6pvOQ/preset:web_app_icon_62/plain/https://static.rustore.ru/apk/393868735/content/ICON/3f605e3e-f5b3-434c-af4d-77bc5f38820e.png@webp",
                                screenshotUrlList = listOf(
                                        "https://static.rustore.ru/imgproxy/-y8kd-4B6MQ-1OKbAbnoAIMZAzvoMMG9dSiHMpFaTBc/preset:web_scr_lnd_335/plain/https://static.rustore.ru/apk/393868735/content/SCREENSHOT/dfd33017-e90d-4990-aa8c-6f159d546788.jpg@webp",
                                        "https://static.rustore.ru/imgproxy/dZCvNtRKKFpzOmGlTxLszUPmwi661IhXynYZGsJQvLw/preset:web_scr_lnd_335/plain/https://static.rustore.ru/apk/393868735/content/SCREENSHOT/60ec4cbc-dcf6-4e69-aa6f-cc2da7de1af6.jpg@webp",
                                ),
                                description = "Легендарный рейд героев в Фэнтези РПГ. Станьте героем гильдии и зразите мастера подземелья!",
                        ),
                        AppDetails(
                                id = "2",
                                name = "ВКонтакте",
                                developer = "VK",
                                category = Category.SOCIAL,
                                ageRating = 6,
                                size = 87.4f,
                                iconUrl = "https://static.rustore.ru/imgproxy/gi7isSeWaOdDyern23Uv4oSv4Lv8xRn8D-IKzQ8dEY0/preset:web_app_icon_62/plain/https://static.rustore.ru/3f3d7180-6eb9-45ad-8706-f467c6dcf82a@webp",
                                screenshotUrlList = emptyList(),
                                description = "ВКонтакте — крупнейшая социальная сеть в России.",
                        ),
                        AppDetails(
                                id = "3",
                                name = "Яндекс Карты",
                                developer = "Yandex LLC",
                                category = Category.MAPS,
                                ageRating = 0,
                                size = 54.2f,
                                iconUrl = "https://static.rustore.ru/imgproxy/uk4eQOt5jhw-yCSHwIZYwyGLHRduRvjqAhvysY__Wmc/preset:web_app_icon_62/plain/https://static.rustore.ru/apk/586431/content/ICON/a5f2fe7d-cd63-4f3f-a2f8-40d997c1d6f4.png@webp",
                                screenshotUrlList = emptyList(),
                                description = "Яндекс Карты — навигатор с пробками онлайн.",
                        ),
                        AppDetails(
                                id = "4",
                                name = "Яндекс Музыка",
                                developer = "Yandex LLC",
                                category = Category.MUSIC,
                                ageRating = 6,
                                size = 42.1f,
                                iconUrl ="https://static.rustore.ru/imgproxy/mwOwYCi-iiETpcXs6s8d_oZBA67kKgQR-uDwacLOfBk/preset:web_app_icon_62/plain/https://static.rustore.ru/apk/303463871/content/ICON/729a3c3a-2363-46f4-8da8-306ef4839e30.png@webp",
                                screenshotUrlList = emptyList(),
                                description = "Яндекс Музыка — слушайте любимые треки онлайн и офлайн.",
                        ),
                        AppDetails(
                                id = "5",
                                name = "Сбербанк Онлайн",
                                developer = "Sberbank",
                                category = Category.FINANCE,
                                ageRating = 6,
                                size = 118.5f,
                                iconUrl = "https://static.rustore.ru/imgproxy/lQKIdJKRbtJBX0dxbZueqU-a5TEP_-_yKjFjWljOsaE/preset:web_app_icon_62/plain/https://static.rustore.ru/apk/462271/content/ICON/f1b3c68a-b734-48ce-b62f-490208d3fa0e.png@webp",
                                screenshotUrlList = listOf(
                                        "https://static.rustore.ru/imgproxy/YAyMfd1C5Y4ADB-81F4yT0rqc7hoSOkMOyTXTyXkG60/preset:web_scr_prt_162/plain/https://static.rustore.ru/2026/2/19/e2/apk/462271/content/SCREENSHOT/af9920e4-eb9a-45d7-9224-ada17e9fad94.png@webp",
                                        "https://static.rustore.ru/imgproxy/ueOMCvH3fqGWDCmSF009YQq_6JMq7UMhSWbAcOEwZBA/preset:web_scr_prt_162/plain/https://static.rustore.ru/2026/2/19/e8/apk/462271/content/SCREENSHOT/fd8a50aa-7485-4d53-abb4-753098690a0a.png@webp"
                                ),
                                description = "Сбербанк Онлайн — мобильное приложение для управления счетами, картами и кредитами.",
                        ),
                        AppDetails(
                                id = "6",
                                name = "Ozon — интернет-магазин",
                                developer = "Ozon",
                                category = Category.SHOPPING,
                                ageRating = 6,
                                size = 76.3f,
                                iconUrl = "https://static.rustore.ru/imgproxy/hJVgIrWgS4mntLa3nyfCy5Y6M1Vbynn4M0FQjl28eM4/preset:web_app_icon_62/plain/https://static.rustore.ru/2025/12/22/22/apk/514239/content/ICON/76a9e830-f493-4661-b55f-43a30a73408b.png@webp" ,
                                screenshotUrlList = emptyList(),
                                description = "Ozon — один из крупнейших интернет-магазинов России.",
                        ),
                        AppDetails(
                                id = "7",
                                name = "Гильдия Героев: Экшен ММО РПГ",
                                developer = "VK Play",
                                category = Category.GAME,
                                ageRating = 12,
                                size = 223.7f,
                                iconUrl = "https://static.rustore.ru/imgproxy/APsbtHxkVa4MZ0DXjnIkSwFQ_KVIcqHK9o3gHY6pvOQ/preset:web_app_icon_62/plain/https://static.rustore.ru/apk/393868735/content/ICON/3f605e3e-f5b3-434c-af4d-77bc5f38820e.png@webp",
                                screenshotUrlList = listOf(
                                        "https://static.rustore.ru/imgproxy/-y8kd-4B6MQ-1OKbAbnoAIMZAzvoMMG9dSiHMpFaTBc/preset:web_scr_lnd_335/plain/https://static.rustore.ru/apk/393868735/content/SCREENSHOT/dfd33017-e90d-4990-aa8c-6f159d546788.jpg@webp",
                                        "https://static.rustore.ru/imgproxy/dZCvNtRKKFpzOmGlTxLszUPmwi661IhXynYZGsJQvLw/preset:web_scr_lnd_335/plain/https://static.rustore.ru/apk/393868735/content/SCREENSHOT/60ec4cbc-dcf6-4e69-aa6f-cc2da7de1af6.jpg@webp",
                                ),
                                description = "Легендарный рейд героев в Фэнтези РПГ. Станьте героем гильдии и зразите мастера подземелья!",
                        ),
                        AppDetails(
                                id = "8",
                                name = "ВКонтакте",
                                developer = "VK",
                                category = Category.SOCIAL,
                                ageRating = 6,
                                size = 87.4f,
                                iconUrl = "https://static.rustore.ru/imgproxy/gi7isSeWaOdDyern23Uv4oSv4Lv8xRn8D-IKzQ8dEY0/preset:web_app_icon_62/plain/https://static.rustore.ru/3f3d7180-6eb9-45ad-8706-f467c6dcf82a@webp",
                                screenshotUrlList = emptyList(),
                                description = "ВКонтакте — крупнейшая социальная сеть в России.",
                        ),
                        AppDetails(
                                id = "9",
                                name = "Яндекс Карты",
                                developer = "Yandex LLC",
                                category = Category.MAPS,
                                ageRating = 0,
                                size = 54.2f,
                                iconUrl = "https://static.rustore.ru/imgproxy/uk4eQOt5jhw-yCSHwIZYwyGLHRduRvjqAhvysY__Wmc/preset:web_app_icon_62/plain/https://static.rustore.ru/apk/586431/content/ICON/a5f2fe7d-cd63-4f3f-a2f8-40d997c1d6f4.png@webp",
                                screenshotUrlList = emptyList(),
                                description = "Яндекс Карты — навигатор с пробками онлайн.",
                        ),
                        AppDetails(
                                id = "10",
                                name = "Яндекс Музыка",
                                developer = "Yandex LLC",
                                category = Category.MUSIC,
                                ageRating = 6,
                                size = 42.1f,
                                iconUrl ="https://static.rustore.ru/imgproxy/mwOwYCi-iiETpcXs6s8d_oZBA67kKgQR-uDwacLOfBk/preset:web_app_icon_62/plain/https://static.rustore.ru/apk/303463871/content/ICON/729a3c3a-2363-46f4-8da8-306ef4839e30.png@webp",
                                screenshotUrlList = emptyList(),
                                description = "Яндекс Музыка — слушайте любимые треки онлайн и офлайн.",
                        ),
                        AppDetails(
                                id = "11",
                                name = "Сбербанк Онлайн",
                                developer = "Sberbank",
                                category = Category.FINANCE,
                                ageRating = 6,
                                size = 118.5f,
                                iconUrl = "https://static.rustore.ru/imgproxy/lQKIdJKRbtJBX0dxbZueqU-a5TEP_-_yKjFjWljOsaE/preset:web_app_icon_62/plain/https://static.rustore.ru/apk/462271/content/ICON/f1b3c68a-b734-48ce-b62f-490208d3fa0e.png@webp",
                                screenshotUrlList = listOf(
                                        "https://static.rustore.ru/imgproxy/YAyMfd1C5Y4ADB-81F4yT0rqc7hoSOkMOyTXTyXkG60/preset:web_scr_prt_162/plain/https://static.rustore.ru/2026/2/19/e2/apk/462271/content/SCREENSHOT/af9920e4-eb9a-45d7-9224-ada17e9fad94.png@webp",
                                        "https://static.rustore.ru/imgproxy/ueOMCvH3fqGWDCmSF009YQq_6JMq7UMhSWbAcOEwZBA/preset:web_scr_prt_162/plain/https://static.rustore.ru/2026/2/19/e8/apk/462271/content/SCREENSHOT/fd8a50aa-7485-4d53-abb4-753098690a0a.png@webp"
                                ),
                                description = "Сбербанк Онлайн — мобильное приложение для управления счетами, картами и кредитами.",
                        ),
                        AppDetails(
                                id = "12",
                                name = "Ozon — интернет-магазин",
                                developer = "Ozon",
                                category = Category.SHOPPING,
                                ageRating = 6,
                                size = 76.3f,
                                iconUrl = "https://static.rustore.ru/imgproxy/hJVgIrWgS4mntLa3nyfCy5Y6M1Vbynn4M0FQjl28eM4/preset:web_app_icon_62/plain/https://static.rustore.ru/2025/12/22/22/apk/514239/content/ICON/76a9e830-f493-4661-b55f-43a30a73408b.png@webp" ,
                                screenshotUrlList = emptyList(),
                                description = "Ozon — один из крупнейших интернет-магазинов России.",
                        ),
                )
        }
}