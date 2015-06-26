# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/Eric/tools/Android/android-sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

-ignorewarnings

-keepattributes Signature

-keep class sun.misc.Unsafe { *; }

-dontwarn android.net.http.**

-keep class com.google.** { *; }

-keep class com.google.gson.** { *; }

#ButterKnife
-keep class butterknife.** { *; }

-dontwarn butterknife.internal.**

-keep class **$$ViewInjector { *; }

-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}

-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}

#LoganSquare
-keep class com.bluelinelabs.logansquare.** { *; }
-keep @com.bluelinelabs.logansquare.annotation.JsonObject class *
-keep class **$$JsonObjectMapper { *; }

#DataBinding
-dontwarn android.databinding.**
-keep class * extends android.databinding.ViewDataBinding
-keep class com.liuguangqiang.idaily.databinding.** { *; }

-keep class android.support.design.** { *; }