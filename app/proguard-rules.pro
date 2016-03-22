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
-dontwarn android.support.v4.**
-dontwarn  com.google.common.**

#Keep the annotated things annotated
-keepattributes *Annotation*
-keep class javax.inject.** { *; }

-keep class com.liuguangqiang.di.** { *; }
-keep class com.liuguangqiang.ui.widget.** { *; }
-keep class com.liuguangqiang.domain.** { *; }

-keep class com.viewpagerindicator.** { *; }
-keep class com.android.okhttp.** { *; }
-keep class com.makeramen.** { *; }

#ButterKnife
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }

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

#EventBus
-keep class de.greenrobot.** { *; }

-keepclassmembers class ** {
    public void onEvent*(**);
}

# Only required if you use AsyncExecutor
-keepclassmembers class * extends de.greenrobot.event.util.ThrowableFailureEvent {
    <init>(java.lang.Throwable);
}

#DataBinding
-dontwarn android.databinding.**
-keep class android.databinding.** { *; }
-keep class * extends android.databinding.ViewDataBinding
-keep class com.liuguangqiang.idaily.databinding.** { *; }

-keep class android.support.design.** { *; }

#Retrofit
-dontwarn retrofit.**
-keep class retrofit.** { *; }
-keepattributes Signature
-keepattributes Exceptions

###### dagger 2.0
-keep class dagger.** { *; }
-keep interface dagger.** { *; }

-keep class **$$ModuleAdapter { *; }
-keepnames class **$$InjectAdapter { *; }

-keepclassmembers class * {
    @javax.inject.Inject <fields>;
    @javax.inject.Inject <init>(...);
}
-adaptclassstrings

#Glide
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
    **[] $VALUES;
    public *;
}