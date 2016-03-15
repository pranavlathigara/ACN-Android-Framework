# ACN Android Framework
[![Build Status](https://travis-ci.org/ugurcany/ACN-Android-Framework.svg?branch=master)](https://travis-ci.org/ugurcany/ACN-Android-Framework)
[![Coverage Status](https://coveralls.io/repos/github/ugurcany/ACN-Android-Framework/badge.svg?branch=master)](https://coveralls.io/github/ugurcany/ACN-Android-Framework?branch=master)
[![API](https://img.shields.io/badge/API-15%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=15)
[![MIT license](http://img.shields.io/badge/license-MIT-brightgreen.svg)](http://opensource.org/licenses/MIT)

ACN Android Framework is being developed to reduce application development effort and time by encapsulating the initialization and configuration of many widely-used third-party libraries inside its custom Application and Activity classes.

It also provides custom view classes with extra features added to default views of Android and other third-party views shared on GitHub. See below for more details.

## Table of Contents
1. [Context classes](#context)
    - [AcnApplication](#acnapplication)
    - [AcnActivity](#acnactivity)
2. [View classes](#view)
    - [AcnButton](#acnbutton)
    - [AcnTextView](#acntextview)
    - [AcnImageView](#acnimageview)
    - [AcnViewPager](#acnviewpager)
    - [AcnImageGallery](#acnimagegallery)
    - [AcnImagePager](#acnimagepager)
    - [AcnVideoView](#acnvideoview)
    - [AcnInfiniteListView](#acninfinitelistview)
3. [Util classes](#util)
    - [BusProvider](#busprovider)
    - [ImageClickHandler](#imageclickhandler)
    - [InfiniteListAdapter](#infinitelistadapter)
4. [How to use this framework](#howtouse)
5. [License](#license)


## <a name="context"></a>1. Context classes
The following classes can be found under `context` package:

#### <a name="acnapplication"></a>AcnApplication (abstract class, extends Application)
- Constructor takes the following params:
    - `appFont` (String)
        - e.g., "Gotham-Black.ttf" (located in the directory `assets/fonts/`)
    - `loggerTag` (String)
        - e.g., "LOGGER"
    - `imageFadeInDuration` (int)
        - e.g., 300 (in millis)
- Initializes the following libraries:
    - [Logger](https://github.com/orhanobut/logger)
    - [AndroidBaseUtils](https://github.com/TheFinestArtist/AndroidBaseUtils)
    - [Iconify](https://github.com/JoanZapata/android-iconify) (required to support FontAwesome icons inside **AcnTextView**)
    - [Calligraphy](https://github.com/chrisjenx/Calligraphy)
    - [Universal Image Loader](https://github.com/nostra13/Android-Universal-Image-Loader)
    - [ReactiveNetwork](https://github.com/pwittchen/ReactiveNetwork) (required to observe the Internet connectivity status) 
- Has multidex support

#### <a name="acnactivity"></a>AcnActivity (abstract class, extends AppCompatActivity)
- Constructor takes the following params:
    - `layoutRes` (int) 
        - e.g., `R.layout.activity_main`
    - `toolbarRes` (int)
        - e.g., `R.id.toolbar`
    - `backButtonRes` (Integer -- nullable)
        - e.g., `R.id.back_button`
    - `backButtonVisible` (boolean)
    - `statusBarColor` (int)
        - e.g., `R.color.statusBar`
- Initializes the following libraries:
    - [Butter Knife](http://jakewharton.github.io/butterknife/)
- Registers/unregisters [Otto Event Bus](http://square.github.io/otto/) at `onResume()`/`onPause()`
- Includes abstract method `onInternetStatusChanged(ConnectivityStatus status)`
    - While overriding, add `@Subscribe` before the method



## <a name="view"></a>2. View classes
The following classes can be found under `view` package:

#### <a name="acnbutton"></a>AcnButton (extends FancyButton)
- Can do anything that [FancyButton](https://github.com/medyo/Fancybuttons) does

#### <a name="acntextview"></a>AcnTextView (extends IconTextView)
- Can do anything that [IconTextView](https://github.com/JoanZapata/android-iconify) does
- Allows to use [FontAwesome icons](http://fortawesome.github.io/Font-Awesome/icons/) inside text
    - `android:text="Hello world {fa-globe}"`

#### <a name="acnimageview"></a>AcnImageView (extends FrameLayout)
- Includes the following UI components:
    - ImageView
    - [LoadingView](https://github.com/zzz40500/android-shapeLoadingView) (default loading animation)
    - [GifImageView](https://github.com/koral--/android-gif-drawable) (custom loading animation)
- Supports loading image from URL, drawable, and assets
    - `acn_imageview.setImageFromURL("http://goo.gl/T59s3M", zoomable);`
    - `acn_imageview.setImageFromDrawable(R.drawable.image, zoomable);`
    - `acn_imageview.setImageFromAssets("image.jpg", zoomable);` (located in the directory `assets/`)
- Supports zoomable images
    - Set the second param of above methods to `true`
- Supports custom GIF image for loading animation
    - Set the following attributes in XML:
        - `app:gifSrc="loading.gif"` (located in the directory `assets/`)
        - `app:gifSize="80dp"`
- Custom XML attributes:
    - `scaleType` (enum)
        - `fitCenter` (default)
        - `centerCrop`
        - `fitXY`
    - `gifSrc` (string)
    - `gifSize` (dimension)
    
#### <a name="acnviewpager"></a>AcnViewPager (extends LinearLayout)
- Includes the following UI components:
    - [SlidingTabLayout](http://developer.android.com/samples/SlidingTabsBasic/src/com.example.android.common/view/SlidingTabLayout.html) & [SlidingTabStrip](http://developer.android.com/samples/SlidingTabsBasic/src/com.example.android.common/view/SlidingTabStrip.html) (customized versions of them)
    - ViewPager
- Set fragments and tab titles with one line of code:
    - `acn_viewpager.setContent(fragments, tabTitles);`
- Supports using [FontAwesome icons](http://fortawesome.github.io/Font-Awesome/icons/) inside tab titles
- Custom XML attributes:
    - `indicatorColor` (color)
    - `dividerColor` (color)
    - `selectedTitleColor` (color)
    - `unselectedTitleColor` (color)
    - `tabBackgroundColor` (color)
    - `tabTitleSize` (dimension)
- [Screenshot](/screenshots/AcnViewPager.jpg)

#### <a name="acnimagegallery"></a>AcnImageGallery (extends LinearLayout)
- Includes two or three **AcnImageView** components at each row, depending on `columnType` attribute
    - Each image has an aspect ratio of 3:2 if `columnType` is set to `pair`
    - Each image has an aspect ratio of 1:1 if `columnType` is set to `triplet`
    - No limit for number of rows
- Supports loading images from URL list
    - `acn_imagegallery.setImagesFromURLList(imageURLs);`
- Set **ImageClickHandler** to handle image clicks
    - `acn_imagegallery.setImageClickHandler(new ImageClickHandler() { ... });`
- Custom XML attributes:
    - `spacing` (dimension)
    - `columnType` (enum)
        - `pair` (default)
        - `triplet`
- [Screenshot](/screenshots/AcnImageGallery.jpg)
        
#### <a name="acnimagepager"></a>AcnImagePager (extends RelativeLayout)
- Includes the following UI components:
    - [SliderLayout](https://github.com/daimajia/AndroidImageSlider) (Has no functionality -- just to be able to use PagerIndicator)
    - [HackyViewPager](https://github.com/chrisbanes/PhotoView/blob/master/sample/src/main/java/uk/co/senab/photoview/sample/HackyViewPager.java)
    - [PagerIndicator](https://github.com/daimajia/AndroidImageSlider)
- Supports loading images from URL list
    - `acn_imagepager.setImagesFromURLList(imageURLs, zoomable);`
- Supports zoomable images
    - Set the second param of above method to `true`
- Custom XML attributes:
    - `selectedIndicatorColor` (color)
    - `unselectedIndicatorColor` (color)
    - `selectedIndicatorSize` (dimension)
    - `unselectedIndicatorSize` (dimension)
    - `indicatorBottomMargin` (dimension)
- [Screenshot](/screenshots/AcnImagePager.jpg)

#### <a name="acnvideoview"></a>AcnVideoView (extends JCVideoPlayer)
- Can do anything that [Jiecao Video Player](https://github.com/lipangit/jiecaovideoplayer) does
- Supports loading video & thumbnail image from URL and configuring video title
    - `acn_videoview.setVideo(videoURL, thumbnailURL, videoTitle, showTitle);`
- You need to call `AcnVideoView.releaseAllVideos();` at `onPause()` of your Activity or Fragment
- [Screenshot](/screenshots/AcnVideoView.jpg)

#### <a name="acninfinitelistview"></a>AcnInfiniteListView (extends ListView)
- Initialize it as follows:
    - `acn_infinitelistview.init(adapter, maxNumOfItems, loadingView);`
        - `adapter` (**InfiniteListAdapter**)
            - Extend it to create your own adapter
                - Override its `onNewLoadRequired()` method to load new items when required
        - `maxNumOfItems` (int)
            - To make listview prevent loading more when item count reaches this number
        - `loadingView` (View)
            - Footer view to be displayed while loading new items
- Add new item to list as follows:
    - `acn_infinitelistview.addNewItem(item);`
- [Screenshot](/screenshots/AcnInfiniteListView.jpg)



## <a name="util"></a>3. Util classes
The following classes can be found under `util` package:

#### <a name="busprovider"></a>BusProvider
- Includes static instance of [Otto Event Bus](http://square.github.io/otto/)
    - Use this class to register/unregister [Otto Event Bus](http://square.github.io/otto/)
        - `BusProvider.getInstance().register(this);`
        - `BusProvider.getInstance().unregister(this);`

#### <a name="imageclickhandler"></a>ImageClickHandler (abstract class)
- Includes abstract method `onImageClicked(int position, String imageURL)`

#### <a name="infinitelistadapter"></a>InfiniteListAdapter (abstract class, extends ArrayAdapter)
- Constructor takes the following params:
    - `activity` (Activity)
    - `layoutRes` (int)
        - e.g., `R.layout.item_text`
    - `itemList` (ArrayList)
- Includes abstract method `onNewLoadRequired()`



## <a name="howtouse"></a>4. How to use this framework
1. Add `acn-android-framework` module to your project
2. Inside module-level `build.gradle` file (that belongs to your module), add the following line under `dependencies` tag:
    - `compile project(':acn-android-framework')`
3. Inside project-level `build.gradle` file...
    - add the following line under `allprojects/repositories` tag:
        - `maven { url "https://jitpack.io" }`
    - add the following line under `buildscript/dependencies` tag:
        - `classpath 'com.neenbedankt.gradle.plugins:android-apt:1.8'`
4. Inside `AndroidManifest.xml` file of your module, add the following lines under `Application` tag:
    - `tools:replace="theme, label"`
    - `android:theme="@style/AcnTheme"`

See `test` module for sample usage.



## <a name="license"></a>5. License
```
The MIT License (MIT)

Copyright (c) 2016 Ugurcan Yildirim

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
