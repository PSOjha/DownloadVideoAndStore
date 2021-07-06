package com.example.downloadvideo;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.downloader.Error;
import com.downloader.OnCancelListener;
import com.downloader.OnDownloadListener;
import com.downloader.OnPauseListener;
import com.downloader.OnProgressListener;
import com.downloader.OnStartOrResumeListener;
import com.downloader.PRDownloader;
import com.downloader.Progress;
import com.downloader.Status;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private static String dirPathInternal, dirPathExternal;

    private File directory;

    /* final String URL1 = "http://www.appsapk.com/downloading/latest/Facebook-119.0.0.23.70.apk";
     final String URL2 = "http://www.appsapk.com/downloading/latest/WeChat-6.5.7.apk";
     final String URL3 = "http://www.appsapk.com/downloading/latest/Instagram.apk";
     final String URL4 = "http://www.appsapk.com/downloading/latest/Emoji%20Flashlight%20-%20Brightest%20Flashlight%202018-2.0.1.apk";
     final String URL5 = "http://www.appsapk.com/downloading/latest/Screen%20Recorder-7.7.apk";
     final String URL6 = "http://www.appsapk.com/downloading/latest/Call%20Recorder%20-%20Automatic%20Call%20Recorder-1.6.0.apk";
     final String URL7 = "http://www.appsapk.com/downloading/latest/Sound%20Profile%20(+%20volume%20scheduler)-5.25.apk";
     final String URL8 = "http://www.appsapk.com/downloading/latest/Evernote%20-%20stay%20organized.-7.9.7.apk";
     final String URL9 = "http://www.appsapk.com/downloading/latest/UC-Browser.apk";
     final String URL10 = "http://www.appsapk.com/downloading/latest/Barcode%20Scanner-1.2.apk";
     final String URL11 = "http://download.blender.org/peach/bigbuckbunny_movies/BigBuckBunny_640x360.m4v";
     final String URL12 = "http://www2.sdfi.edu.cn/netclass/jiaoan/englit/download/Harry%20Potter%20and%20the%20Sorcerer's%20Stone.pdf";
     final String URL13 = "https://media.giphy.com/media/Bk0CW5frw4qfS/giphy.gif";
     final String URL14 = "http://techslides.com/demos/sample-videos/small.mp4";*/
    final String URL15 = "https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4";

    Button buttonOne, buttonTwo, buttonThree, buttonFour,
            buttonFive, buttonSix, buttonSeven, buttonEight,
            buttonNine, buttonTen, buttonEleven, buttonTwelve,
            buttonThirteen, buttonFourteen, buttonFifteen,
            buttonCancelOne, buttonCancelTwo, buttonCancelThree,
            buttonCancelFour, buttonCancelFive, buttonCancelSix,
            buttonCancelSeven, buttonCancelEight, buttonCancelNine,
            buttonCancelTen, buttonCancelEleven, buttonCancelTwelve,
            buttonCancelThirteen, buttonCancelFourteen, buttonCancelFifteen;

    TextView textViewProgressOne, textViewProgressTwo, textViewProgressThree,
            textViewProgressFour, textViewProgressFive, textViewProgressSix,
            textViewProgressSeven, textViewProgressEight, textViewProgressNine,
            textViewProgressTen, textViewProgressEleven, textViewProgressTwelve,
            textViewProgressThirteen, textViewProgressFourteen, textViewProgressFifteen;

    ProgressBar progressBarOne, progressBarTwo, progressBarThree,
            progressBarFour, progressBarFive, progressBarSix,
            progressBarSeven, progressBarEight, progressBarNine,
            progressBarTen, progressBarEleven, progressBarTwelve,
            progressBarThirteen, progressBarFourteen, progressBarFifteen;

    int downloadIdOne, downloadIdTwo, downloadIdThree, downloadIdFour,
            downloadIdFive, downloadIdSix, downloadIdSeven,
            downloadIdEight, downloadIdNine, downloadIdTen,
            downloadIdEleven, downloadIdTwelve, downloadIdThirteen,
            downloadIdFourteen, downloadIdFifteen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            directory = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "downlod_new");
            directory.setReadable(true, true);
            if (!directory.exists()) {
                directory.mkdirs();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Internally Stored
        dirPathInternal = directory.getAbsolutePath();
        //Internalpath = "/storage/emulated/0/Download/11zon";

        //Externally Stored
        dirPathExternal = "/storage/23DA-19EB/Download";
        init();

        onClickListenerFourteen();
        onClickListenerFifteen();
    }

    private void init() {

        buttonFourteen = findViewById(R.id.buttonFourteen);
        buttonFifteen = findViewById(R.id.buttonFifteen);


        buttonCancelFourteen = findViewById(R.id.buttonCancelFourteen);
        buttonCancelFifteen = findViewById(R.id.buttonCancelFifteen);

        textViewProgressFourteen = findViewById(R.id.textViewProgressFourteen);
        textViewProgressFifteen = findViewById(R.id.textViewProgressFifteen);

        progressBarFourteen = findViewById(R.id.progressBarFourteen);
        progressBarFifteen = findViewById(R.id.progressBarFifteen);
    }


    public void onClickListenerFourteen() {
        buttonFourteen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(MainActivity.this, dirPathInternal, Toast.LENGTH_SHORT).show();
                if (Status.RUNNING == PRDownloader.getStatus(downloadIdFourteen)) {
                    PRDownloader.pause(downloadIdFourteen);
                    return;
                }

                buttonFourteen.setEnabled(false);
                progressBarFourteen.setIndeterminate(true);
                progressBarFourteen.getIndeterminateDrawable().setColorFilter(
                        Color.BLUE, android.graphics.PorterDuff.Mode.SRC_IN);

                if (Status.PAUSED == PRDownloader.getStatus(downloadIdFourteen)) {
                    PRDownloader.resume(downloadIdFourteen);
                    return;
                }
                downloadIdFourteen = PRDownloader.download(URL15, dirPathInternal, "big_buck_bunny_720p_10mb.mp4")
                        .build()
                        .setOnStartOrResumeListener(new OnStartOrResumeListener() {
                            @Override
                            public void onStartOrResume() {
                                progressBarFourteen.setIndeterminate(false);
                                buttonFourteen.setEnabled(true);
                                buttonFourteen.setText(R.string.pause);
                                buttonCancelFourteen.setEnabled(true);
                                buttonCancelFourteen.setText(R.string.cancel);
                            }
                        })
                        .setOnPauseListener(new OnPauseListener() {
                            @Override
                            public void onPause() {
                                buttonFourteen.setText(R.string.resume);
                            }
                        })
                        .setOnCancelListener(new OnCancelListener() {
                            @Override
                            public void onCancel() {
                                downloadIdFourteen = 0;
                                buttonFourteen.setText(R.string.start);
                                buttonCancelFourteen.setEnabled(false);
                                progressBarFourteen.setProgress(0);
                                textViewProgressFourteen.setText("");
                                progressBarFourteen.setIndeterminate(false);
                            }
                        })
                        .setOnProgressListener(new OnProgressListener() {
                            @Override
                            public void onProgress(Progress progress) {
                                long progressPercent = progress.currentBytes * 100 / progress.totalBytes;
                                progressBarFourteen.setProgress((int) progressPercent);
                                textViewProgressFourteen.setText(Utils.getProgressDisplayLine(progress.currentBytes, progress.totalBytes));
                            }
                        })
                        .start(new OnDownloadListener() {
                            @Override
                            public void onDownloadComplete() {
                                buttonFourteen.setEnabled(false);
                                buttonCancelFourteen.setEnabled(false);
                                buttonFourteen.setText(R.string.completed);
                            }


                            public void onError(Error error) {
                                buttonFourteen.setText(R.string.start);
                                Toast.makeText(getApplicationContext(), getString(R.string.some_error_occurred) + " " + "14", Toast.LENGTH_SHORT).show();
                                textViewProgressFourteen.setText("");
                                progressBarFourteen.setProgress(0);
                                downloadIdFourteen = 0;
                                buttonCancelFourteen.setEnabled(false);
                                progressBarFourteen.setIndeterminate(false);
                                buttonFourteen.setEnabled(true);
                            }
                        });
            }
        });

        buttonCancelFourteen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PRDownloader.cancel(downloadIdFourteen);
            }
        });

    }

    public void onClickListenerFifteen() {
        buttonFifteen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, dirPathExternal, Toast.LENGTH_SHORT).show();
                if (Status.RUNNING == PRDownloader.getStatus(downloadIdFifteen)) {
                    PRDownloader.pause(downloadIdFifteen);
                    return;
                }

                buttonFifteen.setEnabled(false);
                progressBarFifteen.setIndeterminate(true);
                progressBarFifteen.getIndeterminateDrawable().setColorFilter(
                        Color.BLUE, android.graphics.PorterDuff.Mode.SRC_IN);

                if (Status.PAUSED == PRDownloader.getStatus(downloadIdFifteen)) {
                    PRDownloader.resume(downloadIdFifteen);
                    return;
                }

                downloadIdFifteen = PRDownloader.download(URL15, dirPathExternal, "big_buck_bunny_720p_10mb.mp4")
                        .build()
                        .setOnStartOrResumeListener(new OnStartOrResumeListener() {
                            @Override
                            public void onStartOrResume() {
                                progressBarFifteen.setIndeterminate(false);
                                buttonFifteen.setEnabled(true);
                                buttonFifteen.setText(R.string.pause);
                                buttonCancelFifteen.setEnabled(true);
                                buttonCancelFifteen.setText(R.string.cancel);
                            }
                        })
                        .setOnPauseListener(new OnPauseListener() {
                            @Override
                            public void onPause() {
                                buttonFifteen.setText(R.string.resume);
                            }
                        })
                        .setOnCancelListener(new OnCancelListener() {
                            @Override
                            public void onCancel() {
                                downloadIdFifteen = 0;
                                buttonFifteen.setText(R.string.start);
                                buttonCancelFifteen.setEnabled(false);
                                progressBarFifteen.setProgress(0);
                                textViewProgressFifteen.setText("");
                                progressBarFifteen.setIndeterminate(false);
                            }
                        })
                        .setOnProgressListener(new OnProgressListener() {
                            @Override
                            public void onProgress(Progress progress) {
                                long progressPercent = progress.currentBytes * 100 / progress.totalBytes;
                                progressBarFifteen.setProgress((int) progressPercent);
                                textViewProgressFifteen.setText(Utils.getProgressDisplayLine(progress.currentBytes, progress.totalBytes));
                            }
                        })
                        .start(new OnDownloadListener() {
                            @Override
                            public void onDownloadComplete() {
                                buttonFifteen.setEnabled(false);
                                buttonCancelFifteen.setEnabled(false);
                                buttonFifteen.setText(R.string.completed);
                            }

                            @Override
                            public void onError(Error error) {
                                buttonFifteen.setText(R.string.start);
                                Toast.makeText(getApplicationContext(), getString(R.string.some_error_occurred) + " " + "15", Toast.LENGTH_SHORT).show();
                                textViewProgressFifteen.setText("");
                                progressBarFifteen.setProgress(0);
                                downloadIdFifteen = 0;
                                buttonCancelFifteen.setEnabled(false);
                                progressBarFifteen.setIndeterminate(false);
                                buttonFifteen.setEnabled(true);
                            }
                        });
            }
        });

        buttonCancelFifteen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PRDownloader.cancel(downloadIdFifteen);
            }
        });
    }

}