package com.survivalcoding.gangnam2kiandroidstudy.presentation.mockdata

import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Ingredient
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Procedure
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Recipe
import kotlinx.collections.immutable.persistentListOf

object MockRecipeData {
    val recipeListOne = Recipe(
        1,
        "Japanese",
        "Ttekbokki",
        "https://cdn.pixabay.com/photo/2017/07/27/16/48/toppokki-2545943_1280.jpg",
        "Kim Dahee",
        "30 min",
        5.0,
        ingredients = persistentListOf(
            Ingredient(
                1,
                "Tomatos",
                "https://cdn.pixabay.com/photo/2017/10/06/17/17/tomato-2823826_1280.jpg",
                "500g",
                1
            )
        ),
        procedures = persistentListOf(
            Procedure(
                step = 1,
                content = "Lorem Ipsum tempor incididunt ut labore et dolore,in voluptate velit esse cillum dolore eu fugiat nulla pariatur?",
                recipeId = 1
            )
        )
    )

    val recipeListThree = persistentListOf(
        Recipe(
            1,
            "Indian",
            "Classic Greek Salad",
            "https://cdn.pixabay.com/photo/2017/11/10/15/04/steak-2936531_1280.jpg",
            "Chef John",
            "20 min",
            4.0,
            ingredients = persistentListOf(
                Ingredient(
                    1,
                    "Tomatos",
                    "https://cdn.pixabay.com/photo/2017/10/06/17/17/tomato-2823826_1280.jpg",
                    "500g",
                    1
                )
            ),
            procedures = persistentListOf(
                Procedure(
                    step = 1,
                    content = "Lorem Ipsum tempor incididunt ut labore et dolore,in voluptate velit esse cillum dolore eu fugiat nulla pariatur?",
                    recipeId = 1
                )
            )
        ),
        Recipe(
            2,
            "Indian",
            "Classic Greek Salad",
            "https://cdn.pixabay.com/photo/2017/11/10/15/04/steak-2936531_1280.jpg",
            "Chef John",
            "20 min",
            4.0,
            ingredients = persistentListOf(
                Ingredient(
                    1,
                    "Tomatos",
                    "https://cdn.pixabay.com/photo/2017/10/06/17/17/tomato-2823826_1280.jpg",
                    "500g",
                    1
                )
            ),
            procedures = persistentListOf(
                Procedure(
                    step = 1,
                    content = """
                        Lorem Ipsum tempor incididunt ut labore et dolore,in voluptate velit esse cillum dolore eu fugiat nulla pariatur?
                        Tempor incididunt ut labore et dolore,in voluptate velit esse cillum dolore eu fugiat nulla pariatur?
                    """.trimIndent(),
                    recipeId = 1
                )
            )
        ),
        Recipe(
            3,
            "Indian",
            "Classic Greek Salad",
            "https://cdn.pixabay.com/photo/2017/11/10/15/04/steak-2936531_1280.jpg",
            "Chef John",
            "20 min",
            4.0,
            ingredients = persistentListOf(
                Ingredient(
                    1,
                    "Tomatos",
                    "https://cdn.pixabay.com/photo/2017/10/06/17/17/tomato-2823826_1280.jpg",
                    "500g",
                    1
                )
            ),
            procedures = persistentListOf(
                Procedure(
                    step = 1,
                    content = "Lorem Ipsum tempor incididunt ut labore et dolore,in voluptate velit esse cillum dolore eu fugiat nulla pariatur?",
                    recipeId = 1
                )
            )
        )
    )
}