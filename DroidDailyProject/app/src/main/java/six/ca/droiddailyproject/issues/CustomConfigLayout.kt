package six.ca.droiddailyproject.issues

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import six.ca.droiddailyproject.databinding.CustomConfigBinding

/**
 * @author hellenxu
 * @date 2020-10-28
 * Copyright 2020 Six. All rights reserved.
 */
class CustomConfigLayout @JvmOverloads constructor(
    ctx: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
): FrameLayout(ctx, attrs, defStyleAttr) {

    private val contentView = CustomConfigBinding.inflate(LayoutInflater.from(ctx), this, true)

    fun setConfig(detail: ConfigDetail) {
        contentView.shortDescription.text = detail.shortDescription
        contentView.longDescription.text = detail.longDescription
    }
}

data class ConfigDetail(
    val imgUrl: String,
    val shortDescription: String,
    val longDescription: String
)