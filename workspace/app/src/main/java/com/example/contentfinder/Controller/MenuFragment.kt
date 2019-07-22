package com.example.contentfinder.Controller

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.ImageButton
import android.widget.TextView
import com.example.contentfinder.R

class MenuFragment : DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.FullScreenDialogStyle)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView: View = inflater.inflate(R.layout.dialog_menu,container,false)
        val closeButton = rootView.findViewById<ImageButton>(R.id.close_button_menu)
        val browseMenu = rootView.findViewById<TextView>(R.id.browse_text_menu)
        val favoriteMenu = rootView.findViewById<TextView>(R.id.favorite_text_menu)

        closeButton.setOnClickListener(object :View.OnClickListener
        {
            override fun onClick(v: View?) {
                dismiss()
            }
        })

        browseMenu.setOnClickListener(object :View.OnClickListener
        {
            override fun onClick(v: View?) {
                val browseIntent = Intent(v?.context,MainActivity::class.java)
                startActivity(browseIntent)
            }
        })

        favoriteMenu.setOnClickListener(object :View.OnClickListener
        {
            override fun onClick(v: View?) {
                val favoriteIntent = Intent(v?.context,FavoriteActivity::class.java)
                startActivity(favoriteIntent)
            }
        })

        return rootView
    }

    override fun onStart() {
        super.onStart()
        val dialog = dialog
        if (dialog != null){
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.MATCH_PARENT
            dialog.window?.setLayout(width,height)
        }
    }

}