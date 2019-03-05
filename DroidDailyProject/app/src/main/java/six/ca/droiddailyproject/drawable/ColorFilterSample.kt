package six.ca.droiddailyproject.drawable

import android.app.Activity
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.LightingColorFilter
import android.graphics.PorterDuff
import android.os.Bundle
import android.support.v4.content.ContextCompat
import kotlinx.android.synthetic.main.act_color_filter.*
import six.ca.droiddailyproject.R

/**
 * @CopyRight six.ca
 * Created by Heavens on 2019-03-04.
 */
class ColorFilterSample: Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_color_filter)

        iconClear.setColorFilter(ContextCompat.getColor(this, R.color.colorPrimary), PorterDuff.Mode.MULTIPLY)

        iconSrc.setColorFilter(ContextCompat.getColor(this, R.color.colorPrimary), PorterDuff.Mode.SRC)

        iconSrcIn.setColorFilter(ContextCompat.getColor(this, R.color.colorPrimary), PorterDuff.Mode.SRC_IN)

        iconSrcOut.setColorFilter(ContextCompat.getColor(this, R.color.colorPrimary), PorterDuff.Mode.SRC_OUT)

        iconSrcAtop.setColorFilter(ContextCompat.getColor(this, R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP)

        iconSrcOver.setColorFilter(ContextCompat.getColor(this, R.color.colorPrimary), PorterDuff.Mode.SRC_OVER)

        iconDst.setColorFilter(ContextCompat.getColor(this, R.color.colorPrimary), PorterDuff.Mode.DST)

        iconDstIn.setColorFilter(ContextCompat.getColor(this, R.color.colorPrimary), PorterDuff.Mode.DST_IN)

        iconDstOut.setColorFilter(ContextCompat.getColor(this, R.color.colorPrimary), PorterDuff.Mode.DST_OUT)

        iconDstAtop.setColorFilter(ContextCompat.getColor(this, R.color.colorPrimary), PorterDuff.Mode.DST_ATOP)

        iconDstOver.setColorFilter(ContextCompat.getColor(this, R.color.colorPrimary), PorterDuff.Mode.DST_OVER)

        val filter = LightingColorFilter(0, ContextCompat.getColor(this, R.color.colorPrimary))

        val colorMatrix = ColorMatrix(floatArrayOf(
            1f, 0f, 0f, 0f, 0f,
            0f, .52f, 0f, 0f, 0f,
            0f, 0f, .47f, 0f, 0f,
            0f, 0f, 0f, 1f, 0f))

        iconClear.colorFilter = ColorMatrixColorFilter(colorMatrix)
        iconSrc.colorFilter = filter
    }
}