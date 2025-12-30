package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.survivalcoding.gangnam2kiandroidstudy.R
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@Composable
fun RecipeLinkDialog(
    title: String,
    description: String,
    link: String,
    modifier: Modifier = Modifier,
    onCloseClick: () -> Unit = {},
    onCopyLinkClick: (String) -> Unit = {},
) {
    Dialog(
        onDismissRequest = { onCloseClick() }
    ) {
        Card(
            modifier = modifier
                .size(310.dp, 167.dp),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(
                containerColor = AppColors.white
            )
        ) {
            Box(
                modifier = Modifier,
                contentAlignment = Alignment.TopEnd
            ) {
                Box(
                    modifier = Modifier
                        .padding(top = 15.dp, end = 15.dp)
                        .size(5.dp)
                ) {
                    IconButton(
                        onClick = { onCloseClick() }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_close),
                            contentDescription = "Close Button",
                        )
                    }
                }

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 15.dp)
                ) {
                    Spacer(Modifier.height(20.dp))
                    Text(text = title, style = AppTextStyles.largeTextBold.copy(fontWeight = FontWeight.SemiBold))

                    Spacer(Modifier.height(10.dp))
                    Text(
                        text = description,
                        style = AppTextStyles.smallerTextRegular.copy(lineHeight = 20.sp),
                        color = AppColors.gray2
                    )

                    Spacer(Modifier.height(10.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(43.dp)
                            .background(color = AppColors.gray4, shape = RoundedCornerShape(9.dp)),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Spacer(Modifier.width(14.dp))
                        Text(
                            text = link,
                            modifier = Modifier.weight(1f),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            style = AppTextStyles.smallerTextBold.copy(fontWeight = FontWeight.Medium)
                        )

                        SmallButton(
                            text = "Copy Link",
                            modifier = Modifier.size(85.dp, 43.dp)
                        ) {
                            onCopyLinkClick(link)
                        }
                    }
                }
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RecipeLinkDialogPreview() {
    RecipeLinkDialog(
        title = "Recipe Link",
        description = "Copy recipe link and share your recipe link with friends and family.",
        link = "app.Recipe.co/jollof_rice"
    )
}
