# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/hoseinraeisi/Library/Android/sdk/tools/proguard/proguard-android.txt
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

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
-dontwarn okio.**
-dontwarn javax.annotation.**
-dontwarn javax.inject.**
-dontwarn sun.misc.Unsafe
-dontwarn java.nio.**
-dontwarn javax.lang.**
-dontwarn javax.tools.**
-dontwarn com.squareup.javapoet.**
-dontwarn com.google.**
-dontnote com.google.**
-keep class org.apache.http.** { *; }
-keep class retrofit.** { *; }
-dontwarn com.squareup.picasso.**
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
    **[] $VALUES;
    public *;
}
-dontwarn retrofit2.Platform$Java8
-keepattributes Signature
-keepattributes *Annotation*
-keep class com.google.gson.stream.** { *; }
-keep class com.holding.medis.models.** { *; }
-keep class com.google.gson.** { *; }
-keepclasseswithmembernames class * {
    native <methods>;
}