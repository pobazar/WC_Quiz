package msk.pobazar.wcquiz.database_firebase.api

import android.content.Context
import android.graphics.drawable.Drawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.google.firebase.storage.StorageReference
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import msk.pobazar.wcquiz.data_remote.api.ImageApi
import timber.log.Timber
import javax.inject.Inject

class ImageApiImpl @Inject constructor(
    private val reference: StorageReference,
    private val context: Context
) : ImageApi {

    override fun getUri(urls: List<String>): Observable<Map<String, String>> {
        val imagesUriSubject: PublishSubject<Map<String, String>> = PublishSubject.create()
        val result = mutableMapOf<String, String>()
        var resultSize = urls.size

        for (url in urls) {
            reference
                .child("$IMAGES_PATH/$url.$FORMAT")
                .downloadUrl
                .addOnSuccessListener { uri ->
                    if (result.containsKey(url))
                        resultSize--
                    else
                        result[url] = uri.toString()
                    if (result.size == resultSize)
                        imagesUriSubject.onNext(result)
                }
                .addOnFailureListener {
                    if (result.containsKey(url))
                        resultSize--
                    else
                        result[url] = ""
                    if (result.size == resultSize)
                        imagesUriSubject.onNext(result)
                    Timber.e(it)
                }
        }

        return imagesUriSubject
    }

    override fun loadImage(uri: String): Completable {
        val imagesSubject: PublishSubject<Completable> = PublishSubject.create()

        Glide.with(context)
            .load(uri)
            .listener(
                object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        imagesSubject.onComplete()
                        Timber.e(e)
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

        return Completable.fromObservable(imagesSubject)
    }

    companion object {
        private const val IMAGES_PATH = "images"
        private const val FORMAT = "jpg"
    }
}

