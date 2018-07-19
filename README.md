# PinBallLoadingView
A pin ball loading view(一个弹球球loading view)。


[![Api reqeust](https://img.shields.io/badge/api-1+-green.svg)](https://github.com/samlss/PinBallLoadingView)  [![MIT Licence](https://badges.frapsoft.com/os/mit/mit.svg?v=103)](https://github.com/samlss/PinBallLoadingView/blob/master/LICENSE) [![Blog](https://img.shields.io/badge/samlss-blog-orange.svg)](https://blog.csdn.net/Samlss)

<br>

  * [中文](#%E4%B8%AD%E6%96%87)
  * [English](#english)
  * [License](#license)

<br>

![gif1](https://github.com/samlss/PinBallLoadingView/blob/master/screenshots/screenshot1.gif)



## 中文

### 使用<br>
在根目录的build.gradle添加这一句代码：
```
allprojects {
    repositories {
        //...
        maven { url 'https://jitpack.io' }
    }
}
```

在app目录下的build.gradle添加依赖使用：
```
dependencies {
    implementation 'com.github.samlss:PinBallLoadingView:1.0'
}
```

布局中使用：
```
<com.iigo.library.PinBallLoadingView
                android:id="@+id/pblv_loading1"
                android:layout_width="100dp"
                android:layout_height="100dp"
                app:moving_circle_color="#ff669900"
                app:moving_speed_ratio="8.8"
                app:moving_circle_radius="20"
                app:outer_circle_stoke_color="#ffff8800"
                app:outer_circle_stoke_width="20" />
```

<br>

代码中使用：
```
  pinBallLoadingView.start(); //开始动画
  pinBallLoadingView.stop(); //结束动画
  pinBallLoadingView.setMovingCircleColor(Color.parseColor("#ff669900")); //设置可移动圆的颜色
  pinBallLoadingView.setOuterCircleStrokeColor(Color.parseColor("#ffff8800")); //设置外圆描边颜色
```

<br>

属性说明：

| 属性        | 说明           |
| ------------- |:-------------:|
| moving_circle_radius      | 可移动小球的半径，默认为10 |
| moving_circle_color | 可移动圆的颜色，默认为红色 |
| moving_speed_ratio      | 速度比例，值越大移动越快，默认为5|
| outer_circle_stoke_color      | 外圆描边颜色，默认为白色|
| outer_circle_stoke_width      | 外圈的绘边大小，默认为5 |

<br>

如果不能满足你的需要，你可以下载源码自行修改。

## English

### Use<br>
Add it in your root build.gradle at the end of repositories：
```
allprojects {
    repositories {
        //...
        maven { url 'https://jitpack.io' }
    }
}
```

Add it in your app build.gradle at the end of repositories:
```
dependencies {
    implementation 'com.github.samlss:PinBallLoadingView:1.0'
}
```


in layout.xml：
```
<com.iigo.library.PinBallLoadingView
                android:id="@+id/pblv_loading1"
                android:layout_width="100dp"
                android:layout_height="100dp"
                app:moving_circle_color="#ff669900"
                app:moving_speed_ratio="8.8"
                app:moving_circle_radius="20"
                app:outer_circle_stoke_color="#ffff8800"
                app:outer_circle_stoke_width="20" />
```

<br>

in java code：
```
  pinBallLoadingView.start(); //start animation
  pinBallLoadingView.stop(); //stop animation
  pinBallLoadingView.setMovingCircleColor(Color.parseColor("#ff669900")); //set the color of the moving circle
  pinBallLoadingView.setOuterCircleStrokeColor(Color.parseColor("#ffff8800")); //set the stroke color of the outer circle
```

<br>

Attributes description：

| attr        | description  |
| ------------- |:-------------:|
| moving_circle_radius      | the moving circle radius, the default is 10 |
| moving_circle_color | The color of the moving circle, the default is red |
| moving_speed_ratio      | speed ratio, the bigger the value, the faster the speed, the default is 5|
| outer_circle_stoke_color      | The color of the outer circle stroke, the default is white|
| outer_circle_stoke_width      | Outer circle width, the default is 5 |

If you can not meet your needs, you can download the source code to modify it.

[id]: http://example.com/ "Optional Title Here"

## [LICENSE](https://github.com/samlss/PinBallLoadingView/blob/master/LICENSE)
