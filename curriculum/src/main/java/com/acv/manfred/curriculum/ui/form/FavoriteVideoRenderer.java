package com.acv.manfred.curriculum.ui.form;

import com.acv.manfred.curriculum.R;

public class FavoriteVideoRenderer extends VideoRenderer {

    @Override
    protected void renderLabel() {
        String label = "Favorite ";
        getLabel().setText(label);
    }

    @Override
    protected void renderMarker(Video video) {
        getMarker().setImageResource(R.drawable.ic_favorite_border_black_24dp);
    }
}
