package com.lakhai.petprojectapplicationlakhai.data.webview

import android.webkit.WebView
import android.webkit.WebViewClient
import com.lakhai.petprojectapplicationlakhai.ui.FromchosenActivity
import com.lakhai.petprojectapplicationlakhai.ui.GettedreceiptActivity
import com.lakhai.petprojectapplicationlakhai.ui.RandomrecipeActivity

class WebViewCustomClient(val activity:RandomrecipeActivity?,val notRandomActivity:GettedreceiptActivity?,val
fromchosenActivity: FromchosenActivity?):WebViewClient() {
    override fun onPageFinished(view: WebView?, url: String?) {
        super.onPageFinished(view, url)
        if(activity!=null){
            activity.setContentView(activity.binding.root)
            activity.binding.scrollView.scrollTo(0, 0)
        }else if(notRandomActivity!=null){
            notRandomActivity.setContentView(notRandomActivity.binding.root)
            notRandomActivity.binding?.scrollView?.scrollTo(0, 0)
        }else{
            fromchosenActivity?.setContentView(fromchosenActivity.binding.root)
            fromchosenActivity?.binding?.scrollView?.scrollTo(0, 0)
        }
    }
}