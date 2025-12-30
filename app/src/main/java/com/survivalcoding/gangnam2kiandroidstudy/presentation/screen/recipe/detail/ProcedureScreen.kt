package com.survivalcoding.gangnam2kiandroidstudy.presentation.screen.recipe.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.domain.model.Procedure
import com.survivalcoding.gangnam2kiandroidstudy.presentation.component.ProcedureItem
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Composable
fun ProcedureScreen(
    procedures: ImmutableList<Procedure>,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.fillMaxSize()) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Row {
                Icon(
                    painter = painterResource(R.drawable.recipe_serve),
                    contentDescription = "Recipe Serve",
                    tint = AppColors.gray3
                )
                Spacer(Modifier.width(5.dp))
                Text(
                    text = "1 serve",
                    style = AppTextStyles.smallerTextRegular,
                    color = AppColors.gray3
                )
            }

            Spacer(Modifier.weight(1f))
            Text(
                text = "${procedures.size} Steps",
                style = AppTextStyles.smallerTextRegular,
                color = AppColors.gray3
            )
        }

        Spacer(Modifier.height(20.dp))

        LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            items(procedures) { procedure ->
                ProcedureItem(procedure = procedure)
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ProcedureScreenPreview() {
    val procedures = persistentListOf(
        Procedure(
            step = 1,
            content = "Lorem Ipsum tempor incididunt ut labore et dolore,in voluptate velit esse cillum dolore eu fugiat nulla pariatur?",
            recipeId = 1
        ),
        Procedure(
            step = 2,
            content = """
                Lorem Ipsum tempor incididunt ut labore et dolore,in voluptate velit esse cillum dolore eu fugiat nulla pariatur?
                Tempor incididunt ut labore et dolore,in voluptate velit esse cillum dolore eu fugiat nulla pariatur?
            """.trimIndent(),
            recipeId = 1
        )
    )

    ProcedureScreen(
        procedures = procedures,
        modifier = Modifier.padding(30.dp)
    )
}