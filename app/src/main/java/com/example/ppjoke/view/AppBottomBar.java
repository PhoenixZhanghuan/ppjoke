package com.example.ppjoke.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MenuItem;

import com.example.ppjoke.R;
import com.example.ppjoke.model.BottomBar;
import com.example.ppjoke.model.Destination;
import com.example.ppjoke.utils.AppConfig;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;

import java.util.List;

public class AppBottomBar extends BottomNavigationView {

    private static int[] sIcons = new int[]{R.drawable.icon_tab_home, R.drawable.icon_tab_sofa, R.drawable.icon_tab_publish, R.drawable.icon_tab_find, R.drawable.icon_tab_mine};
    private BottomBar config;

    public AppBottomBar(Context context) {
        super(context, null);
    }

    public AppBottomBar(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
    }

    @SuppressLint("RestrictedApi")
    public AppBottomBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        config = AppConfig.getsBottomBarConfig();

        int[][] state = new int[2][];
        state[0] = new int[]{android.R.attr.state_selected};
        state[1] = new int[]{};

        int[] colors = new int[]{Color.parseColor(config.activeColor), Color.parseColor(config.inActiveColor)};
        ColorStateList stateList = new ColorStateList(state, colors);
        setItemTextColor(stateList);
        setItemIconTintList(stateList);

        setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);
        List<BottomBar.Tab> tabs = config.tabs;
        for (BottomBar.Tab tab : tabs) {
            if (!tab.enable) {
                continue;
            }

            int itemId = getItemId(tab.pageUrl);
            if (itemId < 0) {
                continue;
            }

            MenuItem menuItem = getMenu().add(0, itemId, tab.index, tab.title);
            menuItem.setIcon(sIcons[tab.index]);

        }

        int index = 0;
        for(BottomBar.Tab tab : config.tabs) {
            if(!tab.enable) {
                continue;
            }

            int itemId = getItemId(tab.pageUrl);
            if(itemId < 0) {
                continue;
            }

            int iconSize = dp2Px(tab.size);
            BottomNavigationMenuView menuView = (BottomNavigationMenuView) getChildAt(0);
            BottomNavigationItemView itemView = (BottomNavigationItemView) menuView.getChildAt(index);
            itemView.setIconSize(iconSize);

            if(TextUtils.isEmpty(tab.title)) {
                int tintColor = TextUtils.isEmpty(tab.tintColor) ? Color.parseColor("#ff678f") : Color.parseColor(tab.tintColor);
                itemView.setIconTintList(ColorStateList.valueOf(tintColor));
                itemView.setShifting(false);
            }
            index++;
        }

        if(config.selectTab != 0) {
            BottomBar.Tab selectTab = config.tabs.get(config.selectTab);
            if(selectTab.enable) {
                int itemId = getItemId(selectTab.pageUrl);
                post(() -> setSelectedItemId(itemId));
            }
        }

    }

    private int dp2Px(int size) {
        DisplayMetrics metrics = getContext().getResources().getDisplayMetrics();
        return (int)(metrics.density * size + 0.5f);
    }

    private int getItemId(String pageUrl) {
        Destination destination = AppConfig.getDestConfig().get(pageUrl);
        if(destination == null) {
            return -1;
        }
        return destination.id;
    }
}
