Hansel And Gretel
=================

Android library providing bread crumbs for compatibility fragments.



Usage
=====

*For a working implementation of this project see the `sample/` folder.*

 1. Include the widget in your view. This should usually be placed directly
    above the container in which the associated fragments will be displayed.
    
        <android.support.v4.app.FragmentBreadCrumbs
            android:id="@+id/breadcrumbs"
            android:layout_width="fill_parent"
            android:layout_height="40dp" />
    
 2. In your `onCreate` method, bind the widget to the activity.
    
        FragmentBreadCrumbs crumbs = (FragmentBreadCrumbs)findViewById(R.id.breadcrumbs);
        crumbs.setActivity(this);
    
 3. Add the required style attributes to your theme.
    
        <item name="hagDividerVertical">@drawable/hag__divider_dark</item>
        <item name="hagSelectableItemBackground">@drawable/hag__background_dark</item>
    
    or
    
        <item name="hagDividerVertical">@drawable/hag__divider_dark</item>
        <item name="hagSelectableItemBackground">@drawable/hag__background_dark</item>
    
    or specify your own drawables for the divider and background.
    
 4. *(Optional)* Set the title of the initial view. This can be useful when the
    initial activity view has a default fragment.
    
        //continued from above
        crumbs.setTitle("Settings", null);


Including In Your Project
-------------------------

The HanselAndGretel library is presented as an Android library project. A
standalone JAR is not possible due to the theming capabilities offered by the
widget.

You can include this project by [referencing it as a library project[1] in
Eclipse or ant.

If you are a Maven user you can easily include the library by specifying it as
a dependency:

    <dependency>
      <groupId>com.jakewharton</groupId>
      <artifactId>hanselandgretel</artifactId>
      <version>1.0.2</version>
      <type>apklib</type>
    </dependency>

This project depends on the `Fragment` classes which are available in the
[Android Support Library][2].



Developed By
============

* Jake Wharton - <jakewharton@gmail.com>



License
=======

    Copyright 2012 Jake Wharton

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.



 [1]: http://developer.android.com/guide/developing/projects/projects-eclipse.html#ReferencingLibraryProject
 [2]: http://developer.android.com/sdk/compatibility-library.html
