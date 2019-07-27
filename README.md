# Bottom Bar Pager

An android Library used to easily implement a bottom app bar with title and animated vector drawable and set it up with a view pager with three fragments.

<img src="/images/screenshoot.gif" width="250" />

---

### Download using Gradle

Add this in your root `build.gradle` at the end of `repositories` in `allprojects` section:
```groovy
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
        ...
    }
}
```

Then add this dependency to your **module-level** `build.gradle` in `dependencies` section:
```groovy
implementation 'com.github.OmarRaed:BottomBarPager-Android:1.0.0
```

---

## How To Use

Add view to your layout:
```xml
    <com.omaar.bottombarpager.BottomBarPager
        android:id="@+id/tabs"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:layout_alignParentBottom="true"
        app:selected_tab_background="@color/darker_gray"
        app:selected_text_color="@color/colorPrimary"
        app:bar_background="@color/light_gray"
        app:text_color="@color/blackColor"/>
```

Then add it to java code :

First create a DataHolder object which will hold :
- The three animated vectors
- The three fragments
- The three titles (optional)


```java
//Initializeing three fragments
FragOne f1 = new FragOne() ;
FragTwo f2 = new FragTwo() ;
FragThree f3 = new FragThree() ;

//Initializing three animated vectors
AnimatedVectorDrawable v1 = (AnimatedVectorDrawable) getResources().getDrawable(R.drawable.places_animated_icon) ;
AnimatedVectorDrawable v2 = (AnimatedVectorDrawable) getResources().getDrawable(R.drawable.metro_animated_icon) ;
AnimatedVectorDrawable v3 = (AnimatedVectorDrawable) getResources().getDrawable(R.drawable.metro_animated_icon) ;

//create a new data holder object
BottomBarPagerDataHolder dataHolder = new BottomBarPagerDataHolder() ;

//set the fragments
dataHolder.setFragment1(f1);
dataHolder.setFragment2(f2);
dataHolder.setFragment3(f3);

//set the animated vectors
dataHolder.setTab1_vector(v1);
dataHolder.setTab2_vector(v2);
dataHolder.setTab3_vector(v3);

//set the titles (optional)
dataHolder.setTab1_title("First Title");
dataHolder.setTab2_title("Second Title");
```

Then find the view and initialize it with the data holder object
```
BottomBarPager bottomBarPager = findViewById(R.id.tabs) ;
bottomBarPager.initializeAppBar(dataHolder);
```
---
### The Colors :

<img src="/images/colors.png" width="500" />

Default colors :
- app:selected_tab_background -> #CCCCCC
- app:selected_text_color -> #FFFFFF
- app:bar_background -> #EEEEEE
- app:text_color -> #000000
---

### Limitations

This Library requires api level 23 or more (Android Marshmallow) .
This Library is now limited to only three tabs.

---


### License

```
 Copyright 2019 Omar Raed

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
```

