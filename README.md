# ACN Android Framework
ACN Android Framework is being developed to reduce application development effort and time by encapsulating the initialization and configuration of many widely-used third-party libraries inside its custom Application and Activity classes.

It also provides custom view classes with extra features added to default views of Android. See below for more details.



## Context classes
The following classes can be found under `context` package:

#### AcnApplication (abstract class, extends Application)
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
    - [Butter Knife](http://jakewharton.github.io/butterknife/)
- Registers/unregisters [Otto Event Bus](http://square.github.io/otto/) at `onResume()`/`onPause()`
- Includes abstract method `internetCatcher(ConnectivityStatus status)`
    - While overriding, add `@Subscribe` before the method



## View classes
The following classes can be found under `view` package:

#### AcnButton (extends FancyButton)
- Can do anything that [FancyButton](https://github.com/medyo/Fancybuttons) does

#### AcnTextView (extends IconTextView)
- Can do anything that [IconTextView](https://github.com/JoanZapata/android-iconify) does
- Allows to use [FontAwesome icons](http://fortawesome.github.io/Font-Awesome/icons/) inside text
    - `android:text="Hello world {fa-globe}"`

#### AcnImageView (extends FrameLayout)
- Includes the following UI components:
    - ImageView
    - [LoadingView](https://github.com/zzz40500/android-shapeLoadingView)
- Supports loading image from URL, drawable, and assets
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

#### AcnImageGallery (extends LinearLayout)
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
        - `pair`
        - `triplet`



## Util classes
The following classes can be found under `util` package:

#### BusProvider
- Includes static instance of [Otto Event Bus](http://square.github.io/otto/)
    - Use this class to register/unregister [Otto Event Bus](http://square.github.io/otto/)
        - `BusProvider.getInstance().register(this);`
        - `BusProvider.getInstance().unregister(this);`

#### ImageClickHandler (abstract class)
- Includes abstract method `onImageClicked(int position, String imageURL)`



## How to use this framework
1. Add `acnandroid` module to your project
2. Inside module-level `build.gradle` file of your module, add the following line under `dependencies` tag:
    - `compile project(':acnandroid')`
3. Inside project-level `build.gradle` file, add the following line under `allprojects/repositories` tag:
    - `maven { url "https://jitpack.io" }`
4. Inside `AndroidManifest.xml` file of your module, add the following lines under `Application` tag:
    - `tools:replace="theme, label"`
    - `android:theme="@style/AcnTheme"`

See `test` module for sample usage.
