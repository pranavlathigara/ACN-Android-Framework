package acn.android.framework.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

/**
 * Created by ugurcan.yildirim on 10.03.2016.
 */
public class AcnVideoView extends JCVideoPlayer {

    public AcnVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init(){

        JCVideoPlayer.setThumbImageViewScalType(ImageView.ScaleType.CENTER_CROP);

    }

    public void setVideo(String videoURL, String thumbnailURL, String videoTitle, boolean showTitle){

        this.setUp(videoURL, thumbnailURL, videoTitle, showTitle);

    }

    public static void releaseAllVideos(){

        JCVideoPlayer.releaseAllVideos();

    }

}
