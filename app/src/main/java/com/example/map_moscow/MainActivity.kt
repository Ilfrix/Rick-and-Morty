package com.example.map_moscow

import android.R
import android.app.Activity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Transformations.map
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.mapview.MapView

/*
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
*/

class MapActivity : Activity() {
    /**
     * Замените "your_api_key" валидным API-ключом.
     * Ключ можно получить на сайте https://developer.tech.yandex.ru/
     */
    private val MAPKIT_API_KEY = "your_api_key"
    //private val TARGET_LOCATION: Point = Point(59.945933, 30.320045)
    private var mapView: MapView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        /**
         * Задайте API-ключ перед инициализацией MapKitFactory.
         * Рекомендуется устанавливать ключ в методе Application.onCreate(),
         * но в данном примере он устанавливается в Activity.
         */
        MapKitFactory.setApiKey(MAPKIT_API_KEY)
        /**
         * Инициализация библиотеки для загрузки необходимых нативных библиотек.
         * Рекомендуется инициализировать библиотеку MapKit в методе Activity.onCreate()
         * Инициализация в методе Application.onCreate() может привести к лишним вызовам и увеличенному использованию батареи.
         */
        MapKitFactory.initialize(this)
        // Создание MapView.
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /*
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        */
        mapView = findViewById<View>(R.id.mapview) as MapView
        /*
        // Перемещение камеры в центр Санкт-Петербурга.
        mapView!!.map.move(
            CameraPosition(TARGET_LOCATION, 14.0f, 0.0f, 0.0f),
            Animation(Animation.Type.SMOOTH, 5),
            null
        )
        */
    }

    override fun onStop() {
        // Вызов onStop нужно передавать инстансам MapView и MapKit.
        mapView!!.onStop()
        MapKitFactory.getInstance().onStop()
        super.onStop()
    }

    override fun onStart() {
        // Вызов onStart нужно передавать инстансам MapView и MapKit.
        super.onStart()
        MapKitFactory.getInstance().onStart()
        mapView!!.onStart()
    }
}