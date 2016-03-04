# AcnFramework – Android Framework
AcnFramework is an Android framework developed to reduce the development effort by performing the initialization of many widely-used libraries inside its custom Application and Activity classes.

It also provides several view classes with extra features added to default views of Android. See below for more details.



## Context classes
The following classes can be found under `context` package:

#### AcnApplication (abstract class, extends Application)
- Constructor takes the following params:
    - `defaultFont` (String) 
        - e.g., "Gotham-Black.ttf" (located in the directory `assets/fonts/`)
    - `loggerTag` (String)
        - e.g., "LOGGER"
- Initializes the following libraries:
    - [Logger](https://github.com/orhanobut/logger)
    - [EasyPreferences](https://github.com/Pixplicity/EasyPreferences)
    - [Iconify](https://github.com/JoanZapata/android-iconify) (required to support FontAwesome icons inside AcnTextView)
    - [Calligraphy](https://github.com/chrisjenx/Calligraphy)
    - [Universal Image Loader](https://github.com/nostra13/Android-Universal-Image-Loader)
    - [ReactiveNetwork](https://github.com/pwittchen/ReactiveNetwork) (required to observe the Internet connectivity status) 
- Has multidex support

#### AcnActivity (abstract class, extends AppCompatActivity)
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
    - [ButterKnife](http://jakewharton.github.io/butterknife/)
- Registers/unregisters [Otto Event Bus](http://square.github.io/otto/) at `onResume()`/`onPause()`
- Includes abstract method `internetCatcher(ConnectivityStatus)`
    - While overriding, add `@Subscribe` before the method



## View classes
The following classes can be found under `view` package:

#### AcnButton (extends FancyButton)
- Can do anything that [FancyButton](https://github.com/medyo/Fancybuttons) does

#### AcnTextView (extends IconTextView)
- Allows to use [FontAwesome icons](http://fortawesome.github.io/Font-Awesome/icons/) inside text
    - `android:text="Hello world {fa-globe}"`
- Can do anything that [IconTextView](https://github.com/JoanZapata/android-iconify) does

#### AcnImageView (extends FrameLayout)
- Includes the following UI components:
    - ImageView
    - [DilatingDotsProgressBar](https://github.com/JustZak/DilatingDotsProgressBar)
- Supports loading image from URL, drawable, and assets
    - While loading, it shows dots dilating
        - `acn_imageview.setImageFromURL("http://goo.gl/T59s3M", false);`
        - `acn_imageview.setImageFromDrawable(R.drawable.image, false);`
        - `acn_imageview.setImageFromAssets("image.jpg", false);`
- Supports zoomable images
    - Set the second param of above methods to `true`
- Custom XML attributes:
    - `scaleType` (enum)
        - `fitCenter`
        - `centerCrop`
        - `fitXY`
    - `dotColor` (color)
    - `dotCount` (integer)
    - `dotRadius` (dimension)



## Util classes
The following classes can be found under `util` package:

#### BusProvider
- Includes static instance of [Otto Event Bus](http://square.github.io/otto/)
    - Use this class to register/unregister [Otto Event Bus](http://square.github.io/otto/)
        - `BusProvider.getInstance().register(this);`
        - `BusProvider.getInstance().unregister(this);`



## Other notes
- Inside module level `gradle` file, add the following line under `dependencies` tag:
    - `compile project(':acnframework')`
- Inside project level `gradle` file, add the following line under `allprojects/repositories` tag:
    - `maven { url "https://jitpack.io" }`
- Inside `AndroidManifest.xml`, add the following lines under `Application` tag:
    - `tools:replace="android:theme, android:name"`
    - `android:theme="@style/AcnTheme"`

