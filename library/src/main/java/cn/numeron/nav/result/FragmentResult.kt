@file:JvmName("FragmentResult")
//用于Fragment跳转返回时回传的数据处理
package cn.numeron.nav.result

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

const val RESULT_OK = 1

const val RESULT_CANCELED = 0

const val RESULT_UNMATCHED = -1

inline fun <reified A : FragmentActivity> A.installNavResult() {
    supportFragmentManager.registerFragmentLifecycleCallbacks(FragmentLifecycleListener, true)
}

fun clearResultValue() {
    FragmentLifecycleListener.clearResultValue()
}

inline fun <reified T : Any> Fragment.setResult(result: Int, value: T? = null) {
    val typedValue = object : TypedValue<T>(result, value) {}
    FragmentLifecycleListener.putResult(typedValue)
}

inline fun <reified T : Any> onResult(noinline callback: (resultCode: Int, result: T?) -> Unit) {
    val typedCallback = object : TypedCallback<T>(null, callback) {}
    FragmentLifecycleListener.putCallback(typedCallback)
}