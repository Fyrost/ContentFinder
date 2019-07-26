package com.example.contentfinder.Controller

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.room.Room
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.contentfinder.Entity.ResultDatabase
import com.example.contentfinder.R
import io.reactivex.Completable
import kotlinx.android.synthetic.main.description_content.*
import kotlinx.android.synthetic.main.description_fragment.*
import kotlinx.android.synthetic.main.description_header.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DescriptionActivity : AppCompatActivity() {
    var hashMap: HashMap<String, String> = hashMapOf()
    lateinit var db : ResultDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.description_fragment)
        hashMap.put("song", "Music")
        hashMap.put("music-video", "Music Video")
        hashMap.put("feature-movie", "Movie")
        hashMap.put("audiobook", "Audiobook")
        hashMap.put("tv-episode", "TV Show")

        var kind = intent.getStringExtra("trackCategory")
        var trackId = intent.getIntExtra("trackId", 0)
        var trackName = intent.getStringExtra("trackName")
        var trackCategory = hashMap[kind]
        var trackGenre = intent.getStringExtra("trackGenre")
        var trackPrice = "$ " + intent.getStringExtra("trackPrice")
        var trackYear = intent.getStringExtra("trackYear").take(4)
        var trackArtist = intent.getStringExtra("trackArtist")
        var trackDescription = intent.getStringExtra("trackDescription")
        var trackImg = intent.getStringExtra("trackImg").replace("100x100bb.jpg", "400x400bb.jpg")

        Glide.with(this)
            .load(trackImg)
            .listener(object: RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    progress_bar_description.visibility =  View.INVISIBLE
                    image_description.visibility =  View.VISIBLE
                    image_gradient_description.visibility = View.VISIBLE
                    return true
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    progress_bar_description.visibility =  View.INVISIBLE
                    image_description.visibility =  View.VISIBLE
                    image_gradient_description.visibility = View.VISIBLE
                    image_description.setImageDrawable(resource)
                    return true
                }
            })
            .into(image_description)

        db = Room.databaseBuilder(
            applicationContext,
            ResultDatabase::class.java, "results.db"
        ).build()

        GlobalScope.launch {
            var resultDuplicate = db.resultDao().resultExists(trackId)
            if (resultDuplicate == 1) {
                fab.setImageDrawable(resources.getDrawable(R.drawable.ic_favorite_border))
            }
            fab.setOnClickListener {
                GlobalScope.launch {
                    resultDuplicate = db.resultDao().resultExists(trackId)
                    if (resultDuplicate == 0) {
                        db.resultDao().insertResult(trackId, trackName, trackImg, trackPrice, trackGenre, trackDescription, trackArtist, kind, trackYear)
                        fab.setImageDrawable(resources.getDrawable(R.drawable.ic_favorite_border))
                    } else {
                        db.resultDao().deleteResult(trackId)
                        fab.setImageDrawable(resources.getDrawable(R.drawable.ic_favorite))
                    }
                }
            }
        }

        title_description.text = trackName
        advisory_textView.text = trackCategory
        genre_textView.text = trackGenre
        price_textView.text = trackPrice
        year_textView.text = trackYear
        artist_textView.text = trackArtist
        description_textView.text = trackDescription


        close_button_description.setOnClickListener { finish() }
    }
}