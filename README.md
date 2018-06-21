# FitLoop
Google Fit inspired Circular Progress bar

A progress drawable that uses an imageview to draw the progress rings.

[![Screenshot_20180621-163114.png](https://s22.postimg.cc/6ys003vip/Screenshot_20180621-163114.png)](https://postimg.cc/image/r65fseszx/)

Its simple to use.

1. Create imageview in your layout

          <in.cyberwalker.loop.CircularProgressImageView
             android:layout_width="256dp"
             android:layout_height="256dp"
             android:id="@+id/cpimv"
             app:layout_constraintBottom_toBottomOf="parent"
             app:layout_constraintLeft_toLeftOf="parent"
             app:layout_constraintRight_toRightOf="parent"
             app:layout_constraintTop_toTopOf="parent" />


2. Craete Data set

        CircleProgressDataSet dataset = new CircleProgressDataSet(10  // primaryProgress
                , 20 // secondaryProgress
                , getResources().getColor(R.color.colorAccent) // primaryProgressColor
                , getResources().getColor(R.color.colorPrimary) // secondaryProgressColor
                , getResources().getColor(R.color.ltGray) // Ring Color
        );
        
3. set Data set to imageview

        CircularProgressImageView circularProgressImageView = findViewById(R.id.cpimv);
        
        circularProgressImageView
              .setDataSet(dataset, animate /* pass true if you want it to draw with animation else false*/)
              .build();
              
 And you are done!
 
 Check the drawable class for other supports
 

          Copyright (c) 2018 cyb3rWalk3r Studios

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

