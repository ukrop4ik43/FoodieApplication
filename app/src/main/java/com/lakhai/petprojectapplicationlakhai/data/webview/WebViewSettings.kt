package com.lakhai.petprojectapplicationlakhai.data.webview

import android.annotation.SuppressLint
import android.util.Log
import android.webkit.WebSettings
import com.lakhai.petprojectapplicationlakhai.ui.FromchosenActivity
import com.lakhai.petprojectapplicationlakhai.ui.GettedreceiptActivity
import com.lakhai.petprojectapplicationlakhai.ui.RandomrecipeActivity

class WebViewSettings {
    @SuppressLint("SetJavaScriptEnabled")
    fun setWebView(activity:RandomrecipeActivity,title:String){
        activity?.binding?.relatedVideoWebView?.settings?.javaScriptEnabled = true
        activity?.binding?.relatedVideoWebView?.settings?.databaseEnabled = true
            activity?.binding?.relatedVideoWebView?.settings?.domStorageEnabled = true
        activity?.binding?.relatedVideoWebView?.settings?.useWideViewPort = true
        activity?.binding?.relatedVideoWebView?.settings?.allowContentAccess = true
        activity?.binding?.relatedVideoWebView?.settings?.allowFileAccess = true
        activity?.binding?.relatedVideoWebView?.requestFocus()
        activity?.binding?.relatedVideoWebView?.settings?.defaultTextEncodingName = "UTF-8"
        activity?.binding?.relatedVideoWebView?.settings?.cacheMode =
            WebSettings.LOAD_CACHE_ELSE_NETWORK
        activity?.binding?.relatedVideoWebView?.isVerticalScrollBarEnabled = true;
        activity?.binding?.relatedVideoWebView?.isHorizontalScrollBarEnabled = true;
        val arrayOfTitle = title?.split(" ")?.toTypedArray()
        var baseYoutubeUrl = "https://www.youtube.com/results?search_query="
        for (i in arrayOfTitle?.indices!!) {
            arrayOfTitle[i] = arrayOfTitle[i].lowercase()
            Log.d("bldsdssdcs", arrayOfTitle[i])
            baseYoutubeUrl = baseYoutubeUrl + arrayOfTitle[i] + "+"
        }
        activity?.binding?.relatedVideoWebView?.loadUrl(baseYoutubeUrl)
        activity?.binding?.relatedVideoWebView?.webViewClient = WebViewCustomClient(
            activity!!,null,null)
    }

    @SuppressLint("SetJavaScriptEnabled")
    fun setWebViewsS(activity:FromchosenActivity,title:String){
        activity?.binding?.relatedVideoWebView?.settings?.javaScriptEnabled = true
        activity?.binding?.relatedVideoWebView?.settings?.databaseEnabled = true
        activity?.binding?.relatedVideoWebView?.settings?.domStorageEnabled = true
        activity?.binding?.relatedVideoWebView?.settings?.useWideViewPort = true
        activity?.binding?.relatedVideoWebView?.settings?.allowContentAccess = true
        activity?.binding?.relatedVideoWebView?.settings?.allowFileAccess = true
        activity?.binding?.relatedVideoWebView?.requestFocus()
        activity?.binding?.relatedVideoWebView?.settings?.defaultTextEncodingName = "UTF-8"
        activity?.binding?.relatedVideoWebView?.settings?.cacheMode =
            WebSettings.LOAD_CACHE_ELSE_NETWORK
        activity?.binding?.relatedVideoWebView?.isVerticalScrollBarEnabled = true;
        activity?.binding?.relatedVideoWebView?.isHorizontalScrollBarEnabled = true;
        val arrayOfTitle = title?.split(" ")?.toTypedArray()
        var baseYoutubeUrl = "https://www.youtube.com/results?search_query="
        for (i in arrayOfTitle?.indices!!) {
            arrayOfTitle[i] = arrayOfTitle[i].lowercase()
            Log.d("bldsdssdcs", arrayOfTitle[i])
            baseYoutubeUrl = baseYoutubeUrl + arrayOfTitle[i] + "+"
        }
        activity?.binding?.relatedVideoWebView?.loadUrl(baseYoutubeUrl)
        activity?.binding?.relatedVideoWebView?.webViewClient = WebViewCustomClient(null,null,activity)

    }

    @SuppressLint("SetJavaScriptEnabled")
    fun setWebViews(activity:GettedreceiptActivity,title:String){
        activity?.binding?.relatedVideoWebView?.settings?.javaScriptEnabled = true
        activity?.binding?.relatedVideoWebView?.settings?.databaseEnabled = true
        activity?.binding?.relatedVideoWebView?.settings?.domStorageEnabled = true
        activity?.binding?.relatedVideoWebView?.settings?.useWideViewPort = true
        activity?.binding?.relatedVideoWebView?.settings?.allowContentAccess = true
        activity?.binding?.relatedVideoWebView?.settings?.allowFileAccess = true
        activity?.binding?.relatedVideoWebView?.requestFocus()
        activity?.binding?.relatedVideoWebView?.settings?.defaultTextEncodingName = "UTF-8"
        activity?.binding?.relatedVideoWebView?.settings?.cacheMode =
            WebSettings.LOAD_CACHE_ELSE_NETWORK
        activity?.binding?.relatedVideoWebView?.isVerticalScrollBarEnabled = true;
        activity?.binding?.relatedVideoWebView?.isHorizontalScrollBarEnabled = true;
        val arrayOfTitle = title?.split(" ")?.toTypedArray()
        var baseYoutubeUrl = "https://www.youtube.com/results?search_query="
        for (i in arrayOfTitle?.indices!!) {
            arrayOfTitle[i] = arrayOfTitle[i].lowercase()
            Log.d("bldsdssdcs", arrayOfTitle[i])
            baseYoutubeUrl = baseYoutubeUrl + arrayOfTitle[i] + "+"
        }
        activity?.binding?.relatedVideoWebView?.loadUrl(baseYoutubeUrl)
        activity?.binding?.relatedVideoWebView?.webViewClient = WebViewCustomClient(null,activity,null)

    }
}