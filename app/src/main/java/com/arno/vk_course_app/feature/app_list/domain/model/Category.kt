package com.arno.vk_course_app.feature.app_list.domain.model


enum class Category {
        APP,
        GAME,
        PRODUCTIVITY,
        SOCIAL,
        EDUCATION,
        ENTERTAINMENT,
        MUSIC,
        VIDEO,
        PHOTOGRAPHY,
        HEALTH,
        SPORTS,
        NEWS,
        BOOKS,
        BUSINESS,
        FINANCE,
        TRAVEL,
        MAPS,
        FOOD,
        SHOPPING,
        UTILITIES,
}


fun Category.toText(): String = when (this) {
        Category.APP -> "Приложения"
        Category.GAME -> "Игры"
        Category.PRODUCTIVITY -> "Производительность"
        Category.SOCIAL -> "Социальные сети"
        Category.EDUCATION -> "Образование"
        Category.ENTERTAINMENT -> "Развлечения"
        Category.MUSIC -> "Музыка"
        Category.VIDEO -> "Видео"
        Category.PHOTOGRAPHY -> "Фотография"
        Category.HEALTH -> "Здоровье"
        Category.SPORTS -> "Спорт"
        Category.NEWS -> "Новости"
        Category.BOOKS -> "Книги"
        Category.BUSINESS -> "Бизнес"
        Category.FINANCE -> "Финансы"
        Category.TRAVEL -> "Путешествия"
        Category.MAPS -> "Карты"
        Category.FOOD -> "Еда"
        Category.SHOPPING -> "Покупки"
        Category.UTILITIES -> "Утилиты"
}
