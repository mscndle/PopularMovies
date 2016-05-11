package com.popularmovies.mcondle.popularmovies.fragment;

import com.popularmovies.mcondle.popularmovies.network.model.Video;

import java.util.List;

/**
 * Created by mandeep.condle on 4/28/16.
 */
public interface VideosAsyncDelegate {

    void asyncComplete(List<Video> videos);
}
