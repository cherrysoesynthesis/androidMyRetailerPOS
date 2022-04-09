//package com.dcs.myretailer.app.Setting;
//
//import android.content.Context;
//import android.content.res.Configuration;
//import android.graphics.Canvas;
//import android.graphics.Color;
//import android.graphics.ImageFormat;
//import android.graphics.Paint;
//import android.hardware.Camera;
//import android.media.AudioFormat;
//import android.media.AudioManager;
//import android.media.AudioTrack;
//import android.view.SurfaceHolder;
//import android.view.SurfaceView;
//import android.widget.Toast;
//
//import com.dcs.myretailer.app.Logger;
//import com.google.zxing.BarcodeFormat;
//import com.google.zxing.BinaryBitmap;
//import com.google.zxing.DecodeHintType;
//import com.google.zxing.LuminanceSource;
//import com.google.zxing.MultiFormatReader;
//import com.google.zxing.NotFoundException;
//import com.google.zxing.PlanarYUVLuminanceSource;
//import com.google.zxing.Result;
//import com.google.zxing.common.HybridBinarizer;
//
//import java.io.IOException;
//import java.util.EnumMap;
//import java.util.EnumSet;
//import java.util.Map;
//
//public class BarcodeReaderPreview extends SurfaceView implements SurfaceHolder.Callback, Camera.PreviewCallback{
//    private SurfaceHolder mHolder;
//    private Camera mCamera;
//    private boolean isRunning = false;
//    private OnBarcodeReadSuccess listener;
//    private Map<DecodeHintType,Object> hintmap = new EnumMap<DecodeHintType, Object>(DecodeHintType.class);
//    private MultiFormatReader mfr = new MultiFormatReader();
//    private byte[] soundtone = new byte[0];
//    private AudioTrack at;
//    private long scancooldown = 0;
//    private int tonecycle = 6;
//    private int tonelength = 1000;
//    public interface OnBarcodeReadSuccess{
//        public void ReadSuccess(String text);
//    }
//
//    public BarcodeReaderPreview(Context context) {
//        super(context);
//        at = new AudioTrack(AudioManager.STREAM_MUSIC, 44100, AudioFormat.CHANNEL_OUT_MONO, AudioFormat.ENCODING_PCM_8BIT, 512, AudioTrack.MODE_STREAM);
//        at.play();
//
//        soundtone = new byte[tonelength*tonecycle];
//        for(int i=0;i<tonelength;i++){
//            for(int j=0;j<tonecycle;j++){
//                if((i%2) == 0){
//                    soundtone[(i*tonecycle)+j] = (byte)0x0;
//                }else{
//                    soundtone[(i*tonecycle)+j] = (byte)0xFF;
//                }
//            }
//        }
//
//        hintmap.put(DecodeHintType.TRY_HARDER, Boolean.TRUE);
//        hintmap.put(DecodeHintType.POSSIBLE_FORMATS, EnumSet.allOf(BarcodeFormat.class));
//        hintmap.put(DecodeHintType.PURE_BARCODE, Boolean.FALSE);
//        mfr.setHints(hintmap);
//        mHolder = getHolder();
//        mHolder.addCallback(this);
//    }
//
//    @Override
//    public void surfaceCreated(SurfaceHolder holder) {
//        if(isRunning)return;
//        synchronized(this){
//            try{
//
//                mCamera = Camera.open();
//
//                getResources().getConfiguration();
//                if(getResources().getConfiguration().orientation== Configuration.ORIENTATION_PORTRAIT){
//                    mCamera.setDisplayOrientation(90);
//                }
//
//
//
//                Camera.Parameters params = mCamera.getParameters();
//                params.setPreviewFormat(ImageFormat.NV21);
//                params.setExposureCompensation(params.getMaxExposureCompensation());
//                if(params.isAutoExposureLockSupported()) {
//                    params.setAutoExposureLock(false);
//                }
//
//                for(String p:params.getSupportedSceneModes()){
//                    if(Camera.Parameters.SCENE_MODE_BARCODE == p){
//                        params.setSceneMode(Camera.Parameters.SCENE_MODE_BARCODE);
//                        break;
//                    }
//                }
//
//                for(String p:params.getSupportedFocusModes()){
//                    if(Camera.Parameters.FOCUS_MODE_CONTINUOUS_VIDEO == p){
//                        params.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_VIDEO);
//                        break;
//                    }
//                }
//
//                mCamera.setParameters(params);
//
//                mCamera.setPreviewCallback(this);
//                mCamera.setPreviewDisplay(holder);
//                mCamera.startPreview();
//                isRunning = true;
//
//
//            }catch(IOException e){
//                Logger.WriteLog("BarcodeReaderPreview",e.toString());
//                Toast.makeText(this.getContext(), StrTextConst.GetText(StrTextConst.TextType.GENERAL, 60), Toast.LENGTH_SHORT).show();
//                Canvas c = holder.lockCanvas();
//                Paint p = new Paint();
//                p.setColor(Color.BLACK);
//                c.drawRect(0, 0, c.getWidth(), c.getHeight(), p);
//                holder.unlockCanvasAndPost(c);
//            }catch(RuntimeException e){
//                Logger.WriteLog("BarcodeReaderPreview",e.toString());
//                Toast.makeText(this.getContext(), StrTextConst.GetText(StrTextConst.TextType.GENERAL, 61), Toast.LENGTH_LONG).show();
//                Canvas c = holder.lockCanvas();
//                Paint p = new Paint();
//                p.setColor(Color.BLACK);
//                c.drawRect(0, 0, c.getWidth(), c.getHeight(), p);
//                holder.unlockCanvasAndPost(c);
//            }
//        }
//    }
//
//    @Override
//    public void surfaceChanged(SurfaceHolder holder, int format, int width,int height) {
//        if(mHolder.getSurface()==null){
//            return;
//        }
//
//        try{
//            mCamera.stopPreview();
//        }catch(Exception e){}
//
//        try{
//            Camera.Parameters params = mCamera.getParameters();
//
//            if(getResources().getConfiguration().orientation== Configuration.ORIENTATION_PORTRAIT){
//                mCamera.setDisplayOrientation(90);
//            }
//            params.setPreviewFormat(ImageFormat.NV21);
//
//            params.setExposureCompensation(params.getMaxExposureCompensation());
//            if(params.isAutoExposureLockSupported()) {
//                params.setAutoExposureLock(false);
//            }
//
//            for(String p:params.getSupportedFocusModes()){
//                if(Camera.Parameters.FOCUS_MODE_CONTINUOUS_VIDEO == p){
//                    params.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_VIDEO);
//                    break;
//                }
//            }
//
//            for(String p:params.getSupportedSceneModes()){
//                if(Camera.Parameters.SCENE_MODE_BARCODE == p){
//                    params.setSceneMode(Camera.Parameters.SCENE_MODE_BARCODE);
//                    break;
//                }
//            }
//
//            mCamera.setParameters(params);
//
//            mCamera.setPreviewCallback(this);
//            mCamera.setPreviewDisplay(holder);
//            mCamera.startPreview();
//        }catch(IOException e){
//            //Toast.makeText(this.getContext(), "Failed to open camera!", Toast.LENGTH_SHORT).show();
//        }catch(RuntimeException e){
//            //Toast.makeText(this.getContext(), "Barcode reading is not possible because no camera capable for this device found!", Toast.LENGTH_LONG);
//        }
//    }
//
//    @Override
//    public void surfaceDestroyed(SurfaceHolder holder) {
//        synchronized(this){
//            try{
//                if(mCamera!=null){
//                    mCamera.setPreviewCallback(null);
//                    mCamera.stopPreview();
//                    isRunning = false;
//                    mCamera.release();
//                }
//                if(at!=null){
//                    at.stop();
//                    at.release();
//                }
//            }catch(Exception e){}
//        }
//    }
//
//    public void setOnBarcodeReadSuccessListener(OnBarcodeReadSuccess listener){
//        this.listener = listener;
//    }
//
//
//    @Override
//    public void onPreviewFrame(byte[] data, Camera camera) {
//        if(!isRunning)return;
//
//        Canvas c = null;
//
//        if(mHolder == null){
//            return;
//        }
//
//        try{
//            synchronized(mHolder){
//                if(listener!=null){
//                    if(System.currentTimeMillis()>scancooldown){
//                        Camera.Size s = camera.getParameters().getPreviewSize();
//                        byte[] rotate = rotateYUV420Degree90(data,s.width,s.height);
//
//                        LuminanceSource source = new PlanarYUVLuminanceSource(rotate, s.width, s.height, 0, 0, s.width, s.height, false);
//                        Result r;
//                        try{
//                            BinaryBitmap b = new BinaryBitmap(new HybridBinarizer(source));
//                            r = mfr.decodeWithState(b);
//
//                            if(r!=null){
//                                at.write(soundtone, 0, soundtone.length);
//                                listener.ReadSuccess(r.getText());
//
//
//                            }
//
//                        }catch(NotFoundException e){}
//                        scancooldown = System.currentTimeMillis()+1000;
//                    }
//                }
//            }
//        }catch(Exception e){
//
//        }finally {
//            if(c!=null){
//                mHolder.unlockCanvasAndPost(c);
//            }
//        }
//
//    }
//
//    private byte[] rotateYUV420Degree90(byte[] data, int imageWidth, int imageHeight) {
//        byte [] yuv = new byte[imageWidth*imageHeight*3/2];
//        // Rotate the Y luma
//        int i = 0;
//        for(int x = 0;x < imageWidth;x++)
//        {
//            for(int y = imageHeight-1;y >= 0;y--)
//            {
//                yuv[i] = data[y*imageWidth+x];
//                i++;
//            }
//        }
//        // Rotate the U and V color components
//        i = imageWidth*imageHeight*3/2-1;
//        for(int x = imageWidth-1;x > 0;x=x-2)
//        {
//            for(int y = 0;y < imageHeight/2;y++)
//            {
//                yuv[i] = data[(imageWidth*imageHeight)+(y*imageWidth)+x];
//                i--;
//                yuv[i] = data[(imageWidth*imageHeight)+(y*imageWidth)+(x-1)];
//                i--;
//            }
//        }
//        return yuv;
//    }
//
//}
//
