package msk.pobazar.wcquiz.data_remote.repo

import android.content.Context
import android.graphics.drawable.Drawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import msk.pobazar.wcquiz.domain.repo.remote.ImageRepoRemote
import javax.inject.Inject

class ImageRepoRemoteImpl @Inject constructor(
    private val context: Context
) : ImageRepoRemote {

    private val imagesSubject: PublishSubject<Completable> = PublishSubject.create()

    override fun loadImage(url: String): Observable<Completable> {
        Glide.with(context)
            .load(url)
            .listener(
                object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        imagesSubject.onError(Throwable(e))
                        e?.printStackTrace()
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        imagesSubject.onComplete()
                        return false
                    }
                }
            )
            .into(100, 100)
        return imagesSubject
    }
}