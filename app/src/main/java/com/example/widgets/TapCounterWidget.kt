package com.example.widgets

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.widget.RemoteViews

class TapCounterWidget : AppWidgetProvider() {

    companion object {
        const val ACTION_TAP = "com.example.widgets.WIDGET_TAPPED"
        const val PREFS_NAME = "TapCounterPrefs"
        const val COUNT_KEY = "count_value"
    }

    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onReceive(context: Context, intent: Intent) {
        super.onReceive(context, intent)

        if (intent.action == ACTION_TAP) {
            val appWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID)

            if (appWidgetId != AppWidgetManager.INVALID_APPWIDGET_ID) {
                val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
                var currentCount = prefs.getInt(COUNT_KEY + appWidgetId, 0)

                // Increment logic (0 to 99)
                currentCount = if (currentCount >= 99) 0 else currentCount + 1

                // Save new count
                prefs.edit().putInt(COUNT_KEY + appWidgetId, currentCount).apply()

                // Force UI update
                val appWidgetManager = AppWidgetManager.getInstance(context)
                updateAppWidget(context, appWidgetManager, appWidgetId)
            }
        }
    }

    private fun updateAppWidget(context: Context, appWidgetManager: AppWidgetManager, appWidgetId: Int) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val count = prefs.getInt(COUNT_KEY + appWidgetId, 0)

        // Construct the RemoteViews object
        val views = RemoteViews(context.packageName, R.layout.widget_counter)
        views.setTextViewText(R.id.counter_text, count.toString())

        // Set up the click intent
        val intent = Intent(context, TapCounterWidget::class.java).apply {
            action = ACTION_TAP
            putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
        }

        val pendingIntent = PendingIntent.getBroadcast(
            context, appWidgetId, intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        views.setOnClickPendingIntent(R.id.widget_root, pendingIntent)

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views)
    }
}