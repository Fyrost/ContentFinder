package com.example.contentfinder.Controller

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.contentfinder.R
import kotlinx.android.synthetic.main.description_content.*
import kotlinx.android.synthetic.main.description_header.*

class DescriptionActivity : AppCompatActivity() {
    var hashMap: HashMap<String, String> = hashMapOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.description_fragment)
        hashMap.put("song", "Music")
        hashMap.put("music-video", "Music Video")
        hashMap.put("feature-movie", "Movie")
        hashMap.put("null", "Audiobook")
        hashMap.put("tv-episode", "TV Show")

        Glide.with(this)
            .load(intent.getStringExtra("trackImg").replace("100x100bb.jpg", "400x400bb.jpg"))
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

        println(intent.getStringExtra("trackCategory"))

        title_description.text = intent.getStringExtra("trackName")
        advisory_textView.text = hashMap[intent.getStringExtra("trackCategory")]
        genre_textView.text = intent.getStringExtra("trackGenre")
        price_textView.text = "$ " + intent.getStringExtra("trackPrice")
        year_textView.text = intent.getStringExtra("trackYear").take(4)
        artist_textView.text = intent.getStringExtra("trackArtist")
        description_textView.text = intent.getStringExtra("trackDescription")


        close_button_description.setOnClickListener { finish() }
    }
}