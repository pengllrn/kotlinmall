package com.kotlin.base.common

import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import java.util.*

/**
 * Author：Pengllrn
 * Date: 2019/3/3
 */
class AppManager private constructor() {
    private val activityStack:Stack<Activity> = Stack()

    companion object {
        val INSTANCE: AppManager by lazy { AppManager() }
    }

    /*
    入栈
     */
    fun addActivity(activity: Activity){
        activityStack.add(activity)
    }

    /*
    出栈
     */
    fun removeActivity(activity: Activity){
        activityStack.remove(activity)
        activity.finish()
    }

    /*
    获取当前栈顶
     */
    fun currentActivity():Activity{
        return activityStack.lastElement()
    }

    /*
    清理栈
     */
    fun finishAllActivity(){
        for(activity in activityStack){
            activity.finish()
        }

        activityStack.clear()
    }

    fun exitApp(context:Context){
        finishAllActivity()
        val activityManager =
        context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        activityManager.killBackgroundProcesses(context.packageName)
        System.exit(0)
    }
}