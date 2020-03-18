package msk.pobazar.wcquiz.data_remote.repo

import android.content.Context
import com.bumptech.glide.Glide
import io.reactivex.Completable
import io.reactivex.Observable
import msk.pobazar.wcquiz.domain.repo.remote.ImageRepoRemote
import javax.inject.Inject


class ImageRepoRemoteImpl @Inject constructor(
    private val context: Context
) : ImageRepoRemote {
    override fun loadImage(url: String): Observable<Completable> {
        return Observable.fromCallable {
//            Glide.with(context)
//                .load(url)
            Completable.complete()
//            .listener(
//                object : RequestListener<Drawable> {
//                    override fun onLoadFailed(
//                        e: GlideException?,
//                        model: Any?,
//                        target: Target<Drawable>?,
//                        isFirstResource: Boolean
//                    ): Boolean {
//                        it.onError(Throwable(e))
//                        e?.printStackTrace()
//                        return false
//                    }
//
//                    override fun onResourceReady(
//                        resource: Drawable?,
//                        model: Any?,
//                        target: Target<Drawable>?,
//                        dataSource: DataSource?,
//                        isFirstResource: Boolean
//                    ): Boolean {
//                        it.onError(Throwable())
//                        return false
//                    }
//                }
//            )
        }
    }
}