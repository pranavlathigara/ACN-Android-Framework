# ACN Android Framework
ACN Android Framework is being developed to reduce application development effort and time by encapsulating the initialization and configuration of many widely-used third-party libraries inside its custom Application and Activity classes.

It also provides custom view classes with extra features added to default views of Android. See below for more details.

## Table of Contents
1. [Context classes](#context)
    - [AcnApplication](#acnapplication)
    - [AcnActivity](#acnactivity)
2. [View classes](#view)
    - [AcnButton](#acnbutton)
    - [AcnTextView](#acntextview)
    - [AcnImageView](#acnimageview)
    - [AcnImageGallery](#acnimagegallery)
    - [AcnImagePager](#acnimagepager)
3. [Util classes](#util)
    - [BusProvider](#busprovider)
    - [ImageClickHandler](#imageclickhandler)
4. [How to use this framework](#howtouse)



## <a name="context"></a>Context classes
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
    - [EasyPreferences](https://github.com/Pixplicity/EasyPreferences)
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
    - `statusBarColor` (int)
        - e.g., `R.color.statusBar`
- Initializes the following libraries:
    - [Butter Knife](http://jakewharton.github.io/butterknife/)
- Registers/unregisters [Otto Event Bus](http://square.github.io/otto/) at `onResume()`/`onPause()`
- Includes abstract method `onInternetStatusChanged(ConnectivityStatus status)`
    - While overriding, add `@Subscribe` before the method



## <a name="view"></a>View classes
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
    - [LoadingView](https://github.com/zzz40500/android-shapeLoadingView)
    - [GifImageView](https://github.com/koral--/android-gif-drawable)
- Supports loading image from URL, drawable, and assets
    - `acn_imageview.setImageFromURL("http://goo.gl/T59s3M", false);`
    - `acn_imageview.setImageFromDrawable(R.drawable.image, false);`
    - `acn_imageview.setImageFromAssets("image.jpg", false);`
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
        
#### <a name="acnimagepager"></a>AcnImagePager (extends RelativeLayout)
- Includes the following UI components:
    - [SliderLayout](https://github.com/daimajia/AndroidImageSlider) (Has no functionality -- just to be able to use PagerIndicator)
    - [HackyViewPager](https://github.com/chrisbanes/PhotoView/blob/master/sample/src/main/java/uk/co/senab/photoview/sample/HackyViewPager.java)
    - [PagerIndicator](https://github.com/daimajia/AndroidImageSlider)
- Supports loading images from URL list
    - `acn_imagepager.setImagesFromURLList(imageURLs, false);`
- Supports zoomable images
    - Set the second param of above method to `true`
- Custom XML attributes:
    - `selectedIndicatorColor` (color)
    - `unselectedIndicatorColor` (color)
    - `selectedIndicatorSize` (dimension)
    - `unselectedIndicatorSize` (dimension)



## <a name="util"></a>Util classes
The following classes can be found under `util` package:

#### <a name="busprovider"></a>BusProvider
- Includes static instance of [Otto Event Bus](http://square.github.io/otto/)
    - Use this class to register/unregister [Otto Event Bus](http://square.github.io/otto/)
        - `BusProvider.getInstance().register(this);`
        - `BusProvider.getInstance().unregister(this);`

#### <a name="imageclickhandler"></a>ImageClickHandler (abstract class)
- Includes abstract method `onImageClicked(int position, String imageURL)`



## <a name="howtouse"></a>How to use this framework
1. Add `acnandroid` module to your project
2. Inside module-level `build.gradle` file of your module, add the following line under `dependencies` tag:
    - `compile project(':acnandroid')`
3. Inside project-level `build.gradle` file, add the following line under `allprojects/repositories` tag:
    - `maven { url "https://jitpack.io" }`
4. Inside `AndroidManifest.xml` file of your module, add the following lines under `Application` tag:
    - `tools:replace="theme, label"`
    - `android:theme="@style/AcnTheme"`

See `test` module for sample usage.
