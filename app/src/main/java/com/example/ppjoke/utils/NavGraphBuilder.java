package com.example.ppjoke.utils;

import android.content.ComponentName;

import androidx.navigation.ActivityNavigator;
import androidx.navigation.NavController;
import androidx.navigation.NavGraph;
import androidx.navigation.NavGraphNavigator;
import androidx.navigation.NavigatorProvider;
import androidx.navigation.fragment.FragmentNavigator;

import com.example.ppjoke.model.Destination;

import java.util.HashMap;
import java.util.Iterator;

public class NavGraphBuilder {

    public static void build(NavController controller) {

        NavigatorProvider provider = controller.getNavigatorProvider();
        NavGraph navGraph = new NavGraph(new NavGraphNavigator(provider));

        FragmentNavigator fragmentNavigator = provider.getNavigator(FragmentNavigator.class);
        ActivityNavigator activityNavigator = provider.getNavigator(ActivityNavigator.class);

        HashMap<String, Destination> destConfig = AppConfig.getDestConfig();
        Iterator<Destination> iterator = destConfig.values().iterator();

        while (iterator.hasNext()) {
            Destination node = iterator.next();
            if(node.isFragment) {
                FragmentNavigator.Destination destination = fragmentNavigator.createDestination();
                destination.setId(node.id);
                destination.setClassName(node.className);
                destination.addDeepLink(node.pageUrl);
                navGraph.addDestination(destination);
            }else {
                ActivityNavigator.Destination destination = activityNavigator.createDestination();
                destination.setId(node.id);
                destination.setComponentName(new ComponentName(AppGlobals.getsApplication().getPackageName(), node.className));
                destination.addDeepLink(node.pageUrl);
                navGraph.addDestination(destination);
            }

            if(node.asStarter) {
                navGraph.setStartDestination(node.id);
            }
        }

        controller.setGraph(navGraph);

    }
}
